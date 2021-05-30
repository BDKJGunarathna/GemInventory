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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


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

    //Buttons
    @FXML
    private Button incomeExpensesMenu1;
    @FXML
    private Button searchIncomeExpensesMenu1;
    @FXML
    private Button searchHome;
    @FXML
    private FontAwesomeIconView printBtn;

    //declare variables
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    SearchIncomeExpenses SearchIncomeExpenses = null;


    ObservableList<SearchIncomeExpenses> list = FXCollections.observableArrayList();

    //Initializes the controller class
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowSearchIncomeExpenses();

        //initialize search filter method
        searchIncomeExpensesDetails();
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


    // Update and Delete Method
    private void ShowSearchIncomeExpenses() {

        FinanceManagement.DBConnection.getConnection();
       refreshIncomeExpensesTable();

            //Retrieve and set values to the columns
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

                            //Define update and delete fontawesome icons
                            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                            FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                            //Define Styles for Delete Icon
                            deleteIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#ff1744;"
                            );

                            //Define Styles for Edit Icon
                            editIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#00E676;"
                            );

                            //Delete Icon Method
                            deleteIcon.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {

                                try {
                                    FinanceManagement.SearchIncomeExpenses searchIncomeExpenses = searchIncomeExpensesTable.getSelectionModel().getSelectedItem();
                                    //SQL QUERY (DELETE)
                                    String query = "DELETE FROM incomeandexpenses WHERE id  =" + searchIncomeExpenses.getId() ;
                                    DBConnection.getConnection();
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

                            //Edit Icon Method
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

                                //set income and expenses details need to be updated
                                updateIncomeExpensesController.setTextField(searchIncomeExpenses.getId(), searchIncomeExpenses.getDescription(),
                                        searchIncomeExpenses.getType(), searchIncomeExpenses.getDate(), searchIncomeExpenses.getAmount());
                                Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();


                            });


                            //Define Styles for action column
                            HBox managebtn = new HBox(editIcon, deleteIcon);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                            HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                            setGraphic(managebtn); //Set Edit and Delete Icons

                            setText(null);

                        }
                    }

                };

                return cell;
            };
            searchIncomeExpensesTableAction.setCellFactory(cellFoctory); //Set Delete and Edit icons to action column
            searchIncomeExpensesTable.setItems(list); //Set income and expenses Details to Table View


        }



    //get income and expenses list
    public ObservableList<FinanceManagement.SearchIncomeExpenses> getSearchIncomeExpensesList(){
        ObservableList<FinanceManagement.SearchIncomeExpenses> searchIncomeExpensesList = FXCollections.observableArrayList();
        FinanceManagement.DBConnection.getConnection();
        String query = "SELECT * FROM incomeandexpenses";
        Statement st;
        ResultSet rs;

        try {
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


    //refreshTable method (Refresh the table view after update or delete income and expenses details)
    @FXML
    private void refreshIncomeExpensesTable() {
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
        reportIncomeExpensesController IncomeExpensesReport = new reportIncomeExpensesController();
        IncomeExpensesReport.generatePDF();
    }



    //"Home" Button method (Direct to "financeHome" page)
    @FXML
    public void SearchIncomeExpensesToHome(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("financeHome.fxml"));
            Stage searchDashboard = (Stage) searchHome.getScene().getWindow();
            searchDashboard.setTitle("Gem Merchant System");
            searchDashboard.setScene(new Scene(root, 1050, 780));
            searchDashboard.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // Nav bar
    //"Income and Expenses" Button method (Direct to "Income and Expenses" page)
    @FXML
    public void SearchIncomeExpensesOnAction(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("incomeExpenses.fxml"));
            Stage searchInEx1 = (Stage) incomeExpensesMenu1.getScene().getWindow();
            searchInEx1.setTitle("Gem Merchant System");
            searchInEx1.setScene(new Scene(root, 1050, 780));
            searchInEx1.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //"Search Income and Expenses" Button method (Direct to "Search Income and Expenses" page)
    @FXML
    public void SearchSearchIncomeExpensesOnAction(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("searchIncomeExpenses.fxml"));
            Stage searchSeInEx1 = (Stage) searchIncomeExpensesMenu1.getScene().getWindow();
            searchSeInEx1.setTitle("Gem Merchant System");
            searchSeInEx1.setScene(new Scene(root, 1050, 780));
            searchSeInEx1.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

