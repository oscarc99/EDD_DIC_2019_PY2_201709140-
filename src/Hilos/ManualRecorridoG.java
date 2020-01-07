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
public class ManualRecorridoG extends Thread {

    String dato[];
    JLabel imagen;
    int velocidad;
    JLabel descripcion;
    JLabel recorido;
    int posicion;
    String recorrid;
    JLabel edd;
    String Type;
    JLabel visitados;
    JScrollPane jScrollPane;

    public ManualRecorridoG(String[] dato, JLabel imagen, int velocidad, JLabel descripcion, JLabel recorido, int posicion, JLabel edd, String tipo, JLabel jLabelVisitados, JScrollPane scroll) {
        this.recorrid = "Recorrido: ";
        for (int i = 0; i < posicion; i++) {
            if (dato[posicion] != null) {
                recorrid += dato[i] + ", ";
            }
        }

        this.dato = dato;
        this.imagen = imagen;
        this.velocidad = velocidad;
        this.descripcion = descripcion;
        this.recorido = recorido;
        this.posicion = posicion;
        this.edd = edd;
        this.Type = tipo;
        this.visitados = jLabelVisitados;
    }

    @Override
    public void run() {
        if (Type.equals("Anchura")) {
            try {
                if (dato[posicion] != null) {
                    recorrid += dato[posicion] + ", ";
                }
                mostrar();
                Proyecto_2.grafo.GrafoRPintado(dato[posicion]);
                descripcion.setText("Vistando " + dato[posicion]);
                visitados.setText(recorrid);
                mostrar();
                TimeUnit.SECONDS.sleep(velocidad);
                mostrar();
                Proyecto_2.grafo.GrafoRPintado();
                descripcion.setText("Agrega " + dato[posicion]);
                mostrar();
                recorido.setText(recorrid);
                mostrar();

            } catch (InterruptedException ex) {
                Logger.getLogger(AutoAVL.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Visitado");

        } else  {
            //Profundidad
            try {
                if (dato[posicion] != null) {
                    recorrid += dato[posicion] + ", ";
                }
                mostrar(dato[posicion]);
                Proyecto_2.grafo.GrafoRPintado(dato[posicion]);
                descripcion.setText("Vistando " + dato[posicion]);
                mostrar(dato[posicion]);
                visitados.setText(recorrid);
                mostrar(dato[posicion]);
                TimeUnit.SECONDS.sleep(velocidad);
                mostrar(dato[posicion]);
                Proyecto_2.grafo.GrafoRPintado();
                descripcion.setText("Agrega " + dato[posicion]);
                mostrar(dato[posicion]);
                recorido.setText(recorrid);

                mostrar(dato[posicion]);

            } catch (InterruptedException ex) {
                Logger.getLogger(AutoAVL.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Visitado");
        }

    }

    public void mostrar() {

        ImageIcon icono = new ImageIcon("src\\Imagenes\\GrafoNodos.png");
        icono.getImage().flush();
        imagen.setIcon(icono);
        imagen.revalidate();
        imagen.validate();
        imagen.repaint();

        ImageIcon ic = new ImageIcon("src\\Imagenes\\Cola" + posicion + ".png");
        ic.getImage().flush();
        edd.setIcon(ic);
        edd.revalidate();
        edd.validate();
        edd.repaint();


    }

    public void mostrar(String p) {

        ImageIcon icono = new ImageIcon("src\\Imagenes\\GrafoNodos.png");
        icono.getImage().flush();
        imagen.setIcon(icono);
        imagen.revalidate();
        imagen.validate();
        imagen.repaint();


        ImageIcon ic = new ImageIcon("src\\Imagenes\\" + p + "0.png");
        ic.getImage().flush();
        edd.setIcon(ic);
        edd.revalidate();
        edd.validate();
        edd.repaint();


    }

}
