package Hilos;

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
public class ManualRecorridoAVL extends Thread {

    int dato[];
    JLabel imagen;
    int velocidad;
    JLabel descripcion;
    JLabel recorido;
    String recorrid;
    int posicion;

    public ManualRecorridoAVL(int[] dato, JLabel imagen, int velocidad, JLabel descripcion, JLabel recorido, int posicion) {
        this.recorrid= "Recorrido: ";
        for (int i = 0; i < posicion; i++) {
            recorrid+=  dato[i]+", ";
        }
        
        
        this.dato = dato;
        this.imagen = imagen;
        this.velocidad = velocidad;
        this.descripcion = descripcion;
        this.recorido = recorido;
        this.recorido = recorido;
        this.posicion = posicion;
    }

    @Override
    public void run() {
        mostrar();

        try {

            recorrid += dato[posicion] + ", ";
            mostrar();
            Proyecto_2.avl.visitar(dato[posicion]);
            descripcion.setText("Vistando " + dato[posicion]);
            mostrar();
            TimeUnit.SECONDS.sleep(velocidad);
            mostrar();
            Proyecto_2.avl.reportVisit();
            descripcion.setText("Agrega " + dato[posicion]);
            mostrar();
            recorido.setText(recorrid);

            mostrar();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(AutoAVL.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Visita terminada");
        mostrar();
    }

    public void mostrar() {

        ImageIcon icono = new ImageIcon("src\\Imagenes\\AVLTree.png");
        icono.getImage().flush();
        imagen.setIcon(icono);
        imagen.revalidate();
        imagen.validate();
        imagen.repaint();

    }
}
