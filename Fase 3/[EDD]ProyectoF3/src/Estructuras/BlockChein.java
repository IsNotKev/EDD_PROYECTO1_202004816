/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Entregas;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edd.proyectof3.EDDProyectoF3;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class BlockChein {
    
    Nodo genesis;
    int index;
    
    public BlockChein() {
        genesis = null;
        index = 0;
    }   
    
    public class Bloque{
        int index;
        String timestamp;    
        int nonce;
        ArrayList<Entregas> entregas; 
        String previusHash;
        String rootMerkle;
        String hash; 

        public Bloque(int index, String timestamp,String previusH, int nonce, String rootMerkle, String hash ,ArrayList<Entregas> entregas ) {
            this.index = index;
            this.timestamp = timestamp;
            this.nonce = nonce;
            this.previusHash = previusH;
            this.rootMerkle = rootMerkle;
            this.hash = hash;
            this.entregas = entregas;
        }  
        
        public void imprimir(){
            System.out.println("INDEX: " + index);
            System.out.println("TIMESTAMP: " + timestamp);
            System.out.println("NONCE: " + nonce);
            System.out.println("PHASH: " + previusHash);
            System.out.println("ROOTM: " + rootMerkle);
            System.out.println("HASH: " + hash);
        }
    }
    public class Nodo{
        Nodo next;
        Bloque info;
        
        public Nodo(Bloque info){
            this.info = info;
            this.next = null;
        }
    }
    
    public void agregar(Bloque info){
        if(genesis == null){
            genesis = new Nodo(info);
        }else{
            Nodo aux = genesis;
            while(aux.next!=null){
                aux = aux.next;
            }
            //info.previusHash = aux.info.hash;
            aux.next = new Nodo(info);
        }
    }
    
    public String ultimoH(){
        if(genesis == null){
            return "0000";
        }else{
            Nodo aux = genesis;
            while(aux.next!=null){
                aux = aux.next;
            }
            return aux.info.hash;
        }
    }
    
    public void imprimir(){
        Nodo aux = genesis;
        while(aux!=null){
            aux.info.imprimir();
            aux = aux.next;
        }
    }
    
    public void crearBloque(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yy::HH:mm:ss");
        String hora = dtf.format(LocalDateTime.now());
        
        String previusH = ultimoH();
        
        EDDProyectoF3.arbol.generarArbol();
        String rooth = EDDProyectoF3.arbol.getMerkleRoot();
        
        int nonce = 0;      
        String hash = EDDProyectoF3.convertirSHA256(index+hora+previusH+rooth+nonce);
        char[] aCaracteres = hash.toCharArray();
        boolean correcto = false;
        
        int cant = EDDProyectoF3.cantCeros;
        int ss = 0;
               
        while (!correcto) {           
            for(int i = 0;i<cant;i++){
                //System.out.println(aCaracteres[i]);
                if(aCaracteres[i] == '0'){
                    ss++;
                }
            }
            if(ss==cant){
                correcto = true;
            }else{
                nonce++;
                hash = EDDProyectoF3.convertirSHA256(index+hora+previusH+rooth+nonce);
                aCaracteres = hash.toCharArray();
                ss = 0;
            }
        }
        
        ArrayList<Entregas> et = EDDProyectoF3.entregas;
        
        //AGREGANDO NUEVO BLOQUE
        Bloque n = new Bloque(index,hora,previusH,nonce,rooth,hash,et);
        agregar(n);
        
        //REINICIANDO ESTRUCTURAS
        EDDProyectoF3.arbol = new AMerckle();
        EDDProyectoF3.entregas = new ArrayList<Entregas>();
        
        //GENERANDO JSON
        generarJson(n);
        
        index++;
    }
    
    public void generarJson(Bloque n){
        
        
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(prettyGson.toJson(n));
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            String ruta = System.getProperty("user.dir") + "\\blockchain\\Bloques\\"+n.index+".json";
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);

            pw.println(prettyGson.toJson(n));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    public void graficar(){
        String resultado="digraph G{\nlabel=\"BlockChain\";\nrankdir=\"LR\";\n";  
        
        Nodo aux = genesis;
        String anterior = "";
        while(aux!=null){
            resultado += "L"+aux.info.index+"[shape=record, label=\"{<I"+aux.info.index+">INDEX: "+ aux.info.index +"\\n HASH: "+aux.info.hash+"}|{<N"+aux.info.index+">PREVIOUS HASH: "+aux.info.previusHash+"}|{MERKLE ROOT: "+aux.info.rootMerkle+"}\"];\n";
            if(!anterior.equals("")){
                resultado += anterior + "->L"+aux.info.index+":N" + aux.info.index + ";\n";
            }           
            anterior = "L"+aux.info.index+":" +"I"+aux.info.index;
            aux = aux.next;
        }
        
        resultado += "\n}";
        
        try {
            String ruta = System.getProperty("user.dir") + "\\blockchain.txt";
            File file = new File(ruta);
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(resultado);
            bw.close(); 
            EDDProyectoF3.graficarDot("blockchain");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
