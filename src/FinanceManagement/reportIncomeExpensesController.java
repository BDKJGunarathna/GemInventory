package FinanceManagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import com.itextpdf.html2pdf.HtmlConverter;
import javafx.scene.control.Alert;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.*;

public class reportIncomeExpensesController {


    @FXML
    private Button incomeExpensesMenu4;
    @FXML
    private Button searchIncomeExpensesMenu4;
    @FXML
    private Button updateIncomeExpensesMenu4;
    @FXML
    private Button reportIncomeExpensesMenu4;


    //declare variables
    private String HTML, HTML1, HTML2, HTML3, HTML4;
    private DBConnection handler;
    private Connection connection;
    private String query, query_i, query_e;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;



    public reportIncomeExpensesController() throws SQLException {
        //Initialize DBHandler class
        handler = new DBConnection();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Count and Group By Closure)
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

        /* SQL QUERY to get total income */
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
            //PDF Name : Types_of_Employee_Report.pdf
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






    @FXML
    public void ReportIncomeExpensesOnAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("incomeExpenses.fxml"));
            Stage reportInEx4 = (Stage) incomeExpensesMenu4.getScene().getWindow();
            reportInEx4.setTitle("City of Gems");
            reportInEx4.setScene(new Scene(root, 1250,800));
            reportInEx4.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void ReportSearchIncomeExpensesOnAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("searchIncomeExpenses.fxml"));
            Stage reportSeInEx4 = (Stage) searchIncomeExpensesMenu4.getScene().getWindow();
            reportSeInEx4.setTitle("City of Gems");
            reportSeInEx4.setScene(new Scene(root, 1250,800));
            reportSeInEx4.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void ReportUpdateIncomeExpensesOnAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("updateIncomeExpenses.fxml"));
            Stage reportUpInEx4 = (Stage) updateIncomeExpensesMenu4.getScene().getWindow();
            reportUpInEx4.setTitle("City of Gems");
            reportUpInEx4.setScene(new Scene(root, 1250,800));
            reportUpInEx4.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @FXML
    public void ReportReportIncomeExpensesOnAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("reportIncomeExpenses.fxml"));
            Stage reportReInEx4 = (Stage) reportIncomeExpensesMenu4.getScene().getWindow();
            reportReInEx4.setTitle("City of Gems");
            reportReInEx4.setScene(new Scene(root, 1250,800));
            reportReInEx4.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

