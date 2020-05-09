/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf202;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author umuty
 */
public class OperatorenController implements Initializable {
    
    DatabaseController db = new DatabaseController();
    
    @FXML
    private TableView tableOpts;
    
    @FXML
    private TableColumn IDCol;

    @FXML
    private TableColumn NameCol;

    @FXML
    private TableColumn NachnameCol;

    @FXML
    private TableColumn FirmnameCol;
    
    
    private ObservableList <Operatoren> dataOpts; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataOpts = FXCollections.observableArrayList();

        IDCol.setCellValueFactory(
                new PropertyValueFactory<Operatoren, String>("id"));
        NameCol.setCellValueFactory(
                new PropertyValueFactory<Operatoren, String>("name"));
        NachnameCol.setCellValueFactory(
                new PropertyValueFactory<Operatoren, String>("Nachname"));
        FirmnameCol.setCellValueFactory(
                new PropertyValueFactory<Operatoren, String>("Firmname"));
    
    String sqlList = "SELECT ID, Name, Nachname, Firmname FROM [operatoren]";
   
            try{Connection conn = db.ConnectDB();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sqlList);
                while(rs.next()){
                    Operatoren opt = new Operatoren();
                  //  System.out.println(rs.getInt("ID")+ " " + rs.getString("Name")+" " + rs.getString("Nachname")+" " +rs.getString("FirmName")+" ");
                  opt.id.set(rs.getString("ID"));
                  opt.id.set(rs.getString("Name"));
                  opt.id.set(rs.getString("Nachname"));
                  opt.id.set(rs.getString("Firmname"));
                  dataOpts.add(opt);
                }
                tableOpts.setItems(dataOpts);
            }
            catch(SQLException e){
                System.out.println("Data couldn't be gathered.");
            }
     }


        public void Operatoren(ActionEvent event) throws IOException //This method loads a new scene in a current window
{
    //note that on this line you can substitue "Screen2.fxml" for a string chosen prior to this line.
    Parent loader = FXMLLoader.load(getClass().getResource("LoggedInPage.fxml"));//Creates a Parent called loader and assign it as ScReen2.FXML

    Scene scene = new Scene(loader); //This creates a new scene called scene and assigns it as the Sample.FXML document which was named "loader"

    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //this accesses the window.

    app_stage.setScene(scene); //This sets the scene as scene

    app_stage.show(); // this shows the scene
    
}    
    
    
       public void personList(ActionEvent event) throws IOException
{
    db.DBController(1);
}
    public void personSil(ActionEvent event) throws IOException
{
    db.DBController(2);
}
    public void personEkle(ActionEvent event) throws IOException
{
    db.DBController(3);
}
    public void personDuzenle(ActionEvent event) throws IOException
{
    db.DBController(4);
} 
    
   
}

