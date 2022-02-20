/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyectof1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author kevin
 */
public class Cola {
    private Nodo cabecera;
    
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
            if(cabecera == null){
                cabecera = nodonuevo;
            }
            else{
                Nodo aux = cabecera;
                while(aux.next != null){
                    aux=aux.next;
                }
                aux.next = nodonuevo;
            }
    }
    
    public Cliente deletC(){
        Cliente aux = null;
        if(cabecera != null){
            aux = (Cliente)cabecera.info;      
            cabecera = cabecera.next;
        }      
        return aux;
    }
    
    /*public Imagen deleteImg(){
        Imagen aux = null;
        if(cabecera != null){
            aux = (Imagen)cabecera.info;      
            cabecera = cabecera.next;
        }      
        return aux;
    }*/
    
    public void imprimirC(){
     
        if(cabecera != null){
            if(EDDProyectoF1.imprimir){
                
                EDDProyectoF1.espera.entregar((Imagen)cabecera.info);               
                EDDProyectoF1.imprimir = false;
                
                cabecera = cabecera.next;
                
            }else{
                EDDProyectoF1.imprimir = true;
            }
        }
        
    }
    
    public void imprimirBW(){        
        if(cabecera != null){
            EDDProyectoF1.espera.entregar((Imagen)cabecera.info);
            cabecera = cabecera.next;
        }
    }
    
    public void imprimir(){
        String n = "";
        Nodo aux = cabecera;
        while(aux != null){
            n += aux.info;
            n += "-";
            aux=aux.next;
        }
        System.out.println(n);
    }
    
    public void imprimirImpresoras(){
        Nodo aux = cabecera;
        
        while(aux != null){
            Imagen n = (Imagen)aux.info;
            System.out.println("Imagen Con ID " + n.getId());
            aux = aux.next;
        }
    }
    
    public void generarDot(String title){
        String resultado="digraph G{\nlabel=\""+title+"\";\nnode [shape=box];\n";        
        String conexiones="";
        String nodos="";
        
        Nodo aux = cabecera;
        
        String auxiliar;
        
        if(title.equals("Impresora BW")){
            nodos += "N1[label=\"IMPRESORA \nB Y N\"];\n";
            if(aux != null){
                conexiones+="N1 -> "+"N"+aux.hashCode()+"[arrowhead=none];\n";
            }
            while(aux != null){
                nodos += "N"+aux.hashCode()+"[label=\"IMG BN\"];\n";
                if(aux.next != null){
                    conexiones+="N"+aux.hashCode()+ " -> "+"N"+aux.next.hashCode()+"[arrowhead=none];\n";
                }
                aux = aux.next;
            }
        }else if(title.equals("Impresora C")){
            nodos += "N1[label=\"IMPRESORA \nCOLOR\"];\n";
            if(aux != null){
                conexiones+="N1 -> "+"N"+aux.hashCode()+"[arrowhead=none];\n";
            }
            while(aux != null){
                nodos += "N"+aux.hashCode()+"[label=\"IMG C\"];\n";
                if(aux.next != null){
                    conexiones+="N"+aux.hashCode()+ " -> "+"N"+aux.next.hashCode()+"[arrowhead=none];\n";
                }
                aux = aux.next;
            }
        }else{
            while(aux != null){
                Cliente n = (Cliente)aux.info;
                auxiliar = nodos;
                nodos = "N"+aux.hashCode()+"[label=\"Cliente "+n.getId()+" \nIMG C: "+n.getC()+" \nIMG BN: "+n.getBw()+"\"];\n";
                nodos+=auxiliar;
                if(aux.next != null){
                    conexiones+="N"+aux.next.hashCode()+ " -> "+"N"+aux.hashCode()+"[arrowhead=none];\n";
                }
                aux = aux.next;
            }
        }   
        resultado+= "//Agregando nodods\n";
        resultado+=nodos+"\n";
        resultado+= "//Agregando conexiones\n";
        resultado+="{rank= same;\n"+conexiones+"\n";
        
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
        
    }
    
    public void graficar(Cola n, String title){
        n.generarDot(title);
        try {
           
           String dotPath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
           String fileInputPath = System.getProperty("user.dir") + "\\"+title+".txt";
           String fileOutputPath = System.getProperty("user.dir") + "\\"+title+".jpg";;

           String tParam = "-Tjpg";
           String tOParam = "-o";
           
           String[] cmd = new String[5];
           cmd[0] = dotPath;
           cmd[1] = tParam;
           cmd[2] = fileInputPath;
           cmd[3] = tOParam;
           cmd[4] = fileOutputPath;

           Runtime rt = Runtime.getRuntime();

           rt.exec( cmd );
           
           //System.out.println("Graficado");
           
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
    
    public void sumarPaso(){
        Nodo aux = cabecera;
        
        while(aux != null){
            
            Cliente n = (Cliente)aux.info;
            
            n.pasos += 1;
            
            aux = aux.next;
        }
    }
}
