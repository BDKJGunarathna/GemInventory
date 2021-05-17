package OrderManagement;

import com.jfoenix.controls.JFXButton;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UpdateorderController implements Initializable{


    //products table
    @FXML
    private TableView<OrderManagement.Product> tvProduct;
    @FXML
    private TableColumn<OrderManagement.Product, Integer> colProID;
    @FXML
    private TableColumn<OrderManagement.Product, String> colProName;
    @FXML
    private TableColumn<OrderManagement.Product, String> colProType;
    @FXML
    private TableColumn<OrderManagement.Product, Double> colProPrice;
    @FXML
    private TableColumn<OrderManagement.Product, Integer> colProQty;



    @FXML
        private JFXButton updateorderhomebtn;

        @FXML
        private JFXButton updateorderlogoutbtn;

       /* @FXML
        private TextField updateordercustid;

        @FXML
        private TextField updateordercustname;

        @FXML
        private TextField updateorderordid;

        @FXML
        private TextField updateorderinvoid;
*/
        @FXML
        private TextField updateorderprodid;

        @FXML
        private TextField updateorderproName;

        @FXML
        private TextField updateorderprice;

        @FXML
        private TextField updateorderqty;

        //@FXML
       // private JFXDatePicker updateorderdate;

        @FXML
        private TextField updateOrderStatus;

        @FXML
        private JFXButton updateorderupdatebtn;

        @FXML
        private JFXButton updateordercancelbtn;


    //declare variables
    private int orderID;
    private boolean update;
    Order order;


    //switch to home
    @FXML
    public void OrderHistoryToHome(ActionEvent event){
        try {

            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage homeUI = (Stage) updateorderhomebtn.getScene().getWindow();
            homeUI.setTitle("Gem Merchant System");
            homeUI.setScene(new Scene(root, 993, 705));
            homeUI.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


    }


        @Override
        public void initialize(URL location, ResourceBundle resources) {

                showProductsDetails();

        }

        @FXML
        public void updateorderlogoutAction(ActionEvent updateordev1) throws IOException {
            updateorderlogoutbtn.getScene().getWindow().hide();

            Stage updateordlogout = new Stage();
            Parent updateordroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
            Scene updateordscene2 = new Scene(updateordroot2);
            updateordlogout.setScene(updateordscene2);
            updateordlogout.show();
            updateordlogout.setResizable(false);
        }


    //getProduct list method (2)
    public ObservableList<OrderManagement.Product> getProductList(){
        ObservableList<OrderManagement.Product> productList = FXCollections.observableArrayList();
        // Connection conn = getConnection();
        OrderManagement.DBConnection.getConnection();
        String query = "SELECT P.id, P.name, P.type, P.price, P.quantity FROM jewelry P ";
        Statement st;
        ResultSet rs;

        try{
            //st = conn.createStatement();
            st = OrderManagement.DBConnection.getConnection().createStatement();
            rs = st.executeQuery(query);
            OrderManagement.Product prod;

            while(rs.next()){
                prod = new OrderManagement.Product(rs.getInt("P.id"), rs.getString("P.name"), rs.getString("P.type"), rs.getDouble("P.price"), rs.getInt("P.quantity"));
                productList.add(prod);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return productList;
    }

    //get Product details (3)
    public void showProductsDetails(){
        ObservableList<OrderManagement.Product> list = getProductList();

        //set values to the columns
        colProID.setCellValueFactory(new PropertyValueFactory<OrderManagement.Product, Integer>("pId")); //property defined in product class
        colProName.setCellValueFactory(new PropertyValueFactory<OrderManagement.Product, String>("pName"));
        colProType.setCellValueFactory(new PropertyValueFactory<OrderManagement.Product, String>("pType"));
        colProPrice.setCellValueFactory(new PropertyValueFactory<OrderManagement.Product, Double>("pPrice"));
        colProQty.setCellValueFactory(new PropertyValueFactory<Product, Integer>("pQty"));

        tvProduct.setItems(list);
    }


    @FXML
        public void updateorderAction(ActionEvent updateordev2) {

        updateRecord();

        /*try {
            PauseTransition updateorderpt2 = new PauseTransition();
            updateorderpt2.setDuration(Duration.seconds(3));
            updateorderpt2.setOnFinished(ev -> {
                System.out.println("Order Update Successfully");
            });
            updateorderpt2.play();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }*/

        try {
            //switch to OrderHistory.fxml
            Parent root = FXMLLoader.load(getClass().getResource("OrderHistory.fxml"));
            Stage orderhistory = (Stage) updateorderupdatebtn.getScene().getWindow();
            orderhistory.setTitle("Gem Merchant System");
            orderhistory.setScene(new Scene(root, 1200, 700));
            orderhistory.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }




       }

    //query to update
    private void updateRecord(){

        String query = "UPDATE jewelry_orders SET proid= "+updateorderprodid.getText()+" , proname = '"+updateorderproName.getText()+"',unprice = "+updateorderprice.getText()+",  qty = "+updateorderqty.getText()+" ,total = "+ updateorderprice.getText()+" * "+updateorderqty.getText()+", status = '"+updateOrderStatus.getText()+"'  WHERE id= " + orderID+"  and status != 'complete' " ;
        executeUpdate(query);
    }



    private void executeUpdate(String query) {
        //Connection conn = getConnection();
        OrderManagement.DBConnection.getConnection();
        Statement st;

        try{
            //st = conn.createStatement();
            st = DBConnection.getConnection().createStatement();
            st.executeUpdate(query);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void setUpdate(boolean b) {
        this.update = b;
    }

    public void setTextField(int order_id,int pro_id, String pro_name, double un_price, int quantity,  String stat) {
        orderID = order_id;
        updateorderprodid.setText(""+pro_id);
        updateorderproName.setText(pro_name);
        updateorderprice.setText(""+un_price);
        updateorderqty.setText(""+quantity);
        updateOrderStatus.setText(stat);

    }
}


