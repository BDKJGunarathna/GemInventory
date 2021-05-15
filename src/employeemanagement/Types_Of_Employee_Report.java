package employeemanagement;


import com.itextpdf.html2pdf.HtmlConverter;
import dbconnection.DBHandler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Types_Of_Employee_Report
{
    private String HTML;
    private DBHandler handler;//DBHandler is the connection class
    private Connection connection;
    private String query;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;

    public Types_Of_Employee_Report() throws SQLException {

        handler = new DBHandler();
        connection = handler.getConnection();
        String data = "";
        query = "SELECT empType,COUNT(*) as NOE FROM cityofgems.employeemanagement_table GROUP BY empType";

        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            data  +=  "<tr style='border: 1px solid #a0a2ab; border-collapse: collapse;'>" +
                    "<td style='border: 1px solid #a0a2ab; border-collapse: collapse;'>"+resultSet.getString("empType")+"</td>" +
                    "<td style='border: 1px solid #a0a2ab; border-collapse: collapse; text-align:center;'>"+resultSet.getString("NOE")+"</td>" +
                    "</tr>";
        }

        HTML = "<h1 style='color: #2196f3; text-align: center;'>CITY OF GEMS</h1>"+
        "<h3 style='text-align: center'>Types of Employee Report</h3>"+
        "<table style='width: 100%; border: 1px solid #a0a2ab; border-collapse: collapse;'>" +
                "<tr style='border: 1px solid #a0a2ab; border-collapse: collapse;'>" +
                "<th style='border: 1px solid #a0a2ab; border-collapse: collapse;'>Employee Type</th>" +
                "<th style='border: 1px solid #a0a2ab; border-collapse: collapse;'>No of Employees</th>" +
                "</tr>" + data + "</table>" ;
    }



    public void createPdf(){
        try{

            HtmlConverter.convertToPdf(HTML, new FileOutputStream("string-to-pdf.pdf"));

            System.out.println( "PDF Created!" );

        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }
    }

}