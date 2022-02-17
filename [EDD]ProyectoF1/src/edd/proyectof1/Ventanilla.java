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
public class Ventanilla {
    
    private int id;
    private Cliente cliente;
    private Pila imgs;

    public Ventanilla(int id) {
        this.id = id;
        this.cliente = null;
        this.imgs = new Pila();
    }
       
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pila getImgs() {
        return imgs;
    }

    public void setImgs(Pila imgs) {
        this.imgs = imgs;
    }

      
    
}
