package FinanceManagement;

import com.itextpdf.html2pdf.HtmlConverter;
import javafx.scene.control.Alert;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class reportIncomeExpensesController {

    //declare variables
    private String HTML, HTML1, HTML2, HTML3, HTML4;
    private DBConnection handler;
    private Connection connection;
    private String query, query_i, query_e;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;


    public reportIncomeExpensesController() throws SQLException {
        //Initialize DBConnection class
        handler = new DBConnection();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Group By Clause)
        query = "SELECT description,amount FROM cityofgems.incomeandexpenses GROUP BY description";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query);
        //Execute the query
        resultSet = preparedStatement.executeQuery();

        //Process the ResultSet object
        while(resultSet.next()){
            //Table data
            data  += "<tr style='border: 1px solid #a0a2ab; border-collapse: collapse;'>" +
                    "<td style='border: 1px solid #a0a2ab; border-collapse: collapse;'>"+resultSet.getString("description")+"</td>" +
                    "<td style='border: 1px solid #a0a2ab; border-collapse: collapse; text-align:center;'>"+resultSet.getString("amount")+"</td>" +
                    "</tr>";
        }

        //Create PDF from HTML (Table creation join with table data)
        HTML1 =  "<h1 style='color: #2196f3; text-align: center;'>CITY OF GEMS</h1>"+
                "<h3 style='text-align: center'>Monthly Income and Expenses Report</h3>"+
                "<table style='width: 100%; border: 1px solid #a0a2ab; border-collapse: collapse;'>" +
                "<tr style='border: 1px solid #a0a2ab; border-collapse: collapse;'>" +
                "<th style='border: 1px solid #a0a2ab; border-collapse: collapse;'>Description</th>" +
                "<th style='border: 1px solid #a0a2ab; border-collapse: collapse;'>Amount</th>" +
                "</tr>" + data +
                "</table>" ;



        //SQL QUERY to get total expenditure
        query_e = "SELECT SUM(amount) as 'TE' FROM incomeandexpenses WHERE type='Expenditure'";

        //Create a statement using connection object
        preparedStatement = connection.prepareStatement(query_e);

        //Execute the query
        resultSet = preparedStatement.executeQuery();


        //Process the ResultSet object
        resultSet.next();
        //Create PDF from HTML
        HTML2 = "<h4>Total Expenses : " + resultSet.getString("TE") + "</h4>";

        //Convert total expenditure into double
        String totEx = resultSet.getString("TE");
        Double totalEx = Double.parseDouble(totEx);

        // SQL QUERY to get total income
        query_i = "SELECT SUM(amount) as 'TI' FROM incomeandexpenses WHERE type='Income'";
        preparedStatement = connection.prepareStatement(query_i);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        HTML3 = "<h4>Total Income : " + resultSet.getString("TI") + "</h4>";

        //Convert total income into double
        String totIn = resultSet.getString("TI");
        double totalIn = Double.parseDouble(totIn);

        HTML4 = "<h4>Profit : " + (totalIn - totalEx) + "</h4>";

        HTML = HTML1 + HTML3 + HTML2 + HTML4;

    }


    //Create PDF Method
    public void createPdf(){
        try{
            //Convert HTML to PDF
            //PDF Name : Monthly_Income_and_Expenses_Report.pdf
            HtmlConverter.convertToPdf(HTML, new FileOutputStream("Income and Expenses Report.pdf"));

            //Alert Information box
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Income and Expenses Report Successfully Downloaded");
            alert.show();
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

