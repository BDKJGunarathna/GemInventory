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

public class reportIncomeExpensesController {

    //declare variables
    private ResultSet resultSet = null;



    //method to generate Income and Expenses report
    public void generateIncomeExpensesReport() throws SQLException {
        try{

            String report_name = "C:\\Users\\Dasuni\\Desktop\\ITP\\untitled\\src\\FinanceManagement\\Income and Expenses Report.pdf";
            //create Document object
            Document document = new Document();
            //set pdf instance
            PdfWriter.getInstance(document,new FileOutputStream(report_name));
            //open the document
            document.open();

            Font bold = new Font(Font.FontFamily.HELVETICA,30,Font.BOLD);
            document.add(new Paragraph("                                               City of Gems"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            Paragraph p = new Paragraph("                                   Monthly Income and Expenses Report");
            document.add(p);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            //get Income and Expenses description and amount with result set
            Connection conn = getConnection();
            String query1 = "SELECT description,amount FROM cityofgems.incomeandexpenses GROUP BY description";
            Statement st1;
            ResultSet r;

            try {
                st1 = conn.createStatement();
                r = st1.executeQuery(query1);

                if (r != null) {
                    PdfPTable table = new PdfPTable(2);

                    PdfPCell c1 = new PdfPCell(new Phrase("                         Description"));
                    table.addCell(c1);
                    c1 = new PdfPCell(new Phrase("                          Amount"));
                    table.addCell(c1);

                    table.setHeaderRows(1);

                    while (r.next()) {
                        table.addCell(String.valueOf(r.getString("description")));
                        table.addCell(String.valueOf(r.getString("amount")));


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

