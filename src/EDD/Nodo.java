package EDD;

/**
 *
 * @author Oscar C
 */
public class Nodo {
    
    String dato;
    private Nodo next;
    private Nodo before;

    public Nodo(String dato) {
        this.dato = dato;
        next = null;
        before = null;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }

    public void setBefore(Nodo before) {
        this.before = before;
    }

    public Nodo getNext() {
        return next;
    }

    public Nodo getBefore() {
        return before;
    }

}

