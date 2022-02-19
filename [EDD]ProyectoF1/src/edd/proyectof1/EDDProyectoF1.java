
package edd.proyectof1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EDDProyectoF1 {
    
    // Estructuras Principales       
    public static Lista ventanillas = new Lista();
    public static Cola recepcion = new Cola();   
    public static Cola impresoraC = new Cola();
    public static Cola impresoraBW = new Cola();
    public static ListaCircular espera = new ListaCircular(); 
    public static Lista atendidos = new Lista();
    
    public static boolean imprimir = false;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {     
        // Variables Auxiliares
        
        
        // Lector de opciones
        Scanner leer = new Scanner(System.in);
        Scanner leer2 = new Scanner(System.in);
        String opcion = "";
        
        // Presionar Enter Para Continuar
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sTexto;

        while (!opcion.equals("6")) {
            System.out.println("\n------------ MENÚ PRINCIPAL ------------");
            System.out.println("1. Parámetros Iniciales");
            System.out.println("2. Ejecutar Paso");
            System.out.println("3. Estado En Memoria De Las Estructuras");
            System.out.println("4. Reportes");
            System.out.println("5. Acerca De");
            System.out.println("6. Salir");
            System.out.println("----------------------------------------");
            System.out.println("Elija Una Opción\n");
            
            opcion = leer.nextLine();
            
            switch(opcion){
                case "1":                    
                    System.out.println("\n---- CARGA MASIVA DE CLIENTES ----");
                    System.out.println("\nEscriba La Ruta Del Archivo JSON\n");
                                
                    String ruta = leer.nextLine();
                                
                    int no = leerArchivo(ruta);
                                
                    System.out.println("\n"+no + " Cliente(s) Agregado(s) A La Cola De Recepción. Presione Enter Para Continuar.");
                    sTexto = br.readLine();

                    System.out.println("\n---- CANTIDAD DE VENTANILLAS ----");
                    System.out.println("\nEscriba La Cantidad De Ventanillas\n");
                                
                    int cant = leer2.nextInt();
                                
                    ventanillas.vaciar();
                    for(int i = 0; i<=(cant-1) ; i++) {
                        Ventanilla n = new Ventanilla(i+1);
                        ventanillas.add(n);
                    }
                                                                                              
                    System.out.println("\n"+cant+" Ventanilla(s) Creada(s). Presione Enter Para Continuar.");
                    sTexto = br.readLine();                                                          
                    break;
                case "2":
                    
                    System.out.println("--------------- EJECUCIÓN DE PASO ---------------");
                    
                    //Mandar Cliente A Lista Atendidos
                    espera.atendidos();
                    
                    //Imprimir y Dar
                    
                    impresoraC.imprimirC();
                    impresoraBW.imprimirBW();
                    
                    /*System.out.println("----------Impresora a color----------");
                    impresoraC.imprimirImpresoras();
                    System.out.println("----------Impresora a BW----------");
                    impresoraBW.imprimirImpresoras();
                    
                    System.out.println("----------Lista de Espera-----------");
                    espera.imprimir();*/
                    
                    //Pasar Cliente A Ventanilla
                    int vVacia = ventanillas.estaVacia();                                      
                    if(vVacia != 0){
                        Cliente sCliente = recepcion.deletC();
                        if(sCliente != null){
                            ventanillas.asignarCliente(sCliente,vVacia);
                        }
                        //System.out.println("Ventanilla " + vVacia + " esta vacía");                       
                    }else{
                        //System.out.println("Todo Lleno");
                    }
                    
                    //Dar imagen; Enviar Imagenes a Impresoras y Cliente a Lista de Espera
                    ventanillas.darImg(vVacia);
                   
                    System.out.println("\nPresione Enter Para Continuar.");
                    sTexto = br.readLine(); 
                    
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    
                    System.out.println("\n--------------- ACERCA DE ---------------");
                    System.out.println("> Kevin Steve Martinez Lemus");
                    System.out.println("> 202004816");
                    System.out.println("> Estructura De Datos Sección \"C\"");
                    System.out.println("> Ingeniería en Ciencias y Sistemas");
                    System.out.println("> 5to Semestre");
                    
                    System.out.println("\nPresione Enter Para Continuar.");
                    sTexto = br.readLine();
                    
                    break;
                case "6":
                    
                    System.out.println("\nAdios!\n");
                    
                    break;
                default:
                    
                    System.out.println("\nOpción Inválida. Elija Una Opción Correcta.");
                    
                    break;
                
            }
        }
        
    }
    
    public static int leerArchivo(String ruta) throws FileNotFoundException{
    
        File doc = new File(ruta);
        Scanner obj1 = new Scanner(doc);
        String t = "";
        int cont = 0;
        boolean exist = true;
        
        JsonParser parser = new JsonParser();
        
        while (obj1.hasNextLine()){
            t += obj1.nextLine();
            t += "\n";
        }
        
            /*t = t.replaceFirst("\\{", "[");
            t = t.substring(0, t.length()-2);*/
            //t +="]";
            
        // Obtain Object                   
        JsonObject gsonObj = parser.parse(t).getAsJsonObject();  
         
        while(exist){
            cont += 1;
            try {
                JsonObject ej = gsonObj.get("Cliente"+cont).getAsJsonObject();
                
                int id = Integer.parseInt(ej.get("id_cliente").getAsString());
                int imgc = Integer.parseInt(ej.get("img_color").getAsString());
                int imgbw = Integer.parseInt(ej.get("img_bw").getAsString());
                String name = ej.get("nombre_cliente").getAsString();
                
                /*System.out.println("-------------------------------");
                System.out.println("Cliente"+cont);
                System.out.println("ID: " + id);
                System.out.println("El Nombre es "+name);
                System.out.println("Imagenes a Color: " + imgc);
                System.out.println("Imagenes B&W: " +imgbw);*/
                
                Lista imgs = new Lista();
                
                for (int i = 0; i<=imgc-1 ; i++) {
                    Imagen nImagenC = new Imagen(id,true);
                    imgs.add(nImagenC);
                }
                
                for(int i=0;i<=imgbw-1;i++){
                    Imagen nImgBW = new Imagen(id, false);
                    imgs.add(nImgBW);
                }
                
                
                int cant = (imgc+imgbw);
                
                Cliente nuevoCliente = new Cliente(name,id,imgs,cant);
                recepcion.add(nuevoCliente);
                
            } catch (Exception e) {
                exist = false;
            }
        }
        return cont-1;
    }
    
}
