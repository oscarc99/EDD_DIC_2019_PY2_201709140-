package EDD;

/**
 *
 * @author Oscar C
 */
public class List {
    private Nodo first;
    private Nodo last;
    private int size;

    public List() {
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

    public void setFirst(Nodo first) {
        this.first = first;
    }

    public Nodo getFirst() {
        return first;
    }

    public Nodo getLast() {
        return last;
    }

    public int getSize() {
        return size;
    }

    public void setLast(Nodo last) {
        this.last = last;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void add_first(String dato) {
        Nodo n = new Nodo(dato);
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

    public void add_last(String dato) {
        if (isEmpty()) {
            add_first(dato);

        } else {
            Nodo n = new Nodo(dato);
            this.last.setNext(n);
            n.setBefore(last);
            this.last = n;
            size++;

        }
    }

    public void add_at(String dato, int index) {
        if (index >= 0 && index <= size) {
            if (index == 0) {
                add_first(dato);
                return;
            }
            if (index == size) {
                add_last(dato);
                return;
            }
            Nodo aux = first;
            int x = 0;
            while (aux != null) {
                if (x == index) {
                    break;
                }
                aux = aux.getNext();
                x++;
            }
            Nodo n = new Nodo(dato);
            aux.getBefore().setNext(n);
            n.setBefore(aux.getBefore());
            n.setNext(aux);
            aux.setBefore(n);
            this.size++;

        }
    }

    public String get_element_at(int index) {
        if (index >= 0 && index < size) {
            Nodo iterador = this.getFirst();
            int x = 0;
            while (iterador != null) {
                if (index == x) {
                    return iterador.getDato();
                }
                iterador = iterador.getNext();
                x++;
            }
        }
        return "";

    }

    public void remove_at(int index) {
        if (index >= 0 && index < size) {
            Nodo aux = this.getFirst();
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
