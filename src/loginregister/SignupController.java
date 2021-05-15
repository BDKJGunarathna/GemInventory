package loginregister;

import com.jfoenix.controls.*;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import dbconnection.DBHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupController implements Initializable {

    @FXML
    private AnchorPane parentPane;

    @FXML
    private JFXButton login;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXButton signup;

    @FXML
    private JFXRadioButton male;

    @FXML
    private ToggleGroup gender1;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton female;

    @FXML
    private JFXRadioButton other;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField phone;

    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        name.setStyle("-fx-text-inner-color: #a0a2ab");
        password.setStyle("-fx-text-inner-color: #a0a2ab");
        email.setStyle("-fx-text-inner-color: #a0a2ab");

        handler = new DBHandler();
    }

    @FXML
    public void signupAction(ActionEvent ev1) {
        //loading
        PauseTransition pt1 = new PauseTransition();
        pt1.setDuration(Duration.seconds(3));
        pt1.setOnFinished(ev -> {

        });
        pt1.play();

        //Establishing a Connection
        connection = handler.getConnection();

        //Get values and assign to declared variables
        String user_name = name.getText();
        String pass = password.getText();
        String user_email = email.getText();
        String phone_number = String.valueOf(phone.getText());

        //Validate data (If any data does not input to update form popup alert error box else Insert data into Database
        if (user_name.isEmpty() || pass.isEmpty() || user_email.isEmpty() || phone_number.isEmpty()) {
            //Alert error box
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();//Shows the alert object and then blocks until alert is closed
        } else {
            //saving Data
            if (validateEmail() & validatePhone()) {
                //SQL QUERY (INSERT)
                String insert = "INSERT INTO signup_table(username,password,gender,email,phone_no)" + "VALUES (?,?,?,?,?)";

                //Establishing a Connection
                connection = handler.getConnection();
                try {
                    //Create a statement using connection object
                    pst = connection.prepareStatement(insert);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                try {
                    //Get data from form inputs and Add data to PreparedStatement
                    pst.setString(1, name.getText());
                    pst.setString(2, password.getText());
                    pst.setString(3, getGender());
                    pst.setString(4, email.getText());
                    pst.setInt(5, Integer.parseInt(phone.getText()));

                    //Update Query (It will returns the number of affected rows)
                    pst.executeUpdate();

                    //Alert Information box
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully SignUp");
                    alert.show();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    //SignUp Method (Direct to signup page)
    @FXML
    public void loginAction(ActionEvent ev2) throws IOException {
        signup.getScene().getWindow().hide();

        Stage login = new Stage();//Create a Stage
        Parent root1 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene scene1 = new Scene(root1);//Create a scene
        login.setScene(scene1);//Set Scene object on the Stage
        login.show();//Show the Stage which create above (makes the Stage visible and the exits)
        login.setResizable(false);//User cannot resize the frame
    }

    //gender selection method
    public String getGender() {
        String gen = "";//declare and assign variable

        //if toggle button select male variable assign Male else if toggle button select female variable assign Female else if toggle button select other variable assign Others
        if (male.isSelected()) {
            gen = "Male";
        } else if (female.isSelected()) {
            gen = "Female";
        } else if (other.isSelected()) {
            gen = "Other";
        }

        return gen;
    }

    //Email Validation
    private boolean validateEmail () {
        //Pattern Define (Return compiled version of a regular expression into the pattern)
        Pattern p = Pattern.compile("[a-zA-z0-9][a-zA-z0-9._]*@[a-zA-z0-9]+([.][a-zA-z]+)+");
        //Matching the above define pattern against regular expression
        Matcher m = p.matcher(email.getText());
        //if email is like defined pattern email validate and create a user account else popup a warning
        if (m.find() && m.group().equals(email.getText())) {
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

    //Phone Number validation
    private boolean validatePhone () {
        //Pattern Define (Return compiled version of a regular expression into the pattern)
        Pattern p = Pattern.compile("(0|94)?[7][0-9]{8}");
        //Matching the above define pattern against regular expression
        Matcher m = p.matcher(phone.getText());
        //if phone number is like defined pattern phone number validate and create a user account else popup a warning
        if (m.find() && m.group().equals(phone.getText())) {
            return true;
        } else {
            //Alert Warning box
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Phone Number");
            alert.setHeaderText(null);
            alert.setContentText("Enter Valid Phone Number");
            alert.showAndWait();//Shows the alert object and then blocks until alert is closed

            return false;
        }
    }

}
