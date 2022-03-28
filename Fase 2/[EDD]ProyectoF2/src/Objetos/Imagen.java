/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Estructuras.ABB;

/**
 *
 * @author kevin
 */
public class Imagen {
    int id;
    ABB capas;
    int cant;
    
    public Imagen(int id, ABB capas) {
        this.id = id;
        this.capas = capas;
    }

    public Imagen(int id, int cant) {
        this.id = id;
        this.cant = cant;
    }
  
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ABB getCapas() {
        return capas;
    }

    public void setCapas(ABB capas) {
        this.capas = capas;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
    
    
}
