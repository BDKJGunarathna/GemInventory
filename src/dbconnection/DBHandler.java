package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends Configs{

    //Create a database connection class
    Connection dbconnection;

    //Connection method that returns connection instance
    public Connection getConnection() {
        //Connection string, extract it here, should include host, port and database name
        String connectionString = "jdbc:mysql://"+ Configs.dbhost + ":" + Configs.dbport + "/" + Configs.dbname + "?autoReconnect=true&useSSl=false";

        try {
            //Register mysql driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            //Create connection using drivermanager, accepting three parameters, connection string, database name and database password
            dbconnection = DriverManager.getConnection(connectionString, Configs.dbuser, Configs.dbpass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbconnection;
    }

}
