package Hilos;

import EDD.NodeV;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import proyecto_2.Proyecto_2;

public class ManualGrafo extends Thread{

    NodeV as;

    int velocidad = 1;
    JLabel descip;
    JLabel image;

    public ManualGrafo(NodeV as, int velocidad, JLabel descip, JLabel image) {
        this.as = as;
        this.velocidad = velocidad;
        this.descip = descip;
        this.image = image;
    }

    @Override
    public void run() {
        mostrar();
        if (as != null) {
            mostrar();
            try {
                TimeUnit.SECONDS.sleep(velocidad);
                descip.setText("Crea vertice ("+ as.getV1()+", "+ as.getV2()+") " );
                mostrar();
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
            
        }
        String m = "Se inserto ("+ as.getV1()+", "+ as.getV2()+") ";
        JOptionPane.showMessageDialog(null, m);
        mostrar();
    }

    public void mostrar() {

        ImageIcon icono = new ImageIcon("src\\Imagenes\\Grafo.png");
        icono.getImage().flush();
        image.setIcon(icono);
        image.revalidate();
        image.validate();
        image.repaint();

    }

}
