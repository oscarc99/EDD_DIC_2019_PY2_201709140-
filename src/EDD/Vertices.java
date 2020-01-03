package EDD;

/**
 *
 * @author Oscar C
 */

public class Vertices {
    private NodeV first;
    private NodeV last;
    private int size;

    public Vertices() {
        first = null;
        last = null;
        size = 0;
    }
    
    public void clear(){
        first = null;
        last = null;
        size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    public void setFirst(NodeV first) {
        this.first = first;
    }

    public NodeV getFirst() {
        return first;
    }

    public NodeV getLast() {
        return last;
    }

    public int getSize() {
        return size;
    }

    public void setLast(NodeV last) {
        this.last = last;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void add_first(String dato1, String dato2) {
        NodeV n = new NodeV(dato1, dato2);
        if (isEmpty()) {
            first = n;
            last = n;
            size++;

        } else {
            n.setNext(first);
            first.setBefore(n);
            first = n;
            size++;

        }
    }

    public void add_last(String dato, String d2) {
        if (isEmpty()) {
            add_first(dato, d2);

        } else {
            NodeV n = new NodeV(dato, d2);
            this.last.setNext(n);
            n.setBefore(last);
            this.last = n;
            size++;

        }
    }

    public void add_at(String dato,String d2,  int index) {
        if (index >= 0 && index <= size) {
            if (index == 0) {
                add_first(dato,d2);
                return;
            }
            if (index == size) {
                add_last(dato, d2);
                return;
            }
            NodeV aux = first;
            int x = 0;
            while (aux != null) {
                if (x == index) {
                    break;
                }
                aux = aux.getNext();
                x++;
            }
            NodeV n = new NodeV(dato, d2);
            aux.getBefore().setNext(n);
            n.setBefore(aux.getBefore());
            n.setNext(aux);
            aux.setBefore(n);
            this.size++;

        }
    }

    

    public void remove_at(int index) {
        if (index >= 0 && index < size) {
            NodeV aux = this.getFirst();
            int x = 0;
            while (aux != null) {
                if (x == index) {
                    break;
                }
                aux = aux.getNext();
                x++;
            }

            if (index == 0) {

                this.first = aux.getNext();
                this.first.setBefore(null);
                this.size--;

            } else if (index == this.size - 1) {
                this.last = aux.getBefore();
                this.last.setNext(null);
                this.size--;
            } else {
                aux.getBefore().setNext(aux.getNext());
                aux.getNext().setBefore(aux.getBefore());
                this.size--;
            }

        }
    }

}
