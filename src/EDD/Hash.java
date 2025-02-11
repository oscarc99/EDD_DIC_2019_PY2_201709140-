package EDD;

import Object.User;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Oscar C
 */
public class Hash {
    //HASH MALA
    public User[] tabla;
    int m; //Tamaño de tabla
    double cantidad;//Usuarios que llevo guardado

    public Hash(int size) {
        this.tabla = new User[size];
        this.m = size;
    }

    public boolean empty() {
        return cantidad == 0;
    }

    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();

        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
    /*
     public boolean existe(int carnet) {
     int indice = funcion(carnet, m);
     int indiceTemp = indice;
     int in = 1;
     do {
     if (tabla[indice] != null) {
     if (tabla[indice].getCarnet() == carnet) {
     return true;
     }

     } else {
     indice = (carnet % 7 + 1) * in;
     if (m <= indice) {
     indice = indice - m;
     }
     in++;
     }
     } while (indice != indiceTemp);
     return false;
     }

     public boolean ingreso(int user, String pass) {
     int indice = funcion(user, m);
     int indiceTemp = indice;
     int in = 1;
     do {
     if (tabla[indice] != null && tabla[indice].getCarnet() == user && tabla[indice].getPassword().equals(convertirSHA256(pass))) {
     return true;

     } else {
     indice = (user % 7 + 1) * in;
     if (m <= indice) {
     indice = indice - m;
     }
     in++;
     }
     } while (indice != indiceTemp);

     return false;
     }

     public User in(int carnet) {
     int indice = funcion(carnet, m);
     int indiceTemp = indice;
     int in = 1;
     do {
     if (tabla[indice] != null) {
     if (tabla[indice].getCarnet() == carnet) {
     return tabla[indice];
     }

     } else {
     indice = (carnet % 7 + 1) * in;
     if (m <= indice) {
     indice = indice - m;
     }
     in++;
     }
     } while (indice != indiceTemp);

     return null;
     }

     public void mod(int carnet, String nam, String ap, String pass) {
     int indice = funcion(carnet, m);
     int indiceTemp = indice;
     int in = 1;
     do {
     if (tabla[indice] != null) {
     if (tabla[indice].getCarnet() == carnet) {
     tabla[indice].setName(nam);
     tabla[indice].setApellido(ap);
     tabla[indice].setPass(pass);
     break;
     }
     } else {
     indice = (carnet % 7 + 1) * in;
     if (m <= indice) {
     indice = indice - m;
     }
     in++;
     }
     } while (indice != indiceTemp);

     }

     public void delete(int carnet) {
     int indice = funcion(carnet, m);
     int indiceTemp = indice;
     int in = 1;
     do {
     if (tabla[indice] != null) {
     if (tabla[indice].getCarnet() == carnet) {
     tabla[indice] = null;
     User[] temporal = this.tabla;
     User[] nuevaTabla = new User[m];
     this.tabla = nuevaTabla;

     for (int i = 0; i < temporal.length; i++) {
     if (temporal[i] != null) {
     insertarHash(temporal[i].getCarnet(), temporal[i]);
     }

     }
     break;
     }
     } else {
     indice = (carnet % 7 + 1) * in;
     if (m <= indice) {
     indice = indice - m;
     }
     in++;
     }
     } while (indice != indiceTemp);

     }
     */

    private int funcion(int llave, int m) {
        return llave % m;
    }

    public void insertarHash(String Name, String Apellido, int carnet, String pass) {

        User nueva = new User(Name, Apellido, carnet, pass);

        if (desbordamiento()) {
            redimensionar();
            insertarHash(carnet, nueva);
        } else {
            insertarHash(carnet, nueva);
        }

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

                indice = (llave % m + 1) * in;
                while (indice >= m) {

                    indice = indice - m;

                }
                in++;

            }
        } while (indice != indiceTemp);

    }

    private void redimensionar() {

        m = siguientePrimo(m);
        User[] temporal = this.tabla;
        User[] nuevaTabla = new User[m];
        this.tabla = nuevaTabla;
        this.cantidad = 0;
        for (int i = 0; i < temporal.length; i++) {
            if (temporal[i] != null) {
                insertarHash(temporal[i].getCarnet(), temporal[i]);
            }

        }
        /*
         for (int i = 0; i < temp; i++) {
         if (tabla[i] != null) {
         nuevaTabla[i] = tabla[i];
         }
         }*/

    }

    private boolean desbordamiento() {

        return (cantidad / m) * 100 > 55;

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

    private String reporte() {
        String r = "";
        for (int i = 0; i < this.tabla.length; i++) {
            if (this.tabla[i] != null) {
                r = r + "<tr> <td> " + (i + 1) + " Nombre: " + this.tabla[i].getName() + " " + this.tabla[i].getApellido() + " Carnet: " + this.tabla[i].getCarnet() + "</td> </tr> \n";
            } else {
                r = r + "<tr> <td> NULL </td></tr>; \n";
            }

        }
        return r;
    }

    public String getName(int carnet) {
        String n = "";
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] != null) {
                if (tabla[i].getCarnet() == carnet) {
                    return tabla[i].getName();
                }
            }
        }
        return n;
    }

    public void report() {
        System.out.println("Tamaño HASH: " + m);
        System.out.println("Guardados HASH: " + cantidad);
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src\\Imagenes\\UserR.dot");

            pw = new PrintWriter(fichero);

            pw.println("digraph G { \n");
            pw.println("node [shape = none];\n");

            pw.println("2[label=  <<table border= \"1 \" cellspacing= \" 0 \">;\n");
            pw.println(reporte());
            pw.println("</table>>];\n");
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
        try {
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng src\\Imagenes\\UserR.dot -o src\\Imagenes\\UserR.png");
            //Process p2 = Runtime.getRuntime().exec("cmd /c src\\Imagenes\\UserR.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    public void eliminar(int carnet) {
        for (int i = 0; i < this.tabla.length; i++) {
            if (tabla[i] != null) {
                if (this.tabla[i].getCarnet() == carnet) {
                    tabla[i] = null;

                    break;
                }
            }

        }

        User[] temporal = this.tabla;
        
        this.tabla = new User[m];
        this.cantidad = 0;
        for (int i = 0; i < temporal.length; i++) {
            if (temporal[i] != null) {
                insertarHash(temporal[i].getCarnet(), temporal[i]);
            }

        }

    }

    public boolean exist(int carnet) {
        for (int i = 0; i < this.tabla.length; i++) {
            if (this.tabla[i] != null) {
                if (this.tabla[i].getCarnet() == carnet) {
                    return true;
                }
            }

        }
        return false;
    }

    public User log(int user) {
        for (int i = 0; i < this.tabla.length; i++) {
            if (tabla[i] != null) {
                if (this.tabla[i].getCarnet() == user) {
                    return this.tabla[i];
                }
            }

        }
        return null;
    }

    public boolean in(int user, String pass) {
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] != null) {
                if (tabla[i].getCarnet() == user && tabla[i].getPassword().equals(convertirSHA256(pass))) {
                    return true;
                }
            }

        }
        return false;
    }

    public void modificar(int carnet, String nam, String ap, String pass) {
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] != null) {
                if (tabla[i].getCarnet() == carnet) {
                    tabla[i].setName(nam);
                    tabla[i].setApellido(ap);
                    tabla[i].setPass(pass);
                }
            }

        }

    }
}
