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
public class Mensajero {
    long dpi;
    String nombre;
    String apellido;
    String tipo_licencia;
    String genero;
    String telefono;
    String direccion;
    int entregas = 0;

    public Mensajero(long dpi, String nombre, String apellido, String tipo_licencia, String genero, String telefono, String direccion) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo_licencia = tipo_licencia;
        this.genero = genero;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public void entregar(){
        entregas += 1;
    }

    public int getEntregas() {
        return entregas;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipo_licencia() {
        return tipo_licencia;
    }

    public void setTipo_licencia(String tipo_licencia) {
        this.tipo_licencia = tipo_licencia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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
    
    
}
