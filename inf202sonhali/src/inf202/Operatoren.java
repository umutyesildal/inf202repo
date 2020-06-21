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
    public SimpleStringProperty name,nachname,Unterschrift;
    public SimpleIntegerProperty id,level;
    
public Operatoren(int id,int level,String name,String nachname,String Unterschrift){
    this.id = new SimpleIntegerProperty(id);
    this.level = new SimpleIntegerProperty(level);
    this.name = new SimpleStringProperty(name);
    this.nachname = new SimpleStringProperty(nachname);
    this.Unterschrift = new SimpleStringProperty(Unterschrift);

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

    public String getUnterschrift() {
        return Unterschrift.get();
    }

    public SimpleStringProperty UnterschriftProperty() {
        return Unterschrift;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }
    
    public int getLevel() {
        return level.get();
    }

    public SimpleIntegerProperty LevelProperty() {
        return level;
    }

}
