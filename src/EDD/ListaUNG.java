/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

import Object.User;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Oscar C
 */
public class ListaUNG {
    private Node first;
    private Node last;
    private int size;

    public ListaUNG() {
        first = null;
        last = null;
        size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }

    public int getSize() {
        return size;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void add_first(User dato) {
        Node n = new Node(dato);
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

    public void add_last(User dato) {
        if (isEmpty()) {
            add_first(dato);

        } else {
            Node n = new Node(dato);
            last.setNext(n);
            n.setBefore(last);
            last = n;
            size++;

        }
    }

    public void add_at(User dato, int index) {
        if (index >= 0 && index <= size) {
            if (index == 0) {
                add_first(dato);
                return;
            }
            if (index == size) {
                add_last(dato);
                return;
            }
            Node aux = first;
            int x = 0;
            while (aux != null) {
                if (x == index) {
                    break;
                }
                aux = aux.getNext();
                x++;
            }
            Node n = new Node(dato);
            aux.getBefore().setNext(n);
            n.setBefore(aux.getBefore());
            n.setNext(aux);
            aux.setBefore(n);
            this.size++;

        }
    }

    public User get_element_at(int index) {
        if (index >= 0 && index < size) {
            Node iterador = this.getFirst();
            int x = 0;
            while (iterador != null) {
                if (index == x) {
                    return iterador.getDato();
                }
                iterador = iterador.getNext();
                x++;
            }
        }
        return null;

    }

    public void remove_at(int index) {
        if (index >= 0 && index < size) {
            Node aux = this.getFirst();
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
    private String graficar(){
        int c=0;
        String g="";
        String dir ="";
        Node temp = this.getFirst();
        
        while(temp!=null){
            g = g +"node"+c+"[label = \" "+temp.getDato().getCarnet()+" , " +temp.getDato().getMotivo()+" \"] "+  " \n";
            if(temp.getNext() != null ){
                dir  = dir  + " node"+c+ " -> ";
            }else{
                dir = dir  + " node"+c+ " [dir=both]; \n";
            }
            temp = temp.getNext();
            
            c++;
        }
        g = g + dir ;
        return g;
    }
    public void report(){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("report\\No_Guardados.dot");

            pw = new PrintWriter(fichero);

            pw.println("digraph G { \n");
            pw.println("rankdir = LR;\n");
            pw.println("node [shape=rectangle, height=0.5, width=0.5];\n");
            pw.println("graph[ nodesep = 0.5]; \n");
            pw.println(graficar());
            pw.println("}\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
           // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try{
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng report\\No_Guardados.dot -o report\\No_Guardados.png");
            Process sp = Runtime.getRuntime().exec("cmd /c d report\\No_Guardados.png");
        }catch(Exception e2){
                            e2.printStackTrace();
        }
    }
 
}
