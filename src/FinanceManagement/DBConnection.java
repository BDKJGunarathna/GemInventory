package FinanceManagement;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection{

    public static String url = "jdbc:mysql://localhost:3306/cityofgems";
    public static String userName = "root";
    public static String password = "Dasuni2020#";
    public static Connection con;

    public static Connection getConnection() {

        try {

            //Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(url, userName, password);

        }
        catch (Exception e) {
            System.out.println("Database connection is not success!!!");
        }

        return con;
    }
}

