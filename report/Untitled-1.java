    public void prof(String vertice) {
        pila = 1;
        Boolean[] visited = new Boolean[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        Pila pilaEDD = new Pila();
        pilaEDD.push(vertice);
        pilaEDD.report(vertice, 0);
        while (pilaEDD.empty() == false) {

            vertice = pilaEDD.peek();

            //Vertice no ha sido visitado
            if (!visited[posicion(vertice)]) {
                pilaEDD.report(vertice, pila);
                visited[posicion(vertice)] = true;
                Nodo itr = adjList[posicion(vertice)].getFirst();
                while (itr != null) {
                    if (!visited[posicion(itr.getDato())]) {
                        pila = 0;
                        pilaEDD.push(itr.getDato());
                        break;
                    }

                    itr = itr.getNext();
                }
                pila++;
            } else {
                pilaEDD.report(vertice, pila);
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