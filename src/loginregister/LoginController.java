package loginregister;

import com.jfoenix.controls.*;
import dbconnection.DBHandler;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private JFXButton signup;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton forgotpassword;

    //declare variables
    private DBHandler handler;//DBHandler is the connection class
    private Connection connection;
    private PreparedStatement pst;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setStyle("-fx-color-label-visible: #a0a2ab;");
        password.setStyle("-fx-text-innner-color: #a0a2ab;");

        //Initialize DBHandler class
        handler = new DBHandler();
    }

    //Login Method
    @FXML
    public void loginAction(ActionEvent e) {
        //loading
        PauseTransition pt = new PauseTransition();//Create PauseTransition object
        pt.setDuration(Duration.seconds(3));//Wait 3 seconds
        pt.setOnFinished(ev -> {
            //Retrieve Data from Database
            //Establishing a Connection
            connection = handler.getConnection();

            //SQL QUERY (SELECT) String
            String q1 = "SELECT * from signup_table where username=? and password=?";

            try {
                //Create a statement using connection object
                pst = connection.prepareStatement(q1);

                //Get data from TextField inputs and Add data to preparedstatement
                pst.setString(1, username.getText());
                pst.setString(2, password.getText());

                //Update Query (It will returns the number of affected rows)
                ResultSet rs = pst.executeQuery();

                int count = 0;//declare a variable

                //Process the ResultSet object
                while (rs.next()) {
                    count = count + 1;//Increment variable
                }

                //Validate data (If give correct username and password then direct to dashboard page else popup alert error box)
                if (count == 1) {
                    login.getScene().getWindow().hide();

                    Stage dashboard = new Stage();
                    dashboard.setTitle("CITY OF GEMS - DASHBOARD");//Set Title of interface
                    try {
                        Parent dash_root = FXMLLoader.load(getClass().getResource("/sample/dashboard.fxml"));
                        Scene dash_scene = new Scene(dash_root);
                        dashboard.setScene(dash_scene);
                        dashboard.show();
                        dashboard.setResizable(false);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else {
                    //Alert error box
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Username and Password is not Correct");
                    alert.show();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }

            finally {
                try {
                    connection.close();//Close the database connection
                } catch (SQLException ex2) {
                    ex2.printStackTrace();
                }
            }
        });
        pt.play();//Starts the Transition

    }

    //SignUp Method (Direct to signup page)
    @FXML
    public void signUp(ActionEvent e1) throws IOException {
        login.getScene().getWindow().hide();

        Stage signup = new Stage();//Create a Stage
        signup.setTitle("CITY OF GEMS - SIGNUP");//Set Title of interface
        //Setup the Scene
        Parent root = FXMLLoader.load(getClass().getResource("/loginregister/signup.fxml"));
        Scene scene = new Scene(root);//Create a scene
        signup.setScene(scene);//Set Scene object on the Stage
        signup.show();//Show the Stage which create above (makes the Stage visible and the exits)
        signup.setResizable(false);//User cannot resize the frame
    }

}
