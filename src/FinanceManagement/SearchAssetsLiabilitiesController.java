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
//import javafx.scene.input.MouseEvent;


public class SearchAssetsLiabilitiesController implements Initializable {

    @FXML
    private TextField searchAssetsLiabilities;

    //Table
    @FXML
    private TableView<SearchAssetsLiabilities> searchAssetsLiabilitiesTable;
    @FXML
    private TableColumn<SearchAssetsLiabilities, Integer> searchAssetsLiabilitiesId;
    @FXML
    private TableColumn<SearchAssetsLiabilities, String> searchAssetsLiabilitiesTableDescription;
    @FXML
    private TableColumn<SearchAssetsLiabilities, String> searchAssetsLiabilitiesTableType;
    @FXML
    private TableColumn<SearchAssetsLiabilities, String> searchAssetsLiabilitiesTableDate;
    @FXML
    private TableColumn<SearchAssetsLiabilities, String> searchAssetsLiabilitiesTableAmount;
    @FXML
    private TableColumn<SearchAssetsLiabilities, String> searchAssetsLiabilitiesTableAction;

    @FXML
    private Button assetsLiabilitiesMenu1;
    @FXML
    private Button searchAssetsLiabilitiesMenu1;
    @FXML
    private Button searchAssetsLiabilitieHome;
    @FXML
    private Button closeBtn;
    @FXML
    private FontAwesomeIconView printBtn1;


    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    SearchAssetsLiabilities SearchAssetsLiabilities = null;

    ObservableList<SearchAssetsLiabilities> list = FXCollections.observableArrayList();

    private SearchAssetsLiabilities order = null;

    //Initializes the controller class
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ShowSearchAssetsLiabilities();

