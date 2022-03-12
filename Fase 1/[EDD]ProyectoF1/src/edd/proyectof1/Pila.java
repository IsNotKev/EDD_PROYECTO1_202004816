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
    Nodo cabecera;
    
    public class Nodo{
        public Object info;
        public Nodo next = null;
        
        public Nodo(Object info){
            this.info = info;
        }    
    }
    
    public void push(Object info){
        Nodo nuevonodo = new Nodo(info);
        nuevonodo.next = cabecera;
        cabecera = nuevonodo;
    }
    
    public Object pop(){
        Object aux = cabecera.info;       
        cabecera = cabecera.next;        
        return aux;     
    }
    
    public void vaciarImgs(){
        Nodo aux = cabecera;
        
        while(aux!=null){
            Imagen n = (Imagen)aux.info;
            
            if(n.isTipo()){
                EDDProyectoF1.impresoraC.add(n);
            }else{
                EDDProyectoF1.impresoraBW.add(n);
            }
            
            aux = aux.next;
        }
        
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
