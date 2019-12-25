/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_2;
import EDD.*;

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
        //System.out.println(arbol.root.getData());
        //System.out.println(arbol.root.getRight().getData());
        arbol.report();
    }
    
}
