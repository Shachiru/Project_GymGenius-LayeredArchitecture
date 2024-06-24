package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MemberFormController {

    @FXML
    private TableColumn<?, ?> colMemberAddress;

    @FXML
    private TableColumn<?, ?> colMemberDOB;

    @FXML
    private TableColumn<?, ?> colMemberGender;

    @FXML
    private TableColumn<?, ?> colMemberId;

    @FXML
    private TableColumn<?, ?> colMemberMobile;

    @FXML
    private TableColumn<?, ?> colMemberName;

    @FXML
    private Label lblMAddress;

    @FXML
    private Label lblMemberGender;

    @FXML
    private Label lblMemberMobile;

    @FXML
    private Label lblMemberName;

    @FXML
    private Pane pagingPane;

    @FXML
    private DatePicker pickerDate;

    @FXML
    private TableView<?> tblMember;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtMobileNo;

    @FXML
    private TextField txtName;

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
    void tableClick(MouseEvent event) {

    }

    @FXML
    void txtKeyOnReleased(KeyEvent event) {

    }

}
