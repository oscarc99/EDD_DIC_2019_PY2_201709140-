package Hilos;

import EDD.NodeLD;
import GUI.AVLTree;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import proyecto_2.Proyecto_2;

/**
 *
 * @author Oscar C
 */
public class DeleteAVL extends Thread {

    int delete;
    JLabel jLabel;
    int velocidad = 1;
    JLabel descip;
    JLabel image;
    JScrollPane jScrollPane;

    public DeleteAVL(JLabel jLanel, int eli, int v, JLabel d, JLabel ima, JScrollPane scroll) {
        this.jLabel = jLanel;
        this.delete = eli;
        this.velocidad = v;
        this.descip = d;
        this.image = ima;
        this.jScrollPane = scroll;
        mostrar();
    }

    @Override
    public void run() {
        try {

            mostrar();
            Proyecto_2.avl.pintar(delete);

            mostrar();
            TimeUnit.SECONDS.sleep(velocidad);
            jLabel.setText("Eliminando: " + delete);
            mostrar();
            descip.setText("Nodo encontrado ");
            mostrar();
            Proyecto_2.avl.delete(delete);
            mostrar();
            Proyecto_2.avl.report();

            mostrar();
            descip.setText("Se elimino " + delete);
            mostrar();
        } catch (InterruptedException ex) {
            Logger.getLogger(AVLTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        mostrar();
        ImageIcon icono = new ImageIcon("src\\Imagenes\\AVLTree.png");
        icono.getImage().flush();
        image.setIcon(icono);
        image.revalidate();
        image.validate();
        image.repaint();
    }

    public void mostrar() {

        ImageIcon icono = new ImageIcon("src\\Imagenes\\AVLTree.png");
        icono.getImage().flush();
        image.setIcon(icono);
        image.revalidate();
        image.validate();
        image.repaint();
        jScrollPane.revalidate();
        jScrollPane.validate();
        jScrollPane.repaint();

    }

}
