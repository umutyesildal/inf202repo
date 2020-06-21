/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf202;

import static java.awt.SystemColor.window;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author umuty
 */
public class FXMLDocumentController implements Initializable {

    Operatoren opt = new Operatoren(1, 1, "a", "b", "c"); //creating operator object 
    DatabaseController db = new DatabaseController(); //creating database object
    final ObservableList mCB = FXCollections.observableArrayList(); // ObservableList for musteriCB
    final ObservableList eCB = FXCollections.observableArrayList(); // ObservableList for ekipmanCB
    ArrayList<String> yuzeyDurumu = new ArrayList<>(); //arraylist for yuzeyDurumu
    ArrayList<String> muayeneAsamasi = new ArrayList<>();//arraylist for muayeneAsamasi
    ArrayList<String> muayeneKapsami = new ArrayList<>();//arraylist for muayeneKapsami
    ArrayList<String> operatoren = new ArrayList<>();//arraylist for operatoren
    @FXML
    private Label label;
    @FXML
    public AnchorPane ap;
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
    public TextField raporNoTF; // Otomatik de gelebilir bakıcaz buna. 	
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
    public TextField hataninYeriTF;  // Red ise zorunlu el ile girilecek.
    @FXML
    public ChoiceBox sonucCB;  //OK ya da RED
    @FXML
    public ChoiceBox operatorCB;  //database Name ve Nachname
    @FXML
    public TextField operatorLevelTF;  //      database Level      
    @FXML
    public TextField operatorTarihTF;  //Tarih
    @FXML
    public TextField operatorImzaTF;
    @FXML
    public ChoiceBox degerlendirenCB;  //database Name ve Nachname
    @FXML
    public TextField degerlendirenLevelTF;  //db Level
    @FXML
    public TextField degerlendirenTarihTF;  //tarih
    @FXML
    public TextField degerlendirenImzaTF;
    @FXML
    public ChoiceBox onayCB;  // database Name Nachname
    @FXML
    public TextField onayLevelTF;    // db Level
    @FXML
    public TextField onayTarihTF;    //  Tarih
    @FXML
    public TextField onayImzaTF;

