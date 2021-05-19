package supplierManagement;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.Statement;

public class NewPurchaseOrderController implements Initializable {

    @FXML
    private JFXButton newpurchhome;
    @FXML
    private JFXButton newpurchlogout;

    @FXML
    private JFXButton checkGemLogout;

    //Table View
    @FXML
    private TableView <GetGemstonesToOrder> viewGemstones;
    @FXML
    private TableColumn <GetGemstonesToOrder, Integer> checkOrderGemIdColumn;
    @FXML
    private TableColumn <GetGemstonesToOrder, String> checkGemstonesGemDesColumn;
    @FXML
    private TableColumn <GetGemstonesToOrder, Double> checkGemstoneReOrderColumn;
    @FXML
    private TableColumn <GetGemstonesToOrder, Double> checkGemstoneUnitPriceColumn;

    @FXML
    private TextField checkGemstoneSearchField;
    @FXML
    private JFXButton checkGemstonesGetGemstones;
    @FXML
    private JFXButton checkgemstonesmenubutton;
    @FXML
    private JFXButton checkGemstonenewpurchordmenu;
    @FXML
    private JFXButton checkGemstoneNewsupmenu;
    @FXML
    private JFXButton checkGemstoneGemstoneordlistmenu;
    @FXML
    private JFXButton checkGemstoneSuplistmenu;

