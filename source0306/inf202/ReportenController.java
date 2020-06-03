/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf202;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import static java.lang.System.out;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author umuty
 */
public class ReportenController implements Initializable {

    DatabaseController db = new DatabaseController();
    private ObservableList<Reporten> ReportenList;
    
    @FXML
    private TableView<Reporten> reportenTable;
    @FXML
    private TableColumn<Reporten, Integer> berichtNummer;
    @FXML
    private TableColumn<Reporten, String> berichtName;
    @FXML
    private TableColumn<Reporten, String> kundeName;
    
    
    
public void handleDisplayTables(ActionEvent event) throws NullPointerException {
        try {Connection conn = db.ConnectReporten();
            String dbliste = "SELECT * FROM [reporten]";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(dbliste);
            ReportenList = FXCollections.observableArrayList();
 
            while (rs.next()) {
                int berichtNummer = rs.getInt("berichtNummer");
                String berichtName = rs.getString("berichtName");
                String kundeName = rs.getString("kundeName");
                ReportenList.add(new Reporten(berichtNummer,berichtName,kundeName));
            }
        } catch (Exception e) {
            System.out.println(e);
        }        
        reportenTable.setItems(ReportenList);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    berichtName.setCellValueFactory(cellData -> cellData.getValue().berichtNameProperty());
    kundeName.setCellValueFactory(cellData -> cellData.getValue().kundeNameProperty());
    berichtNummer.setCellValueFactory(cellData -> cellData.getValue().berichtNummerProperty().asObject());
    System.setOut(new PrintStream(out, true));  
     }

       public void Reporten(ActionEvent event) throws IOException //This method loads a new scene in a current window
{
    //note that on this line you can substitue "Screen2.fxml" for a string chosen prior to this line.
    Parent loader = FXMLLoader.load(getClass().getResource("Report1Deneme.fxml"));//Creates a Parent called loader and assign it as ScReen2.FXML

    Scene scene = new Scene(loader); //This creates a new scene called scene and assigns it as the Sample.FXML document which was named "loader"

    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //this accesses the window.

    app_stage.setScene(scene); //This sets the scene as scene

    app_stage.show(); // this shows the scene
    
}   
    public void GoBack(ActionEvent event) throws IOException //This method loads a new scene in a current window
{
    //note that on this line you can substitue "Screen2.fxml" for a string chosen prior to this line.
    Parent loader = FXMLLoader.load(getClass().getResource("LoggedInPage.fxml"));//Creates a Parent called loader and assign it as ScReen2.FXML

    Scene scene = new Scene(loader); //This creates a new scene called scene and assigns it as the Sample.FXML document which was named "loader"

    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //this accesses the window.

    app_stage.setScene(scene); //This sets the scene as scene

    app_stage.show(); // this shows the scene
    
}
    
    public void buttonclickedbericht(ActionEvent event) throws IOException //This method loads a new scene in a current window
{
    //note that on this line you can substitue "Screen2.fxml" for a string chosen prior to this line.
    Parent loader = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));//Creates a Parent called loader and assign it as ScReen2.FXML

    Scene scene = new Scene(loader); //This creates a new scene called scene and assigns it as the Sample.FXML document which was named "loader"

    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //this accesses the window.

    app_stage.setScene(scene); //This sets the scene as scene

    app_stage.show(); // this shows the scene
    
}
    
    
    
}
