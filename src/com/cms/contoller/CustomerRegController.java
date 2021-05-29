package com.cms.contoller;

import com.cms.config.CustomerData;
import com.cms.dbcontroller.CustomerDbController;
import com.cms.modle.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;




public class CustomerRegController implements Initializable {


    @FXML
    private Button btn_submit;

    @FXML
    private TextField txt_first_name,txt_last_name,txt_email,txt_address,txt_contact_no;

    @FXML
    private DatePicker txt_registered_date;



    public void register(ActionEvent event){
        Customer customer = new Customer();

        //To get the values from the textfields and set them
        customer.setFristName(txt_first_name.getText());
        customer.setLastName(txt_last_name.getText());
        customer.setEmail(txt_email.getText());
        customer.setAddress(txt_address.getText());
        customer.setContactNo(txt_contact_no.getText());
        LocalDate localDate = txt_registered_date.getValue() == null ? LocalDate.now() : txt_registered_date.getValue();
        customer.setRegisteredDate(localDate.toString());

        String validationMsg = CustomerData.validation(customer);

        if(validationMsg.equals(CustomerData.VALID)){
            CustomerDbController customerDbController = new CustomerDbController();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(customerDbController.save(customer));
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(validationMsg);
            alert.showAndWait();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txt_registered_date.setValue(LocalDate.now());
    }
}