        //initialize search filter method
        searchAssetsLiabilitiesDetails();
    }


    //Search Method
    @FXML
    private void searchAssetsLiabilitiesDetails() {
        ObservableList<FinanceManagement.SearchAssetsLiabilities> list = getSearchAssetsLiabilitiesList();

        //Wrap the ObservableList in a FilteredList (initially display all data)
        FilteredList<SearchAssetsLiabilities> filteredAssetsLiabilitiesDetails = new FilteredList<>(list, b -> true);

        //Set the filter Predicate whenever the filter changes
        searchAssetsLiabilities.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredAssetsLiabilitiesDetails.setPredicate(searchAssetsLiabilities -> {
                //If filter text is empty, display all Assets and Liabilities
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //Compare description, type, and ID of every Assets and Liabilities with filter text
                String lowerCaseFIlter = newValue.toLowerCase();

                if (String.valueOf(searchAssetsLiabilities.getAssetsliabilities_description()).toLowerCase().indexOf(lowerCaseFIlter) != -1)
                {
                    return true;//Filter matches description
                }
                else if (String.valueOf(searchAssetsLiabilities.getAssetsliabilities_type()).toLowerCase().indexOf(lowerCaseFIlter) != -1)
                {
                    return true;//Filter matches type
                }
                else if (String.valueOf(searchAssetsLiabilities.getAssetsliabilities_id()).toLowerCase().indexOf(lowerCaseFIlter) != -1)
                {
                    return true;//Filter matches Id
                }
                else
                    return false;//Does not match
            });
        });

        //Wrap the FilteredList in a SortedList
        SortedList<SearchAssetsLiabilities> sortedAssetsLiabilitiesDetails = new SortedList<>(filteredAssetsLiabilitiesDetails);

        //Bind the SortedList comparator to the AssetsLiabilitiesTableView comparator
        //Otherwise, sorting the AssetsLiabilitiesTableView would have no effect
        sortedAssetsLiabilitiesDetails.comparatorProperty().bind(searchAssetsLiabilitiesTable.comparatorProperty());

        //Add sorted (and filtered) data to the table
        searchAssetsLiabilitiesTable.setItems(sortedAssetsLiabilitiesDetails);
    }



    // Update and Delete Method
    private void ShowSearchAssetsLiabilities() {

        FinanceManagement.DBConnection.getConnection();
        refreshTable1();


        //Retrieve and set values to the columns
        searchAssetsLiabilitiesId.setCellValueFactory(new PropertyValueFactory<>("assetsliabilities_id"));
        searchAssetsLiabilitiesTableDescription.setCellValueFactory(new PropertyValueFactory<>("assetsliabilities_description"));
        searchAssetsLiabilitiesTableType.setCellValueFactory(new PropertyValueFactory<>("assetsliabilities_type"));
        searchAssetsLiabilitiesTableDate.setCellValueFactory(new PropertyValueFactory<>("assetsliabilities_date"));
        searchAssetsLiabilitiesTableAmount.setCellValueFactory(new PropertyValueFactory<>("assetsliabilities_amount"));


        //add cell of button edit
        Callback<TableColumn<FinanceManagement.SearchAssetsLiabilities, String>, TableCell<FinanceManagement.SearchAssetsLiabilities, String>> cellFoctory = (TableColumn<FinanceManagement.SearchAssetsLiabilities, String> param) -> {
            // make cell containing buttons
            final TableCell<FinanceManagement.SearchAssetsLiabilities, String> cell = new TableCell<FinanceManagement.SearchAssetsLiabilities, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        //Define fontawesome icons
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

                        deleteIcon.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {

                            try {
                                FinanceManagement.SearchAssetsLiabilities searchAssetsLiabilities = searchAssetsLiabilitiesTable.getSelectionModel().getSelectedItem();
                                //SQL QUERY (DELETE)
                                String query = "DELETE FROM assetsandliabilities WHERE assetsliabilities_id  =" + searchAssetsLiabilities.getAssetsliabilities_id() ;
                                //Establishing a Connection
                                DBConnection.getConnection();
                                //Create a statement using connection object
                                Statement st = DBConnection.getConnection().createStatement();
                                st.execute(query);
                                ShowSearchAssetsLiabilities();
                            } catch (SQLException ex) {
                                Logger.getLogger(SearchAssetsLiabilitiesController.class.getName()).log(Level.SEVERE, null, ex);
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

                            SearchAssetsLiabilities searchAssetsLiabilities = searchAssetsLiabilitiesTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("updateAssetsLiabilities.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(SearchAssetsLiabilitiesController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            //get the Controller
                            UpdateAssetsLiabilitiesController updateAssetsLiabilitiesController = loader.getController();
                            //Parameter pass to setUpdate method create on update assets and liabilities details
                            updateAssetsLiabilitiesController.setUpdate(true);

                            //set assets and liabilities details want to update
                            updateAssetsLiabilitiesController.setTextField(searchAssetsLiabilities.getAssetsliabilities_id(), searchAssetsLiabilities.getAssetsliabilities_description(),
                                    searchAssetsLiabilities.getAssetsliabilities_type(), searchAssetsLiabilities.getAssetsliabilities_date(), searchAssetsLiabilities.getAssetsliabilities_amount());
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

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        searchAssetsLiabilitiesTableAction.setCellFactory(cellFoctory);
        searchAssetsLiabilitiesTable.setItems(list);

    }



    //get Assets and Liabilities List
    public ObservableList<FinanceManagement.SearchAssetsLiabilities> getSearchAssetsLiabilitiesList(){
        ObservableList<FinanceManagement.SearchAssetsLiabilities> searchAssetsLiabilitiesList = FXCollections.observableArrayList();
        //Connection conn = getConnection();
        FinanceManagement.DBConnection.getConnection();
        String query = "SELECT * FROM assetsandliabilities";
        Statement st;
        ResultSet rs;

        try {
            //st = conn.createStatement();
            st = FinanceManagement.DBConnection.getConnection().createStatement();
            rs = st.executeQuery(query);
            FinanceManagement.SearchAssetsLiabilities searchAssetsLiabilities;

            while (rs.next()) {
                searchAssetsLiabilities = new FinanceManagement.SearchAssetsLiabilities(rs.getInt("assetsliabilities_id"), rs.getString("assetsliabilities_description"), rs.getString("assetsliabilities_type"), rs.getDate("assetsliabilities_date").toLocalDate(), rs.getDouble("assetsliabilities_amount"));
                searchAssetsLiabilitiesList.add(searchAssetsLiabilities);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return searchAssetsLiabilitiesList;
    }


    //refreshTable (Refresh the table view after update or delete assets and liabilities details)
    @FXML
    private void refreshTable1() {
        try {
            list.clear();

            String query = "Select * from assetsandliabilities";
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new SearchAssetsLiabilities(
                        resultSet.getInt("assetsliabilities_id"),
                        resultSet.getString("assetsliabilities_description"),
                        resultSet.getString("assetsliabilities_type"),
                        resultSet.getDate("assetsliabilities_date").toLocalDate(),
                        resultSet.getDouble("assetsliabilities_amount")));
                searchAssetsLiabilitiesTable.setItems(list);

            }


        } catch (SQLException ex) {
            Logger.getLogger(SearchAssetsLiabilitiesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    //Generate Report Method
    @FXML
    private void print1(javafx.scene.input.MouseEvent event) throws SQLException {
        //Create Generate Types of Employee Object
        reportAssetsLiabilitiesController AssetsLiabilitiesReport = new reportAssetsLiabilitiesController();
        //Create Types of Employee PDF
        AssetsLiabilitiesReport.createPdf1();
    }



    //"Home" Button method (Direct to "financeHome" page)
    @FXML
    public void SearchAssetsLiabilitiesToHome(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("financeHome.fxml"));
            Stage searchDashboard = (Stage) searchAssetsLiabilitieHome.getScene().getWindow();
            searchDashboard.setTitle("City of Gems");
            searchDashboard.setScene(new Scene(root, 1050, 780));
            searchDashboard.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }



    // Nav bar
    //"Income and Expenses" Button method (Direct to "Assets and Liabilities" page)
    @FXML
    public void SearchAssetsLiabilitiesOnAction(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("assetsLiabilities.fxml"));
            Stage searchInEx1 = (Stage) assetsLiabilitiesMenu1.getScene().getWindow();
            searchInEx1.setTitle("City of Gems");
            searchInEx1.setScene(new Scene(root, 1050, 780));
            searchInEx1.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //"Search Income and Expenses" Button method (Direct to "Search Assets and Liabilities" page)
    @FXML
    public void SearchSearchAssetsLiabilitiesOnAction(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("searchAssetsLiabilities.fxml"));
            Stage searchSeInEx1 = (Stage) searchAssetsLiabilitiesMenu1.getScene().getWindow();
            searchSeInEx1.setTitle("City of Gems");
            searchSeInEx1.setScene(new Scene(root, 1050, 780));
            searchSeInEx1.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
