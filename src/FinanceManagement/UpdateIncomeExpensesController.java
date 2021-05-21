package FinanceManagement;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateIncomeExpensesController implements Initializable {


    //Table
    @FXML
    private TableView<UpdateIncomeExpenses> updateIncomeExpensesTable;
    @FXML
    private TableColumn<UpdateIncomeExpenses, Integer> updateIncomeExpensesId;
    @FXML
    private TableColumn<UpdateIncomeExpenses, String> updateIncomeExpensesTableDescription;
    @FXML
    private TableColumn<UpdateIncomeExpenses, String> updateIncomeExpensesTableType;
    @FXML
    private TableColumn<UpdateIncomeExpenses, Date> updateIncomeExpensesTableDate;
    @FXML
    private TableColumn<UpdateIncomeExpenses, Double> updateIncomeExpensesTableAmount;

    @FXML
    private TextField descriptionTxt;
    @FXML
    private ComboBox<String> typeTxt;
    @FXML
    private DatePicker dateTxt;
    @FXML
    private TextField amountTxt;


    @FXML
    private JFXButton insertIncomeExpensesBtn;
    @FXML
    private JFXButton updateHome;
    @FXML
    private Button updateClose;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXButton updateCancelBtn;
    @FXML
    private JFXButton updateLogoutBtn;


    //Create type selection list
    ObservableList<String> incomeExpensestypelist = FXCollections.observableArrayList("Income", "Expenditure");


    //declare variables
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    UpdateIncomeExpenses updateIncomeExpenses = null;
    private boolean update;
    private int incomeExpensesId;
    ObservableList<FinanceManagement.UpdateIncomeExpenses> list;


    //Initializes the controller class
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Initialize the type combobox
        typeTxt.setItems(incomeExpensestypelist);


        // TODO
        showUpdateIncomeExpenses();
    }

    //Get dashboard UI
    @FXML
    public void UpdateIncomeExpensesToHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage updateDashboard = (Stage) updateHome.getScene().getWindow();
            updateDashboard.setTitle("City of Gems");
            updateDashboard.setScene(new Scene(root, 993, 705));
            updateDashboard.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    //Log out
    @FXML
    public void UpdateIncomeExpensesLogoutAction(ActionEvent updateInEx1) throws IOException {
        updateLogoutBtn.getScene().getWindow().hide();

        Stage updatelogout = new Stage();
        Parent updateInExroot4 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene updateInExScene4 = new Scene(updateInExroot4);
        updatelogout.setScene(updateInExScene4);
        updatelogout.show();
        updatelogout.setResizable(false);
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

/*
    @FXML
    private void getAddView(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("incomeExpenses.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

*/

    @FXML
    private void refreshTable() {

        list.clear();
        ResultSet resultSet = null;
        connection = DBConnection.getConnection();
        String query = "SELECT * FROM `incomeandexpenses`";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new UpdateIncomeExpenses(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getString("type"),
                        resultSet.getDate("date"),
                        resultSet.getDouble("amount")));
                updateIncomeExpensesTable.setItems(list);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /*

    @FXML
    private void print(MouseEvent event) {
    }

    private void loadDate() {

        connection = DbConnect.getConnect();
        refreshTable();

*/


 /*
    //check connection
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cityofgems", "root", "Dasuni2020#");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error!" + ex.getMessage());
            return null;
        }
    }


     */


    //get income and expenses list
    public ObservableList<FinanceManagement.UpdateIncomeExpenses> getUpdateIncomeExpensesList() {
        ObservableList<FinanceManagement.UpdateIncomeExpenses> UpdateIncomeExpensesList = FXCollections.observableArrayList();
        //Connection conn = getConnection();
        FinanceManagement.DBConnection.getConnection();
        String query = "SELECT * FROM incomeandexpenses";
        Statement st;
        ResultSet rs;

        try {
            //st = conn.createStatement();
            st = FinanceManagement.DBConnection.getConnection().createStatement();
            rs = st.executeQuery(query);
            FinanceManagement.UpdateIncomeExpenses updateIncomeExpenses;

            while (rs.next()) {
                updateIncomeExpenses = new FinanceManagement.UpdateIncomeExpenses(rs.getInt("id"), rs.getString("description"), rs.getString("type"), rs.getDate("date"), rs.getDouble("amount"));
                UpdateIncomeExpensesList.add(updateIncomeExpenses);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return UpdateIncomeExpensesList;
    }

    //get income and expenses details
    public void showUpdateIncomeExpenses() {
        list = getUpdateIncomeExpensesList();

        //set values to the columns
        updateIncomeExpensesId.setCellValueFactory(new PropertyValueFactory<>("id"));
        updateIncomeExpensesTableDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        updateIncomeExpensesTableType.setCellValueFactory(new PropertyValueFactory<>("type"));
        updateIncomeExpensesTableDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        updateIncomeExpensesTableAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        updateIncomeExpensesTable.setItems(list);

    }


    //Cancel button method (Clear Fields method)
    @FXML
    private void UpdatencomeExpensesDetailsClear() {
        //Set all the form inputs to null
        descriptionTxt.setText(null);
        typeTxt.setValue(null);
        dateTxt.setValue(null);
        amountTxt.setText(null);
    }


    //query to update
    private void updateIncomeExpensesRecord() {

        String query = "UPDATE incomeandexpenses SET description= " + descriptionTxt.getText() + " , type = '" + typeTxt.getValue() + "',date = '" + dateTxt.getValue() + "',  amount = " + amountTxt.getText() + " WHERE id= " + incomeExpensesId;

    }


    private void executeUpdate() {
        //Connection conn = getConnection();
        try {
            Connection con = FinanceManagement.DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE incomeandexpenses SET description= ? , type = ?, date = ?, amount = ? WHERE id = ? "
            );

            ps.setString(1, descriptionTxt.getText().toString());
            ps.setString(2, typeTxt.getValue().toString());
            ps.setDate(3, java.sql.Date.valueOf(dateTxt.getValue()));
            ps.setDouble(4, Double.parseDouble(amountTxt.getText()));
            ps.setInt(5, incomeExpensesId);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void setTextField(int income_expenses_id, String desciption, String type, LocalDate toLocalDate, double amount) {
        incomeExpensesId = income_expenses_id;
        descriptionTxt.setText("" + desciption);
        typeTxt.setStyle("-fx-text-inner-color: #fff");
        typeTxt.setValue(type);
        dateTxt.setValue(toLocalDate);
        amountTxt.setText("" + amount);

    }

    public void setUpdate(boolean b) {
        this.update = b;
    }

/*
    // update button
    @FXML
    public void updateBtnIncomeExpensesOnAction(ActionEvent updateInEx2) {


        executeUpdate();


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        refreshTable();



        try {
            PauseTransition updateorderpt2 = new PauseTransition();
            updateorderpt2.setDuration(Duration.seconds(3));
            updateorderpt2.setOnFinished(ev -> {
                System.out.println("Order Update Successfully");
            });
            updateorderpt2.play();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }



*/

    // Update button method
    @FXML
    public void updateBtnIncomeExpensesOnAction(ActionEvent event){


        if(checkUpdateUiUnfilledFieldsValidation() && checkUpdateUiAmountFieldValidation() && checkUpdateUiAmountFieldZeroValidation() ) {
            executeUpdate();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("updateIncomeExpenses.fxml"));
                Stage saveInsert = (Stage) updateBtn.getScene().getWindow();
                saveInsert.setTitle("City of Gems");
                saveInsert.setScene(new Scene(root, 993, 705));
                saveInsert.show();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }


            try {
                //Alert Information box
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Record updated successfully");
                alert.show();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        }



    }




    // Form Validation methods

    // (1)check if any unfilled fields in income and expenses form
    @FXML
    private boolean checkUpdateUiUnfilledFieldsValidation(){

        if(descriptionTxt.getText().isEmpty() || typeTxt.getValue().isEmpty() || dateTxt.getEditor().getText().isEmpty() ||amountTxt.getText().isEmpty()){
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
    private boolean checkUpdateUiAmountFieldValidation(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m1 = p.matcher(amountTxt.getText());

        if((m1.find() && m1.group().equals(amountTxt.getText()))) {

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

    // (3)check if the amount field input number is greater than 0
    @FXML
    private boolean checkUpdateUiAmountFieldZeroValidation(){
        if(Double.parseDouble(amountTxt.getText()) >0 ){
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



}
