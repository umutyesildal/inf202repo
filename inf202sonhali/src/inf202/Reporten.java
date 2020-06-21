/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf202;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author umuty
 */
public class Reporten {
public SimpleStringProperty berichtName,kundeName;
public SimpleIntegerProperty berichtNummer;
    
public Reporten(int berichtNummer,String berichtName,String kundeName){
    this.berichtNummer = new SimpleIntegerProperty(berichtNummer);
    this.berichtName = new SimpleStringProperty(berichtName);
    this.kundeName = new SimpleStringProperty(kundeName);

}    
    
    public static void main(String[] args) {
    }
        
    public int getBerichtNummer(){
        return berichtNummer.get();
    }

    public SimpleIntegerProperty berichtNummerProperty() {
        return berichtNummer;
    }    
    public String getBerichtName() {
        return berichtName.get();
    }

    public SimpleStringProperty berichtNameProperty() {
        return berichtName;
    }    
    public String getKundeName() {
        return kundeName.get();
    }

    public SimpleStringProperty kundeNameProperty() {
        return kundeName;
    }    
        

}
