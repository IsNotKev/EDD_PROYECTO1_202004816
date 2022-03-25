/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Capa;
import edd.proyectof2.EDDProyectoF2;
import edd.proyectof2.Usuario;
import java.io.*;
import java.util.*;

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
    
    public static ArrayList<String> graficar_recursivo(Nodo raiz){
        ArrayList<String> respuesta = new ArrayList<String>();
        
        int numero;
        
        if(raiz == null){
            respuesta.add("");
            respuesta.add("");
        }else if(raiz.izquierda == null && raiz.derecha == null){
            numero = ((Capa)raiz.valor).getId();           
            respuesta.add("N"+EDDProyectoF2.cont);
            respuesta.add("N"+EDDProyectoF2.cont+"[label=\""+numero+"\"]\n");
            EDDProyectoF2.cont += 1;
        }else{
            numero = ((Capa)raiz.valor).getId();
            String conj = "";
            ArrayList<String> izquierda = graficar_recursivo(raiz.izquierda);
            ArrayList<String> derecha = graficar_recursivo(raiz.derecha);
            
            
            
            conj += "N"+EDDProyectoF2.cont+"[label=\""+numero+"\"]\n";
            
            
            if(!izquierda.get(0).equals("")){
                conj += "N"+EDDProyectoF2.cont + "->" + izquierda.get(0) + ";\n";
            }
            
            if(!derecha.get(0).equals("")){
                conj += "N"+EDDProyectoF2.cont + "->" + derecha.get(0) + ";\n";
            }
            
            respuesta.add("N"+EDDProyectoF2.cont);
            respuesta.add(conj + izquierda.get(1) + derecha.get(1));
            EDDProyectoF2.cont += 1;
           
        }
        
        return respuesta;
    }
    
    public void graficar(String title){
        String resultado="digraph G{\nlabel=\""+title+"\";\nnode [shape=circle];\n";        
       
        resultado += graficar_recursivo(raiz).get(1);
        
        resultado+="}\n}";
        
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
        
        EDDProyectoF2.cont = 0;
    }
    
    public void mostrarCapas(Usuario us){
        mostrarCapas(us,raiz);
    }
    
    public void mostrarCapas(Usuario us,Nodo raiz){
        
        int no ;
        
        if(raiz == null){
            
        }else if(raiz.izquierda == null && raiz.derecha == null){
            no = ((Capa)raiz.valor).getId();
            us.jComboBox1.addItem("Capa" + no);
        }else{
            no = ((Capa)raiz.valor).getId();
            us.jComboBox1.addItem("Capa" + no);   
            mostrarCapas(us,raiz.izquierda);
            mostrarCapas(us,raiz.derecha);
        }
        
    }
}
