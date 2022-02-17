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
public class Lista {
    private Nodo cabecera;
    
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
    
    public void vaciar(){
        cabecera = null;
    }
    
    public void imprimir(){
            String n = "";
            Nodo aux = cabecera;
            while(aux != null){
                n += aux.info;
                n += "-";
                aux=aux.next;
            }
            System.out.println(n);
    }
}
