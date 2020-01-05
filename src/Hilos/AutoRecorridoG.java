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
public class AutoRecorridoG extends Thread {

    String dato[];
    JLabel imagen;
    int velocidad;
    JLabel descripcion;
    JLabel recorido;
    String recorrid;
    JLabel edd;
    String type;

    public AutoRecorridoG(String[] dato, JLabel imagen, int velocidad, JLabel descripcion, JLabel recorido, JLabel edd, String tipo) {
        this.dato = dato;
        this.edd = edd;
        this.imagen = imagen;
        this.velocidad = velocidad;
        this.descripcion = descripcion;
        this.recorido = recorido;
        this.type = tipo;
    }

    @Override
    public void run() {

        if (type.equals("Anchura")) {
            try {
                for (int i = 0; i < dato.length; i++) {
                    mostrarA(i);
                    recorrid += dato[i] + ", ";
                    mostrarA(i);
                    Proyecto_2.grafo.GrafoRPintado(dato[i]);
                    descripcion.setText("Vistando " + dato[i]);
                    mostrarA(i);
                    TimeUnit.SECONDS.sleep(velocidad);
                    mostrarA(i);
                    Proyecto_2.grafo.GrafoRPintado();
                    descripcion.setText("Agrega " + dato[i]);
                    mostrarA(i);
                    recorido.setText(recorrid);
                    mostrarA(i);
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(AutoAVL.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Recorrido terminado");

            Proyecto_2.grafo.NonVisit();
        } else {
            //Profundidad

            try {
                for (int i = 0; i < dato.length; i++) {
                    mostrarP(dato[i]);
                    recorrid += dato[i] + ", ";
                    mostrarP(dato[i]);
                    Proyecto_2.grafo.GrafoRPintado(dato[i]);
                    descripcion.setText("Vistando " + dato[i]);
                    mostrarP(dato[i]);
                    TimeUnit.SECONDS.sleep(velocidad);
                    mostrarP(dato[i]);
                    Proyecto_2.grafo.GrafoRPintado();
                    descripcion.setText("Agrega " + dato[i]);
                    mostrarP(dato[i]);
                    recorido.setText(recorrid);
                    mostrarP(dato[i]);
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(AutoAVL.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Recorrido terminado");

            Proyecto_2.grafo.NonVisit();

        }

    }

    public void mostrarA(int pos) {
        //MAnera de mostrar la anchura
        ImageIcon icono = new ImageIcon("src\\Imagenes\\GrafoNodos.png");
        icono.getImage().flush();
        imagen.setIcon(icono);
        imagen.revalidate();
        imagen.validate();
        imagen.repaint();

        ImageIcon ic = new ImageIcon("src\\Imagenes\\Cola" + pos + ".png");
        ic.getImage().flush();
        edd.setIcon(ic);
        edd.revalidate();
        edd.validate();
        edd.repaint();

    }

    public void mostrarP(String pos) {
        //MAnera de mostrar la anchura
        ImageIcon icono = new ImageIcon("src\\Imagenes\\GrafoNodos.png");
        icono.getImage().flush();
        imagen.setIcon(icono);
        imagen.revalidate();
        imagen.validate();
        imagen.repaint();

        ImageIcon ic = new ImageIcon("src\\Imagenes\\" + pos + "1.png");
        ic.getImage().flush();
        edd.setIcon(ic);
        edd.revalidate();
        edd.validate();
        edd.repaint();

    }

}
