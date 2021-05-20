package jewelryinventoryfunction;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class homecontroller implements Initializable {


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
        private TextField search;
        @FXML
        private JFXButton addjewelrylogout;
        @FXML
        private JFXButton addbtn2;
        @FXML
        private JFXButton updatebtn2;
        @FXML
        private JFXButton deletebtn2;
        @FXML
        private JFXButton gem;
        @FXML
        private JFXButton notibtn;
        @FXML
        private JFXButton home;
        @FXML
        private JFXButton reportbtn;

        Jewelry jew;
        PreparedStatement pst;

        @Override
        public void initialize(URL url, ResourceBundle rb) {

            showTable();
            searchJewelryDetails() ;


        }

        @FXML
        public void logoutjewelryAction(ActionEvent addjev1) throws IOException {
            addjewelrylogout.getScene().getWindow().hide();

            Stage logoutjewelry = new Stage();
            Parent jewelryroot2 = FXMLLoader.load(getClass().getResource("/jewleryinventoryfunction/login.fxml"));
            Scene jewelryscene2 = new Scene(jewelryroot2);
            logoutjewelry.setScene(jewelryscene2);
            logoutjewelry.show();
            logoutjewelry.setResizable(false);
        }

        @FXML
        public void dashbordOnAction(ActionEvent event) {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                Stage updatej = (Stage) updatebtn2.getScene().getWindow();
                updatej.setTitle("City of Gems");
                updatej.setScene(new Scene(root, 1250, 800));
                updatej.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @FXML
        public void addJewelryOnAction(ActionEvent event) {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("addjewelrydetails.fxml"));
                Stage hm3 = (Stage) home.getScene().getWindow();
                hm3.setTitle("City of Gems");
                hm3.setScene(new Scene(root, 1250, 800));
                hm3.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @FXML
        public void updateJewelryOnAction(ActionEvent event) {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("updatejewelrydetails.fxml"));
                Stage updatej = (Stage) updatebtn2.getScene().getWindow();
                updatej.setTitle("City of Gems");
                updatej.setScene(new Scene(root, 1250, 800));
                updatej.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @FXML
        public void deleteJewelryOnAction(ActionEvent event) {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("deletejewelrydetails.fxml"));
                Stage deletej = (Stage) deletebtn2.getScene().getWindow();
                deletej.setTitle("City of Gems");
                deletej.setScene(new Scene(root, 1250, 800));
                deletej.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void storeGemOnAction(ActionEvent event) {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("recievedgemdetails.fxml"));
                Stage storeg = (Stage) gem.getScene().getWindow();
                storeg.setTitle("City of Gems");
                storeg.setScene(new Scene(root, 1250, 800));
                storeg.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void notiSendOnAction(ActionEvent event) {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("gemrequestform.fxml"));
                Stage not = (Stage) notibtn.getScene().getWindow();
                not.setTitle("City of Gems");
                not.setScene(new Scene(root, 1250, 800));
                not.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    //database connection
        public Connection getConnection() {
            Connection conn;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cityofgems", "root", "jami1998");
                return conn;
            } catch (Exception ex) {
                System.out.println("Error!" + ex.getMessage());
                return null;
            }
        }

        //get jewelry details
        public ObservableList<Jewelry> getJewelryList() {
            ObservableList<Jewelry> jewelryList = FXCollections.observableArrayList();
            Connection conn = getConnection();
            String query = "SELECT * FROM jewelry ";
            Statement st;
            ResultSet rs;

            try {
                st = conn.createStatement();
                rs = st.executeQuery(query);
                Jewelry jew;

                while (rs.next()) {
                    jew = new Jewelry(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getString("meterial"), rs.getDouble("weight"), rs.getInt("quantity"), rs.getDouble("price"));
                    jewelryList.add(jew);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return jewelryList;
        }


        //get jewelry details
        public void showTable() {
            ObservableList<Jewelry> list = getJewelryList();

            //set values to the columns
            idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
            namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
            typecol.setCellValueFactory(new PropertyValueFactory<>("type"));
            meterialcol.setCellValueFactory(new PropertyValueFactory<>("meterial"));
            weightcol.setCellValueFactory(new PropertyValueFactory<>("weight"));
            quancol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

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


     /*   @Override
           public void actionPerformed(ActionEvent e){

            try{
                String idcol = search.getText();
                pst = getConnection().prepareStatement("SELECT * FROM jewelry WHERE id = ?");
                pst.setString(1,idcol);

                ResultSet rs = pst.executeQuery();

                if(rs.next() == true ){
                    String namecol =rs.getString(1);
                    String typecol = rs.getString(2);
                    String meterialcol = rs.getString(3);
                    String weightcol = rs.getString(4);
                    String quancol = rs.getString(5);
                    String pricecol = rs.getString(6);
                }
            }
        }

*/

    /*    public void search(ActionEvent event) {
            try {

                String id = search.getText();
                pst = getConnection().prepareStatement("select name,type,meterial,weight,quantity,price from jewelry where id = ?");
                pst.setString(1, id);
                ResultSet rs = pst.executeQuery();

                if (rs.next() == true) {
                    String name = rs.getString(1);
                    String type = rs.getString(2);
                    String meterial = rs.getString(3);
                    String weight = rs.getString(4);
                    String quantity = rs.getString(5);
                    String price = rs.getString(6);


                } else {
                    search.setText("");

                    JOptionPane.showMessageDialog(null, "Invalid Product ID");

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

     */
    }

