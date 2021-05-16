package jewelryinventoryfunction;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.sql.DriverManager;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.util.Callback;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdatejewelrydetailsController implements Initializable {

    @FXML
    private TableView<Jewelry> jewelry;
    @FXML
    private TableColumn<Jewelry, Integer> idcol;
    @FXML
    private TableColumn<Jewelry, String> namecol;
    @FXML
    private TableColumn<Jewelry, String> typecol;
    @FXML
    private TableColumn<Jewelry, String> meterialcol;
    @FXML
    private TableColumn<Jewelry, Double> weightcol;
    @FXML
    private TableColumn<Jewelry, Integer> quancol;
    @FXML
    private TableColumn<Jewelry, Double> pricecol;


    @FXML
    private JFXButton addbtn3;
    @FXML
    private JFXButton updatebtn3;
    @FXML
    private JFXButton deletebtn3;
    @FXML
    private JFXButton store3;
    @FXML
    private JFXButton donebtn3;
    @FXML
    private TextField tid;
    @FXML
    private TextField tprice;
    @FXML
    private JFXButton homeupdate;

    //declare variables
    private int jewID;
    private boolean update;
    Jewelry jewel;

    //Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showTable();
    }

   //Buttons
    @FXML
    public void addJewelryOnAction(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("addjewelrydetails.fxml"));
            Stage addj = (Stage) addbtn3.getScene().getWindow();
            addj.setTitle("City of Gems");
            addj.setScene(new Scene(root, 1250, 800));
            addj.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateJewelryOnAction(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("updatejewelrydetails.fxml"));
            Stage updatej = (Stage) updatebtn3.getScene().getWindow();
            updatej.setTitle("City of Gems");
            updatej.setScene(new Scene(root, 1250, 800));
            updatej.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteJewelryOnAction(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("deletejewelrydetails.fxml"));
            Stage deletej = (Stage) deletebtn3.getScene().getWindow();
            deletej.setTitle("City of Gems");
            deletej.setScene(new Scene(root, 1250, 800));
            deletej.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void storeGemOnAction(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("recievedgemdetails.fxml"));
            Stage storeg = (Stage) store3.getScene().getWindow();
            storeg.setTitle("City of Gems");
            storeg.setScene(new Scene(root, 1250, 800));
            storeg.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void homeOnAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            Stage updatej = (Stage) homeupdate.getScene().getWindow();
            updatej.setTitle("City of Gems");
            updatej.setScene(new Scene(root, 1250,800));
            updatej.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //check connection
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cityofgems", "root", "jami1998");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error!" + ex.getMessage());
            return null;
        }
    }

    //get jewelry details
    public ObservableList<Jewelry> getJewelryList() {
        ObservableList<Jewelry> jewelryList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM jewelry ";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Jewelry jew;

            while (rs.next()) {
                jew = new Jewelry(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getString("meterial"), rs.getDouble("weight"), rs.getInt("quantity"), rs.getDouble("price"));
                jewelryList.add(jew);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jewelryList;
    }


    //get jewelry details
    public void showTable(){
        ObservableList<Jewelry> list = getJewelryList();

        //set values to the columns
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        typecol.setCellValueFactory(new PropertyValueFactory<>("type"));
        meterialcol.setCellValueFactory(new PropertyValueFactory<>("meterial"));
        weightcol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        quancol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

        jewelry.setItems(list);
    }

    @FXML
    public void updatejewelryAction(ActionEvent event) {

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
         Alert alert=new Alert(Alert.AlertType.WARNING);
         alert.setTitle("Update Success!");
         alert.setHeaderText(null);
         alert.setContentText("Jewelry price updated Successfully!");
         alert.showAndWait();
            //switch to home.fxml
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            Stage home = (Stage) updatebtn3.getScene().getWindow();
            home.setTitle("City of Gems");
            home.setScene(new Scene(root, 1200, 700));
            home.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


    }

    //query to update
    private void updateRecord(){

        String query = "UPDATE jewelry SET id = "+ tid.getText() +" price = " + tprice.getText() + "   WHERE id= " + jewID+"" ;
        executeUpdate(query);
    }

    private void executeUpdate (String query){
        Connection conn = getConnection();
        Statement st;

        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setUpdate(boolean b) {
        this.update = b;
    }

    @FXML
    public void setTextField (int jew_id, int jid,int jprice){
        jewID = jew_id;
      //  Jewelry jewelry1 = jewelry.getSelectionModel().getSelectedItem();
        tid.setText(String.valueOf(jid));
        tprice.setText(String.valueOf(jprice));
    }

    @FXML
    private void handleMouse (MouseEvent event){
        Jewelry jewelry1 = jewelry.getSelectionModel().getSelectedItem();
        tid.setText("" + jewelry1.getId());
        tprice.setText("" + jewelry1.getPrice());
    }

}







//////////////////////////////////////////////////
/*
   public void doneOnActionhome2(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            Stage storeg = (Stage) homeupdate.getScene().getWindow();
            storeg.setTitle("City of Gems");
            storeg.setScene(new Scene(root, 1250,800));
            storeg.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

        //check connection
        public Connection getConnection() {
            Connection conn;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cityofgems", "root", "jami1998");
                return conn;
            } catch (Exception ex) {
                System.out.println("Error!" + ex.getMessage());
                return null;
            }
        }

        //get  list method
        public ObservableList<Jewelry> getJewelryList() {
            ObservableList<Jewelry> jewelryList = FXCollections.observableArrayList();
            Connection conn = getConnection();
            String query = "SELECT * FROM jewelry ";
            Statement st;
            ResultSet rs;

            try {
                st = conn.createStatement();
                rs = st.executeQuery(query);
                Jewelry jew;

                while (rs.next()) {
                    jew = new Jewelry(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getString("meterial"), rs.getDouble("weight"), rs.getInt("quantity"), rs.getDouble("price"));
                    jewelryList.add(jew);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return jewelryList;
        }


        @FXML
        private void ShowTable() {
            ObservableList<Jewelry> list = getJewelryList();

            idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
            namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
            typecol.setCellValueFactory(new PropertyValueFactory<>("type"));
            meterialcol.setCellValueFactory(new PropertyValueFactory<>("meterial"));
            weightcol.setCellValueFactory(new PropertyValueFactory<>("weight"));
            quancol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

            jewelry.setItems(list);

        }

    @FXML
    public void updatejewelryAction(ActionEvent updateordev2) {

        updateRecord();

        try {
            PauseTransition updateorderpt2 = new PauseTransition();
            updateorderpt2.setDuration(Duration.seconds(3));
            updateorderpt2.setOnFinished(ev -> {
                System.out.println("Details Update Successfully");
            });
            updateorderpt2.play();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

      try {
            //switch to OrderHistory.fxml
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            Stage orderhistory = (Stage) homeupdate.getScene().getWindow();
            orderhistory.setTitle("City of Gems");
            orderhistory.setScene(new Scene(root, 1200, 700));
            orderhistory.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


    }

    //query to update
    private void updateRecord(){

        String query = "UPDATE jewelry SET price = " + tprice + "   WHERE id= " + jewID+"" ;
        Statement st1;
        Connection conn = getConnection();
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            st.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //    ShowTable();
        try{
            //st = conn.createStatement();
            st = getConnection().createStatement();
            st.executeUpdate(query);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    //    executeUpdate(query);
    }



    private void executeUpdate(String query) {
        //Connection conn = getConnection();
     //   OrderManagement.DBConnection.getConnection();
        Statement st;
       Connection conn = getConnection();
        Statement st = conn.createStatement();
        st.execute(query);
    //    ShowTable();
       try{
            //st = conn.createStatement();
            st = getConnection().createStatement();
            st.executeUpdate(query);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

   public void setUpdate(boolean b) {
        this.update = b;
    }



    /*          //add cell of button edit
      Callback<TableColumn<Jewelry, String>, TableCell<Jewelry, String>> cellFoctory = (TableColumn<Jewelry, String> param) -> {
            // make cell containing buttons
            final TableCell<Jewelry, String> cell = new TableCell<Jewelry, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );

                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                Jewelry jew = jewelry.getSelectionModel().getSelectedItem();
                                String query = "UPDATE jewelry SET  price = " + tprice + "  WHERE id  = 0 ";
                                //" + tid.getText() + "

                                Connection conn = getConnection();
                                Statement st = conn.createStatement();
                                st.execute(query);
                                ShowTable();
                            } catch (SQLException ex) {
                                Logger.getLogger(DeletejewelrydetailsController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        HBox managebtn = new HBox(editIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        actioncol.setCellFactory(cellFoctory);
            jewelry.setItems(list);
        }
*/
  /*  @FXML
        private void handleMouse (MouseEvent event){
            Jewelry jewelry1 = jewelry.getSelectionModel().getSelectedItem();
            tid.setText("" + jewelry1.getId());
            tprice.setText("" + jewelry1.getPrice());
        }
*/








