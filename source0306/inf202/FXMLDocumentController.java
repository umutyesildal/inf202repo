/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf202;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author umuty
 */
public class FXMLDocumentController implements Initializable {
    Operatoren opt = new Operatoren(1,1,"a","b","c");
    DatabaseController db = new DatabaseController();
    final ObservableList mCB = FXCollections.observableArrayList();
    final ObservableList eCB = FXCollections.observableArrayList();
    ArrayList<String> yuzeyDurumu = new ArrayList<>();
    ArrayList<String> muayeneAsamasi = new ArrayList<>();
    ArrayList<String> muayeneKapsami = new ArrayList<>();
    @FXML
    private Label label;
    @FXML
    public ChoiceBox musteriCB; //Database'den FirmName
    @FXML
    public TextField muayeneProseduruTF; //Otomatik gelicek degistirmeye izin var.
    @FXML
    public TextField sayfaNoTF; // Otomatik gelicek degistirmeye izin var.
    @FXML
    public TextField projeAdiTF; //Text ile yazılıcak.
    @FXML
    public ChoiceBox muayeneKapsamiCB; // 1-100 arasi degerler girilecek sadece.
    @FXML
    public TextField raporNoTF; // Otomatik de gelebilir bakıcaz buna. 		**********
    @FXML
    public TextField testYeriTF; // Database'den adress çekicek.Degisebilir ama
    @FXML
    public TextField resimNoTF; // Zorunlu değil.boşsa - kalcak.
    @FXML
    public TextField raporTarihiTF; // tarih gelcek.
    @FXML
    public TextField muayeneStandartiTF; //Standart degisebilir.
    @FXML
    public ChoiceBox yuzeyDurumuCB; // baska yerden eklenebilicek.
    @FXML
    public TextField isEmriNoTF; //Database'den gelcek.
    @FXML
    public TextField degerlendirmeStandartıTF; // standart degisebilir.
    @FXML
    public ChoiceBox muayeneAsamasiCB; // seçenekler arasından sec. seçenekler ne ?????????
    @FXML
    public TextField teklifNoTF; // database
    @FXML
    public TextField kutupMesafesiTF; //otomatik dbase KutupMesafesi
    @FXML
    public TextField muayeneBolgesiTF; //standart değişebilir.
    @FXML
    public TextField yuzeySicakligiTF;  //  kisi gircek
    @FXML
    public ChoiceBox cihazCB; // EkipmanIsmi
    @FXML
    public ChoiceBox akimTipiCB; // AC DC seç
    @FXML
    public TextField muayeneBolgesiSiddetiTF; // standart degisebilir.
    @FXML
    public TextField mpTasiyiciOrtamTF; //otomatik db MPTasiyiciOrtam
    @FXML
    public TextField luxmetreTF; //standart degisebilir.
    @FXML
    public TextField miknatislamaTeknigiTF; //db MiknatislamaTeknigi
    @FXML        
    public TextField muayeneOrtamiTF; //ZD
    @FXML        
    public TextField yuzeyTF; //standart degisebilir.
    @FXML        
    public TextField UVIsikSiddetiTF; //db UVIsikSiddeti
    @FXML        
    public TextField miknatisGiderimiTF; //Zorunlu degil
    @FXML        
    public TextField isikCihaziTanimiTF;    //standart degisebilir.     
    @FXML        
    public TextField isikMesafesiTF;      // db IsikMesafesi  
    @FXML        
    public TextField isilIslemTF;     // zd
    @FXML        
    public TextField kaldirmaTestiTarihNoTF;     // elle girilcek.
    @FXML        
    public TextField standartSapmalarTF;    //standart degisebilir.
    @FXML        
    public TextField muayeneTarihleriTF;      // Tarih gelcek.
    @FXML        
    public TextField aciklamaEklerTF;   // zd
    @FXML        
    public TextField kaynakParcaNoTF;     // zorunlu alan elle 
    @FXML        
    public TextField kontrolUzunTF;    //el ile girilecek, zorunlu alan.
    @FXML        
    public TextField kaynakYonTF;  //el ile girilecek, zorunlu alan.
    @FXML        
    public TextField kalinlikTF;   //el ile girilecek, zorunlu alan.
    @FXML        
    public TextField capTF;   //el ile girilecek, zorunlu alan.
    @FXML        
    public TextField hataTipiTF;//    Red ise zorunlu el ile girilecek.
    @FXML        
    public TextField hataninTipiTF;  // Red ise zorunlu el ile girilecek.
    @FXML
    public ChoiceBox sonucCB;  //OK ya da RED
    @FXML
    public ChoiceBox operatorCB;  //database Name ve Nachname
    @FXML        
    public TextField operatorLevelTF;  //      database Level      
    @FXML
    public TextField operatorTarihTF;  //Tarih
    @FXML
    public ChoiceBox degerlendirenCB;  //database Name ve Nachname
    @FXML
    public TextField degerlendirenLevelTF;  //db Level
    @FXML
    public TextField degerlendirenTarihTF;  //tarih
    @FXML
    public ChoiceBox onayCB;  // database Name Nachname
    @FXML
    public TextField onayLevelTF;    // db Level
    @FXML
    public TextField onayTarihTF;    //  Tarih



    
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    fillBox();
    musteriCB.setItems(mCB);
    ekipmanFillBox(); 
    cihazCB.setItems(eCB);
    dateFinder();
    yuzeyDurumuEkleyici();
    muayeneAsamasiEkleyici();
    CBAktivasyon();    
    } 
    
    
    public void fillBox(){
        String fillBox = "SELECT FirmName FROM [Firm]";        
        try (Connection conn = this.db.ConnectFirm();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(fillBox)) {
            while(rs.next()){
                mCB.add(rs.getString("FirmName"));
            }
            stmt.close();
            rs.close();
        }   catch (SQLException e) {
            System.out.println("Error fillBox.");
        }
}
        public void yuzeyDurumuEkleyici(){
        String yuzeyDurumuString = "SELECT yuzeyDurumu FROM yuzeyDurumu";        
        try (Connection conn = this.db.ConnectYuzeyDurumu();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(yuzeyDurumuString)) {
            while(rs.next()){
                yuzeyDurumu.add(rs.getString("yuzeyDurumu"));
            }
            stmt.close();
            rs.close();
        }   catch (SQLException e) {
            System.out.println("Error yuzeyDurumu.");
        }
}
        public void muayeneAsamasiEkleyici(){
        String muayeneAsamasiString = "SELECT muayeneAsamasi FROM muayeneAsamasi";        
        try (Connection conn = this.db.ConnectMuayeneAsamasi();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(muayeneAsamasiString)) {
            while(rs.next()){
                muayeneAsamasi.add(rs.getString("muayeneAsamasi"));
            }
            stmt.close();
            rs.close();
        }   catch (SQLException e) {
            System.out.println("Error muayeneAsamasi.");
        }
}
    
    
    
    public void ekipmanFillBox(){
    String ekipmanFillBox = "SELECT [ekipmanName] FROM [Ekipman]";        
    try (Connection conn = this.db.ConnectAusrustung();
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(ekipmanFillBox)) {
        while(rs.next()){
            eCB.add(rs.getString("ekipmanName"));
            }
            stmt.close();
            rs.close();
        }   catch (SQLException e) {
            System.out.println("Error ekipmanFillBox.");
        }
} 
        
    public void CBAktivasyon(){
        for(int i = 0;i<muayeneAsamasi.size();i++){
         muayeneAsamasiCB.getItems().add(muayeneAsamasi.get(i));
         System.out.println("m1"+ muayeneAsamasi.get(i));
        }
        for(int j = 0;j<yuzeyDurumu.size();j++){
         yuzeyDurumuCB.getItems().add(yuzeyDurumu.get(j));
         System.out.println("Y1"+ yuzeyDurumu.get(j));
        }
        
        for (int e = 1 ;e<101;e++){
            muayeneKapsamiCB.getItems().add(e);
        }       

       akimTipiCB.getItems().add("AC");
       akimTipiCB.getItems().add("DC");
       
       sonucCB.getItems().add("OK");
       sonucCB.getItems().add("RED");
    }
    
    public void dateFinder(){
               //getting current date and time using Date class
       DateFormat df = new SimpleDateFormat("dd/MM/yy");
       Date dateobj = new Date();
       System.out.println(df.format(dateobj));
       raporTarihiTF.setText(df.format(dateobj));
       muayeneTarihleriTF.setText(df.format(dateobj));
       operatorTarihTF.setText(df.format(dateobj));
       degerlendirenTarihTF.setText(df.format(dateobj));
       onayTarihTF.setText(df.format(dateobj));
    }
    
    
     public void buttonclicked(ActionEvent event) throws IOException //This method loads a new scene in a current window
{
    //note that on this line you can substitue "Screen2.fxml" for a string chosen prior to this line.
    Parent loader = FXMLLoader.load(getClass().getResource("Reporten.fxml"));//Creates a Parent called loader and assign it as ScReen2.FXML

    Scene scene = new Scene(loader); //This creates a new scene called scene and assigns it as the Sample.FXML document which was named "loader"

    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //this accesses the window.

    app_stage.setScene(scene); //This sets the scene as scene

    app_stage.show(); // this shows the scene
    
}
     
        public void saveAsPdf(ActionEvent event) throws IOException{
            System.out.println("Whatthehell!");
        }  
     
 
         
}
