/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import edd.proyectof3.EDDProyectoF3;
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
        }
        ArrayList<ArrayList<String>> nodos = new ArrayList<ArrayList<String>>();
        nodos.add(newTxList);
        while (newTxList.size() != 1) {            
            newTxList = getNodeHashList(newTxList);    
            nodos.add(newTxList);
        }
        
        this.merkleRoot = newTxList.get(0);
        //graficarArbolM(nodos);
        /*for(int i = 0 ; i<nodos.size() ; i++){
            System.out.println(nodos.get(i).size());
        }*/
        
        //System.out.println(nodos);
    }

    public void graficarArbolM( ArrayList<ArrayList<String>> nodos){
        int tamano = nodos.size();
        raiz = new Nodo(nodos.get(tamano-1).get(0));
        
        int index = 1;
        int contador = 0;
        
        while(index<tamano){                        
            ArrayList<String> padres = nodos.get(tamano-index);
            ArrayList<String> hijos = nodos.get(tamano-index-1);
            
            for(String cadena :padres) {
                int i = 0;
                System.out.println("N"+contador+"[label=\""+cadena+"\"]");
                String ant = "N"+contador;
                contador++;
                while(i<hijos.size()){
                    System.out.println("N"+contador+"[label=\""+hijos.get(i)+"\"]");
                    System.out.println(ant+"->N"+contador +";");
                    i++;
                    System.out.println(cadena+"->"+hijos.get(i)+";");
                    i++;
                }
            }
            
            index++;
        }
    }
    
    public String getMerkleRoot() {
        return merkleRoot;
    }
    
    
}
