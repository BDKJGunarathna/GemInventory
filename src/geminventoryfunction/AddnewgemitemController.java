package geminventoryfunction;

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

public class AddnewgemitemController implements Initializable {

    @FXML
    private JFXButton homebtn;

    @FXML
    private JFXButton logoutbtn;

    @FXML
    private TextField entergemidbox;

    @FXML
    private TextField enterweightbox;

    @FXML
    private TextField entershapebox;

    @FXML
    private TextField enterdimensionbox;

    @FXML
    private TextField enterpricebox;

    @FXML
    private JFXTextArea enterdescriptionbox;

    @FXML
    private JFXButton clearaddnewgemfields;

    @Override
    public void initialize(URL arggem0, ResourceBundle arggem1) {

    }

    @FXML
    public void logoutgemAction(ActionEvent ev3) throws IOException {
        logoutbtn.getScene().getWindow().hide();

        Stage logoutgem = new Stage();
        Parent gemroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene gemscene2 = new Scene(gemroot2);
        logoutgem.setScene(gemscene2);
        logoutgem.show();
        logoutgem.setResizable(false);
    }

    @FXML
    public void clearaddnewgemfieldsAction(ActionEvent ev5) throws IOException {
        clearaddnewgemfields.getScene().getWindow().hide();

        Stage clearaddnewgemfields = new Stage();
        Parent gemroot1 = FXMLLoader.load(getClass().getResource("/geminventoryfunction/addnewgemitem.fxml"));
        Scene gemscene1 = new Scene(gemroot1);
        clearaddnewgemfields.setScene(gemscene1);
        clearaddnewgemfields.show();
        clearaddnewgemfields.setResizable(false);
    }

    @FXML
    public void addnewgemitemAction(ActionEvent geme) {
        PauseTransition pt2 = new PauseTransition();
        pt2.setDuration(Duration.seconds(3));
        pt2.setOnFinished(ev -> {
            System.out.println("Add Gem Item Successfully");
        });
        pt2.play();
    }
}
