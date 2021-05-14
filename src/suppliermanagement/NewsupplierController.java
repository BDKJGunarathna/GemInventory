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

public class NewsupplierController implements Initializable {

    @FXML
    private JFXButton newsupplierhomebtn;

    @FXML
    private JFXButton newsupplierlogout;

    @FXML
    private JFXButton nssupplierpurchaseorderbtn;

    @FXML
    private JFXButton nssuppliernewsupplierbtn;

    @FXML
    private JFXButton nssuppliergemstonebtn;

    @FXML
    private JFXButton nssuppliersuplistbtn;

    @FXML
    private TextField nssupplierid;

    @FXML
    private TextField nssuppliername;

    @FXML
    private TextField nssupplieremail;

    @FXML
    private TextField nssupplierphonenumber;

    @FXML
    private TextField nssupplierwebsite;

    @FXML
    private TextField nssupplierstreet;

    @FXML
    private TextField nssuppliercity;

    @FXML
    private TextField nssupplierzipcode;

    @FXML
    private TextField nssuppliermunicipality;

    @FXML
    private TextField nssupplierstate;

    @FXML
    private ComboBox<?> nssuppliercountry;

    @FXML
    private JFXCheckBox nssupplierdiamonds;

    @FXML
    private JFXCheckBox nssupplierruby;

    @FXML
    private JFXCheckBox nssupplierjade;

    @FXML
    private JFXCheckBox nssupplieramber;

    @FXML
    private JFXCheckBox nssuppliersupphire;

    @FXML
    private JFXCheckBox nssupplieremerald;

    @FXML
    private JFXCheckBox nssupplierpearl;

    @FXML
    private JFXCheckBox nssupplierturuquoise;

    @FXML
    private JFXTextArea nssupplierdescription;

    @FXML
    private JFXTextArea nssupplieraddnote;

    @FXML
    private JFXButton nssupplieradd;

    @FXML
    private JFXButton nssupplierreset;

    @FXML
    private JFXButton nssuppliercheckgemstonesbtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void logoutnewsupplierAction(ActionEvent newsupev1) throws IOException {
        newsupplierlogout.getScene().getWindow().hide();

        Stage logoutnewsupplier = new Stage();
        Parent newsupplierroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene newsupplierscene2 = new Scene(newsupplierroot2);
        logoutnewsupplier.setScene(newsupplierscene2);
        logoutnewsupplier.show();
        logoutnewsupplier.setResizable(false);
    }

    @FXML
    public void newsuppliermenubuttonAction(ActionEvent newsupev2) throws IOException {
        nssuppliernewsupplierbtn.getScene().getWindow().hide();

        Stage newsupbtn = new Stage();
        Parent newsupplierroot3 = FXMLLoader.load(getClass().getResource("/suppliermanagement/newsupplier.fxml"));
        Scene newsupplierscene3 = new Scene(newsupplierroot3);
        newsupbtn.setScene(newsupplierscene3);
        newsupbtn.show();
        newsupbtn.setResizable(false);
    }

    @FXML
    public void newpurchaseordermenubuttonAction(ActionEvent newsupev2) throws IOException {
        nssupplierpurchaseorderbtn.getScene().getWindow().hide();

        Stage purchordbtn = new Stage();
        Parent purchordroot4 = FXMLLoader.load(getClass().getResource("/suppliermanagement/newpurchaseorder.fxml"));
        Scene purchordscene4 = new Scene(purchordroot4);
        purchordbtn.setScene(purchordscene4);
        purchordbtn.show();
        purchordbtn.setResizable(false);
    }

}
