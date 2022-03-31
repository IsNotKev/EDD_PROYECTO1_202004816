/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Imagen;
import edd.proyectof2.EDDProyectoF2;
import edd.proyectof2.Usuario;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class AVL {
    public Nodo raiz;
    
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        EDDProyectoF2.cont = 0;
    }
    
    public void mostrarImgs(Usuario us){
        mostrarImgs(us,raiz);
    }
    
    public void mostrarImgs(Usuario us,Nodo raiz){
        
        int no ;
        
        if(raiz == null){
            return;
        }else if(raiz.izquierda == null && raiz.derecha == null){
            no = ((Imagen)raiz.valor).getId();
            us.jComboBox2.addItem(""+no);
        }else{
            no = ((Imagen)raiz.valor).getId();
            us.jComboBox2.addItem(""+no);   
            mostrarImgs(us,raiz.izquierda);
            mostrarImgs(us,raiz.derecha);
        }
        
    }
    
    public ABB buscar(int n){
        Nodo aux = raiz;
        
        while(aux != null){
            if(((Imagen)aux.valor).getId() == n){
                return((Imagen)aux.valor).getCapas();
            }else if(((Imagen)aux.valor).getId() > n){
                aux = aux.izquierda;
            }else{
                aux = aux.derecha;
            }
        }
        return null;
    }
    
    public Imagen obtenerMayor(Nodo raiz){
        if(raiz.derecha == null){
            Imagen valor = (Imagen)raiz.valor;
            return valor;
        }else{
            return obtenerMayor(raiz.derecha);
        }
    }
    
    public Nodo eliminar(int n, Nodo raiz){
        if(((Imagen)raiz.valor).getId() == n){
            if(raiz.izquierda == null && raiz.derecha == null){
                raiz = null;
            }else if(raiz.izquierda != null){
                Imagen x = obtenerMayor(raiz.izquierda);
                raiz.valor = x;
                raiz.izquierda = eliminar(x.getId(),raiz.izquierda);
            }else{
                raiz = raiz.derecha;
            }
        }else{
            if(((Imagen)raiz.valor).getId()<n){
                raiz.derecha = eliminar(n,raiz.derecha);
            }else{
                raiz.izquierda = eliminar(n,raiz.izquierda);
            }
        }
        return raiz;
    }
    
    public Lista top5(){
        return top5_recursivo(raiz);
    }
    
    public Lista top5_recursivo(Nodo raiz){
        Lista res = new Lista();
        
        Imagen aux;
        
        if(raiz==null){
            
        }else if(raiz.izquierda==null && raiz.derecha==null){
            aux = new Imagen(((Imagen)raiz.valor).getId(),((Imagen)raiz.valor).getCapas().contarCapas());
            res.agregarOrdenado(aux);
        }else{
            aux = new Imagen(((Imagen)raiz.valor).getId(),((Imagen)raiz.valor).getCapas().contarCapas());
            res.agregarOrdenado(aux);
            res.agregarLista(top5_recursivo(raiz.izquierda));
            res.agregarLista(top5_recursivo(raiz.derecha));
        }
        
        return res;
    }
    
    public int contarImagenes(){
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
