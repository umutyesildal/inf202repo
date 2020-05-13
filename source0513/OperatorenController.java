/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf202;

import java.awt.print.Book;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
    private ObservableList<Operatoren> OperatorenList;
    
    @FXML
    private TableView<Operatoren> Operatorentable;
    @FXML
    private TableColumn<Operatoren, Integer> id;
    @FXML
    private TableColumn<Operatoren, String> name;
    @FXML
    private TableColumn<Operatoren, String> nachname;
    @FXML
    private TableColumn<Operatoren, String> firmname;
    
    
    @FXML
    private void handleDisplayTables(ActionEvent event) throws NullPointerException {
        try {Connection conn = db.ConnectDB();
            String dbliste = "SELECT * FROM [operatoren]";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(dbliste);
            OperatorenList = FXCollections.observableArrayList();
 
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String nachname = rs.getString("nachname");
                String firmname = rs.getString("firmname");
                OperatorenList.add(new Operatoren(id, name, nachname,firmname));
                System.out.println(id + " " + name+" " + nachname+" " + firmname+" " + "osmannn");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    /*    id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        firmname.setCellValueFactory(new PropertyValueFactory<>("firmname"));

        name.setCellValueFactory(cellData -> { return (new SimpleStringProperty(((Operatoren)cellData.getValue()).getName()));});
        nachname.setCellValueFactory(cellData -> { return (new SimpleStringProperty(((Operatoren)cellData.getValue()).getNachname()));});
        firmname.setCellValueFactory(cellData -> { return (new SimpleStringProperty(((Operatoren)cellData.getValue()).getFirmname()));});
        id.setCellValueFactory(cellData -> { return (new SimpleIntegerProperty(((Operatoren)cellData.getValue()).getId())).asObject();});
 
*/
        Operatorentable.setItems(OperatorenList);
      //  Operatorentable.getColumns().addAll(id,name,nachname,firmname);
      //  Operatorentable.refresh();
   
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    nachname.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
    firmname.setCellValueFactory(cellData -> cellData.getValue().firmnameProperty());
    id.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
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

