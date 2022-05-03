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
public class Ruta {
    int d1;
    int d2;
    int peso;

    public Ruta(int d1, int d2, int peso) {
        this.d1 = d1;
        this.d2 = d2;
        this.peso = peso;
    }
    
    public int getD1() {
        return d1;
    }

    public void setD1(int d1) {
        this.d1 = d1;
    }

    public int getD2() {
        return d2;
    }

    public void setD2(int d2) {
        this.d2 = d2;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    public void imprimir(){
        System.out.println("----------------------");
        System.out.println("inicio: " + d1);
        System.out.println("final: " + d2);
        System.out.println("peso: " + peso);
    }
}
