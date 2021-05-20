package com.cms.contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class MainMenuController {

    @FXML
    private Button btn_reg, btn_view, btn_srch;




    public void menuSelect(ActionEvent event) throws Exception{
        if(event.getSource() == btn_reg){
            //To load the fxml file
            Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("com/cms/ui/customer_registration.fxml"));
            Stage stage = new Stage();
            //To set the title
            stage.setTitle("Register Customer");
            stage.setScene(new Scene(pane));
            stage.show();
        }
        else if(event.getSource() == btn_view){
            //To load the fxml file
            Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("com/cms/ui/customer_detail.fxml"));
            Stage stage = new Stage();
            //To set the title
            stage.setTitle("Customers' Details");
            stage.setScene(new Scene(pane));
            stage.show();
        }
        else if(event.getSource() == btn_srch){
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
