/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import edd.proyectof3.EDDProyectoF3;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class AMerckle {
    String merkleRoot = "";
    ArrayList<String> txLst = new ArrayList<String>();
    Nodo raiz;
    
    
    public class Nodo{        
        String info;
        Nodo izq;
        Nodo der;

        public Nodo(String info) {
            this.info = info;
            this.izq = null;
            this.der = null;
        }       
    }
    
    public AMerckle() {
        txLst = new ArrayList<String>();
    }
    
    public void agregar(String n){
        String cod = EDDProyectoF3.convertirSHA256(n);
        txLst.add(cod);
    }
    
    public ArrayList<String> getNodeHashList(ArrayList<String> tempTxList){
        ArrayList<String> newTxList = new ArrayList<String>();
        int index = 0;
        double n =(((double)tempTxList.size())/2)%2;
        //System.out.println(n);
        while (index < tempTxList.size()) {
            // left
            String left = tempTxList.get(index);
            index++;
            // right
            String right = "";
            if (index != tempTxList.size()) {
                right = tempTxList.get(index);             
            }
            
            if(right.equals("")){
                newTxList.add(left);
                newTxList.add(left);
            }else{
                String sha2HexValue = EDDProyectoF3.convertirSHA256(left + right);
                newTxList.add(sha2HexValue);
            }  
            
            index++;
            if(index>=tempTxList.size() && n==1.0 && newTxList.size() != 1){
                newTxList.add(left);
            }
        }
        return newTxList;
    }
    
    public void generarArbol(){
        ArrayList<String> newTxList = this.txLst;
               
        if(newTxList.size()%2 != 0){
            newTxList.add(EDDProyectoF3.convertirSHA256(""));
        }else if(newTxList.size() == 0){
            newTxList.add(EDDProyectoF3.convertirSHA256(""));
            newTxList.add(EDDProyectoF3.convertirSHA256(""));
        }
        ArrayList<ArrayList<String>> nodos = new ArrayList<ArrayList<String>>();
        nodos.add(newTxList);
        while (newTxList.size() > 1) {            
            newTxList = getNodeHashList(newTxList);    
            nodos.add(newTxList);
        }
        
        this.merkleRoot = newTxList.get(0);
        graficarArbolM(nodos);
        /*for(int i = 0 ; i<nodos.size() ; i++){
            System.out.println(nodos.get(i).size());
        }*/
        
        //System.out.println(nodos);
    }

    public void graficarArbolM( ArrayList<ArrayList<String>> nodos){      
        String resultado="digraph G{\nlabel=\"Arbol Merkle\";\n"; 
        int tamano = nodos.size();
        int contador = 0;
        for (int i = tamano-1; i >= 0; i--) {
            int n = nodos.get(i).size();
            for (int j = 0; j < n; j++) {
                resultado += "N"+contador+"[label=\""+nodos.get(i).get(j)+"\"];\n";
                contador++;
            }
        }
        
        int niv = 0;
        
        while(niv<tamano){
            int t = nodos.get(niv).size();
            for (int i = 0; i < t; i++) {
                
            }
            niv++;
        }
        
        resultado += "\n}";
        
        try {
            String ruta = System.getProperty("user.dir") + "\\merkle.txt";
            File file = new File(ruta);
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(resultado);
            bw.close(); 
            EDDProyectoF3.graficarDot("merkle");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public String getMerkleRoot() {
        return merkleRoot;
    }
    
    
}
