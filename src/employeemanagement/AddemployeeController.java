package employeemanagement;

import com.jfoenix.controls.*;
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
import dbconnection.DBHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddemployeeController implements Initializable {

    @FXML
    private JFXButton addemployeemenubtn;

    @FXML
    private JFXButton viewemployeemenubtn;

    @FXML
    private JFXButton employeecountmenubtn;

    @FXML
    private JFXButton logout;

    @FXML
    private JFXButton home;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private JFXDatePicker dateofbirth;

    @FXML
    private TextField nicnumber;

    @FXML
    private TextField enteraddress;

    @FXML
    private TextField phonenumber1;

    @FXML
    private TextField phonenumber2;

    @FXML
    private TextField email;

    @FXML
    private ComboBox<String> employeetype;

    @FXML
    private TextField salary;

    @FXML
    private JFXButton addanemployeebtn;

    @FXML
    private JFXButton clearfieldsbtn;

    //Create employee type selection list
    ObservableList<String> emptypelist = FXCollections.observableArrayList("CEO", "Sales & Administrator Manager", "Inventory Manager", "Supplier Manager", "Finishing & Distribution Manager", "Assistant Manager", "Admin", "Sales Representative", "Accountant", "Accountant Assistant", "Store Supervisor", "Marketing Executive", "Office Assistant", "Gemologist", "Administrative Assistant", "Quality Checker", "Clerk", "Laborer");

    //declare variables
    private Connection connection;
    private DBHandler handler;//DBHandler is the connection class
    private PreparedStatement pst;

    @Override
    public void initialize(URL arg2, ResourceBundle arg3) {
        //Initialize the employetype combobox
        employeetype.setItems(emptypelist);

        //Initialize DBHandler class
        handler = new DBHandler();
    }

    //Logout Method(Direct to login page)
    @FXML
    public void logoutAction(ActionEvent ev3) throws IOException {
        logout.getScene().getWindow().hide();

        Stage logout = new Stage();//Create a Stage
        logout.setTitle("CITY OF GEMS - LOGIN");//Set Title of interface
        //Setup the Scene
        Parent root2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene scene2 = new Scene(root2);//Create a scene
        logout.setScene(scene2);//Set Scene object on the Stage
        logout.show();//Show the Stage which create above (makes the Stage visible and the exits)
        logout.setResizable(false);//User cannot resize the frame
    }

    //Home Page (Direct to Employee Management Home Page)
    @FXML
    public void addemployeehomeAction(ActionEvent emphome8) throws  IOException {
        home.getScene().getWindow().hide();

        Stage addemphome = new Stage();//Create a Stage
        addemphome.setTitle("CITY OF GEMS - EMPLOYEE MANAGEMENT HOME");//Set Title of interface
        //Setup the Scene
        Parent addemproot8 = FXMLLoader.load(getClass().getResource("/employeemanagement/employeemanagementhome.fxml"));
        Scene addempscene8 = new Scene(addemproot8);//Create a scene
        addemphome.setScene(addempscene8);//Set Scene object on the Stage
        addemphome.show();//Show the Stage which create above (makes the Stage visible and the exits)
        addemphome.setResizable(false);
    }

    //Add Employee Button method (Direct to add employee page)
    @FXML
    public void addemployeemenubuttonAction(ActionEvent ev4) throws IOException {
        addemployeemenubtn.getScene().getWindow().hide();

        Stage addmenu = new Stage();//Create a Stage
        addmenu.setTitle("CITY OF GEMS - ADD EMPLOYEE");//Set Title of interface
        //Setup the Scene
        Parent root3 = FXMLLoader.load(getClass().getResource("/employeemanagement/addemployee.fxml"));
        Scene scene3 = new Scene(root3);//Create a scene
        addmenu.setScene(scene3);//Set Scene object on the Stage
        addmenu.show();//Show the Stage which create above (makes the Stage visible and the exits)
        addmenu.setResizable(false);//User cannot resize the frame
    }

    //View Employee Button method (Direct to filter employee page)
    @FXML
    public void viewemployeemenubuttonAction(ActionEvent ev6) throws IOException {
        viewemployeemenubtn.getScene().getWindow().hide();

        Stage viewemp = new Stage();//Create a Stage
        viewemp.setTitle("CITY OF GEMS - FILTERED EMPLOYEE REPORTS");//Set Title of interface
        //Setup the Scene
        Parent root5 = FXMLLoader.load(getClass().getResource("/employeemanagement/filteremployees.fxml"));
        Scene scene4 = new Scene(root5);//Create a scene
        viewemp.setScene(scene4);//Set Scene object on the Stage
        viewemp.show();//Show the Stage which create above (makes the Stage visible and the exits)
        viewemp.setResizable(false);//User cannot resize the frame
    }

    //Employee Count Button method (Direct to employee count page)
    @FXML
    public void employeecountmenubuttonAction(ActionEvent ev7) throws IOException {
        employeecountmenubtn.getScene().getWindow().hide();

        Stage empcount = new Stage();//Create a Stage
        empcount.setTitle("CITY OF GEMS - EMPLOYEE COUNT");//Set Title of interface
        //Setup the Scene
        Parent root6 = FXMLLoader.load(getClass().getResource("/employeemanagement/employeecount.fxml"));
        Scene scene5 = new Scene(root6);//Create a scene
        empcount.setScene(scene5);//Set Scene object on the Stage
        empcount.show();//Show the Stage which create above (makes the Stage visible and the exits)
        empcount.setResizable(false);//User cannot resize the frame;['
    }

    //Add Employee Button method
    @FXML
    public void addanemployeeAction(ActionEvent e) {
        //Establishing a Connection
        connection = handler.getConnection();

        //Get values and assign to declared variables
        String emp_name = firstname.getText() + " " + lastname.getText();
        String birth = String.valueOf(dateofbirth.getValue());
        String nic = String.valueOf(nicnumber.getText());
        String emp_address = enteraddress.getText();
        String emp_phone1 = String.valueOf(phonenumber1.getText());
        String emp_phone2 = String.valueOf(phonenumber2.getText());
        String emp_type = (String) employeetype.getValue();
        String emp_email = email.getText();

        //Validate data (If any data does not input to update form popup alert error box else Insert data into Database or clear inputs from form)
        if (emp_name.isEmpty() || birth.isEmpty() || nic.isEmpty() || emp_address.isEmpty() || emp_phone1.isEmpty() || emp_phone2.isEmpty() || emp_type.isEmpty() || emp_email.isEmpty()) {
            //Alert error box
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();//Shows the alert object and then blocks until alert is closed
        }else {
            //Saving Employee Details
            if (validateEmail() & validateHomePhone() & validateMobilePhone() & validateNICNumber()) {
                //SQL QUERY (INSERT)
                String insert = "INSERT INTO employeemanagement_table(empName,empDOB,empAddress,nic_number,empPhone1,empPhone2,empType,empEmail,empSalary)" + "VALUES(?,?,?,?,?,?,?,?,?)";

                //Establishing a Connection
                connection = handler.getConnection();
                try {
                    //Create a statement using connection object
                    pst = connection.prepareStatement(insert);
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }

                try {
                    //Get data from form inputs and Add data to PreparedStatement
                    pst.setString(1, (firstname.getText() + " " + lastname.getText()));
                    pst.setString(2, String.valueOf(dateofbirth.getValue()));
                    pst.setString(3, enteraddress.getText());
                    pst.setString(4, nicnumber.getText());
                    pst.setInt(5, Integer.parseInt(phonenumber1.getText()));
                    pst.setInt(6, Integer.parseInt(phonenumber2.getText()));
                    pst.setString(7, employeetype.getValue());
                    pst.setString(8, email.getText());
                    pst.setInt(9, Integer.parseInt(salary.getText()));

                    //Update Query (It will returns the number of affected rows)
                    pst.executeUpdate();

                    //Alert Information box
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added");
                    alert.show();
                } catch (SQLException ex2) {
                    ex2.printStackTrace();
                }

                //Clear Fields Method Called
                emp_details_clear();
            }

        }

    }

    //Clear Fields method
    @FXML
    private void emp_details_clear() {
        //Set all the form inputs to null
        firstname.setText(null);
        lastname.setText(null);
        dateofbirth.setValue(null);
        nicnumber.setText(null);
        enteraddress.setText(null);
        phonenumber1.setText(null);
        phonenumber2.setText(null);
        employeetype.setValue(null);
        email.setText(null);
        salary.setText(null);
    }

    //Email Validation
    private boolean validateEmail () {
        //Pattern Define (Return compiled version of a regular expression into the pattern)
        Pattern p1 = Pattern.compile("[a-zA-z0-9][a-zA-z0-9._]*@[a-zA-z0-9]+([.][a-zA-z]+)+");
        //Matching the above define pattern against regular expression
        Matcher m1 = p1.matcher(email.getText());
        //if email is like defined pattern email validate and create an add form else popup a warning
        if (m1.find() && m1.group().equals(email.getText())) {
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
    private boolean validateHomePhone () {
        //Pattern Define (Return compiled version of a regular expression into the pattern)
        Pattern p1 = Pattern.compile("(0|94)?(63|25|36|55|57|65|32|11|91|33|47|51|21|67|34|81|35|37|23|66|41|54|31|52|38|27|45|26|24)?[0-9]{7}");
        //Matching the above define pattern against regular expression
        Matcher m1 = p1.matcher(phonenumber1.getText());
        //if home phone number is like defined pattern phone number validate and create an add form else popup a warning
        if (m1.find() && m1.group().equals(phonenumber1.getText())) {
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
    private boolean validateMobilePhone () {
        //Pattern Define (Return compiled version of a regular expression into the pattern)
        Pattern p1 = Pattern.compile("(0|94)?(70|71|72|75|76|77|78)?[0-9]{7}");
        //Matching the above define pattern against regular expression
        Matcher m1 = p1.matcher(phonenumber2.getText());
        //if mobile phone number is like defined pattern mobile phone number validate and create an add form else popup a warning
        if (m1.find() && m1.group().equals(phonenumber2.getText())) {
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

    //NIC Number Validation
    private boolean validateNICNumber () {
        //Patterns Define (Return compiled version of a regular expression into the patterns)
        Pattern p1 = Pattern.compile("(9|8|7|6|5)?[0-9][001-365][001-999][0-9]*V");
        Pattern p2 = Pattern.compile("(9|8|7|6|5)?[0-9][001-365][001-999][0-9]*v");
        //Matching the above define patterns against regular expression
        Matcher m1 = p1.matcher(nicnumber.getText());
        Matcher m2 = p2.matcher(nicnumber.getText());
        //if NIC number is like defined pattern NIC number validate and create an add form else popup a warning
        if (m1.find() && m1.group().equals(nicnumber.getText()) || m2.find() && m2.group().equals(nicnumber.getText())) {
            return true;
        } else {
            //Alert Warning Box
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate NIC Number");
            alert.setHeaderText(null);
            alert.setContentText("Enter Valid NIC Number");
            alert.showAndWait();//Shows the alert object and then blocks until alert is closed

            return false;
        }
    }

}
