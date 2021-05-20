package supplierManagement;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;



public class SupplierListController implements Initializable {

    //--------------variable declaration-------------------------------------------
    //table View
    @FXML
    private TableView <SupplierListTableView> supplierTableView;
    @FXML
    private TableColumn <SupplierListTableView, Integer> sidcol;
    @FXML
    private TableColumn <SupplierListTableView,String> snamecol;
    @FXML
    private TableColumn <SupplierListTableView,String> addresscol;
    @FXML
    private TableColumn <SupplierListTableView,String> phonecol;
    @FXML
    private TableColumn <SupplierListTableView,String> emailcol;
    @FXML
    private TableColumn <SupplierListTableView, String> websitecol;
    @FXML
    private TableColumn <SupplierListTableView,String> suplygemtypecol;
    @FXML
    private TableColumn <SupplierListTableView,String> countrycol;
    @FXML
    private TableColumn <SupplierListTableView,String> descripCol;

    //nav pane buttons with home and logout
    @FXML
    private Button supplierListhomebtn;
    @FXML
    private Button supplierListSuplistbtnInNav;
    @FXML
    private Button supplierListLogout;
    @FXML
    private Button removeSupButton;
    @FXML
    private Button supplierListPurchaseorderbtn;
    @FXML
    private Button supplierListnewsupplierbtn;
    @FXML
    private Button supplierListgemstonebtn;


    //textFields for Update
    @FXML
    private TextField txtUpdateSName;
    @FXML
    private TextField txtUpdateSEmail;
    @FXML
    private TextField txtUpdateSAddress;
    @FXML
    private TextField txtUpdateSPhone;
    @FXML
    private TextField txtUpdateSCountry;
    @FXML
    private TextField txtUpdateSWebsite;
    @FXML
    private TextArea txtUpdateSsupGtypes;
    @FXML
    private TextArea txtUpdateSDescription;
    @FXML
    private TextField txtUpdateSupId;

    //buttons
    @FXML
    private Button save;
    @FXML
    private Button clear;

    @FXML
    private TextField supplierSearchField; //search textField

    private SupplierListTableView supView; //object of viewGemOrder Class



    @Override
    public void initialize(URL url, ResourceBundle rb){
        showSupplierList(); //method initialized to show data in supplier list
        SearchField(); // method to load filtered data
    }


    //----------------------scene transitions-----------------------------------
    //to go to new purchase order
    @FXML
    public void supListToNewPurchaseAction(ActionEvent event) throws IOException {
        supplierListPurchaseorderbtn.getScene().getWindow().hide();

        Stage StageNewP = new Stage();
        Parent PRoot = FXMLLoader.load(getClass().getResource("/supplierManagement/newPurchaseOrder.fxml"));
        Scene sceneNp = new Scene(PRoot);
        StageNewP.setScene(sceneNp);
        StageNewP.show();
        StageNewP.setResizable(false);
    }

    //to go to new supplier window
    @FXML
    public void supListToNewSupplierAction(ActionEvent event) throws IOException {
        supplierListnewsupplierbtn.getScene().getWindow().hide();

        Stage StageSupp = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/supplierManagement/newSupplier.fxml"));
        Scene sceneSup = new Scene(root);
        StageSupp.setScene(sceneSup);
        StageSupp.show();
        StageSupp.setResizable(false);
    }

    //method for logout
    @FXML
    public void logOutOfSupList(ActionEvent purchordev1) throws IOException {
        supplierListLogout.getScene().getWindow().hide();

        Stage logoutpurchord = new Stage();
        Parent purchordroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene purchordscene2 = new Scene(purchordroot2);
        logoutpurchord.setScene(purchordscene2);
        logoutpurchord.show();
        logoutpurchord.setResizable(false);
    }

    //to load own window
    @FXML
    public void supplierListNavPaneAction(ActionEvent event) throws IOException {
        supplierListSuplistbtnInNav.getScene().getWindow().hide();

        Stage SupListBtn = new Stage();
        Parent SuppListRoot = FXMLLoader.load(getClass().getResource("/supplierManagement/supplierList.fxml"));
        Scene SupListScene = new Scene(SuppListRoot);
        SupListBtn.setScene(SupListScene);
        SupListBtn.show();
        SupListBtn.setResizable(false);
    }

