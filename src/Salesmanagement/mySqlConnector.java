package Salesmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class mySqlConnector {

    Connection conn=null;

    public static Connection ConnectDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sellereports","root","");
            JOptionPane.showMessageDialog(null,"ConnectionEstablished");
            return conn;
        }catch (Exception e){
            System.out.println(e.getStackTrace());
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }

    public static ObservableList <sellers> getDatasellers(){
        Connection conn= ConnectDb();
        ObservableList <sellers> list= FXCollections.observableArrayList();
        try{
            PreparedStatement ps= conn.prepareStatement("Select*from sellers");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                //System.out.println(rs.getFloat("Total_amount"));
                list.add(new sellers(rs.getString("idSellers"), rs.getString("SellerName"),rs.getInt("No_of_sales"),rs.getString("Telephone"),rs.getFloat("Total_amount"),rs.getFloat("sales_comission")));

            }


        }catch (Exception e){

        }



        return list;


    }
}
