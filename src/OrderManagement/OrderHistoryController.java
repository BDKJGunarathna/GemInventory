package OrderManagement;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OrderHistoryController implements Initializable {

    @FXML
    private TableView<OrderManagement.Order> tvOrder;
    @FXML
    private TableColumn<OrderManagement.Order, Integer> col_orderID;
    @FXML
    private TableColumn<OrderManagement.Order,Integer> col_invoiceNo;
    @FXML
    private TableColumn<OrderManagement.Order,Integer> col_cusId;
    @FXML
    private TableColumn<OrderManagement.Order,String> col_proId;
    @FXML
    private TableColumn<OrderManagement.Order,String> col_proName;
    @FXML
    private TableColumn<OrderManagement.Order, Float> col_unPrice;
    @FXML
    private TableColumn<OrderManagement.Order,Integer> col_qty;
    @FXML
    private TableColumn<OrderManagement.Order,Float> col_total;
    @FXML
    private TableColumn<OrderManagement.Order, String> col_ordereddate;
    @FXML
    private TableColumn<OrderManagement.Order,String> col_status;
    @FXML
    private TableColumn<OrderManagement.Order,String> col_action;
    @FXML
    private Button btnOrderHistoryHome;
    @FXML
    private Button addOrderBtnHistory;



    @Override
    public void initialize(URL url, ResourceBundle rb){

        OrderHistory();
    }
/*
    @FXML
    private void getAddView(MouseEvent event){
        try{
            //get CreateOrder.fxml
            Parent parent = FXMLLoader.load(getClass().getResource("CreateOrder.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
*/

    //get addOrder UI
    @FXML
    public void OrderCreation(ActionEvent event){
        try {

            Parent root = FXMLLoader.load(getClass().getResource("CreateOrder.fxml"));
            Stage homeUI = (Stage) addOrderBtnHistory.getScene().getWindow();
            homeUI.setTitle("Gem Merchant System");
            homeUI.setScene(new Scene(root, 1050, 650));
            homeUI.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    //switch to home
    @FXML
    public void OrderHistoryToHome(ActionEvent event){
        try {
            //switch to OrderHistory.fxml
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage homeUI = (Stage) btnOrderHistoryHome.getScene().getWindow();
            homeUI.setTitle("Gem Merchant System");
            homeUI.setScene(new Scene(root, 993, 705));
            homeUI.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    //check connection
    /*public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cityofgems", "root", "Gaya02");
            return conn;
        }
        catch(Exception ex){
            System.out.println("Error!"+ ex.getMessage());
            return null;
        }
    }

     */

    //get order list method (2)
    public ObservableList<OrderManagement.Order> getOrderList(){
        ObservableList<OrderManagement.Order> orderList = FXCollections.observableArrayList();
        //Connection conn = getConnection();
        OrderManagement.DBConnection.getConnection();
        String query = "SELECT * FROM jewelry_orders ";
        Statement st;
        ResultSet rs;

        try{
            //st = conn.createStatement();
            st = OrderManagement.DBConnection.getConnection().createStatement();
            rs = st.executeQuery(query);
            OrderManagement.Order order;

            while(rs.next()){
                order = new OrderManagement.Order(rs.getInt("Id"), rs.getString("invoiceNo"), rs.getInt("cusid"), rs.getInt("proid"), rs.getString("proname"),  rs.getDouble("unprice"), rs.getInt("qty"), rs.getDouble("total"), rs.getDate("date"), rs.getString("status"));
                orderList.add(order);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return orderList;
    }



    private void OrderHistory() {
        ObservableList<OrderManagement.Order> list = getOrderList();

        col_orderID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_invoiceNo.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        col_cusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        col_proId.setCellValueFactory(new PropertyValueFactory<>("proId"));
        col_proName.setCellValueFactory(new PropertyValueFactory<>("proName"));
        col_unPrice.setCellValueFactory(new PropertyValueFactory<>("unPrice"));
        col_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        col_ordereddate.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        //add cell of button edit
        Callback<TableColumn<OrderManagement.Order, String>, TableCell<OrderManagement.Order, String>> cellFoctory = (TableColumn<OrderManagement.Order, String> param) -> {
            // make cell containing buttons
            final TableCell<OrderManagement.Order, String> cell = new TableCell<OrderManagement.Order, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                OrderManagement.Order order = tvOrder.getSelectionModel().getSelectedItem();
                                String query = "DELETE FROM jewelry_orders WHERE id  =" + order.getId()+" and status != 'complete'";
                                //Connection conn = getConnection();
                                OrderManagement.DBConnection.getConnection();
                                //Statement st = conn.createStatement();
                                Statement st = DBConnection.getConnection().createStatement();
                                st.execute(query);
                                OrderHistory();
                            } catch (SQLException ex) {
                                Logger.getLogger(OrderHistoryController.class.getName()).log(Level.SEVERE, null, ex);
                            }


                        });


                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            Order order = tvOrder.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("updateorder.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(OrderHistoryController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            UpdateorderController updateorderController = loader.getController();
                            updateorderController.setUpdate(true);
                            updateorderController.setTextField(order.getId(), order.getProId(),order.getProName(),order.getUnPrice(),order.getQty(), order.getStatus());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();




                        });



                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        col_action.setCellFactory(cellFoctory);

        tvOrder.setItems(list);


            }
        }