/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf202;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author umuty
 */
public class Operatoren {

    public  SimpleStringProperty id = new SimpleStringProperty();
    public  SimpleStringProperty name = new SimpleStringProperty();
    public  SimpleStringProperty nachname = new SimpleStringProperty();
    public  SimpleStringProperty firmname = new SimpleStringProperty();

    public String getName() {
        return name.get();
    }

    public void setName(String nameStr) {
        name.set(nameStr);
    }

    public String getNachname() {
        return nachname.get();
    }

    public void setNachname(String nachnameStr) {
        nachname.set(nachnameStr);
    }

    public String getFirmname() {
        return firmname.get();
    }

    public void setFirmname(String firmnameStr) {
        firmname.set(firmnameStr);
    }

    public String getId() {
        return id.get();
    }

    public void setIdNO(String idStr) {
        id.set(idStr);
    }
}
