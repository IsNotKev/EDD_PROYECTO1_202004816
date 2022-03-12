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
public class ListaCircular {
    private Nodo inicio;
    
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
        Nodo nuevo = new Nodo(info);
        
        if(inicio == null){
            inicio = nuevo;
            inicio.next = inicio;
            inicio.anterior = inicio;
        }else{
            Nodo aux = inicio;
            while(aux.next != inicio){
                aux = aux.next;
            }
            
            nuevo.anterior = aux;
            nuevo.next = inicio;
            aux.next = nuevo;
            inicio.anterior = nuevo;
            
        }
    }
    
    public void delete(int id){
        Nodo aux = inicio;
        
        if(aux!=null){
            if(aux.next == inicio){
                Cliente n = (Cliente)aux.info;            
                if(n.getId() == id){
                    inicio = null;
                }
            }else{
                Cliente n = (Cliente)aux.info;            
                if(n.getId() == id){
                    Nodo aux2 = aux.next;
                    Nodo aux3 = aux.anterior;
                    
                    inicio = aux2;
                    aux2.anterior = aux3;
                    aux3.next = inicio;
                    
                }else{
                    aux = aux.next;
                    while(aux != inicio){
                        Cliente n1 = (Cliente)aux.info;
                        if(n1.getId() == id){
                           Nodo aux2 = aux.next;
                           Nodo aux3 = aux.anterior;
                           
                           aux3.next = aux2;
                           aux2.anterior = aux3;
                           break;
                        }                        
                        aux = aux.next;
                    }
                }
            }                        
        }
    }
    
    public void entregar(Imagen img){
        Nodo aux = inicio;
        
        if(aux.next == inicio){
            Cliente cliente = (Cliente)aux.info;
            if(cliente.getId() == img.getId()){
                cliente.getImgs().add(img);
                
                if(img.isTipo()){
                    System.out.println("Imagen A Color Entregada a Cliente " + cliente.getId());
                }else{
                    System.out.println("Imagen Blanco Y Negro Entregada a Cliente " + cliente.getId());
                }
                               
            }
        }else{
            Cliente n1 = (Cliente)aux.info;
            if(n1.getId() == img.getId()){
                n1.getImgs().add(img);

                if(img.isTipo()){
                    System.out.println("Imagen A Color Entregada a Cliente " + n1.getId());
                }else{
                    System.out.println("Imagen Blanco Y Negro Entregada a Cliente " + n1.getId());
                }
            }
                     
            aux = aux.next;
            while(aux != inicio){
                Cliente cliente = (Cliente)aux.info;
                if(cliente.getId() == img.getId()){
                    cliente.getImgs().add(img);

                    if(img.isTipo()){
                        System.out.println("Imagen A Color Entregada a Cliente " + cliente.getId());
                    }else{
                        System.out.println("Imagen Blanco Y Negro Entregada a Cliente " + cliente.getId());
                    }
                }
                aux = aux.next;
            }
        }      
    }
    
    public void imprimir(){
        Nodo aux = inicio;
        
        if(aux != null){
            if(aux.next == inicio){
                Cliente n = (Cliente)aux.info;
                System.out.println("Cliente " + n.getId() + " Esperando");
            }else{
                Cliente n1 = (Cliente)aux.info;
                System.out.println("Cliente " + n1.getId() + " Esperando");
                aux = aux.next;
                while(aux != inicio){
                    Cliente n = (Cliente)aux.info;
                    System.out.println("Cliente " + n.getId() + " Esperando");
                    aux = aux.next;
                }
            }
        }
    }
    
    public void atendidos(){
        Nodo aux = inicio;
        
        if(aux != null){           
                Cliente n = (Cliente)aux.info;
                if(n.getCant() == n.getImgs().tamano()){
                    System.out.println("Cliente " + n.getId() + " Atendido Con " + n.pasos + " Pasos.");
                    EDDProyectoF1.atendidos.add(n); 
                }
                aux = aux.next;               
                while(aux != inicio){
                    Cliente n1 = (Cliente)aux.info;
                    if(n1.getCant() == n1.getImgs().tamano()){
                        EDDProyectoF1.atendidos.add(n1);   
                        System.out.println("Cliente " + n1.getId() + " Atendido Con " + n1.pasos + " Pasos.");
                    }
                    aux = aux.next;
                }            
        }       
    }
    
    public void generarDot(String title){
        String resultado="digraph G{\nlabel=\""+title+"\";\nnode [shape=box];\nrankdir=LR;\n";        
        String conexiones="";
        String nodos="";
        
        Nodo aux = inicio;
        String ini;
        
        
        
        if(aux != null){          
            Cliente n1 = (Cliente)aux.info;
            nodos += "N"+aux.hashCode()+"[label=\"Cliente "+n1.getId()+" \nIMG C: "+n1.getC()+" \nIMG BN: "+n1.getBw()+"\"];\n";
            ini = "N"+aux.hashCode();
            if(aux.next != null){
                conexiones+="N"+aux.hashCode()+ " -> "+"N"+aux.next.hashCode()+";\n";
                conexiones+="N"+aux.next.hashCode()+ " -> "+"N"+aux.hashCode()+";\n";
            }else{
                conexiones+="N"+aux.hashCode()+ " -> "+ini+";\n";
                conexiones+=ini+" -> "+"N"+aux.hashCode()+";\n";
            }
            
            Lista.Nodo aux2 = n1.getImgs().cabecera;
            if(aux2 != null){
                conexiones+="N"+aux.hashCode()+ " -> "+"N"+aux2.hashCode()+"[arrowhead=none];\n";
            }
            while(aux2!=null){
                Imagen g = (Imagen)aux2.info;
                        
                if(g.isTipo()){
                    nodos += "N"+aux2.hashCode()+"[label=\"IMAGEN C\"];\n";
                }else{
                    nodos += "N"+aux2.hashCode()+"[label=\"IMAGEN BN\"];\n";
                }
                if(aux2.next != null){
                    conexiones+="N"+aux2.hashCode()+ " -> "+"N"+aux2.next.hashCode()+";\n";
                }
                aux2 = aux2.next;
            }
            
            
            aux = aux.next;
            while(aux != inicio){
                Cliente n = (Cliente)aux.info;
                nodos += "N"+aux.hashCode()+"[label=\"Cliente "+n.getId()+" \nIMG C: "+n.getC()+" \nIMG BN: "+n.getBw()+"\"];\n";
                if(aux.next != null){
                    conexiones+="N"+aux.hashCode()+ " -> "+"N"+aux.next.hashCode()+";\n";
                    conexiones+="N"+aux.next.hashCode()+ " -> "+"N"+aux.hashCode()+";\n";
                }else{
                    conexiones+="N"+aux.hashCode()+ " -> "+ini+";\n";
                    conexiones+=ini+" -> "+"N"+aux.hashCode()+";\n";
                }
                
                Lista.Nodo aux3 = n.getImgs().cabecera;
                if(aux3 != null){
                    conexiones+="N"+aux.hashCode()+ " -> "+"N"+aux3.hashCode()+"[arrowhead=none];\n";
                }
                while(aux3!=null){
                    Imagen g = (Imagen)aux3.info;

                    if(g.isTipo()){
                        nodos += "N"+aux3.hashCode()+"[label=\"IMAGEN C\"];\n";
                    }else{
                        nodos += "N"+aux3.hashCode()+"[label=\"IMAGEN BN\"];\n";
                    }
                    if(aux3.next != null){
                        conexiones+="N"+aux3.hashCode()+ " -> "+"N"+aux3.next.hashCode()+";\n";
                    }
                    aux3 = aux3.next;
                }
                aux = aux.next;          
            }
        }
       
        resultado+= "//Agregando nodods\n";
        resultado+=nodos+"\n";
        resultado+= "//Agregando conexiones\n";
        resultado+="{\n"+conexiones+"\n";
        
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
    
    public void graficar(ListaCircular n, String title){
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
        Nodo aux = inicio;
        
        if(aux != null){
            if(aux.next == inicio){
                Cliente n = (Cliente)aux.info;
                n.pasos += 1;
            }else{
                Cliente n1 = (Cliente)aux.info;
                n1.pasos += 1;
                aux = aux.next;
                while(aux != inicio){
                    Cliente n = (Cliente)aux.info;
                    n.pasos += 1;
                    aux = aux.next;
                }
            }
        }
        
        
    }
}
