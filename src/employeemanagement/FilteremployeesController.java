package employeemanagement;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FilteremployeesController implements Initializable {

    @FXML
    private JFXButton filtaddemployeemenubtn;

    @FXML
    private JFXButton filtviewemployeemenubtn;

    @FXML
    private JFXButton filtemployeecountmenubtn;

    @FXML
    private JFXButton filteremplogout;

    @FXML
    private JFXButton filteremphome;

    @FXML
    private JFXButton viewall;

    @FXML
    private JFXButton suspendemp;

    @FXML
    private JFXButton activeemp;

    @FXML
    private JFXButton ceo;

    @FXML
    private JFXButton salesmanagers;

    @FXML
    private JFXButton supmanagers;

    @FXML
    private JFXButton admins;

    @FXML
    private JFXButton accountassistants;

    @FXML
    private JFXButton officeassistants;

    @FXML
    private JFXButton qualitycheckers;

    @FXML
    private JFXButton inventormanagers;

    @FXML
    private JFXButton dismanagers;

    @FXML
    private JFXButton salesreps;

    @FXML
    private JFXButton storesupervisors;

    @FXML
    private JFXButton gemologists;

    @FXML
    private JFXButton clerks;

    @FXML
    private JFXButton assistantmanagers;

    @FXML
    private JFXButton accountants;

    @FXML
    private JFXButton marketingexecutives;

    @FXML
    private JFXButton adminassistants;

    @FXML
    private JFXButton laborers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //Logout Method(Direct to login page)
    @FXML
    public void filtlogoutAction(ActionEvent filtev3) throws IOException {
        filteremplogout.getScene().getWindow().hide();

        Stage filtlogout = new Stage();//Create a Stage
        filtlogout.setTitle("CITY OF GEMS - LOGIN");//Set Title of interface
        //Setup the Scene
        Parent filtroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene filtscene2 = new Scene(filtroot2);//Create a scene
        filtlogout.setScene(filtscene2);//Set Scene object on the Stage
        filtlogout.show();//Show the Stage which create above (makes the Stage visible and the exits)
        filtlogout.setResizable(false);//User cannot resize the frame
    }

    //Home Page (Direct to Employee Management Home Page)
    @FXML
    public void flithomeAction(ActionEvent emphome8) throws  IOException {
        filteremphome.getScene().getWindow().hide();

        Stage filtemphome = new Stage();//Create a Stage
        filtemphome.setTitle("CITY OF GEMS - EMPLOYEE MANAGEMENT HOME");//Set Title of interface
        //Setup the Scene
        Parent filtemproot8 = FXMLLoader.load(getClass().getResource("/employeemanagement/employeemanagementhome.fxml"));
        Scene filtempscene8 = new Scene(filtemproot8);//Create a scene
        filtemphome.setScene(filtempscene8);//Set Scene object on the Stage
        filtemphome.show();//Show the Stage which create above (makes the Stage visible and the exits)
        filtemphome.setResizable(false);
    }

    //Add Employee Button method (Direct to add employee page)
    @FXML
    public void filtaddemployeemenubuttonAction(ActionEvent filtev4) throws IOException {
        filtaddemployeemenubtn.getScene().getWindow().hide();

        Stage filtaddmenu = new Stage();//Create a Stage
        filtaddmenu.setTitle("CITY OF GEMS - ADD EMPLOYEE");//Set Title of interface
        //Setup the Scene
        Parent filtroot3 = FXMLLoader.load(getClass().getResource("/employeemanagement/addemployee.fxml"));
        Scene filtscene3 = new Scene(filtroot3);//Create a scene
        filtaddmenu.setScene(filtscene3);//Set Scene object on the Stage
        filtaddmenu.show();//Show the Stage which create above (makes the Stage visible and the exits)
        filtaddmenu.setResizable(false);//User cannot resize the frame
    }

    //View Employee Button method (Direct to filter employee page)
    @FXML
    public void filtviewemployeemenubuttonAction(ActionEvent ev6) throws IOException {
        filtviewemployeemenubtn.getScene().getWindow().hide();

        Stage filtviewemp = new Stage();//Create a Stage
        filtviewemp.setTitle("CITY OF GEMS - FILTERED EMPLOYEE REPORTS");//Set Title of interface
        //Setup the Scene
        Parent filtroot5 = FXMLLoader.load(getClass().getResource("/employeemanagement/filteremployees.fxml"));
        Scene filtscene4 = new Scene(filtroot5);//Create a scene
        filtviewemp.setScene(filtscene4);//Set Scene object on the Stage
        filtviewemp.show();//Show the Stage which create above (makes the Stage visible and the exits)
        filtviewemp.setResizable(false);//User cannot resize the frame
    }

    //Employee Count Button method (Direct to employee count page)
    @FXML
    public void filtemployeecountmenubuttonAction(ActionEvent filtev7) throws IOException {
        filtemployeecountmenubtn.getScene().getWindow().hide();

        Stage filtempcount = new Stage();//Create a Stage
        filtempcount.setTitle("CITY OF GEMS - EMPLOYEE COUNT");//Set Title of interface
        //Setup the Scene
        Parent filtroot6 = FXMLLoader.load(getClass().getResource("/employeemanagement/employeecount.fxml"));
        Scene filtscene5 = new Scene(filtroot6);//Create a scene
        filtempcount.setScene(filtscene5);//Set Scene object on the Stage
        filtempcount.show();//Show the Stage which create above (makes the Stage visible and the exits)
        filtempcount.setResizable(false);//User cannot resize the frame
    }

    //View All Button Method (Direct to view employee details page)
    @FXML
    public void viewallAction(ActionEvent filtev8) throws IOException {
        viewall.getScene().getWindow().hide();

        Stage filtviewall = new Stage();//Create a Stage
        filtviewall.setTitle("CITY OF GEMS - EMPLOYEE DETAILS");//Set Title of interface
        //Setup the Scene
        Parent filtroot7 = FXMLLoader.load(getClass().getResource("/employeemanagement/viewemployeedetails.fxml"));
        Scene filtscene6 = new Scene(filtroot7);//Create a scene
        filtviewall.setScene(filtscene6);//Set Scene object on the Stage
        filtviewall.show();//Show the Stage which create above (makes the Stage visible and the exits)
        filtviewall.setResizable(false);//User cannot resize the frame
    }

    //Generate Suspended Employee List Report
    @FXML
    private void printSuspendedEmployeeList(ActionEvent event) throws SQLException {
        //Create Generate Suspended Employee List Object
        Suspended_Employee_List_Report suspended_employee_list_report = new Suspended_Employee_List_Report();
        //Create Suspended Employee List PDF
        suspended_employee_list_report.createPdf();
    }

    //Generate Active Employee List Report
    @FXML
    private void printActiveEmployeeList(ActionEvent event) throws SQLException {
        //Create Generate Active Employee List Object
        Active_Employee_List_Report active_employee_list_report = new Active_Employee_List_Report();
        //Create Suspended Active List PDF
        active_employee_list_report.createPdf();
    }

    //Generate CEO Details Report
    @FXML
    private void printCEODetails(ActionEvent event) throws SQLException {
        //Create CEO Details Object
        CEODetails ceoDetails = new CEODetails();
        //Create CEO Details PDF
        ceoDetails.createPdf();
    }

    //Generate Sales Managers Details Report
    @FXML
    private void printSalesManagersDetails(ActionEvent event) throws SQLException {
        //Create Sales Managers Details Object
        Sales_Managers_Report sales_managers_report = new Sales_Managers_Report();
        //Create Sales Managers Details PDF
        sales_managers_report.createPdf();
    }

    //Generate Inventory Managers Details Report
    @FXML
    private void printInventoryManagersDetails(ActionEvent event) throws SQLException {
        //Create Inventory Manager Details Object
        Inventory_Managers_Report inventory_managers_report = new Inventory_Managers_Report();
        //Create Inventory Manager Details PDF
        inventory_managers_report.createPdf();
    }

    //Generate Supplier Managers Details Report
    @FXML
    private void printSupplierManagersDetails(ActionEvent event) throws SQLException {
        //Create Supplier Manager Details Object
        Supplier_Managers_Report supplier_managers_report = new Supplier_Managers_Report();
        //Create Supplier Manager Details PDF
        supplier_managers_report.createPdf();
    }

    //Generate Distribution Managers Details Report
    @FXML
    private void printDistributionManagersDetails(ActionEvent event) throws SQLException {
        //Create Distribution Manager Details Object
        Distribution_Managers_Report distribution_managers_report = new Distribution_Managers_Report();
        //Create Distribution Manager Details PDF
        distribution_managers_report.createPdf();
    }

    //Generate Assistant Managers Details Report
    @FXML
    private void printAssistantManagersDetails(ActionEvent event) throws SQLException {
        //Create Assistant Manager Details Object
        Assistant_Managers_Report assistant_managers_report = new Assistant_Managers_Report();
        //Create Assistant Manager Details PDF
        assistant_managers_report.createPdf();
    }

    //Generate Admins Details Report
    @FXML
    private void printAdminsDetails(ActionEvent event) throws SQLException {
        //Create Admin Details Object
        Admins_Report admins_report = new Admins_Report();
        //Create Admin Details PDF
        admins_report.createPdf();
    }

    //Generate Sales Representatives Details Report
    @FXML
    private void printSalesRepresentativesDetails(ActionEvent event) throws SQLException {
        //Create Sales Representative Details Object
        Sales_Representative_Report sales_representative_report = new Sales_Representative_Report();
        //Create Sale Representative Details PDF
        sales_representative_report.createPdf();
    }

    //Generate Accountants Details Report
    @FXML
    private void printAccountantsDetails(ActionEvent event) throws SQLException {
        //Create Accountant Details Object
        Accountants_Report  accountants_report = new Accountants_Report();
        //Create Accountant Details PDF
        accountants_report.createPdf();
    }

    //Generate Accountant Assistants Details Report
    @FXML
    private void printAccountantAssistantsDetails(ActionEvent event) throws SQLException {
        //Create Accountant Assistants Details Object
        Accountant_Assistants_Report  accountant_assistants_report = new Accountant_Assistants_Report();
        //Create Accountant Assistants Details PDF
        accountant_assistants_report.createPdf();
    }

    //Generate Supervisors Details Report
    @FXML
    private void printStoreSupervisorsDetails(ActionEvent event) throws SQLException {
        //Create Store Supervisors Details Object
        Store_Supervisors_Report  store_supervisors_report = new Store_Supervisors_Report();
        //Create Store Supervisors Details PDF
        store_supervisors_report.createPdf();
    }

    //Generate Marketing Executives Details Report
    @FXML
    private void printMarketingExecutivesDetails(ActionEvent event) throws SQLException {
        //Create Marketing Executives Details Object
        Marketing_Executives_Report  marketing_executives_report = new Marketing_Executives_Report();
        //Create Marketing Executives Details PDF
        marketing_executives_report.createPdf();
    }

    //Generate Office Assistants Details Report
    @FXML
    private void printOfficeAssistantsDetails(ActionEvent event) throws SQLException {
        //Create Office Assistants Details Object
        Office_Assistants_Report  office_assistants_report = new Office_Assistants_Report();
        //Create Office Assistants Details PDF
        office_assistants_report.createPdf();
    }

    //Generate Gemologists Details Report
    @FXML
    private void printGemologistsDetails(ActionEvent event) throws SQLException {
        //Create Gemologists Details Object
        Gemologists_Report  gemologists_report = new Gemologists_Report();
        //Create Gemologists Details PDF
        gemologists_report.createPdf();
    }

    //Generate Admin Assistants Details Report
    @FXML
    private void printAdminAssistantsDetails(ActionEvent event) throws SQLException {
        //Create Admin Assistants Details Object
        Admin_Assistants_Report  admin_assistants_report = new Admin_Assistants_Report();
        //Create Admin Assistants Details PDF
        admin_assistants_report.createPdf();
    }

    //Generate Quality Checkers Details Report
    @FXML
    private void printQualityCheckersDetails(ActionEvent event) throws SQLException {
        //Create Quality Checkers Details Object
        Quality_Checkers_Report  quality_checkers_report = new Quality_Checkers_Report();
        //Create Quality Checkers Details PDF
        quality_checkers_report.createPdf();
    }

    //Generate Clerks Details Report
    @FXML
    private void printClerksDetails(ActionEvent event) throws SQLException {
        //Create Clerks Details Object
        Clerks_Report  clerks_report = new Clerks_Report();
        //Create Clerks Details PDF
        clerks_report.createPdf();
    }

    //Generate Laborers Details Report
    @FXML
    private void printLaborersDetails(ActionEvent event) throws SQLException {
        //Create Laborers Details Object
        Laborers_Report  laborers_report = new Laborers_Report();
        //Create Laborers Details PDF
        laborers_report.createPdf();
    }

}
