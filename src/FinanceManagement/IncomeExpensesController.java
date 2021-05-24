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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IncomeExpensesController implements Initializable {

    //Text inputs
    @FXML
    private TextField incomeExpensesDescription;
    @FXML
    private ComboBox<String> incomeExpensesType;
    @FXML
    private DatePicker incomeExpensesDate;
    @FXML
    private TextField incomeExpensesAmount;

    //Buttons
    @FXML
    private Button incomeExpensesMenu;
    @FXML
    private Button searchIncomeExpensesMenu;
    @FXML
    private Button insertHome;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private JFXButton insertlogout;



    //Create income and expenses type selection list
    ObservableList<String> incomeExpensestypelist = FXCollections.observableArrayList("Income", "Expenditure");

    //declare variables
    private Connection connection;


    public void initialize(URL url, ResourceBundle rb) {

        //Initialize the type combobox
        incomeExpensesType.setItems(incomeExpensestypelist);

    }


    //Save Button method (Direct to "Income and Expenses" page)
    @FXML
    public void saveIncomeExpensesOnAction(ActionEvent event){

        //Validate data (If any data does not input to insert form popup alert error box else Insert data into Database or clear inputs from form)
        if(checkUnfilledFieldsValidation() && checkAmountFieldValidation() && checkAmountFieldZeroValidation() ) {
            insertIncomeExpensesRecord();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("incomeExpenses.fxml"));
                Stage saveInsert = (Stage) saveBtn.getScene().getWindow();
                saveInsert.setTitle("City of Gems");
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
    private void insertIncomeExpensesRecord(){
        String query = "INSERT into incomeandexpenses VALUES ("+0+",'"+ incomeExpensesDescription.getText() +"','"+ incomeExpensesType.getValue() +"','" +incomeExpensesDate.getValue()+"',"+incomeExpensesAmount.getText()+")";
        executeQuery(query);
        //showIncomeExpenses();
    }

    private void executeQuery(String query) {
        //Connection conn = getConnection();
        DBConnection.getConnection();
        Statement st;

        try {
            //st = conn.createStatement();
            st = DBConnection.getConnection().createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // Form Validation methods

    // (1)check if any unfilled fields in income and expenses form
    @FXML
    private boolean checkUnfilledFieldsValidation(){

        if(incomeExpensesDescription.getText().isEmpty() || incomeExpensesType.getValue().isEmpty() || incomeExpensesDate.getEditor().getText().isEmpty() ||incomeExpensesAmount.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter All values into the fields.");
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
        Matcher m1 = p.matcher(incomeExpensesAmount.getText());

        if((m1.find() && m1.group().equals(incomeExpensesAmount.getText()))) {

            return true;
        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please enter only a number to the Amount field.");
            alert.showAndWait();

            return false;
        }
    }

    // (3)check if the amount field input number is equal to 0
    @FXML
    private boolean checkAmountFieldZeroValidation(){
        if(Double.parseDouble(incomeExpensesAmount.getText()) > 0){
            return true;
        }

        else{

            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please enter the amount greater than 0");
            alert.showAndWait();

            return false;
        }
    }



    //Cancel button method (Clear Fields method)
    @FXML
    private void incomeExpensesDetailsClear() {
        //Set all the form inputs to null
        incomeExpensesDescription.setText(null);
        incomeExpensesType.setValue(null);
        incomeExpensesDate.setValue(null);
        incomeExpensesAmount.setText(null);
    }



    //"Home" Button method (Direct to "Dashboard" page)
    @FXML
    public void IncomeExpensesToHome(ActionEvent event){
        try {

            Parent root = FXMLLoader.load(getClass().getResource("financeHome.fxml"));
            Stage insertDashboard = (Stage) insertHome.getScene().getWindow();
            insertDashboard.setTitle("City of Gems");
            insertDashboard.setScene(new Scene(root, 1050, 780));
            insertDashboard.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //"Logout" Button method (Direct to "login" page)
    @FXML
    public void logoutAction(ActionEvent ev3) throws IOException {
        insertlogout.getScene().getWindow().hide();

        Stage logout = new Stage();//Create a Stage
        logout.setTitle("CITY OF GEMS - LOGIN");//Set Title of interface
        //Setup the Scene
        Parent root2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene scene2 = new Scene(root2);//Create a scene
        logout.setScene(scene2);//Set Scene object on the Stage
        logout.show();//Show the Stage which create above (makes the Stage visible and the exits)
        logout.setResizable(false);//User cannot resize the frame
    }



    // Nav bar
    //"Income and Expenses" Button method (Direct to "Income and Expenses" page)
    @FXML
    public void addIncomeExpensesOnAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("incomeExpenses.fxml"));
            Stage addInEx = (Stage) incomeExpensesMenu.getScene().getWindow();
            addInEx.setTitle("City of Gems");
            addInEx.setScene(new Scene(root, 1050,780));
            addInEx.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //"Search Income and Expenses" Button method (Direct to "Search Income and Expenses" page)
    @FXML
    public void addSearchIncomeExpensesOnAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("searchIncomeExpenses.fxml"));
            Stage addSeInEx = (Stage) searchIncomeExpensesMenu.getScene().getWindow();
            addSeInEx.setTitle("City of Gems");
            addSeInEx.setScene(new Scene(root, 1050,780));
            addSeInEx.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
