package Salesmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class addForm {

    /*
    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSales;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtComis;

    @FXML
    private TextField txtNumber;
    @FXML
    private Button btnInsert;
    private DatabaseMetaData DBConnection;

    public addForm(){}

    /*
    @FXML
    private void handleButtonAction (ActionEvent event) throws SQLException {
        if(event.getSource() == btnInsert){
            insertSellers();
            System.out.println("Clicked");
        }

    }*/


    /*
    int index=-1;
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;


    private void insertSellers() throws SQLException {
        String query = "INSERT into sellers VALUES ("+ txtID.getText() +"',"+ txtName.getText() +", '"+ txtSales.getText() +"','"+ txtAmount.getText() +"',"+ txtComis.getText() +","+ txtNumber.getText()+")";
        executeQuery(query);

    }



    private void executeQuery(String query) throws SQLException {
        //Connection conn=getConnection();
        DBConnection.getConnection();
        Statement statement;
        try{
            //st = conn.createStatement();
            statement= DBConnection.getConnection().createStatement();
            statement.executeUpdate(query);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    */

}
