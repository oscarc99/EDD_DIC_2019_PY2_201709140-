package EDD;

/**
 *
 * @author Oscar C
 */
public class NodeC {
    private String dato;
    private NodeC next;

    public NodeC(String dato) {
        this.dato = dato;
        this.next = null;
    }

    public String getDato() {
        return dato;
    }

    public NodeC getNext() {
        return next;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public void setNext(NodeC next) {
        this.next = next;
    }
    
    
            
}
