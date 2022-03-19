/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author kevin
 */
public class Lista {
    Nodo cabecera;
    
    public class Nodo{        
        Object info;
        Nodo next;

        public Nodo(Object info) {
            this.info = info;
            this.next = null;
        }       
    }
    
    public void add(Object info){
            Nodo nodonuevo = new Nodo(info);
            if(cabecera == null){
                cabecera = nodonuevo;
            }
            else{
                Nodo aux = cabecera;
                while(aux.next != null){
                    aux=aux.next;
                }
                aux.next = nodonuevo;
            }
    }
    
}
