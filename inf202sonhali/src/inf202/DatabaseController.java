/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf202;

import java.sql.*;
import java.util.Scanner;
import javafx.collections.ObservableList;

public class DatabaseController { //Database connection
   
   Scanner myObj = new Scanner(System.in);
      
    public Connection ConnectDB(){
      Connection conn = null;
      String url = "jdbc:sqlite:fabrik.db";
        try{
            conn = DriverManager.getConnection(url);
        } catch(SQLException e){
            System.out.println("no connection");           
        }
        return conn;
    }  
        
    public void AddFirm(String FirmName,String Adress,String IsEmri,String TeklifNo){ // adding Firm to the database
            String firmEkle = "INSERT INTO [Firm](FirmName,Adress,IsEmri,TeklifNo) VALUES(?,?,?,?)";        
            try (Connection conn = this.ConnectDB();
            PreparedStatement pstmt = conn.prepareStatement(firmEkle)) {
            pstmt.setString(1, FirmName);
            pstmt.setString(2, Adress);
            pstmt.setString(3, IsEmri);
            pstmt.setString(4, TeklifNo);
            pstmt.executeUpdate();
            System.out.println("Sign Up Completed.");
        }   catch (SQLException e) {
            System.out.println("Error.");
        }  
    }
    
        public void yuzeyDurumuEkle(String yuzeyDurumu){ // adding yuzeyDurumu to the database
    String yuzeyDurumuEkle = "INSERT INTO [yuzeyDurumu](yuzeyDurumu) VALUES(?)";        
            try (Connection conn = this.ConnectDB();
            PreparedStatement pstmt = conn.prepareStatement(yuzeyDurumuEkle)) {
            pstmt.setString(1, yuzeyDurumu);
            pstmt.executeUpdate();
            System.out.println("yuzeyDurumuEkleme completed.");
        }   catch (SQLException e) {
            System.out.println("Error.");
        }
    }
    public void berichtEkle(int berichtNummer,String berichtName,String kundeName) { // adding bericht to the database
        String berichtEkle = "INSERT INTO [reporten](berichtNummer,berichtName,kundeName) VALUES(?,?,?)";
        try (Connection conn = this.ConnectDB();
            PreparedStatement pstmt = conn.prepareStatement(berichtEkle)) {
            pstmt.setInt(1, berichtNummer);
            pstmt.setString(2, berichtName);
            pstmt.setString(3, kundeName);
            pstmt.executeUpdate();
            System.out.println("report completed.");
        } catch (SQLException e) {
            System.out.println("Error.");
        }
    }
        public void muayeneAsamasiEkle(String muayeneAsamasi){// adding muayeneAsamasi to the database
    String muayeneAsamasiEkle = "INSERT INTO [muayeneAsamasi](muayeneAsamasi) VALUES(?)";        
            try (Connection conn = this.ConnectDB();
            PreparedStatement pstmt = conn.prepareStatement(muayeneAsamasiEkle)) {
            pstmt.setString(1, muayeneAsamasi);
            pstmt.executeUpdate();
            System.out.println("muayeneAsamasiEkleme completed.");
        }   catch (SQLException e) {
            System.out.println("Error.");
        }
    }
    
    
        public void AddAusrustung(String ekipmanName,int KutupMesafesi,String MPTasiyiciOrtam,String MiknatislamaTeknigi,String UVIsikSiddeti,String IsikMesafesi){// adding Ekipman to the database
            String AusrustungEkle = "INSERT INTO [Ekipman](ekipmanName,KutupMesafesi,MPTasiyiciOrtam,MiknatislamaTeknigi,UVIsikSiddeti,IsikMesafesi) VALUES(?,?,?,?,?,?)";        
            try (Connection conn = this.ConnectDB();
            PreparedStatement pstmt = conn.prepareStatement(AusrustungEkle)) {
            pstmt.setString(1, ekipmanName);
            pstmt.setInt(2, KutupMesafesi);
            pstmt.setString(3, MPTasiyiciOrtam);
            pstmt.setString(4, MiknatislamaTeknigi);
            pstmt.setString(5, UVIsikSiddeti);
            pstmt.setString(6, IsikMesafesi);
            pstmt.executeUpdate();
            System.out.println("Sign Up Completed.");
        }   catch (SQLException e) {
            System.out.println("Error.");
        }  
    }
      
    public void SignUp(String Username, String Password){// adding user to the database
    String sqlSignUp = "INSERT INTO [Users](Username,Password) VALUES(?,?)";
            try(Connection conn = this.ConnectDB();
                PreparedStatement pstmt = conn.prepareStatement(sqlSignUp)){
                pstmt.setString(1, Username);
                pstmt.setString(2, Password);
                pstmt.executeUpdate();
            }catch (SQLException e){
                System.out.println("Error.");
            }
        
    }
    
    public void personEkle(String name,String Nachname,int Level,String Unterschrift){   // adding operator to the database                 
    String sqlEkle = "INSERT INTO [operatoren](Name,Nachname,Level,Unterschrift) VALUES(?,?,?,?)";        
            try (Connection conn = this.ConnectDB();
            PreparedStatement pstmt = conn.prepareStatement(sqlEkle)) {
            pstmt.setString(1, name);
            pstmt.setString(2, Nachname);
            pstmt.setInt(3, Level);
            pstmt.setString(4, Unterschrift);
            pstmt.executeUpdate();
            System.out.println("Sign Up Completed.");
        }   catch (SQLException e) {
            System.out.println("Error.");
        }    
    }
    
    public void personSil(int id){// deleting operator to the database
    String sqlSil = "DELETE FROM [operatoren] WHERE id = ?";
            try (Connection conn = this.ConnectDB();
            PreparedStatement pstmt = conn.prepareStatement(sqlSil)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }   catch (SQLException e) {
            System.out.println("Error.");
        }  
    }
    public void personDuzenle(int idDuzenle,String nameDuzenle,String NachnameDuzenle,int LevelDuzenle,String UnterschriftDuzenle){// editing operator to the database
    String sql = "UPDATE [operatoren] SET Name = ? ,"
        + "Nachname = ? ,"
        + "Level = ? ,"
        + "Unterschrift = ?"
        + "WHERE ID = ?";
        try (Connection conn = this.ConnectDB();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, nameDuzenle);
            pstmt.setString(2, NachnameDuzenle);
            pstmt.setInt(3, LevelDuzenle);
            pstmt.setString(4, UnterschriftDuzenle);
            pstmt.setInt(5, idDuzenle);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
      
}