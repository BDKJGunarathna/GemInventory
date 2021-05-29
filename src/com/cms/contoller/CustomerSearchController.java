package com.cms.contoller;

import com.cms.config.CustomerData;
import com.cms.dbcontroller.CustomerDbController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class CustomerSearchController {

    @FXML
    private TextField cust_id;

    @FXML
    private TextField first_name;



    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    CustomerDbController customerDbController = new CustomerDbController();


    @FXML
    void search(ActionEvent event) {
        Integer id = 0;

        System.out.println("Search");
        System.out.println("id   : " +cust_id.getText());
        System.out.println("nsme : " +first_name.getText());

        if(cust_id.getText().isEmpty()){
            //If the user did not input the ID, then the ID will be assigned to -1
            id = -1;
        }
        else{
            //To assign the ID to the user entered value
            id = Integer.parseInt(cust_id.getText());
        }

        try {
            id = customerDbController.findByIdAndName( id , first_name.getText());

            if(id > 0){
                CustomerData.id = id;
                //To load the fxml file
                Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("com/cms/ui/customer_view.fxml"));
                Stage stage = new Stage();
                //To set the title
                stage.setTitle("Customer Profile ");
                stage.setScene(new Scene(pane));
                stage.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("No record found!");
                alert.showAndWait();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}