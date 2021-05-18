package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbconnect {

   private static String Host="127.0.0.1";
    private int Port=3306;
    private static String DBName="CityOfGems";
    private static String password="root123";
    private static String username="root";
    private static Connection connection;



   public static Connection getConnection() {
        Connection connection;
        try {
        connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cityofgems","root","root123");

        }catch (Exception e){
            System.out.println("Error:"+e.getMessage());
            return null;
        }
        return connection;
   }

  /*  private static String Host="127.0.0.1";
    private int Port=3306;
    private static String DBName="CityOfGems";
    private static String password="root123";
    private static String username="root";
    private static Connection connection;*/


    /*public static Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",Host,Port,DBName,username,password));
        }catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(dbconnect.class.getName()).log(Level.SEVERE,null,e);
        }
        return connection;
    }*/


}
