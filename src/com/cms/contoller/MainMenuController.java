package com.cms.contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class MainMenuController {

    @FXML
    private Button btn_register, btn_view_details, btn_search;




    public void menuSelect(ActionEvent event) throws Exception{
        if(event.getSource() == btn_register){
            //To load the fxml file
            Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("com/cms/ui/customer_registration.fxml"));
            Stage stage = new Stage();
            //To set the title
            stage.setTitle("Register Customer");
            stage.setScene(new Scene(pane));
            stage.show();
        }
        else if(event.getSource() == btn_view_details){
            //To load the fxml file
            Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("com/cms/ui/customer_detail.fxml"));
            Stage stage = new Stage();
            //To set the title
            stage.setTitle("Customers' Details");
            stage.setScene(new Scene(pane));
            stage.show();
        }
        else if(event.getSource() == btn_search){
            //To load the fxml file
            Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("com/cms/ui/customer_search.fxml"));
            Stage stage = new Stage();
            //To set the title
            stage.setTitle("Search Customer");
            stage.setScene(new Scene(pane));
            stage.show();
        }
    }
}
