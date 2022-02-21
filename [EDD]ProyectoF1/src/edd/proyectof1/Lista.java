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
public class Lista {
    Nodo cabecera;
    
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
    
    public int estaVacia(){
        
        Nodo aux = cabecera;
        
        while(aux != null){
            Ventanilla ver = (Ventanilla)aux.info;
            if(ver.getCliente() == null ){
                return ver.getID();
            }
            aux = aux.next;
        }
        
        return 0;
    }
    
    public void asignarCliente(Cliente nCliente, int id){
        Nodo aux = cabecera;
        nCliente.setVentanilla(id);
        
        while(aux != null){
            Ventanilla ver = (Ventanilla)aux.info;
            if(ver.getID() == id){
                ver.setCliente(nCliente);
                System.out.println("El Cliente " + nCliente.getId() + " INGRESA A VENTANILLA " + id);
                break;
            }
            aux = aux.next;
        }
    }
    
    public void darImg(int excepcion){
        Nodo aux = cabecera;
        while(aux!=null){
            Ventanilla ver = (Ventanilla)aux.info;
            if(ver.getID() != excepcion){
                if (ver.getCliente() != null) {
                    ver.recibirImg();           
                }
            }
            aux = aux.next;
        }
    }
    
    public Imagen deletImg(){
        Imagen aux = null;
        if(cabecera != null){
            aux = (Imagen)cabecera.info;      
            cabecera = cabecera.next;
        }      
        return aux;
    }
    public void vaciar(){
        cabecera = null;
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
    
    public int tamano(){
        int contador = 0;
        
        Nodo aux = cabecera;
        
        while(aux != null){
            contador += 1;
            aux = aux.next;
        }           
        return contador;
    }
    
    public void eliminarEspera(){
        Nodo aux = cabecera;
        
        while(aux!= null){
            Cliente n = (Cliente)aux.info;
            
            EDDProyectoF1.espera.delete(n.getId());
            
            aux = aux.next;
        }
    }
    
    
    public void generarDot(String title){
        String resultado="digraph G{\nlabel=\""+title+"\";\nnode [shape=box];\nrankdir=LR;\n";        
        String conexiones="";
        String nodos="";
        
        Nodo aux = cabecera;
       
        if(title.equals("Ventanillas")){
                                  
            while(aux != null){
                
                Ventanilla ve = (Ventanilla)aux.info;
            
                if(ve.getCliente() != null){
                    Cliente n = ve.getCliente();
                    nodos += "N"+n.hashCode()+"[label=\"Cliente "+n.getId()+" \nIMG C: "+n.getC()+" \nIMG BN: "+n.getBw()+"\"];\n";
                    nodos += "N"+aux.hashCode()+"[label=\"VENTANILLA " + ve.getID() + "\"];\n";
                    conexiones += "N"+n.hashCode()+" -> "+"N"+aux.hashCode()+";\n";
                    
                    Pila.Nodo aux2 = ve.getImgs().cabecera;
                    
                    if(aux2 != null){
                        conexiones += "N"+aux.hashCode()+" -> "+"N"+aux2.hashCode()+"[arrowhead=none];\n";
                    }
                    
                    while(aux2 != null){
                        
                        Imagen g = (Imagen)aux2.info;
                        
                        if(g.isTipo()){
                            nodos += "N"+aux2.hashCode()+"[label=\"IMAGEN C\"];\n";
                        }else{
                            nodos += "N"+aux2.hashCode()+"[label=\"IMAGEN BN\"];\n";
                        }
                        
                        if(aux2.next != null){
                            conexiones+="N"+aux2.hashCode()+ " -> "+"N"+aux2.next.hashCode()+"[arrowhead=none];\n";
                        }
                        
                        aux2 = aux2.next;
                    }
                   
                }else{
                    nodos += "N"+aux.hashCode()+"[label=\"VENTANILLA " + ve.getID() + "\"];\n";
                }
                                             
                if(aux.next != null){
                    conexiones+="N"+aux.hashCode()+ " -> "+"N"+aux.next.hashCode()+";\n";
                }
                aux = aux.next;
            }
        }else{
            while(aux != null){
                Cliente n = (Cliente)aux.info;
                nodos += "N"+aux.hashCode()+"[label=\"Cliente "+n.getId()+" \nIMG C: "+n.getC()+" \nIMG BN: "+n.getBw()+"\"];\n";
                if(aux.next != null){
                    conexiones+="N"+aux.hashCode()+ " -> "+"N"+aux.next.hashCode()+";\n";
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
    
    public void graficar(Lista n , String title){
        
       if(title.equals("TopColor") || title.equals("TopBW")){
           n.generarDotTop(title);
       }else{
           n.generarDot(title);
       }
       
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
            Ventanilla n = (Ventanilla)aux.info;           
            if(n.getCliente() != null){
                n.getCliente().pasos += 1;
            }          
            aux = aux.next;
        }
    }
    
    public Cliente buscar(int id){
        Cliente n = null;
        
        Nodo aux = cabecera;
        
        while(aux != null){
            Cliente auxiliar = (Cliente)aux.info;
            
            if(auxiliar.getId() == id){
                n = auxiliar;
            }
            
            aux = aux.next;
        }
        
        return n;
    }
    
    public Cliente masPasos(Lista a){
        
        Nodo aux = cabecera;
        int id = 0;
        int cant=0;
        
        while(aux != null){
            Cliente auxiliar = (Cliente)aux.info;
            if(auxiliar.pasos > cant){
                id = auxiliar.getId();
                cant = auxiliar.pasos;
            }           
            aux = aux.next;
        }
        
              
        Cliente n = a.buscar(id);        
        return n;
    }
    
    public void topColor( Lista p){
        
        Lista top = new Lista();   
        
        for(int i = 0;i<5;i++){
            Nodo aux = cabecera;
            int id = 0;
            int cant = 0;
            while(aux != null){
                Cliente n = (Cliente)aux.info;
                
                if(n.getC()>=cant && !(top.existe(n.getId()))){
                    id = n.getId();
                    cant = n.getC();
                }   
                aux = aux.next;
            }
            
            if(p.buscar(id) != null){
                top.add(p.buscar(id));
            }           
        }  
        
        top.graficar(top, "TopColor");
    }  
    
    public void topBW( Lista p){
        
        Lista top = new Lista();   
        
        for(int i = 0;i<5;i++){
            Nodo aux = cabecera;
            int id = 0;
            int cant = ((Cliente)aux.info).getBw();
            while(aux != null){
                Cliente n = (Cliente)aux.info;
                
                if(n.getBw()<=cant && !(top.existe(n.getId()))){
                    id = n.getId();
                    cant = n.getBw();
                }   
                aux = aux.next;
            }
            
            if(p.buscar(id) != null){
                top.add(p.buscar(id));
            }           
        }  
        
        top.graficar(top, "TopBW");
    }
    
    public void generarDotTop(String title){
        String resultado="digraph G{\nlabel=\""+title+"\";\nnode [shape=box];\nrankdir=TB;\n";        
        String conexiones="";
        String nodos="";
        
        Nodo aux = cabecera;
        
        int t = 0;
        
        while(aux!=null){
            t += 1;
            Cliente n = (Cliente)aux.info;
            nodos += "N"+aux.hashCode()+"[label=\"TOP "+t+"\n\nCliente "+n.getId()+" \nIMG C: "+n.getC()+" \nIMG BN: "+n.getBw()+"\"];\n";
            if(aux.next != null){
                conexiones+="N"+aux.hashCode()+ " -> "+"N"+aux.next.hashCode()+";\n";
            }
            
            aux = aux.next;
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
   
    
    public boolean existe(int id){
        boolean existe = false;
        
        Nodo aux = cabecera;
        
        while(aux != null){
            Cliente n = (Cliente)aux.info;
            if(n.getId() == id){
                existe = true;
            }              
            aux = aux.next;
        }
        
        return existe;
    }
    
    public String aleatorio(){
    
        Nodo aux = cabecera;
        int x = (int) (Math.random()*(10-0)) + 0;
        int cont = 0;
        
        while(aux != null){
            
            if(x==cont){
                return (String)aux.info;
            }           
            aux = aux.next;           
            cont +=1;
        }   
        
        return "";
    }
    
}
