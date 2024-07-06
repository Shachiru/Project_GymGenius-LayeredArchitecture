package lk.ijse.pos.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.SupplementBO;
import lk.ijse.pos.dto.SupplementDTO;
import lk.ijse.pos.tdm.SupplementTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplementFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colProductName;
    @FXML
    private TableColumn<?, ?> colQty;
    @FXML
    private TableColumn<?, ?> colSupplementId;
    @FXML
    private TableColumn<?, ?> colUnitPrice;
    @FXML
    private Label lblSupName;
    @FXML
    private Label lblSupQTY;
    @FXML
    private Label lblUnitPrice;
    @FXML
    private Pane pagingPane;
    @FXML
    private TableView<SupplementTM> tblSupplement;
    @FXML
    private TextField txtProductName;
    @FXML
    private TextField txtQty;
    @FXML
    private TextField txtSupplementId;
    @FXML
    private TextField txtUnitPrice;

    SupplementBO supplementBO = (SupplementBO) BOFactory.getInstance().getBOType(BOFactory.BOType.SUPPLEMENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            txtSupplementId.setText(generateSupID());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        loadSupplementTable();
        setCellValueFactory();
        initUI();
    }

    private String generateSupID() {
        try {
            ResultSet rst = supplementBO.generateNextIdSupplement();
            String currentSupID = "";
            if (rst.next()){
                currentSupID = rst.getString(1);
                return nextSupID(currentSupID);
            }
            return nextSupID(null);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextSupID(String currentSupID) {
        if (currentSupID != null) {
            String [] split = currentSupID.split("Sup ");
            int supId = Integer.parseInt(split[1]);
            supId++;
            return "Sup " + supId;
        }
        return "Sup 1";
    }

    private void initUI() {
        txtSupplementId.setDisable(true);
    }

    private void setCellValueFactory() {
        colSupplementId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void loadSupplementTable() {
        try {
            ArrayList<SupplementDTO> allSupplement = supplementBO.getAllSupplement();
            for (SupplementDTO supplementDTO: allSupplement) {
                tblSupplement.getItems().add(new SupplementTM(
                        supplementDTO.getId(),
                        supplementDTO.getProductName(),
                        supplementDTO.getUnitPrice(),
                        supplementDTO.getQty()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtSupplementId.setText("");
        txtProductName.setText("");
        txtUnitPrice.setText("");
        txtQty.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtSupplementId.getText();
        try {
            boolean isDeleted = supplementBO.deleteSupplement(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted");
                loadSupplementTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtSupplementId.getText();
        String productName = txtProductName.getText();
        String unitPrice = txtUnitPrice.getText();
        String qty = txtQty.getText();
        try {
            boolean isSaved = supplementBO.saveSupplement(new SupplementDTO(id, productName, unitPrice, qty));
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved");
                loadSupplementTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtSupplementId.getText();
        String productName = txtProductName.getText();
        String unitPrice = txtUnitPrice.getText();
        String qty = txtQty.getText();

        try {
            boolean isUpdated = supplementBO.updateSupplement(new SupplementDTO(id, productName, unitPrice, qty));
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated");
                loadSupplementTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void supplementTableClick(MouseEvent event) {
        TablePosition pos = tblSupplement.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ObservableList<TableColumn<SupplementTM,?> > columns = tblSupplement.getColumns();

        txtSupplementId.setText(columns.get(0).getCellData(row).toString());
        txtProductName.setText(columns.get(1).getCellData(row).toString());
        txtUnitPrice.setText(columns.get(2).getCellData(row).toString());
        txtQty.setText(columns.get(3).getCellData(row).toString());
    }

}