    int deger = 0;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) { // When opening the scene these methods works.
        fillBox();
        musteriCB.setItems(mCB);
        ekipmanFillBox();
        cihazCB.setItems(eCB);
        dateFinder();
        yuzeyDurumuEkleyici();
        muayeneAsamasiEkleyici();
        operatorenEkleyici();
        CBAktivasyon();
        berichtNummerGiver();
    }

    public void saveToDatabase() { //saves report to database
        System.out.println(Integer.parseInt(raporNoTF.getText()) + projeAdiTF.getText() + (String) musteriCB.getSelectionModel().getSelectedItem());
        db.berichtEkle(Integer.parseInt(raporNoTF.getText()), projeAdiTF.getText(), (String) musteriCB.getSelectionModel().getSelectedItem());
    }

    public void fillBox() {  // FirmName to choiceBox
        String fillBox = "SELECT FirmName FROM [Firm]";
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(fillBox)) {
            while (rs.next()) {
                mCB.add(rs.getString("FirmName"));
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error fillBox.");
        }
    }

    public void yuzeyDurumuEkleyici() {// yuzeyDurumu to choiceBox
        String yuzeyDurumuString = "SELECT yuzeyDurumu FROM yuzeyDurumu";
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(yuzeyDurumuString)) {
            while (rs.next()) {
                yuzeyDurumu.add(rs.getString("yuzeyDurumu"));
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error yuzeyDurumu.");
        }
    }

    public void operatorenEkleyici() { // operator to choicebox
        String operatorString = "SELECT Name,Nachname FROM operatoren";
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(operatorString)) {
            while (rs.next()) {
                String a = (rs.getString("Name") + " " + rs.getString("Nachname"));
                operatoren.add(a);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error operatoren.");
        }
    }

    public void muayeneAsamasiEkleyici() {// yuzeyDurumu to choiceBox
        String muayeneAsamasiString = "SELECT muayeneAsamasi FROM muayeneAsamasi";
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(muayeneAsamasiString)) {
            while (rs.next()) {
                muayeneAsamasi.add(rs.getString("muayeneAsamasi"));
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error muayeneAsamasi.");
        }
    }

    public void ekipmanFillBox() { //ekipman adding to choicebox
        String ekipmanFillBox = "SELECT [ekipmanName] FROM [Ekipman]";
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(ekipmanFillBox)) {
            while (rs.next()) {
                eCB.add(rs.getString("ekipmanName"));
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error ekipmanFillBox.");
        }
    }

    public void berichtNummerGiver() { // report number adding.
        String berichtNummerGiver = "SELECT MAX(berichtNummer) FROM [reporten]";
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(berichtNummerGiver)) {
            if (rs.next()) {
                raporNoTF.setText(String.valueOf(rs.getInt(1) + 1));
            }

        } catch (SQLException e) {
            System.out.println("Error berichtNummerGiver");
        }
    }

    public void CBAktivasyon() { // Activating the choiceboxes
        for (int i = 0; i < muayeneAsamasi.size(); i++) {
            muayeneAsamasiCB.getItems().add(muayeneAsamasi.get(i));
            System.out.println("m1" + muayeneAsamasi.get(i));
        }
        for (int j = 0; j < yuzeyDurumu.size(); j++) {
            yuzeyDurumuCB.getItems().add(yuzeyDurumu.get(j));
            System.out.println("Y1" + yuzeyDurumu.get(j));
        }

        for (int e = 1; e < 101; e++) {
            muayeneKapsamiCB.getItems().add(e);
        }

        for (int f = 0; f < operatoren.size(); f++) {
            operatorCB.getItems().add(operatoren.get(f));
            degerlendirenCB.getItems().add(operatoren.get(f));
            onayCB.getItems().add(operatoren.get(f));
            System.out.println("x1" + operatoren.get(f));
        }

        akimTipiCB.getItems().add("AC");
        akimTipiCB.getItems().add("DC");

        sonucCB.getItems().add("OK");
        sonucCB.getItems().add("RED");

    }

    public void dateFinder() { // writing date to necessary places
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

    public void buttonclicked(ActionEvent event) throws IOException //get to reporten.fxml
    {
        Parent loader = FXMLLoader.load(getClass().getResource("Reporten.fxml"));

        Scene scene = new Scene(loader);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(scene);

        app_stage.show();

    }

    public void getHelp(ActionEvent event) throws IOException { // Get Help
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ich bin hier, um dir zu helfen !");
        alert.setHeaderText("Verwendung dieses Berichts:");
        alert.setContentText("Sie müssen jede Lücke füllen, die keine ZD-Markierung hat. Einige Lücken haben den Text \"otomatik gelecek\". Diese Lücken werden gefüllt, wenn Sie aus dem Auswahlfeld auswählen und unten auf die Schaltfläche \"Bosluklari Doldur\" klicken.Auf der Seite mit den MuayeneSonuclari muss zuerst das Sonuc ausgewählt werden. Je nach Sonuc müssen die erforderlichen Felder ausgefüllt werden.Wenn sich Ihre Firma nicht in der Auswahlbox befindet, können Sie Ihre Firma unter \"Firmen und Arbeitsplätze\" auf der Homepage speichern. ");

        alert.showAndWait();
    }

    public void getChoiceBoxItem() {    // Populating textfields according to choiceboxes.
        musteriAdresiGetir((String) musteriCB.getSelectionModel().getSelectedItem());
        isEmriGetir((String) musteriCB.getSelectionModel().getSelectedItem());
        teklifNoGetir((String) musteriCB.getSelectionModel().getSelectedItem());
        kutupMesafesiGetir((String) cihazCB.getSelectionModel().getSelectedItem());
        mpTasiyiciOrtamGiver((String) cihazCB.getSelectionModel().getSelectedItem());
        miknatislamaTeknigiGiver((String) cihazCB.getSelectionModel().getSelectedItem());
        UVIsikSiddetiGiver((String) cihazCB.getSelectionModel().getSelectedItem());
        isikMesafesiGiver((String) cihazCB.getSelectionModel().getSelectedItem());
        OperatorLevelGiver(firstWordGiver((String) operatorCB.getSelectionModel().getSelectedItem()));
        OperatorImzaGiver(firstWordGiver((String) operatorCB.getSelectionModel().getSelectedItem()));
        degerlendirenLevelGiver(firstWordGiver((String) degerlendirenCB.getSelectionModel().getSelectedItem()));
        degerlendirenImzaGiver(firstWordGiver((String) degerlendirenCB.getSelectionModel().getSelectedItem()));
        onayLevelGiver(firstWordGiver((String) onayCB.getSelectionModel().getSelectedItem()));
        onayImzaGiver(firstWordGiver((String) onayCB.getSelectionModel().getSelectedItem()));
        operatorCB.getSelectionModel().getSelectedItem();
        degerlendirenCB.getSelectionModel().getSelectedItem();
        onayCB.getSelectionModel().getSelectedItem();

        switch (sonucAyarla()) {
            case 0:
                System.out.println("No problem at Result");
                break;
            case 1:
                System.out.println("Hata Tipi kann nicht null sein! ");
                String nurHataTipi = "Hata Tipi kann nicht null sein!";
                dialogError(nurHataTipi);
                break;

            case 2:
                System.out.println("HataninYeri kann nicht null sein! ");
                String nurHataninYeri = "HataninYeri kann nicht null sein!";
                dialogError(nurHataninYeri);
                break;

            case 3:
                System.out.println("1.Hata Tipi kann nicht null sein! \n 2.HataninYeri kann nicht null sein! ");
                String ikisiDe = "1.Hata Tipi kann nicht null sein! \n 2.HataninYeri kann nicht null sein! ";
                dialogError(ikisiDe);
        }

        int a1 = zorunluKisimCheck();
        if (a1 > 0) {
            String yazdir = ("Nicht alle notwendige Lücken hat gefüllt.");
            dialogError(yazdir);
        } else {
            degeriArttir();
        }

    }

    public void musteriAdresiGetir(String b) {  // starting to populate textfields according to choicebox items 381 to 597 
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT Adress FROM [Firm] WHERE Firmname= '" + b + "'")) {
            while (rs.next()) {
                String x = rs.getString("Adress");
                System.out.println(x);
                testYeriTF.setText(x);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error fillBox.");
        }
    }

    public void isEmriGetir(String b) {
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT IsEmri FROM [Firm] WHERE Firmname= '" + b + "'")) {
            while (rs.next()) {
                String x = rs.getString("IsEmri");
                System.out.println(x);
                isEmriNoTF.setText(x);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error isEmriGetir.");
        }
    }

    public void teklifNoGetir(String b) {
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT TeklifNo FROM [Firm] WHERE Firmname= '" + b + "'")) {
            while (rs.next()) {
                String x = rs.getString("TeklifNo");
                System.out.println(x);
                teklifNoTF.setText(x);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error feklifNoGetir.");
        }
    }

    public void kutupMesafesiGetir(String b) {
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT KutupMesafesi FROM [Ekipman] WHERE ekipmanName= '" + b + "'")) {
            while (rs.next()) {
                String x = rs.getString("KutupMesafesi");
                System.out.println(x);
                kutupMesafesiTF.setText(x);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error kutupMesafesi.");
        }
    }

    public void mpTasiyiciOrtamGiver(String b) {
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT MPTasiyiciOrtam FROM [Ekipman] WHERE ekipmanName= '" + b + "'")) {
            while (rs.next()) {
                String x = rs.getString("MPTasiyiciOrtam");
                System.out.println(x);
                mpTasiyiciOrtamTF.setText(x);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error mpTasiyiciOrtam.");
        }
    }

    public void miknatislamaTeknigiGiver(String b) {
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT MiknatislamaTeknigi FROM [Ekipman] WHERE ekipmanName= '" + b + "'")) {
            while (rs.next()) {
                String x = rs.getString("MiknatislamaTeknigi");
                System.out.println(x);
                miknatislamaTeknigiTF.setText(x);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error miknatislamaTeknigi.");
        }
    }

    public void UVIsikSiddetiGiver(String b) {
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT UVIsikSiddeti FROM [Ekipman] WHERE ekipmanName= '" + b + "'")) {
            while (rs.next()) {
                String x = rs.getString("UVIsikSiddeti");
                System.out.println(x);
                UVIsikSiddetiTF.setText(x);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error UVIsikSiddeti.");
        }
    }

    public void isikMesafesiGiver(String b) {
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT IsikMesafesi FROM [Ekipman] WHERE ekipmanName= '" + b + "'")) {
            while (rs.next()) {
                String x = rs.getString("IsikMesafesi");
                System.out.println(x);
                isikMesafesiTF.setText(x);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error IsikMesafesi.");
        }
    }

    public void OperatorLevelGiver(String b) {
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT Level FROM [operatoren] WHERE Name= '" + b + "'")) {
            while (rs.next()) {
                int x = rs.getInt("Level");
                System.out.println(x);
                operatorLevelTF.setText(Integer.toString(x));
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error OperatorLevelGiver.");
        }
    }

    public void OperatorImzaGiver(String b) {
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT Unterschrift FROM [operatoren] WHERE Name= '" + b + "'")) {
            while (rs.next()) {
                String x = rs.getString("Unterschrift");
                System.out.println(x);
                operatorImzaTF.setText(x);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error OperatorImzaGiver.");
        }
    }

    public void degerlendirenLevelGiver(String b) {
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT Level FROM [operatoren] WHERE Name= '" + b + "'")) {
            while (rs.next()) {
                int x = rs.getInt("Level");
                System.out.println(x);
                degerlendirenLevelTF.setText(Integer.toString(x));
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error degerlendirenLevelGiver.");
        }
    }

    public void degerlendirenImzaGiver(String b) {
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT Unterschrift FROM [operatoren] WHERE Name= '" + b + "'")) {
            while (rs.next()) {
                String x = rs.getString("Unterschrift");
                System.out.println(x);
                degerlendirenImzaTF.setText(x);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error degerlendirenImzaGiver.");
        }
    }

    public void onayLevelGiver(String b) {
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT Level FROM [operatoren] WHERE Name= '" + b + "'")) {
            while (rs.next()) {
                int x = rs.getInt("Level");
                System.out.println(x);
                onayLevelTF.setText(Integer.toString(x));
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error onayLevelGiver.");
        }
    }

    public void onayImzaGiver(String b) {
        try (Connection conn = this.db.ConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT Unterschrift FROM [operatoren] WHERE Name= '" + b + "'")) {
            while (rs.next()) {
                String x = rs.getString("Unterschrift");
                System.out.println(x);
                onayImzaTF.setText(x);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error onayImzaGiver.");
        }
    }

    public int sonucAyarla() { // According to Result make textfields red.if hatatipi is null add 1 to x, if hataninYeri is null add 2 to x,so if x = 1 only hatatipi,x=2 only hataninyeri x=3 both of them
        int x = 0;
        if (sonucCB.getSelectionModel().getSelectedItem().equals("RED")) {
            if (hataTipiTF.getText().isEmpty()) {
                hataTipiTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
                x = x + 1;
            }
            if (hataninYeriTF.getText().isEmpty()) {
                hataninYeriTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
                x = x + 2;
            }
        }
        return x;
    }

    public void dialogError(String a) { // dialog alert error with given string.
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Ein Fehler aufgetreten.");
        alert.setContentText(a);

        alert.showAndWait();

    }

    public int zorunluKisimCheck() { // 

        int sayi1 = 0;
        if (muayeneProseduruTF.getText().isEmpty()) {
            muayeneProseduruTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (sayfaNoTF.getText().isEmpty()) {
            sayfaNoTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (projeAdiTF.getText().isEmpty()) {
            projeAdiTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (raporNoTF.getText().isEmpty()) {
            raporNoTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (testYeriTF.getText().isEmpty()) {
            testYeriTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (raporTarihiTF.getText().isEmpty()) {
            raporTarihiTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (muayeneStandartiTF.getText().isEmpty()) {
            muayeneStandartiTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (isEmriNoTF.getText().isEmpty()) {
            isEmriNoTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (degerlendirmeStandartıTF.getText().isEmpty()) {
            degerlendirmeStandartıTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (teklifNoTF.getText().isEmpty()) {
            teklifNoTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (kutupMesafesiTF.getText().isEmpty()) {
            kutupMesafesiTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (muayeneBolgesiTF.getText().isEmpty()) {
            muayeneBolgesiTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (yuzeySicakligiTF.getText().isEmpty()) {
            yuzeySicakligiTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (muayeneBolgesiSiddetiTF.getText().isEmpty()) {
            muayeneBolgesiSiddetiTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (mpTasiyiciOrtamTF.getText().isEmpty()) {
            mpTasiyiciOrtamTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (luxmetreTF.getText().isEmpty()) {
            luxmetreTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (miknatislamaTeknigiTF.getText().isEmpty()) {
            miknatislamaTeknigiTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (yuzeyTF.getText().isEmpty()) {
            yuzeyTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (UVIsikSiddetiTF.getText().isEmpty()) {
            UVIsikSiddetiTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (isikCihaziTanimiTF.getText().isEmpty()) {
            isikCihaziTanimiTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (isikMesafesiTF.getText().isEmpty()) {
            isikMesafesiTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (kaldirmaTestiTarihNoTF.getText().isEmpty()) {
            kaldirmaTestiTarihNoTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (standartSapmalarTF.getText().isEmpty()) {
            standartSapmalarTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (muayeneTarihleriTF.getText().isEmpty()) {
            muayeneTarihleriTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (kaynakParcaNoTF.getText().isEmpty()) {
            kaynakParcaNoTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (kontrolUzunTF.getText().isEmpty()) {
            kontrolUzunTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (kaynakYonTF.getText().isEmpty()) {
            kaynakYonTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (kalinlikTF.getText().isEmpty()) {
            kalinlikTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (operatorLevelTF.getText().isEmpty()) {
            operatorLevelTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (operatorTarihTF.getText().isEmpty()) {
            operatorTarihTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (operatorImzaTF.getText().isEmpty()) {
            operatorImzaTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (degerlendirenLevelTF.getText().isEmpty()) {
            degerlendirenLevelTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (degerlendirenTarihTF.getText().isEmpty()) {
            degerlendirenTarihTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (degerlendirenImzaTF.getText().isEmpty()) {
            degerlendirenImzaTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (onayLevelTF.getText().isEmpty()) {
            onayLevelTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (onayTarihTF.getText().isEmpty()) {
            onayTarihTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        if (onayImzaTF.getText().isEmpty()) {
            onayImzaTF.setStyle(("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;"));
            sayi1 = sayi1 + 1;
        }
        return sayi1;
    }

    public void savePDF() {
        if (deger > 0) {
            TextInputDialog dialog = new TextInputDialog("Directory");
            dialog.setTitle("Directory");
            dialog.setHeaderText("Bitte Geben sie das Verzeichnis zum Speichern");
            dialog.setContentText("ID :");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            String directory = result.get();
            if (result.isPresent()) {

                System.out.println("ID: " + result.get());
            }
            AnchorPane root = new AnchorPane();
            root = ap;
            root.getChildrenUnmodifiable();
            try {
                WritableImage nodeshot = root.snapshot(new SnapshotParameters(), null);
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                ImageIO.write(SwingFXUtils.fromFXImage(nodeshot, null), "png", output);
                output.close();
                PDDocument doc = new PDDocument();
                PDPage page = new PDPage();
                PDImageXObject pdfimage;
                PDPageContentStream content;
                pdfimage = PDImageXObject.createFromByteArray(doc, output.toByteArray(), "jpg");
                content = new PDPageContentStream(doc, page);
                PDRectangle box = page.getMediaBox();
                double factor = Math.min(box.getWidth() / nodeshot.getWidth(), box.getHeight() / nodeshot.getHeight());
                float height = (float) (nodeshot.getHeight() * factor);
                content.drawImage(pdfimage, 0, box.getHeight() - height, (float) (nodeshot.getWidth() * factor), height);
                content.close();
                doc.addPage(page);
                String a = directory + "\\" + (String) (projeAdiTF.getText() + "_" + ((String) musteriCB.getSelectionModel().getSelectedItem()) + "_" + raporNoTF.getText()) + ".pdf";
                System.out.println(a);
                File outputFile = new File(a);
                doc.save(outputFile);
                doc.close();

                try {
                    HttpEntity entity = MultipartEntityBuilder.create()
                            .addPart("file", new FileBody(outputFile))
                            .build();

                    HttpPost request = new HttpPost("http://165.22.76.96:5000/upload");
                    request.setEntity(entity);

                    CloseableHttpClient client = HttpClientBuilder.create().build();
                    HttpResponse response = client.execute(request);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (Exception e) {
            }
        } else {
            dialogError("Sie können kein PDF erstellen oder Excel erstellen, ohne alle Lücken zu füllen");
        }
    }

    public void Export_Excel() throws IOException {
        if (deger > 0) {
            TextInputDialog dialog = new TextInputDialog("Directory");
            dialog.setTitle("Directory");
            dialog.setHeaderText("Bitte Geben sie das Verzeichnis zum Speichern");
            dialog.setContentText("ID :");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            String directory = result.get();
            if (result.isPresent()) {

                System.out.println("ID: " + result.get());
            }

            String MusteriIsim = (String) musteriCB.getSelectionModel().getSelectedItem();
            String projectAdiTFValue = projeAdiTF.getText();
            String testYeriTFValue = testYeriTF.getText();
            String MuayeneStandartiTFValue = muayeneStandartiTF.getText();
            String DegerlendirmeStandartiTFValue = degerlendirmeStandartıTF.getText();
            String MuayeneProseduruTFValue = muayeneProseduruTF.getText();
            String muayeneKapsamiCBValue = String.valueOf(muayeneKapsamiCB.getSelectionModel().getSelectedItem());
            String resimNoTFValue = resimNoTF.getText();
            String yuzeyDurumuCBValue = (String) yuzeyDurumuCB.getSelectionModel().getSelectedItem();
            String muayeneAsamasiCBValue = (String) muayeneAsamasiCB.getSelectionModel().getSelectedItem();
            String SayfaNoTFValue = sayfaNoTF.getText();
            String RaporNoTFValue = raporNoTF.getText();
            String raporTarihiTFValue = raporTarihiTF.getText();
            String isEmriNoTFValue = isEmriNoTF.getText();
            String teklifNoTFValue = teklifNoTF.getText();
            String kutupMesafesiTFValue = kutupMesafesiTF.getText();
            String muayeneBolgesiTFValue = muayeneBolgesiTF.getText();
            String yuzeySicakligiTFValue = yuzeySicakligiTF.getText();
            String cihazCBValue = (String) cihazCB.getSelectionModel().getSelectedItem();
            String akimTipiCBValue = (String) akimTipiCB.getSelectionModel().getSelectedItem();
            String muayeneBolgesiSiddetiTFValue = muayeneBolgesiSiddetiTF.getText();
            String mpTasiyiciOrtamTFValue = mpTasiyiciOrtamTF.getText();
            String luxMetreTFValue = luxmetreTF.getText();
            String miknatislamaTeknigiTFValue = miknatislamaTeknigiTF.getText();
            String UVIsikSiddetiTFValue = UVIsikSiddetiTF.getText();
            String isikCihaziTanimiTFValue = isikCihaziTanimiTF.getText();
            String muayeneOrtamiTFValue = muayeneOrtamiTF.getText();
            String miknatisGiderimiTFValue = miknatisGiderimiTF.getText();
            String ısikMesafesiTFValue = isikMesafesiTF.getText();
            String isilIslemTFValue = isilIslemTF.getText();
            String yuzeyTFValue = yuzeyTF.getText();
            String kaldirmaTestiTarihNoTFValue = kaldirmaTestiTarihNoTF.getText();
            String standartSapmalarTFValue = standartSapmalarTF.getText();
            String muayeneTarihleriTFValue = muayeneTarihleriTF.getText();
            String aciklamaEklerTFValue = aciklamaEklerTF.getText();
            String kaynakParcaNoTFValue = kaynakParcaNoTF.getText();
            String kontrolUzunTFValue = kontrolUzunTF.getText();
            String capTFValue = capTF.getText();
            String hataTipiTFValue = hataTipiTF.getText();
            String hataninYeriTFValue = hataninYeriTF.getText();
            String sonucCBValue = (String) sonucCB.getSelectionModel().getSelectedItem();
            String kaynakYonTFValue = kaynakYonTF.getText();
            String kalinlikTFValue = kalinlikTF.getText();
            String operatorCBValue = (String) operatorCB.getSelectionModel().getSelectedItem();
            String degerlendirenCBValue = (String) degerlendirenCB.getSelectionModel().getSelectedItem();
            String onayCBValue = (String) onayCB.getSelectionModel().getSelectedItem();
            String operatorLevelTFValue = operatorLevelTF.getText();
            String degerlendirenLevelTFValue = degerlendirenLevelTF.getText();
            String onayLevelTFValue = onayLevelTF.getText();
            String operatorTarihTFValue = operatorTarihTF.getText();
            String degerlendirenTarihTFValue = degerlendirenTarihTF.getText();
            String onayTarihTFValue = onayTarihTF.getText();
            FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\umuty\\Desktop\\deneme\\manyetikrapor.xlsx"));
            XSSFWorkbook xssfWB = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = xssfWB.getSheetAt(0);
            CellStyle style = xssfWB.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setBorderTop(BorderStyle.MEDIUM);
            style.setBorderBottom(BorderStyle.MEDIUM);
            style.setBorderLeft(BorderStyle.MEDIUM);
            style.setBorderRight(BorderStyle.MEDIUM);
            XSSFRow row1 = sheet.getRow(2);
            XSSFCell cell = row1.createCell(3);
            XSSFCell cell4 = row1.createCell(19);
            XSSFCell cell9 = row1.createCell(26);
            cell.setCellValue(MusteriIsim);
            cell.setCellStyle(style);
            cell4.setCellValue(MuayeneProseduruTFValue);
            cell4.setCellStyle(style);
            cell9.setCellValue(SayfaNoTFValue);
            cell9.setCellStyle(style);
            XSSFRow row2 = sheet.getRow(3);
            XSSFCell cell0 = row2.createCell(3);
            cell0.setCellValue(projectAdiTFValue);
            cell0.setCellStyle(style);
            XSSFCell cell5 = row2.createCell(19);
            cell5.setCellValue(muayeneKapsamiCBValue);
            cell5.setCellStyle(style);
            XSSFCell cell10 = row2.createCell(26);
            cell10.setCellValue(RaporNoTFValue);
            cell10.setCellStyle(style);
            XSSFRow row3 = sheet.getRow(4);
            XSSFCell cell1 = row3.createCell(3);
            cell1.setCellValue(testYeriTFValue);
            cell1.setCellStyle(style);
            XSSFCell cell6 = row3.createCell(19);
            cell6.setCellValue(resimNoTFValue);
            cell6.setCellStyle(style);
            XSSFCell cell11 = row3.createCell(26);
            cell11.setCellValue(raporTarihiTFValue);
            cell11.setCellStyle(style);
            XSSFRow row4 = sheet.getRow(5);
            XSSFCell cell2 = row4.createCell(3);
            cell2.setCellValue(MuayeneStandartiTFValue);
            cell2.setCellStyle(style);
            XSSFCell cell7 = row4.createCell(19);
            cell7.setCellValue(yuzeyDurumuCBValue);
            cell7.setCellStyle(style);
            XSSFCell cell12 = row4.createCell(26);
            cell12.setCellValue(isEmriNoTFValue);
            cell12.setCellStyle(style);
            XSSFRow row5 = sheet.getRow(6);
            XSSFCell cell3 = row5.createCell(3);
            cell3.setCellValue(DegerlendirmeStandartiTFValue);
            cell3.setCellStyle(style);
            XSSFCell cell8 = row5.createCell(19);
            cell8.setCellValue(muayeneAsamasiCBValue);
            cell8.setCellStyle(style);
            XSSFCell cell13 = row5.createCell(26);
            cell13.setCellValue(teklifNoTFValue);
            cell13.setCellStyle(style);
            XSSFRow row6 = sheet.getRow(8);
            XSSFCell cell14 = row6.createCell(4);
            cell14.setCellValue(kutupMesafesiTFValue);
            cell14.setCellStyle(style);
            XSSFCell cell20 = row6.createCell(16);
            cell20.setCellValue(muayeneBolgesiTFValue);
            cell20.setCellStyle(style);
            XSSFCell cell26 = row6.createCell(25);
            cell26.setCellValue(yuzeySicakligiTFValue);
            cell26.setCellStyle(style);
            XSSFRow row7 = sheet.getRow(9);
            XSSFCell cell15 = row7.createCell(4);
            cell15.setCellValue(cihazCBValue);
            cell.setCellStyle(style);
            XSSFCell cell21 = row7.createCell(16);
            cell21.setCellValue(akimTipiCBValue);
            cell21.setCellStyle(style);
            XSSFCell cell27 = row7.createCell(25);
            cell27.setCellValue(muayeneBolgesiSiddetiTFValue);
            cell27.setCellStyle(style);
            XSSFRow row8 = sheet.getRow(10);
            XSSFCell cell16 = row8.createCell(4);
            cell16.setCellValue(mpTasiyiciOrtamTFValue);
            cell16.setCellStyle(style);
            XSSFCell cell22 = row8.createCell(16);
            cell22.setCellValue(luxMetreTFValue);
            cell22.setCellStyle(style);
            XSSFRow row9 = sheet.getRow(11);
            XSSFCell cell17 = row9.createCell(4);
            cell17.setCellValue(miknatislamaTeknigiTFValue);
            cell17.setCellStyle(style);
            XSSFCell cell23 = row9.createCell(16);
            cell23.setCellValue(muayeneOrtamiTFValue);
            cell23.setCellStyle(style);
            XSSFCell cell28 = row9.createCell(25);
            cell28.setCellValue(yuzeyTFValue);
            cell28.setCellStyle(style);
            XSSFRow row10 = sheet.getRow(12);
            XSSFCell cell18 = row10.createCell(4);
            cell18.setCellValue(UVIsikSiddetiTFValue);
            cell18.setCellStyle(style);
            XSSFCell cell24 = row10.createCell(16);
            cell24.setCellValue(miknatisGiderimiTFValue);
            cell24.setCellStyle(style);
            XSSFCell cell29 = row10.createCell(25);
            cell29.setCellValue(isikCihaziTanimiTFValue);
            cell29.setCellStyle(style);
            XSSFRow row11 = sheet.getRow(13);
            XSSFCell cell19 = row11.createCell(4);
            cell19.setCellValue(ısikMesafesiTFValue);
            cell19.setCellStyle(style);
            XSSFCell cell25 = row11.createCell(16);
            cell25.setCellValue(isilIslemTFValue);
            cell25.setCellStyle(style);
            XSSFCell cell30 = row11.createCell(25);
            cell30.setCellValue(kaldirmaTestiTarihNoTFValue);
            cell30.setCellStyle(style);
            XSSFRow row12 = sheet.getRow(19);
            XSSFCell cell31 = row12.createCell(7);
            cell31.setCellValue(standartSapmalarTFValue);
            cell31.setCellStyle(style);
            XSSFRow row13 = sheet.getRow(20);
            XSSFCell cell32 = row13.createCell(7);
            cell32.setCellValue(muayeneTarihleriTFValue);
            cell32.setCellStyle(style);
            XSSFRow row14 = sheet.getRow(21);
            XSSFCell cell33 = row14.createCell(7);
            cell33.setCellValue(aciklamaEklerTFValue);
            cell33.setCellStyle(style);
            XSSFRow row24 = sheet.getRow(24);
            XSSFCell cell34 = row24.createCell(1);
            cell34.setCellValue(kaynakParcaNoTFValue);
            cell34.setCellStyle(style);
            XSSFCell cell35 = row24.createCell(8);
            cell35.setCellValue(kontrolUzunTFValue);
            cell35.setCellStyle(style);
            XSSFCell cell36 = row24.createCell(11);
            cell36.setCellValue(kaynakYonTFValue);
            cell36.setCellStyle(style);
            XSSFCell cell37 = row24.createCell(17);
            cell37.setCellValue(kalinlikTFValue);
            cell37.setCellStyle(style);
            XSSFCell cell38 = row24.createCell(18);
            cell38.setCellValue(capTFValue);
            cell38.setCellStyle(style);
            XSSFCell cell39 = row24.createCell(22);
            cell39.setCellValue(hataTipiTFValue);
            cell39.setCellStyle(style);
            XSSFCell cell40 = row24.createCell(24);
            cell40.setCellValue(hataninYeriTFValue);
            cell40.setCellStyle(style);
            XSSFCell cell41 = row24.createCell(27);
            cell41.setCellValue(sonucCBValue);
            cell41.setCellStyle(style);
            XSSFRow row39 = sheet.getRow(39);
            XSSFCell cell42 = row39.createCell(5);
            cell42.setCellValue(operatorCBValue);
            cell42.setCellStyle(style);
            XSSFCell cell43 = row39.createCell(15);
            cell43.setCellValue(degerlendirenCBValue);
            cell43.setCellStyle(style);
            XSSFCell cell44 = row39.createCell(20);
            cell44.setCellValue(onayCBValue);
            XSSFRow row40 = sheet.getRow(40);
            XSSFCell cell45 = row40.createCell(5);
            cell45.setCellValue(operatorLevelTFValue);
            cell45.setCellStyle(style);
            XSSFCell cell46 = row40.createCell(15);
            cell46.setCellValue(degerlendirenLevelTFValue);
            cell46.setCellStyle(style);
            XSSFCell cell47 = row40.createCell(20);
            cell47.setCellValue(onayLevelTFValue);
            cell47.setCellStyle(style);
            XSSFRow row41 = sheet.getRow(41);
            XSSFCell cell48 = row41.createCell(5);
            cell48.setCellStyle(style);
            cell48.setCellValue(operatorTarihTFValue);
            XSSFCell cell49 = row41.createCell(15);
            cell49.setCellValue(degerlendirenTarihTFValue);
            cell49.setCellStyle(style);
            XSSFCell cell50 = row41.createCell(20);
            cell50.setCellValue(onayTarihTFValue);
            cell50.setCellStyle(style);
            FileOutputStream outputStream = new FileOutputStream(directory + "\\" + (projectAdiTFValue + "_" + MusteriIsim + "_" + RaporNoTFValue) + ".xlsx");
            System.out.println(directory + "\\" + (projectAdiTFValue + "_" + MusteriIsim) + ".xlsx");
            xssfWB.write(outputStream);
            inputStream.close();
            xssfWB.close();
            outputStream.close();

            try {

                File efile = new File(directory + "\\" + (projectAdiTFValue + "_" + MusteriIsim + "_" + RaporNoTFValue) + ".xlsx");
                HttpEntity entity = MultipartEntityBuilder.create().addPart("file", new FileBody(efile)).build();

                HttpPost request = new HttpPost("http://165.22.76.96:5000/upload");
                request.setEntity(entity);

                CloseableHttpClient client = HttpClientBuilder.create().build();
                HttpResponse response = client.execute(request);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        } else {
            dialogError("Sie können kein PDF erstellen oder Excel erstellen, ohne alle Lücken zu füllen");
        }
    }

    public void degeriArttir() {
        deger++;
    }

    public String firstWordGiver(String b) {
        String firstWord = b;
        if (firstWord.contains(" ")) {
            firstWord = firstWord.substring(0, firstWord.indexOf(" "));
            System.out.println("first word" + firstWord);
        }
        return firstWord;
    }

}
