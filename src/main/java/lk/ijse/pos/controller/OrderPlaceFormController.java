package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.MemberBO;
import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.bo.custom.SupplementBO;
import lk.ijse.pos.dto.MemberDTO;
import lk.ijse.pos.dto.SupplementDTO;
import lk.ijse.pos.entity.Member;
import lk.ijse.pos.tdm.OrderTM;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrderPlaceFormController implements Initializable {
    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnPlaceOrder;
    @FXML
    private JFXComboBox<String> cmbMemberId;
    @FXML
    private JFXComboBox<String> cmbSupplementId;
    @FXML
    private TableColumn<?, ?> colAction;
    @FXML
    private TableColumn<?, ?> colDesc;
    @FXML
    private TableColumn<?, ?> colQty;
    @FXML
    private TableColumn<?, ?> colSupplementId;
    @FXML
    private TableColumn<?, ?> colTotal;
    @FXML
    private TableColumn<?, ?> colUnitPrice;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblMemberName;
    @FXML
    private Label lblOrderDate;
    @FXML
    private Label lblOrderId;
    @FXML
    private Label lblQtyOnHand;
    @FXML
    private Label lblUnitPrice;
    @FXML
    private Pane pagingPane;
    @FXML
    private TableView<OrderTM> tblShopCart;
    @FXML
    private TextField txtQty;
    @FXML
    private TextField txtTotal;

    private ObservableList<OrderTM> cartList = FXCollections.observableArrayList();
    private double netTotal = 0;

    MemberBO memberBO = (MemberBO) BOFactory.getInstance().getBOType(BOFactory.BOType.MEMBER);
    SupplementBO supplementBO = (SupplementBO) BOFactory.getInstance().getBOType(BOFactory.BOType.SUPPLEMENT);
    OrderBO orderBO = (OrderBO) BOFactory.getInstance().getBOType(BOFactory.BOType.ORDER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            lblOrderId.setText(generateOrderID());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        setCellValueFactory();
        getMemberId();
        getSupplementId();
        setDate();
    }

    public String generateOrderID() throws SQLException,ClassNotFoundException{
        try {
            ResultSet rst = orderBO.generateNextIdOrder();
            String currentOrderId = "";
            if (rst.next()) {
                currentOrderId = rst.getString(1);
                return nextOrderId(currentOrderId);
            }
            return nextOrderId(null);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextOrderId(String currentOrderId){
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("O ");
            int orderID = Integer.parseInt(split[1]);
            orderID++;
            return "O " + orderID;
        }
        return "O 1";
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblOrderDate.setText(String.valueOf(now));
    }

    private void getSupplementId() {
        ObservableList<String> supplementList = FXCollections.observableArrayList();
        try {
            List<String> supplementIdList = supplementBO.getSupIds();
            for (String sId : supplementIdList) {
                supplementList.add(sId);
            }
            cmbSupplementId.setItems(supplementList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getMemberId() {
        ObservableList<String> memberList = FXCollections.observableArrayList();
        try {
            List<String> memberIdList = memberBO.getMemIds();
            for (String memberId : memberIdList) {
                memberList.add(memberId);
            }
            cmbMemberId.setItems(memberList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colSupplementId.setCellValueFactory(new PropertyValueFactory<>("supplement_id"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String supplementId = cmbSupplementId.getValue();
        String desc = lblDescription.getText();
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double total = qty * unitPrice;
        JFXButton btnRemove = new JFXButton("Remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int selectedIndex = tblShopCart.getSelectionModel().getSelectedIndex();
                cartList.remove(selectedIndex);

                tblShopCart.refresh();
                calculateNetTotal();
            }
        });

        for (int i = 0; i < tblShopCart.getItems().size(); i++) {
            if (supplementId.equals(colSupplementId.getCellData(i))) {
                qty += cartList.get(i).getQty();
                total = unitPrice * qty;

                cartList.get(i).setQty(qty);
                cartList.get(i).setTotal(total);

                tblShopCart.refresh();
                calculateNetTotal();
                txtQty.setText("");
                return;
            }
        }

        OrderTM orderTm = new OrderTM(supplementId, desc, unitPrice, qty, total, btnRemove);
        cartList.add(orderTm);

        tblShopCart.setItems(cartList);
        txtQty.setText("");
        calculateNetTotal();
    }

    private void calculateNetTotal() {
        netTotal = 0;
        for (int i = 0; i < tblShopCart.getItems().size(); i++) {
            netTotal += (double) colTotal.getCellData(i);
        }
        txtTotal.setText(String.valueOf(netTotal));
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        /*String orderId = lblOrderId.getText();
        Date date = Date.valueOf(lblOrderDate.getText());
        String memberId = cmbMemberId.getValue();

        double orderAmount = 0;
        Order order = new Order(orderId, date, memberId);
        List<OrderDetail> orderList = new ArrayList<>();

        for (int i = 0; i < tblShopCart.getItems().size(); i++) {
            OrderTm orderTm = cartList.get(i);

            OrderDetail orderDetail = new OrderDetail(
                    orderId,
                    orderTm.getSupplement_id(),
                    orderTm.getQty(),
                    orderTm.getUnitPrice(),
                    orderTm.getQty() * orderTm.getUnitPrice()
            );
            orderList.add(orderDetail);
        }
        PlaceOrder placeOrder = new PlaceOrder(order, orderList);

        try {
            boolean isOrderPlaced = PlaceOrderRepo.orderPlaced(placeOrder);
            if (isOrderPlaced) {
                ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
                Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Order Successfully.. Do you want print a bill ?", yes, no).showAndWait();

                if (result.orElse(no) == yes) {
                    JasperDesign jasperDesign =
                            JRXmlLoader.load("src/main/resources/view/reports/GymBill.jrxml");

                    JRDesignQuery jrDesignQuery = new JRDesignQuery();
                    jrDesignQuery.setText("SELECT * FROM orders o INNER JOIN order_detail od ON o.o_id = od.order_id WHERE o.o_id = (SELECT MAX(o.o_id) FROM orders) ORDER BY od.order_id DESC LIMIT 1");

                    jasperDesign.setQuery(jrDesignQuery);

                    JasperReport jasperReport =
                            JasperCompileManager.compileReport(jasperDesign);

                    JasperPrint jasperPrint =
                            JasperFillManager.fillReport(
                                    jasperReport,
                                    null,
                                    DbConnection.getInstance().getConnection());

                    JasperViewer.viewReport(jasperPrint,false);
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Something went Wrong").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }*/
    }

    @FXML
    void cmbMemberIdOnAction(ActionEvent event) {
        String memberId = cmbMemberId.getValue();
        try {
            MemberDTO member = memberBO.searchMember(memberId);
            lblMemberName.setText(member.getName());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbSupplementIdOnAction(ActionEvent event) {
        String supplementId = cmbSupplementId.getValue();
        try {
            SupplementDTO supplement = supplementBO.searchSupplement(supplementId);
            if (supplement != null) {
                lblDescription.setText(supplement.getProductName());
                lblUnitPrice.setText(supplement.getUnitPrice());
                lblQtyOnHand.setText(supplement.getQty());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}