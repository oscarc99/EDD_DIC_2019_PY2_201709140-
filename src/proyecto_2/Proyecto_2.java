package proyecto_2;

import EDD.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Oscar C
 */
public class Proyecto_2 {

    /**
     * @param args the command line arguments
     */
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
        String s = convertirSHA256("123456");
        System.out.println(s);
        
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
        
        
        
        
        
        
    }

    public static String convertirSHA256(String password) {
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
}

