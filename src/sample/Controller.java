package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btnhomGem;

    @FXML
    private Button btnhomjewlery;

    @FXML
    private Button btnhomorders;

    @FXML
    private Button btnhomecus;

    @FXML
    private Button btnhomsales;

    @FXML
    private Button btnhomemp;

    @FXML
    private Button btnhomFinance;

    @FXML
    private Button btnhomSup;

    @FXML
    private LineChart<?, ?> charttotsales;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private JFXButton dashHomebtn;

    @FXML
    private JFXButton dashLogoutbtn;

    @Override
    public void initialize(URL dash0, ResourceBundle dash1) {

    }

    @FXML
    public void logoutdashAction(ActionEvent dashev1) throws IOException {
        dashLogoutbtn.getScene().getWindow().hide();

        Stage logoutdash = new Stage();
        Parent dashroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene dashscene2 = new Scene(dashroot2);
        logoutdash.setScene(dashscene2);
        logoutdash.show();
        logoutdash.setResizable(false);
    }

    @FXML
    public void dashHomeAction(ActionEvent dashev2) throws IOException {
        dashHomebtn.getScene().getWindow().hide();

        Stage homedash = new Stage();
        Parent dashroot3 = FXMLLoader.load(getClass().getResource("/sample/dashboard.fxml"));
        Scene dashscene3 = new Scene(dashroot3);
        homedash.setScene(dashscene3);
        homedash.show();
        homedash.setResizable(false);
    }

    @FXML
    public void dashEmployeeAction(ActionEvent dashev2) throws IOException {
        btnhomemp.getScene().getWindow().hide();

        Stage empdash = new Stage();
        Parent dashroot4 = FXMLLoader.load(getClass().getResource("/employeemanagement/employeemanagementhome.fxml"));
        Scene dashscene4 = new Scene(dashroot4);
        empdash.setScene(dashscene4);
        empdash.show();
        empdash.setResizable(false);
    }

}
