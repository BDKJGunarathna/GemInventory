package com.cms.contoller;

import com.cms.config.CustomerData;
import com.cms.config.DBConnection;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;




public class CustomerRegController implements Initializable {


    @FXML
    private Button btn_submit;

    @FXML
    private TextField tx_email,tx_address,tx_cnct_no,tx_f_name, tx_l_name;

    @FXML
    private DatePicker tx_date;



    public void register(ActionEvent event){
        Customer customer = new Customer();

        //To get the values from the textfields and set them
        customer.setFristName(tx_f_name.getText());
        customer.setLastName(tx_l_name.getText());
        customer.setEmail(tx_email.getText());
        customer.setAddress(tx_address.getText());
        customer.setContactNo(tx_cnct_no.getText());
        LocalDate localDate = tx_date.getValue() == null ? LocalDate.now() : tx_date.getValue();
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
        tx_date.setValue(LocalDate.now());
    }
}
