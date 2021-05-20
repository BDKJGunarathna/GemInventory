package supplierManagement;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ViewGemstoneOrderListController implements Initializable {

    //--------Variable Declarations--------------------------
    //logout/home buttons
    @FXML
    private JFXButton GOlogoutButton;
    @FXML
    private JFXButton GOhomeButton;

    //other buttons
    @FXML
    private JFXButton saveOrderButton;
    @FXML
    private JFXButton clearFieldButton;
    @FXML
    private JFXButton cancelOrderButton;
    @FXML
    private JFXButton deleteOrderButton;
    @FXML
    private JFXButton sendEmailButton;

    //Nav pane buttons
    @FXML
    private JFXButton goListNewPButton;
    @FXML
    private JFXButton goNewSupButton;
    @FXML
    private JFXButton goGemstoneOButton;
    @FXML
    private JFXButton goSupplierListButton;

    //tableView
    @FXML
    private TableView<ViewGemOrders> gOrderList;
    @FXML
    private TableColumn<ViewGemOrders, Integer> orderIDcol;
    @FXML
    private TableColumn<ViewGemOrders, Date> orderDateCol;
    @FXML
    private TableColumn<ViewGemOrders, Integer> supplierIdCol;
    @FXML
    private TableColumn<ViewGemOrders, Integer> gemIdCol;
    @FXML
    private TableColumn<ViewGemOrders, String> gDesCol;
    @FXML
    private TableColumn<ViewGemOrders, Double> quantityCol;
    @FXML
    private TableColumn<ViewGemOrders, String> suppNameCol;
    @FXML
    private TableColumn<ViewGemOrders, Date> deliveryDateCol;
    @FXML
    private TableColumn<ViewGemOrders, String> orderStatusCol;

    //Update textFields
    @FXML
    private TextField txtUpdateOid;
    @FXML
    private TextField txtUpdateGemDes;
    @FXML
    private TextField txtUpdateQuantity;
    @FXML
    private JFXDatePicker txtUpdateDdate;
    @FXML
    private TextField txtUpdateSid;
    @FXML
    private TextField txtUpdateSupname;
    @FXML
    private TextField txtUpdateGemId;

    @FXML
    private TextField GOSearchField; //search textField

    private ViewGemOrders viewGOrder; //object of viewGemOrder Class


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showOrderTable(); //method initialized to retrieve order data to table
        search(); // method initialized to apply search filters when loading the table

    }

    //-------------Scene transitions------------------------------
    //to go to dashboard/home
    @FXML
    public void GOhomeButtonOnAction(ActionEvent event) throws IOException {
        GOhomeButton.getScene().getWindow().hide();

        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("/supplierManagement/Dashboard.fxml"));
        Scene dashHome = new Scene(parent);
        stage.setScene(dashHome);
        stage.show();
        stage.setResizable(false);

    }

    //to load the same UI
    @FXML
    public void goGemstoneOButtonOnAction(ActionEvent event) throws IOException {
        goGemstoneOButton.getScene().getWindow().hide();

        Stage stageSS1 = new Stage();
        Parent pRoot1 = FXMLLoader.load(getClass().getResource("/supplierManagement/viewGemstoneOrderList.fxml"));
        Scene sceneSS1 = new Scene(pRoot1);
        stageSS1.setScene(sceneSS1);
        stageSS1.show();
        stageSS1.setResizable(false);

    }

    //to logout
    @FXML
    public void GOlogoutButtonOnAction(ActionEvent event) throws IOException {
        GOlogoutButton.getScene().getWindow().hide();

        Stage stageSS145 = new Stage();
        Parent pRoot155 = FXMLLoader.load(getClass().getResource("/supplierManagement/viewGemstoneOrderList.fxml"));
        Scene sceneSS133 = new Scene(pRoot155);
        stageSS145.setScene(sceneSS133);
        stageSS145.show();
        stageSS145.setResizable(false);
    }

    //to go to new purchase order window
    @FXML
    public void goLisNewPButtonOnAction(ActionEvent event) throws IOException {
        goListNewPButton.getScene().getWindow().hide();

        Stage stageSS11 = new Stage();
        Parent pRoot11 = FXMLLoader.load(getClass().getResource("/supplierManagement/newPurchaseOrder.fxml"));
        Scene sceneSS11 = new Scene(pRoot11);
        stageSS11.setScene(sceneSS11);
        stageSS11.show();
        stageSS11.setResizable(false);
    }

    //to go to new supplier window
    @FXML
    public void goNewSupButtonOnAction(ActionEvent event) throws IOException {
        goNewSupButton.getScene().getWindow().hide();

        Stage stageSS2 = new Stage();
        Parent pRoot112 = FXMLLoader.load(getClass().getResource("/supplierManagement/newSupplier.fxml"));
        Scene sceneSS112 = new Scene(pRoot112);
        stageSS2.setScene(sceneSS112);
        stageSS2.show();
        stageSS2.setResizable(false);
    }

    //to go to supplier list window
    @FXML
    public void goSupplierListButtonOnAction(ActionEvent event) throws IOException {
        goSupplierListButton.getScene().getWindow().hide();

        Stage stageSS22 = new Stage();
        Parent pRoot1123 = FXMLLoader.load(getClass().getResource("/supplierManagement/supplierList.fxml"));
        Scene sceneSS1121 = new Scene(pRoot1123);
        stageSS22.setScene(sceneSS1121);
        stageSS22.show();
        stageSS22.setResizable(false);
    }

    //-------------RETRIEVE------------------------------------------------------------------------------------------------------

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

    //get gemstones order details with result set
    public ObservableList<ViewGemOrders> getGemStoneOrderList() {
        ObservableList<ViewGemOrders> gemStoneOrderList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query1 = "SELECT p_OrderId, g_id, supp_id, g_description, p_ordered_date, g_quantity, expected_delivery_date,p_order_status,g_sup_name FROM cityofgems.purchase_order_table WHERE p_OrderId = p_OrderId";
        Statement st;
        ResultSet r;

        try {
            st = conn.createStatement();
            r = st.executeQuery(query1);
            ViewGemOrders orders;

            if (r != null) {
                while (r.next()) {
                    orders = new ViewGemOrders(r.getInt("p_OrderId"), r.getDate("p_ordered_date"),r.getInt("g_id"), r.getString("g_description"),r.getDouble("g_quantity"),r.getInt("supp_id"), r.getString("g_sup_name"),r.getDate("expected_delivery_date"), r.getString("p_order_status"));
                    gemStoneOrderList.add(orders);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return gemStoneOrderList;
    }

    //method to show gemstone Order details in tableView
    public void showOrderTable() {
        ObservableList<ViewGemOrders> list1 = getGemStoneOrderList();

        //set values to the columns
        orderIDcol.setCellValueFactory(new PropertyValueFactory<>("p_OrderId"));
        orderDateCol.setCellValueFactory(new PropertyValueFactory<>("p_ordered_date"));
        gemIdCol.setCellValueFactory(new PropertyValueFactory<>("g_id"));
        gDesCol.setCellValueFactory(new PropertyValueFactory<>("g_description"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("g_quantity"));
        supplierIdCol.setCellValueFactory(new PropertyValueFactory<>("supp_id"));
        suppNameCol.setCellValueFactory(new PropertyValueFactory<>("g_sup_name"));
        deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("expected_delivery_date"));
        orderStatusCol.setCellValueFactory(new PropertyValueFactory<>("p_order_status"));


        gOrderList.setItems(list1); //adding the items to the tableview
    }


    //action event for the delete button
    @FXML
    public void deleteOrderButtonOnAction(ActionEvent event) throws SQLException {
        Stage stage = (Stage) deleteOrderButton.getScene().getWindow();
        //gOrderList.getItems().removeAll(gOrderList.getSelectionModel().getSelectedItem());
        deleteOrderMethod();

    }

    //-----------------------DELETE----------------------------------------------------------------------------------------

    //method for the delete button with the delete query
    private void deleteOrderMethod() throws SQLException {
        ViewGemOrders list3 = gOrderList.getSelectionModel().getSelectedItem();
        String query = "DELETE FROM cityofgems.purchase_order_table WHERE p_OrderId =" + list3.getP_OrderId() + " AND p_order_status = 'Closed'";
        DBConnect.getConnection();
        Statement st = null;
        try {
            st = DBConnect.getConnection().createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        st.execute(query);
        if (list3.getP_order_status().equals("Closed")) { //newly added
            Notifications notificationBuilder = Notifications.create();
            notificationBuilder.title("Notification");
            notificationBuilder.text("Record deleted successfully");
            notificationBuilder.hideAfter(Duration.seconds(3));
            notificationBuilder.position(Pos.BOTTOM_CENTER);
            notificationBuilder.darkStyle();
            notificationBuilder.show();

            showOrderTable();
        }//newly added

    }


    //--------------------UPDATE-------------------------------------------------------------------------------------------------

    //event when save button is clicked
    public void saveOrderButtonOnAction(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(saveOrderButton)) {
            updatePurchaseOrderRecord();
        }
    }

    //String query for updating order table
    private void updatePurchaseOrderRecord() {
        String query = "UPDATE cityofgems.purchase_order_table SET g_id = '" + txtUpdateGemId.getText() + "', g_description = '" + txtUpdateGemDes.getText() + "', g_quantity = '" + txtUpdateQuantity.getText() + "', expected_delivery_date = '" + txtUpdateDdate.getValue() + "', supp_id = '" + txtUpdateSid.getText() + "', g_sup_name = '" + txtUpdateSupname.getText() + "' WHERE p_OrderId = " + txtUpdateOid.getText() + "";
        executeSql(query);
        showOrderTable();
    }

    //Updating data in database
       private void executeSql (String query){
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
        public void tableRowSelectionHandler (MouseEvent mouseEvent){
            //getting the selected row
            ViewGemOrders oList = gOrderList.getSelectionModel().getSelectedItem();

            //Assigning the textFields with the selected row's data
            txtUpdateGemId.setText("" + oList.getG_id());
            txtUpdateGemDes.setText("" + oList.getG_description());
            txtUpdateQuantity.setText("" + oList.getG_quantity());
            txtUpdateDdate.setValue(LocalDate.parse("" +oList.getExpected_delivery_date()));
            txtUpdateSid.setText("" + oList.getSupp_id());
            txtUpdateSupname.setText("" + oList.getG_sup_name());
            txtUpdateOid.setText("" + oList.getP_OrderId());

        }


        //event to clear the fields of update form
        public void clearFieldButtonOnAction (ActionEvent actionEvent) {
                if (actionEvent.getSource().equals(clearFieldButton)) {
                    clearTxtFields();
                }
            }

        //method for clearing fields
        private void clearTxtFields() {
        txtUpdateGemId.setText("");
        txtUpdateGemDes.setText("");
        txtUpdateQuantity.setText("");
        txtUpdateDdate.setValue(null);
        txtUpdateSid.setText("");
        txtUpdateSupname.setText("");
        txtUpdateOid.setText("");

        }

        //---------------SEARCH---------------------------------------------------------------------------------------------------

    //Search method
    private void search() {
        ObservableList<ViewGemOrders> orderList = getGemStoneOrderList();

        //wrap list in filtered list
        FilteredList<ViewGemOrders> filteredOrderList = new FilteredList<>(orderList, b -> true);

        GOSearchField.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredOrderList.setPredicate(viewGOrder ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String filterByLowerCase = newValue.toLowerCase();

                if(String.valueOf(viewGOrder.getG_description()).toLowerCase().indexOf(filterByLowerCase) != -1){ //check for gem description matches
                    return true;
                }else if(String.valueOf(viewGOrder.getG_sup_name()).toLowerCase().indexOf(filterByLowerCase) != -1){ //check for supplier name matches
                    return true;
                }else
                    return false;
            });
        });

        SortedList<ViewGemOrders> sortedDetails = new SortedList<>(filteredOrderList);

        sortedDetails.comparatorProperty().bind(gOrderList.comparatorProperty()); //merging comparator properties

        gOrderList.setItems(sortedDetails); //add sorted data to table

    }


















}

