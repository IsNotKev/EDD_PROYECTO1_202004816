/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyectof2;

import Estructuras.*;
import Objetos.*;

/**
 *
 * @author kevin
 */

public class EDDProyectoF2 {

    public static int cont = 0;
    public static ArbolB clientes = new ArbolB();
    public static Cliente clienteActual ;
    //public static Cliente clientePrueba = new Cliente(252525252,"Kevin","123");
    
    public static void main(String[] args) {
        /*Login log = new Login();
        log.setVisible(true);*/
        
        /*Usuario u = new Usuario();
        u.setVisible(true); */
        
        Admin ad = new Admin();
        ad.setVisible(true);     
        
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
