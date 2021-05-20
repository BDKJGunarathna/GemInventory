package com.cms.contoller;

import com.cms.config.CustomerData;
import com.cms.dbcontroller.CustomerDbController;
import com.cms.modle.Customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;



public class CustomerViewController implements Initializable {

    @FXML
    private Label cust_id;

    @FXML
    private Label cust_name;

    @FXML
    private Label cust_email;

    @FXML
    private Label address;

    @FXML
    private Label cust_contact;

    @FXML
    private Label cust_reg_date;

    @FXML
    private Button btn_del;

    @FXML
    private Label last_name;


    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    CustomerDbController customerDbController = new CustomerDbController();



    @FXML
    public void update(ActionEvent event){
        try {
            CustomerData.id = Integer.parseInt(cust_id.getText());
            //To load the fxml file
            Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("com/cms/ui/customer_update.fxml"));
            Stage stage = new Stage();
            //To set the title
            stage.setTitle("Update Customer Details ");
            stage.setScene(new Scene(pane));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void delete(ActionEvent event) {
        try {
            CustomerData.id = Integer.parseInt(cust_id.getText());
            //To load the fxml file
            Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("com/cms/ui/customer_delete.fxml"));
            Stage stage = new Stage();
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
        Customer customer = customerDbController.findById(CustomerData.id);

        //To display the values
        cust_id.setText(customer.getCustId().toString());
        cust_name.setText(customer.getFristName());
        last_name.setText(customer.getLastName());
        cust_email.setText(customer.getEmail());
        address.setText(customer.getAddress());
        cust_contact.setText(customer.getContactNo());
        cust_reg_date.setText(customer.getRegisteredDate());
    }
}
