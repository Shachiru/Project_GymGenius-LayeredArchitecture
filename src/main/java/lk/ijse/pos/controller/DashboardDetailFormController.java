package lk.ijse.pos.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

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

}
