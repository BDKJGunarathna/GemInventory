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

public class CEODetails {
    //declare variables
    private String HTML;
    private DBHandler handler;//DBHandler is the connection class
    private Connection connection;
    private String query;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;

    public CEODetails() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Where Closure)
        query = "SELECT * FROM employeemanagement_table WHERE empType = 'CEO'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        //Process the ResultSet object
        resultSet.next();
        //Create PDF from HTML
        HTML =  "<h1 style='color: #2196f3; text-align: center;'>CITY OF GEMS</h1>"+
                "<h3 style='text-align: center'>CEO</h3>"+
                "<h4>ID : " + resultSet.getString("empID") + "</h4>" +
                "<h4>Name : " + resultSet.getString("empName") + "</h4>" +
                "<h4>DOB : " + resultSet.getString("empDOB") + "</h4>" +
                "<h4>Address : " + resultSet.getString("empAddress") + "</h4>" +
                "<h4>NIC Number : " + resultSet.getString("nic_number") + "</h4>" +
                "<h4>Home Number : " + resultSet.getString("empPhone1") + "</h4>" +
                "<h4>Mobile Number : " + resultSet.getString("empPhone2") + "</h4>" +
                "<h4>Email : " + resultSet.getString("empEmail") + "</h4>";
    }

    //Create PDF Method
    public void createPdf(){
        try{
            //Convert HTML to PDF
            //PDF Name : CEODetails.pdf
            HtmlConverter.convertToPdf(HTML, new FileOutputStream("CEODetails.pdf"));

            //Alert Information box
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("CEO Details Successfully Downloaded");
            alert.show();
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }
    }
}
