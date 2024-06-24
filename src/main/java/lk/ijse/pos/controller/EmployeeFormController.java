package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class EmployeeFormController {

    @FXML
    private TableColumn<?, ?> colEmpAddress;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colEmpMobile;

    @FXML
    private TableColumn<?, ?> colEmpName;

    @FXML
    private TableColumn<?, ?> colEmpRole;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblMobile;

    @FXML
    private Label lblName;

    @FXML
    private Label lblRole;

    @FXML
    private Pane pagingPane;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private TextField txtEmpAddress;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtEmpMobile;

    @FXML
    private TextField txtEmpName;

    @FXML
    private TextField txtEmpRole;

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
    void empTableClick(MouseEvent event) {

    }

    @FXML
    void txtKeyOnReleased(KeyEvent event) {

    }

}
