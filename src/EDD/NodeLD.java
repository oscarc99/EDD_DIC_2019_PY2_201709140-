package EDD;

/**
 *
 * @author Oscar C
 */
public class NodeLD {

    int dato;
    private NodeLD next;
    private NodeLD before;

    public NodeLD(int dato) {
        this.dato = dato;
        next = null;
        before = null;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public void setNext(NodeLD next) {
        this.next = next;
    }

    public void setBefore(NodeLD before) {
        this.before = before;
    }

    public NodeLD getNext() {
        return next;
    }

    public NodeLD getBefore() {
        return before;
    }

}
