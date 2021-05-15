package employeemanagement;

import com.jfoenix.controls.JFXButton;
import dbconnection.DBHandler;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import empmodels.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewemployeedetailsController implements Initializable {

    @FXML
    private JFXButton viewempdetailslogoutbtn;

    @FXML
    private JFXButton viewempdetailshomebtn;

    @FXML
    private TextField viewempdetailssearch;

    @FXML
    private FontAwesomeIconView refresh;

    @FXML
    private FontAwesomeIconView print;

    @FXML
    private FontAwesomeIconView add;

    @FXML
    private FontAwesomeIconView employeecount;

    @FXML
    private FontAwesomeIconView viewemployee;

    @FXML
    private TableView<Employee> viewemployeetable;

    @FXML
    private TableColumn<Employee, String> viewempdetailsempid;

    @FXML
    private TableColumn<Employee, String> viewempdetailsempname;

    @FXML
    private TableColumn<Employee, String> viewempdetailsdob;

    @FXML
    private TableColumn<Employee, String> viewempdetailsnic;

    @FXML
    private TableColumn<Employee, String> viewempdetailsaddress;

    @FXML
    private TableColumn<Employee, String> viewempdetailsphone;

    @FXML
    private TableColumn<Employee, String> viewempdetailsphonemobile;

    @FXML
    private TableColumn<Employee, String> viewempdetailsemptype;

    @FXML
    private TableColumn<Employee, String> viewempdetailsemail;

    @FXML
    private TableColumn<Employee, String> viewempdetailssalary;

    @FXML
    private TableColumn<Employee, String> viewempdetailssuspend;

    @FXML
    private TableColumn<Employee, String> viewempdetailsactions;

    //declare variables
    private String query = null;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;
    private Employee employee = null;
    private DBHandler handler;//DBHandler is the connection class

    //Create employee list
    ObservableList<Employee> EmployeeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Initialize DBHandler class
        handler = new DBHandler();

        //Initialize loadDate method
        loadDate();

        //Wrap the ObservableList in a FilteredList (initially display all data)
        FilteredList<Employee> filteredEmployeeDetails = new FilteredList<>(EmployeeList, b -> true);

        //Set the filter Predicate whenever the filter changes
        viewempdetailssearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredEmployeeDetails.setPredicate(employee -> {
                //If filter text is empty, display all employees
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //Compare employee name, employee type, and employee ID of every person with filter text
                String lowerCaseFIlter = newValue.toLowerCase();

                if (employee.getEmpName().toLowerCase().indexOf(lowerCaseFIlter) != -1) {
                    return true;//Filter matches employee name
                } else if (employee.getEmpType().toLowerCase().indexOf(lowerCaseFIlter) != -1) {
                    return true;//Filter matches employee type
                } else if (String.valueOf(employee.getEmpID()).toLowerCase().indexOf(lowerCaseFIlter) != -1) {
                    return true;//Filter matches employee ID
                } else
                    return false;//Does not match
            });
        });

        //Wrap the FilteredList in a SortedList
        SortedList<Employee> sortedEmployeeDetails = new SortedList<>(filteredEmployeeDetails);

        //Bind the SortedList comparator to the EmployeeTableView comparator
        //Otherwise, sorting the EmployeeTableView would have no effect
        sortedEmployeeDetails.comparatorProperty().bind(viewemployeetable.comparatorProperty());

        //Add sorted (and filtered) data to the table
        viewemployeetable.setItems(sortedEmployeeDetails);
    }

    //Logout Method(Direct to login page)
    @FXML
    public void viewempdetlogoutAction(ActionEvent viewempdetev3) throws IOException {
        viewempdetailslogoutbtn.getScene().getWindow().hide();

        Stage viewempdetlogout = new Stage();//Create a Stage
        //Setup the Scene
        Parent viewempdetroot2 = FXMLLoader.load(getClass().getResource("/loginregister/login.fxml"));
        Scene viewempdetscene2 = new Scene(viewempdetroot2);//Create a scene
        viewempdetlogout.setScene(viewempdetscene2);//Set Scene object on the Stage
        viewempdetlogout.show();//Show the Stage which create above (makes the Stage visible and the exits)
        viewempdetlogout.setResizable(false);//User cannot resize the frame
    }

    //getAddView Method (PopUP add employee interface from utility window)
    @FXML
    private void getAddView(MouseEvent event) {
        try {
            //Setup the Scene
            Parent parent = FXMLLoader.load(getClass().getResource("/employeemanagement/addemployee.fxml"));
            Scene scene = new Scene(parent);//Create a scene
            Stage stage = new Stage();//Create a Stage
            stage.setScene(scene);//Set Scene object on the Stage
            stage.initStyle(StageStyle.UTILITY);//Defines a Stage style with a solid white background and minimal platform decorations used for a utility window
            stage.show();//Show the Stage which create above (makes the Stage visible and the exits)
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //getViewEmployeeView Method (PopUP filter employees interface from utility window)
    @FXML
    private void getViewEmployeeView(MouseEvent event) {
        try {
            //Setup the Scene
            Parent parent = FXMLLoader.load(getClass().getResource("/employeemanagement/filteremployees.fxml"));
            Scene scene = new Scene(parent);//Create a scene
            Stage stage = new Stage();//Create a Stage
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);//Defines a Stage style with a solid white background and minimal platform decorations used for a utility window
            stage.show();//Show the Stage which create above (makes the Stage visible and the exits)
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //getEmployeeCountView Method (PopUP employee count interface from utility window)
    @FXML
    private void getEmployeeCouneView(MouseEvent event) {
        try {
            //Setup the Scene
            Parent parent = FXMLLoader.load(getClass().getResource("/employeemanagement/employeecount.fxml"));
            Scene scene = new Scene(parent);//Create a scene
            Stage stage = new Stage();//Create a Stage
            stage.setScene(scene);//Set Scene object on the Stage
            stage.initStyle(StageStyle.UTILITY);//Defines a Stage style with a solid white background and minimal platform decorations used for a utility window
            stage.show();//Show the Stage which create above (makes the Stage visible and the exits)
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //refreshTable (Refresh the table view after update employee details or delete employee or add an employee functions)
    @FXML
    private void refreshTable() {
        //Remove all the elements
        EmployeeList.clear();

        //SQL QUERY (RETRIEVE)
        query = "SELECT * FROM employeemanagement_table";
        try {
            //Create a statement using connection object
            preparedStatement = connection.prepareStatement(query);
            //Execute the query
            resultSet = preparedStatement.executeQuery();

            //Process the ResultSet object
            while (resultSet.next()) {
                //Retrieve Employee Details from employeemanagement table(DB) and Add Employee Details to Table View
                EmployeeList.add(new Employee(
                        resultSet.getInt("empID"),
                        resultSet.getString("empName"),
                        resultSet.getDate("empDOB"),
                        resultSet.getString("nic_number"),
                        resultSet.getString("empAddress"),
                        resultSet.getInt("empPhone1"),
                        resultSet.getInt("empPhone2"),
                        resultSet.getString("empType"),
                        resultSet.getString("empEmail"),
                        resultSet.getDouble("empSalary"),
                        resultSet.getString("empSuspend")));
                viewemployeetable.setItems(EmployeeList);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Generate Report Method
    @FXML
    private void print(MouseEvent event) throws SQLException {
        //Create Generate Types of Employee Object
        Types_Of_Employee_Report typesOfEmployeeReport = new Types_Of_Employee_Report();
        //Create Types of Employee PDF
        typesOfEmployeeReport.createPdf();
    }

    //Table View Method
    @FXML
    private void loadDate() {
        //Establishing a Connection
        connection = handler.getConnection();
        //Refresh Table Call
        refreshTable();

        //Retrieve and Set Value for each Table Cell
        viewempdetailsempid.setCellValueFactory(new PropertyValueFactory<>("empID"));
        viewempdetailsempname.setCellValueFactory(new PropertyValueFactory<>("empName"));
        viewempdetailsdob.setCellValueFactory(new PropertyValueFactory<>("empDOB"));
        viewempdetailsnic.setCellValueFactory(new PropertyValueFactory<>("nic_number"));
        viewempdetailsaddress.setCellValueFactory(new PropertyValueFactory<>("empAddress"));
        viewempdetailsphone.setCellValueFactory(new PropertyValueFactory<>("empPhone1"));
        viewempdetailsphonemobile.setCellValueFactory(new PropertyValueFactory<>("empPhone2"));
        viewempdetailsemptype.setCellValueFactory(new PropertyValueFactory<>("empType"));
        viewempdetailsemail.setCellValueFactory(new PropertyValueFactory<>("empEmail"));
        viewempdetailssalary.setCellValueFactory(new PropertyValueFactory<>("empSalary"));
        viewempdetailssuspend.setCellValueFactory(new PropertyValueFactory<>("empSuspend"));

        //Add cell of button edit
        Callback<TableColumn<Employee, String>, TableCell<Employee, String>> cellFoctory = (TableColumn<Employee, String> param)-> {
            //Make cell containing buttons
            final TableCell<Employee, String> cell = new TableCell<Employee, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //That cell created only on non-empty rows (Data Validation)
                    //if row is empty nothing add to action cell else add Trash fontawesome icon(To Delete Function) and Pencil Square fontawesome icon(To Update Function)
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        //Define fontawesome icons
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        //Define Styles for Delete Icon
                        deleteIcon.setStyle(
                                "-fx-cursor: hand ;"
                                        + "-glyph-size: 20px ;"
                                        + "-fx-fill: #ff1744 ;"
                        );
                        //Define Styles for Edit Icon
                        editIcon.setStyle(
                                "-fx-cursor: hand ;"
                                        + "-glyph-size: 20px ;"
                                        + "-fx-fill: #00E676 ;"
                        );

                        //Delete Icon Method
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                employee = viewemployeetable.getSelectionModel().getSelectedItem();
                                //SQL QUERY (DELETE)
                                query = " DELETE FROM employeemanagement_table WHERE empID =" + employee.getEmpID();
                                //Establishing a Connection
                                connection = handler.getConnection();
                                //Create a statement using connection object
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();//Execute
                                refreshTable();//refresh table method call
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        });

                        //Edit Icon Method
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            employee = viewemployeetable.getSelectionModel().getSelectedItem();
                            //Setup the Scene
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/employeemanagement/updateemployeedetails.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                            //get the Controller
                            updateemployeedetailsController updateemployeedetailsController = loader.getController();
                            //Parameter pass to setUpdate method create on update employee details
                            updateemployeedetailsController.setUpdate(true);
                            //set employee details want to update
                            updateemployeedetailsController.setTextField(employee.getEmpID(), employee.getEmpAddress(), employee.getEmpPhone1(), employee.getEmpPhone2(),
                                    employee.getEmpType(), employee.getEmpEmail(), employee.getEmpSalary(), employee.getEmpSuspend());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();//Create a Stage
                            stage.setScene(new Scene(parent));//Set Scene object on the Stage
                            stage.initStyle(StageStyle.UTILITY);//Defines a Stage style with a solid white background and minimal platform decorations used for a utility window
                            stage.show();//Show the Stage which create above (makes the Stage visible and the exits)
                        });

                        //Define Styles for action column
                        HBox managebutton = new HBox(editIcon, deleteIcon);
                        managebutton.setStyle("-fx-alignment: center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebutton);//Set Edit and Delete Icons

                        setText(null);
                    }

                }

            };

            return cell;
        };
        viewempdetailsactions.setCellFactory(cellFoctory);//Set Delete and Edit icons to action column
        viewemployeetable.setItems(EmployeeList);//Set Employee Details to Table View
    }

}
