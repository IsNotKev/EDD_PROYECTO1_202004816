/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyectof1;

import java.util.HashSet;
import java.util.Set;

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
    
    public int estaVacia(){
        
        Nodo aux = cabecera;
        
        while(aux != null){
            Ventanilla ver = (Ventanilla)aux.info;
            if(ver.getCliente() == null ){
                return ver.getID();
            }
            aux = aux.next;
        }
        
        return 0;
    }
    
    public void asignarCliente(Cliente nCliente, int id){
        Nodo aux = cabecera;
        nCliente.setVentanilla(id);
        
        while(aux != null){
            Ventanilla ver = (Ventanilla)aux.info;
            if(ver.getID() == id){
                ver.setCliente(nCliente);
                System.out.println("El Cliente " + nCliente.getId() + " INGRESA A VENTANILLA " + id);
                break;
            }
            aux = aux.next;
        }
    }
    
    public void darImg(int excepcion){
        Nodo aux = cabecera;
        while(aux!=null){
            Ventanilla ver = (Ventanilla)aux.info;
            if(ver.getID() != excepcion){
                if (ver.getCliente() != null) {
                    ver.recibirImg();           
                }
            }
            aux = aux.next;
        }
    }
    
    public Imagen deletImg(){
        Imagen aux = null;
        if(cabecera != null){
            aux = (Imagen)cabecera.info;      
            cabecera = cabecera.next;
        }      
        return aux;
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
    
    public int tamano(){
        int contador = 0;
        
        Nodo aux = cabecera;
        
        while(aux != null){
            contador += 1;
            aux = aux.next;
        }           
        return contador;
    }
    
}
