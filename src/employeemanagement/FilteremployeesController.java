package employeemanagement;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FilteremployeesController implements Initializable {

    @FXML
    private JFXButton filtaddemployeemenubtn;

    @FXML
    private JFXButton filtviewemployeemenubtn;

    @FXML
    private JFXButton filtemployeecountmenubtn;

    @FXML
    private JFXButton filteremplogout;

    @FXML
    private JFXButton filteremphome;

    @FXML
    private JFXButton viewall;

    @FXML
    private JFXButton suspendemp;

    @FXML
    private JFXButton activeemp;

    @FXML
    private JFXButton ceo;

    @FXML
    private JFXButton salesmanagers;

    @FXML
    private JFXButton supmanagers;

    @FXML
    private JFXButton admins;

    @FXML
    private JFXButton accountassistants;

    @FXML
    private JFXButton officeassistants;

    @FXML
    private JFXButton qualitycheckers;

    @FXML
    private JFXButton inventormanagers;

    @FXML
    private JFXButton dismanagers;

    @FXML
    private JFXButton salesreps;

    @FXML
    private JFXButton storesupervisors;

    @FXML
    private JFXButton gemologists;

    @FXML
    private JFXButton clerks;

    @FXML
    private JFXButton assistantmanagers;

    @FXML
    private JFXButton accountants;

    @FXML
    private JFXButton marketingexecutives;

    @FXML
    private JFXButton adminassistants;

    @FXML
    private JFXButton laborers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //Logout Method(Direct to login page)
    @FXML
    public void filtlogoutAction(ActionEvent filtev3) throws IOException {
        filteremplogout.getScene().getWindow().hide();

        Stage filtlogout = new Stage();//Create a Stage
        //Setup the Scene
        Parent filtroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene filtscene2 = new Scene(filtroot2);//Create a scene
        filtlogout.setScene(filtscene2);//Set Scene object on the Stage
        filtlogout.show();//Show the Stage which create above (makes the Stage visible and the exits)
        filtlogout.setResizable(false);//User cannot resize the frame
    }

    //Add Employee Button method (Direct to add employee page)
    @FXML
    public void filtaddemployeemenubuttonAction(ActionEvent filtev4) throws IOException {
        filtaddemployeemenubtn.getScene().getWindow().hide();

        Stage filtaddmenu = new Stage();//Create a Stage
        //Setup the Scene
        Parent filtroot3 = FXMLLoader.load(getClass().getResource("/employeemanagement/addemployee.fxml"));
        Scene filtscene3 = new Scene(filtroot3);//Create a scene
        filtaddmenu.setScene(filtscene3);//Set Scene object on the Stage
        filtaddmenu.show();//Show the Stage which create above (makes the Stage visible and the exits)
        filtaddmenu.setResizable(false);//User cannot resize the frame
    }

    //View Employee Button method (Direct to filter employee page)
    @FXML
    public void filtviewemployeemenubuttonAction(ActionEvent ev6) throws IOException {
        filtviewemployeemenubtn.getScene().getWindow().hide();

        Stage filtviewemp = new Stage();//Create a Stage
        //Setup the Scene
        Parent filtroot5 = FXMLLoader.load(getClass().getResource("/employeemanagement/filteremployees.fxml"));
        Scene filtscene4 = new Scene(filtroot5);//Create a scene
        filtviewemp.setScene(filtscene4);//Set Scene object on the Stage
        filtviewemp.show();//Show the Stage which create above (makes the Stage visible and the exits)
        filtviewemp.setResizable(false);//User cannot resize the frame
    }

    //Employee Count Button method (Direct to employee count page)
    @FXML
    public void filtemployeecountmenubuttonAction(ActionEvent filtev7) throws IOException {
        filtemployeecountmenubtn.getScene().getWindow().hide();

        Stage filtempcount = new Stage();//Create a Stage
        //Setup the Scene
        Parent filtroot6 = FXMLLoader.load(getClass().getResource("/employeemanagement/employeecount.fxml"));
        Scene filtscene5 = new Scene(filtroot6);//Create a scene
        filtempcount.setScene(filtscene5);//Set Scene object on the Stage
        filtempcount.show();//Show the Stage which create above (makes the Stage visible and the exits)
        filtempcount.setResizable(false);//User cannot resize the frame
    }

    //View All Button Method (Direct to view employee details page)
    @FXML
    public void viewallAction(ActionEvent filtev8) throws IOException {
        viewall.getScene().getWindow().hide();

        Stage filtviewall = new Stage();//Create a Stage
        //Setup the Scene
        Parent filtroot7 = FXMLLoader.load(getClass().getResource("/employeemanagement/viewemployeedetails.fxml"));
        Scene filtscene6 = new Scene(filtroot7);//Create a scene
        filtviewall.setScene(filtscene6);//Set Scene object on the Stage
        filtviewall.show();//Show the Stage which create above (makes the Stage visible and the exits)
        filtviewall.setResizable(false);//User cannot resize the frame
    }

    @FXML
    private void printSuspendedEmployeeList(ActionEvent event) throws SQLException {
        //Create Generate Suspended Employee List Object
        Suspended_Employee_List_Report suspended_employee_list_report = new Suspended_Employee_List_Report();
        //Create Suspended Employee List PDF
        suspended_employee_list_report.createPdf();
    }

}
