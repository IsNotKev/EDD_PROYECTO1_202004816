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
public class Pila {
    private Nodo cabecera;
    
    public class Nodo{
        public int info;
        public Nodo next = null;
        
        public Nodo(int info){
            this.info = info;
        }    
    }
    
    public void push(int info){
        Nodo nuevonodo = new Nodo(info);
        nuevonodo.next = cabecera;
        cabecera = nuevonodo;
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
