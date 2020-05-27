/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf202;

import java.sql.*;
import java.util.Scanner;
public class DatabaseController {
    
   Scanner myObj = new Scanner(System.in);
   
    private Connection ConnectDB(){
      Connection conn = null;
      String url = "jdbc:sqlite:fabrik.db";
        try{
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch(SQLException e){
            System.out.println("no connection");           
        }
        return conn;
    }  
    
    public void DBController(int IncomingNumber){
        switch(IncomingNumber){
            case 1:
           
            String sqlList = "select * from [operatoren]";
        
            try{Connection conn = this.ConnectDB();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sqlList);
                while(rs.next()){
                    System.out.println(rs.getInt("ID")+ " " + rs.getString("Name")+" " + rs.getString("Nachname")+" " +rs.getString("FirmName")+" ");
                }
            }
            catch(SQLException e){
                System.out.println("Data couldn't be gathered.");
            }      
            break;
             
            case 2:    
            String sqlSil = "DELETE FROM [operatoren] WHERE id = ?";
            System.out.println("Give id.");
            int id = myObj.nextInt();
        try (Connection conn = this.ConnectDB();
             PreparedStatement pstmt = conn.prepareStatement(sqlSil)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error.");
        }
            break;
            
            case 3:
                
        System.out.println("Enter Name");
        String name = myObj.nextLine();
        System.out.println("Enter Nachname");
        String Nachname = myObj.nextLine();
        System.out.println("Enter FirmName");
        String Firmname = myObj.nextLine();
        String sqlEkle = "INSERT INTO [operatoren](Name,Nachname,FirmName) VALUES(?,?,?)";
        
        try (Connection conn = this.ConnectDB();
             PreparedStatement pstmt = conn.prepareStatement(sqlEkle)) {
            pstmt.setString(1, name);
            pstmt.setString(2, Nachname);
            pstmt.setString(3, Firmname);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error.");
        }
    
                
            break;
        }
        
        
    }
    
}
