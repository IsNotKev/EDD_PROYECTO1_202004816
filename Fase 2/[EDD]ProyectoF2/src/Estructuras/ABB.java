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
            return;
        }else if(raiz.izquierda == null && raiz.derecha == null){
            no = ((Capa)raiz.valor).getId();
            us.jComboBox1.addItem(""+no);
        }else{
            no = ((Capa)raiz.valor).getId();
            us.jComboBox1.addItem(""+no);   
            mostrarCapas(us,raiz.izquierda);
            mostrarCapas(us,raiz.derecha);
        }
        
    }
    
    public Matriz buscar(int n){
        Nodo aux = raiz;
        
        while(aux != null){
            if(((Capa)aux.valor).getId() == n){
                return((Capa)aux.valor).getPixeles();
            }else if(n<((Capa)aux.valor).getId()){
                aux = aux.izquierda;
            }else{
                aux = aux.derecha;
            }
        }
        return null;
    }
    
    public Matriz crearImagen(String t){
        Matriz img = null;
        switch(t){
            case "In":
                img = imagenIn(raiz);
                break;
            case "Post":
                img = imagenPost(raiz);
                break;
            case "Pre":
                img = imagenPre(raiz);
                break;
            default:
                System.out.println("ERROR");
                break;
        }
        return img;
    }
    
    public Matriz imagenPre(Nodo raiz){
        Matriz img = new Matriz();
        int no;
        if(raiz == null){
            img = null;
        }else if(raiz.izquierda == null && raiz.derecha == null){
            no = ((Capa)raiz.valor).getId();
            img.agregarCapa(EDDProyectoF2.clienteActual.getCapas().buscar(no));
        }else{
            no = ((Capa)raiz.valor).getId();
            img.agregarCapa(EDDProyectoF2.clienteActual.getCapas().buscar(no));   
            img.agregarCapa(imagenPre(raiz.izquierda));
            img.agregarCapa(imagenPre(raiz.derecha));
        }
        
        return img;
    }
    public Matriz imagenIn(Nodo raiz){
        Matriz img = new Matriz();
        
        int no;
        if(raiz == null){
            img = null;
        }else if(raiz.izquierda == null && raiz.derecha == null){
            no = ((Capa)raiz.valor).getId();
            img.agregarCapa(EDDProyectoF2.clienteActual.getCapas().buscar(no));
        }else{ 
            
            no = ((Capa)raiz.valor).getId();
            
            img.agregarCapa(imagenIn(raiz.izquierda));
            img.agregarCapa(EDDProyectoF2.clienteActual.getCapas().buscar(no));
            img.agregarCapa(imagenIn(raiz.derecha));
        }
        
        return img;
    }
    public Matriz imagenPost(Nodo raiz){
        Matriz img = new Matriz();
        
        int no;
        if(raiz == null){
            img = null;
        }else if(raiz.izquierda == null && raiz.derecha == null){
            no = ((Capa)raiz.valor).getId();
            img = EDDProyectoF2.clienteActual.getCapas().buscar(no);
        }else{             
            
            no = ((Capa)raiz.valor).getId();
            
            img.agregarCapa(imagenIn(raiz.izquierda));
            img.agregarCapa(imagenIn(raiz.derecha));
            img.agregarCapa(EDDProyectoF2.clienteActual.getCapas().buscar(no));                     
        }
        
        return img;
    }
    
    public void generarTablaCapasHojas(String title){
        String resultado="digraph G{\nN0[shape=record, label=\"{CAPAS HOJAS"+hojas(raiz)+"}\"];\n}";              
        
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
    
    public String hojas(Nodo raiz){
        String res = "";
        
        if(raiz == null){
            res = "";
        }else if(raiz.derecha == null &&  raiz.izquierda == null){
            res = "|Capa "+((Capa)raiz.valor).getId();
        }else{
            res = hojas(raiz.derecha) + hojas(raiz.izquierda);
        }
        
        return res;
    }
    
    public void generarProfundidad(String title){
        String resultado="digraph G{\nN0[shape=record, label=\"{PROFUNDIDAD|"+profundidad(raiz)+" Niveles}\"];\n}";              
        
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
    
    public int profundidad(Nodo raiz){
        int res = 0;
        if(raiz == null){
            res = 0;
        }else if(raiz.derecha == null &&  raiz.izquierda == null){
            res = 1;
        }else{
            int d = profundidad(raiz.derecha);
            int i = profundidad(raiz.izquierda);
            
            if(d>i){
                res = d +1;
            }else{
                res = i +1;
            }
        }
        return res;
    }
    
    public void graficarRecorridos(String title){
        String resultado="digraph G{\nN0[shape=record, label=\"{ | PreOrden | InOrden | PostOrden }|{RECORRIDO|"+preorden(raiz, true)+"|"+inorden(raiz, true)+"|"+postorden(raiz, true)+"}\"];\n}";              
        
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
    
    public String preorden(Nodo raiz, boolean primero){
        String res = "";
        
        if(raiz == null){
            res = "";
        }else if(raiz.izquierda==null && raiz.derecha == null){
            if(!primero){
                res += ",";
            }
            res += +((Capa)raiz.valor).getId();
        }else{
            if(!primero){
                res += ",";
            }
            res += ((Capa)raiz.valor).getId();
            res += preorden(raiz.izquierda,false);
            res += preorden(raiz.derecha,false);
        }
        
        return res;
    }
    
    public String inorden(Nodo raiz, boolean primero){
        String res = "";
        
        if(raiz == null){
            res = "";
        }else if(raiz.izquierda==null && raiz.derecha == null){
            if(!primero){
                res += ",";
            }
            res += +((Capa)raiz.valor).getId();
        }else{
            res += inorden(raiz.izquierda,primero);
            res += ","+((Capa)raiz.valor).getId();           
            res += inorden(raiz.derecha,false);
        }
        
        return res;
    }
    
    public String postorden(Nodo raiz, boolean primero){
        String res = "";
        
        if(raiz == null){
            res = "";
        }else if(raiz.izquierda==null && raiz.derecha == null){
            if(!primero){
                res += ",";
            }
            res += +((Capa)raiz.valor).getId();
        }else{
            res += postorden(raiz.izquierda,primero);                     
            res += postorden(raiz.derecha,false);
            res += ","+((Capa)raiz.valor).getId(); 
        }
        
        return res;
    }
    
    public int contarCapas(){
        return contar(raiz);
    }
    
    public int contar(Nodo raiz){
        int n = 0;
        
        if(raiz == null){
            n= 0;
        }else if(raiz.izquierda == null && raiz.derecha == null){
            n = 1;
        }else{
            n += contar(raiz.derecha) + contar(raiz.izquierda) + 1;
        }       
        return n;
    }
}
