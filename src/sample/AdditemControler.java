package sample;
import sample.dbconnect;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdditemControler implements Initializable {

    @FXML
    private Button btnclose;

    @FXML
    private Button btnAdditm;

    @FXML
    private Button btndemo;

    @FXML
    private TextField txtgemId;

    @FXML
    private TextField txtgemwt;

    @FXML
    private TextField txtgemshp;

    @FXML
    private TextField txtgemdm;

    @FXML
    private TextField txtgemqty;

    @FXML
    private TextField txtgemreorder;

    @FXML
    private TextField txtgempr;

    @FXML
    private TextArea txtgemdesc;



    Connection con;
    PreparedStatement st;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    //close button navigates to home page
    @FXML
    public void close(ActionEvent ae1)throws IOException {
        btnclose.getScene().getWindow().hide();
        Stage close=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene1=new Scene(root);
        close.setScene(scene1);
        close.show();
        close.setResizable(false);
    }


    //database connection
    public void Connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cityofgems","root","root123");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

   @FXML
    public void addnewgem(ActionEvent ae2) {
       Connect();
       //assigning user inputs from text views  to variables
       String gemId = txtgemId.getText();
       String description = txtgemdesc.getText();
       String weight = txtgemwt.getText();
       String shape = txtgemshp.getText();
       String dimension = txtgemdm.getText();
       String price = txtgempr.getText();
       String quantity = txtgemqty.getText();
       String reorder = txtgemreorder.getText();

    //validations
       if(gemId.isEmpty() || description.isEmpty() || weight.isEmpty() || shape.isEmpty() || dimension.isEmpty() || price.isEmpty() || quantity.isEmpty() || reorder.isEmpty()){
           Alert alertInsert=new Alert(Alert.AlertType.ERROR);
           alertInsert.setHeaderText(null);
           alertInsert.setContentText("Please fill all data");
           alertInsert.show();
       }else {


           try {
               st = con.prepareStatement("INSERT INTO inventory(gemId,description,weight,shape,dimension,unitsInStock,reorderLevel,cost)values (?,?,?,?,?,?,?,?)");
               st.setString(1, gemId);
               st.setString(2, description);
               st.setString(3, weight);
               st.setString(4, shape);
               st.setString(5, dimension);
               st.setString(6, quantity);//edited
               st.setString(7, reorder);//edited
               st.setString(8, price);

               int status = st.executeUpdate();


               if (status == 1) {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Success");
                   alert.setContentText("Item Added Successfully");
                   alert.showAndWait();

                   txtgemId.setText("");
                   txtgemdesc.setText("");
                   txtgemwt.setText("");
                   txtgemshp.setText("");
                   txtgemdm.setText("");
                   txtgemqty.setText("");
                   txtgemreorder.setText("");
                   txtgempr.setText("");
               } else {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Fail");
                   alert.setContentText("Error occurred");
                   alert.showAndWait();

                   txtgemId.setText("");
                   txtgemdesc.setText("");
                   txtgemwt.setText("");
                   txtgemshp.setText("");
                   txtgemdm.setText("");
                   txtgemqty.setText("");
                   txtgemreorder.setText("");
                   txtgempr.setText("");
               }

           } catch (SQLException throwables) {
               throwables.printStackTrace();
           }
       }
       }
        //demo button action
       public void demodata(){

        Connect();
        String gemid="gem12";
        String description="AMETHYST 16.87 CTS 15935";
        String weight="16.87";
        String shape="Cushion";
        String dimensions="6 x 15.6 mm";
        String price="50,000";
        String quantity="3";
        String reorder="1";

           txtgemId.setText(gemid);
           txtgemdesc.setText(description);
           txtgemwt.setText(weight);
           txtgemshp.setText(shape);
           txtgemdm.setText(dimensions);
           txtgempr.setText(price);
           txtgemqty.setText(quantity);
           txtgemreorder.setText(reorder);

       }


   }

