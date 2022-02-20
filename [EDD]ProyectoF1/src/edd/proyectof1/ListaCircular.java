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
    
    public void delete(int id){
        Nodo aux = inicio;
        
        if(aux!=null){
            if(aux.next == inicio){
                Cliente n = (Cliente)aux.info;            
                if(n.getId() == id){
                    inicio = null;
                }
            }else{
                Cliente n = (Cliente)aux.info;            
                if(n.getId() == id){
                    Nodo aux2 = aux.next;
                    Nodo aux3 = aux.anterior;
                    
                    inicio = aux2;
                    aux2.anterior = aux3;
                    aux3.next = inicio;
                    
                }else{
                    aux = aux.next;
                    while(aux != inicio){
                        Cliente n1 = (Cliente)aux.info;
                        if(n1.getId() == id){
                           Nodo aux2 = aux.next;
                           Nodo aux3 = aux.anterior;
                           
                           aux3.next = aux2;
                           aux2.anterior = aux3;
                           break;
                        }                        
                        aux = aux.next;
                    }
                }
            }                        
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
                    System.out.println("Cliente " + n.getId() + " Atendido Con " + n.pasos + " Pasos.");
                    EDDProyectoF1.atendidos.add(n);
                }
            }else{
                Cliente n = (Cliente)aux.info;
                if(n.getCant() == n.getImgs().tamano()){
                    System.out.println("Cliente " + n.getId() + " Atendido Con " + n.pasos + " Pasos.");
                    EDDProyectoF1.atendidos.add(n); 
                }else{
                    aux = aux.next;               
                    while(aux != inicio){
                        Cliente n1 = (Cliente)aux.info;
                        if(n1.getCant() == n1.getImgs().tamano()){
                            EDDProyectoF1.atendidos.add(n);   
                            System.out.println("Cliente " + n1.getId() + " Atendido Con " + n1.pasos + " Pasos.");
                        }
                        aux = aux.next;
                    } 
                }                           
            }
        }       
    }
    
    public void graficarEspera(){
    
    
    }
    
    public void sumarPaso(){
        Nodo aux = inicio;
        
        if(aux != null){
            if(aux.next == inicio){
                Cliente n = (Cliente)aux.info;
                n.pasos += 1;
            }else{
                Cliente n1 = (Cliente)aux.info;
                n1.pasos += 1;
                aux = aux.next;
                while(aux != inicio){
                    Cliente n = (Cliente)aux.info;
                    n.pasos += 1;
                    aux = aux.next;
                }
            }
        }
        
        
    }
}
