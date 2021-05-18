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

public class deleteControler implements Initializable {

    @FXML
    private TextField deleteGemId;

    @FXML
    private TextField  deleteGemWeight;

    @FXML
    private TextField  deleteGemShape;

    @FXML
    private TextField  deleteGemDimension;

    @FXML
    private TextField  deleteGemQuantity;

    @FXML
    private TextArea  deleteGemDescription;

    @FXML
    private Button btnDeleteClose;

    @FXML
    private Button btnDelete;

    Connection con;
    PreparedStatement st;
    @FXML
    public void close(ActionEvent ae1)throws IOException {
        btnDeleteClose.getScene().getWindow().hide();
        Stage close=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene1=new Scene(root);
        close.setScene(scene1);
        close.show();
        close.setResizable(false);
    }

    public void deleteRecord(ActionEvent ae2)throws ClassNotFoundException,SQLException{
        try {
            String query = "DELETE FROM inventory WHERE gemId='" + deleteGemId.getText() + "'";
            st = con.prepareStatement(query);
            st.executeUpdate();
            Connection connection = st.getConnection();
            st = connection.prepareStatement(query);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deleted");
            alert.setContentText("Item Deleted  Successfully");
            alert.showAndWait();
        }
        catch (SQLException e){
            System.out.println("Error occurred while deleting the record");
            e.printStackTrace();
        }
    }

    public void Connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cityofgems","root","root123");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    Connect();
    }
}

