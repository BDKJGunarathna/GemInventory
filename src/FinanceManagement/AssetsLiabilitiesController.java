package FinanceManagement;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssetsLiabilitiesController implements Initializable {

    //Text inputs
    @FXML
    private TextField assetsLiabilitiesDescription;
    @FXML
    private ComboBox<String> assetsLiabilitiesType;
    @FXML
    private DatePicker assetsLiabilitiesDate;
    @FXML
    private TextField assetsLiabilitiesAmount;

    //Buttons
    @FXML
    private Button assetsLiabilitiesMenu;
    @FXML
    private Button searchAssetsLiabilitiesMenu;
    @FXML
    private Button assetsLiabilitiesHome;
    @FXML
    private Button assetsLiabilitiesCancelBtn;
    @FXML
    private Button assetsLiabilitiesSaveBtn;
    @FXML
    private JFXButton assetsLiabilitiesLogout;

    //products table
    @FXML
    private TableView<UpdateIncomeExpenses> assetsIncomeExpensesTable;
    @FXML
    private TableColumn<UpdateIncomeExpenses, Integer> assetsIncomeExpensesTableId;
    @FXML
    private TableColumn<UpdateIncomeExpenses, String> assetsIncomeExpensesTableDescription;
    @FXML
    private TableColumn<UpdateIncomeExpenses, String> assetsIncomeExpensesTableType;
    @FXML
    private TableColumn<UpdateIncomeExpenses, Date> assetsIncomeExpensesTableDate;
    @FXML
    private TableColumn<UpdateIncomeExpenses, Double> assetsIncomeExpensesTableAmount;



    //Create Assets and Liabilities type selection list
    ObservableList<String> assetsLiabilitiestypelist = FXCollections.observableArrayList("Asset", "Liability", "Equity");

    //declare variables
    private Connection connection;


    public void initialize(URL url, ResourceBundle rb) {

        //Initialize the assets liabilities type combobox
        assetsLiabilitiesType.setItems(assetsLiabilitiestypelist);
        showIncomeExpensesDetails();

    }

    // Table view
    //get assets liabilities list method
    public ObservableList<UpdateIncomeExpenses> getIncomeExpensesList(){
        ObservableList<UpdateIncomeExpenses> incomeExpensesList = FXCollections.observableArrayList();
        //Establishing a Connection
        DBConnection.getConnection();
        String query = "SELECT id, description, type, date, amount FROM incomeandexpenses ";
        Statement st;
        ResultSet rs;

        try{
            st = DBConnection.getConnection().createStatement();
            rs = st.executeQuery(query);
            UpdateIncomeExpenses inEX;

            while(rs.next()){
                inEX = new UpdateIncomeExpenses(rs.getInt("id"), rs.getString("description"), rs.getString("type"), rs.getDate("date"), rs.getDouble("amount"));
                incomeExpensesList.add(inEX);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return incomeExpensesList;
    }

    //get assets and liabilities details
    public void showIncomeExpensesDetails(){
        ObservableList<UpdateIncomeExpenses> list = getIncomeExpensesList();

        //set values to the columns
        assetsIncomeExpensesTableId.setCellValueFactory(new PropertyValueFactory<UpdateIncomeExpenses, Integer>("id"));
        assetsIncomeExpensesTableDescription.setCellValueFactory(new PropertyValueFactory<UpdateIncomeExpenses, String>("description"));
        assetsIncomeExpensesTableType.setCellValueFactory(new PropertyValueFactory<UpdateIncomeExpenses, String>("type"));
        assetsIncomeExpensesTableDate.setCellValueFactory(new PropertyValueFactory<UpdateIncomeExpenses, Date>("date"));
        assetsIncomeExpensesTableAmount.setCellValueFactory(new PropertyValueFactory<UpdateIncomeExpenses, Double>("amount"));

        assetsIncomeExpensesTable.setItems(list);
    }



    //Save Button method (Direct to "Assets and Liabilities" page)
    @FXML
    public void saveAssetsLiabilitiesOnAction(ActionEvent event){

        //Check when the save button is clicked, inserting values are matching to the validations
        if(checkUnfilledFieldsValidation() && checkAmountFieldValidation() && checkAmountFieldZeroValidation() ) {
            insertAssetsLiabilitiesRecord();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("assetsLiabilities.fxml"));
                Stage saveInsert = (Stage) assetsLiabilitiesSaveBtn.getScene().getWindow();
                saveInsert.setTitle("Gem Merchant System");
                saveInsert.setScene(new Scene(root, 1050, 780));
                saveInsert.show();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

            try {
                //Alert Information box
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Record inserted successfully");
                alert.show();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        }
    }


    // SQL query to insert data
    private void insertAssetsLiabilitiesRecord(){
        String query = "INSERT into assetsandliabilities VALUES ("+0+",'"+ assetsLiabilitiesDescription.getText() +"','"+ assetsLiabilitiesType.getValue() +"','" +assetsLiabilitiesDate.getValue()+"',"+assetsLiabilitiesAmount.getText()+")";
        executeQuery(query);
    }

    private void executeQuery(String query) {
        //Establishing a Connection
        DBConnection.getConnection();
        Statement st;

        try {
            st = DBConnection.getConnection().createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // Form Validation methods

    // (1)check if any unfilled fields in assets and liabilities form
    @FXML
    private boolean checkUnfilledFieldsValidation(){

        if(assetsLiabilitiesDescription.getText().isEmpty() || assetsLiabilitiesType.getValue().isEmpty() || assetsLiabilitiesDate.getEditor().getText().isEmpty() ||assetsLiabilitiesAmount.getText().isEmpty()){
            //Alert Information box
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please enter all values into the fields.");
            alert.showAndWait();

            return false;
        }

        else{
            return true;
        }

    }


    // (2)check if the amount field input is a number
    @FXML
    private boolean checkAmountFieldValidation(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m1 = p.matcher(assetsLiabilitiesAmount.getText());

        if((m1.find() && m1.group().equals(assetsLiabilitiesAmount.getText()))) {

            return true;
        }
        else{
            //Alert Information box
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please enter only numbers to the Amount field.");
            alert.showAndWait();

            return false;
        }
    }

    // (3)check if the amount field input number is equal to 0
    @FXML
    private boolean checkAmountFieldZeroValidation(){
        if(Double.parseDouble(assetsLiabilitiesAmount.getText()) > 0){
            return true;
        }

        else{
            //Alert Information box
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an amount greater than 0");
            alert.showAndWait();

            return false;
        }
    }



    //Cancel button method (Fields will be cleared)
    @FXML
    private void assetsLiabilitiesDetailsClear() {
        //Set all the form inputs to null
        assetsLiabilitiesDescription.setText(null);
        assetsLiabilitiesType.setValue(null);
        assetsLiabilitiesDate.setValue(null);
        assetsLiabilitiesAmount.setText(null);
    }



    //Home Button method (Direct to financeHome page)
    @FXML
    public void AssetsLiabilitiesToHome(ActionEvent event){
        try {

            Parent root = FXMLLoader.load(getClass().getResource("financeHome.fxml"));
            Stage insertDashboard = (Stage) assetsLiabilitiesHome.getScene().getWindow();
            insertDashboard.setTitle("Gem Merchant System");
            insertDashboard.setScene(new Scene(root, 1050, 780));
            insertDashboard.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //Logout Button method (Direct to login page)
    @FXML
    public void AssetsLiabilitieslogoutAction(ActionEvent ev3) throws IOException {
        assetsLiabilitiesLogout.getScene().getWindow().hide();

        Stage logout = new Stage();
        logout.setTitle("CITY OF GEMS - LOGIN");
        Parent root2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene scene2 = new Scene(root2);
        logout.setScene(scene2);
        logout.show();
        logout.setResizable(false);
    }



    // Nav bar
    //Income and Expenses Button method (Direct to Income and Expenses page)
    @FXML
    public void addAssetsLiabilitiesOnAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("assetsLiabilities.fxml"));
            Stage addInEx = (Stage) assetsLiabilitiesMenu.getScene().getWindow();
            addInEx.setTitle("Gem Merchant System");
            addInEx.setScene(new Scene(root, 1050,780));
            addInEx.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Search Income and Expenses Button method (Direct to Search Income and Expenses page)
    @FXML
    public void addSearchAssetsLiabilitiesOnAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("searchAssetsLiabilities.fxml"));
            Stage addSeInEx = (Stage) searchAssetsLiabilitiesMenu.getScene().getWindow();
            addSeInEx.setTitle("Gem Merchant System");
            addSeInEx.setScene(new Scene(root, 1050,780));
            addSeInEx.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

