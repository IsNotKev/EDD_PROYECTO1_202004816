/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyectof3;

import Estructuras.*;
import Objetos.*;
import at.favre.lib.crypto.bcrypt.BCrypt;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author kevin
 */
public class EDDProyectoF3 {

    public static Lista rutas = new Lista();
    public static ListaAdyacencia milista = new ListaAdyacencia();
    public static ArrayList<Lugar> lugares = new ArrayList<Lugar>();
    public static ArrayList<Cliente> clientes = new ArrayList<Cliente>();  
    public static ArrayList<Mensajero> mensajeros = new ArrayList<Mensajero>();
    
    public static ArrayList<Entregas> entregas = new ArrayList<Entregas>();  
    
    public static Cliente actual;
    
    public static BlockChein blockchein = new BlockChein();
    public static AMerckle arbol = new AMerckle();
    
    public static Timer timer = new Timer (180000, new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Generando Bloque ...");
                blockchein.crearBloque();
            }
        });
    public static int cantCeros = 4;
    
    public static void main(String[] args) {
        timer.start();
        for(int i=0; i<37;i++){
            mensajeros.add(null);
        }        
        
        LogIn l = new LogIn();
        l.setVisible(true);
        
        /*Admin a = new Admin();
        a.setVisible(true);*/
        
        /*Usuario us = new Usuario();
        us.setVisible(true);*/

        /*arbol.agregar("Hola2");
        arbol.agregar("Hola3");
        arbol.agregar("Hola4");
        arbol.agregar("Hola5");
        arbol.agregar("Hola6");
        arbol.agregar("Hola7");
        arbol.agregar("Hola8");
        arbol.agregar("Hola9");
        arbol.generarArbol();
        System.out.println(arbol.getMerkleRoot());*/ 
        /*entregas.add(new Entregas("sede1","mi casa","hoy","yo","juanito"));
        blockchein.crearBloque();
        blockchein.crearBloque();
        blockchein.crearBloque();*/
        //blockchein.imprimir();
        
    }
    
    public static void agregarMensajero(Mensajero nuevo){
        long dpi = nuevo.getDpi(); 
        
        verificarTamano();
        
        int tamano = mensajeros.size();
        int pos = Math.toIntExact(dpi%tamano);
        
        if(pos<=tamano-1){
            Mensajero p = mensajeros.get(pos);
            if(p==null){
                mensajeros.set(pos, nuevo);
                //System.out.println(pos + " : Libre");
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
        int pos = Math.toIntExact((dpi%7+1)*i);
        if(pos<=tamano-1){
            Mensajero p = mensajeros.get(pos);
            if(p==null){
                mensajeros.set(pos, nuevo);
                //System.out.println(pos + " : Libre");
            }else{
                //System.out.println(pos + ": Ocupado");
                agregarMensajeroRecursivo(nuevo,i+1);               
            }
        }else{
            System.out.println("Error");
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
        //System.out.println(cant+","+porcentaje+","+tamano);
        if(cant>=porcentaje){
            int nuevaCant = tamano +1;
            while (!esPrimo(nuevaCant)) {                
                nuevaCant += 1;
            }
            int mas = nuevaCant-tamano;
            for(int j=0;j<mas;j++){
                mensajeros.add(null);
            }
            //System.out.println("Se actualizo tamano a " + nuevaCant);
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
    
    public static void graficarMensajeros(){
        String resultado="digraph G{\nlabel=\"Mensajeros\";\n";        
        
        resultado += "N0[shape=record,label=\"{";
        
        for (int i = 0; i < mensajeros.size(); i++) {
            Mensajero a = mensajeros.get(i);
            if(a!=null){
                resultado += "DPI:"+a.getDpi()+"\\nNOMBRE: " + a.getNombre() + "\\nAPELLIDO: "+ a.getApellido() +"\\nENTREGAS: " + a.getEntregas() +"|";
            }else{
                resultado += "-"+i + "-|";
            }         
        }
        
        resultado = resultado.substring(0, resultado.length()-1);
        
        resultado+="}\"];\n}";
        
        try {
            String ruta = System.getProperty("user.dir") + "\\mensajeros.txt";
            File file = new File(ruta);
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(resultado);
            bw.close(); 
            graficarDot("mensajeros");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    
    public static String convertirSHA256(String password) {
	MessageDigest md = null;
	try {
		md = MessageDigest.getInstance("SHA-256");
	} 
	catch (NoSuchAlgorithmException e) {		
		e.printStackTrace();
		return null;
	}
	    
	byte[] hash = md.digest(password.getBytes());
	StringBuffer sb = new StringBuffer();
	    
	for(byte b : hash) {        
		sb.append(String.format("%02x", b));
	}
	    
	return sb.toString();
}
}
