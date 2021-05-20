package supplierManagement;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class NewSupplierController implements Initializable {
///------------------------variable declaration------------------------------------
        //title bar
        @FXML
        private JFXButton newsupplierhomebtn;
        @FXML
        private JFXButton newsupplierlogout;

        @FXML
        private Label LabelSelectedGType;

        //navigation pane buttons
        @FXML
        private JFXButton nssuppliercheckgemstonesbtn;
        @FXML
        private JFXButton nssupplierpurchaseorderbtn;
        @FXML
        private JFXButton nssuppliernewsupplierbtn;
        @FXML
        private JFXButton nssuppliergemstonebtn;
        @FXML
        private JFXButton nssuppliersuplistbtn;

        //User input fields
        @FXML
        private TextField nssuppliername;
        @FXML
        private TextField nssupplieremail;
        @FXML
        private TextField nssupplierphonenumber;
        @FXML
        private TextField nssupplierwebsite;
        @FXML
        private JFXTextArea nssupplierdescription;
        @FXML
        private TextField nssupplierAddress;
        @FXML
        private TextField nssuppliercountry;

        //checklist
        @FXML
        private JFXCheckBox CB1diamonds;
        @FXML
        private JFXCheckBox CB2sapphire;
        @FXML
        private JFXCheckBox CB3ruby;
        @FXML
        private JFXCheckBox CB4topaz;
        @FXML
        private JFXCheckBox CB5zircon;
        @FXML
        private JFXCheckBox CB6moonstone;
        @FXML
        private JFXCheckBox CB7peridot;
        @FXML
        private JFXCheckBox CB8quartz;
        @FXML
        private JFXCheckBox CB9aquamarine;
        @FXML
        private JFXCheckBox CB10spinel;
        @FXML
        private JFXCheckBox CB11amethyst;
        @FXML
        private JFXCheckBox CB12rockcrystal;
        @FXML
        private JFXCheckBox CB13citrine;
        //end of checklist

        //Buttons
        @FXML
        private JFXButton nssupplieradd;
        @FXML
        private JFXButton nssupplierreset;


        @Override
        public void initialize(URL location, ResourceBundle resources) {

        }

/////-----------------scene transitions--------------------------------------------------------------------------
        //to go to dashboard/home
        @FXML
        public void newSupplierToHome(ActionEvent event) throws IOException {
            newsupplierhomebtn.getScene().getWindow().hide();

            Stage supStage = new Stage();
            Parent parentSup = FXMLLoader.load(getClass().getResource("/supplierManagement/Dashboard.fxml"));
            Scene supToDash = new Scene(parentSup);
            supStage.setScene(supToDash);
            supStage.show();
            supStage.setResizable(false);
        }

        //to log out
        @FXML
        public void logoutnewsupplierAction(ActionEvent newsupev1) throws IOException {
            newsupplierlogout.getScene().getWindow().hide();

            Stage logoutnewsupplier = new Stage();
            Parent newsupplierroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
            Scene newsupplierscene2 = new Scene(newsupplierroot2);
            logoutnewsupplier.setScene(newsupplierscene2);
            logoutnewsupplier.show();
            logoutnewsupplier.setResizable(false);
        }

        //to load own page
        @FXML
        public void newsuppliermenubuttonAction(ActionEvent newsupev2) throws IOException {
            nssuppliernewsupplierbtn.getScene().getWindow().hide();

            Stage newsupbtn = new Stage();
            Parent newsupplierroot3 = FXMLLoader.load(getClass().getResource("/supplierManagement/newSupplier.fxml"));
            Scene newsupplierscene3 = new Scene(newsupplierroot3);
            newsupbtn.setScene(newsupplierscene3);
            newsupbtn.show();
            newsupbtn.setResizable(false);
        }

        //to go to new purchase order
        @FXML
        public void newpurchaseordermenubuttonAction(ActionEvent newsupev2) throws IOException {
            nssupplierpurchaseorderbtn.getScene().getWindow().hide();

            Stage purchordbtn = new Stage();
            Parent purchordroot4 = FXMLLoader.load(getClass().getResource("/supplierManagement/newPurchaseOrder.fxml"));
            Scene purchordscene4 = new Scene(purchordroot4);
            purchordbtn.setScene(purchordscene4);
            purchordbtn.show();
            purchordbtn.setResizable(false);
        }

        //to go to gemstone order list window
        @FXML
        public void newSupToGorderList(ActionEvent event) throws IOException {
            nssuppliergemstonebtn.getScene().getWindow().hide();

            Stage supStage1 = new Stage();
            Parent parentSup1 = FXMLLoader.load(getClass().getResource("/supplierManagement/viewGemstoneOrderList.fxml"));
            Scene supToGlist = new Scene(parentSup1);
            supStage1.setScene(supToGlist);
            supStage1.show();
            supStage1.setResizable(false);
        }

       //to go to supplier list window
        @FXML
        public void newSupToSupList(ActionEvent event) throws IOException {
            nssuppliersuplistbtn.getScene().getWindow().hide();

            Stage supStage3 = new Stage();
            Parent parentSup = FXMLLoader.load(getClass().getResource("/supplierManagement/supplierList.fxml"));
            Scene supToSList = new Scene(parentSup);
            supStage3.setScene(supToSList);
            supStage3.show();
            supStage3.setResizable(false);
        }

    ////---------------------------------INSERT-------------------------------------------------------------------------------------------------------

        //method to display the selected checkbox items in the label
        @FXML
        private void checkEvent(ActionEvent event){
            String message = "";

            //checking which items are checked by the user
            if(CB1diamonds.isSelected()){
                message += CB1diamonds.getText() + "\n";
            }
            if(CB2sapphire.isSelected()){
                message += CB2sapphire.getText() + "\n";
            }
            if(CB3ruby.isSelected()){
                message += CB3ruby.getText() + "\n";
            }
            if(CB4topaz.isSelected()){
                message += CB4topaz.getText() + "\n";
            }
            if(CB5zircon.isSelected()){
                message += CB5zircon.getText() + "\n";
            }
            if(CB6moonstone.isSelected()){
                message += CB6moonstone.getText() + "\n";
            }
            if(CB7peridot.isSelected()){
                message += CB7peridot.getText() + "\n";
            }
            if(CB8quartz.isSelected()){
                message += CB8quartz.getText() + "\n";
            }
            if(CB9aquamarine.isSelected()){
                message += CB9aquamarine.getText() + "\n";
            }
            if(CB10spinel.isSelected()){
                message += CB10spinel.getText() + "\n";
            }
            if(CB11amethyst.isSelected()){
                message += CB11amethyst.getText() + "\n";
            }
            if(CB12rockcrystal.isSelected()){
                message += CB12rockcrystal.getText() + "\n";
            }
            if(CB13citrine.isSelected()){
                message += CB13citrine.getText() + "\n";
            }
            LabelSelectedGType.setText(message); //setting the selected checked item type to display in label
        }

        //event handler for add supplier button to create Supplier
        @FXML
        public void addSupplierButtonOnAction(ActionEvent event) {

            //Validating empty fields (supplier name, supplier phone, supplier email, supplying gemstone types, country)
            if (nssuppliername.getText().isEmpty() || nssupplierphonenumber.getText().isEmpty() || nssupplieremail.getText().isEmpty() || LabelSelectedGType.getText().isEmpty() || nssuppliercountry.getText().isEmpty()) {

                //Alert box to warn user of empty fields
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Field validation");
                alert.setHeaderText("Empty fields!");
                alert.setContentText("Please fill all the necessary values" + "\n" + "\n" +
                        "You cannot proceed without entering the values");
                alert.setHeight(400);
                alert.showAndWait();
            } else {
                insertSupplierDetails(); //calling the method to insert data into database

                //showing a notification that supplier is created successfully
                Notifications notificationBuilder = Notifications.create();
                notificationBuilder.title("Notification");
                notificationBuilder.text("New Supplier added successfully");
                notificationBuilder.hideAfter(Duration.seconds(5));
                notificationBuilder.position(Pos.BOTTOM_CENTER);
                notificationBuilder.darkStyle();
                notificationBuilder.show();

                Stage stage = (Stage) nssupplieradd.getScene().getWindow();
            }
        }


        //establishing database connection
        public Connection getConnection() {
            Connection con;
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cityofgems", "root", "DBPassword");
                return con;
            } catch (Exception e) {
                System.out.println("Error!" + e.getMessage());
            return null;
            }
        }

        //method/sql query for inserting details into the database
        private void insertSupplierDetails() {
            String query ="INSERT into supplier_info_table (supplierID, s_name, s_phone, s_email, s_website, s_description, s_address, supp_gemstoneTypes, s_country)VALUES ("+0+",'" + nssuppliername.getText() + "', '"+nssupplierphonenumber.getText() + "','" + nssupplieremail.getText() + "','" + nssupplierwebsite.getText() + "','" + nssupplierdescription.getText() + "','" + nssupplierAddress.getText() + "','" + LabelSelectedGType.getText() + "','"+nssuppliercountry.getText()+"')";
            executeUpdate(query);
        }

        //inserting data into database
        private void executeUpdate (String query){
            Connection con = getConnection();
            Statement st;
            try {
                st = con.createStatement();
                st.executeUpdate(query);
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }

      //-------------resetForm----------------------
        //resetForm button On Action method
        @FXML
        public void SupplierResetButtonOnAction(ActionEvent event){
            Stage stage =(Stage) nssupplierreset.getScene().getWindow();
            resetForm();
        }

        //method for the reset button
        private void resetForm() {

            //setting null values
            nssuppliername.setText(null);
            nssupplieremail.setText(null);
            nssupplierphonenumber.setText(null);
            nssupplierwebsite.setText(null);
            nssupplierdescription.setText(null);
            nssupplierAddress.setText(null);
            nssuppliercountry.setText(null);
            nssupplierdescription.setText(null);
            LabelSelectedGType.setText(null);

        }



}


