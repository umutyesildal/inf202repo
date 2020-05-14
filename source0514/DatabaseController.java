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
   HomePageController hpg = new HomePageController();
   
   Scanner myObj = new Scanner(System.in);
   
       private Connection ConnectLogIn(){
      Connection conn = null;
      String url = "jdbc:sqlite:LogIn.db";
        try{
            conn = DriverManager.getConnection(url);
        } catch(SQLException e){
            System.out.println("no connection");           
        }
        return conn;
    }
       
       private void check(String username,String Password){
        int i = 0;
        try{Connection conn = this.ConnectDB();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from [operatoren]");
        while(rs.next()){
        if((rs.getString("Username")==username) && (rs.getString("Password")==Password)){
        System.out.println("Logged In");
        i++;
        }
        }
        
        if(i == 1){
        hpg.successMessage();
        }else{
            System.out.println("Giris basarisiz");
        }
        }
        catch(SQLException e){
        System.out.println("Data couldn't be gathered.");
        }     
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
      
    
    public void personEkle(String name,String Nachname,String Firmname){                    
    String sqlEkle = "INSERT INTO [operatoren](Name,Nachname,FirmName) VALUES(?,?,?)";        
            try (Connection conn = this.ConnectDB();
            PreparedStatement pstmt = conn.prepareStatement(sqlEkle)) {
            pstmt.setString(1, name);
            pstmt.setString(2, Nachname);
            pstmt.setString(3, Firmname);
            pstmt.executeUpdate();
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
    public void personDuzenle(int idDuzenle,String nameDuzenle,String NachnameDuzenle,String FirmnameDuzenle){
    String sql = "UPDATE [operatoren] SET Name = ? ,"
        + "Nachname = ? ,"
        + "Firmname = ?"       
        + "WHERE ID = ?";
        try (Connection conn = this.ConnectDB();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, nameDuzenle);
            pstmt.setString(2, NachnameDuzenle);
            pstmt.setString(3, FirmnameDuzenle);
            pstmt.setInt(4, idDuzenle);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
