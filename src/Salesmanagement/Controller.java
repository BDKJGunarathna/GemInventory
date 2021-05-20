package Salesmanagement;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private Button adbuttonID;

    @FXML
    public void showOrderOnAction(ActionEvent event) {

        try {
            Parent root1 = FXMLLoader.load((getClass().getResource("/Salesmanagement/addForm.fxml")));
            Stage stage = (Stage) adbuttonID.getScene().getWindow();
            stage.setTitle("sales details");
            stage.setScene(new Scene(root1, 600, 400));
            stage.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }




    @FXML
    private LineChart<String,Number> chart;

    @FXML
    private CategoryAxis MID;

    @FXML
    private NumberAxis NID;

    @FXML
    private TableView<sellers> tableID;

    @FXML
    private TableColumn<sellers, String> fxid;

    @FXML
    private TableColumn<sellers,String> fxname;

    @FXML
    private TableColumn<sellers, Integer> fxno;

    @FXML
    private TableColumn<sellers, Float> fxamount;

    @FXML
    private TableColumn<sellers, Float> fxcomis;

    @FXML
    private TableColumn<sellers, String> fxphone;

    public void showsellers() {
        ObservableList<sellers> listM;

        fxid.setCellValueFactory(new PropertyValueFactory<sellers, String>("id"));
        fxname.setCellValueFactory(new PropertyValueFactory<sellers, String>("name"));
        fxno.setCellValueFactory(new PropertyValueFactory<sellers, Integer>("No_Of_sales"));
        fxamount.setCellValueFactory(new PropertyValueFactory<sellers, Float>("Tot_amount"));
        fxcomis.setCellValueFactory(new PropertyValueFactory<sellers, Float>("Com"));
        fxphone.setCellValueFactory(new PropertyValueFactory<sellers, String>("tp"));

        listM = mySqlConnector.getDatasellers();
        tableID.setItems(listM);
    }
    int index=-1;
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series<String,Number> series=new XYChart.Series();

        series.getData().add(new XYChart.Data("jan",50));
        series.getData().add(new XYChart.Data("feb",75));
        series.getData().add(new XYChart.Data("march",25));
        series.getData().add(new XYChart.Data("apr",50));

        chart.getData().add(series);

        showsellers();




    }
}
