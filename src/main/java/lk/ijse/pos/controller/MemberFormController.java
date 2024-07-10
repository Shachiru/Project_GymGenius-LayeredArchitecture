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
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.MemberBO;
import lk.ijse.pos.dto.MemberDTO;
import lk.ijse.pos.entity.Member;
import lk.ijse.pos.tdm.EmployeeTM;
import lk.ijse.pos.tdm.MemberTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MemberFormController implements Initializable {


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

    MemberBO memberBO = (MemberBO) BOFactory.getInstance().getBOType(BOFactory.BOType.MEMBER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            txtMemberId.setText(generateMemId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        loadMemberTable();
        setCellValueFactory();
        initUI();
    }

    private void initUI() {
        txtMemberId.setDisable(true);
    }

    private void loadMemberTable() {
        ObservableList<MemberTM> tmList = FXCollections.observableArrayList();
        try {
            ArrayList<MemberDTO> allMember = memberBO.getAllMember();
            for (MemberDTO memberDTO : allMember){
                MemberTM memberTM = new MemberTM(
                        memberDTO.getId(),
                        memberDTO.getName(),
                        memberDTO.getAddress(),
                        memberDTO.getMobile(),
                        memberDTO.getDob(),
                        memberDTO.getGender());
                tmList.add(memberTM);
            }
            tblMember.setItems(tmList);
            tblMember.refresh();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
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
        try {
            boolean isDeleted = memberBO.deleteMember(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").show();
                loadMemberTable();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
        try {
            boolean isSaved = memberBO.saveMember(new MemberDTO(id, name, address, mobile, dob, gender));
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved!").show();
                tblMember.getItems().add(new MemberTM(id, name, address, mobile, dob, gender));
                tblMember.refresh();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtMemberId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String mobile = txtMobileNo.getText();
        String dob = String.valueOf(pickerDate.getValue());
        String gender = txtGender.getText();
        try {
            boolean isUpdated = memberBO.updateMember(new MemberDTO(id, name, address, mobile, dob, gender));
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated!").show();
                loadMemberTable();
            }
        } catch (SQLException  | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void tableClick(MouseEvent event) {
        TablePosition pos = tblMember.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ObservableList<TableColumn<MemberTM,?> > columns = tblMember.getColumns();

        txtMemberId.setText(columns.get(0).getCellData(row).toString());
        txtName.setText(columns.get(1).getCellData(row).toString());
        txtAddress.setText(columns.get(2).getCellData(row).toString());
        txtMobileNo.setText(columns.get(3).getCellData(row).toString());
        pickerDate.setValue(LocalDate.parse(columns.get(4).getCellData(row).toString()));
        txtGender.setText(columns.get(5).getCellData(row).toString());
    }

    @FXML
    void txtKeyOnReleased(KeyEvent event) {

    }

    private void setCellValueFactory() {
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMemberAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMemberMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colMemberDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colMemberGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    private String generateMemId() {
        try {
            ResultSet rst = memberBO.generateNextIdMember();
            String currentMemberId = "";
            if (rst.next()){
                currentMemberId = rst.getString(1);
                return nextMemId(currentMemberId);
            }
            return nextMemId(null);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
