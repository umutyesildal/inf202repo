/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf202;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author umuty
 */

public class HomePageController implements Initializable {
  //  DatabaseController db = new DatabaseController();


    @FXML
    Button button;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    DatabaseController db = new DatabaseController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void successMessage(){
        System.out.println("giris yapıldı");
    }
    
    public void buttonLogIn(ActionEvent event) throws IOException{
        
      String deneme1 = username.getText();
      String deneme2 = password.getText();
      
      System.out.println(deneme1 + " " + deneme2);
      
        int i = 0;
        try{Connection conn = db.ConnectLogIn();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from [Users]");
        while(rs.next()){
        System.out.println(rs.getString("Username")+" " + rs.getString("Password"));
        if((rs.getString("Username").equals(deneme1)) && (rs.getString("Password").equals(deneme2))){
        System.out.println("Logged In");
        i++;
        }
        }
        }catch(SQLException e){
        System.out.println("Data couldn't be gathered.");
        }
      
        if(i == 0){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText("Username and password does not match.");
        alert.showAndWait();
        }   else{
        Parent loader = FXMLLoader.load(getClass().getResource("LoggedInPage.fxml"));//Creates a Parent called loader and assign it as ScReen2.FXML
        Scene scene = new Scene(loader); //This creates a new scene called scene and assigns it as the Sample.FXML document which was named "loader"
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //this accesses the window.
        app_stage.setScene(scene); //This sets the scene as scene
        app_stage.show(); // this shows the scene            
        }
    }
          public void buttonclickedSigned(ActionEvent event) throws IOException //This method loads a new scene in a current window
{
    //note that on this line you can substitue "Screen2.fxml" for a string chosen prior to this line.
    Parent loader = FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));//Creates a Parent called loader and assign it as ScReen2.FXML

    Scene scene = new Scene(loader); //This creates a new scene called scene and assigns it as the Sample.FXML document which was named "loader"

    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //this accesses the window.

    app_stage.setScene(scene); //This sets the scene as scene

    app_stage.show(); // this shows the scene
    
}
          
     
}
