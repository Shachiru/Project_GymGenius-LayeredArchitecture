package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginFormController {

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Hyperlink hyperSignUp;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblPasswordValidate;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblUsernameValidate;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private PasswordField txtPw;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginOnAction(ActionEvent event) {

    }

    @FXML
    void linkSignUpOnAction(ActionEvent event) {

    }

}
