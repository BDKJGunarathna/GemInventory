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
import java.util.ResourceBundle;

public class GemrequestformController implements Initializable {

    @FXML
    private JFXButton gemrequesthomebtn;

    @FXML
    private JFXButton gemrequestlogoutbtn;

    @FXML
    private TextField gemrequestdescription;

    @FXML
    private TextField gemrequestquantity;

    @FXML
    private JFXButton gemrequestsendbtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void logoutgemrequestAction(ActionEvent addjev1) throws IOException {
        gemrequestlogoutbtn.getScene().getWindow().hide();

        Stage logoutgemrequest = new Stage();
        Parent gemrequestroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene gemrequestscene2 = new Scene(gemrequestroot2);
        logoutgemrequest.setScene(gemrequestscene2);
        logoutgemrequest.show();
        logoutgemrequest.setResizable(false);
    }

    @FXML
    public void sendAction(ActionEvent gemreqev2) {
        Stage stage = (Stage) gemrequestsendbtn.getScene().getWindow();
        stage.close();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
                Stage hm = (Stage) gemrequestsendbtn.getScene().getWindow();
                hm.setScene(new Scene(root, 1250,800));
                hm.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }



