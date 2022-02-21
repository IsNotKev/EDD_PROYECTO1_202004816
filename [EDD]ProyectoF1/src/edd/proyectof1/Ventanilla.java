/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyectof1;

/**
 *
 * @author kevin
 */
public class Ventanilla {
    
    private int id;
    private Cliente cliente;
    private Pila imgs;
    boolean recibe;

    public Ventanilla(int id) {
        this.id = id;
        this.cliente = null;
        this.imgs = new Pila();
    }
    
    public int getID(){
        return id;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pila getImgs() {
        return imgs;
    }

    public void setImgs(Pila imgs) {
        this.imgs = imgs;
    }

     public void recibirImg(){
         if(cliente != null){
             Imagen aux = (cliente.getImgs()).deletImg();
             
             if(aux != null){
                 
                 if(aux.isTipo()){
                     System.out.println("Ventanilla " + id + " Recibió Una Imagen A Color");
                 }else{
                     System.out.println("Ventanilla " + id + " Recibió Una Imagen Blanco Y Negro");
                 }                
                 imgs.push(aux);
             }else{       
                 System.out.println("Cliente " + cliente.getId() +" salió de ventanilla " + id);
                 EDDProyectoF1.espera.add(cliente);
                 imgs.vaciarImgs();
                 cliente = null;
             }
             
         }
     }
    
}
