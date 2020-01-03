package Hilos;

import EDD.NodeLD;
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
public class ManualAVL extends Thread {
    
    NodeLD as;
    JLabel jLabel;
    int velocidad = 1;
    JLabel descrip;
    JLabel image;
    
    public ManualAVL(JLabel jLabel, NodeLD as, int value, JLabel des, JLabel jLabelImage) {
        this.jLabel = jLabel;
        this.as = as;
        this.velocidad = value;
        this.descrip = des;
        this.image = jLabelImage;
        mostrar();
    }
    
    @Override
    public void run() {
        mostrar();
        if (as != null) {
            mostrar();
            try {
                mostrar();
                
                Proyecto_2.avl.buscar(as.getDato());
                mostrar();
                TimeUnit.SECONDS.sleep(velocidad);
                jLabel.setText("Insertando:  " + as.getDato());
                descrip.setText("Búsqueda de la posición de inserción del nodo nuevo"); 
                Proyecto_2.avl.insertDes(as.getDato());
                mostrar();
                
                
                TimeUnit.SECONDS.sleep(velocidad);
                descrip.setText("Inserta nodo calcula equilibrio"); 
                Proyecto_2.avl.insert(as.getDato());
                Proyecto_2.avl.report();
                mostrar();
                descrip.setText("Inserto"); 
                
            } catch (InterruptedException ex) {
                Logger.getLogger(AutoAVL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        mostrar();
        JOptionPane.showMessageDialog(null, "Se inserto " + as.getDato());
        
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
