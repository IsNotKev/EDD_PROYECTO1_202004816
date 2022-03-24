/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Estructuras.Lista;

/**
 *
 * @author kevin
 */
public class Album {
    String nombre;
    Lista imgs;

    public Album(String nombre, Lista imgs) {
        this.nombre = nombre;
        this.imgs = imgs;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lista getImgs() {
        return imgs;
    }

    public void setImgs(Lista imgs) {
        this.imgs = imgs;
    }
    
}