    //user input declarations
    @FXML
    private TextField newpurchgemid;
    @FXML
    private TextField newpurchsupplierId;
    @FXML
    private TextField newpurchdescription;
    @FXML
    private JFXDatePicker newpurchdate;
    @FXML
    private ComboBox<String> newpurchshape;
    @FXML
    private Spinner<Double> newpurchweight;
    //Spinner value factory for weight
    final double initialValue = 0.5;
    SpinnerValueFactory<Double> svt = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.5,25.0,initialValue);
    @FXML
    private TextField newpurchquantity;
    @FXML
    private TextField newpurchprice;
    @FXML
    private TextField newpurchsuppliernamesearch;
    @FXML
    private TextField newpurchsupemail;
    @FXML
    private JFXDatePicker newpurchdeliverydate;
    @FXML
    private JFXTextArea newpurchadditionalnotes;
    @FXML
    private TextField orderStatus;

    //buttons
    @FXML
    private JFXButton newpurchsubmitorderbtn;
    @FXML
    private JFXButton newpurchresetformbtn;
    @FXML
    private JFXButton newpurchaddsupplierbtn;
    @FXML
    private JFXButton newpurchcancelbtn;

    //navigation pane buttons
    @FXML
    private JFXButton newpurchcheckgemstonesmenu;
    @FXML
    private JFXButton newpurchnewpurchordmenu;
    @FXML
    private JFXButton newpurchnewsupmenu;
    @FXML
    private JFXButton newpurchgemstoneordlistmenu;
    @FXML
    private JFXButton supListNavMenu;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Array to display shapes in comboBox
        ObservableList<String> shapes = FXCollections.observableArrayList(
                "Round",
                "Square",
                "Oval",
                "Radiant",
                "Heart",
                "Emerald"
        );
        newpurchshape.setItems(shapes);
        newpurchshape.setPromptText("Round");
        //spinner value factory
        newpurchweight.setValueFactory(svt);
        orderStatus.setText("Processing");
    }

    @FXML
    public void getGemstonesToOrderButtonOnAction(ActionEvent event){
        showTable();
    }

    //get gemstones details where the reorder level is 5
    public ObservableList<GetGemstonesToOrder> getGemStoneReOrderList() {
        ObservableList<GetGemstonesToOrder> gemStoneReOrderList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT gem_id, gem_description,reorder_level,price_of_each FROM cityofgems.gem_inventory_table WHERE reorder_level = 5";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            GetGemstonesToOrder gemLevel;

            while (rs.next()) {
                gemLevel = new GetGemstonesToOrder(rs.getInt("gem_id"), rs.getString("gem_description"), rs.getDouble("reorder_level"), rs.getDouble("price_of_each"));
                gemStoneReOrderList.add(gemLevel);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return gemStoneReOrderList;
    }


    //method to show gem details in table
    public void showTable(){
        ObservableList<GetGemstonesToOrder> list = getGemStoneReOrderList();

        //set values to the columns
        checkOrderGemIdColumn.setCellValueFactory(new PropertyValueFactory<>("gem_id"));
        checkGemstonesGemDesColumn.setCellValueFactory(new PropertyValueFactory<>("gem_description"));
        checkGemstoneReOrderColumn.setCellValueFactory(new PropertyValueFactory<>("reorder_level"));
        checkGemstoneUnitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price_of_each"));


        viewGemstones.setItems(list);
    }

    @FXML
    public void NavPaneSupplierListOnAction(ActionEvent event) throws IOException {
        supListNavMenu.getScene().getWindow().hide();

        Stage stageSS = new Stage();
        Parent pRoot = FXMLLoader.load(getClass().getResource("/supplierManagement/supplierList.fxml"));
        Scene sceneSS = new Scene(pRoot);
        stageSS.setScene(sceneSS);
        stageSS.show();
        stageSS.setResizable(false);
    }

    //method for logout
    @FXML
    public void logoutpurchorderAction(ActionEvent purchordev1) throws IOException {
        newpurchlogout.getScene().getWindow().hide();

        Stage logoutpurchord = new Stage();
        Parent purchordroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene purchordscene2 = new Scene(purchordroot2);
        logoutpurchord.setScene(purchordscene2);
        logoutpurchord.show();
        logoutpurchord.setResizable(false);
    }


    //method for navigation pane new supplier button
    @FXML
    public void newsuppliermenubuttonAction(ActionEvent purchordev2) throws IOException {
        newpurchnewsupmenu.getScene().getWindow().hide();

        Stage newpurchbtn = new Stage();
        Parent newpurchoot3 = FXMLLoader.load(getClass().getResource("/supplierManagement/newSupplier.fxml"));
        Scene newpurchscene3 = new Scene(newpurchoot3);
        newpurchbtn.setScene(newpurchscene3);
        newpurchbtn.show();
        newpurchbtn.setResizable(false);
    }

    @FXML
    public void newpurchgemstoneordlistmenuOnAction(ActionEvent event) throws IOException{
        newpurchgemstoneordlistmenu.getScene().getWindow().hide();

        Stage stage77 = new Stage();
        Parent parentRt = FXMLLoader.load(getClass().getResource("/supplierManagement/viewGemstoneOrderList.fxml"));
        Scene scene9 = new Scene(parentRt);
        stage77.setScene(scene9);
        stage77.show();
        stage77.setResizable(false);
    }


    //method for the new purchase order button in navigation pane to direct to the purchase order UI
    @FXML
    public void newpurchaseordermenubuttonAction(ActionEvent newpurchordev2) throws IOException {
        newpurchnewpurchordmenu.getScene().getWindow().hide();

        Stage newpurchordbtn = new Stage();
        Parent newpurchordroot4 = FXMLLoader.load(getClass().getResource("/supplierManagement/newPurchaseOrder.fxml"));
        Scene newpurchordscene4 = new Scene(newpurchordroot4);
        newpurchordbtn.setScene(newpurchordscene4);
        newpurchordbtn.show();
        newpurchordbtn.setResizable(false);
    }

    //navPane MAINUI
    @FXML
    public void newPurchCheckGemstoneOnAction(ActionEvent newpurchordev3) throws IOException {
        newpurchcheckgemstonesmenu.getScene().getWindow().hide();

        Stage newPurchCheckGembtn = new Stage();
        Parent newPurchCheckroot2 = FXMLLoader.load(getClass().getResource("/supplierManagement/mainUI.fxml"));
        Scene newPurchCheckscene3 = new Scene(newPurchCheckroot2);
        newPurchCheckGembtn.setScene(newPurchCheckscene3);
        newPurchCheckGembtn.show();
        newPurchCheckGembtn.setResizable(false);
    }


    //method for the submit order button to store details in database
    @FXML
    public void createNewOrderButtonOnAction(ActionEvent event){

        //Validating fields
        if (newpurchgemid.getText().isEmpty() | newpurchshape.getItems().toString().isEmpty() | newpurchquantity.getText().isEmpty()| newpurchsuppliernamesearch.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Field validation");
            alert.setHeaderText("Empty fields!");
            alert.setContentText("Please fill all the necessary values" + "\n" + "\n" +
                    "You cannot proceed without entering the values");
            alert.setHeight(400);
            alert.showAndWait();
        } else{

            insertPurchaseOrderDetails();

            Notifications notificationBuilder1 = Notifications.create();
            notificationBuilder1.title("Notification");
            notificationBuilder1.text("New purchase order details saved successfully");
            notificationBuilder1.hideAfter(Duration.seconds(3));
            notificationBuilder1.position(Pos.BOTTOM_CENTER);
            notificationBuilder1.darkStyle();
            notificationBuilder1.show();
            Stage stage = (Stage) newpurchsubmitorderbtn.getScene().getWindow();

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
    private void insertPurchaseOrderDetails() {
        String query ="INSERT into cityofgems.purchase_order_table (p_OrderId, g_id, supp_id, g_description, p_ordered_date, g_shape, g_weight, g_quantity, g_price, expected_delivery_date, p_ad_notes, p_order_status, g_sup_name, g_sup_email)VALUES ("+0+"," + newpurchgemid.getText() + ", "+newpurchsupplierId.getText()+" ,'"+newpurchdescription.getText() + "','" + newpurchdate.getValue() + "','" + newpurchshape.getSelectionModel().getSelectedItem()+ "'," + newpurchweight.getValue() + "," + newpurchquantity.getText() + ",'" + newpurchprice.getText() + "','"+newpurchdeliverydate.getValue()+"','"+newpurchadditionalnotes.getText()+"','"+orderStatus.getText()+"','"+newpurchsuppliernamesearch.getText()+"','"+newpurchsupemail.getText()+"')";
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

    //cancel button OnAction method
    @FXML
    public void cancelButtonOnAction(ActionEvent event){

        //Confirmation
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("CANCEL!");
        alert.setContentText("Are you sure you want to cancel?");
        alert.setHeight(400);
        alert.showAndWait();

        Stage stage2 =(Stage) newpurchcancelbtn.getScene().getWindow();
        stage2.close();

    }

    //method for the reset button
    private void resettingForm(){
        //setting null values
        newpurchgemid.setText(null);
        newpurchdescription.setText(null);
        newpurchsupplierId.setText(null);

        newpurchdate.setValue(null);
        newpurchshape.setPromptText("Round");

        newpurchweight.setValueFactory(null);
        newpurchweight.setValueFactory(svt);

        newpurchquantity.setText(null);
        newpurchprice.setText(null);
        newpurchdeliverydate.setValue(null);
        newpurchadditionalnotes.setText(null);
        orderStatus.setText(null);
        newpurchsuppliernamesearch.setText(null);
        newpurchsupemail.setText(null);
    }



    //resetForm button On Action method
    @FXML
    public void resetButtonOnAction(ActionEvent event){
        Stage stage =(Stage) newpurchresetformbtn.getScene().getWindow();
        resettingForm();
    }


}


