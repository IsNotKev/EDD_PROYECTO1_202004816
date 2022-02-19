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
public class Cola {
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
    
    public Cliente deletC(){
        Cliente aux = null;
        if(cabecera != null){
            aux = (Cliente)cabecera.info;      
            cabecera = cabecera.next;
        }      
        return aux;
    }
    
    /*public Imagen deleteImg(){
        Imagen aux = null;
        if(cabecera != null){
            aux = (Imagen)cabecera.info;      
            cabecera = cabecera.next;
        }      
        return aux;
    }*/
    
    public void imprimirC(){
     
        if(cabecera != null){
            if(EDDProyectoF1.imprimir){
                
                EDDProyectoF1.espera.entregar((Imagen)cabecera.info);               
                EDDProyectoF1.imprimir = false;
                
                cabecera = cabecera.next;
                
            }else{
                EDDProyectoF1.imprimir = true;
            }
        }
        
    }
    
    public void imprimirBW(){        
        if(cabecera != null){
            EDDProyectoF1.espera.entregar((Imagen)cabecera.info);
            cabecera = cabecera.next;
        }
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
    
    public void imprimirImpresoras(){
        Nodo aux = cabecera;
        
        while(aux != null){
            Imagen n = (Imagen)aux.info;
            System.out.println("Imagen Con ID " + n.getId());
            aux = aux.next;
        }
    }
}
