package customermanagement;

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

public class CustomerregistrationController implements Initializable {

    @FXML
    private JFXButton customerereghomebtn;

    @FXML
    private JFXButton customerereglogoutbtn;

    @FXML
    private TextField customereregcustomername;

    @FXML
    private TextField customereregcustomeremail;

    @FXML
    private TextField customereregcustomeraddress;

    @FXML
    private TextField customereregcontactnumber;

    @FXML
    private JFXDatePicker customereregdate;

    @FXML
    private JFXButton customereregsubmitbtn;

    @FXML
    private JFXButton customereregregcustmenu;

    @FXML
    private JFXButton customereregcustdetailsmenu;

    @FXML
    private JFXButton customereregsearchprofmenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void custreglogoutAction(ActionEvent custregev3) throws IOException {
        customerereglogoutbtn.getScene().getWindow().hide();

        Stage custreglogout = new Stage();
        Parent custregroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene custregscene2 = new Scene(custregroot2);
        custreglogout.setScene(custregscene2);
        custreglogout.show();
        custreglogout.setResizable(false);
    }

    @FXML
    public void customerregmenubuttonAction(ActionEvent custregev4) throws IOException {
        customereregregcustmenu.getScene().getWindow().hide();

        Stage custregmenu = new Stage();
        Parent custregroot3 = FXMLLoader.load(getClass().getResource("/customermanagement/customerregistration.fxml"));
        Scene custregscene3 = new Scene(custregroot3);
        custregmenu.setScene(custregscene3);
        custregmenu.show();
        custregmenu.setResizable(false);
    }

    @FXML
    public void searchprofmenubuttonAction(ActionEvent custregev5) throws IOException {
        customereregsearchprofmenu.getScene().getWindow().hide();

        Stage searchprofmenu = new Stage();
        Parent custregroot4 = FXMLLoader.load(getClass().getResource("/customermanagement/searchcustomerprofile.fxml"));
        Scene custregscene4 = new Scene(custregroot4);
        searchprofmenu.setScene(custregscene4);
        searchprofmenu.show();
        searchprofmenu.setResizable(false);
    }

}
