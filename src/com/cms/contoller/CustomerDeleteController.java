package com.cms.contoller;

import com.cms.config.CustomerData;
import com.cms.dbcontroller.CustomerDbController;
import com.cms.modle.Customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;



public class CustomerDeleteController implements Initializable {

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
    void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(customerDbController.remove(CustomerData.id));
        //To show the alert message
        alert.showAndWait();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Customer customer = customerDbController.findById(CustomerData.id);

        //To show the values
        cust_id.setText(customer.getCustId().toString());
        cust_name.setText(customer.getFristName());
        last_name.setText(customer.getLastName());
        cust_email.setText(customer.getEmail());
        address.setText(customer.getAddress());
        cust_contact.setText(customer.getContactNo());
        cust_reg_date.setText(customer.getRegisteredDate());

    }
}
