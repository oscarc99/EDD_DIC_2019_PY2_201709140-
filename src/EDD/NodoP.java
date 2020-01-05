/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

/**
 *
 * @author Oscar C
 */
public class NodoP {
    NodoP next;
    String dato;

    
    
    public NodoP(String dato) {
        this.dato = dato;
    }

    public NodoP getNext() {
        return next;
    }

    public String getDato() {
        return dato;
    }

    public void setNext(NodoP next) {
        this.next = next;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    
    
    
    
    
}
