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
public class ListaCircular {
    private Nodo inicio;
    
    public class Nodo{        
        Object info;
        Nodo next;
        Nodo anterior;

        public Nodo(Object info) {
            this.info = info;
            this.next = null;
            this.anterior = null;
        }       
    }
    
    public void add(Object info){
        Nodo nuevo = new Nodo(info);
        
        if(inicio == null){
            inicio = nuevo;
            inicio.next = inicio;
            inicio.anterior = inicio;
        }else{
            Nodo aux = inicio;
            while(aux.next != inicio){
                aux = aux.next;
            }
            
            nuevo.anterior = aux;
            nuevo.next = inicio;
            aux.next = nuevo;
            inicio.anterior = nuevo;
            
        }
    }
    
    public void entregar(Imagen img){
        Nodo aux = inicio;
        
        if(aux.next == inicio){
            Cliente cliente = (Cliente)aux.info;
            if(cliente.getId() == img.getId()){
                cliente.getImgs().add(img);
                
                if(img.isTipo()){
                    System.out.println("Imagen A Color Entregada a Cliente " + cliente.getId());
                }else{
                    System.out.println("Imagen Blanco Y Negro Entregada a Cliente " + cliente.getId());
                }
                               
            }
        }else{
            Cliente n1 = (Cliente)aux.info;
            if(n1.getId() == img.getId()){
                n1.getImgs().add(img);

                if(img.isTipo()){
                    System.out.println("Imagen A Color Entregada a Cliente " + n1.getId());
                }else{
                    System.out.println("Imagen Blanco Y Negro Entregada a Cliente " + n1.getId());
                }
            }
                     
            aux = aux.next;
            while(aux != inicio){
                Cliente cliente = (Cliente)aux.info;
                if(cliente.getId() == img.getId()){
                    cliente.getImgs().add(img);

                    if(img.isTipo()){
                        System.out.println("Imagen A Color Entregada a Cliente " + cliente.getId());
                    }else{
                        System.out.println("Imagen Blanco Y Negro Entregada a Cliente " + cliente.getId());
                    }
                }
                aux = aux.next;
            }
        }      
    }
    
    public void imprimir(){
        Nodo aux = inicio;
        
        if(aux != null){
            if(aux.next == inicio){
                Cliente n = (Cliente)aux.info;
                System.out.println("Cliente " + n.getId() + " Esperando");
            }else{
                Cliente n1 = (Cliente)aux.info;
                System.out.println("Cliente " + n1.getId() + " Esperando");
                aux = aux.next;
                while(aux != inicio){
                    Cliente n = (Cliente)aux.info;
                    System.out.println("Cliente " + n.getId() + " Esperando");
                    aux = aux.next;
                }
            }
        }
    }
    
    public void atendidos(){
        Nodo aux = inicio;
        
        if(aux != null){
            if(aux.next == inicio){
                Cliente n = (Cliente)aux.info;
                if(n.getCant() == n.getImgs().tamano()){
                    EDDProyectoF1.atendidos.add(n);
                    inicio = null;
                }
            }else{
                Cliente n = (Cliente)aux.info;
                if(n.getCant() == n.getImgs().tamano()){
                    EDDProyectoF1.atendidos.add(n);
                    Nodo aux1 = aux.anterior;
                    Nodo aux2 = aux.next;
                    
                    inicio = aux2;
                    inicio.anterior = aux1;
                    aux1.next = inicio;                    
                }
                aux = aux.next;
            }
        }       
    }
}
