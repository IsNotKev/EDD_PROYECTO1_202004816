/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Capa;

/**
 *
 * @author kevin
 */
public class ABB {
    Nodo raiz;
    
    public static class Nodo{
        
        Object valor;
        Nodo izquierda;
        Nodo derecha;
        
        public Nodo(Object valor){
            this.valor = valor;
            this.izquierda = this.derecha = null;
        }
    }

    public ABB() {
        this.raiz = null;
    }
    
    public void agregar(Object valor){
        raiz = agregar_recursivo(valor,raiz);
    }
    
    public static Nodo agregar_recursivo(Object valor, Nodo raiz){
        
        if(raiz == null){
            return new Nodo(valor);
        }else{
            int id = ((Capa)valor).getId();
            int act = ((Capa)raiz.valor).getId();
            if(id < act){
                raiz.izquierda = agregar_recursivo(valor,raiz.izquierda);
            }else if(act < id){
                raiz.derecha = agregar_recursivo(valor,raiz.derecha);
            }/*else{
                System.out.println("ya existe");
            }*/
            return raiz;
        }       
    }
}
