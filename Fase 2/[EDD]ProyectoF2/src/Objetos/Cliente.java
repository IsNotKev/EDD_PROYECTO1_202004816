/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Estructuras.*;
import edd.proyectof2.EDDProyectoF2;
import edd.proyectof2.Usuario;

/**
 *
 * @author kevin
 */
public class Cliente {
    
    //Datos Del Cliente
    long dpi;
    String nombre;
    String contra;
    
    // Estructuras 
    Lista albumes;
    ABB capas;
    AVL imgs;
    
    public Cliente(long dpi, String nombre, String contra) {
        
        this.dpi = dpi;
        this.nombre = nombre;
        this.contra = contra;
        
        albumes = new Lista();
        capas = new ABB();
        imgs = new AVL();
    }   
    
    public long getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
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
    
    public void agregarCapa(Capa capa){
        capas.agregar(capa);
    }
    
    public void generarVentana(){
        Usuario us = new Usuario();
        us.setVisible(true);
        capas.mostrarCapas(us);
        imgs.mostrarImgs(us);
    }
    
    public void agregarAlbum(Album nAlbum){
        albumes.add(nAlbum);
    }
    
    public void graficarAlbum(){
        albumes.graficar(dpi+"_Albumes");
        EDDProyectoF2.graficarDot(dpi+"_Albumes");
    }
    
    public void graficarACapas(){
        capas.graficar(dpi+"_Capas");
    }
    
    public void graficarCapa(int n){
        Matriz miMatriz = capas.buscar(n);
        miMatriz.graficarMatriz(dpi+"_Capa"+n);
        EDDProyectoF2.graficarDot(dpi+"_Capa"+n);
    }
    
    public void agregarImagen(Imagen img){
        imgs.insertar(img);
    }
    
    public void graficarAImagenes(){
        imgs.graficar(dpi+"_Imagenes");
        EDDProyectoF2.graficarDot(dpi+"_Imagenes");
    }
    
    public void generarImagen(int n, String t){
        ABB qCapas = imgs.buscar(n);
        Matriz img;
        img = qCapas.crearImagen(t);
        img.graficarMatriz(dpi+"_img"+n);
        EDDProyectoF2.graficarDot(dpi+"_img"+n);
    }
    
    public void eliminarImagen(int n){
        imgs.raiz = imgs.eliminar(n,imgs.raiz);
        albumes.eliminar(n);
    }
    
    public void generarArbolDeCapaDeImagen(int n){
        ABB capas = imgs.buscar(n);
        capas.graficar(dpi+"_ac_img"+n);
        EDDProyectoF2.graficarDot(dpi+"_ac_img"+n);
    }
    
    public void capasHojas(){
        capas.generarTablaCapasHojas(dpi+"_capasHojas");
        EDDProyectoF2.graficarDot(dpi+"_capasHojas");
    }
    
    public void profundidad(){
        capas.generarProfundidad(dpi+"_profundidad");
        EDDProyectoF2.graficarDot(dpi+"_profundidad");
    }
    
    public void recorridos(){
        capas.graficarRecorridos(dpi+"_recorridos");
        EDDProyectoF2.graficarDot(dpi+"_recorridos");      
    }
    
    public void generarTop5(){
        Lista t = imgs.top5();
        t.graficarTop(dpi+"_top5");
        EDDProyectoF2.graficarDot(dpi+"_top5");
    }
    
    public int contarImagenes(){
        return imgs.contarImagenes();
    }
    
    public int contarCapas(){
        return capas.contarCapas();
    }
    
    public int contarAlbumes(){
        return albumes.tamano();
    }
}
