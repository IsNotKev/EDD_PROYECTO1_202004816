/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Album;
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

        public Nodo(Object info) {
            this.info = info;
            this.next = null;
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
            EDDProyectoF2.graficarDot(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
