package proyecto_2;

import EDD.*;
import GUI.Login;
import Hilos.AutoAVL;

import Object.User;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

/**
 *
 * @author Oscar C
 */
public class Proyecto_2 {

    /**
     * @param args the command line arguments
     */
    public static Tabla usuarios = new Tabla(37);
    public static ListaUNG err = new ListaUNG();
    //Estructuras para el graficado
    public static AVLTree avl = new AVLTree();

    public static Graph grafo;
    public static String nodos[];
    //Edd para llenar
    public static Linked_List doble = new Linked_List();
    public static Vertices v = new Vertices();

    public static void main(String[] args) {
        carpetas();
        Login l = new Login();
        l.setVisible(true);
        
    }

    public static int[] numeros(String json) {
        int contador = 0;
        int arreglo[] = null;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(json));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonA = (JSONArray) jsonObject.get("Array");

            int num;

            for (Object j : jsonA) {

                contador++;
            }
            arreglo = new int[contador];
            contador = 0;
            for (Object j : jsonA) {
                jsonObject = (JSONObject) j;
                num = toIntExact((long) jsonObject.get("num"));
                arreglo[contador] = num;
                contador++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arreglo;
    }

    public static void listVertices(String ruta) {
        v.clear();
        int c = 0;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(ruta));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonA = (JSONArray) jsonObject.get("Graph");

            String num;
            for (Object j : jsonA) {
                c++;
            }
            String n[] = new String[c];
            c = 0;
            for (Object j : jsonA) {
                jsonObject = (JSONObject) j;
                num = (String) jsonObject.get("Node");
                n[c] = num;
                c++;
            }
            grafo = new Graph(n.length, n);
            String S;
            for (Object j : jsonA) {
                jsonObject = (JSONObject) j;
                num = (String) jsonObject.get("Node");
                //System.out.println("---------------"+ num);

                JSONArray json = (JSONArray) jsonObject.get("Adjacency");
                for (Object nod : json) {
                    jsonObject = (JSONObject) nod;

                    S = (String) jsonObject.get("Node");
                    v.add_last(num, S);
                    System.out.println(num + "," + S);
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        NodeV n = v.getFirst();
        while (n != null) {

            System.out.println(n.getV1() + ", " + n.getV2());

            n = n.getNext();

        }
    }

    public static void listTree(String ruta) {
        doble.clear();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(ruta));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonA = (JSONArray) jsonObject.get("Input");

            int num;

            for (Object j : jsonA) {
                jsonObject = (JSONObject) j;
                num = toIntExact((long) jsonObject.get("num"));
                doble.add_last(num);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readUser(String ruta) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(ruta));
            String Name = "";
            String Apellido = "";
            String Carnet = "";
            int car;
            String pass = "";

            JSONArray jsonA = (JSONArray) obj;
            JSONObject jsonObject;
            for (Object j : jsonA) {
                jsonObject = (JSONObject) j;
                Name = (String) jsonObject.get("Nombre");
                Apellido = (String) jsonObject.get("Apellido");
                Carnet = (String) jsonObject.get("Carnet");
                car = carnet(Carnet);
                pass = (String) jsonObject.get("Password");
                if (!usuarios.exist(car)) {
                    if (pass.length() >= 8) {
                        usuarios.insertarHash(Name, Apellido, car, pass);
                    } else {
                        err.add_last(new User(Name, Apellido, car, pass, "Contraseña menor a 8"));
                    }

                } else {
                    err.add_last(new User(Name, Apellido, car, pass, "Usuario ya existe"));
                }

            }
            
            //System.out.println(n.getDato().getMotivo());
            err.report();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int carnet(String carne) {
        String c = "";
        String[] arrOfStr = carne.split("-");

        for (String a : arrOfStr) {
            c = c + a;
        }
        return Integer.parseInt(c);
    }

    public static int toIntExact(long value) {
        if ((int) value != value) {
            throw new ArithmeticException("integer overflow");
        }
        return (int) value;
    }

    public static void carpetas() {
        String crear = "src\\Imagenes\\burbuja";
        File c = new File(crear);
        c.mkdirs();
        crear ="src\\Imagenes\\insercion";
        File i = new File(crear);
        i.mkdirs();
        crear ="src\\Imagenes\\quicksort";
        File q = new File(crear);
        q.mkdirs();
    }
    
        private static int siguientePrimo(int inicio) {
        int siguiente = inicio + 1;
        while (!esPrimo(siguiente)) {
            siguiente++;
        }
        return siguiente;
    }

    private static boolean esPrimo(int numero) {
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
}
