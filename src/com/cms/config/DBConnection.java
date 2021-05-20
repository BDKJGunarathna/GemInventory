package com.cms.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/itp_customer","root","Shanel@@884&484");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return con;
    }

}
