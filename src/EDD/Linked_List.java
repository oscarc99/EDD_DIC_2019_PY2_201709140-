package EDD;

/**
 *
 * @author Oscar C
 */
public class Linked_List {

    private NodeLD first;
    private NodeLD last;
    private int size;

    public Linked_List() {
        first = null;
        last = null;
        size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    public void setFirst(NodeLD first) {
        this.first = first;
    }

    public NodeLD getFirst() {
        return first;
    }

    public NodeLD getLast() {
        return last;
    }

    public int getSize() {
        return size;
    }

    public void setLast(NodeLD last) {
        this.last = last;
    }

    public void setSize(int size) {
        this.size = size;
    }

    void add_first(int dato) {
        NodeLD n = new NodeLD(dato);
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

    void add_last(int dato) {
        if (isEmpty()) {
            add_first(dato);

        } else {
            NodeLD n = new NodeLD(dato);
            last.setNext(n);
            n.setBefore(last);
            last = n;
            size++;

        }
    }

    void add_at(int dato, int index) {
        if (index >= 0 && index <= size) {
            if (index == 0) {
                add_first(dato);
                return;
            }
            if (index == size) {
                add_last(dato);
                return;
            }
            NodeLD aux = first;
            int x = 0;
            while (aux != null) {
                if (x == index) {
                    break;
                }
                aux = aux.getNext();
                x++;
            }
            NodeLD n = new NodeLD(dato);
            aux.getBefore().setNext(n);
            n.setBefore(aux.getBefore());
            n.setNext(aux);
            aux.setBefore(n);
            this.size++;

        }
    }

    int get_element_at(int index) {
        if (index >= 0 && index < size) {
            NodeLD iterador = this.getFirst();
            int x = 0;
            while (iterador != null) {
                if (index == x) {
                    return iterador.getDato();
                }
                iterador = iterador.getNext();
                x++;
            }
        }
        return 0;

    }

    void remove_at(int index) {
        if (index >= 0 && index < size) {
            NodeLD aux = this.getFirst();
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

    void insertOrdenado(int art) {
        if (this.isEmpty()) {
            this.add_first(art);
            return;
        }
        int posicion = 0;
        NodeLD aux = this.getFirst();
        while (aux != null) {
            if (art < this.getFirst().getDato()) {
                posicion = 0;
                break;
            }
            if (art > this.getLast().getDato()) {
                posicion = size;
                break;
            }
            if (art > aux.getDato()) {
                posicion++;
            }
            if (art > aux.getDato()) {
                break;
            }
            aux = aux.getNext();
        }
        this.add_at(art, posicion);

    }
}
