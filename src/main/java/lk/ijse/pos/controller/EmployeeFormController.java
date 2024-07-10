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
import lk.ijse.pos.bo.custom.EmployeeBO;
import lk.ijse.pos.dto.EmployeeDTO;
import lk.ijse.pos.tdm.EmployeeTM;
import lk.ijse.pos.tdm.MemberTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

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
    private TableView<EmployeeTM> tblEmployee;
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

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBOType(BOFactory.BOType.EMPLOYEE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            txtEmpId.setText(generateEmployeeId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadEmployeeTable();
        setCellValueFactory();
        initUI();
    }

    public String generateEmployeeId() throws SQLException, ClassNotFoundException {
        try {
            ResultSet rst = employeeBO.generateNextIdEmployee();
            String currentEmpId = "";
            if (rst.next()){
                currentEmpId = rst.getString(1);
                return nextEmpId(currentEmpId);
            }
            return nextEmpId(null);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextEmpId(String currentEmpId) {
        if (currentEmpId != null) {
            String[] split = currentEmpId.split("Emp ");
            int empId = Integer.parseInt(split[1]);
            empId++;
            return "Emp " + empId;
        }
        return "Emp 1";
    }

    private void initUI() {
        txtEmpId.setDisable(true);
    }

    private void loadEmployeeTable() {
        ObservableList<EmployeeTM> tmList = FXCollections.observableArrayList();
        try {
            ArrayList<EmployeeDTO> allEmployee = employeeBO.getAllEmployee();
            for (EmployeeDTO employeeDTO : allEmployee) {
                EmployeeTM employeeTM = new EmployeeTM(
                        employeeDTO.getId(),
                        employeeDTO.getName(),
                        employeeDTO.getAddress(),
                        employeeDTO.getMobile(),
                        employeeDTO.getEmpRole(),
                        employeeDTO.getUserId());
                tmList.add(employeeTM);
            }
            tblEmployee.setItems(tmList);
            tblEmployee.refresh();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmpMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colEmpRole.setCellValueFactory(new PropertyValueFactory<>("empRole"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtEmpId.setText("");
        txtEmpName.setText("");
        txtEmpAddress.setText("");
        txtEmpMobile.setText("");
        txtEmpRole.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtEmpId.getText();
        try {
            boolean isDeleted = employeeBO.deleteEmployee(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").show();
                loadEmployeeTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = txtEmpId.getText();
        String name = txtEmpName.getText();
        String address = txtEmpAddress.getText();
        String mobile = txtEmpMobile.getText();
        String role = txtEmpRole.getText();
        String userId = "U001";
        try {
            boolean isSaved = employeeBO.saveEmployee(new EmployeeDTO(id, name, address, mobile, role, userId));
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved").show();
                tblEmployee.getItems().add(new EmployeeTM(id,name,address,mobile,role,userId));
                tblEmployee.refresh();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Enter update button").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtEmpId.getText();
        String name = txtEmpName.getText();
        String address = txtEmpAddress.getText();
        String mobile = txtEmpMobile.getText();
        String role = txtEmpRole.getText();
        String userId = "U001";
        try {
            boolean isUpdated = employeeBO.updateEmployee(new EmployeeDTO(id,name,address,mobile,role,userId));
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated").show();
                loadEmployeeTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void empTableClick(MouseEvent event) {
        TablePosition pos = tblEmployee.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ObservableList<TableColumn<EmployeeTM,?>> columns = tblEmployee.getColumns();

        txtEmpId.setText(columns.get(0).getCellData(row).toString());
        txtEmpName.setText(columns.get(1).getCellData(row).toString());
        txtEmpAddress.setText(columns.get(2).getCellData(row).toString());
        txtEmpMobile.setText(columns.get(3).getCellData(row).toString());
        txtEmpRole.setText(columns.get(4).getCellData(row).toString());

    }

    @FXML
    void txtKeyOnReleased(KeyEvent event) {
    }

}


