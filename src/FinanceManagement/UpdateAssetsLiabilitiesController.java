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

public class UpdateAssetsLiabilitiesController implements Initializable {

    //Table
    @FXML
    private TableView<UpdateAssetsLiabilities> updateAssetsLiabilitiesTable;
    @FXML
    private TableColumn<UpdateAssetsLiabilities, Integer> updateIncomeExpensesId;
    @FXML
    private TableColumn<UpdateAssetsLiabilities, String> updateAssetsLiabilitiesTableDescription;
    @FXML
    private TableColumn<UpdateAssetsLiabilities, String> updateAssetsLiabilitiesTableType;
    @FXML
    private TableColumn<UpdateAssetsLiabilities, Date> updateAssetsLiabilitiesTableDate;
    @FXML
    private TableColumn<UpdateAssetsLiabilities, Double> updateAssetsLiabilitiesTableAmount;


    @FXML
    private TextField assetsLiabilitiesDescriptionTxt;
    @FXML
    private ComboBox<String> assetsLiabilitiesTypeTxt;
    @FXML
    private DatePicker assetsLiabilitiesDateTxt;
    @FXML
    private TextField assetsLiabilitiesAmountTxt;


    @FXML
    private JFXButton insertAssetsLiabilitiesBtn;
    @FXML
    private JFXButton updateHome;
    @FXML
    private Button updateClose;
    @FXML
    private JFXButton assetsLiabilitiesUpdateBtn;
    @FXML
    private JFXButton updateAssetsLiabilitiesCancelBtn;
    @FXML
    private JFXButton updateLogoutBtn;


    //Create type selection list
    ObservableList<String> assetsLiabilitiestypelist = FXCollections.observableArrayList("Asset", "Liability", "Equity");


    //declare variables
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    UpdateAssetsLiabilities updateAssetsLiabilities = null;
    private boolean update;
    private int assetsLiabilitiesId;
    ObservableList<FinanceManagement.UpdateAssetsLiabilities> list;


    //Initializes the controller class
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Initialize the type combobox
        assetsLiabilitiesTypeTxt.setItems(assetsLiabilitiestypelist);


        // TODO
       // showUpdateAssetsLiabilities();
    }



    // Update button method
    @FXML
    public void updateBtnAssetsLiabilitiesOnAction(ActionEvent event){


        if(checkUpdateUiUnfilledFieldsValidation() && checkUpdateUiAmountFieldValidation() && checkUpdateUiAmountFieldZeroValidation() ) {
            executeUpdate();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("updateAssetsLiabilities.fxml"));
                Stage saveInsert = (Stage) assetsLiabilitiesUpdateBtn.getScene().getWindow();
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

        if(assetsLiabilitiesDescriptionTxt.getText().isEmpty() || assetsLiabilitiesTypeTxt.getValue().isEmpty() || assetsLiabilitiesDateTxt.getEditor().getText().isEmpty() ||assetsLiabilitiesAmountTxt.getText().isEmpty()){
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
        Matcher m1 = p.matcher(assetsLiabilitiesAmountTxt.getText());

        if((m1.find() && m1.group().equals(assetsLiabilitiesAmountTxt.getText()))) {

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
        if(Double.parseDouble(assetsLiabilitiesAmountTxt.getText()) >0 ){
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


    //get income and expenses list
    public ObservableList<FinanceManagement.UpdateAssetsLiabilities> getUpdateAssetsLiabilitiesList() {
        ObservableList<FinanceManagement.UpdateAssetsLiabilities> UpdateAssetsLiabilitiesList = FXCollections.observableArrayList();
        //Connection conn = getConnection();
        FinanceManagement.DBConnection.getConnection();
        String query = "SELECT * FROM assetsandliabilities";
        Statement st;
        ResultSet rs;

        try {
            //st = conn.createStatement();
            st = FinanceManagement.DBConnection.getConnection().createStatement();
            rs = st.executeQuery(query);
            FinanceManagement.UpdateAssetsLiabilities updateAssetsLiabilities;

            while (rs.next()) {
                updateAssetsLiabilities = new FinanceManagement.UpdateAssetsLiabilities(rs.getInt("id"), rs.getString("description"), rs.getString("type"), rs.getDate("date"), rs.getDouble("amount"));
                UpdateAssetsLiabilitiesList.add(updateAssetsLiabilities);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return UpdateAssetsLiabilitiesList;
    }


    // SQL query to update
    private void updateAssetsLiabilitiesRecord() {

        String query = "UPDATE assetsandliabilities SET assetsliabilities_description= " + assetsLiabilitiesDescriptionTxt.getText() + " , assetsliabilities_type = '" + assetsLiabilitiesTypeTxt.getValue() + "',assetsliabilities_date = '" + assetsLiabilitiesDateTxt.getValue() + "',  assetsliabilities_amount = " + assetsLiabilitiesAmountTxt.getText() + " WHERE id= " + assetsLiabilitiesId;
    }


    private void executeUpdate() {
        //Connection conn = getConnection();
        try {
            Connection con = FinanceManagement.DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE assetsandliabilities SET assetsliabilities_description= ? , assetsliabilities_type = ?, assetsliabilities_date = ?, assetsliabilities_amount = ? WHERE assetsliabilities_id = ? "
            );

            ps.setString(1, assetsLiabilitiesDescriptionTxt.getText().toString());
            ps.setString(2, assetsLiabilitiesTypeTxt.getValue().toString());
            ps.setDate(3, java.sql.Date.valueOf(assetsLiabilitiesDateTxt.getValue()));
            ps.setDouble(4, Double.parseDouble(assetsLiabilitiesAmountTxt.getText()));
            ps.setInt(5, assetsLiabilitiesId);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void setUpdate(boolean b) {
        this.update = b;
    }

    public void setTextField(int assets_liabilities_id, String desciption, String type, LocalDate toLocalDate, double amount) {
        assetsLiabilitiesId = assets_liabilities_id;
        assetsLiabilitiesDescriptionTxt.setText("" + desciption);
        assetsLiabilitiesTypeTxt.setStyle("-fx-text-inner-color: #fff");
        assetsLiabilitiesTypeTxt.setValue(type);
        assetsLiabilitiesDateTxt.setValue(toLocalDate);
        assetsLiabilitiesAmountTxt.setText("" + amount);

    }




    //refreshTable (Refresh the table view after update assets and liabilities details)
    @FXML
    private void refreshTable1() {

        list.clear();
        ResultSet resultSet = null;
        connection = DBConnection.getConnection();
        String query = "SELECT * FROM `assetsandliabilities`";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new UpdateAssetsLiabilities(
                        resultSet.getInt("assetsliabilities_id"),
                        resultSet.getString("assetsliabilities_description"),
                        resultSet.getString("assetsliabilities_type"),
                        resultSet.getDate("assetsliabilities_date"),
                        resultSet.getDouble("assetsliabilities_amount")));
                updateAssetsLiabilitiesTable.setItems(list);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    //Cancel button method (Clear Fields method)
    @FXML
    private void UpdateAssetsLiabilitiesDetailsClear() {
        //Set all the form inputs to null
        assetsLiabilitiesDescriptionTxt.setText(null);
        assetsLiabilitiesTypeTxt.setValue(null);
        assetsLiabilitiesDateTxt.setValue(null);
        assetsLiabilitiesAmountTxt.setText(null);
    }








}
