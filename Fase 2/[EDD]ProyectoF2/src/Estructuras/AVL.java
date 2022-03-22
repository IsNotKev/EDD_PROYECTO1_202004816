/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Imagen;
import edd.proyectof2.EDDProyectoF2;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class AVL {
    Nodo raiz;
    
    public static class Nodo{
        
        Object valor;
        int equilibrio;
        Nodo izquierda;
        Nodo derecha;
        
        public Nodo(Object valor){
            this.valor = valor;
            this.izquierda = this.derecha = null;
            this.equilibrio = 0;
        }
    }

    public AVL() {
        this.raiz = null;
    }
    
    /*public Nodo buscar(int d, Nodo r){
    if(raiz==null){
        return null;
    }else if(r.dato==d){
        return r;
    }else if(r.dato<d){
        return buscar(d, r.hijoDerecho);
    }else{
        return buscar(d, r.hijoIzquierdo);
    }
    }*/

   //obtener factor de equilibrio
    public int obtenerFE(Nodo x){
        if(x==null){
            return -1;
        }else{
            return x.equilibrio;
        }
    }

//rotacion simple a la izquierda
    public Nodo rotacionIzquierda(Nodo c){
        Nodo auxiliar=c.izquierda;
        c.izquierda=auxiliar.derecha;
        auxiliar.derecha=c;
        c.equilibrio=Math.max(obtenerFE(c.izquierda), obtenerFE(c.derecha))+1;  //obtiene el maximo
        auxiliar.equilibrio=Math.max(obtenerFE(auxiliar.izquierda), obtenerFE(auxiliar.derecha))+1;
        return auxiliar;
    }

//rotacion simple derecha
    public Nodo rotacionDerecha(Nodo c){
        Nodo auxiliar=c.derecha;
        c.derecha=auxiliar.izquierda;
        auxiliar.izquierda=c;
        c.equilibrio=Math.max(obtenerFE(c.izquierda), obtenerFE(c.derecha))+1;  //obtiene el maximo
        auxiliar.equilibrio=Math.max(obtenerFE(auxiliar.izquierda), obtenerFE(auxiliar.derecha))+1;
        return auxiliar;
    }


//rotacion doble a la der
    public Nodo rotacionDobleIzquierda(Nodo c){
        Nodo temporal;
        c.izquierda= rotacionDerecha(c.izquierda);
        temporal=rotacionIzquierda(c);
        return temporal;

    }
//rotacion doble a la izq
    public Nodo rotacionDobleDerecha(Nodo c){
        Nodo temporal;
        c.derecha= rotacionIzquierda(c.derecha);
        temporal=rotacionDerecha(c);
        return temporal;
    }

    //insertar avl
    public Nodo insertarAVL(Nodo nuevo, Nodo subAr){
        Nodo nuevoPadre=subAr;
        int dato = ((Imagen)nuevo.valor).getId();
        int act = ((Imagen)subAr.valor).getId();
        if(dato<act){
            if(subAr.izquierda==null){
                subAr.izquierda=nuevo;
            }else{
                subAr.izquierda=insertarAVL(nuevo, subAr.izquierda);
                if((obtenerFE(subAr.izquierda)-obtenerFE(subAr.derecha)==2)){
                    if(dato<(((Imagen)subAr.izquierda.valor).getId())){
                        nuevoPadre=rotacionIzquierda(subAr);
                    }else{
                        nuevoPadre=rotacionDobleIzquierda(subAr);
                    }
                }
            }
        }else if(dato>act){
            if(subAr.derecha==null){
                subAr.derecha=nuevo;
            }else{
                subAr.derecha=insertarAVL(nuevo, subAr.derecha);
                if((obtenerFE(subAr.derecha)-obtenerFE(subAr.izquierda)==2)){
                    if(dato>(((Imagen)subAr.derecha.valor).getId())){
                        nuevoPadre=rotacionDerecha(subAr);
                    }else{
                        nuevoPadre=rotacionDobleDerecha(subAr);
                    }
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Nodo duplicado");
        }

      //actualizar altura
        if((subAr.izquierda==null)&&(subAr.derecha!=null)){
            subAr.equilibrio=subAr.derecha.equilibrio+1;
        }else if((subAr.derecha==null)&&(subAr.izquierda!=null)){
            subAr.equilibrio=subAr.izquierda.equilibrio+1;
        }else{
            subAr.equilibrio=Math.max(obtenerFE(subAr.izquierda), obtenerFE(subAr.derecha))+1;
        }
        return nuevoPadre;
    }


    //insertar normal
    public void insertar(Object d){
       Nodo nuevo= new Nodo(d);
       if(raiz==null){
           raiz=nuevo;
       }else{
           raiz=insertarAVL(nuevo, raiz);
       }
    }
    
    public static ArrayList<String> graficar_recursivo(Nodo raiz){
        ArrayList<String> respuesta = new ArrayList<String>();
        
        int numero;
        
        if(raiz == null){
            respuesta.add("");
            respuesta.add("");
        }else if(raiz.izquierda == null && raiz.derecha == null){
            numero = ((Imagen)raiz.valor).getId();           
            respuesta.add("N"+EDDProyectoF2.cont);
            respuesta.add("N"+EDDProyectoF2.cont+"[label=\""+numero+","+raiz.equilibrio+"\"]\n");
            EDDProyectoF2.cont += 1;
        }else{
            numero = ((Imagen)raiz.valor).getId();
            String conj = "";
            ArrayList<String> izquierda = graficar_recursivo(raiz.izquierda);
            ArrayList<String> derecha = graficar_recursivo(raiz.derecha);
            
            
            
            conj += "N"+EDDProyectoF2.cont+"[label=\""+numero+","+raiz.equilibrio+"\"]\n";
            
            
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
}
