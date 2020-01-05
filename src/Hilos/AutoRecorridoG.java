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
    String recorrid = "";
    JLabel edd;
    String type;
    JLabel visitados;

    public AutoRecorridoG(String[] dato, JLabel imagen, int velocidad, JLabel descripcion, JLabel recorido, JLabel edd, String tipo, JLabel jLabelVisitados) {
        this.dato = dato;
        this.edd = edd;
        this.imagen = imagen;
        this.velocidad = velocidad;
        this.descripcion = descripcion;
        this.recorido = recorido;
        this.type = tipo;
        this.visitados = jLabelVisitados;
    }

    @Override
    public void run() {

        if (type.equals("Anchura")) {
            try {
                for (int i = 0; i < dato.length; i++) {
                    mostrarA(i);
                    if (dato[i] != null) {
                        recorrid += dato[i] + ", ";
                    }
                    mostrarA(i);
                    Proyecto_2.grafo.GrafoRPintado(dato[i]);
                    descripcion.setText("Vistando " + dato[i]);
                    visitados.setText(recorrid);
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

            mostrarArbolA();
            Proyecto_2.grafo.NonVisit();
        } else {
            //Profundidad

            try {
                for (int i = 0; i < dato.length; i++) {
                    mostrarP(dato[i]);
                    if (dato[i] != null) {
                        recorrid += dato[i] + ", ";
                    }
                    mostrarP(dato[i]);
                    Proyecto_2.grafo.GrafoRPintado(dato[i]);
                    descripcion.setText("Vistando " + dato[i]);
                    visitados.setText(recorrid);
                    mostrarP(dato[i]);
                    TimeUnit.SECONDS.sleep(velocidad);
                    mostrarP(dato[i]);
                    Proyecto_2.grafo.GrafoRPintado();
                    descripcion.setText("Agrega " + dato[i]);
                    mostrarP(dato[i]);
                    recorido.setText(recorrid);
                    mostrarP(dato[i]);
                    TimeUnit.SECONDS.sleep(velocidad + 1);
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(AutoAVL.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Recorrido terminado");

            Proyecto_2.grafo.NonVisit();
            mostrarArbolP();
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

        ImageIcon ic = new ImageIcon("src\\Imagenes\\" + pos + "0.png");
        ic.getImage().flush();
        edd.setIcon(ic);
        edd.revalidate();
        edd.validate();
        edd.repaint();

    }

    public void mostrarArbolP() {
        //MAnera de mostrar la anchura
        ImageIcon icono = new ImageIcon("src\\Imagenes\\Profundidad.png");
        icono.getImage().flush();
        imagen.setIcon(icono);
        imagen.revalidate();
        imagen.validate();
        imagen.repaint();
    }

    public void mostrarArbolA() {
        //MAnera de mostrar la anchura
        ImageIcon icono = new ImageIcon("src\\Imagenes\\Anchura.png");
        icono.getImage().flush();
        imagen.setIcon(icono);
        imagen.revalidate();
        imagen.validate();
        imagen.repaint();
    }

}
