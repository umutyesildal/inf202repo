/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf202;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FirmenController implements Initializable {
    
    FXMLDocumentController report = new FXMLDocumentController();

    @FXML
    private TextField NameGiver;
    @FXML
    private TextField KutupMesafesici; 
    @FXML
    private TextField MPTasiyiciOrtamGiver;
    @FXML
    private TextField MiknatislamaTeknigiGiver;
    @FXML
    private TextField UVIsikSiddetiGiver; 
    @FXML
    private TextField IsikMesafesiGiver; 
    
    @FXML
    private TextField FirmNameGiver;
    @FXML
    private TextField AdressGiver;
    @FXML
    private TextField IsEmriGiver;
    @FXML
    private TextField TeklifNoGiver;
    
    @FXML
    public TextField yuzeyDurumuTF;
    @FXML
    public TextField muayeneAsamasiTF;
    
    DatabaseController db = new DatabaseController();
    
    public String FirmNameGiver(){
       String FirmNameVeren = FirmNameGiver.getText();
       return FirmNameVeren;
   }
    public String AdressGiver(){
       String AdressVeren = AdressGiver.getText();
       return AdressVeren;
   }
    public String IsEmriGiver(){
       String IsEmriVeren = IsEmriGiver.getText();
       return IsEmriVeren;
   }
    public String TeklifNoGiver(){
       String TeklifNoVeren = TeklifNoGiver.getText();
       return TeklifNoVeren;
   }
    
    public String nameGiver(){
       String nameveren = NameGiver.getText();
       return nameveren;
   }
    public String MPTasiyiciOrtamGiver(){
       String nameveren = MPTasiyiciOrtamGiver.getText();
       return nameveren;
   }
    public String MiknatislamaTeknigiGiver(){
       String nameveren = MiknatislamaTeknigiGiver.getText();
       return nameveren;
   }
    public String UVIsikSiddetiGiver(){
       String nameveren = UVIsikSiddetiGiver.getText();
       return nameveren;
   }
    public String IsikMesafesiGiver(){
       String nameveren = IsikMesafesiGiver.getText();
       return nameveren;
   }
    public int KutupMesafesiciGiver(){
       String KutupMesafesiString = KutupMesafesici.getText();
       int KutupMesafesiveren = Integer.parseInt(KutupMesafesiString);
       return KutupMesafesiveren;
   }
    
    public void FirmaEkle(ActionEvent event) throws IOException
{
    db.AddFirm(FirmNameGiver(),AdressGiver(),IsEmriGiver(),TeklifNoGiver());
} 
    
    public void EkipmanEkle(ActionEvent event) throws IOException
{
    db.AddAusrustung(nameGiver(),KutupMesafesiciGiver(),MPTasiyiciOrtamGiver(),MiknatislamaTeknigiGiver(),UVIsikSiddetiGiver(),IsikMesafesiGiver());
} 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
        public void LoggedIn(ActionEvent event) throws IOException //This method loads a new scene in a current window
{
    //note that on this line you can substitue "Screen2.fxml" for a string chosen prior to this line.
    Parent loader = FXMLLoader.load(getClass().getResource("LoggedInPage.fxml"));//Creates a Parent called loader and assign it as ScReen2.FXML

    Scene scene = new Scene(loader); //This creates a new scene called scene and assigns it as the Sample.FXML document which was named "loader"

    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //this accesses the window.

    app_stage.setScene(scene); //This sets the scene as scene

    app_stage.show(); // this shows the scene
    
}    
    
 public void yuzeyDurumuAdder(){
     String yuzeyDurumuString = yuzeyDurumuTF.getText();
     db.yuzeyDurumuEkle(yuzeyDurumuString);
 } 
 public void muayeneAsamasiAdder(){
     String muayeneAsamasiString = muayeneAsamasiTF.getText();
     db.muayeneAsamasiEkle(muayeneAsamasiString);
 }   
}
