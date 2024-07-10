package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.MemberBO;
import lk.ijse.pos.bo.custom.SupplementBO;
import lk.ijse.pos.dto.MemberDTO;
import lk.ijse.pos.dto.SupplementDTO;
import lk.ijse.pos.entity.Member;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
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
    private TableView<?> tblShopCart;
    @FXML
    private TextField txtQty;
    @FXML
    private TextField txtTotal;

    MemberBO memberBO = (MemberBO) BOFactory.getInstance().getBOType(BOFactory.BOType.MEMBER);
    SupplementBO supplementBO = (SupplementBO) BOFactory.getInstance().getBOType(BOFactory.BOType.SUPPLEMENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getMemberId();
        getSupplementId();
        setDate();
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

    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

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
//// order id ekk generate wenna ona initialize eke