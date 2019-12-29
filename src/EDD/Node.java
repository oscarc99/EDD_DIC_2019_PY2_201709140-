/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;
import Object.User;

/**
 *
 * @author Oscar C
 */
public class Node {
    
    User dato;
    private Node next;
    private Node before;

    public Node(User dato) {
        this.dato = dato;
        next = null;
        before = null;
    }

    public User getDato() {
        return dato;
    }

    public void setDato(User dato) {
        this.dato = dato;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setBefore(Node before) {
        this.before = before;
    }

    public Node getNext() {
        return next;
    }

    public Node getBefore() {
        return before;
    }

}
