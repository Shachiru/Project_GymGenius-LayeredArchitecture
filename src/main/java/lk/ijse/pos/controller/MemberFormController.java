package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.pos.dao.custom.impl.MemberDAOImpl;


import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class MemberFormController {

    @FXML
    private TableColumn<String, String> colMemberAddress;

    @FXML
    private TableColumn<String, String> colMemberDOB;

    @FXML
    private TableColumn<String, String> colMemberId;

    @FXML
    private TableColumn<String, String> colMemberMobile;

    @FXML
    private TableColumn<String, String> colMemberName;

    @FXML
    private TableColumn<String, String> colMemberGender;

    @FXML
    private Pane pagingPane;

    @FXML
    private DatePicker pickerDate;

    @FXML
    private TableView<MemberTm> tblMember;

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
    private Label lblMAddress;

    @FXML
    private Label lblMemberName;

    @FXML
    private Label lblMemberGender;

    @FXML
    private Label lblMemberMobile;

    MemberRepo memberRepo = new MemberRepo();

    private List<Member> memberList = new ArrayList<>();

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtMemberId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtMobileNo.setText("");
        pickerDate.setValue(null);
        txtGender.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtMemberId.getText();
        MemberRepo memberRepo = new MemberRepo();

        try {
            boolean isDeleted = memberRepo.deleteMember(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Member deleted").show();
                loadMemberTable();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String id = txtMemberId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String mobile = txtMobileNo.getText();
        String dob = String.valueOf(pickerDate.getValue());
        String gender = txtGender.getText();

        Member member = new Member(id, name, address, mobile, dob, gender);

        if (DataValidateController.validateMemberGender(txtGender.getText())) {
            lblMemberGender.setText(" ");

            if (DataValidateController.validateMemberMobile(txtMobileNo.getText())) {
                lblMemberMobile.setText(" ");

                if (DataValidateController.validateMemberAddress(txtAddress.getText())) {
                    lblMAddress.setText(" ");

                    if (DataValidateController.validateMemberName(txtName.getText())) {
                        lblMemberName.setText(" ");

                        try {
                            boolean isSaved = MemberRepo.saveMember(member);
                            if (isSaved) {
                                new Alert(Alert.AlertType.CONFIRMATION, "Member saved").show();
                                loadMemberTable();
                            }
                        } catch (SQLException e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                        }
                    } else {
                        lblMemberName.setText("Invalid Name");
                    }
                } else {
                    lblMAddress.setText("Invalid Address");
                }
            } else {
                lblMemberMobile.setText("Invalid Mobile");
            }
        } else {
            lblMemberGender.setText("Invalid Gender");
        }
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

        MemberRepo memberRepo = new MemberRepo();
        ObservableList<MemberTm> tmList = FXCollections.observableArrayList();
        try {
            List<Member> memberList = memberRepo.getMember();
            for (Member member : memberList) {
                MemberTm memberTm = new MemberTm(
                        member.getId(),
                        member.getName(),
                        member.getAddress(),
                        member.getMobile(),
                        member.getDob(),
                        member.getGender()
                );
                tmList.add(memberTm);
            }
            tblMember.setItems(tmList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }

    private List<Member> getAllMember() {
        List<Member> memberList = null;
        try {
            memberList = MemberRepo.getMember();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return memberList;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtMemberId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String mobile = txtMobileNo.getText();
        String dob = String.valueOf(pickerDate.getValue());
        String gender = txtGender.getText();

        Member member = new Member(id, name, address, mobile, dob, gender);

        try {
            boolean isUpdated = MemberRepo.updateMember(member);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Member updated").show();
                loadMemberTable();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void initialize() throws ClassNotFoundException {
        MemberDAOImpl memberDAO = new MemberDAOImpl();
        try {
            txtMemberId.setText(memberDAO.generateNextId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setCellValueFactory();
        loadMemberTable();
        this.memberList = getAllMember();
    }

    @FXML
    void tableClick(MouseEvent mouseEvent) {
        TablePosition pos = tblMember.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ObservableList<TableColumn<MemberTm, ?>> columns = tblMember.getColumns();

        txtMemberId.setText(columns.get(0).getCellData(row).toString());
        txtName.setText(columns.get(1).getCellData(row).toString());
        txtAddress.setText(columns.get(2).getCellData(row).toString());
        txtMobileNo.setText(columns.get(3).getCellData(row).toString());
        pickerDate.setValue(LocalDate.parse(columns.get(4).getCellData(row).toString()));
        txtGender.setText(columns.get(5).getCellData(row).toString());

    }

    @FXML
    void txtKeyOnReleased(KeyEvent event) {
        ValidateUtil.validation(map);
    }
}