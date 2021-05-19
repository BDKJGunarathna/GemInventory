//controller.java

package OrderManagement;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CreateOrderController implements Initializable {

    //products table
    @FXML
    private TableView<Product> tvProduct;
    @FXML
    private TableColumn<Product, Integer> colProID;
    @FXML
    private TableColumn<Product, String> colProName;
    @FXML
    private TableColumn<Product, String> colProType;
    @FXML
    private TableColumn<Product, Double> colProPrice;
    @FXML
    private TableColumn<Product, Integer> colProQty;

    //customer table
    @FXML
    private TableView<Customer> tvCustomer;
    @FXML
    private TableColumn<Customer, Integer> colCusId;
    @FXML
    private TableColumn<Customer, String> colCusFName;
    @FXML
    private TableColumn<Customer, String> colCusLName;
    @FXML
    private TableColumn<Customer, String> colCusTelephone;

    //text inputs
    @FXML
    private TextField tfCusID;
    @FXML
    private TextField tfProductId;
    @FXML
    private TextField tfInvoiceNo;
    @FXML
    private TextField tfProName;
    @FXML
    private TextField tfPrice;
    @FXML
    private TextField tfQuantity;
    @FXML
    private DatePicker dpOrderedDate;
    @FXML
    private TextField tfOrderStatus;


    @FXML
    private Button btnCancelOrders;
    @FXML
    private Button btnCreateOrders;
    @FXML
    private Button btnCreateOrderHome;
   


    @FXML
    private void setCusDetailsOnMouseClicked(ActionEvent event){
        Customer cus = tvCustomer.getSelectionModel().getSelectedItem();
        tfCusID.setText(""+cus.getCId());

    }

    @FXML
    private void setProDetailsOnMouseClicked(ActionEvent event){
        Product pro = tvProduct.getSelectionModel().getSelectedItem();
        tfProductId.setText(""+pro.getPId());
        tfProName.setText(pro.getPName());
        tfPrice.setText(""+pro.getPPrice());
    }

    // Form Validation methods
    //(1)check if any unfilled textfields in create order form
    private boolean checkUnfilledText(){
        if(tfInvoiceNo.getText().isEmpty()|| tfCusID.getText().isEmpty() || tfProductId.getText().isEmpty()
                ||tfProName.getText().isEmpty()||tfPrice.getText().isEmpty()||
                tfQuantity.getText().isEmpty()||tfOrderStatus.getText().isEmpty()|| dpOrderedDate.getEditor().getText().isEmpty()){

            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please Fill  All the text fields in the  create order form");
            alert.showAndWait();

            return false;
        }

        else{
            return true;
        }

    }

    // (2) Number input validation for cusId
    private boolean cusIdNumberInputValidation(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m1 = p.matcher(tfCusID.getText());

        if((m1.find() && m1.group().equals(tfCusID.getText()))   ){

            return true;
        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please enter only number to the text input CusID");
            alert.showAndWait();

            return false;
        }
    }


    private boolean quantityNumberInputValidation(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m2= p.matcher(tfQuantity.getText());

        if(m2.find() && m2.group().equals(tfQuantity.getText())){

            return true;
        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please enter only number to the text input for Quantity ");
            alert.showAndWait();

            return false;
        }
    }


    //(4) entered Quantity validation
    private boolean validateQuantity(){
        if(Integer.parseInt(tfQuantity.getText()) >0){
            return true;
        }

        else{

            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please enter quantity greater than 0");
            alert.showAndWait();

            return false;
        }
    }

    /*
    //product name input validations
    private boolean validateProductName(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(tfProName.getText());
        if(m.find() && m.group().equals(tfProName.getText())){
            return true;
        }

        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please enter only letters for ProdName text field ");
            alert.showAndWait();
            return false;
        }
    }

    */


    //switch to home
    @FXML
    public void CreateOrderToHome(ActionEvent event){
        try {

            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage homeUI = (Stage) btnCreateOrderHome.getScene().getWindow();
            homeUI.setTitle("Gem Merchant System");
            homeUI.setScene(new Scene(root, 993, 705));
            homeUI.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    @FXML
    public void cancelOrderOnAction(ActionEvent event){

        try {
            //switch to OrderHistory.fxml
            Parent root = FXMLLoader.load(getClass().getResource("OrderHistory.fxml"));
            Stage orderhistory = (Stage) btnCancelOrders.getScene().getWindow();
            orderhistory.setTitle("Gem Merchant System");
            orderhistory.setScene(new Scene(root, 1200, 700));
            orderhistory.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    @FXML
    public void createOrderOnAction(ActionEvent event){

        if(checkUnfilledText() && quantityNumberInputValidation() && cusIdNumberInputValidation() && validateQuantity()){
        insertOrder();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("OrderHistory.fxml"));
            Stage orderhistory = (Stage) btnCancelOrders.getScene().getWindow();
            orderhistory.setTitle("Gem Merchant System");
            orderhistory.setScene(new Scene(root, 1200, 700));
            orderhistory.show();

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        showProductsDetails();
        showCustomerDetails();
    }

    //check connection
   /* public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cityofgems", "root", "Gaya02");
            return conn;
        }
        catch(Exception ex){
            System.out.println("Error!"+ ex.getMessage());
            return null;
        }
    }*/

    //getProduct list method (2)
    public ObservableList<Product> getProductList(){
        ObservableList<Product> productList = FXCollections.observableArrayList();
       // Connection conn = getConnection();
        DBConnection.getConnection();
        String query = "SELECT P.id, P.name, P.type, P.price, P.quantity FROM jewelry P ";
        Statement st;
        ResultSet rs;

        try{
            //st = conn.createStatement();
            st = DBConnection.getConnection().createStatement();
            rs = st.executeQuery(query);
            Product prod;

            while(rs.next()){
                prod = new Product(rs.getInt("P.id"), rs.getString("P.name"), rs.getString("P.type"), rs.getDouble("P.price"), rs.getInt("P.quantity"));
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
        ObservableList<Product> list = getProductList();

        //set values to the columns
        colProID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("pId")); //property defined in product class
        colProName.setCellValueFactory(new PropertyValueFactory<Product, String>("pName"));
        colProType.setCellValueFactory(new PropertyValueFactory<Product, String>("pType"));
        colProPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("pPrice"));
        colProQty.setCellValueFactory(new PropertyValueFactory<Product, Integer>("pQty"));

        tvProduct.setItems(list);
    }


    // customer
    //get customer list method (2)
    public ObservableList<Customer> getCustomerList(){
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        //Connection conn = getConnection();
        DBConnection.getConnection();
        String query = "SELECT C.cusId, C.firstName, C.lastName,  C.contactNo FROM customers C ";
        Statement st;
        ResultSet rs;

        try{
            //st = conn.createStatement();
            st = DBConnection.getConnection().createStatement();
            rs = st.executeQuery(query);
            Customer cus;

            while(rs.next()){
                cus = new Customer(rs.getInt("C.cusId"), rs.getString("C.firstName"), rs.getString("C.lastName"), rs.getString("C.contactNo"));
                customerList.add(cus);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return customerList;
    }


    //get customer details (3)
    public void showCustomerDetails(){
        ObservableList<Customer> list = getCustomerList();

        //set values to the columns
        colCusId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("cId")); //property defined in product class
        colCusFName.setCellValueFactory(new PropertyValueFactory<Customer, String>("cFName"));
        colCusLName.setCellValueFactory(new PropertyValueFactory<Customer, String>("cLName"));
        colCusTelephone.setCellValueFactory(new PropertyValueFactory<Customer, String>("cTelephone"));

        tvCustomer.setItems(list);
    }


    //query to insert
    private void insertOrder(){
        String query = "INSERT into jewelry_orders VALUES ("+0+",'"+ tfInvoiceNo.getText() +"',"+ tfCusID.getText() +", '"+ tfProductId.getText() +"','"+ tfProName.getText() +"',"+ tfPrice.getText() +","+ tfQuantity.getText()+","+ tfPrice.getText()+"*"+tfQuantity.getText()+",'" +dpOrderedDate.getValue()+"','"+tfOrderStatus.getText()+"')";
        executeUpdate(query);
    }

    private void executeUpdate(String query) {
        //Connection conn = getConnection();
        DBConnection.getConnection();
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


}


