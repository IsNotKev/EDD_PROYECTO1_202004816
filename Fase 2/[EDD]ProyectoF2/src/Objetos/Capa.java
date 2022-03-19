/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Estructuras.Matriz;

/**
 *
 * @author kevin
 */
public class Capa {
    int id;
    Matriz pixeles;

    public Capa(int id, Matriz pixeles) {
        this.id = id;
        this.pixeles = pixeles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Matriz getPixeles() {
        return pixeles;
    }

    public void setPixeles(Matriz pixeles) {
        this.pixeles = pixeles;
    }       
    
}
