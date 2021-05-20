package Salesmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.*;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.util.Arrays;


import javax.swing.*;

public class xyz {

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
    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;


    private void Delete(){
        Connection con=ConnectDb();
        PreparedStatement ps;
        String Query = "Delete from sellers WHERE idsellers = ?";
        try{
            ps=con.prepareStatement(Query);
            ps.setString(1, txtID.getText());
            ps.execute();
            JOptionPane.showMessageDialog(null,"Delete");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e );
        }

    }


    private void insertSellers() {
        String query = "INSERT into sellereports.sellers VALUES ('"+ txtID.getText() +"','"+ txtName.getText() +"', "+ txtSales.getText() +","+ txtAmount.getText() +","+ txtComis.getText() +","+ txtNumber.getText()+")";
        System.out.println(query);
        executeQuery(query);
    }

    private void updateSellers() {
        PreparedStatement ps;
        String Query = "UPDATE sellers SET SellerName=?, No_of_sales=?, Total_amount=?,sales_comission=?,Telephone=? WHERE idsellers = ?";
        Connection con = ConnectDb();
        try {
            ps = con.prepareStatement(Query);
            ps.setString(1, txtName.getText());
            ps.setInt(2, Integer.parseInt(txtSales.getText()));

            ps.setFloat(3, Float.parseFloat(txtAmount.getText()));
            ps.setFloat(4, Float.parseFloat(txtComis.getText()));

            ps.setString(5, txtNumber.getText());
            ps.setString(6, txtID.getText());

            ps.executeUpdate();
        }
        catch(Exception e){

        }


        /*String query = "UPDATE sellers SET SellerName  = '" + txtName.getText() + "', No_of_sales = " + txtSales.getText() + ", " +
                "Total_amount = " + txtAmount.getText() + ", sales_comission = " + txtComis.getText() + ",Telephone = " + txtNumber.getText() + " , WHERE idsellers = " + txtID.getText() + "";

        System.out.println(query);
        executeQuery(query);*/



    }




    private void executeQuery(String query)  {
        Connection cn= ConnectDb();
        Statement statement;
        try{

            assert cn != null;
            statement= cn.createStatement();
            statement.executeUpdate(query);


        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private Connection ConnectDb() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sellereports","root","");
            JOptionPane.showMessageDialog(null,"ConnectionEstablished");
            return conn;
        }catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }


    @FXML
    private void handleButtonAction (ActionEvent event) {
        if (event.getSource() == btnInsert) {
            insertSellers();
        }else if(event.getSource()==btnUpdate) {
            updateSellers();
        }else if(event.getSource()==btnDelete){
            Delete();
        }
    }
}
