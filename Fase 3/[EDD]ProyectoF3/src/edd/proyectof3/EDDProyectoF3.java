/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyectof3;

import Estructuras.*;
import Objetos.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class EDDProyectoF3 {

    public static Lista lugares = new Lista();
    public static Lista rutas = new Lista();
    public static Lista clientes = new Lista();
    public static ListaAdyacencia milista = new ListaAdyacencia();
    public static ArrayList<Mensajero> mensajeros = new ArrayList<Mensajero>();
    
    public static void main(String[] args) {
        
        for(int i=0; i<37;i++){
            mensajeros.add(null);
        }
        
        /*LogIn l = new LogIn();
        l.setVisible(true);*/
        
        Admin a = new Admin();
        a.setVisible(true);
    }
    
    public static void agregarMensajero(Mensajero nuevo){
        long dpi = nuevo.getDpi(); 
        
        //verificarTamano();
        
        int tamano = mensajeros.size();
        int pos = Math.toIntExact(dpi%tamano);
        
        if(pos<=tamano-1){
            Mensajero p = mensajeros.get(pos);
            if(p==null){
                mensajeros.set(pos, nuevo);
            }else{
                agregarMensajeroRecursivo(nuevo,1);
            }
        }else{
            System.out.println("Error");
        }       
    }
    
    public static void agregarMensajeroRecursivo(Mensajero nuevo, int i){
        long dpi = nuevo.getDpi();
        int tamano = mensajeros.size();
        int pos = Math.toIntExact((dpi%tamano+1)*i);
        
        if(pos<=tamano-1){
            Mensajero p = mensajeros.get(pos);
            if(p==null){
                mensajeros.set(pos, nuevo);
            }else{
                agregarMensajeroRecursivo(nuevo,i+1);
            }
        }   
    }
    
    public static void verificarTamano(){
        int tamano = mensajeros.size();
        int porcentaje = Math.toIntExact(Math.round(tamano*0.75));
        int cant = 0;
        for(int i = 0;i<tamano;i++){
            Mensajero m = mensajeros.get(i);
            if(m!=null){
                cant += 1;
            }
        }
        
        if(cant>=porcentaje){
            int nuevaCant = cant +1;
            while (!esPrimo(nuevaCant)) {                
                nuevaCant += 1;
            }
            int mas = nuevaCant-cant;
            for(int j=0;j<mas;j++){
                mensajeros.add(null);
            }
            System.out.println("Se actualizo tamano a " + nuevaCant);
        }
        
    } 
    
    public static boolean esPrimo(int numero) {
        if (numero == 0 || numero == 1 || numero == 4) {
          return false;
        }
        for (int x = 2; x < numero / 2; x++) {
          if (numero % x == 0){
              return false;
          }         
        }
        return true;
    }
    
    public static void graficarDot(String title){
       try {
           
           String dotPath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
           String fileInputPath = System.getProperty("user.dir") + "\\"+title+".txt";
           String fileOutputPath = System.getProperty("user.dir") + "\\"+title+".png";

           String tParam = "-Tpng";
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
}
