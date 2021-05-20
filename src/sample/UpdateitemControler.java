package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class UpdateitemControler implements Initializable {

    @FXML
    private TextField upgemid;

    @FXML
    private TextField upgemweight;

    @FXML
    private TextField upgemshp;

    @FXML
    private TextField upgemdim;

    @FXML
    private TextField upgemqty;

    @FXML
    private TextArea upgemdesc;

    @FXML
    private Button btnupclose;

    @FXML
    private Button btnup;
    @FXML
    private Button btndemo;

    Connection con;
    PreparedStatement st;

    ObservableList<Gems>gemList= FXCollections.observableArrayList();

    public void handleButtonAction(ActionEvent event) throws SQLException {
        if(event.getSource()==btnup){

        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connect();

    }
    @FXML
    public void close(ActionEvent ae1)throws IOException {
        btnupclose.getScene().getWindow().hide();
        Stage close=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene1=new Scene(root);
        close.setScene(scene1);
        close.show();
        close.setResizable(false);
    }

    @FXML
    public void updateRecord(ActionEvent ae2) throws SQLException {
       String query="UPDATE inventory SET description=?,weight=?,shape=?,dimension=?,unitsInStock=? WHERE gemId='"+upgemid.getText()+"'";

        st=con.prepareStatement(query);

        st.setString(1,upgemdesc.getText());
        st.setString(2,upgemweight.getText());
        st.setString(3,upgemshp.getText());
        st.setString(4,upgemdim.getText());
        st.setString(5,upgemqty.getText());//edited

        String gemDesc=upgemdesc.getText();
        String gemWeight=upgemweight.getText();
        String gemShape=upgemshp.getText();
        String gemDimension=upgemdim.getText();
        String gemQuantity=upgemqty.getText();


        int status = st.executeUpdate() ;

        Connection connection=st.getConnection();

        st=connection.prepareStatement(query);
        if(status==1){

            Alert updateAlert=new Alert(Alert.AlertType.INFORMATION);
            updateAlert.setHeaderText(null);
            updateAlert.setContentText("Successfully updated");
            updateAlert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fail");
            alert.setContentText("Error occurred while updating");
            alert.showAndWait();
        }



    }
    public void excecuteQuery(String query) throws SQLException {
        Connection con= st.getConnection();
        Statement st;




    }
    public void Connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cityofgems","root","root123");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void demodataupdate(){

        Connect();
        String gemid="gem2";
        String description="RUBY RED";
        String weight="0.86";
        String shape="Pear";
        String dimensions="6 x 15.6 mm";
        String quantity="3";

        upgemid.setText(gemid);
        upgemdesc.setText(description);
       upgemweight.setText(weight);
       upgemshp.setText(shape);
        upgemdim.setText(dimensions);
        upgemqty.setText(quantity);


    }
}

