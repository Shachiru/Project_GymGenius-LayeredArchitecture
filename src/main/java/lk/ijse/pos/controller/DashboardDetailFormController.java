package lk.ijse.pos.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.EmployeeBO;
import lk.ijse.pos.bo.custom.MemberBO;
import lk.ijse.pos.bo.custom.SupplementBO;

import java.sql.SQLException;

public class DashboardDetailFormController {

    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private Label lblEmployeeCount;
    @FXML
    private Label lblMemberCount;
    @FXML
    private Label lblSupplements;
    @FXML
    private Pane pagingPane;
    @FXML
    private PieChart pieChart;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBOType(BOFactory.BOType.EMPLOYEE);
    MemberBO memberBO = (MemberBO) BOFactory.getInstance().getBOType(BOFactory.BOType.MEMBER);
    SupplementBO supplementBO = (SupplementBO) BOFactory.getInstance().getBOType(BOFactory.BOType.SUPPLEMENT);

    public void initialize() throws SQLException {
        setPieChart();
        setDataToBarChart();
        countMembers();
        countEmployee();
        countSupplement();
    }

    private void countSupplement() {
        try {
            int count = supplementBO.countSupplement();
            lblSupplements.setText(String.valueOf(count));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void countEmployee() {
        try {
            int count = employeeBO.countEmployee();
            lblEmployeeCount.setText(String.valueOf(count));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void countMembers() {
        try {
            int count = memberBO.countMember();
            lblMemberCount.setText(String.valueOf(count));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDataToBarChart() {

    }

    private void setPieChart() {

    }
}
