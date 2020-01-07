package EDD;

import Object.User;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

/**
 *
 * @author Oscar C
 */
public class Tabla {

    private int size = 37;

    private double ocupados;

    public User[] tabla;

    public Tabla(int size) {

        this.size = size;
        tabla = new User[size];

    }

    
    
    public int getSize() {
        return size;
    }

    public double getOcupados() {
        return ocupados;
    }

    public double calcularPorcentaje() {
        
        System.out.println((ocupados / size));
        return ocupados / size;
    }

    private int funcion1(int clave) {
        return clave % size;
    }

    private int funcion2(int clave) {
        return (clave % 7 + 1);
    }

    private int doubleHash(int clave, int factor) {
        return factor * funcion2(clave);
    }

    public void insertarHash(String Name, String Apellido, int carnet, String pass) {

        User nueva = new User(Name, Apellido, carnet, pass);
        if (desbordamiento()) {
            System.out.println("DESBORDO REHASH" + this.calcularPorcentaje());
            reHash();
            insertarHash(carnet, nueva);
        } else {
            System.out.println("------ Factor" + this.calcularPorcentaje());
            insertarHash(carnet, nueva);
        }

    }

    public void insertarHash(int carnet, User nueva) {
        System.out.println("Ingresando " + carnet);
        int itr = 1;
        int indice = funcion1(carnet);
        System.out.println("Original Indice");
        int indiceTemp = indice;

        do {
            if (tabla[indice] == null) {
                tabla[indice] = nueva;
                System.out.println("Inserto " + carnet);
                ocupados++;
                break;
            } else {

                indice = doubleHash(carnet, itr);
                System.out.println("Nuevo indice " + indice);
                while (indice >= size) {

                    indice = indice - size;
                    System.out.println("Se paso ahora es " + indice);
                }
                itr++;

            }

        } while (indice != indiceTemp);

    }

    private boolean desbordamiento() {

        return calcularPorcentaje() > 0.550;
    }

    private void reHash() {
        System.out.println("REHASH");
        User[] temporal = this.tabla;

        for (int i = 0; i < tabla.length; i++) {

            temporal[i] = tabla[i];
        }
        size = siguientePrimo(size);
        User[] nuevaTabla = new User[size];
        this.ocupados = 0;
        this.tabla = nuevaTabla;

        for (int i = 0; i < temporal.length; i++) {
            if (temporal[i] != null) {
                insertarHash(temporal[i].getCarnet(), temporal[i]);
            }
        }
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

    public boolean empty() {
        return ocupados == 0;
    }

    public void eliminar(int carnet) {
        //Elimino 
        int itr = 1;
        int indice = funcion1(carnet);
        int indiceTemp = indice;
        boolean flag = false;
        do {
            if (tabla[indice] != null) {
                if (tabla[indice].getCarnet() == carnet) {
                    tabla[indice] = null;
                    ocupados--;
                    flag = true;
                    break;
                } else {

                    indice = doubleHash(carnet, itr);
                    while (indice >= size) {
                        indice = indice - size;
                    }
                    itr++;

                }
            } else {

                indice = doubleHash(carnet, itr);
                while (indice >= size) {
                    indice = indice - size;
                }
                itr++;

            }
        } while (indice != indiceTemp);
        //Reinserto por los indices
        //Si elimino reinserto
        if (flag) {
            this.ocupados = 0;
            User[] temporal = this.tabla;

            for (int i = 0; i < tabla.length; i++) {

                temporal[i] = tabla[i];
            }

            this.ocupados = 0;

            this.tabla = new User[size];;

            for (int i = 0; i < temporal.length; i++) {
                if (temporal[i] != null) {
                    insertarHash(temporal[i].getCarnet(), temporal[i]);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error al encontrar usuario");
        }
    }

    public boolean exist(int carnet) {
        int itr = 1;

        int indice = funcion1(carnet);
        int indiceTemp = indice;

        do {

            if (tabla[indice] != null) {
                if (tabla[indice].getCarnet() == carnet) {
                    return true;
                } else {
                    indice = doubleHash(carnet, itr);
                    while (indice >= size) {
                        indice = indice - size;
                    }
                    itr++;
                }
            } else {

                indice = doubleHash(carnet, itr);
                while (indice >= size) {
                    indice = indice - size;
                }
                itr++;

            }

        } while (indice != indiceTemp);

        return false;
    }

    public User log(int carnet) {
        int itr = 1;
        int indice = funcion1(carnet);
        int indiceTemp = indice;

        do {
            if (tabla[indice] != null) {
                if (tabla[indice].getCarnet() == carnet) {
                    return tabla[indice];
                } else {
                    indice = doubleHash(carnet, itr);
                    while (indice >= size) {
                        indice = indice - size;
                    }
                    itr++;
                }

            } else {

                indice = doubleHash(carnet, itr);
                while (indice >= size) {
                    indice = indice - size;
                }
                itr++;

            }
        } while (indice != indiceTemp);

        return null;
    }

    public void modificar(int carnet, String nam, String ap, String pass) {
        int indice = funcion1(carnet);
        int itr = 1;
        int indiceTemp = indice;

        do {
            if (tabla[indice] != null) {
                if (tabla[indice].getCarnet() == carnet) {
                    tabla[indice].setName(nam);
                    tabla[indice].setApellido(ap);
                    tabla[indice].setPass(pass);
                    break;
                } else {
                    indice = doubleHash(carnet, itr);
                    while (indice >= size) {
                        indice = indice - size;
                    }
                    itr++;
                }
            } else {
                indice = doubleHash(carnet, itr);
                while (indice >= size) {
                    indice = indice - size;
                }
                itr++;
            }
        } while (indice != indiceTemp);

    }

    public boolean in(int user, String pass) {
        int itr = 1;
        int indice = funcion1(user);
        int indiceTemp = indice;

        do {
            if (tabla[indice] != null) {
                if (tabla[indice].getCarnet() == user && tabla[indice].getPassword().equals(convertirSHA256(pass))) {
                    return true;
                } else {
                    indice = doubleHash(user, itr);
                    while (indice >= size) {
                        indice = indice - size;
                    }
                    itr++;
                }

            } else {

                indice = doubleHash(user, itr);
                while (indice >= size) {
                    indice = indice - size;
                }
                itr++;

            }
        } while (indice != indiceTemp);

        return false;
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

    public void report() {
        System.out.println("Tamaño HASH: " + size);
        System.out.println("Guardados HASH: " + ocupados);
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
            Process p2 = Runtime.getRuntime().exec("cmd /c src\\Imagenes\\UserR.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    private String reporte() {
        String r = "";
        for (int i = 0; i < this.tabla.length; i++) {
            if (this.tabla[i] != null) {
                r = r + "<tr> <td> " + i + ".  Nombre:  " + this.tabla[i].getName() + " " + this.tabla[i].getApellido() + " Carnet: " + this.tabla[i].getCarnet() + "</td> </tr> \n";
            } else {
                r = r + "<tr> <td> NULL </td></tr>; \n";
            }

        }
        return r;
    }

}
