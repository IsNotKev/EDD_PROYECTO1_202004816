/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Estructuras.*;

/**
 *
 * @author kevin
 */
public class Cliente {
    String dpi;
    String nombre;
    String contra;
    ABB capas;
    Lista albumes;

    public Cliente(String dpi, String nombre, String contra) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.contra = contra;
        
    }   
    
    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public ABB getCapas() {
        return capas;
    }

    public void setCapas(ABB capas) {
        this.capas = capas;
    }

    public Lista getAlbumes() {
        return albumes;
    }

    public void setAlbumes(Lista albumes) {
        this.albumes = albumes;
    }
    
    public void imprimir(){
        System.out.println("--------------------------------");
        System.out.println("Nombre: " + nombre);
        System.out.println("DPI: " + dpi);
        System.out.println("Contraseña: " + contra);
    }
    
}
