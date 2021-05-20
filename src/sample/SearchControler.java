package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class SearchControler implements Initializable{




        @FXML
        private TableView<Gems> GemTableView;

        @FXML
        private TableColumn<?, ?> viewgemID;

        @FXML
        private TableColumn<?, ?> viewgemDescription;

        @FXML
        private TableColumn<?, ?> viewgemWeight;

        @FXML
        private TableColumn<?, ?> viewgemShape;

        @FXML
        private TableColumn<?, ?> viewgemDimension;

        @FXML
        private TableColumn<?, ?> viewgemReorderlevel;

        @FXML
        private TableColumn<?, ?> viewgemStock;

        @FXML
        private TableColumn<?, ?> viewgemCost;

        @FXML
        private TextField src;

        @FXML
        private Button btnhome;

        @FXML
        void showInventory(ActionEvent event) {

        }

        //declare variables
      //  private String query = null;
        private dbconnect dbconnect;
        private PreparedStatement preparedStatement;
       // private ResultSet resultSet;
        //private Gems gems = null;
        private Connection connection;
        String query=null;
        PreparedStatement st=null;
        Connection con=null;
        Gems gems=null;
        ResultSet resultSet=null;

        ObservableList<Gems> dataList = FXCollections.observableArrayList();
       // ObservableList<Gems> dataList;
        @Override
        public void initialize(URL location, ResourceBundle resources) {

            Connect();
            showInventory();
            getGemList();
            searchGems();

        }

        public void Connect() {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/cityofgems", "root", "root123");
            } catch (ClassNotFoundException | SQLException e) {

            }
        }

        @FXML
        public void home(ActionEvent ae) throws IOException {
            btnhome.getScene().getWindow().hide();
            Stage close = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene scene1 = new Scene(root);
            close.setScene(scene1);
            close.show();
            close.setResizable(false);
        }
        public ObservableList<Gems>getGemList(){//new code ()blank

            ObservableList<Gems>gemList=FXCollections.observableArrayList();

            query=" SELECT * FROM cityofgems.inventory ";
            // Statement st;

            //new new uncoment this
            ResultSet rs=null;

            try {
                //st=con.createStatement();
                // rs=st.executeQuery(query);
                st=con.prepareStatement(query);
                rs=st.executeQuery();
                Gems gems;

                while (rs.next()){
                    gemList.add(new Gems(
                            rs.getString("gemId"),
                            rs.getString("description"),
                            rs.getString("weight"),
                            rs.getString("shape"),
                            rs.getString("dimension"),
                            rs.getString("unitsInStock"),//edited
                            rs.getString("reorderLevel"),//edited
                            rs.getString("cost")));
                    GemTableView.setItems(gemList);
                    //gems=new Gems(rs.getString("gemId" ),rs.getString("description"),rs.getString("weight"),rs.getString("shape"),rs.getString("dimension"),rs.getString("unitsInStock"),rs.getString("reorderLevel"),rs.getString("cost"));
                    //gemList.add(gems);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return gemList;

        }
        //get gem details to the screen
        public void showInventory(){
            con=dbconnect.getConnection();
            ObservableList<Gems>list=getGemList();

            //set values to the column
            viewgemID.setCellValueFactory(new PropertyValueFactory<>("gemId"));
            viewgemDescription.setCellValueFactory(new PropertyValueFactory<>("gemDescription"));
            viewgemWeight.setCellValueFactory(new PropertyValueFactory<>("gemWeight"));
            viewgemShape.setCellValueFactory(new PropertyValueFactory<>("gemShape"));
            viewgemDimension.setCellValueFactory(new PropertyValueFactory<>("gemDimension"));
            viewgemStock.setCellValueFactory(new PropertyValueFactory<>("gemUnitsInStock"));
            viewgemReorderlevel.setCellValueFactory(new PropertyValueFactory<>("gemReorderLevel"));
            viewgemCost.setCellValueFactory(new PropertyValueFactory<>("gemCost"));

            GemTableView.setItems(list);
        }
        @FXML
        private void refreshTable() {

         /*   query="SELECT * FROM cityofgems.inventory ";

            try{
                preparedStatement=connection.prepareStatement(query);
                resultSet=preparedStatement.executeQuery();

                //process
                while (resultSet.next()){
                    dataList.add(new Gems(
                           resultSet.getString("gemId"),
                            resultSet.getString("description"),
                            resultSet.getString("weight"),
                            resultSet.getString("shape"),
                            resultSet.getString("dimension"),
                            resultSet.getString("unitsInStock"),//edited
                            resultSet.getString("reorderLevel"),//edited
                            resultSet.getString("cost")));
                    GemTableView.setItems(dataList);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
        }

      /*  private void loadData() {
            connection=dbconnect.getConnection();
            refreshTable();

            viewgemID.setCellValueFactory(new PropertyValueFactory<>("gemId"));
            viewgemDescription.setCellValueFactory(new PropertyValueFactory<>("gemDescription"));
            viewgemWeight.setCellValueFactory(new PropertyValueFactory<>("gemWeight"));
            viewgemShape.setCellValueFactory(new PropertyValueFactory<>("gemShape"));
            viewgemDimension.setCellValueFactory(new PropertyValueFactory<>("gemDimension"));
            viewgemStock.setCellValueFactory(new PropertyValueFactory<>("gemUnitsInStock"));
            viewgemReorderlevel.setCellValueFactory(new PropertyValueFactory<>("gemReorderLevel"));
            viewgemCost.setCellValueFactory(new PropertyValueFactory<>("gemCost"));

            GemTableView.setItems(dataList);
        }*/

        private void searchGems() {

            //new code crud
            src.setOnKeyReleased(e -> {
                if(src.getText().equals(" ")){
                    //loadData();
                }
                else {
                    dataList.clear();
                    String query = "SELECT * FROM cityofgems.inventory WHERE gemId LIKE '%" + src.getText() + "'";
                    try {
                        preparedStatement = connection.prepareStatement(query);
                        //  preparedStatement.setString(1,src.getText());
                        resultSet = preparedStatement.executeQuery();
                        while (resultSet.next()) {

                            dataList.add(new Gems(
                                    resultSet.getString("gemId"),
                                    resultSet.getString("description"),
                                    resultSet.getString("weight"),
                                    resultSet.getString("shape"),
                                    resultSet.getString("dimension"),
                                    resultSet.getString("unitsInStock"),//edited
                                    resultSet.getString("reorderLevel"),//edited
                                    resultSet.getString("cost")));

                        }
                        GemTableView.setItems(dataList);
                    } catch (SQLException e1) {
                        e1.printStackTrace();


                    }
                }
            });



        }
    }


