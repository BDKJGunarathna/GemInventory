package employeemanagement;

import com.jfoenix.controls.*;
import dbconnection.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class updateemployeedetailsController implements Initializable {

    @FXML
    private JFXButton updateaddemployeemenubtn;

    @FXML
    private JFXButton updateviewemployeemenubtn;

    @FXML
    private JFXButton updateemployeecountmenubtn;

    @FXML
    private JFXButton updatelogout;

    @FXML
    private JFXButton updatehome;

    @FXML
    private TextField updateaddress;

    @FXML
    private TextField updatephonenumber1;

    @FXML
    private TextField updatephonenumber2;

    @FXML
    private TextField updateemail;

    @FXML
    private ComboBox<String> updateemployeetype;

    @FXML
    private TextField updatesalary;

    @FXML
    private JFXButton updateemployeebtn;

    @FXML
    private TextField updatesuspend;

    //Create employee type selection list
    ObservableList<String> emptypelist = FXCollections.observableArrayList("CEO", "Sales & Administrator Manager", "Inventory Manager", "Supplier Manager", "Finishing & Distribution Manager", "Assistant Manager", "Admin", "Sales Representative", "Accountant", "Accountant Assistant", "Store Supervisor", "Marketing Executive", "Office Assistant", "Gemologist", "Administrative Assistant", "Quality Checker", "Clerk", "Laborer");

    //declare variables
    private Connection connection;
    private DBHandler handler;//DBHandler is the connection class
    private PreparedStatement pst;
    int employeeID;
    private boolean update;

    @Override
    public void initialize(URL arg4, ResourceBundle arg5) {
        //Initialize the employetype combobox
        updateemployeetype.setItems(emptypelist);

        //Initialize DBHandler class
        handler = new DBHandler();

    }

    //Logout Method(Direct to login page)
    @FXML
    public void updatelogoutAction(ActionEvent updateev3) throws IOException {
        updatelogout.getScene().getWindow().hide();

        Stage updatelogout = new Stage();//Create a Stage
        updatelogout.setTitle("CITY OF GEMS - LOGIN");//Set Title of interface
        //Setup the Scene
        Parent updateroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene updatescene2 = new Scene(updateroot2);//Create a scene
        updatelogout.setScene(updatescene2);//Set Scene object on the Stage
        updatelogout.show();//Show the Stage which create above (makes the Stage visible and the exits)
        updatelogout.setResizable(false);//User cannot resize the frame
    }

    //Home Method (Direct to Employee Management Home page)
    @FXML
    public void updatehomeAction(ActionEvent updateev8) throws IOException {
        updatehome.getScene().getWindow().hide();

        Stage updatehome = new Stage();//Create a Stage
        updatehome.setTitle("CITY OF GEMS - EMPLOYEE MANAGEMENT HOME");//Set Title of interface
        //Setup the Scene
        Parent updateroot8 = FXMLLoader.load(getClass().getResource("/employeemanagement/employeemanagementhome.fxml"));
        Scene updatescene8 = new Scene(updateroot8);//Create a Scene
        updatehome.setScene(updatescene8);//Set Scene object on the Stage
        updatehome.show();//Show the Stage which create above (makes the Stage visible and the exits)
        updatehome.setResizable(false);//User cannot resize the frame
    }

    //Add Employee Button method (Direct to add employee page)
    @FXML
    public void updateaddemployeemenubuttonAction(ActionEvent updateev4) throws IOException {
        updateaddemployeemenubtn.getScene().getWindow().hide();

        Stage updateaddmenu = new Stage();//Create a Stage
        updateaddmenu.setTitle("CITY OF GEMS - ADD EMPLOYEE");//Set Title of interface
        //Setup the Scene
        Parent updateroot3 = FXMLLoader.load(getClass().getResource("/employeemanagement/addemployee.fxml"));
        Scene updatescene3 = new Scene(updateroot3);//Create a scene
        updateaddmenu.setScene(updatescene3);//Set Scene object on the Stage
        updateaddmenu.show();//Show the Stage which create above (makes the Stage visible and the exits)
        updateaddmenu.setResizable(false);//User cannot resize the frame
    }

    //View Employee Button method (Direct to filter employee page)
    @FXML
    public void updateviewemployeemenubuttonAction(ActionEvent updateev6) throws IOException {
        updateviewemployeemenubtn.getScene().getWindow().hide();

        Stage updateviewemp = new Stage();//Create a Stage
        updateviewemp.setTitle("CITY OF GEMS - FILTERED EMPLOYEE REPORTS");//Set Title of interface
        //Setup the Scene
        Parent updateroot5 = FXMLLoader.load(getClass().getResource("/employeemanagement/filteremployees.fxml"));
        Scene updatescene4 = new Scene(updateroot5);//Create a scene
        updateviewemp.setScene(updatescene4);//Set Scene object on the Stage
        updateviewemp.show();//Show the Stage which create above (makes the Stage visible and the exits)
        updateviewemp.setResizable(false);//User cannot resize the frame
    }

    //Employee Count Button method (Direct to employee count page)
    @FXML
    public void updateemployeecountmenubuttonAction(ActionEvent updateev7) throws IOException {
        updateemployeecountmenubtn.getScene().getWindow().hide();

        Stage updateempcount = new Stage();//Create a Stage
        updateempcount.setTitle("CITY OF GEMS - EMPLOYEE COUNT");//Set Title of interface
        //Setup the Scene//Setup the Scene
        Parent updateroot6 = FXMLLoader.load(getClass().getResource("/employeemanagement/employeecount.fxml.fxml"));
        Scene updatescene5 = new Scene(updateroot6);//Create a scene
        updateempcount.setScene(updatescene5);//Set Scene object on the Stage
        updateempcount.show();//Show the Stage which create above (makes the Stage visible and the exits)
        updateempcount.setResizable(false);//User cannot resize the frame
    }

    //Update Button method
    @FXML
    public void updateemployeeAction(ActionEvent e) {
        //Establishing a Connection
        connection = handler.getConnection();

        //Get values and assign to declared variables
        String emp_address = updateaddress.getText();
        String emp_phone1 = String.valueOf(updatephonenumber1.getText());
        String emp_phone2 = String.valueOf(updatephonenumber2.getText());
        String emp_email = updateemail.getText();
        String emp_type = (String) updateemployeetype.getValue();

        //Validate data (If any data does not input to update form popup alert error box)
        if (emp_address.isEmpty() || emp_phone1.isEmpty() || emp_phone2.isEmpty() || emp_type.isEmpty() || emp_email.isEmpty()) {
            //Alert error box
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();//Shows the alert object and then blocks until alert is closed
        } else {
            //Saving Employee Details
            if (validateEmail() & validateHomePhone() & validateMobilePhone()) {
                //SQL QUERY (UPDATE) String
                String update = "UPDATE employeemanagement_table SET empAddress=?, empPhone1=?, empPhone2=?, empEmail=?, empType=?, empSalary=?, empSuspend= ? WHERE empID = '"+employeeID+"'";

                //Establishing a Connection
                connection = handler.getConnection();
                try {
                    //Create a statement using connection object
                    pst = connection.prepareStatement(update);
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }

                try {
                    //Get data from form inputs and Add data to PreparedStatement
                    pst.setString(1, updateaddress.getText());
                    pst.setInt(2, Integer.parseInt(updatephonenumber1.getText()));
                    pst.setInt(3, Integer.parseInt(updatephonenumber2.getText()));
                    pst.setString(4, updateemail.getText());
                    pst.setString(5, updateemployeetype.getValue());
                    pst.setInt(6, Integer.parseInt(updatesalary.getText()));
                    pst.setString(7, updatesuspend.getText());

                    //Update Query (It will returns the number of affected rows)
                    pst.executeUpdate();

                    //Alert Information box
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated");
                    alert.show();
                } catch (SQLException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }

    //setTextField method (Using in View Employee Details Page)
    void setTextField(int empid, String empaddress, int empphone1, int empphone2, String emptype, String empemail, double empsalary, String empsuspend) {
        //Set values for variables
        employeeID = empid;
        updateaddress.setText(empaddress);
        updatephonenumber1.setText(String.valueOf(empphone1));
        updatephonenumber2.setText(String.valueOf(empphone2));
        updateemail.setText(empemail);
        updateemployeetype.setValue(emptype);
        updatesalary.setText(String.valueOf(empsalary));
        updatesuspend.setText(empsuspend);
    }

    //setUpdate method (Usingin View Employee Details Page)
    void setUpdate(boolean b) {
        this.update = b;
    }

    //Email Validation
    private boolean validateEmail() {
        //Pattern Define (Return compiled version of a regular expression into the pattern)
        Pattern p1 = Pattern.compile("[a-zA-z0-9][a-zA-z0-9._]*@[a-zA-z0-9]+([.][a-zA-z]+)+");
        //Matching the above define pattern against regular expression
        Matcher m1 = p1.matcher(updateemail.getText());
        //if email is like defined pattern email validate and update an form create else popup a warning
        if (m1.find() && m1.group().equals(updateemail.getText())) {
            return true;
        } else {
            //Alert Warning box
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Enter Valid Email");
            alert.showAndWait();//Shows the alert object and then blocks until alert is closed

            return false;
        }
    }

    //Home Phone Number validation
    private boolean validateHomePhone() {
        //Pattern Define (Return compiled version of a regular expression into the pattern)
        Pattern p1 = Pattern.compile("(0|94)?(63|25|36|55|57|65|32|11|91|33|47|51|21|67|34|81|35|37|23|66|41|54|31|52|38|27|45|26|24)?[0-9]{7}");
        //Matching the above define pattern against regular expression
        Matcher m1 = p1.matcher(updatephonenumber1.getText());
        //if home phone number is like defined pattern phone number validate and an update form create else popup a warning
        if (m1.find() && m1.group().equals(updatephonenumber1.getText())) {
            return true;
        } else {
            //Alert Warning box
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Home Phone Number");
            alert.setHeaderText(null);
            alert.setContentText("Enter Valid Home Phone Number");
            alert.showAndWait();//Shows the alert object and then blocks until alert is closed

            return false;
        }
    }

    //Mobile Phone Number validation
    private boolean validateMobilePhone() {
        //Pattern Define (Return compiled version of a regular expression into the pattern)
        Pattern p1 = Pattern.compile("(0|94)?(70|71|72|75|76|77|78)?[0-9]{7}");
        //Matching the above define pattern against regular expression
        Matcher m1 = p1.matcher(updatephonenumber2.getText());
        //if mobile phone number is like defined pattern mobile phone number validate and an update form create else popup a warning
        if (m1.find() && m1.group().equals(updatephonenumber2.getText())) {
            return true;
        } else {
            //Alert Warning box
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Mobile Phone Number");
            alert.setHeaderText(null);
            alert.setContentText("Enter Valid Mobile Phone Number");
            alert.showAndWait();//Shows the alert object and then blocks until alert is closed

            return false;
        }
    }

}
