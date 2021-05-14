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

    @FXML
    public void addgemrecAction(ActionEvent gemrecev2) {
        PauseTransition gemrecpt2 = new PauseTransition();
        gemrecpt2.setDuration(Duration.seconds(3));
        gemrecpt2.setOnFinished(ev -> {
            System.out.println("Received Gem Details Add Successfully");
        });
        gemrecpt2.play();
    }

}
