/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf202;

import java.sql.*;
import java.util.Scanner;
import javafx.collections.ObservableList;

public class DatabaseController {
   
   Scanner myObj = new Scanner(System.in);
   
      public Connection ConnectLogIn(){
      Connection conn = null;
      String url = "jdbc:sqlite:LogIn.db";
        try{
            conn = DriverManager.getConnection(url);
        } catch(SQLException e){
            System.out.println("no connection");           
        }
        return conn;
    }
      
    public Connection ConnectFirm(){
      Connection conn = null;
      String url = "jdbc:sqlite:Firm.db";
        try{
            conn = DriverManager.getConnection(url);
        } catch(SQLException e){
            System.out.println("no connection");           
        }
        return conn;  
    }
    
    public Connection ConnectReporten(){
      Connection conn = null;
      String url = "jdbc:sqlite:reporten.db";
        try{
            conn = DriverManager.getConnection(url);
        } catch(SQLException e){
            System.out.println("no connection");           
        }
        return conn;  
    }
   
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
    
        public Connection ConnectAusrustung(){
      Connection conn = null;
      String url = "jdbc:sqlite:Ausrustung.db";
        try{
            conn = DriverManager.getConnection(url);
        } catch(SQLException e){
            System.out.println("no connection");           
        }
        return conn;  
    }   
    
        public Connection ConnectYuzeyDurumu(){
      Connection conn = null;
      String url = "jdbc:sqlite:yuzeyDurumu.db";
        try{
            conn = DriverManager.getConnection(url);
        } catch(SQLException e){
            System.out.println("no connection");           
        }
        return conn;  
    }
        
        public Connection ConnectMuayeneAsamasi(){
      Connection conn = null;
      String url = "jdbc:sqlite:muayeneAsamasi.db";
        try{
            conn = DriverManager.getConnection(url);
        } catch(SQLException e){
            System.out.println("no connection");           
        }
        return conn;  
    }
        
        
        
    public void AddFirm(String FirmName,String Adress,String IsEmri,String TeklifNo){
            String firmEkle = "INSERT INTO [Firm](FirmName,Adress,IsEmri,TeklifNo) VALUES(?,?,?,?)";        
            try (Connection conn = this.ConnectFirm();
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
    
        public void yuzeyDurumuEkle(String yuzeyDurumu){
    String yuzeyDurumuEkle = "INSERT INTO [yuzeyDurumu](yuzeyDurumu) VALUES(?)";        
            try (Connection conn = this.ConnectYuzeyDurumu();
            PreparedStatement pstmt = conn.prepareStatement(yuzeyDurumuEkle)) {
            pstmt.setString(1, yuzeyDurumu);
            pstmt.executeUpdate();
            System.out.println("yuzeyDurumuEkleme completed.");
        }   catch (SQLException e) {
            System.out.println("Error.");
        }
    }
        public void muayeneAsamasiEkle(String muayeneAsamasi){
    String muayeneAsamasiEkle = "INSERT INTO [muayeneAsamasi](muayeneAsamasi) VALUES(?)";        
            try (Connection conn = this.ConnectMuayeneAsamasi();
            PreparedStatement pstmt = conn.prepareStatement(muayeneAsamasiEkle)) {
            pstmt.setString(1, muayeneAsamasi);
            pstmt.executeUpdate();
            System.out.println("muayeneAsamasiEkleme completed.");
        }   catch (SQLException e) {
            System.out.println("Error.");
        }
    }
    
    
        public void AddAusrustung(String Name,int KutupMesafesi,String MPTasiyiciOrtam,String MiknatislamaTeknigi,String UVIsikSiddeti,String IsikMesafesi){
            String AusrustungEkle = "INSERT INTO [Ekipman](Name,KutupMesafesi,MPTasiyiciOrtam,MiknatislamaTeknigi,UVIsikSiddeti,IsikMesafesi) VALUES(?,?,?,?,?,?)";        
            try (Connection conn = this.ConnectAusrustung();
            PreparedStatement pstmt = conn.prepareStatement(AusrustungEkle)) {
            pstmt.setString(1, Name);
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
      
    public void SignUp(String Username, String Password){
    String sqlSignUp = "INSERT INTO [Users](Username,Password) VALUES(?,?)";
            try(Connection conn = this.ConnectLogIn();
                PreparedStatement pstmt = conn.prepareStatement(sqlSignUp)){
                pstmt.setString(1, Username);
                pstmt.setString(2, Password);
                pstmt.executeUpdate();
            }catch (SQLException e){
                System.out.println("Error.");
            }
        
    }
    
    public void personEkle(String name,String Nachname,int Level,String Unterschrift){                    
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
    
    public void personSil(int id){
    String sqlSil = "DELETE FROM [operatoren] WHERE id = ?";
            try (Connection conn = this.ConnectDB();
            PreparedStatement pstmt = conn.prepareStatement(sqlSil)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }   catch (SQLException e) {
            System.out.println("Error.");
        }  
    }
    public void personDuzenle(int idDuzenle,String nameDuzenle,String NachnameDuzenle,int LevelDuzenle,String UnterschriftDuzenle){
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
