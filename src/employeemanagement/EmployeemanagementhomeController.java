package employeemanagement;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeemanagementhomeController implements Initializable {

    @FXML
    private JFXButton emphomelogoutbtn;

    @FXML
    private JFXButton emphomehomebtn;

    @FXML
    private JFXButton emphomeaddempbtn;

    @FXML
    private JFXButton emphomeviewempbtn;

    @FXML
    private JFXButton emphomeempcountbtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //Logout Method(Direct to login page)
    @FXML
    public void emphomelogoutAction(ActionEvent emphomeev3) throws IOException {
        emphomelogoutbtn.getScene().getWindow().hide();

        Stage emphomelogout = new Stage();//Create a Stage
        //Setup the Scene
        Parent emphomeroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene emphomescene2 = new Scene(emphomeroot2);//Create a scene
        emphomelogout.setScene(emphomescene2);//Set Scene object on the Stage
        emphomelogout.show();//Show the Stage which create above (makes the Stage visible and the exits)
        emphomelogout.setResizable(false);//User cannot resize the frame
    }

    //Home Page (Direct to Dashboard Page)
    @FXML
    public void emphomehomeAction(ActionEvent emphome8) throws  IOException {
        emphomehomebtn.getScene().getWindow().hide();

        Stage emphomehome = new Stage();//Create a Stage
        //Setup the Scene
        Parent emphomeroot8 = FXMLLoader.load(getClass().getResource("/sample/dashboard.fxml"));
        Scene emphomescene8 = new Scene(emphomeroot8);//Create a scene
        emphomehome.setScene(emphomescene8);//Set Scene object on the Stage
        emphomehome.show();//Show the Stage which create above (makes the Stage visible and the exits)
        emphomehome.setResizable(false);
    }

    //Add Employee Button method (Direct to add employee page)
    @FXML
    public void emphomeaddemployeeAction(ActionEvent emphomeev4) throws IOException {
        emphomeaddempbtn.getScene().getWindow().hide();

        Stage emphomeaddmenu = new Stage();//Create a Stage
        //Setup the Scene
        Parent emphomeroot3 = FXMLLoader.load(getClass().getResource("/employeemanagement/addemployee.fxml"));
        Scene emphomescene3 = new Scene(emphomeroot3);//Create a scene
        emphomeaddmenu.setScene(emphomescene3);//Set Scene object on the Stage
        emphomeaddmenu.show();//Show the Stage which create above (makes the Stage visible and the exits)
        emphomeaddmenu.setResizable(false);//User cannot resize the frame
    }

    //View Employee Button method (Direct to filter employee page)
    @FXML
    public void emphomeviewemployeeAction(ActionEvent emphomeev6) throws IOException {
        emphomeviewempbtn.getScene().getWindow().hide();

        Stage emphomeviewemp = new Stage();//Create a Stage
        //Setup the Scene
        Parent emphomeroot5 = FXMLLoader.load(getClass().getResource("/employeemanagement/filteremployees.fxml"));
        Scene emphomescene4 = new Scene(emphomeroot5);//Create a scene
        emphomeviewemp.setScene(emphomescene4);//Set Scene object on the Stage
        emphomeviewemp.show();//Show the Stage which create above (makes the Stage visible and the exits)
        emphomeviewemp.setResizable(false);//User cannot resize the frame
    }

    //Employee Count Button method (Direct to employee count page)
    @FXML
    public void emphomeemployeecountmenubuttonAction(ActionEvent emphomeev7) throws IOException {
        emphomeempcountbtn.getScene().getWindow().hide();

        Stage emphometempcount = new Stage();//Create a Stage
        //Setup the Scene
        Parent emphomeroot6 = FXMLLoader.load(getClass().getResource("/employeemanagement/employeecount.fxml"));
        Scene emphomescene5 = new Scene(emphomeroot6);//Create a scene
        emphometempcount.setScene(emphomescene5);//Set Scene object on the Stage
        emphometempcount.show();//Show the Stage which create above (makes the Stage visible and the exits)
        emphometempcount.setResizable(false);//User cannot resize the frame
    }


}
