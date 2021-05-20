package sample;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.scene.control.*;
import jdk.internal.org.objectweb.asm.commons.TableSwitchGenerator;
import sample.Gems;
import javafx.scene.image.ImageView;
import sample.dbconnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileOutputStream;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    private TableView<Gems> GemTV;

  //  @FXML
    //private TableColumn<Gems, String> gNo;

    @FXML
    private TableColumn<Gems, String> viewgemID;

    @FXML
    private TableColumn<Gems, String> viewgemDescription;

    @FXML
    private TableColumn<Gems, String> viewgemWeight;

    @FXML
    private TableColumn<Gems, String> viewgemShape;

    @FXML
    private TableColumn<Gems, String> viewgemDimension;

    @FXML
    private TableColumn<Gems, String> viewgemStock;

    @FXML
    private TableColumn<Gems, String> viewgemReorderlevel;

    @FXML
    private TableColumn<Gems, String> viewgemCost;



    @FXML
    private TableColumn<Gems, String> gqty;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUp;

    @FXML
    private TextField src;

    @FXML
    private Button btnDel;
    @FXML
    private Button btnreport;
    @FXML
    private Button btnsearch;

    @FXML
    private Button btndemo;


    // @FXML
   // private ImageView gemlogo;
ObservableList<Gems>listM;
ObservableList<Gems>dataList;

        int index=-1;
        String query=null;
           PreparedStatement st=null;
            Connection con=null;
            Gems gems=null;
            ResultSet resultSet=null;

   
   // ObservableList<Gems>gemsList=FXCollections.observableArrayList();
    //new code
   /* ObservableList<Gems>filteredData=FXCollections.observableArrayList();

    public Controller(){
        viewgemID.setCellValueFactory(new PropertyValueFactory<>("gemId"));
        viewgemDescription.setCellValueFactory(new PropertyValueFactory<>("gemDescription"));
        viewgemWeight.setCellValueFactory(new PropertyValueFactory<>("gemWeight"));
        viewgemShape.setCellValueFactory(new PropertyValueFactory<>("gemShape"));
        viewgemDimension.setCellValueFactory(new PropertyValueFactory<>("gemDimension"));
        viewgemStock.setCellValueFactory(new PropertyValueFactory<>("gemUnitsInStock"));
        viewgemReorderlevel.setCellValueFactory(new PropertyValueFactory<>("gemReorderLevel"));
        viewgemCost.setCellValueFactory(new PropertyValueFactory<>("gemCost"));

        dataList=getGemList();
        filteredData.addAll(dataList);

        gemsList.addListener(new ListChangeListener<Gems>() {
            @Override
            public void onChanged(Change<? extends Gems> change) {
                updateFilteredData();
            }
        });
    }
    private  void updateFilteredData(){
        //filteredData=clear();

        for(Gems g:gemsList){
            filteredData.add(g);
        }
        reapplyTableSortOrder();
    }


    private boolean matchesFilter(Gems gems){
        String filterString=src.getText();
        if(filterString==null || filterString.isEmpty()){
            return true;
        }
        String lowerCaseFilterString=filterString.toLowerCase();

        if(gems.getGemId().toLowerCase().indexOf(lowerCaseFilterString)!=-1){
            return true;
        }else if(gems.getGemShape().toLowerCase().indexOf(lowerCaseFilterString)!=-1){
            return true;
        }
        return false;
    }
    private void  reapplyTableSortOrder(){
        ArrayList<TableColumn<Gems,?>>sortOrder=new ArrayList<>(GemTV.getSortOrder());
        GemTV.getSortOrder().clear();
        GemTV.getSortOrder().addAll(sortOrder);
    }
    //end*/
    ObservableList<Gems>gemList=FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Connect();
        showInventory();
        getGemList();


        //searchGems();
        //new code

        //end
    }
    public void Connect(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/cityofgems","root","root123");
        }catch (ClassNotFoundException | SQLException e){

        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event){
        System.out.println("You clicked ");

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
                GemTV.setItems(gemList);
                //gems=new Gems(rs.getString("gemId" ),rs.getString("description"),rs.getString("weight"),rs.getString("shape"),rs.getString("dimension"),rs.getString("unitsInStock"),rs.getString("reorderLevel"),rs.getString("cost"));
                //gemList.add(gems);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            return gemList;

    }

@FXML
void searchGem(){
    //set values to the column
    viewgemID.setCellValueFactory(new PropertyValueFactory<>("gemId"));
    viewgemDescription.setCellValueFactory(new PropertyValueFactory<>("gemDescription"));
    viewgemWeight.setCellValueFactory(new PropertyValueFactory<>("gemWeight"));
    viewgemShape.setCellValueFactory(new PropertyValueFactory<>("gemShape"));
    viewgemDimension.setCellValueFactory(new PropertyValueFactory<>("gemDimension"));
    viewgemStock.setCellValueFactory(new PropertyValueFactory<>("gemUnitsInStock"));
    viewgemReorderlevel.setCellValueFactory(new PropertyValueFactory<>("gemReorderLevel"));
    viewgemCost.setCellValueFactory(new PropertyValueFactory<>("gemCost"));

   // dataList=getGemList();
    GemTV.setItems(dataList);

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

        GemTV.setItems(list);
    }
