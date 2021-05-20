package jewelryinventoryfunction;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddjewelrydetailsController implements Initializable {

    @FXML
    private JFXButton addjewelryhome;

    @FXML
    private JFXButton addjewelrylogout;



    @FXML
    private TextField enterjewelrynamebox;

    @FXML
    private TextField enterjewelrytypebox;

    @FXML
    private TextField enterjewelrymaterialbox;

    @FXML
    private TextField enterjewelryweightbox;

    @FXML
    private TextField enterjewelryquantitybox;

    @FXML
    private TextField enterjewelrypricebox;

    @FXML
    private JFXButton addjewelrydonebtn;
    @FXML
    private JFXButton home;
    private Button button;

        @Override
        public void initialize (URL location, ResourceBundle resources){

        }

        @FXML
        public void logoutjewelryAction (ActionEvent addjev1) throws IOException {
            addjewelrylogout.getScene().getWindow().hide();

            Stage logoutjewelry = new Stage();
            Parent jewelryroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
            Scene jewelryscene2 = new Scene(jewelryroot2);
            logoutjewelry.setScene(jewelryscene2);
            logoutjewelry.show();
            logoutjewelry.setResizable(false);
        }
    @FXML
    public void fhomeOnAction2(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            Stage hm3 = (Stage) addjewelryhome.getScene().getWindow();
            hm3.setTitle("City of Gems");
            hm3.setScene(new Scene(root, 1250,800));
            hm3.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void doneAction(ActionEvent event){
            //validation
        if(enterjewelrynamebox.getText().isEmpty() | enterjewelrytypebox.getText().isEmpty() | enterjewelrymaterialbox.getText().isEmpty() | enterjewelryweightbox.getText().isEmpty() | enterjewelryquantitybox.getText().isEmpty() | enterjewelrypricebox.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter All values into the fields");
            alert.showAndWait();


        }else{
            insertDetails();
            Stage stage = (Stage) addjewelrydonebtn.getScene().getWindow();
            stage.close();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
                Stage addj = (Stage) addjewelrydonebtn.getScene().getWindow();
                addj.setTitle("City of Gems");
                addj.setScene(new Scene(root, 1250,800));
                addj.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        }

        public Connection getConnection () {
            Connection conn;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cityofgems", "root", "jami1998");
                return conn;
            } catch (Exception ex) {
                System.out.println("Error!" + ex.getMessage());
                return null;
            }
        }
        private void insertDetails() {
            String query = "INSERT into jewelry VALUES ("+0+", '" + enterjewelrynamebox.getText() + "','" + enterjewelrytypebox.getText() + "','" + enterjewelrymaterialbox.getText() + "'," + enterjewelryweightbox.getText() + "," + enterjewelryquantitybox.getText() + "," + enterjewelrypricebox.getText() + ")";
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

}
