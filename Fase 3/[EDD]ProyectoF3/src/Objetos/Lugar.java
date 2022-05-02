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
public class Lugar {
    int id;
    String departamento;
    String nombre;
    boolean sn_sucursal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSn_sucursal() {
        return sn_sucursal;
    }

    public void setSn_sucursal(boolean sn_sucursal) {
        this.sn_sucursal = sn_sucursal;
    }
    
    
}
