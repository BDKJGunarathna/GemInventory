package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static void dbmain(String args[]) throws ClassNotFoundException, SQLException {
        //Register mysql driver
        Class.forName("com.mysql.jdbc.Driver");
        //Create connection using drivermanager, accepting three parameters, connection string, database name and database password
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cityofgems", "root", "H@nsaka0308");
        System.out.println("Connected to database");//prompt message
    }

}
