/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyectof3;

import Estructuras.*;

/**
 *
 * @author kevin
 */
public class EDDProyectoF3 {

    public static Lista lugares = new Lista();
    public static Lista rutas = new Lista();
    public static Lista clientes = new Lista();
    public static ListaAdyacencia milista = new ListaAdyacencia();
    
    public static void main(String[] args) {
        /*LogIn l = new LogIn();
        l.setVisible(true);*/
        
        Admin a = new Admin();
        a.setVisible(true);
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
