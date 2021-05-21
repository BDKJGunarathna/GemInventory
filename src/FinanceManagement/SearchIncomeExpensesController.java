package FinanceManagement;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
//import javafx.scene.input.MouseEvent;


public class SearchIncomeExpensesController implements Initializable {

    @FXML
    private TextField search;

    //Table
    @FXML
    private TableView<SearchIncomeExpenses> searchIncomeExpensesTable;
    @FXML
    private TableColumn<SearchIncomeExpenses, Integer> searchIncomeExpensesId;
    @FXML
    private TableColumn<SearchIncomeExpenses, String> searchIncomeExpensesTableDescription;
    @FXML
    private TableColumn<SearchIncomeExpenses, String> searchIncomeExpensesTableType;
    @FXML
    private TableColumn<SearchIncomeExpenses, String> searchIncomeExpensesTableDate;
    @FXML
    private TableColumn<SearchIncomeExpenses, String> searchIncomeExpensesTableAmount;
    @FXML
    private TableColumn<SearchIncomeExpenses, String> searchIncomeExpensesTableAction;

    @FXML
    private Button incomeExpensesMenu1;
    @FXML
    private Button searchIncomeExpensesMenu1;
    @FXML
    private Button insertIncomeExpensesBtn;
    @FXML
    private Button searchHome;
    @FXML
    private Button closeBtn;
    @FXML
    private FontAwesomeIconView printBtn;


    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    SearchIncomeExpenses SearchIncomeExpenses = null;

    // Nav bar
    @FXML
    public void SearchIncomeExpensesOnAction(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("incomeExpenses.fxml"));
            Stage searchInEx1 = (Stage) incomeExpensesMenu1.getScene().getWindow();
            searchInEx1.setTitle("City of Gems");
            searchInEx1.setScene(new Scene(root, 993, 705));
            searchInEx1.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void SearchSearchIncomeExpensesOnAction(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("searchIncomeExpenses.fxml"));
            Stage searchSeInEx1 = (Stage) searchIncomeExpensesMenu1.getScene().getWindow();
            searchSeInEx1.setTitle("City of Gems");
            searchSeInEx1.setScene(new Scene(root, 993, 705));
            searchSeInEx1.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    ObservableList<SearchIncomeExpenses> list = FXCollections.observableArrayList();

    private SearchIncomeExpenses order = null;

    //Initializes the controller class
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ShowSearchIncomeExpenses();

