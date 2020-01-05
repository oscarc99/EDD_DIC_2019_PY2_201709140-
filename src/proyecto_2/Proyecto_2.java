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
    public static Hash usuarios = new Hash(37);
    public static ListaUNG err = new ListaUNG();
    //Estructuras para el graficado
    public static AVLTree avl = new AVLTree();
    public static BTree b = new BTree();
    public static Graph grafo;
    public static String nodos[];
    //Edd para llenar
    public static Linked_List doble = new Linked_List();
    public static Vertices v = new Vertices();

    public static void main(String[] args) {
        
        
        Login l = new Login();
        l.setVisible(true);
        readUser("Usuarios.json");
        
         
    
         
        //Pruebas recorrido profundidad
        /*
        listVertices("EjemploGrafo.json");
        NodeV temp = v.getFirst();
        while(temp != null){
            grafo.add(temp.getV1(), temp.getV2());
           
            
            temp = temp.getNext();
        }
         grafo.GrafoR();
         grafo.report();
         System.out.println("Metodo Arreglo");
         System.out.println(grafo.profundidad("a"));
         System.out.println("Metodo repetido");
         grafo.Stack("a");
         System.out.println("");
         System.out.println("Metodo me");
         grafo.prof("a");
         
       */
        
        /*
         String n[] ={"a","g","f", "b", "e", "c" };
         grafo = new Graph(n.length, n);
         grafo.grafIn("a", "g");
        
        
         */
        //listVertices("EjemploGrafo.json");
        //System.out.println("TRAVERSE");
        //btree.traverse();
        /*
         
        
         try {
         System.out.println("pruebas");
         AVLTree arbol = new AVLTree();
         String n[] = {"A", "B", "C", "D", "E"};
         nodos = n;
         grafo = new Graph(nodos.length, nodos);
         grafo.grafIn("A", "B");
         Thread.sleep(3000);
         grafo.add("A", "B");
         grafo.report();
         grafo.GrafoR();
         Thread.sleep(3000);
         grafo.grafIn("E", "B");
         Thread.sleep(3000);
         grafo.add("E", "B");
         grafo.report();
         grafo.GrafoR();
         Thread.sleep(3000);
         grafo.grafIn("A", "E");
         Thread.sleep(3000);
         grafo.add("A", "E");
         grafo.report();
         grafo.GrafoR();
         Thread.sleep(3000);
         

         } catch (InterruptedException ex) {
         Logger.getLogger(Proyecto_2.class.getName()).log(Level.SEVERE, null, ex);
         }
        
         try{
        
         avl.insert(1);
         avl.insert(2);
         avl.insert(3);
         avl.insert(4);
         avl.insert(5);
         avl.insert(6);
         avl.insert(7);
         avl.insert(8);
         avl.insert(9);
         avl.insert(10);
         avl.report();
        
         System.out.println("PREOORDEN");
         System.out.println(avl.preorden());
         for (int i = 0; i < avl.preordenM().length; i++) {
         System.out.println(avl.preordenM()[i]);
         }
         System.out.println("Inorden");
         System.out.println(avl.inorden());
         for (int i = 0; i < avl.inordenM().length; i++) {
         System.out.println(avl.inordenM()[i]);
         }
        
         System.out.println("POSTORDEN");
         System.out.println(avl.postorden());
         for (int i = 0; i < avl.postordenM().length; i++) {
         System.out.println(avl.postordenM()[i]);
         }
         Thread.sleep(3000);
         avl.visitar(1);
         Thread.sleep(3000);
         avl.reportVisit();
         Thread.sleep(3000);
         avl.visitar(4);
         Thread.sleep(3000);
         avl.reportVisit();
         Thread.sleep(3000);
         avl.visitar(2);
         Thread.sleep(3000);
         avl.reportVisit();
         Thread.sleep(3000);
         avl.visitar(3);
         Thread.sleep(3000);
         avl.reportVisit();
         Thread.sleep(3000);
         avl.noVisit();
         avl.reportVisit();
        
        
         } catch (InterruptedException ex) {
         Logger.getLogger(Proyecto_2.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        
         String n[] = {"a", "f", "g", "b", "e", "c"};
         
         grafo = new Graph(n.length, n);
         grafo.add("a", "g");
         grafo.add("a", "f");
        
         grafo.add("g", "b");
         grafo.add("g", "c");
         grafo.add("g", "f");
         grafo.add("g", "a");
        
         grafo.add("f", "a");
         grafo.add("f", "g");
         grafo.add("f", "b");
         grafo.add("f", "c");
        
         grafo.add("b", "g");
         grafo.add("b", "f");
         grafo.add("b", "c");
         grafo.add("b", "e");
        
         grafo.add("c", "g");
         grafo.add("c", "f");
         grafo.add("c", "b");
         grafo.add("c", "e");
        
         grafo.add("e", "b");
         grafo.add("e", "c");
        
         grafo.report();
         
        
        
         //a.delete(4);
         //a.report();
        
        
        
        
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
       
        
         BTree b = new BTree();
         b.Agregar(1, 1);
         b.Agregar(2, 2);
         b.Agregar(3, 3);
         b.report();
        
         
         */
        //listTree("Arbols.json");
        //listVertices("EjemploGrafo.json");
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
