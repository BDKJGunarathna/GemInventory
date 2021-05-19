package supplierManagement;

import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ViewGemstoneOrderListController implements Initializable {


    @FXML
    private FontAwesomeIconView RefreshButton;
    @FXML
    private JFXButton GOlogoutButton;
    @FXML
    private JFXButton GOhomeButton;


    @FXML
    private JFXButton cancelOrderButton;
    @FXML
    private JFXButton editOrderButton;
    @FXML
    private JFXButton deleteOrderButton;
    @FXML
    private JFXButton sendEmailButton;

    @FXML
    private JFXButton goListNewPButton;
    @FXML
    private JFXButton goNewSupButton;
    @FXML
    private JFXButton goGemstoneOButton;
    @FXML
    private JFXButton goSupplierListButton;


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showOrderTable();

    }


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

    //get gemstones order details
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

            if(r != null){
            while (r.next()) {
                orders = new ViewGemOrders(r.getInt("p_OrderId"), r.getDate("p_ordered_date"), r.getInt("supp_id"), r.getInt("g_id"),r.getString("g_description"),r.getDouble("g_quantity"),r.getString("g_sup_name"),r.getDate("expected_delivery_date"),r.getString("p_order_status"));
                gemStoneOrderList.add(orders);
            }}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return gemStoneOrderList;
    }

    //method to show gemstone Order details in tableView
    public void showOrderTable(){
        ObservableList<ViewGemOrders> list1 = getGemStoneOrderList();

        //set values to the columns
        orderIDcol.setCellValueFactory(new PropertyValueFactory<>("p_OrderId"));
        orderDateCol.setCellValueFactory(new PropertyValueFactory<>("p_ordered_date"));
        supplierIdCol.setCellValueFactory(new PropertyValueFactory<>("supp_id"));
        gemIdCol.setCellValueFactory(new PropertyValueFactory<>("g_id"));
        gDesCol.setCellValueFactory(new PropertyValueFactory<>("g_description"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("g_quantity"));
        suppNameCol.setCellValueFactory(new PropertyValueFactory<>("g_sup_name"));
        deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("expected_delivery_date"));
        orderStatusCol.setCellValueFactory(new PropertyValueFactory<>("p_order_status"));


        gOrderList.setItems(list1);
    }


    @FXML
    public void deleteOrderButtonOnAction(ActionEvent event) throws SQLException {
        Stage stage =(Stage) deleteOrderButton.getScene().getWindow();
        //gOrderList.getItems().removeAll(gOrderList.getSelectionModel().getSelectedItem());
        deleteOrderMethod();

    }

    private void deleteOrderMethod() throws SQLException {
        ViewGemOrders list3 = gOrderList.getSelectionModel().getSelectedItem();
        String query = "DELETE FROM cityofgems.purchase_order_table WHERE p_OrderId =" + list3.getP_OrderId() +" AND p_order_status = 'Closed'";
        DBConnect.getConnection();
        Statement st = null;
        try {
            st = DBConnect.getConnection().createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        st.execute(query);
        if(list3.getP_order_status().equals("Closed")) { //newly added
            Notifications notificationBuilder = Notifications.create();
            notificationBuilder.title("Notification");
            notificationBuilder.text("Deleted record successfully");
            notificationBuilder.hideAfter(Duration.seconds(3));
            notificationBuilder.position(Pos.BOTTOM_CENTER);
            notificationBuilder.darkStyle();
            notificationBuilder.show();

            showOrderTable();
        }//newly added

    }













}
