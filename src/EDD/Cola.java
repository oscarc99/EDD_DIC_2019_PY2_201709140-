package EDD;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Oscar C
 */
public class Cola {

    private NodeC first;
    private NodeC last;
    private int size;

    public Cola() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    public void setFirst(NodeC first) {
        this.first = first;
    }

    public void setLast(NodeC last) {
        this.last = last;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public NodeC getFirst() {
        return first;
    }

    public NodeC getLast() {
        return last;
    }

    public int getSize() {
        return size;
    }

    boolean empty() {
        return this.first == null;
    }

    public void enqueque(String data) {
        NodeC n = new NodeC(data);

        if (empty()) {
            this.setFirst(n);
            this.setLast(n);
            this.size++;
        } else {
            this.getLast().setNext(n);
            this.setLast(n);
            this.size++;
        }
    }

    public String dequeque() {
        if (empty()) {

            return "";
        } else {

            String aux = this.getFirst().getDato();
            this.setFirst(this.getFirst().getNext());
            this.size--;
            return aux;
        }

    }

    public String peek() {
        if (empty()) {

            return "";
        } else {
            return this.getFirst().getDato();
        }

    }

    public void report(int name) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src\\Imagenes\\Cola" + name + ".dot");

            pw = new PrintWriter(fichero);

            pw.println(Graf());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng src\\Imagenes\\Cola" + name + ".dot -o src\\Imagenes\\Cola" + name + ".png");
            //Process ap = Runtime.getRuntime().exec("cmd /c  src\\Imagenes\\GrafoNodos.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String Graf() {
        String grafica;
        grafica = "digraph G { \n  node [shape = name]; \n  2[label=  <<table border= \"1 \" cellspacing= \" 0 \">";
        NodeC temp = this.getFirst();
        while (temp != null) {

            grafica += "<tr> \n";
            grafica += "<td> " + temp.getDato() + "</td>";
            grafica += " </tr> \n";
            temp = temp.getNext();
        }

        grafica += "</table>>  shape = none ]; \n }";
        return grafica;

    }

}
