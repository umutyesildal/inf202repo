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
public class Operatoren {
    public SimpleStringProperty name,nachname,firmname;
    public SimpleIntegerProperty id;
    
public Operatoren(int id,String name,String nachname,String firmname){
    this.id = new SimpleIntegerProperty(id);
    this.name = new SimpleStringProperty(name);
    this.nachname = new SimpleStringProperty(nachname);
    this.firmname = new SimpleStringProperty(firmname);

}
    public static void main(String[] args) {
 
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getNachname() {
        return nachname.get();
    }

    public SimpleStringProperty nachnameProperty() {
        return nachname;
    }

    public String getFirmname() {
        return firmname.get();
    }

    public SimpleStringProperty firmnameProperty() {
        return firmname;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }
}
