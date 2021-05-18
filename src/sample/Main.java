package sample;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Gem Inventory");
        primaryStage.setScene(new Scene(root, 1300, 500));
        primaryStage.show();

    }
        public static void main (String[]args){
            launch(args);
/*
            try {
                String fileName = "D:\\CityOfGems\\inventroy.pdf";
                Document document = new Document();

                //pfd writer
                PdfWriter.getInstance(document, new FileOutputStream((fileName)));
                document.open();

           dbconnect db=new dbconnect();
           Connection connection=dbconnect.getConnection();
                PreparedStatement ps=null;
                ResultSet rs=null;

                String query="SELECT * FROM cityofgems.inventory";
                ps=connection.prepareStatement(query);
                rs=ps.executeQuery();

                while (rs.next()){
                    Paragraph para=new Paragraph(rs.getString("gemId")+""+rs.getString("description")+""+rs.getString("weight")+""+rs.getString("shape")+""+rs.getString("dimension")+""+rs.getString("unitsInStock")+""+rs.getString("reorderLevel")+""+rs.getString("cost"));
                    document.add(para);
                    document.add(new Paragraph(""));
                }
                document.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
*/
        }
    }
