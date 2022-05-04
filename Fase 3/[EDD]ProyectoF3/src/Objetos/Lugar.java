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
    String sn_sucursal;

    public Lugar(int id, String departamento, String nombre, String sn_sucursal) {
        this.id = id;
        this.departamento = departamento;
        this.nombre = nombre;
        this.sn_sucursal = sn_sucursal;
    }
    
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

    public String getSn_sucursal() {
        return sn_sucursal;
    }

    public void setSn_sucursal(String sn_sucursal) {
        this.sn_sucursal = sn_sucursal;
    }
    
    public void imprimir(){
        System.out.println("-----------------");
        System.out.println("id: " + id);
        System.out.println("dep: " + departamento);
        System.out.println("name: " + nombre);
        System.out.println("sucursal: " + sn_sucursal);
    }
}
