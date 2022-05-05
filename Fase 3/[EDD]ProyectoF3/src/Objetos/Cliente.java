/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author kevin
 */
public class Cliente {
    //Datos Del Cliente
    long dpi;
    String nombre;
    String usuario;
    String correo;
    String contra;
    String telefono;
    String direccion;
    int id_municipio;
    int solicitudes = 0;

    public Cliente(long dpi, String nombre, String usuario, String correo, String contra, String telefono, String direccion, int id_municipio) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.usuario = usuario;
        this.correo = correo;
        this.contra = contra;
        this.telefono = telefono;
        this.direccion = direccion;
        this.id_municipio = id_municipio;
    }
    
    public void solicitar(){
        solicitudes += 1;
    }

    public int getSolicitudes() {
        return solicitudes;
    }
      
    public long getDpi() {
        return dpi;
    }

    public void setDpi(long dpi) {
        this.dpi = dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(int id_municipio) {
        this.id_municipio = id_municipio;
    }
    
    
    
}
