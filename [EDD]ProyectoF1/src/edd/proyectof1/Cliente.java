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
    private int ventanilla;
    private int cant;

    public Cliente(String nombre, int id, Lista imgs, int cant) {
        this.nombre = nombre;
        this.id = id;
        this.imgs = imgs;
        this.ventanilla = 0;
        this.cant = cant;
    }

    public int getCant() {
        return cant;
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getVentanilla() {
        return ventanilla;
    }

    public void setVentanilla(int ventanilla) {
        this.ventanilla = ventanilla;
    }
    
    public int getId() {
        return id;
    }

    public Lista getImgs() {
        return imgs;
    }

    public void setImgs(Lista imgs) {
        this.imgs = imgs;
    }
  
}
