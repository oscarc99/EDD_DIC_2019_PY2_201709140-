package Hilos;

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
public class AutoRecorridoAVL extends Thread {

    int dato[];
    JLabel imagen;
    int velocidad;
    JLabel descripcion;
    JLabel recorido;
    String recorrid;
    JScrollPane jScrollPane;
    
    public AutoRecorridoAVL(int[] dato, JLabel imagen, int velocidad, JLabel descripcion, JLabel recorido, JScrollPane scroll) {
        this.recorrid= "";
        this.dato = dato;
        this.imagen = imagen;
        this.velocidad = velocidad;
        this.descripcion = descripcion;
        this.recorido = recorido;
        this.jScrollPane = scroll;
    }

    @Override
    public void run() {
        mostrar();
        
        try {
            for (int i = 0; i < dato.length; i++) {
                recorrid+=dato[i]+", ";
                mostrar();
                Proyecto_2.avl.visitar(dato[i]);
                descripcion.setText("Vistando " +dato[i]); 
                mostrar();
                TimeUnit.SECONDS.sleep(velocidad);
                mostrar();
                Proyecto_2.avl.reportVisit();
                descripcion.setText("Agrega "+ dato[i]); 
                mostrar();
                recorido.setText(recorrid); 

            }
            Proyecto_2.avl.noVisit();
            mostrar();
        } catch (InterruptedException ex) {
            Logger.getLogger(AutoAVL.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Recorrido terminado");
        mostrar();
    }

    public void mostrar() {

        ImageIcon icono = new ImageIcon("src\\Imagenes\\AVLTree.png");
        icono.getImage().flush();
        imagen.setIcon(icono);
        imagen.revalidate();
        imagen.validate();
        imagen.repaint();
        
        
                jScrollPane.revalidate();
        jScrollPane.validate();
        jScrollPane.repaint();

    }

}
