/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Estructuras.ListaAdyacencia.Vertice;
import Objetos.Ruta;
import edd.proyectof3.EDDProyectoF3;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author kevin
 */
public class Lista {
    Nodo raiz;
    
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
        Nodo nodonuevo = new Nodo(info);
        if(raiz == null){
            raiz = nodonuevo;
        }
        else{
            Nodo aux = raiz;
            while(aux.next != null){
                aux=aux.next;
            }
            aux.next = nodonuevo;
        }
    }
    
    public int tamano(){
        int c = 0;
        Nodo aux = raiz;
        while(aux.next != null){
            c += 1;
            aux=aux.next;
        }
        return c;
    }
    
    public boolean existeVertice(int i){
        Nodo aux = raiz;
        while(aux!= null){
            Vertice n = (Vertice)aux.info;
            if(n.vert == i){
                return true;
            }
            aux=aux.next;
        }
        return false;
    }
    
    public void graficarGrafo(){
        String resultado="digraph G{\nlabel=\"Grafo de Rutas\";\n";        

        Nodo aux = raiz;    
        while(aux!=null){
            Ruta rout = (Ruta)aux.info;
            resultado += "N"+rout.getD1()+"->N"+rout.getD2()+"[label=\""+rout.getPeso()+"\",dir=none];\n";                 
            aux = aux.next;
        }
        
        
        resultado+="\n}";
        
        try {
            String ruta = System.getProperty("user.dir") + "\\grafo.txt";
            File file = new File(ruta);
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(resultado);
            bw.close(); 
            EDDProyectoF3.graficarDot("grafo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
