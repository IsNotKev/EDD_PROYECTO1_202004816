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
    public static Cliente clientePrueba = new Cliente(252525252,"Kevin","123");
    
    public static void main(String[] args) {
        /*Login log = new Login();
        log.setVisible(true);*/
        
        /*Usuario u = new Usuario();
        u.setVisible(true); */
        
        Admin ad = new Admin();
        ad.setVisible(true);
        
        AVL prueba = new AVL();
        prueba.insertar(new Imagen(74, new ABB()));
        prueba.insertar(new Imagen(40, new ABB()));
        prueba.insertar(new Imagen(23, new ABB()));
        prueba.insertar(new Imagen(8, new ABB()));
        prueba.insertar(new Imagen(6, new ABB()));
        prueba.insertar(new Imagen(37, new ABB()));
        prueba.insertar(new Imagen(32, new ABB()));
        prueba.insertar(new Imagen(45, new ABB()));
        prueba.insertar(new Imagen(25, new ABB()));
        prueba.insertar(new Imagen(1, new ABB()));
        prueba.insertar(new Imagen(88, new ABB()));
        prueba.insertar(new Imagen(29, new ABB()));
        prueba.insertar(new Imagen(4, new ABB()));
        prueba.insertar(new Imagen(11, new ABB()));
        prueba.insertar(new Imagen(39, new ABB()));
        prueba.insertar(new Imagen(14, new ABB()));
        prueba.insertar(new Imagen(66, new ABB()));
        prueba.insertar(new Imagen(24, new ABB()));
        prueba.insertar(new Imagen(12, new ABB()));
        prueba.insertar(new Imagen(7, new ABB()));
        
        prueba.graficar("Prueba");
        graficarDot("Prueba");
        
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