        //initialize search filter method
        searchIncomeExpensesDetails();
    }


    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    // Get incomeExpenses UI
    @FXML
    private void getAddView(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("updateIncomeExpenses.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    @FXML
    private void refreshTable() {
        try {
            list.clear();

            String query = "Select * from incomeandexpenses";
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new SearchIncomeExpenses(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getString("type"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getDouble("amount")));
                searchIncomeExpensesTable.setItems(list);

            }


        } catch (SQLException ex) {
            Logger.getLogger(SearchIncomeExpensesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    //Generate Report Method
    @FXML
    private void print(javafx.scene.input.MouseEvent event) throws SQLException {
        //Create Generate Types of Employee Object
        reportIncomeExpensesController IncomeExpensesReport = new reportIncomeExpensesController();
        //Create Types of Employee PDF
        IncomeExpensesReport.createPdf();
    }


    private void ShowSearchIncomeExpenses() {

        FinanceManagement.DBConnection.getConnection();
        //connection = DbConnect.getConnect();
       refreshTable();


        //public void ShowSearchIncomeExpenses () {
            //ObservableList<sample.SearchIncomeExpenses> list = getSearchIncomeExpensesList();

            //set values to the columns
            searchIncomeExpensesId.setCellValueFactory(new PropertyValueFactory<>("id"));
            searchIncomeExpensesTableDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            searchIncomeExpensesTableType.setCellValueFactory(new PropertyValueFactory<>("type"));
            searchIncomeExpensesTableDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            searchIncomeExpensesTableAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));


            //add cell of button edit
            Callback<TableColumn<FinanceManagement.SearchIncomeExpenses, String>, TableCell<FinanceManagement.SearchIncomeExpenses, String>> cellFoctory = (TableColumn<FinanceManagement.SearchIncomeExpenses, String> param) -> {
                // make cell containing buttons
                final TableCell<FinanceManagement.SearchIncomeExpenses, String> cell = new TableCell<FinanceManagement.SearchIncomeExpenses, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        //that cell created only on non-empty rows
                        if (empty) {
                            setGraphic(null);
                            setText(null);

                        } else {

                            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                            FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                            deleteIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#ff1744;"
                            );
                            editIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#00E676;"
                            );

                            deleteIcon.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {

                                try {
                                    FinanceManagement.SearchIncomeExpenses searchIncomeExpenses = searchIncomeExpensesTable.getSelectionModel().getSelectedItem();
                                    String query = "DELETE FROM incomeandexpenses WHERE id  =" + searchIncomeExpenses.getId() ;
                                    //Connection conn = getConnection();
                                    DBConnection.getConnection();
                                    //Statement st = conn.createStatement();
                                    Statement st = DBConnection.getConnection().createStatement();
                                    st.execute(query);
                                    ShowSearchIncomeExpenses();
                                } catch (SQLException ex) {
                                    Logger.getLogger(SearchIncomeExpensesController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                try {
                                    //Alert Information box
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setHeaderText(null);
                                    alert.setContentText("Record deleted successfully");
                                    alert.show();
                                }
                                catch(Exception ex){
                                    ex.printStackTrace();
                                }

                            });


                          editIcon.setOnMouseClicked((javafx.scene.input.MouseEvent event) ->{

                                SearchIncomeExpenses searchIncomeExpenses = searchIncomeExpensesTable.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("updateIncomeExpenses.fxml"));
                                try {
                                    loader.load();
                                } catch (IOException ex) {
                                    Logger.getLogger(SearchIncomeExpensesController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                UpdateIncomeExpensesController updateIncomeExpensesController = loader.getController();
                                updateIncomeExpensesController.setUpdate(true);

                                //LocalDate lDate = searchIncomeExpenses.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                                updateIncomeExpensesController.setTextField(searchIncomeExpenses.getId(), searchIncomeExpenses.getDescription(),
                                        searchIncomeExpenses.getType(), searchIncomeExpenses.getDate(), searchIncomeExpenses.getAmount());
                                Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();


                            });




                            HBox managebtn = new HBox(editIcon, deleteIcon);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                            HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                            setGraphic(managebtn);

                            setText(null);

                        }
                    }

                };

                return cell;
            };
            searchIncomeExpensesTableAction.setCellFactory(cellFoctory);
            searchIncomeExpensesTable.setItems(list);


        }



    //get incomeExpenses UI
    @FXML
    public void InsertIncomeExpenses(ActionEvent event){
        try {

            Parent root = FXMLLoader.load(getClass().getResource("incomeExpenses.fxml"));
            Stage homeUI = (Stage) insertIncomeExpensesBtn.getScene().getWindow();
            homeUI.setTitle("City of Gems");
            homeUI.setScene(new Scene(root, 993, 705));
            homeUI.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //get dashboard UI
    @FXML
    public void SearchIncomeExpensesToHome(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage searchDashboard = (Stage) searchHome.getScene().getWindow();
            searchDashboard.setTitle("City of Gems");
            searchDashboard.setScene(new Scene(root, 993, 705));
            searchDashboard.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    /*
    //check connection
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cityofgems", "root", "Dasuni2020#");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error!" + ex.getMessage());
            return null;
        }
    }


     */


    //get income and expenses list
    public ObservableList<FinanceManagement.SearchIncomeExpenses> getSearchIncomeExpensesList(){
        ObservableList<FinanceManagement.SearchIncomeExpenses> searchIncomeExpensesList = FXCollections.observableArrayList();
        //Connection conn = getConnection();
        FinanceManagement.DBConnection.getConnection();
        String query = "SELECT * FROM incomeandexpenses";
        Statement st;
        ResultSet rs;

        try {
            //st = conn.createStatement();
            st = FinanceManagement.DBConnection.getConnection().createStatement();
            rs = st.executeQuery(query);
            FinanceManagement.SearchIncomeExpenses searchIncomeExpenses;

            while (rs.next()) {
                searchIncomeExpenses = new FinanceManagement.SearchIncomeExpenses(rs.getInt("id"), rs.getString("description"), rs.getString("type"), rs.getDate("date").toLocalDate(), rs.getDouble("amount"));
                searchIncomeExpensesList.add(searchIncomeExpenses);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return searchIncomeExpensesList;
    }


    public void close(javafx.scene.input.MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }




    //Search Method
    @FXML
    private void searchIncomeExpensesDetails() {
        ObservableList<FinanceManagement.SearchIncomeExpenses> list = getSearchIncomeExpensesList();

        //Wrap the ObservableList in a FilteredList (initially display all data)
        FilteredList<SearchIncomeExpenses> filteredIncomeExpensesDetails = new FilteredList<>(list, b -> true);

        //Set the filter Predicate whenever the filter changes
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredIncomeExpensesDetails.setPredicate(searchIncomeExpenses -> {
                //If filter text is empty, display all income and expenses
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //Compare description, type, and ID of every income expenses with filter text
                String lowerCaseFIlter = newValue.toLowerCase();

                if (String.valueOf(searchIncomeExpenses.getDescription()).toLowerCase().indexOf(lowerCaseFIlter) != -1)
                {
                    return true;//Filter matches description
                }
                else if (String.valueOf(searchIncomeExpenses.getType()).toLowerCase().indexOf(lowerCaseFIlter) != -1)
                {
                    return true;//Filter matches type
                }
                else if (String.valueOf(searchIncomeExpenses.getId()).toLowerCase().indexOf(lowerCaseFIlter) != -1)
                {
                    return true;//Filter matches Id
                }
                else
                    return false;//Does not match
            });
        });

        //Wrap the FilteredList in a SortedList
        SortedList<SearchIncomeExpenses> sortedIncomeExpensesDetails = new SortedList<>(filteredIncomeExpensesDetails);

        //Bind the SortedList comparator to the incomeExpensesTableView comparator
        //Otherwise, sorting the incomeExpensesTableView would have no effect
        sortedIncomeExpensesDetails.comparatorProperty().bind(searchIncomeExpensesTable.comparatorProperty());

        //Add sorted (and filtered) data to the table
        searchIncomeExpensesTable.setItems(sortedIncomeExpensesDetails);
    }




}

































/*
    //database  connection
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cityofgems", "root", "Dasuni2020#");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error!" + ex.getMessage());
            return null;
        }
    }

    public ObservableList<SearchIncomeExpenses> getSearchIncomeExpensesList() {
        ObservableList<SearchIncomeExpenses> searchIncomeExpensesList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM incomeandexpenses";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            SearchIncomeExpenses incomeandexpenses;

            while (rs.next()) {
                incomeandexpenses = new SearchIncomeExpenses(rs.getInt("id"), rs.getString("description"), rs.getString("type"), rs.getDate("date"), rs.getDouble("amount"));
                searchIncomeExpensesList.add(incomeandexpenses);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return searchIncomeExpensesList;
    }


    public void ShowSearchIncomeExpenses() {
        ObservableList<SearchIncomeExpenses> list = getSearchIncomeExpensesList();

        //set values to the columns
        searchIncomeExpensesId.setCellValueFactory(new PropertyValueFactory<SearchIncomeExpenses, Integer>("id"));
        searchIncomeExpensesTableDescription.setCellValueFactory(new PropertyValueFactory<SearchIncomeExpenses, String>("description"));
        searchIncomeExpensesTableType.setCellValueFactory(new PropertyValueFactory<SearchIncomeExpenses, String>("type"));
        searchIncomeExpensesTableDate.setCellValueFactory(new PropertyValueFactory<SearchIncomeExpenses, Date>("date"));
        searchIncomeExpensesTableAmount.setCellValueFactory(new PropertyValueFactory<SearchIncomeExpenses, Double>("amount"));

        searchIncomeExpensesTable.setItems(list);

    }

    //query to insert
    private void searchIncomeExpensesRecord() {
        String query = "INSERT into incomeandexpenses VALUES (" + 0 + ",'" + searchIncomeExpensesTableDescription.getText() + "','" + searchIncomeExpensesTableType.getText() + "', '" +searchIncomeExpensesTableDate.getText()+"'," + searchIncomeExpensesTableAmount.getText() + ")";
        executeQuery(query);
        ShowSearchIncomeExpenses();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;

        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}*/