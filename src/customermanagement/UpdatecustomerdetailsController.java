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

public class UpdatecustomerdetailsController implements Initializable {

    @FXML
    private JFXButton updatecusthomebtn;

    @FXML
    private JFXButton updatecustlogoutbtn;

    @FXML
    private JFXButton updatecustregcustmenu;

    @FXML
    private JFXButton updatecustcustdetailsmenu;

    @FXML
    private JFXButton updatecustsearchprofmenu;

    @FXML
    private TextField updatecustname;

    @FXML
    private TextField updatecustemail;

    @FXML
    private TextField updatecustaddress;

    @FXML
    private TextField updatecustcontactnumber;

    @FXML
    private JFXDatePicker updatecustdate;

    @FXML
    private JFXButton updatecustupdatedetailsbtn;

    @FXML
    private TextField updatecustcustid;

    @FXML
    private JFXButton updatecustcancelbtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void updatecustlogoutAction(ActionEvent updatecustev3) throws IOException {
        updatecustlogoutbtn.getScene().getWindow().hide();

        Stage updatecustlogout = new Stage();
        Parent updatecustroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene updatecustscene2 = new Scene(updatecustroot2);
        updatecustlogout.setScene(updatecustscene2);
        updatecustlogout.show();
        updatecustlogout.setResizable(false);
    }

    @FXML
    public void updatecustregcustmenubuttonAction(ActionEvent updatecustev4) throws IOException {
        updatecustregcustmenu.getScene().getWindow().hide();

        Stage updatecustregcustmenu = new Stage();
        Parent updatecustroot3 = FXMLLoader.load(getClass().getResource("/customermanagement/customerregistration.fxml"));
        Scene updatecustscene3 = new Scene(updatecustroot3);
        updatecustregcustmenu.setScene(updatecustscene3);
        updatecustregcustmenu.show();
        updatecustregcustmenu.setResizable(false);
    }

    @FXML
    public void updatecustsearchprofilemenubuttonAction(ActionEvent updatecustev5) throws IOException {
        updatecustsearchprofmenu.getScene().getWindow().hide();

        Stage updatecustsearchprofilemenu = new Stage();
        Parent updatecustroot4 = FXMLLoader.load(getClass().getResource("/customermanagement/searchcustomerprofile.fxml"));
        Scene updatecustscene4 = new Scene(updatecustroot4);
        updatecustsearchprofilemenu.setScene(updatecustscene4);
        updatecustsearchprofilemenu.show();
        updatecustsearchprofilemenu.setResizable(false);
    }
}
