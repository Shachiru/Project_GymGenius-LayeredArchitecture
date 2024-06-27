package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class SupplementFormController {

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
    private TableView<?> tblSupplement;
    @FXML
    private TextField txtProductName;
    @FXML
    private TextField txtQty;
    @FXML
    private TextField txtSupplementId;
    @FXML
    private TextField txtUnitPrice;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void supplementTableClick(MouseEvent event) {

    }

}