/*public void searchGem(){

//wrap the observable
    FilteredList<Gems> filteredData = new FilteredList<>(gemsList,b ->true);

    //2
    src.textProperty().addListener((observable,oldValue,newValue) ->{
        filteredData.setPredicate(Gems ->{
            //if filter text is empty
            if(newValue==null || newValue.isEmpty()){
                return true;
            }
            //compare
            String lowerCaseFilter=newValue.toLowerCase();

            if (Gems.getGemId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;//filter matches gemid
            }else if (Gems.getGemShape().toLowerCase().indexOf(lowerCaseFilter)!=-1)
                return true;//filter matches gem shape
                else
                    return false;//does not match
            });
        });
    //3 wrap the filterdlist in a sorted list
    SortedList<Gems>sortedData=new SortedList<>(filteredData);
    //4 bind the sorted list
    sortedData.comparatorProperty().bind(GemTV.comparatorProperty());
    //5 add sorted data to the table
    GemTV.setItems(sortedData);
    }*/


public void search(ActionEvent ae3) throws SQLException {
        String gemId=src.getText();

        st=con.prepareStatement(" SELECT gemId, description, weight, shape, dimension, unitsInStock, reorderLevel, cost FROM inventory WHERE gemId=?");
        st.setString(1,gemId);

        ResultSet rs=st.executeQuery();
        if(rs.next()==true){
            String gemid=rs.getString(1);
            String description=rs.getString(2);
            String weight=rs.getString(3);
            String shape=rs.getString(4);
            String dimension=rs.getString(5);
            String units=rs.getString(6);
            String reOrder=rs.getString(7);
            String cost=rs.getString(8);


        }
//new code


}

//new code
  /*  public ObservableList<Gems> searchGems(String gemId)throws ClassNotFoundException,SQLException{
        String sql="SELECT * FROM cityofgems.inventory WHERE gemId= "+gemId;
        try {
            ResultSet rs=null;
            st=con.prepareStatement(sql);
            rs=st.executeQuery();
            ObservableList<Gems>list= getGemList(rs);
            return list;

        }catch (SQLException e){
            System.out.println("Erro"+e);
            e.printStackTrace();
            throw e;
        }
    }*/
    //end

    //new code
   /* public void demoData()  {
        Connect();

        Gems gem3=new Gems("gem12","Hardness: 7n","16.87","Cushion","6 x 15.6 mm","2","1","160,000");
        gemList.add(gem3);

    }*/
    //emd
