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
        countlogout.setTitle("CITY OF GEMS - LOGIN");//Set Title of interface
        //Setup the Scene
        Parent countroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene countscene2 = new Scene(countroot2);//Create a scene
        countlogout.setScene(countscene2);//Set Scene object on the Stage
        countlogout.show();//Show the Stage which create above (makes the Stage visible and the exits)
        countlogout.setResizable(false);//User cannot resize the frame
    }

    //Home Page (Direct to Employee Management Home Page)
    @FXML
    public void counthomeAction(ActionEvent emphome8) throws  IOException {
        counthome.getScene().getWindow().hide();

        Stage countemphome = new Stage();//Create a Stage
        countemphome.setTitle("CITY OF GEMS - EMPLOYEE MANAGEMENT HOME");//Set Title of interface
        //Setup the Scene
        Parent countemproot8 = FXMLLoader.load(getClass().getResource("/employeemanagement/employeemanagementhome.fxml"));
        Scene countempscene8 = new Scene(countemproot8);//Create a scene
        countemphome.setScene(countempscene8);//Set Scene object on the Stage
        countemphome.show();//Show the Stage which create above (makes the Stage visible and the exits)
        countemphome.setResizable(false);
    }

    //Add Employee Button method (Direct to add employee page)
    @FXML
    public void countaddemployeemenubuttonAction(ActionEvent countev4) throws IOException {
        countaddemployee.getScene().getWindow().hide();

        Stage countaddmenu = new Stage();//Create a Stage
        countaddmenu.setTitle("CITY OF GEMS - ADD EMPLOYEE");//Set Title of interface
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
        countviewemp.setTitle("CITY OF GEMS - FILTERED EMPLOYEE REPORTS");//Set Title of interface
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
        countempcount.setTitle("CITY OF GEMS - EMPLOYEE COUNT");//Set Title of interface
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

    @FXML
    private void totCEO() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='CEO'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            nceo.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totAdmin() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Admin'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            nadmins.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totAccountant() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Accountant'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            naccountants.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totGemologist() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Gemologist'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            ngemologists.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totClerk() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Clerk'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            nclerks.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totLaborer() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Laborer'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            nlaborers.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totSalesManager() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Sales & Administrative Manager'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            nsalesmanagers.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totDistributionManger() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Finishing & Distribution Manager'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            nfinishingmanagers.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totAdminAssistant() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Administrative Assistant'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            nadministrativeassistants.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totAccountantAssistant() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Accountant Assistant'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            naccountantassistants.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totMarketing() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Marketing Executive'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            nmarketingexecutives.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totSalesRep() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Sales Representative'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            nstoresup.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totInventory() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Inventory Manager'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            ninventorymanagers.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totSupplier() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Supplier Manager'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            nsupmanagers.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totAM() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Assistant Manager'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            nassistantmanagers.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totStore() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Store Supervisor'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            nstoresup.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totOA() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Office Assistant'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            nofficeassistants.setText(resultSet.getString("COUNT(*)"));
        }
    }

    @FXML
    private void totQuality() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
        query = "SELECT COUNT(*) FROM cityofgems.employeemanagement_table WHERE empType='Quality Checker'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            nqualitycheckers.setText(resultSet.getString("COUNT(*)"));
        }
    }

}
