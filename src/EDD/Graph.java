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
    public boolean[] visita;
    boolean vacia;
    boolean reporte[];
    int cola = 0;
    int pila = 0;

    public int getCola() {
        return cola;
    }

    // constructor  
    public Graph(int V, String a[]) {
        this.visita = new boolean[V];
        this.reporte = new boolean[V];
        this.V = V;
        this.indice = a;
        vacia = true;

        adjList = new List[V];

        for (int i = 0; i < V; i++) {
            adjList[i] = new List();
        }
    }

    public void NonVisit() {
        this.visita = new boolean[V];
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
        vacia = false;
        boolean bandera = false;
        Nodo temp;
        // Add an edge from src to dest.  
        for (int i = 0; i < indice.length; i++) {
            if (indice[i].equals(src)) {
                temp = adjList[i].getFirst();
                while (temp != null) {
                    if (temp.getDato().equals(dest)) {
                        bandera = true;
                    }
                    temp = temp.getNext();
                }
                if (!bandera) {
                    adjList[i].add_last(dest);
                }

            }
        }
        bandera = false;
        for (int i = 0; i < indice.length; i++) {
            if (indice[i].equals(dest)) {
                temp = adjList[i].getFirst();
                while (temp != null) {
                    if (temp.getDato().equals(src)) {
                        bandera = true;
                    }
                    temp = temp.getNext();
                }
                if (!bandera) {
                    adjList[i].add_last(src);
                }

            }
        }

    }

    //Report adyacencia
    public void report() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src\\Imagenes\\Grafo.dot");

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
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng src\\Imagenes\\Grafo.dot -o src\\Imagenes\\Grafo.png");
            //Process ap = Runtime.getRuntime().exec("cmd /c  src\\Imagenes\\Grafo.png");
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
                while (temp != null) {
                    if (temp.getNext() != null) {
                        dir += "node" + i + temp.getDato() + " -> node" + i + temp.getNext().getDato() + "\n";
                        rank += "node" + i + "; node" + i + temp.getDato() + ";";
                        nod += "node" + i + temp.getDato() + " [label=\"" + temp.getDato() + "\" ];\n";
                    }
                    if (temp.getNext() == null) {
                        rank += "node" + i + temp.getDato() + "; ";
                        nod += "node" + i + temp.getDato() + " [label=\"" + temp.getDato() + "\" ];\n";
                    }
                    temp = temp.getNext();

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

    //Report del grafo
    public void GrafoR() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src\\Imagenes\\GrafoNodos.dot");

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
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng src\\Imagenes\\GrafoNodos.dot -o src\\Imagenes\\GrafoNodos.png");
            //Process ap = Runtime.getRuntime().exec("cmd /c  src\\Imagenes\\GrafoNodos.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    private String Grafo() {

        return " digraph G{ \n node [shape=circle]; \n" + Graf() + "\n}";

    }

    private String Graf() {
        String g = "";
        int c = 100;
        boolean visitados[] = new boolean[adjList.length];
        Nodo temp;
        for (int i = 0; i < adjList.length; i++) {
            temp = adjList[i].getFirst();
            while (temp != null) {
                for (int j = 0; j < indice.length; j++) {
                    c = j;
                    if (indice[j].equals(temp.dato)) {
                        break;
                    }
                }
                if (!visitados[c]) {
                    g += indice[i] + " -> " + temp.getDato() + "[penwidth=2, arrowhead=none] ; \n";
                }
                //grafica no ha sido graficado
                temp = temp.getNext();
            }
            visitados[i] = true;
        }
        return g;
    }

    //Grafica nodo a insertar en adyacencia
    public void grafIn(String a, String b) {

        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src\\Imagenes\\Grafo.dot");

            pw = new PrintWriter(fichero);

            pw.println(Graficar(a, b));

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
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng src\\Imagenes\\Grafo.dot -o src\\Imagenes\\Grafo.png");
            //Process ap = Runtime.getRuntime().exec("cmd /c  src\\Imagenes\\Grafo.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String Graficar(String a, String b) {

        return " digraph G{ \n node [shape=rectangle]; \n" + G(a, b) + "\n}";
    }

    private String G(String a, String b) {
        String nod = "";
        boolean visit[] = new boolean[adjList.length];
        String dir = "";
        String rank = "";
        int c = 0;
        int contador = 0;
        Nodo temp;

        if (vacia) {
            System.out.println("GRAFO VACIO");
            return a + "->" + b + " [penwidth=2, arrowhead=none]";
        }

        for (int i = 0; i < adjList.length; i++) {
            temp = adjList[i].getFirst();
            if (contador == 0) {

            } else {
                if (temp != null) {
                    dir += "node" + i + "-> node" + c + " [penwidth=2, arrowhead=none];\n";
                }
            }
            if (temp != null) {
                visit[i] = true;
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
                visit[i] = true;

            }

            rank += "} \n";
            //Si no es el ultimo (enlazo vertical)
        }

        if (!visit[posicion(a)] && !visit[posicion(b)]) {
            dir += "node" + c + "-> " + a + " [penwidth=2, arrowhead=none];\n";
            dir += a + "-> " + b + " [penwidth=2, arrowhead=none];\n";

        } else if (!visit[posicion(b)]) {
            dir += "node" + c + "-> " + b + " [penwidth=2, arrowhead=none];\n";
        } else if (!visit[posicion(a)]) {
            dir += "node" + c + "-> " + a + " [penwidth=2, arrowhead=none];\n";
        }

        for (int i = 0; i < indice.length; i++) {
            if (indice[i] == a) {
                if (!visit[i]) {
                    dir += "node" + c + "-> " + a + " [penwidth=2, arrowhead=none];\n";
                    for (int j = 0; j < indice.length; j++) {
                        if (indice[j] == b) {
                            if (!visit[j]) {
                                dir += a + "-> " + b + " [penwidth=2, arrowhead=none];\n";
                            }
                        }
                    }

                }
            } else if (indice[i] == b) {
                if (!visit[i]) {
                    dir += "node" + c + "-> " + b + " [penwidth=2, arrowhead=none];\n";
                }
            }
        }

        return nod + dir + rank;
    }

    //Recorrido por anchura
    private String anchura(String inicio) {
        String a = "";
        boolean visited[] = new boolean[indice.length];
        Cola queue = new Cola();
        if (posicion(inicio) <= indice.length) {
            visited[posicion(inicio)] = true;
        }

        queue.enqueque(inicio);

        //Mientras no este vacia la cola
        while (!queue.empty()) {
            queue.report(cola);

            //Saco valor y lo obtengo para saber cuales adyacencia debo de meter
            inicio = queue.dequeque();
            a += inicio + "-";

            //Busco adyacentes 
            Nodo temp = adjList[posicion(inicio)].getFirst();
            while (temp != null) {

                if (!visited[posicion(temp.getDato())]) {

                    visited[posicion(temp.getDato())] = true;
                    queue.enqueque(indice[posicion(temp.getDato())]);
                }
                temp = temp.getNext();
            }
            cola++;
        }
        return a;
    }

    private int posicion(String valor) {
        for (int i = 0; i < indice.length; i++) {
            if (indice[i].equals(valor)) {
                return i;
            }
        }
        return 99;
    }

    public String[] BFS(String a) {
        String bfs = anchura(a);
        String[] parts = bfs.split("-");
        cola = 0;
        return parts;

    }

    // Recorrido por profundidad
    private String DFS(String v, boolean visited[]) {

        visited[posicion(v)] = true;

        //System.out.println("Visito: "+v);
        String as = v + "-";
        // Recur for all the vertices adjacent to this vertex 
        Nodo temp = adjList[posicion(v)].getFirst();
        while (temp != null) {

            if (!visited[posicion(temp.getDato())]) {

                as += DFS(temp.getDato(), visited);

            }
            temp = temp.getNext();
        }
        return as;
    }

    public String profundidad(String v) {
        boolean visited[] = new boolean[V];
        return DFS(v, visited);
    }

    public String[] DFS(String a) {
        String bfs = profundidad(a);
        Stack(a);
        String[] parts = bfs.split("-");
        pila = 0;
        return parts;

    }

    //pinta el que va a visitar
    public void GrafoRPintado(String a) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src\\Imagenes\\GrafoNodos.dot");

            pw = new PrintWriter(fichero);

            pw.println(GrafoP(a));

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
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng src\\Imagenes\\GrafoNodos.dot -o src\\Imagenes\\GrafoNodos.png");
            //Process ap = Runtime.getRuntime().exec("cmd /c  src\\Imagenes\\GrafoNodos.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    private String GrafoP(String a) {

        return " digraph G{ \n node [shape=circle]; \n" + GrafP(a) + "\n}";

    }

    private String GrafP(String a) {
        String g = "";
        int c = 100;
        visita[posicion(a)] = true;
        for (int i = 0; i < visita.length; i++) {
            if (visita[i]) {
                //Ya han sido visitados agregar nodo pintado
                g += indice[i] + "[label =\"" + indice[i] + "\" style = filled  fillcolor =\"darkturquoise\"] \n";
            }
        }
        g += indice[posicion(a)] + "[label =\"" + indice[posicion(a)] + "\" style = filled  fillcolor =\"darkturquoise\"] \n";
        boolean visitados[] = new boolean[adjList.length];

        Nodo temp;
        for (int i = 0; i < adjList.length; i++) {
            temp = adjList[i].getFirst();
            while (temp != null) {
                for (int j = 0; j < indice.length; j++) {
                    c = j;
                    if (indice[j].equals(temp.dato)) {
                        break;
                    }
                }
                if (!visitados[c]) {
                    g += indice[i] + " -> " + temp.getDato() + "[penwidth=2, arrowhead=none] ; \n";
                }
                //grafica no ha sido graficado
                temp = temp.getNext();
            }
            visitados[i] = true;
        }
        return g;
    }

    //pinta los ya vistados
    public void GrafoRPintado() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src\\Imagenes\\GrafoNodos.dot");

            pw = new PrintWriter(fichero);

            pw.println(GrafoP());

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
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng src\\Imagenes\\GrafoNodos.dot -o src\\Imagenes\\GrafoNodos.png");
            //Process ap = Runtime.getRuntime().exec("cmd /c  src\\Imagenes\\GrafoNodos.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    private String GrafoP() {

        return " digraph G{ \n node [shape=circle]; \n" + GrafP() + "\n}";

    }

    private String GrafP() {
        String g = "";
        int c = 100;
        boolean visitados[] = new boolean[adjList.length];
        for (int i = 0; i < visita.length; i++) {
            if (visita[i]) {
                //Ya han sido visitados agregar nodo pintado
                g += indice[i] + "[label =\"" + indice[i] + "\" style = filled  fillcolor =\"darkturquoise\"] \n";
            }
        }

        Nodo temp;
        for (int i = 0; i < adjList.length; i++) {
            temp = adjList[i].getFirst();
            while (temp != null) {
                for (int j = 0; j < indice.length; j++) {
                    c = j;
                    if (indice[j].equals(temp.dato)) {
                        break;
                    }
                }
                if (!visitados[c]) {
                    g += indice[i] + " -> " + temp.getDato() + "[penwidth=2, arrowhead=none] ; \n";
                }
                //grafica no ha sido graficado
                temp = temp.getNext();
            }
            visitados[i] = true;
        }
        return g;
    }

    public void Stack(String s) {
        // Initially mark all vertices as not visited 
        Boolean[] visited = new Boolean[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        // Create a stack for DFS 
        Pila sta = new Pila();
        pila = 1;
        // Push the current source node 
        sta.push(s);
        //sta.report(s,0);
        while (sta.empty() == false) {

            // Pop a vertex from stack and print it 
            s = sta.peek();
            sta.pop();

            // Stack may contain same vertex twice. So 
            // we need to print the popped item only 
            // if it is not visited. 
            if (visited[posicion(s)] == false) {
                System.out.print(s + "-");

                pila++;

                visited[posicion(s)] = true;
            }

            Nodo itr = adjList[posicion(s)].getFirst();

            while (itr != null) {

                if (!visited[posicion(itr.getDato())]) {
                    sta.push(itr.getDato());
                }
                itr = itr.getNext();
            }

        }
    }

    public void prof(String vertice) {
        pila = 1;
        Boolean[] visited = new Boolean[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        Pila pilaEDD = new Pila();
        pilaEDD.push(vertice);
        pilaEDD.report(vertice,0); 
        while (pilaEDD.empty() == false) {
            
            vertice = pilaEDD.peek();
            
            //Vertice no ha sido visitado
            if (!visited[posicion(vertice)]) {
                pilaEDD.report(vertice,pila); 
                visited[posicion(vertice)] = true;
                Nodo itr = adjList[posicion(vertice)].getFirst();
                while (itr != null) {
                    if (!visited[posicion(itr.getDato())]) {
                        pila =0;
                        pilaEDD.push(itr.getDato());
                        break;
                    }

                    itr = itr.getNext();
                }
                pila++;
            } else {
                pilaEDD.report(vertice,pila); 
                boolean addStack = false;
                Nodo itr = adjList[posicion(vertice)].getFirst();
                while (itr != null) {
                    if (!visited[posicion(itr.getDato())]) {
                        pilaEDD.push(itr.getDato());
                        addStack = true;
                        break;
                    }

                    itr = itr.getNext();
                }
                if (!addStack) {
                    pilaEDD.pop();
                    addStack = false;
                }
                pila++;
                
            }
            
        }

    }

}
