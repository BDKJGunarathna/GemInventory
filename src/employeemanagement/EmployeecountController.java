package employeemanagement;

import com.jfoenix.controls.*;
import dbconnection.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeecountController implements Initializable {

    @FXML
    private JFXButton countaddemployee;

    @FXML
    private JFXButton countviewemployee;

    @FXML
    private JFXButton countemployeecount;

    @FXML
    private JFXButton countlogout;

    @FXML
    private JFXButton counthome;

    @FXML
    private Label totemployees;

    @FXML
    private Label nceo;

    @FXML
    private Label nadmins;

    @FXML
    private Label naccountants;

    @FXML
    private Label ngemologists;

    @FXML
    private Label nclerks;

    @FXML
    private Label nlaborers;

    @FXML
    private Label nsalesmanagers;

    @FXML
    private Label nfinishingmanagers;

    @FXML
    private Label nadministrativeassistants;

    @FXML
    private Label naccountantassistants;

    @FXML
    private Label nmarketingexecutives;

    @FXML
    private Label nsalesreps;

    @FXML
    private Label ninventorymanagers;

    @FXML
    private Label nsupmanagers;

    @FXML
    private Label nassistantmanagers;

    @FXML
    private Label nstoresup;

    @FXML
    private Label nofficeassistants;

    @FXML
    private Label nqualitycheckers;

    private DBHandler handler;//DBHandler is the connection class
    private Connection connection;
    private String query;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //Logout Method(Direct to login page)
    @FXML
    public void countlogoutAction(ActionEvent countev3) throws IOException {
        countlogout.getScene().getWindow().hide();

        Stage countlogout = new Stage();//Create a Stage
        //Setup the Scene
        Parent countroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene countscene2 = new Scene(countroot2);//Create a scene
        countlogout.setScene(countscene2);//Set Scene object on the Stage
        countlogout.show();//Show the Stage which create above (makes the Stage visible and the exits)
        countlogout.setResizable(false);//User cannot resize the frame
    }

    //Add Employee Button method (Direct to add employee page)
    @FXML
    public void countaddemployeemenubuttonAction(ActionEvent countev4) throws IOException {
        countaddemployee.getScene().getWindow().hide();

        Stage countaddmenu = new Stage();//Create a Stage
        //Setup the Scene
        Parent countroot3 = FXMLLoader.load(getClass().getResource("/employeemanagement/addemployee.fxml"));
        Scene countscene3 = new Scene(countroot3);//Create a scene
        countaddmenu.setScene(countscene3);//Set Scene object on the Stage
        countaddmenu.show();//Show the Stage which create above (makes the Stage visible and the exits)
        countaddmenu.setResizable(false);//User cannot resize the frame
    }

    //View Employee Button method (Direct to filter employee page)
    @FXML
    public void countviewemployeemenubuttonAction(ActionEvent countev6) throws IOException {
        countviewemployee.getScene().getWindow().hide();

        Stage countviewemp = new Stage();//Create a Stage
        //Setup the Scene
        Parent countroot5 = FXMLLoader.load(getClass().getResource("/employeemanagement/filteremployees.fxml"));
        Scene countscene4 = new Scene(countroot5);//Create a scene
        countviewemp.setScene(countscene4);//Set Scene object on the Stage
        countviewemp.show();//Show the Stage which create above (makes the Stage visible and the exits)
        countviewemp.setResizable(false);//User cannot resize the frame
    }

    //Employee Count Button method (Direct to employee count page)
    @FXML
    public void countemployeecountmenubuttonAction(ActionEvent countev7) throws IOException {
        countemployeecount.getScene().getWindow().hide();

        Stage countempcount = new Stage();//Create a Stage
        //Setup the Scene
        Parent countroot6 = FXMLLoader.load(getClass().getResource("/employeemanagement/employeecount.fxml"));
        Scene countscene5 = new Scene(countroot6);//Create a scene
        countempcount.setScene(countscene5);//Set Scene object on the Stage
        countempcount.show();//Show the Stage which create above (makes the Stage visible and the exits)
        countempcount.setResizable(false);//User cannot resize the frame
    }

    @FXML
    private void totalEmployee() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            totemployees.setText(resultSet.getString("COUNT(*)"));
        }
    }

}
