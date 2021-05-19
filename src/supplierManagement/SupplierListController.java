package supplierManagement;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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


public class SupplierListController implements Initializable {

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

    @FXML
    private TableColumn <SupplierListTableView, Double> totOrderPlacedCol;
    @FXML
    private TableColumn <SupplierListTableView,Double> totOrdersDeliveredCol;
    @FXML
    private TableColumn <SupplierListTableView,Double> totOverdueOrdersCol;
    @FXML
    private TableColumn <SupplierListTableView,String> ActivityCol;


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


    @FXML
    private Button save;
    @FXML
    private Button clear;



    @Override
    public void initialize(URL url, ResourceBundle rb){

        showSupplierList();
    }



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

    @FXML
    public void removeSupplierOnAction(ActionEvent event) throws SQLException {
        Stage stage =(Stage) removeSupButton.getScene().getWindow();
        //supplierTableView.getItems().removeAll(supplierTableView.getSelectionModel().getSelectedItem());
        removeSupFromDb();
    }


    private void removeSupFromDb() throws SQLException {

            SupplierListTableView list = supplierTableView.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM cityofgems.supplier_info_table WHERE supplierID =" + list.getSupplierID();
            DBConnect.getConnection();
            Statement st = DBConnect.getConnection().createStatement();
            st.execute(query);
            showSupplierList();


    }





   /* @FXML
        private void handleMouseEvent(MouseEvent event){
        SupplierListTableView supList = supplierTableView.getSelectionModel().getSelectedItem();
        txtUpdateSName.setText("" +supList.getS_name());
        txtUpdateSEmail.setText("" +supList.getS_email());
        txtUpdateSAddress.setText("" +supList.getS_address());
        txtUpdateSPhone.setText("" +supList.getS_phone());
        txtUpdateSCountry.setText("" +supList.getS_country());
        txtUpdateSWebsite.setText("" +supList.getS_website());
        txtUpdateSsupGtypes.setText("" +supList.getSupp_gemstoneTypes());
        txtUpdateSDescription.setText("" +supList.getS_description());
        }*/
        /*
        UpdateSupplierController update = load.getController();
        update.setUpdate(true);
        UpdateSupplierController.setTextField(list2.getSupplierID(), list2.getS_address(), list2.getS_country(), list2.getS_description(), list2.getS_email())
        editSupButton.getScene().getWindow();
        Stage supedit = new Stage();
        Parent supeditRoot = FXMLLoader.load(getClass().getResource("/supplierManagement/updateSupplier.fxml"));
        Scene supeditScene = new Scene(supeditRoot);
        supedit.setScene(supeditScene);
        supedit.show();
        supedit.setResizable(false); */



    //check connection
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

    //get order list method (2)
    public ObservableList<SupplierListTableView> getSupplierList(){
        ObservableList<SupplierListTableView> SupplierList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM cityofgems.supplier_info_table";
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



    private void showSupplierList() {

        ObservableList<SupplierListTableView> list = getSupplierList();

        sidcol.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        snamecol.setCellValueFactory(new PropertyValueFactory<>("s_name"));
        phonecol.setCellValueFactory(new PropertyValueFactory<>("s_phone"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("s_email"));
        websitecol.setCellValueFactory(new PropertyValueFactory<>("s_website"));
        addresscol.setCellValueFactory(new PropertyValueFactory<>("s_address"));
        suplygemtypecol.setCellValueFactory(new PropertyValueFactory<>("supp_gemstoneTypes"));
        countrycol.setCellValueFactory(new PropertyValueFactory<>("s_country"));
        descripCol.setCellValueFactory(new PropertyValueFactory<>("s_description"));

        supplierTableView.setItems(list);
    }


    //Update
    @FXML
    public void saveButtonOnAction(ActionEvent event){
        if(event.getSource().equals(save)){
            updateSupplierRecord();

        }
    }

    public void clearButtonOnAction(ActionEvent actionEvent) {
                if(actionEvent.getSource().equals(clear)){
                    clearFields();
                }
    }

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

        @FXML
        public void rowSelectionHandleEvent(MouseEvent mouseEvent) {
            SupplierListTableView supList = supplierTableView.getSelectionModel().getSelectedItem();
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


}