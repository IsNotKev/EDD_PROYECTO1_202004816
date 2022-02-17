/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyectof1;

/**
 *
 * @author kevin
 */
public class Cliente {
    
    private String nombre;
    private int id;
    private Lista imgs;

    public Cliente(String nombre, int id, Lista imgs) {
        this.nombre = nombre;
        this.id = id;
        this.imgs = imgs;
    }
    

    public Lista getImgs() {
        return imgs;
    }

    public void setImgs(Lista imgs) {
        this.imgs = imgs;
    }
  
}