    //to go to gemstone order list window
    @FXML
    public void supplierListTogemstoneList(ActionEvent event) throws IOException {
        supplierListgemstonebtn.getScene().getWindow().hide();

        Stage stageGL = new Stage();
        Parent parentGL = FXMLLoader.load(getClass().getResource("/supplierManagement/viewGemstoneOrderList.fxml"));
        Scene gemstoneList = new Scene(parentGL);
        stageGL.setScene(gemstoneList);
        stageGL.show();
        stageGL.setResizable(false);
    }

    //to go to gemstone order list window
    @FXML
    public void supListTOdashboard(ActionEvent event) throws IOException {
        supplierListhomebtn.getScene().getWindow().hide();

        Stage stageDB = new Stage();
        Parent parentDB = FXMLLoader.load(getClass().getResource("/supplierManagement/Dashboard.fxml"));
        Scene dashB = new Scene(parentDB);
        stageDB.setScene(dashB);
        stageDB.show();
        stageDB.setResizable(false);
    }

    //-------------------------------DELETE-------------------------------------------------------------------------------------------
    //event for delete remove button
    @FXML
    public void removeSupplierOnAction(ActionEvent event) throws SQLException {
        Stage stage =(Stage) removeSupButton.getScene().getWindow();
        //supplierTableView.getItems().removeAll(supplierTableView.getSelectionModel().getSelectedItem());
        removeSupFromDb(); //method calling to delete data
    }

    //sql query to delete data from database
    private void removeSupFromDb() throws SQLException {

            SupplierListTableView list = supplierTableView.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM cityofgems.supplier_info_table WHERE supplierID =" + list.getSupplierID();
            DBConnect.getConnection();
            Statement st = DBConnect.getConnection().createStatement();
            st.execute(query);
            showSupplierList();
    }


    //check database connection
            public Connection getConnection(){
                Connection con;
                try{
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cityofgems", "root", "DBPassword");
                    return con;
                }
                catch(Exception ex){
                    System.out.println("Error!"+ ex.getMessage());
                    return null;
                }
            }

