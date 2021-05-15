package jewelryinventoryfunction;

import com.jfoenix.controls.*;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RecievedgemdetailsController implements Initializable {

    @FXML
    private JFXButton receivedgemdetailshomebtn;

    @FXML
    private JFXButton receivedgemdetailslogoutbtn;

    @FXML
    private TextField receivedgemdetailsreqnum;

    @FXML
    private TextField receivedgemdetailsgemid;

    @FXML
    private TextField receivedgemdetailsdescription;

    @FXML
    private TextField receivedgemdetailsweight;

    @FXML
    private TextField receivedgemdetailsquantity;

    @FXML
    private JFXButton receivedgemdetailsaddbtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void logoutreceivedgemAction(ActionEvent recgemev1) throws IOException {
        receivedgemdetailslogoutbtn.getScene().getWindow().hide();

        Stage logoutrecgem = new Stage();
        Parent recgemroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene recgemscene2 = new Scene(recgemroot2);
        logoutrecgem.setScene(recgemscene2);
        logoutrecgem.show();
        logoutrecgem.setResizable(false);
    }

    /*@FXML
    public void addgemrecAction(ActionEvent gemrecev2) {
        PauseTransition gemrecpt2 = new PauseTransition();
        gemrecpt2.setDuration(Duration.seconds(3));
        gemrecpt2.setOnFinished(ev -> {
            System.out.println("Received Gem Details Add Successfully");
        });
        gemrecpt2.play();
    }
*/

    @FXML
    public void fhomeOnAction(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            Stage hm2 = (Stage) receivedgemdetailshomebtn.getScene().getWindow();
            hm2.setTitle("City of Gems");
            hm2.setScene(new Scene(root, 1250,800));
            hm2.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void addgemrecAction(ActionEvent event) {
        if (receivedgemdetailsreqnum.getText().isEmpty() | receivedgemdetailsgemid.getText().isEmpty() | receivedgemdetailsdescription.getText().isEmpty() | receivedgemdetailsweight.getText().isEmpty() | receivedgemdetailsquantity.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter All values into the fields");
            alert.showAndWait();
        } else {
            insertDetails();
            Stage stage = (Stage) receivedgemdetailsaddbtn.getScene().getWindow();
            stage.close();
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
        String query = "INSERT into received_gem VALUES (" + receivedgemdetailsreqnum.getText() + ",'" + receivedgemdetailsgemid.getText() + "', '" + receivedgemdetailsdescription.getText() + "'," + receivedgemdetailsweight.getText() + "," + receivedgemdetailsquantity.getText() + ")";
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

