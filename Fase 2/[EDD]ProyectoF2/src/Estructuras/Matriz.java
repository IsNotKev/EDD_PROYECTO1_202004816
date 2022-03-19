/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author kevin
 */
public class Matriz {
    
    Lista horizontal = new Lista();
    Lista vertical = new Lista();
    
    
    public class Nodo{        
        Object info;
        Nodo next, anterior;
        Nodo izquierda, derecha, arriba, abajo;
        int x,y;

        public Nodo(Object info) {
            this.info = info;
            x = y = 0;
            this.next= this.anterior = this.izquierda = this.derecha = this.abajo = this.arriba = null;
        }   
        
        public Nodo(Object info,int x, int y) {
            this.info = info;
            this.x = x;
            this.y = y;
            this.next = this.anterior = this.izquierda = this.derecha = this.abajo = this.arriba = null;
        }
    }
    
    public class Lista{
        Nodo raiz, ultimo;
        public Lista(){
            this.raiz = this.ultimo = null;
        }
        
        public void insertar(int no){
            Nodo nuevo = new Nodo(no);
            //System.out.println(no);
            if(raiz == null){
                raiz = ultimo = nuevo;
            }else{
                ordenar(nuevo);
            }
        }
        
        public void ordenar(Nodo nodo){
            Nodo aux = raiz;
            
            while(aux != null){
                if((int)aux.info < (int)nodo.info){
                    aux = aux.next;
                }else{
                    if(aux == raiz){
                        nodo.next =aux;
                        aux.anterior = nodo;                       
                        raiz = nodo;
                        return;
                    }else{
                        nodo.anterior = aux.anterior;
                        aux.anterior.next = nodo;
                        nodo.next = aux;
                        aux.anterior = nodo;
                        return;
                    }
                }
            }
            
            if(ultimo == raiz){
                nodo.anterior = raiz;
                raiz.next = nodo;
                ultimo = nodo;               
            }else{
                ultimo.next = nodo;
                nodo.anterior = ultimo;
                ultimo = nodo; 
            }                               
        }
        
        public Nodo search(int valor){
            Nodo temp = raiz;
            while(temp != null){
                if((int)temp.info == valor){
                    return temp;
                }
                temp = temp.next;
            }
            return null;
        }
        
        public void imprimir(){
            Nodo temp = raiz;
            while(temp != null){
                System.out.println("Cabecera: " + temp.info);
            }
        }
    }
    
    public void Matriz(){
        horizontal = new Lista();
        vertical = new Lista();
    }
    
    public void insertar(String valor, int x, int y){
        Nodo h = horizontal.search(x);
        Nodo v = vertical.search(y);
        
        if(h==null && v == null){
            caso1(valor,x,y);
        }else if(h==null && v != null){
            caso2(valor,x,y);
        }else if(h!=null && v == null){
            caso3(valor,x,y);
        }else{
            caso4(valor,x,y);
        }
    }
    
    // No existe ni x ni y
    
    public void caso1(String valor,int x, int y){
        horizontal.insertar(x);
        vertical.insertar(y);
        
        Nodo h = horizontal.search(x);
        Nodo v = vertical.search(y);
        
        Nodo nuevo = new Nodo(valor,x,y);
        
        h.abajo = nuevo;
        nuevo.arriba = h;
        
        v.derecha = nuevo;
        nuevo.izquierda = v;
    }
    
    //Solo existe y
    public void caso2(String valor, int x , int y){
        horizontal.insertar(x);
        
        Nodo h =  horizontal.search(x);
        Nodo v = vertical.search(y);
        
        boolean agregado = false;
        
        Nodo n = new Nodo(valor,x,y);
        Nodo aux = v.derecha;
        int cabecera;
        
        while(aux != null){
            cabecera = aux.x;
            if(cabecera<x){
                aux = aux.derecha;
            }else{
                n.derecha = aux;
                n.izquierda = aux.izquierda;
                aux.izquierda.derecha = n;
                aux.izquierda = n;
                agregado = true;
                break;
            }
        }
        
        if(agregado == false){
            aux = v.derecha;
            while(aux.derecha !=null){
                aux = aux.derecha;
            }
            n.izquierda = aux;
            aux.derecha = n;
        }
        
        h.abajo = n;
        n.arriba = h;
        
    }
    
    //Solo existe x
    public void caso3(String valor, int x, int y){
        vertical.insertar(y);
        
        Nodo h = horizontal.search(x);
        Nodo v = vertical.search(y);
        boolean agregado = false;
        
        Nodo nuevo = new Nodo(valor,x,y);
        Nodo aux = h.abajo;
        int cabecera;
        
        while(aux != null){
            cabecera = aux.y;
            if(cabecera<y){
                aux = aux.abajo;
            }else{
                nuevo.abajo = aux;
                nuevo.arriba = aux.arriba;
                aux.arriba.abajo = nuevo;
                aux.arriba = nuevo;
                agregado = true;
                break;
            }
        }
        
        if(agregado == false){
            aux = h.abajo;
            while(aux.abajo != null){
                aux = aux.abajo;
            }
            
            nuevo.arriba = aux;
            aux.abajo = nuevo;
        }   
        v.derecha = nuevo;
        nuevo.izquierda = v;
        
    }
    
    //Existe x y y
    public void caso4(String valor, int x , int y){
        Nodo v = vertical.search(y);
        Nodo h = horizontal.search(x);
        
        Nodo n = new Nodo(valor,x,y);
        boolean agregado = false;
        
        Nodo aux = v.derecha;
        int cabecera;
        
        while(aux != null){
            cabecera = aux.x;
            if(cabecera<x){
                aux = aux.derecha;
            }else{
                n.derecha = aux;
                n.izquierda = aux.izquierda;
                aux.izquierda.derecha = n;
                aux.izquierda = n;
                agregado = true;
                break;
            }
        }
        
        if(agregado == false){
            aux = v.derecha;
            while(aux.derecha !=null){
                aux = aux.derecha;
            }
            n.izquierda = aux;
            aux.derecha = n;
        }
        
        agregado = false;
        aux = h.abajo;
        
        while(aux != null){
            cabecera = aux.y;
            if(cabecera<y){
                aux = aux.abajo;
            }else{
                n.abajo = aux;
                n.arriba = aux.arriba;
                aux.arriba.abajo = n;
                aux.arriba = n;
                agregado = true;
                break;
            }
        }
        
        if(agregado == false){
            aux = h.abajo;
            while(aux.abajo != null){
                aux = aux.abajo;
            }
            
            n.arriba = aux;
            aux.abajo = n;
        }
    }
    
    public void imprimir_horizontal(){
        Nodo cabecera = horizontal.raiz;
        while(cabecera != null){
            Nodo aux = cabecera.abajo;
            while(aux != null){
                System.out.println(aux.info + ", x=" + aux.x + ", y = " +aux.y);
                aux = aux.abajo;
            }
            cabecera = cabecera.next;
        }
    }    
 }