public void genReport(){

    try {
        String fileName = "D:\\CityOfGems\\inventroy.pdf";
        Document document = new Document();

        //pfd writer
        PdfWriter.getInstance(document, new FileOutputStream((fileName)));
        document.open();
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Report Generated Success");
        alert.setContentText("Report generated succesfully please find the report at D:\\CityOfGems\\inventroy.pdf");
        alert.showAndWait();
        //new new code

        //table.addCell(cell1);
        //add logo
        Image logo=Image.getInstance("logo2.jpg");
        logo.setAbsolutePosition(10f,10f);
        logo.scaleAbsolute(600,800);
        document.add(logo);

        dbconnect db=new dbconnect();
        Connection connection=dbconnect.getConnection();
        PreparedStatement ps=null;
        ResultSet rs1=null;

        String query="SELECT * FROM cityofgems.inventory";
        ps=connection.prepareStatement(query);
        rs1=ps.executeQuery();

//new new


       while (rs1.next()) {
           PdfPTable table=new PdfPTable(8);
          /* PdfPCell cell1=new PdfPCell(new Paragraph(viewgemID.getText()));
           cell1.setColspan(8);
           cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell1.setBackgroundColor(BaseColor.BLUE);*/

            // Paragraph para=new Paragraph(rs.getString("gemId")+""+rs.getString("description")+""+rs.getString("weight")+""+rs.getString("shape")+""+rs.getString("dimension")+""+rs.getString("unitsInStock")+""+rs.getString("reorderLevel")+""+rs.getString("cost"));
            // document.add(para);
            //add table
            //
            //new code

            //uncoment

         /*  PdfPTable table=new PdfPTable(8);
            PdfPCell cell1=new PdfPCell(new Paragraph("Inventroy Report"));

           cell1.setColspan(8);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBackgroundColor(BaseColor.BLUE);
            table.addCell(cell1);*/


           String gemid = rs1.getString(1);
           String description = rs1.getString(2);
           String weight = rs1.getString(3);
           String shape = rs1.getString(4);
           String dimension = rs1.getString(5);
           String units = rs1.getString(6);
           String reOrder = rs1.getString(7);
           String cost = rs1.getString(8);

           PdfPCell cell1=new PdfPCell(new Paragraph( gemid));
           cell1.setColspan(8);
           cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell1.setBackgroundColor(BaseColor.BLUE);
           table.addCell(cell1);

           PdfPCell c1=new PdfPCell(new Phrase(viewgemID.getText()));
            table.addCell(c1);

           c1=new PdfPCell(new Phrase(viewgemDescription.getText()));
           table.addCell(c1);

           c1=new PdfPCell(new Phrase(viewgemWeight.getText()));
           table.addCell(c1);

           c1=new PdfPCell(new Phrase(viewgemShape.getText()));
            table.addCell(c1);

            c1=new PdfPCell(new Phrase(viewgemDimension.getText()));
            table.addCell(c1);

           c1=new PdfPCell(new Phrase(viewgemStock.getText()));
           table.addCell(c1);

            c1=new PdfPCell(new Phrase(viewgemReorderlevel.getText()));
            table.addCell(c1);

            c1=new PdfPCell(new Phrase( viewgemCost.getText()));
            table.addCell(c1);





          /* String gemid = rs1.getString(1);
            String description = rs1.getString(2);
            String weight = rs1.getString(3);
            String shape = rs1.getString(4);
            String dimension = rs1.getString(5);
            String units = rs1.getString(6);
            String reOrder = rs1.getString(7);
            String cost = rs1.getString(8);*/


           table.addCell(gemid);
           table.addCell(description);
           table.addCell(weight);
           table.addCell(shape);
           table.addCell(dimension);
           table.addCell(units);
           table.addCell(reOrder);
           table.addCell(cost);

          /*   PdfPCell cell1=new PdfPCell(new Paragraph( gemid));
             cell1.setColspan(8);
             cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell1.setBackgroundColor(BaseColor.BLUE);
             table.addCell(cell1);
*/




            document.add(table);

        }

        document.close();

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}


    public void addbtnAction(javafx.event.ActionEvent actionEvent)throws IOException {
        btnAdd.getScene().getWindow().hide();
        Stage addnew=new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("additem.fxml"));
        Scene scene=new Scene(root);
        addnew.setScene(scene);
        addnew.show();
        addnew.setResizable(false);
    }
    public void updatebtnAction(javafx.event.ActionEvent actionEvent)throws IOException{
        btnUp.getScene().getWindow().hide();
        Stage update=new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("updateitem.fxml"));
        Scene scene=new Scene(root);
        update.setScene(scene);
        update.show();
        update.setResizable(false);
    }
    public void deletebtnAction(javafx.event.ActionEvent actionEvent)throws IOException{
        btnDel.getScene().getWindow().hide();
        Stage delete=new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("deleteitem.fxml"));
        Scene scene=new Scene(root);
        delete.setScene(scene);
        delete.show();
        delete.setResizable(false);
    }
    public void reportbtnAction(javafx.event.ActionEvent actionEvent)throws IOException {
        btnsearch.getScene().getWindow().hide();
        Stage search=new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("Search.fxml"));
        Scene scene=new Scene(root);
        search.setScene(scene);
        search.show();
        search.setResizable(false);
    }
    public void SearchbtnAction(javafx.event.ActionEvent actionEvent)throws IOException {
        btnsearch.getScene().getWindow().hide();
        Stage addnew=new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("Search.fxml"));
        Scene scene=new Scene(root);
        addnew.setScene(scene);
        addnew.show();
        addnew.setResizable(false);
    }
   /* @FXML
    private void searchGems() {
        //Wrap the ObservableList in a FilteredList (initially display all data)
        FilteredList<Gems> filteredGemsDetails = new FilteredList<>(gemList, b -> true);
        //Set the filter Predicate whenever the filter changes
        src.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredGemsDetails.setPredicate(gems -> {
                //If filter text is empty, display all employees
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                //Compare employee name, employee type, and employee ID of every person with filter text
                String lowerCaseFIlter = newValue.toLowerCase();
                if (gems.getGemId().toLowerCase().indexOf(lowerCaseFIlter) != -1) {
                    return true;//Filter matches employee name
                } else if (gems.getGemShape().toLowerCase().indexOf(lowerCaseFIlter) != -1) {
                    return true;//Filter matches employee type
                } else if (gems.getGemDescription().toLowerCase().indexOf(lowerCaseFIlter) != -1) {
                    return true;//Filter matches employee ID
                } else
                    return false;//Does not match
            });
        });
        //Wrap the FilteredList in a SortedList
        SortedList<Gems> sortedGemsDetails = new SortedList<>( filteredGemsDetails);
        //Bind the SortedList comparator to the EmployeeTableView comparator
        //Otherwise, sorting the EmployeeTableView would have no effect
        sortedGemsDetails.comparatorProperty().bind(GemTV.comparatorProperty());
        //Add sorted (and filtered) data to the table
        GemTV.setItems( sortedGemsDetails);
    }*/

}
