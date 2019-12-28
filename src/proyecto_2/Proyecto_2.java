package proyecto_2;

import EDD.*;
import GUI.Login;
import java.io.*;

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

    public static void main(String[] args) {
        System.out.println("pruebas");
        AVLTree arbol = new AVLTree();
        arbol.insert(1);
        arbol.insert(2);
        arbol.insert(3);
        arbol.insert(4);
        arbol.insert(5);
        arbol.delete(4);
        arbol.report();

        BTree b = new BTree();
        b.Agregar(1, 1);
        b.Agregar(2, 2);
        b.Agregar(3, 3);
        b.Agregar(4, 4);
        b.Agregar(5, 5);
        b.Agregar(6, 6);
        b.Agregar(7, 7);
        b.Agregar(8, 8);

        b.report();
        //readUser("Usuarios.json");
        Login log;
        log = new Login();
        log.setVisible(true);

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
                Apellido= (String) jsonObject.get("Apellido");
                Carnet= (String) jsonObject.get("Carnet");
                car = carnet(Carnet);
                pass = (String) jsonObject.get("Password");
                if (pass.length() >= 8  && !usuarios.exist(car)){
                    //condiciones para guardar
                    System.out.println(Name);
                    System.out.println(Apellido);
                    System.out.println(car);
                    System.out.println(pass);
                    usuarios.insertarHash(Name, Apellido, car, pass);
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
    }

    public static int carnet(String carne) {
        String c = "";
        String[] arrOfStr = carne.split("-");

        for (String a : arrOfStr) {
            c = c + a;
        }
        return Integer.parseInt(c);
    }

}
