package employeemanagement;

import com.itextpdf.html2pdf.HtmlConverter;
import dbconnection.DBHandler;
import javafx.scene.control.Alert;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Salary_Report {
    //declare variables
    private String HTML;
    private DBHandler handler;//DBHandler is the connection class
    private Connection connection;
    private String query;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;

    public Salary_Report() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //SQL QUERY (RETRIEVE Data using Where Closure)
        query = "SELECT SUM(empSalary) as TS, COUNT(*) as SR FROM employeemanagement_table WHERE empSuspend='NO'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        //Process the ResultSet object
        resultSet.next();
        //Create PDF from HTML
        HTML =  "<h1 style='color: #2196f3; text-align: center;'>CITY OF GEMS</h1>"+
                "<h3 style='text-align: center'>SALARY REPORT</h3>"+
                "<h4>No of Active Employees : " + resultSet.getString("SR") + "</h4>" +
                "<h4>Total Salary : " + resultSet.getString("TS") + "</h4>";
    }

    //Create PDF Method
    public void createPdf(){
        try{
            //Convert HTML to PDF
            //PDF Name : Salary_Report.pdf
            HtmlConverter.convertToPdf(HTML, new FileOutputStream("Salary_Report.pdf"));

            //Alert Information box
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Salary Report Successfully Downloaded");
            alert.show();
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }
    }
}
