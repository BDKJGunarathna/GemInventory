package FinanceManagement;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.sql.*;

import static FinanceManagement.DBConnection.getConnection;


public class reportAssetsLiabilitiesController {

    //declare variables
    private ResultSet resultSet = null;



//method to generate Assets and Liabilities report
public void generateAssetsLiabilitiesReport() throws SQLException {
        try{

        String report_name = "C:\\Users\\Dasuni\\Desktop\\ITP\\untitled\\src\\FinanceManagement\\Assets and Liabilities Report.pdf";
        //create Document object
        Document document = new Document();
        //set pdf instance
        PdfWriter.getInstance(document,new FileOutputStream(report_name));
        //open the document
        document.open();

        Font bold = new Font(Font.FontFamily.HELVETICA,30,Font.BOLD);
        document.add(new Paragraph("                                                 City of Gems"));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        Paragraph p = new Paragraph("                                   Monthly Assets and Liabilities Report");
        document.add(p);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        //get Assets and Liabilities description and amount with result set
        Connection conn = getConnection();
        String query1 = "SELECT assetsliabilities_description,assetsliabilities_amount FROM cityofgems.assetsandliabilities GROUP BY assetsliabilities_description";
        Statement st1;
        ResultSet r;

        try {
        st1 = conn.createStatement();
        r = st1.executeQuery(query1);

        if (r != null) {
        PdfPTable table = new PdfPTable(2);

        PdfPCell c1 = new PdfPCell(new Phrase("                        Description"));
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("                         Amount"));
        table.addCell(c1);

        table.setHeaderRows(1);

        while (r.next()) {
        table.addCell(String.valueOf(r.getString("assetsliabilities_description")));
        table.addCell(String.valueOf(r.getString("assetsliabilities_amount")));


        }

        document.add(table);


        }
        } catch (Exception ex) {
        ex.printStackTrace();
        }



        document.close();
        }catch(Exception e){
        e.printStackTrace();
        e.getCause();
        }

        }

        }

