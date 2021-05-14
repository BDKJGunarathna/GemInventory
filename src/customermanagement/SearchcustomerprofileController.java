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

public class SearchcustomerprofileController implements Initializable {

    @FXML
    private JFXButton searchprofhomebtn;

    @FXML
    private JFXButton searchproflogoutbtn;

    @FXML
    private TextField searchprofcustname;

    @FXML
    private JFXButton searchprofdeleteaccountbtn;

    @FXML
    private TextField searchprofcustid;

    @FXML
    private JFXButton searchprofregcustmenu;

    @FXML
    private JFXButton searchprofcustdetailsmenu;

    @FXML
    private JFXButton searchprofsearchprofilemenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void searchproflogoutAction(ActionEvent searchprofev3) throws IOException {
        searchproflogoutbtn.getScene().getWindow().hide();

        Stage searchproflogout = new Stage();
        Parent searchprofroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene searchprofscene2 = new Scene(searchprofroot2);
        searchproflogout.setScene(searchprofscene2);
        searchproflogout.show();
        searchproflogout.setResizable(false);
    }

    @FXML
    public void regcustmenubuttonAction(ActionEvent searchprofev4) throws IOException {
        searchprofregcustmenu.getScene().getWindow().hide();

        Stage regcustmenu = new Stage();
        Parent searchprofroot3 = FXMLLoader.load(getClass().getResource("/customermanagement/customerregistration.fxml"));
        Scene searchprofscene3 = new Scene(searchprofroot3);
        regcustmenu.setScene(searchprofscene3);
        regcustmenu.show();
        regcustmenu.setResizable(false);
    }

    @FXML
    public void searchprofilemenubuttonAction(ActionEvent searchprofev5) throws IOException {
        searchprofsearchprofilemenu.getScene().getWindow().hide();

        Stage searchprofilemenu = new Stage();
        Parent searchprofroot4 = FXMLLoader.load(getClass().getResource("/customermanagement/searchcustomerprofile.fxml"));
        Scene searchprofscene4 = new Scene(searchprofroot4);
        searchprofilemenu.setScene(searchprofscene4);
        searchprofilemenu.show();
        searchprofilemenu.setResizable(false);
    }

}
