package OrderManagement;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;


import java.io.FileOutputStream;
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
    @FXML
    private TextField txtSearchJewelryOrder;


    private Order order = null;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        OrderHistory();
        searchOrderDetails();
    }




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

 //print method
    @FXML
    private void print(ActionEvent event){


        try {

            String order_report_name = "C:\\Users\\Dinuka\\IdeaProjects\\Order_Management\\src\\OrderManagement\\Order_Details_of_current_month.pdf";
            //create Document object
            Document orderDocument = new Document();
            //set pdf instance
            PdfWriter.getInstance(orderDocument, new FileOutputStream(order_report_name));
            //open the document
            orderDocument.open();

            Font bold = new Font(Font.FontFamily.HELVETICA, 28, Font.BOLD);
            Paragraph p = new Paragraph("***********************Jewelry_Order_Details_Of_The_Current_Month********************");
            orderDocument.add(p);

            orderDocument.add(new Paragraph("  "));
            orderDocument.add(new Paragraph("  "));
            orderDocument.add(new Paragraph("  "));
            orderDocument.add(new Paragraph("  "));



            OrderManagement.DBConnection.getConnection();
            String query = "SELECT * FROM jewelry_orders where YEAR(date) = YEAR(CURRENT_DATE ) and MONTH(date) = MONTH(CURRENT_DATE )";
            Statement st;
            ResultSet rs;


            st = OrderManagement.DBConnection.getConnection().createStatement();
            rs = st.executeQuery(query);
            OrderManagement.Order order;


                st = OrderManagement.DBConnection.getConnection().createStatement();
                rs = st.executeQuery(query);

                if (rs != null) {
                    PdfPTable order_table = null;
                    while (rs.next()) {
                        order_table = new PdfPTable(10);


                        PdfPCell order_cell = new PdfPCell(new Phrase("Order ID"));
                        order_table.addCell(order_cell);
                        order_cell = new PdfPCell(new Phrase("Invoice No"));
                        order_table.addCell(order_cell);
                        order_cell = new PdfPCell(new Phrase("Cus ID"));
                        order_table.addCell(order_cell);
                        order_cell = new PdfPCell(new Phrase("Pro ID"));
                        order_table.addCell(order_cell);
                        order_cell = new PdfPCell(new Phrase("Pro Name"));
                        order_table.addCell(order_cell);
                        order_cell = new PdfPCell(new Phrase("un Price "));
                        order_table.addCell(order_cell);
                        order_cell = new PdfPCell(new Phrase("Quantity"));
                        order_table.addCell(order_cell);
                        order_cell = new PdfPCell(new Phrase("Total"));
                        order_table.addCell(order_cell);
                        order_cell = new PdfPCell(new Phrase("Ordered Date"));
                        order_table.addCell(order_cell);
                        order_cell = new PdfPCell(new Phrase("Status"));
                        order_table.addCell(order_cell);
                        order_table.setHeaderRows(1);

                        order_table.addCell(String.valueOf(rs.getInt("id")));
                        order_table.addCell(String.valueOf(rs.getString("invoiceNo")));
                        order_table.addCell(String.valueOf(rs.getInt("cusid")));
                        order_table.addCell(String.valueOf(rs.getInt("proid")));
                        order_table.addCell(String.valueOf(rs.getString("proname")));
                        order_table.addCell(String.valueOf(rs.getDouble("unprice")));
                        order_table.addCell(String.valueOf(rs.getInt("qty")));
                        order_table.addCell(String.valueOf(rs.getDouble("total")));
                        order_table.addCell(String.valueOf(rs.getDate("date")));
                        order_table.addCell(String.valueOf(rs.getString("status")));
                        orderDocument.add(order_table);
                    }
                }


            orderDocument.close();


            //Alert Information box
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("PDF Downloaded Successfully ");
            alert.show();

        }

        catch(Exception ex){
                ex.printStackTrace();
        }

    }





    //Search Method
    @FXML
    private void searchOrderDetails() {
        ObservableList<OrderManagement.Order> list = getOrderList();


        FilteredList<Order> filteredOrderDetails = new FilteredList<>(list, b -> true);


        txtSearchJewelryOrder.textProperty().addListener((ordObservable, OFValue, ONValue) -> {
            filteredOrderDetails.setPredicate(order -> {

                if (ONValue == null || ONValue.isEmpty()) {
                    return true;
                }


                String lowerValueFilter = ONValue.toLowerCase();

                if (String.valueOf(order.getId()).toLowerCase().indexOf(lowerValueFilter) != -1) {
                    return true;
                }
                else if (String.valueOf(order.getCusId()).toLowerCase().indexOf(lowerValueFilter) != -1) {
                    return true;

                }
               else
                    return false;
            });
        });


        SortedList<Order> sortedOrderDetails = new SortedList<>(filteredOrderDetails);


        sortedOrderDetails.comparatorProperty().bind(tvOrder.comparatorProperty());


        tvOrder.setItems(sortedOrderDetails);
    }



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
        //getOrderList();


        //set retrieved values to the table cells
        col_orderID.setCellValueFactory(new PropertyValueFactory<>("id")); //Order class attributes
        col_invoiceNo.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        col_cusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        col_proId.setCellValueFactory(new PropertyValueFactory<>("proId"));
        col_proName.setCellValueFactory(new PropertyValueFactory<>("proName"));
        col_unPrice.setCellValueFactory(new PropertyValueFactory<>("unPrice"));
        col_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        col_ordereddate.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        //add  edit and delete button to the cell
        Callback<TableColumn<OrderManagement.Order, String>, TableCell<OrderManagement.Order, String>> orderCellFactory = (TableColumn<OrderManagement.Order, String> param) -> {
            // make order table's cell containing update ,edit buttons
            final TableCell<OrderManagement.Order, String> orderCell = new TableCell<OrderManagement.Order, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //empty records cell
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteOrderIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editOrderIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteOrderIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:26px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editOrderIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:26px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteOrderIcon.setOnMouseClicked((MouseEvent event) -> {

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


                        editOrderIcon.setOnMouseClicked((MouseEvent event) -> {

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



                        HBox manageorderbtn = new HBox(editOrderIcon, deleteOrderIcon);
                        manageorderbtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteOrderIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editOrderIcon, new Insets(2, 3, 0, 2));

                        setGraphic(manageorderbtn);

                        setText(null);

                    }
                }

            };

            return orderCell;
        };
        col_action.setCellFactory(orderCellFactory);

        tvOrder.setItems(list);


            }
        }