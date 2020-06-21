/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf202;

import java.awt.print.Book;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
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
    private TableColumn<Operatoren, Integer> level;
    @FXML
    private TableColumn<Operatoren, String> Unterschrift;
    @FXML
    private TextField numarator;
    @FXML
    private TextField nameGiver;
    @FXML
    private TextField NachnameGiver; 
    @FXML
    private TextField levelci;
    @FXML
    private TextField UnterschriftGiver;
    @FXML
    private TextArea Ergebnis;
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
                int level = rs.getInt("level");
                String Unterschrift = rs.getString("Unterschrift");
                OperatorenList.add(new Operatoren(id,level, name, nachname,Unterschrift));
            }
            Ergebnis.clear();
            appendText("Operatoren has been listed.");
        numarator.clear();
        nameGiver.clear();   
        NachnameGiver.clear(); 
        levelci.clear();
        UnterschriftGiver.clear();
        } catch (Exception e) {
            System.out.println(e);
        }        
        Operatorentable.setItems(OperatorenList);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    nachname.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
    Unterschrift.setCellValueFactory(cellData -> cellData.getValue().UnterschriftProperty());
    id.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
    level.setCellValueFactory(cellData -> cellData.getValue().LevelProperty().asObject());
        OutputStream out = new OutputStream() {
        @Override
        public void write(int b) throws IOException {
            appendText(String.valueOf((char) b));
        }
    };
    System.setOut(new PrintStream(out, true));
    
     }
    
    public void appendText(String str) {
    Platform.runLater(() -> Ergebnis.appendText(str));
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
        
    
   public int IDGiver(){
        String numaraString = numarator.getText();
        int numaraveren = Integer.parseInt(numaraString);
        return numaraveren;
   }
   
   public int dialogIDGiver(){
       
    TextInputDialog dialog = new TextInputDialog("ID");
    dialog.setTitle("ID");
    dialog.setHeaderText("Bitte Geben sie die ID für löschende Person.");
    dialog.setContentText("ID :");

    // Traditional way to get the response value.
    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()){
    System.out.println("ID: " + result.get());
}

    // The Java 8 way to get the response value (with lambda expression).
    result.ifPresent(name -> System.out.println("Your name: " + name));
        String dialogID = result.get();
        int numaraveren = Integer.parseInt(dialogID);
        return numaraveren;
   }
   
   public String nameGiver(){
       String nameveren = nameGiver.getText();
       return nameveren;
   }
   public String NachnameGiver(){
       String nachnameveren = NachnameGiver.getText();
       return nachnameveren;
   }
   public int LevelGiver(){
       String levelString = levelci.getText();
       int levelveren = Integer.parseInt(levelString);
       return levelveren;
   }
   public String UnterschriftGiver(){
       String firmnameveren = UnterschriftGiver.getText();
       return firmnameveren;
   }
   
    public void personSil(ActionEvent event) throws IOException
{
    
    db.personSil(dialogIDGiver());
    Ergebnis.clear();
    appendText("Operator has been deleted.");
    numarator.clear();
    nameGiver.clear();   
    NachnameGiver.clear(); 
    levelci.clear();
    UnterschriftGiver.clear();
}
    public void personEkle(ActionEvent event) throws IOException
{
    if(!(numarator.getText().isEmpty())){
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Error ID");
    alert.setContentText("ID muss leer sein !");

    alert.showAndWait();   
    }else{
    db.personEkle(nameGiver(), NachnameGiver(), LevelGiver() , UnterschriftGiver());
    Ergebnis.clear();
    appendText("Operator has been added.");
    numarator.clear();
    nameGiver.clear();   
    NachnameGiver.clear(); 
    levelci.clear();
    UnterschriftGiver.clear();
    }
}
    public void personDuzenle(ActionEvent event) throws IOException
{
    db.personDuzenle(IDGiver(), nameGiver(), NachnameGiver(), LevelGiver(),UnterschriftGiver());
    Ergebnis.clear();
    appendText("Operator records has been changed.");
    numarator.clear();
    nameGiver.clear();   
    NachnameGiver.clear(); 
    levelci.clear();
    UnterschriftGiver.clear();
} 
}

