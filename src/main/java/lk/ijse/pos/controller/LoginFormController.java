package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.MemberBO;
import lk.ijse.pos.bo.custom.impl.MemberBOImpl;
import lk.ijse.pos.dao.custom.impl.UserDAOImpl;
import lk.ijse.pos.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

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
    void btnLoginOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        // bo eka through call krnn ona

    }

    @FXML
    void linkSignUpOnAction(ActionEvent event) throws IOException {

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/signup_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("SignUp Form");

        stage.show();
    }

}
