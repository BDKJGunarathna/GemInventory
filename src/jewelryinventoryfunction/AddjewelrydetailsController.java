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

public class AddjewelrydetailsController implements Initializable {

    @FXML
    private JFXButton addjewelryhome;

    @FXML
    private JFXButton addjewelrylogout;

    @FXML
    private TextField enterjewelryidbox;

    @FXML
    private TextField enterjewelrynamebox;

    @FXML
    private TextField enterjewelrymaterialbox;

    @FXML
    private TextField enterjewelryweightbox;

    @FXML
    private TextField enterjewelryquantitybox;

    @FXML
    private TextField enterjewelrypricebox;

    @FXML
    private ComboBox<?> selectjewelrytype;

    @FXML
    private JFXButton addjewelrydonebtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void logoutjewelryAction(ActionEvent addjev1) throws IOException {
        addjewelrylogout.getScene().getWindow().hide();

        Stage logoutjewelry = new Stage();
        Parent jewelryroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene jewelryscene2 = new Scene(jewelryroot2);
        logoutjewelry.setScene(jewelryscene2);
        logoutjewelry.show();
        logoutjewelry.setResizable(false);
    }

    @FXML
    public void doneAction(ActionEvent addjev2) {
        PauseTransition jewpt2 = new PauseTransition();
        jewpt2.setDuration(Duration.seconds(3));
        jewpt2.setOnFinished(ev -> {
            System.out.println("Add Jewelry Details Successfully");
        });
        jewpt2.play();
    }

}
