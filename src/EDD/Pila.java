package EDD;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Oscar C
 */
public class Pila {

    NodoP cima;
    int size;

    public Pila() {
        this.cima = null;
        this.size = 0;
    }

    boolean empty() {
        return cima == null;
    }

    public int getSize() {
        return size;
    }

    public String peek(){
        if(empty())
    {

        return "";
    }
    else
    {
        return this.cima.getDato();
    }
    }
    
    
    public void push(String dato) {
        NodoP n = new NodoP(dato);
        if (empty()) {
            this.cima = n;
            this.size++;
        } else {
            n.setNext(this.cima);
            this.cima = n;
            this.size++;
        }   
    }

    public String pop() {
        if (empty()) {

            return "";
        } else {

            String aux = this.cima.getDato();
            this.cima = this.cima.getNext();
            this.size--;
            return aux;
        }
    }

    public void report(String name,int num) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src\\Imagenes\\" + name + num+".dot");

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
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng src\\Imagenes\\"+ name + num+ ".dot -o src\\Imagenes\\" + name + num+ ".png");
            //Process ap = Runtime.getRuntime().exec("cmd /c  src\\Imagenes\\GrafoNodos.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String Graf() {
        String grafica;
        grafica = "digraph G { \n  node [shape = name]; \n  2[label=  <<table border= \"1 \" cellspacing= \" 0 \">";
        NodoP temp = this.cima;
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
