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
        PauseTransition gemreqpt2 = new PauseTransition();
        gemreqpt2.setDuration(Duration.seconds(3));
        gemreqpt2.setOnFinished(ev -> {
            System.out.println("Gem Request Form Send Successfully");
        });
        gemreqpt2.play();
    }

}
