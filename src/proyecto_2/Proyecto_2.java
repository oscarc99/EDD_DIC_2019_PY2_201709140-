package proyecto_2;

import EDD.*;
import GUI.Login;

import Object.User;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static Hash usuarios = new Hash(37);
    public static ListaUNG err = new ListaUNG();
    //Estructuras para el graficado
    public static AVLTree avl = new AVLTree();
    public static BTree b = new BTree();
    public static Graph grafo;
    public static String nodos[];
    //Edd para llenar
    public static Linked_List doble = new Linked_List();

    public static void main(String[] args) {
        //Login log = new Login();
        //log.setVisible(true);
        try {
            System.out.println("pruebas");
            AVLTree arbol = new AVLTree();
            String n[] = {"A", "B", "C", "D", "E"};
            nodos = n;
            grafo = new Graph(nodos.length, nodos);
            grafo.add("A", "B");
            grafo.report();
            grafo.GrafoR();
            Thread.sleep(3000);
            grafo.add("E", "C");
            grafo.report();
            grafo.GrafoR();
            Thread.sleep(3000);
            grafo.add("A", "E");
            grafo.report();
            grafo.GrafoR();
            Thread.sleep(3000);
            grafo.add("E", "B");
            grafo.report();
            grafo.GrafoR();

        } catch (InterruptedException ex) {
            Logger.getLogger(Proyecto_2.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        
         try {
            
         arbol.buscar(1);
         Thread.sleep(3000);
            
         //EN el primero no debo insertar desbalancado
         arbol.insert(1);
         arbol.report();
            
            
         arbol.buscar(2);
         Thread.sleep(3000);
         arbol.insertDes(2);
         Thread.sleep(3000);
         arbol.insert(2);
         arbol.report();
            
         arbol.buscar(3);
         Thread.sleep(3000);
         arbol.insertDes(3);
         Thread.sleep(3000);
         arbol.insert(3);
         arbol.report();
            
         Thread.sleep(3000);
         } catch (InterruptedException ex) {
         Logger.getLogger(Proyecto_2.class.getName()).log(Level.SEVERE, null, ex);
         }
       
         */
        BTree b = new BTree();
        b.Agregar(1, 1);
        b.Agregar(2, 2);
        b.Agregar(3, 3);
        b.report();

        readUser("Usuarios.json");

        //listTree("Arbols.json");
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
            Node n = err.getFirst();
            System.out.println(n.getDato().getMotivo());
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

}
