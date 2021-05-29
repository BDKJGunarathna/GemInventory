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
    private TextField cus_id;

    @FXML
    private TextField first_name;

    @FXML
    private TextField last_name;

    @FXML
    private TextField email;

    @FXML
    private TextField address;

    @FXML
    private TextField contact_no;

    @FXML
    private DatePicker reg_date;


    CustomerDbController customerDbController = new CustomerDbController();



    @FXML
    void update(ActionEvent event) {
        Customer customer = new Customer();

        //To get the values from the textfields and set them
        customer.setCustId(Integer.parseInt(cus_id.getText()));
        customer.setFristName(first_name.getText());
        customer.setLastName(last_name.getText());
        customer.setEmail(email.getText());
        customer.setAddress(address.getText());
        customer.setContactNo(contact_no.getText());
        LocalDate localDate = reg_date.getValue();
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
        cus_id.setText(customer.getCustId().toString());
        cus_id.setDisable(true);
        first_name.setText(customer.getFristName());
        last_name.setText(customer.getLastName());
        email.setText(customer.getEmail());
        address.setText(customer.getAddress());
        contact_no.setText(customer.getContactNo());
        reg_date.setValue(LOCAL_DATE(customer.getRegisteredDate()));
    }

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
