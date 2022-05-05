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
public class Entregas {
    String sede;
    String destino;
    String datetime;
    String cliente;
    String mensajero;

    public Entregas(String sede, String destino, String datetime, String cliente, String mensajero) {
        this.sede = sede;
        this.destino = destino;
        this.datetime = datetime;
        this.cliente = cliente;
        this.mensajero = mensajero;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMensajero() {
        return mensajero;
    }

    public void setMensajero(String mensajero) {
        this.mensajero = mensajero;
    }
    
    
    
}
