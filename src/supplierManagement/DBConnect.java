package supplierManagement;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    public static Connection databaseLink;

    public static Connection getConnection() {
        String databaseName = "cityofgems";
        String databaseUser = "root";
        String databasePassword = "DBPassword";
        String url = "jdbc:mysql://localhost:3306/cityofgems";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
}
