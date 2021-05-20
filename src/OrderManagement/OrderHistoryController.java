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
        // initialize order history method
        OrderHistory();

        //initialize search filter method
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

            String report_name = "C:\\Users\\Dinuka\\IdeaProjects\\Order_Management\\src\\OrderManagement\\Order_Details_of_current_month.pdf";
            //create Document object
            Document document = new Document();
            //set pdf instance
            PdfWriter.getInstance(document, new FileOutputStream(report_name));
            //open the document
            document.open();

            Font bold = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD);
            Paragraph p = new Paragraph("Jewelry_Order_Details_Of_The_Current_Month");
            document.add(p);

            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));



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
                    PdfPTable table = null;
                    while (rs.next()) {
                        table = new PdfPTable(10);


                        PdfPCell c1 = new PdfPCell(new Phrase("Order ID"));
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("Invoice No"));
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("Cus ID"));
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("Pro ID"));
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("Pro Name"));
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("un Price "));
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("Quantity"));
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("Total"));
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("Ordered Date"));
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("Status"));
                        table.addCell(c1);
                        table.setHeaderRows(1);

                        table.addCell(String.valueOf(rs.getInt("id")));
                        table.addCell(String.valueOf(rs.getString("invoiceNo")));
                        table.addCell(String.valueOf(rs.getInt("cusid")));
                        table.addCell(String.valueOf(rs.getInt("proid")));
                        table.addCell(String.valueOf(rs.getString("proname")));
                        table.addCell(String.valueOf(rs.getDouble("unprice")));
                        table.addCell(String.valueOf(rs.getInt("qty")));
                        table.addCell(String.valueOf(rs.getDouble("total")));
                        table.addCell(String.valueOf(rs.getDate("date")));
                        table.addCell(String.valueOf(rs.getString("status")));
                        document.add(table);
                    }
                }


            document.close();


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

        //Wrap the ObservableList in a FilteredList (initially display all data)
        FilteredList<Order> filteredOrderDetails = new FilteredList<>(list, b -> true);

        //Set the filter Predicate whenever the filter changes(). search by the filtered list
        txtSearchJewelryOrder.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredOrderDetails.setPredicate(order -> {
                //If filter text is empty, display all employees
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //Compare employee name, employee type, and employee ID of every person with filter text
                String lowerCaseFIlter = newValue.toLowerCase();// convert entered value to the lowercase letters

                if (String.valueOf(order.getId()).toLowerCase().indexOf(lowerCaseFIlter) != -1) {
                    return true;//Filter matches Order ID
                }
                else if (String.valueOf(order.getCusId()).toLowerCase().indexOf(lowerCaseFIlter) != -1) {
                    return true;//Filter matches customer ID

                }
               else
                    return false;//Does not match
            });
        });

        //Wrap the FilteredList in a SortedList
        SortedList<Order> sortedOrderDetails = new SortedList<>(filteredOrderDetails);

        //Bind the SortedList comparator to the OrderTableView comparator
        //Otherwise, sorting the orderTableView would have no effect
        sortedOrderDetails.comparatorProperty().bind(tvOrder.comparatorProperty());

        //Add sorted (and filtered) data to the table
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