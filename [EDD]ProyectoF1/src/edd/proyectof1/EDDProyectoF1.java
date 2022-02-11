
package edd.proyectof1;

import java.util.Scanner;

public class EDDProyectoF1 {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        String opcion = "";
        String opcion2 = "";

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
                    opcion2 = "";
                    while (!opcion2.equals("3")) {    
                        
                        System.out.println("\n------- PARAMETROS INICIALES -------");
                        System.out.println("1. Carga Masiva de Clientes");
                        System.out.println("2. Cantidad de Ventanillas");
                        System.out.println("3. Regresar");
                        System.out.println("------------------------------------");
                        System.out.println("Elija Una Opción\n");

                        opcion2 = leer.nextLine();   
                        
                        switch(opcion2){
                            case "1":
                                System.out.println("\n---- CARGA MASIVA DE CLIENTES ----");
                                System.out.println("\nEscriba La Ruta Del Archivo JSON\n");
                                
                                String ruta = leer.nextLine();
                                
                                opcion2 = "3";
                                break;
                            case "2":
                                System.out.println("\n---- CANTIDAD DE VENTANILLAS ----");
                                System.out.println("\nEscriba La Cantidad De Ventanillas\n");
                                
                                
                                
                                opcion2 = "3";
                                break;
                            default:
                                System.out.println("\nOpción Inválida. Elija Una Opción Correcta.");
                                break;
                        }
                    }
                                                           
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    
                    System.out.println("\nAdios!");
                    
                    break;
                default:
                    
                    System.out.println("\nOpción Inválida. Elija Una Opción Correcta.");
                    
                    break;
                
            }
        }
        
    }
    
}
