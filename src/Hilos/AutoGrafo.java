package Hilos;

import EDD.NodeV;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import proyecto_2.Proyecto_2;

/**
 *
 * @author Oscar C
 */
public class AutoGrafo extends Thread {

    NodeV as;

    int velocidad = 1;
    JLabel descip;
    JLabel image;
    JScrollPane jScrollPane;

    public AutoGrafo(NodeV as, int velocidad, JLabel descip, JLabel image, JScrollPane scroll) {
        this.as = as;
        this.velocidad = velocidad;
        this.descip = descip;
        this.image = image;
        this.jScrollPane = scroll;
    }

    @Override
    public void run() {
        mostrar();
        while (as != null) {
            mostrar();
            try {
                descip.setText("Crea vertice(" + as.getV1() + ", " + as.getV2() + ") ");
                Proyecto_2.grafo.grafIn(as.getV1(), as.getV2());
                mostrar();
                TimeUnit.SECONDS.sleep(velocidad);
                mostrar();
                Proyecto_2.grafo.add(as.getV1(), as.getV2());

                mostrar();
                Proyecto_2.grafo.report();
                descip.setText("Crea adyacencia");
                mostrar();

            } catch (InterruptedException ex) {
                Logger.getLogger(AutoGrafo.class.getName()).log(Level.SEVERE, null, ex);
            }
            as = as.getNext();
        }
        JOptionPane.showMessageDialog(null, "Grafo terminado");
    }

    public void mostrar() {

        ImageIcon icono = new ImageIcon("src\\Imagenes\\Grafo.png");
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
