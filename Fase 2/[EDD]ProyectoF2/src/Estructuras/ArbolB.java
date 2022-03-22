/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Cliente;

/**
 *
 * @author kevin
 */
public class ArbolB {
    
    Nodo raiz;
    
    public class Nodo{
        Cliente info1,info2,info3,info4;
        Nodo n0,n1,n2,n3,n4;
        
        public Nodo(Cliente n){
            info1 = n;
            info2=info3=info4=null;
            n0=n1=n2=n3=n4=null;
        }
        
        public Nodo(Cliente n_1, Cliente n_2){
            info1 = n_1;
            info2 = n_2;
            info3=info4=null;
            n0=n1=n2=n3=n4=null;
        } 
    }
    
    public ArbolB(){
        raiz = null;
    }
    
    public void insertar(Cliente nuevoCliente){
        raiz = insertarRecursivo(nuevoCliente,raiz);
        //System.out.println("Insertado");
    }
    
    public Nodo insertarRecursivo(Cliente cliente, Nodo raiz){
        if(raiz == null){
            raiz = new Nodo(cliente);
        }else if(!tengoHijos(raiz) && !estoyLleno(raiz)){
            raiz = ordenar(cliente,raiz);
        }else if(tengoHijos(raiz)){
            if(raiz.info1.getDpi()>cliente.getDpi()){
                raiz.n0 = insertarRecursivo(cliente,raiz.n0);
            }else if(raiz.info2.getDpi()>cliente.getDpi()){
                raiz.n1 = insertarRecursivo(cliente,raiz.n1);
            }else if(raiz.info3.getDpi()>cliente.getDpi()){
                raiz.n2 = insertarRecursivo(cliente,raiz.n2);
            }else if(raiz.info3.getDpi()>cliente.getDpi()){
                raiz.n3 = insertarRecursivo(cliente,raiz.n3);
            }else{
                raiz.n4 = insertarRecursivo(cliente,raiz.n4);
            }
        }else if(estoyLleno(raiz)){
            raiz = separacion(cliente, raiz);
        }
        
        return raiz;
    }
    
    public Nodo separacion(Cliente cliente, Nodo raiz){
        Nodo nuevo = null;
        if(raiz.info1.getDpi()>cliente.getDpi()){
            nuevo = new Nodo(raiz.info2);
            nuevo.n0 = new Nodo(cliente,raiz.info1);
            nuevo.n1 = new Nodo(raiz.info3, raiz.info4);
        }else if(raiz.info2.getDpi()>cliente.getDpi()){
            nuevo = new Nodo(raiz.info2);
            nuevo.n0 = new Nodo(raiz.info1,cliente);
            nuevo.n1 = new Nodo(raiz.info3, raiz.info4);
        }else if(raiz.info3.getDpi()>cliente.getDpi()){
            nuevo = new Nodo(cliente);
            nuevo.n0 = new Nodo(raiz.info1,raiz.info2);
            nuevo.n1 = new Nodo(raiz.info3, raiz.info4);
        }else if(raiz.info4.getDpi()>cliente.getDpi()){
            nuevo = new Nodo(raiz.info3);
            nuevo.n0 = new Nodo(raiz.info1,raiz.info2);
            nuevo.n1 = new Nodo(cliente, raiz.info4);
        }else{
            nuevo = new Nodo(raiz.info3);
            nuevo.n0 = new Nodo(raiz.info1,raiz.info2);
            nuevo.n1 = new Nodo(raiz.info4,cliente);
        }
        return nuevo;
    }
    
    public Nodo ordenar(Cliente cliente, Nodo raiz){
        if(raiz.info1.getDpi()>cliente.getDpi()){
            raiz.info4 = raiz.info3;
            raiz.info3 = raiz.info2;
            raiz.info2 = raiz.info1;
            raiz.info1 = cliente;
        }else if(raiz.info2.getDpi()>cliente.getDpi()){
            raiz.info4 = raiz.info3;
            raiz.info3 = raiz.info2;
            raiz.info2 = cliente;
        }else if(raiz.info3.getDpi()>cliente.getDpi()){
            raiz.info4 = raiz.info3;
            raiz.info3 = cliente;
        }else{
            raiz.info4 = cliente;
        }
        return raiz;
    }
    
    public boolean estoyLleno(Nodo raiz){
        if(raiz.info1 != null && raiz.info2 != null && raiz.info3 != null && raiz.info4 != null){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean tengoHijos(Nodo raiz){    
        if(raiz.n0 == null && raiz.n1 == null && raiz.n2 == null && raiz.n3 == null && raiz.n4 == null){
            return false;
        }else{
            return true;
        }
    }
}
