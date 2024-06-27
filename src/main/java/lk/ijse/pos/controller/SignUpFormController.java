package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SignUpFormController {

    @FXML
    private JFXButton btnSignUp;
    @FXML
    private AnchorPane rootNode;
    @FXML
    private PasswordField txtPw;
    @FXML
    private TextField txtUserId;
    @FXML
    private TextField txtUsername;

    @FXML
    void btnSignUpOnAction(ActionEvent event) {

    }

}
