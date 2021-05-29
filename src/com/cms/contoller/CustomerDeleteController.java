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
    private Label cus_id;

    @FXML
    private Label cus_first_name;

    @FXML
    private Label cus_email;

    @FXML
    private Label cus_address;

    @FXML
    private Label cus_contact_no;

    @FXML
    private Label cus_registered_date;

    @FXML
    private Button btn_del;

    @FXML
    private Label cus_last_name;



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
        cus_id.setText(customer.getCustId().toString());
        cus_first_name.setText(customer.getFristName());
        cus_last_name.setText(customer.getLastName());
        cus_email.setText(customer.getEmail());
        cus_address.setText(customer.getAddress());
        cus_contact_no.setText(customer.getContactNo());
        cus_registered_date.setText(customer.getRegisteredDate());

    }
}
