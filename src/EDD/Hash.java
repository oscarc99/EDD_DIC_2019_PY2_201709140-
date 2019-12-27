package EDD;

import Object.User;

/**
 *
 * @author Oscar C
 */

public class Hash {

    User[] tabla;
    int m;
    int cantidad;

    public Hash(int m) {
        this.tabla = new User[m];
        this.m = m;
    }

    private int funcion(int llave, int m) {
        return llave % m;
    }

    public void insertarHash(String Name, String Apellido, int carnet, String pass) {

        User nueva = new User(Name, Apellido, carnet, pass);
        insertarHash(carnet, nueva);

    }

    private void insertarHash(int llave, User nueva) {
        int in = 1;
        int indice = funcion(llave, m);
        int indiceTemp = indice;
        do {
            if (tabla[indice] == null) {
                tabla[indice] = nueva;
                cantidad++;
                break;
            } else {

                indice = (llave % 7 + 1) * in;
                in++;

            }
        } while (indice != indiceTemp);

        if (desbordamiento()) {
            redimensionar();
        }

    }

    private void redimensionar() {
        int temp = m;
        m = siguientePrimo(m);
        User[] temporal = this.tabla;
        User[] nuevaTabla = new User[m];
        tabla = nuevaTabla;
        for (int i = 0; i < temporal.length ; i++){
            if(temporal[i]!= null){
                insertarHash(temporal[i].getCarnet(), temporal[i]);
            }
            
        }
        
        /*for (int i = 0; i < temp; i++) {
            if (tabla[i] != null) {
                nuevaTabla[i] = tabla[i];
            }
        }*/

        
    }

    private boolean desbordamiento() {
        return cantidad / m > 0.55;
    }

    private int siguientePrimo(int inicio) {
        int siguiente = inicio + 1;
        while (!esPrimo(siguiente)) {
            siguiente++;
        }
        return siguiente;
    }

    private boolean esPrimo(int numero) {
        int contador = 2;
        boolean primo = true;
        while ((primo) && (contador != numero)) {
            if (numero % contador == 0) {
                primo = false;
            }
            contador++;
        }
        return primo;
    }

    public void cancelar(int llave) {
        cancelarHash(llave);
    }

    private void cancelarHash(int llave) {
        int indice = funcion(llave, m);
        int indiceTemp = indice;
        do {
            if (tabla[indice] != null) {
                if (tabla[indice].getCarnet() == llave) {
                    tabla[indice].setEstado(1);
                    break;
                }
            } else {
                indice += 2;
                if (m <= indice) {
                    indice = indice - m;
                }
            }
        } while (indice != indiceTemp);

    }

}
