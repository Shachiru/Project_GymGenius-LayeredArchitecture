package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.MemberBO;
import lk.ijse.pos.dto.MemberDTO;
import lk.ijse.pos.tdm.MemberTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MemberFormController implements Initializable {

    MemberBO memberBO = (MemberBO) BOFactory.getInstance().getBOType(BOFactory.BOType.MEMBER);

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
    private TableView<MemberTM> tblMember;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateMemId();
        loadMemberTable();
        setCellValueFactory();

    }

    private void setCellValueFactory() {
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMemberAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMemberMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colMemberDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colMemberGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    private void loadMemberTable() {
        try {
            ArrayList<MemberDTO> allMember = memberBO.getAllMember();
            for (MemberDTO memberDTO : allMember){
                tblMember.getItems().add(new MemberTM(
                        memberDTO.getId(),
                        memberDTO.getName(),
                        memberDTO.getAddress(),
                        memberDTO.getMobile(),
                        memberDTO.getDob(),
                        memberDTO.getGender()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateMemId() {
        try {
            ResultSet rst = memberBO.generateNextIdMember();
            String currentMemberId = rst.getString(1);
            if (rst.next()){
                currentMemberId = rst.getString(1);
                return nextMemId(currentMemberId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private String nextMemId(String currentMemberId) {
        if(currentMemberId != null){
            String [] split = currentMemberId.split("M ");
            int memberId = Integer.parseInt(split[1]);
            memberId++;
            return "M " + memberId;
        }
        return "M 1";
    }
}
