package jewelryinventoryfunction;


import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.FileOutputStream;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DeletejewelrydetailsController implements Initializable {

    @FXML
    private TableView<Jewelry> jewelry;
    @FXML
    private TableColumn<Jewelry, Integer> idcol;
    @FXML
    private TableColumn<Jewelry, String> namecol;
    @FXML
    private TableColumn<Jewelry, String> typecol;
    @FXML
    private TableColumn<Jewelry, String> meterialcol;
    @FXML
    private TableColumn<Jewelry, Double> weightcol;
    @FXML
    private TableColumn<Jewelry, Integer> quancol;
    @FXML
    private TableColumn<Jewelry, Double> pricecol;
    @FXML
    private TableColumn<Jewelry, String> actioncol;

    @FXML
    private JFXButton addbtn1;
    @FXML
    private JFXButton updatebtn1;
    @FXML
    private JFXButton deletebtn1;
    @FXML
    private JFXButton store1;
    @FXML
    private JFXButton donebtn;
    @FXML
    private JFXButton homeid;
    @FXML
    private TextField search;
    @FXML
    private JFXButton reportbtn;


    @Override
    public void initialize(URL url, ResourceBundle rb){
        ShowTable();
        searchJewelryDetails();
    }

    @FXML
    public void addJewelryOnAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("addjewelrydetails.fxml"));
            Stage addj = (Stage) addbtn1.getScene().getWindow();
            addj.setTitle("City of Gems");
            addj.setScene(new Scene(root, 1250,800));
            addj.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void updateJewelryOnAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("updatejewelrydetails.fxml"));
            Stage updatej = (Stage) updatebtn1.getScene().getWindow();
            updatej.setTitle("City of Gems");
            updatej.setScene(new Scene(root, 1250,800));
            updatej.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void deleteJewelryOnAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("deletejewelrydetails.fxml"));
            Stage deletej = (Stage) deletebtn1.getScene().getWindow();
            deletej.setTitle("City of Gems");
            deletej.setScene(new Scene(root, 1250,800));
            deletej.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void storeGemOnAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("recievedgemdetails.fxml"));
            Stage storeg = (Stage) store1.getScene().getWindow();
            storeg.setTitle("City of Gems");
            storeg.setScene(new Scene(root, 1250,800));
            storeg.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void doneOnActionhome(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            Stage storeg = (Stage) homeid.getScene().getWindow();
            storeg.setTitle("City of Gems");
            storeg.setScene(new Scene(root, 1250,800));
            storeg.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //check connection
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cityofgems", "root", "jami1998");
            return conn;
        }
        catch(Exception ex){
            System.out.println("Error!"+ ex.getMessage());
            return null;
        }
    }

    //get jewelry list method (2)
    public ObservableList<Jewelry> getJewelryList(){
        ObservableList<Jewelry> jewelryList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM jewelry ";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Jewelry jew;

            while(rs.next()){
                jew = new Jewelry(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getString("meterial"), rs.getDouble("weight"), rs.getInt("quantity"), rs.getDouble("price"));
                jewelryList.add(jew);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return jewelryList;
    }



    @FXML
    private void ShowTable() {
        ObservableList<Jewelry> list = getJewelryList();

        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        typecol.setCellValueFactory(new PropertyValueFactory<>("type"));
        meterialcol.setCellValueFactory(new PropertyValueFactory<>("meterial"));
        weightcol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        quancol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

        jewelry.setItems(list);
        //add cell of button edit
        Callback<TableColumn<Jewelry, String>, TableCell<Jewelry, String>> cellFoctory = (TableColumn<Jewelry, String> param) -> {
            // make cell containing buttons
            final TableCell<Jewelry, String> cell = new TableCell<Jewelry, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                Jewelry jew = jewelry.getSelectionModel().getSelectedItem();
                                String query = "DELETE FROM jewelry WHERE id  = " + jew.getId() ;
                                Connection conn = getConnection();
                                Statement st = conn.createStatement();
                                st.execute(query);
                                ShowTable();
                            } catch (SQLException ex) {
                                Logger.getLogger(DeletejewelrydetailsController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        actioncol.setCellFactory(cellFoctory);

        jewelry.setItems(list);


    }

    //Search Method
    @FXML
    private void searchJewelryDetails() {
        ObservableList<Jewelry> list = getJewelryList();

        //Wrap the ObservableList in a FilteredList (initially display all data)
        FilteredList<Jewelry> filteredJewelryDetails = new FilteredList<>(list, b -> true);

        //Set the filter Predicate whenever the filter changes
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredJewelryDetails.setPredicate(jew -> {
                //If filter text is empty, display all details of jewelry
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //compare values
                String lowerCaseFIlter = newValue.toLowerCase();

                if (String.valueOf(jew.getType()).toLowerCase().indexOf(lowerCaseFIlter) != -1) {
                    return true;//Filter matches customer ID

                } else
                    return false;//Does not match
            });
        });

        //Wrap the FilteredList in a SortedList
        SortedList<Jewelry> sortedJewelryDetails = new SortedList<>(filteredJewelryDetails);

        //Bind the SortedList comparator to the jewelry TableView comparator.Otherwise, sorting the TableView would have no effect
        sortedJewelryDetails.comparatorProperty().bind(jewelry.comparatorProperty());

        //Add sorted (and filtered) data to the table
        jewelry.setItems(sortedJewelryDetails);
    }


    //event handler for get report button
    public void getReportButtonOnAction(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(reportbtn))
            generateJewelryReport(); //calling report generating method
    }

    //method to generate the report
    private void generateJewelryReport() {
        try{

            String report_name = "C:\\Users\\LENOVO\\IdeaProjects\\jewelryinventoryfunction\\src\\jewelryinventoryfunction\\test.pdf";
            //create Document object
            Document document = new Document();
            //set pdf instance
            PdfWriter.getInstance(document,new FileOutputStream(report_name));
            //open the document
            document.open();

            Font bold = new Font(Font.FontFamily.HELVETICA,30,Font.BOLD);
            Paragraph p = new Paragraph("                                                 Available Jewelry Details");
            document.add(p);


            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));




            //get jewelry details with result set
            Connection conn = getConnection();
            String query1 = "SELECT * FROM jewelry";
            Statement st1;
            ResultSet r;

            try {
                st1 = conn.createStatement();
                r = st1.executeQuery(query1);

                if (r != null) {
                    while (r.next()) {
                        PdfPTable table = new PdfPTable(7);

                        PdfPCell c1 = new PdfPCell(new Phrase("id"));
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("name"));
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("type"));
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("meterial"));
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("weight"));
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("quantity"));
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("price"));
                        table.addCell(c1);

                        table.setHeaderRows(1);

                        table.addCell(String.valueOf(r.getInt("id")));
                        table.addCell(String.valueOf(r.getString("name")));
                        table.addCell(String.valueOf(r.getString("type")));
                        table.addCell(String.valueOf(r.getString("meterial")));
                        table.addCell(String.valueOf(r.getDouble("weight")));
                        table.addCell(String.valueOf(r.getInt("quantity")));
                        table.addCell(String.valueOf(r.getDouble("price")));

                        document.add(table);
                    }
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
