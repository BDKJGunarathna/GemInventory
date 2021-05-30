package FinanceManagement;

        import com.itextpdf.html2pdf.HtmlConverter;
        import javafx.scene.control.Alert;

        import java.awt.geom.Line2D;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;


public class reportAssetsLiabilitiesController {

    //declare variables
    private String HTML, HTML1, HTML2, HTML3, HTML4;
    private DBConnection handler;
    private Connection connection;
    private String query, query_a, query_l, query_e;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;


    public reportAssetsLiabilitiesController() throws SQLException {
        //Initialize DBConnection class
        handler = new DBConnection();
        //Establishing a Connection
        connection = handler.getConnection();

        //Declare a variable
        String data = "";

        //SQL QUERY (RETRIEVE Data using Group By Clause)
        query = "SELECT assetsliabilities_description,assetsliabilities_amount FROM cityofgems.assetsandliabilities GROUP BY assetsliabilities_description";

        preparedStatement = connection.prepareStatement(query);

        //Execute the query
        resultSet = preparedStatement.executeQuery();

        //Process the ResultSet object
        while(resultSet.next()){
            //Table data
            data  += "<tr style='border: 1px solid #a0a2ab; border-collapse: collapse;'>" +
                    "<td style='border: 1px solid #a0a2ab; border-collapse: collapse;'>"+resultSet.getString("assetsliabilities_description")+"</td>" +
                    "<td style='border: 1px solid #a0a2ab; border-collapse: collapse; text-align:center;'>"+resultSet.getString("assetsliabilities_amount")+"</td>" +
                    "</tr>";
        }

        //Create PDF from HTML (Table creation join with table data)
        HTML1 =  "<h1 style='color: #2196f3; text-align: center;'>CITY OF GEMS</h1>"+
                "<h3 style='text-align: center'>Monthly Assets and Liabilities Report</h3>"+
                "<table style='width: 100%; border: 1px solid #a0a2ab; border-collapse: collapse;'>" +
                "<tr style='border: 1px solid #a0a2ab; border-collapse: collapse;'>" +
                "<th style='border: 1px solid #a0a2ab; border-collapse: collapse;'>Description</th>" +
                "<th style='border: 1px solid #a0a2ab; border-collapse: collapse;'>Amount</th>" +
                "</tr>" + data +
                "</table>" ;



        //SQL QUERY to get total Assets
        query_a = "SELECT SUM(assetsliabilities_amount) as 'TA' FROM assetsandliabilities WHERE assetsliabilities_type='Asset'";

        preparedStatement = connection.prepareStatement(query_a);

        //Execute the query
        resultSet = preparedStatement.executeQuery();


        //Process the ResultSet object
        resultSet.next();

        //Create PDF from HTML
        HTML2 = "<h4>Total Assets : " + resultSet.getString("TA") + "</h4>";

        //Convert total Assets into double
        String totAs = resultSet.getString("TA");
        Double totalAs = Double.parseDouble(totAs);


        // SQL QUERY to get total Liabilities
        query_l = "SELECT SUM(assetsliabilities_amount) as 'TL' FROM assetsandliabilities WHERE assetsliabilities_type='Liability'";

        preparedStatement = connection.prepareStatement(query_l);

        //Execute the query
        resultSet = preparedStatement.executeQuery();

        //Process the ResultSet object
        resultSet.next();

        //Create PDF from HTML
        HTML3 = "<h4>Total Liabilities : " + resultSet.getString("TL") + "</h4>";

        //Convert total Assets into double
        String totLi = resultSet.getString("TL");
        System.out.println("Total: " +totLi);
        Double totalLi = Double.parseDouble(totLi);

        // SQL QUERY to get total Equity
        query_e = "SELECT SUM(assetsliabilities_amount) as 'TE' FROM assetsandliabilities WHERE assetsliabilities_type='Equity'";

        preparedStatement = connection.prepareStatement(query_e);

        //Execute the query
        resultSet = preparedStatement.executeQuery();

        //Process the ResultSet object
        resultSet.next();

        //Create PDF from HTML
        HTML4 = "<h4>Total Equity : " + resultSet.getString("TE") + "</h4>";

        //Convert total Assets into double
        String totEq = resultSet.getString("TE");
        Double totalEq = Double.parseDouble(totEq);

        //HTML5 = "<h4>Assets : " + (totalLi + totalEq) + "</h4>";

        HTML = HTML1 + HTML3 + HTML2 + HTML4 ;

    }


    //Create PDF Method
    public void generatePDF1(){
        try{
            //Convert HTML to PDF called Monthly_Assets_and_Liabilities_Report.pdf
            HtmlConverter.convertToPdf(HTML, new FileOutputStream("Assets and Liabilities Report.pdf"));

            //Alert Information box
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Assets and Liabilities Report Successfully Downloaded");
            alert.show();
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

