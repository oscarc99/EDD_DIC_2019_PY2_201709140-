/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Oscar C
 */
public class Graph {

    public int V;
    public List[] adjList;
    public String indice[];

    // constructor  
    public Graph(int V, String a[]) {
        this.V = V;
        this.indice = a;
        // define the size of array as  
        // number of vertices 
        adjList = new List[V];

        // Create a new list for each vertex 
        // such that adjacent nodes can be stored 
        for (int i = 0; i < V; i++) {
            adjList[i] = new List();
        }
    }

    public void clear() {
        V = 0;
        adjList = null;
        indice = null;
    }

    public int getV() {
        return V;
    }

    public List[] getAdjListArray() {
        return adjList;
    }

    public String[] getIndice() {
        return indice;
    }

    public void add(String src, String dest) {
        // Add an edge from src to dest.  
        for (int i = 0; i < indice.length; i++) {
            if (indice[i].equals(src)) {
                adjList[i].add_last(dest);
            }
        }

        for (int i = 0; i < indice.length; i++) {
            if (indice[i].equals(dest)) {
                adjList[i].add_last(src);
            }
        }

    }

    public void report() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("report\\Grafo.dot");

            pw = new PrintWriter(fichero);

            pw.println(Graficar());

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
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng report\\Grafo.dot -o report\\Grafo.png");
            Process ap = Runtime.getRuntime().exec("cmd /c  report\\Grafo.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    public String Graficar() {
        return " digraph G{ \n node [shape=rectangle]; \n" + G() + "\n}";
    }

    private String G() {
        String nod = "";
        String dir = "";
        String rank = "";
        int c = 0;
        int contador = 0;

        Nodo temp;
        for (int i = 0; i < adjList.length; i++) {
            temp = adjList[i].getFirst();
            if (contador == 0) {

            } else {
                if (temp != null) {
                    dir += "node" + i + "-> node" + c + " [penwidth=2, arrowhead=none];\n";
                }
            }
            if (temp != null) {

                nod += "node" + i + " [label=\" " + indice[i] + "\" ];\n";

                dir += "node" + i + "-> node" + i + temp.getDato() + " \n";
                nod += "node" + i + temp.getDato() + " [label=\"" + temp.getDato() + "\" ];\n";
                rank += "{rank= same;  node" + i + "; node" + i + temp.getDato() + ";} \n";;
            }
            rank += "{rank= same;  ";
            if (temp != null) {
                while (temp.getNext() != null) {
                    dir += "node" + i + temp.getDato() + " -> node" + i + temp.getNext().getDato() + "\n";
                    rank += "node" + i + "; node" + i + temp.getDato() + ";";
                    nod += "node" + i + temp.getDato() + " [label=\"" + temp.getDato() + "\" ];\n";

                    temp = temp.getNext();
                    if (temp.getNext() == null) {
                        rank += "node" + i + temp.getDato() + "; ";
                        nod += "node" + i + temp.getDato() + " [label=\"" + temp.getDato() + "\" ];\n";
                    }
                }
                //Tengo que enlazar

                c = i;
                contador++;
            }

            rank += "} \n";
            //Si no es el ultimo (enlazo vertical)

        }

        return nod + dir + rank;
    }

    public String Grafo() {
        return " digraph G{ \n node [shape=circle]; \n" + Graf() + "\n}";
    }

    private String Graf() {
        String g = "";
        int c=100;
        boolean visitados[] = new boolean[adjList.length];
        Nodo temp;
        for (int i = 0; i < adjList.length; i++) {
            temp = adjList[i].getFirst();
            while (temp != null) {
                for (int j = 0; j < indice.length; j++) {
                    c =j;
                    if(indice[j].equals(temp.dato)) {
                                                break;
                    }
                }
                if(!visitados[c]) 
                g += indice[i] + " -> " + temp.getDato()+"[penwidth=2, arrowhead=none] ; \n";
                //grafica no ha sido graficado
                temp = temp.getNext();
            }
            visitados[i] = true;
        }
        return g;
    }

    public void GrafoR() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("report\\GrafoNodos.dot");

            pw = new PrintWriter(fichero);

            pw.println(Grafo());

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
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng report\\GrafoNodos.dot -o report\\GrafoNodos.png");
            Process ap = Runtime.getRuntime().exec("cmd /c  report\\GrafoNodos.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

}