    //--------------------------RETRIEVE-------------------------------------------------------------------------------------------------------------
    //array list method to get order list
    public ObservableList<SupplierListTableView> getSupplierList(){
        ObservableList<SupplierListTableView> SupplierList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM cityofgems.supplier_info_table"; //sql query to retrieve data from database
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            SupplierListTableView list;

            while(rs.next()){
                list = new SupplierListTableView(rs.getInt("supplierID"), rs.getString("s_name"), rs.getString("s_phone"), rs.getString("s_email"), rs.getString("s_website"), rs.getString("s_description"), rs.getString("s_address"), rs.getString("supp_gemstoneTypes"), rs.getString("s_country"));
                SupplierList.add(list);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return SupplierList;
    }

    //method to show the retrieved data in table
    private void showSupplierList() {

        ObservableList<SupplierListTableView> list = getSupplierList();
        //get values to columns
        sidcol.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        snamecol.setCellValueFactory(new PropertyValueFactory<>("s_name"));
        phonecol.setCellValueFactory(new PropertyValueFactory<>("s_phone"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("s_email"));
        websitecol.setCellValueFactory(new PropertyValueFactory<>("s_website"));
        addresscol.setCellValueFactory(new PropertyValueFactory<>("s_address"));
        suplygemtypecol.setCellValueFactory(new PropertyValueFactory<>("supp_gemstoneTypes"));
        countrycol.setCellValueFactory(new PropertyValueFactory<>("s_country"));
        descripCol.setCellValueFactory(new PropertyValueFactory<>("s_description"));

        supplierTableView.setItems(list); //adding items to table
    }


    //------------------------UPDATE-------------------------------------------------------------------------------------------------------------------------
    //event to be handled when save button is clicked
    @FXML
    public void saveButtonOnAction(ActionEvent event){
        if(event.getSource().equals(save)){
            updateSupplierRecord();

        }
    }

    //event to be handled when clear button is clicked
    public void clearButtonOnAction(ActionEvent actionEvent) {
                if(actionEvent.getSource().equals(clear)){
                    clearFields();
                }
    }

    //method to clear fields of the update form
    private void clearFields() {

        txtUpdateSName.setText("");
        txtUpdateSAddress.setText("");
        txtUpdateSPhone.setText("");
        txtUpdateSCountry.setText("");
        txtUpdateSWebsite.setText("");
        txtUpdateSsupGtypes.setText("");
        txtUpdateSDescription.setText("");
        txtUpdateSupId.setText("");
        txtUpdateSEmail.setText("");
    }

    //Sql query to update data in database
    private void updateSupplierRecord() {
                String query = "UPDATE cityofgems.supplier_info_table SET s_name = '"+txtUpdateSName.getText()+"', s_address = '" +txtUpdateSAddress.getText()+"', s_phone = '"+txtUpdateSPhone.getText()+"', s_country = '"+txtUpdateSCountry.getText()+"', s_email = '"+txtUpdateSEmail.getText()+"', s_website = '"+txtUpdateSWebsite.getText()+"' ,s_description = '"+txtUpdateSDescription.getText()+"', supp_gemstoneTypes = '"+txtUpdateSsupGtypes.getText()+"' WHERE supplierID = "+txtUpdateSupId.getText()+"";
                executeQuery(query);
                showSupplierList();
    }

    //Updating data in database
    private void executeQuery (String query){
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
        //Mouse clicked event to get the clicked row and load data into the fields
        @FXML
        public void rowSelectionHandleEvent(MouseEvent mouseEvent) {
            //getting the selected row
            SupplierListTableView supList = supplierTableView.getSelectionModel().getSelectedItem();

            //Assigning the textFields with the selected row's data
            txtUpdateSupId.setText("" +supList.getSupplierID());
            txtUpdateSName.setText("" +supList.getS_name());
            txtUpdateSEmail.setText("" +supList.getS_email());
            txtUpdateSAddress.setText("" +supList.getS_address());
            txtUpdateSPhone.setText("" +supList.getS_phone());
            txtUpdateSCountry.setText("" +supList.getS_country());
            txtUpdateSWebsite.setText("" +supList.getS_website());
            txtUpdateSsupGtypes.setText("" +supList.getSupp_gemstoneTypes());
            txtUpdateSDescription.setText("" +supList.getS_description());
        }

        ///-----------------------------SEARCH-------------------------------------------------------------------------------------------------//
        private void SearchField() {
            ObservableList<SupplierListTableView> supplierList = getSupplierList();

            //wrap list in filtered list
            FilteredList<SupplierListTableView> filteredSupplierList = new FilteredList<>(supplierList, b -> true);

            supplierSearchField.textProperty().addListener((observable,oldVal,newVal) -> {
                filteredSupplierList.setPredicate(supView -> {
                    if (newVal == null || newVal.isEmpty()) {
                        return true;
                    }

                    String filterDetailsByLowerCase = newVal.toLowerCase();

                    if (String.valueOf(supView.getS_name()).toLowerCase().indexOf(filterDetailsByLowerCase) != -1) { //check if supplier name matches
                        return true;
                    } else if (String.valueOf(supView.getS_country()).toLowerCase().indexOf(filterDetailsByLowerCase) != -1) { //check if country matches
                        return true;
                    } else if (String.valueOf(supView.getSupp_gemstoneTypes()).toLowerCase().indexOf(filterDetailsByLowerCase) != -1){ //check for gemstone types
                        return true;
                    }else
                        return false;
                });
            });

            SortedList<SupplierListTableView> sortedSupplierDetails = new SortedList<>(filteredSupplierList);

            sortedSupplierDetails.comparatorProperty().bind(supplierTableView.comparatorProperty()); //merging comparator properties

            supplierTableView.setItems(sortedSupplierDetails); //add sorted data to table
        }

}