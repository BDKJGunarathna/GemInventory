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

public class Supplier_Managers_Report {
    //declare variables
    private String HTML;
    private DBHandler handler;//DBHandler is the connection class
    private Connection connection;
    private String query;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;

    public Supplier_Managers_Report() throws SQLException {
        //Initialize DBHandler class
        handler = new DBHandler();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Where Closure)
        query = "SELECT empID, empName, empDOB, empAddress, nic_number, empPhone1, empPhone2, empEmail FROM employeemanagement_table WHERE empType='Supplier Manager'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        //Process the ResultSet object
        while(resultSet.next()){
            //Create a function to set Employee ID, Employee Name, Employee Type and Employee Salary
            data  += "<tr style='border: 1px solid #a0a2ab; border-collapse: collapse;'>" +
                    "<td style='border: 1px solid #a0a2ab; border-collapse: collapse; text-align:center;'>"+resultSet.getString("empID")+"</td>" +
                    "<td style='border: 1px solid #a0a2ab; border-collapse: collapse;'>"+resultSet.getString("empName")+"</td>" +
                    "<td style='border: 1px solid #a0a2ab; border-collapse: collapse; text-align:center;'>"+resultSet.getString("empDOB")+"</td>" +
                    "<td style='border: 1px solid #a0a2ab; border-collapse: collapse;'>"+resultSet.getString("empAddress")+"</td>" +
                    "<td style='border: 1px solid #a0a2ab; border-collapse: collapse; text-align:center;'>"+resultSet.getString("nic_number")+"</td>" +
                    "<td style='border: 1px solid #a0a2ab; border-collapse: collapse; text-align:center;'>"+resultSet.getString("empPhone1")+"</td>" +
                    "<td style='border: 1px solid #a0a2ab; border-collapse: collapse; text-align:center;'>"+resultSet.getString("empPhone2")+"</td>" +
                    "<td style='border: 1px solid #a0a2ab; border-collapse: collapse;'>"+resultSet.getString("empEmail")+"</td>" +
                    "</tr>";
        }
        //Create PDF from HTML
        HTML =  "<h1 style='color: #2196f3; text-align: center;'>CITY OF GEMS</h1>"+
                "<h3 style='text-align: center'>Supplier Managers</h3>"+
                "<table style='width: 100%; border: 1px solid #a0a2ab; border-collapse: collapse;'>" +
                "<tr style='border: 1px solid #a0a2ab; border-collapse: collapse;'>" +
                "<th style='border: 1px solid #a0a2ab; border-collapse: collapse;'>ID</th>" +
                "<th style='border: 1px solid #a0a2ab; border-collapse: collapse;'>Name</th>" +
                "<th style='border: 1px solid #a0a2ab; border-collapse: collapse;'>DOB</th>" +
                "<th style='border: 1px solid #a0a2ab; border-collapse: collapse;'>Address</th>" +
                "<th style='border: 1px solid #a0a2ab; border-collapse: collapse;'>NIC No</th>" +
                "<th style='border: 1px solid #a0a2ab; border-collapse: collapse;'>Home No</th>" +
                "<th style='border: 1px solid #a0a2ab; border-collapse: collapse;'>Mobile No</th>" +
                "<th style='border: 1px solid #a0a2ab; border-collapse: collapse;'>Email</th>" +
                "</tr>" + data +
                "</table>" ;
    }

    //Create PDF Method
    public void createPdf(){
        try{
            //Convert HTML to PDF
            //PDF Name : Supplier_Managers_Report.pdf
            HtmlConverter.convertToPdf(HTML, new FileOutputStream("Supplier_Managers_Report.pdf"));

            //Alert Information box
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Supplier Managers Report Successfully Downloaded");
            alert.show();
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }
    }
}