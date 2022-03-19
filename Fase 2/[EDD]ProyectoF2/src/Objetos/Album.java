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
    int id;
    Lista imgs;

    public Album(int id, Lista imgs) {
        this.id = id;
        this.imgs = imgs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lista getImgs() {
        return imgs;
    }

    public void setImgs(Lista imgs) {
        this.imgs = imgs;
    }
    
    
}
