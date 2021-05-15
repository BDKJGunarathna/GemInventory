package jewelryinventoryfunction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.sql.DriverManager;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
        private JFXButton store2;
        @FXML
        private JFXButton notibtn;
        @FXML
        private JFXButton home;
        @Override
        public void initialize(URL url, ResourceBundle rb){

            showTable();

       /*     ObservableList<Jewelry> jewelryList = FXCollections.observableArrayList();
            // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<Jewelry> filteredData = new FilteredList<>(jewelryList, b -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            search.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(jewel -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (jewel.getType().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; // Filter matches type.
                    }
                    else
                        return false; // Does not match.
                });
            });

            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Jewelry> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(jewelry.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            jewelry.setItems(sortedData);
            */
        }

        @FXML
        public void logoutjewelryAction (ActionEvent addjev1) throws IOException {
            addjewelrylogout.getScene().getWindow().hide();

            Stage logoutjewelry = new Stage();
            Parent jewelryroot2 = FXMLLoader.load(getClass().getResource("/jewleryinventoryfunction/login.fxml"));
            Scene jewelryscene2 = new Scene(jewelryroot2);
            logoutjewelry.setScene(jewelryscene2);
            logoutjewelry.show();
            logoutjewelry.setResizable(false);
        }
        @FXML
        public void dashbordOnAction(ActionEvent event){

            try {
                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                Stage updatej = (Stage) updatebtn2.getScene().getWindow();
                updatej.setTitle("City of Gems");
                updatej.setScene(new Scene(root, 1250,800));
                updatej.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        @FXML
        public void addJewelryOnAction(ActionEvent event){

            try {
              Parent root = FXMLLoader.load(getClass().getResource("addjewelrydetails.fxml"));
              Stage hm3 = (Stage) home.getScene().getWindow();
              hm3.setTitle("City of Gems");
              hm3.setScene(new Scene(root, 1250,800));
              hm3.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        @FXML
        public void updateJewelryOnAction(ActionEvent event){

            try {
                Parent root = FXMLLoader.load(getClass().getResource("updatejewelrydetails.fxml"));
                Stage updatej = (Stage) updatebtn2.getScene().getWindow();
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
                Stage deletej = (Stage) deletebtn2.getScene().getWindow();
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
                Stage storeg = (Stage) store2.getScene().getWindow();
                storeg.setTitle("City of Gems");
                storeg.setScene(new Scene(root, 1250,800));
                storeg.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public void notiSendOnAction(ActionEvent event){

            try {
                Parent root = FXMLLoader.load(getClass().getResource("gemrequestform.fxml"));
                Stage not = (Stage) notibtn.getScene().getWindow();
                not.setTitle("City of Gems");
                not.setScene(new Scene(root, 1250,800));
                not.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }




        //database connection
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
        public void showTable(){
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

    }


