/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Album;
import Objetos.Imagen;
import edd.proyectof2.EDDProyectoF2;
import java.io.*;

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
    
    public void graficar(String title){
        String resultado="digraph G{\nlabel=\""+title+"\";\nnode [shape=square];\n";        
        String nodos = "";
        String conexiones = "";
        
        
        String rank = "rank=same{";
        int c = 0;
        Nodo aux = raiz;
        String anterior = "";
        while(aux!=null){
            
            nodos += "N"+aux.hashCode()+"[label=\""+((Album)aux.info).getNombre()+"\"];\n";
            if(c==0){
                rank += "N"+aux.hashCode();
            }else{
                rank += ",N"+aux.hashCode();
            }
            
            if(!anterior.equals("")){
                conexiones += anterior+"->N"+aux.hashCode()+";\n";
                conexiones += "N"+aux.hashCode()+"->"+anterior+";\n";
            }
            
            Nodo auxiliar = ((Album)aux.info).getImgs().raiz;
            String anterior2 = "";
            while(auxiliar != null){
                
                nodos += "N"+auxiliar.hashCode()+"[label=\""+((int)auxiliar.info)+"\"];\n";
                
                if(!anterior2.equals("")){
                    conexiones += anterior2 + "->N"+auxiliar.hashCode()+";\n";
                }else{
                    conexiones += "N"+aux.hashCode() + "->N"+auxiliar.hashCode()+";\n";
                }
                anterior2 = "N"+auxiliar.hashCode();
                auxiliar = auxiliar.next;
            }
            
            anterior = "N"+aux.hashCode();
            aux = aux.next;
            c++;
        }
       
        resultado+=nodos + rank + "};\n" + conexiones+"\n}";
        
        try {
            String ruta = System.getProperty("user.dir") + "\\"+title+".txt";
            File file = new File(ruta);
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(resultado);
            bw.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void eliminar(int i){
        
        raiz = eliminar_recursivo( i, raiz);
    
    }
    
    public Nodo eliminar_recursivo(int n, Nodo raiz){
        Nodo auxLista = raiz;
        while(auxLista != null){
            Album miLista = (Album)auxLista.info;
            
            Lista imgs = miLista.getImgs();
            
            auxLista = auxLista.next;
        }
        return raiz;
    }
    
    
    public void agregarOrdenado(Imagen info){
        Nodo nuevo = new Nodo(info);
            //System.out.println(no);
            if(raiz == null){
                raiz =  nuevo;
            }else{
                ordenar(nuevo);
            }
    }
    
    public void ordenar(Nodo nodo){
        Nodo aux = raiz;
        Nodo temp = null;
        while(aux != null){
            temp = aux;
            if(((Imagen)aux.info).getCant() > ((Imagen)nodo.info).getCant()){
                aux = aux.next;
            }else{
                if(aux == raiz){
                    nodo.next =aux;
                    aux.anterior = nodo;                       
                    raiz = nodo;
                    return;
                }else{
                    nodo.anterior = aux.anterior;
                    aux.anterior.next = nodo;
                    nodo.next = aux;
                    aux.anterior = nodo;
                    return;
                }
            }       
        }
        
        if(temp == raiz){
            nodo.anterior = raiz;
            raiz.next = nodo;              
        }else{
            temp.next = nodo;
            nodo.anterior = temp;
        }  
    }
    
    public void agregarLista(Lista l){
        if(l!=null){
            Nodo aux = l.raiz;

            while(aux!=null){
                this.agregarOrdenado((Imagen)aux.info);
                aux = aux.next;
            }
        }       
    }
    
    public void graficarTop(String title){
        String resultado="digraph G{\nN0[shape=record,label=\"{Top|1|2|3|4|5}";              
        String names = "{# Img";
        String cants = "{Cantidad";
        
        Nodo aux = raiz;
        for(int i = 0; i<5; i++){
            if(aux!=null){
                names += "|Imagen "+ ((Imagen)aux.info).getId();
                cants += "|"+((Imagen)aux.info).getCant() + " capas";
                aux = aux.next;
            }else{
                names += "|";
                cants += "|";
            }
        }
        
        names += "}";
        cants += "}";
        
        resultado += "|" + names + "|" + cants + "\"];";
        
        resultado += "\n}";
        try {
            String ruta = System.getProperty("user.dir") + "\\"+title+".txt";
            File file = new File(ruta);
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(resultado);
            bw.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        EDDProyectoF2.cont = 0;
    }
    
    public int tamano(){
        Nodo aux = raiz;
        int cont = 0;
        while(aux!=null){
            cont += 1;
            aux = aux.next;
        }
        
        return cont;
    }
}
