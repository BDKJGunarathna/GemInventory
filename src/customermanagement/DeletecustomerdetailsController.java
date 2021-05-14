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

public class DeletecustomerdetailsController implements Initializable {

    @FXML
    private JFXButton deletecusthomebtn;

    @FXML
    private JFXButton deletecustlogoutbtn;

    @FXML
    private TextField deletecustname;

    @FXML
    private TextField deletecustemail;

    @FXML
    private TextField deletecustaddress;

    @FXML
    private TextField deletecustcontactnumber;

    @FXML
    private JFXDatePicker deletecustdate;

    @FXML
    private JFXButton deletecustdeleteaccountbtn;

    @FXML
    private TextField deletecustid;

    @FXML
    private JFXButton deletecustcancelbtn;

    @FXML
    private JFXButton deletecustregcustmenu;

    @FXML
    private JFXButton deletecustcustdetailsmenu;

    @FXML
    private JFXButton deletecustsearchprofmenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void deletecustlogoutAction(ActionEvent deletecustev3) throws IOException {
        deletecustlogoutbtn.getScene().getWindow().hide();

        Stage deletecustlogout = new Stage();
        Parent deletecustroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene deletecustscene2 = new Scene(deletecustroot2);
        deletecustlogout.setScene(deletecustscene2);
        deletecustlogout.show();
        deletecustlogout.setResizable(false);
    }

    @FXML
    public void deletecustregcustmenubuttonAction(ActionEvent deletecustev4) throws IOException {
        deletecustregcustmenu.getScene().getWindow().hide();

        Stage deletecustregmenu = new Stage();
        Parent deletecustroot3 = FXMLLoader.load(getClass().getResource("/customermanagement/customerregistration.fxml"));
        Scene deletecustscene3 = new Scene(deletecustroot3);
        deletecustregmenu.setScene(deletecustscene3);
        deletecustregmenu.show();
        deletecustregmenu.setResizable(false);
    }

    @FXML
    public void deletecustsearchprofilemenubuttonAction(ActionEvent deletecustev5) throws IOException {
        deletecustsearchprofmenu.getScene().getWindow().hide();

        Stage deletecustsearchprofilemenu = new Stage();
        Parent deletecustroot4 = FXMLLoader.load(getClass().getResource("/customermanagement/searchcustomerprofile.fxml"));
        Scene deletecustscene4 = new Scene(deletecustroot4);
        deletecustsearchprofilemenu.setScene(deletecustscene4);
        deletecustsearchprofilemenu.show();
        deletecustsearchprofilemenu.setResizable(false);
    }

}
