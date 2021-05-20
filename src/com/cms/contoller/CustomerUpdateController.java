package com.cms.contoller;

import com.cms.config.CustomerData;
import com.cms.dbcontroller.CustomerDbController;
import com.cms.modle.Customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;



public class CustomerUpdateController implements Initializable {

    @FXML
    private TextField cust_id;

    @FXML
    private TextField name;

    @FXML
    private TextField l_name;

    @FXML
    private TextField email;

    @FXML
    private TextField addres;

    @FXML
    private TextField contact;

    @FXML
    private DatePicker r_date;


    CustomerDbController customerDbController = new CustomerDbController();



    @FXML
    void update(ActionEvent event) {
        Customer customer = new Customer();

        //To get the values from the textfields and set them
        customer.setCustId(Integer.parseInt(cust_id.getText()));
        customer.setFristName(name.getText());
        customer.setEmail(email.getText());
        customer.setAddress(addres.getText());
        customer.setContactNo(contact.getText());
        customer.setLastName(l_name.getText());
        LocalDate localDate = r_date.getValue();
        customer.setRegisteredDate(localDate.toString());

        String validationMsg = CustomerData.validation(customer);

        if(validationMsg.equals(CustomerData.VALID)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(customerDbController.update(customer));
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(validationMsg);
            alert.showAndWait();
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomerDbController customerDbController = new CustomerDbController();

        Customer customer = customerDbController.findById(CustomerData.id);

        //To get the details and set them in the textfields
        cust_id.setText(customer.getCustId().toString());
        cust_id.setDisable(true);
        name.setText(customer.getFristName());
        l_name.setText(customer.getLastName());
        email.setText(customer.getEmail());
        addres.setText(customer.getAddress());
        contact.setText(customer.getContactNo());
        r_date.setValue(LOCAL_DATE(customer.getRegisteredDate()));
    }

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
