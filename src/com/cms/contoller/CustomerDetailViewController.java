package com.cms.contoller;

import com.cms.config.CustomerData;
import com.cms.config.DBConnection;
import com.cms.dbcontroller.CustomerDbController;
import com.cms.modle.*;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;



public class CustomerDetailViewController implements Initializable {

    @FXML
    private TableView table_cust;

    @FXML
    private TableColumn<Customer, Initializable> custId;

    @FXML
    private TableColumn<Customer, String> name;

    @FXML
    private TableColumn<?, ?> l_name;

    @FXML
    private TableColumn<?, ?> email;

    @FXML
    private TableColumn<?, ?> address;

    @FXML
    private TableColumn<?, ?> contactno;

    @FXML
    private TableColumn<?, ?> regdate;

    @FXML
    private TableColumn<?, Button> action;

    @FXML
    private TableColumn<?, Button> delete;



    @FXML
    public void generate(ActionEvent event){
        try {
            Connection con = DBConnection.getConnection();

            //To open the connection to the actual file
            InputStream in = new FileInputStream(new File(""));
            //To load into JasperDesign
            JasperDesign jd = JRXmlLoader.load(in);
            //To compile the jasper report
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap para = new HashMap();
            //To fill the report
            JasperPrint j = JasperFillManager.fillReport(jr,para, con);
            //To view the report
            JasperViewer.viewReport(j,false);
            con.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



    public void update(ActionEvent event){
        try {
            Button pressed = (Button)event.getSource();
            CustomerData.id = Integer.parseInt(pressed.getId());
            //To load the fxml file
            Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("com/cms/ui/customer_update.fxml"));
            Stage stage = new Stage();
            pane.setId(pressed.getId());
            //To set the title
            stage.setTitle("Update Customer Details ");
            stage.setScene(new Scene(pane));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



    public void delete(ActionEvent event) {

        try {
            Button pressed = (Button)event.getSource();
            CustomerData.id = Integer.parseInt(pressed.getId());
            //To load the fxml file
            Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("com/cms/ui/customer_delete.fxml"));
            Stage stage = new Stage();
            pane.setId(pressed.getId());
            //To set the title
            stage.setTitle("Delete Customer Account ");
            stage.setScene(new Scene(pane));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        custId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        name.setCellValueFactory(new PropertyValueFactory<>("fristName"));
        l_name.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        contactno.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        regdate.setCellValueFactory(new PropertyValueFactory<>("registeredDate"));
        action.setCellValueFactory(new PropertyValueFactory<>("update"));
        delete.setCellValueFactory(new PropertyValueFactory<>("delete"));




        CustomerDbController customerDbController = new CustomerDbController();
        //To set the values
        table_cust.setItems(FXCollections.observableArrayList(customerDbController.findAll()));

    }
}
