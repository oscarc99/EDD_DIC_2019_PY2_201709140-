/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hilos;

import EDD.NodeLD;
import java.awt.Image;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import proyecto_2.Proyecto_2;

/**
 *
 * @author Oscar C
 */
public class AutoAVL extends Thread {

    NodeLD as;
    JLabel jLabel;
    int velocidad = 1;
    JLabel descip;
    JLabel image;

    public AutoAVL(JLabel jLanel, NodeLD as, int v, JLabel d, JLabel ima) {
        this.jLabel = jLanel;
        this.as = as;
        this.velocidad = v;
        this.descip = d;
        this.image = ima;
        mostrar();
    }

    @Override
    public void run() {
        mostrar();
        while (as != null) {
            mostrar();
            try {
                
                mostrar();
                Proyecto_2.avl.buscar(as.getDato());
                jLabel.setText("Insertando:  " + as.getDato());
                mostrar();
                
                TimeUnit.SECONDS.sleep(velocidad);
                descip.setText("Busqueda posicion para insertar"); 
                Proyecto_2.avl.insertDes(as.getDato());
                
                mostrar();
                TimeUnit.SECONDS.sleep(velocidad);
                descip.setText("Inserta y calcula Factor de Equilibrio"); 
                Proyecto_2.avl.insert(as.getDato());
                Proyecto_2.avl.report();
                descip.setText("Rotando"); 
                mostrar();

                as = as.getNext();

            } catch (InterruptedException ex) {
                Logger.getLogger(AutoAVL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        mostrar();
        JOptionPane.showMessageDialog(null, "Arbol terminado");
        mostrar();
        this.interrupt();
    }

    public void mostrar() {

        ImageIcon icono = new ImageIcon("src\\Imagenes\\AVLTree.png");
        icono.getImage().flush();
        image.setIcon(icono);
        image.revalidate();
        image.validate();
        image.repaint();

    }

}
