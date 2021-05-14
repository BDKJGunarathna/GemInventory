package suppliermanagement;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewpurchaseorderController implements Initializable {

    @FXML
    private JFXButton newpurchhome;

    @FXML
    private JFXButton newpurchlogout;

    @FXML
    private TextField newpurchorderid;

    @FXML
    private TextField newpurchgemid;

    @FXML
    private TextField newpurchdescription;

    @FXML
    private JFXDatePicker newpurchdate;

    @FXML
    private ComboBox<?> newpurchshape;

    @FXML
    private Spinner<?> newpurchweight;

    @FXML
    private Spinner<?> newpurchquantity;

    @FXML
    private TextField newpurchprice;

    @FXML
    private TextField newpurchsuppliernamesearch;

    @FXML
    private TextField newpurchsupemail;

    @FXML
    private TextField newpurchsupaddress;

    @FXML
    private JFXDatePicker newpurchdeliverydate;

    @FXML
    private JFXTextArea newpurchadditionalnotes;

    @FXML
    private JFXButton newpurchsubmitorderbtn;

    @FXML
    private JFXButton newpurchresetformbtn;

    @FXML
    private JFXButton newpurchaddsupplierbtn;

    @FXML
    private JFXButton newpurchcheckgemstonesmenu;

    @FXML
    private JFXButton newpurchnewpurchordmenu;

    @FXML
    private JFXButton newpurchnewsupmenu;

    @FXML
    private JFXButton newpurchgemstoneordlistmenu;

    @FXML
    private JFXButton newpurchsuplistmenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void logoutpurchorderAction(ActionEvent purchordev1) throws IOException {
        newpurchlogout.getScene().getWindow().hide();

        Stage logoutpurchord = new Stage();
        Parent purchordroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene purchordscene2 = new Scene(purchordroot2);
        logoutpurchord.setScene(purchordscene2);
        logoutpurchord.show();
        logoutpurchord.setResizable(false);
    }

    @FXML
    public void newsuppliermenubuttonAction(ActionEvent purchordev2) throws IOException {
        newpurchnewsupmenu.getScene().getWindow().hide();

        Stage newpurchbtn = new Stage();
        Parent newpurchoot3 = FXMLLoader.load(getClass().getResource("/suppliermanagement/newsupplier.fxml"));
        Scene newpurchscene3 = new Scene(newpurchoot3);
        newpurchbtn.setScene(newpurchscene3);
        newpurchbtn.show();
        newpurchbtn.setResizable(false);
    }

    @FXML
    public void newpurchaseordermenubuttonAction(ActionEvent newpurchordev2) throws IOException {
        newpurchnewpurchordmenu.getScene().getWindow().hide();

        Stage newpurchordbtn = new Stage();
        Parent newpurchordroot4 = FXMLLoader.load(getClass().getResource("/suppliermanagement/newpurchaseorder.fxml"));
        Scene newpurchordscene4 = new Scene(newpurchordroot4);
        newpurchordbtn.setScene(newpurchordscene4);
        newpurchordbtn.show();
        newpurchordbtn.setResizable(false);
    }

}
