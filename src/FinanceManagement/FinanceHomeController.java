package FinanceManagement;



        import com.jfoenix.controls.JFXButton;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.image.Image;
        import javafx.stage.Stage;

        import javax.swing.text.html.ImageView;
        import java.net.URL;
        import java.util.ResourceBundle;

public class FinanceHomeController implements Initializable {



    @FXML
    private Button btnhomFinance;


    @FXML
    private JFXButton financeHomeHomebtn;

    @FXML
    private JFXButton financeHomeLogoutbtn;

    @FXML
    private JFXButton financeHomeIncomeExpensesBtn;

    @FXML
    private JFXButton financeHomeAssetsLiabilitiesBtn;

    @FXML
    private ImageView logo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void financeHomeHomeAction(ActionEvent event) {
        try {
            //switch to financeHome.fxml
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage finanHo = (Stage) financeHomeHomebtn.getScene().getWindow();
            finanHo.setTitle("Gem Merchant System");
            finanHo.setScene(new Scene(root, 1050, 780));
            finanHo.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    public void financeHomeLogoutAction(ActionEvent event)  {
        try {
            //switch to financeHome.fxml
            Parent root = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
            Stage finanLo = (Stage) financeHomeLogoutbtn.getScene().getWindow();
            finanLo.setTitle("Gem Merchant System");
            finanLo.setScene(new Scene(root, 1050, 780));
            finanLo.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }



    @FXML
    public void financeHomeIncomeExpensesOnAction(ActionEvent event){
        try {
            //switch to financeHome.fxml
            Parent root = FXMLLoader.load(getClass().getResource("incomeExpenses.fxml"));
            Stage finanInEx = (Stage) financeHomeIncomeExpensesBtn.getScene().getWindow();
            finanInEx.setTitle("Gem Merchant System");
            finanInEx.setScene(new Scene(root, 1050, 780));
            finanInEx.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    public void financeHomeAssetsLiabilitiesOnAction(ActionEvent event){
        try {
            //switch to financeHome.fxml
            Parent root = FXMLLoader.load(getClass().getResource("assetsLiabilities.fxml"));
            Stage finanAsLi = (Stage) financeHomeAssetsLiabilitiesBtn.getScene().getWindow();
            finanAsLi.setTitle("Gem Merchant System");
            finanAsLi.setScene(new Scene(root, 1050, 780));
            finanAsLi.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


}
