/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Cliente;
import edd.proyectof2.EDDProyectoF2;
import java.io.*;

/**
 *
 * @author kevin
 */
public class ArbolB {
    
    Nodo raiz;
    
    public class Nodo{
        Cliente info1,info2,info3,info4,info5;
        Nodo n0,n1,n2,n3,n4,n5;
        boolean esHoja;
        
        public Nodo(){
            info1=null;
            info2=info3=info4=info5=null;
            n0=null;
            n1=null;
            n2=null;
            n3=null;
            n4=null;
            this.esHoja = true;
        }
    }
    
    public ArbolB(){
        raiz = new Nodo();
    }
    
    public void insertar(Cliente key){
        Nodo r = raiz;
        nonFullInsert(raiz,key);
        
        if(estoyLleno(raiz)){
            Nodo s = new Nodo();
            s.esHoja = false;
            raiz = s;
            raiz.n0 = r;
            split(raiz,0,r);
        }

    }
    
    public void nonFullInsert(Nodo x, Cliente key){
        if(x.esHoja){
            if(x.info1 == null || x.info1.getDpi()>key.getDpi() ){
                x.info5 = x.info4;
                x.info4 = x.info3;
                x.info3 = x.info2;
                x.info2 = x.info1;
                x.info1 = key;
            }else if(x.info2 == null || x.info2.getDpi()>key.getDpi()){
                x.info5 = x.info4;
                x.info4 = x.info3;
                x.info3 = x.info2;
                x.info2 = key;
            }else if(x.info3 == null || x.info3.getDpi()>key.getDpi()){
                x.info5 = x.info4;
                x.info4 = x.info3;
                x.info3 = key;
            }else if(x.info4 == null || x.info4.getDpi()>key.getDpi()){
                x.info5 = x.info4;
                x.info4 = key;
            }else{
                x.info5 = key;
            }
        }else{
            if(x.info1.getDpi()>key.getDpi()){
                nonFullInsert(x.n0,key);
                if(estoyLleno(x.n0)){
                    split(x,0,x.n0);
                }                  
            }else if(x.info2 == null || x.info2.getDpi()>key.getDpi()){
                nonFullInsert(x.n1,key);
                if(estoyLleno(x.n1)){
                    split(x,1,x.n1);
                }               
            }else if(x.info3 == null ||x.info3.getDpi()>key.getDpi()){
                nonFullInsert(x.n2,key);
                if(estoyLleno(x.n2)){
                    split(x,2,x.n2);
                } 

            }else if(x.info4 == null ||x.info4.getDpi()>key.getDpi()){
                nonFullInsert(x.n3,key);
                if(estoyLleno(x.n3)){
                    split(x,3,x.n3);
                }
            }else{
                nonFullInsert(x.n4,key);
                if(estoyLleno(x.n4)){
                    split(x,4,x.n4);
                }              
            }
        }
    }
    
    public void split(Nodo x, int i, Nodo y){
        Nodo z = new Nodo();
        z.esHoja = y.esHoja;
        
        z.info1 = y.info4;
        z.info2 = y.info5;
        
        if(!y.esHoja){
            z.n0 = y.n3;
            z.n1 = y.n4;
            z.n2 = y.n5;
        }

        if(i==0){
            x.n5 = x.n4;
            x.n4 = x.n3;
            x.n3 = x.n2;
            x.n2 = x.n1;
            x.n1 = z;

        }else if(i==1){
            x.n5 = x.n4;
            x.n4 = x.n3;
            x.n3 = x.n2;
            x.n2 = z;
        }else if(i==2){
            x.n5 = x.n4;
            x.n4 = x.n3;
            x.n3 = z;
        }else if(i==3){
            x.n5 = x.n4;
            x.n4 = z;
        }else if(i==4){
            x.n5 = z;
        }
        
        if(i==0){
            x.info5 = x.info4;
            x.info4 = x.info3;
            x.info3 = x.info2;
            x.info2 = x.info1;
            x.info1 = y.info3;
        }else if(i==1){
            x.info5 = x.info4;
            x.info4 = x.info3;
            x.info3 = x.info2;
            x.info2 = y.info3;
        }else if(i==2){
            x.info5 = x.info4;
            x.info4 = x.info3;
            x.info3 = y.info3;
        }else if(i==3){
            x.info5 = x.info4;
            x.info4 = y.info3;
        }else if(i==4){
            x.info5 = y.info3;
        }
        
        y.info3 = y.info4 = y.info5 = null;
        y.n3 = y.n4 = y.n5 = null;
    }
    
    public boolean estoyLleno(Nodo raiz){
        if(raiz.info5 != null){
            return true;
        }else{
            return false;
        }
    }
    
    public String graficar_recursivo(Nodo raiz, String padre){
        String nodos = "";
        String conexiones = "";
        
        int c = EDDProyectoF2.cont;
        EDDProyectoF2.cont += 1;
        
        if(!padre.equals("")){
            conexiones += padre + "->N"+ c+";\n";
        }
        
        String r2="x",r3="x",r4="x";
        
        if(raiz.info2 != null){
            r2 = "" + raiz.info2.getDpi();
        }
        if(raiz.info3 != null){
            r3 = "" + raiz.info3.getDpi();
        }
        if(raiz.info4 != null){
            r4 = "" + raiz.info4.getDpi();
        }
        
        nodos += "N"+ c+"[shape=record,label=\"<N"+c+"_0>|{"+raiz.info1.getDpi()+"}|<N"+c+"_1>|{"+r2+"}|<N"+c+"_2>|{"+r3+"}|<N"+c+"_3>|{"+r4+"}|<N"+c+"_4>\"];\n";
        
        if(raiz.n0!=null){
            conexiones += graficar_recursivo(raiz.n0,"N"+c+":N"+c+"_0");
        }
        if(raiz.n1!=null){
            conexiones += graficar_recursivo(raiz.n1,"N"+c+":N"+c+"_1");
        }
        if(raiz.n2!=null){
            conexiones += graficar_recursivo(raiz.n2,"N"+c+":N"+c+"_2");
        }
        if(raiz.n3!=null){
            conexiones += graficar_recursivo(raiz.n3,"N"+c+":N"+c+"_3");
        }
        if(raiz.n4!=null){
            conexiones += graficar_recursivo(raiz.n4,"N"+c+":N"+c+"_4");
        }           
        
        return nodos+conexiones;
    }
    
    public void graficar(String title){
    String resultado="digraph G{\nlabel=\""+title+"\";\n";        
       
        resultado += graficar_recursivo(raiz,"");
        
        resultado+="\n}";
        
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
