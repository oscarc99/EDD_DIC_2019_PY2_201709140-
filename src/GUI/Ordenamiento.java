package GUI;

import Object.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultCaret;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import static proyecto_2.Proyecto_2.toIntExact;

/**
 *
 * @author Oscar C
 */
public class Ordenamiento extends javax.swing.JFrame {

    /**
     * Creates new form Ordenamiento
     */
    User u;
    private int[] a;
    private int nImagenes;
    private String tipoOrdenamiento;
    Thread x;
    Runnable bubble;
    private int indiceManual;

    public Ordenamiento(User X) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.u = X;
        //DefaultCaret caret = (DefaultCaret)this.jTextArea1.getCaret();
        //caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        this.setVisible(true);
        nImagenes = 0;
        this.tipoOrdenamiento = "";
        bubble = new Runnable() {
            @Override
            public void run() {
                int i = 1;
                String path = "";
                while (true) {
                    try {
                        path = "src\\imagenes\\" + tipoOrdenamiento + "\\i" + i + ".png";
                        File tmpDir = new File(path);
                        boolean exists = tmpDir.exists();
                        if (!exists) {
                            break;
                        }
                        ImageIcon icono = new ImageIcon(path);
                        icono.getImage().flush();
                        jLabel1.setIcon(icono);
                        jLabel1.revalidate();
                        jLabel1.validate();
                        jLabel1.repaint();
                        Thread.sleep(jSlider.getValue() * 1000);
                        i++;
                    } catch (Exception ex) {
                        // Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                JOptionPane.showMessageDialog(null,"Recorrido terminado");
            }
        };
        x = new Thread();
        indiceManual = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSlider = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton3.setText("QuickSort");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Insercion");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Anterior paso");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Siguiente paso");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Recorrido automático");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton1.setText("Cargar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Burbuja");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jSlider.setMajorTickSpacing(1);
        jSlider.setMaximum(3);
        jSlider.setMinimum(1);
        jSlider.setValue(2);

        jLabel2.setText("Velocidad: ");

        jButton8.setText("Regresar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel2))
                            .addComponent(jButton3)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addGap(68, 68, 68)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton8)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(90, 90, 90))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton5)
                                .addComponent(jButton6))
                            .addComponent(jButton7)
                            .addComponent(jSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        nImagenes = 1;
        this.tipoOrdenamiento = "quicksort";
        int[] aux = new int[this.a.length];
        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }
        this.quickSort(aux, 0, a.length - 1, nImagenes);

        JOptionPane.showMessageDialog(null, "Ordenamiento preparado");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.tipoOrdenamiento = "insercion";
        int[] aux = new int[this.a.length];
        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }
        this.insercionOrdenada(aux);
        JOptionPane.showMessageDialog(null, "Ordenamiento preparado");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (this.indiceManual == 1) {
            this.jButton5.setEnabled(false);
        } else {
            this.jButton5.setEnabled(true);
            this.jButton6.setEnabled(true);
        }

        this.indiceManual--;
        String path = "src\\imagenes\\" + tipoOrdenamiento + "\\i" + this.indiceManual + ".png";
        File tmpDir = new File(path);
        boolean exists = tmpDir.exists();

        ImageIcon icono = new ImageIcon(path);
        icono.getImage().flush();
        jLabel1.setIcon(icono);
        jLabel1.revalidate();
        jLabel1.validate();
        jLabel1.repaint();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (this.nImagenes <= this.indiceManual + 1) {
            this.jButton6.setEnabled(false);
        } else {
            this.jButton5.setEnabled(true);
            this.jButton6.setEnabled(true);
        }

        this.indiceManual++;
        String path = "src\\imagenes\\" + tipoOrdenamiento + "\\i" + this.indiceManual + ".png";
        File tmpDir = new File(path);
        boolean exists = tmpDir.exists();
        if (!exists) {
        }
        ImageIcon icono = new ImageIcon(path);
        icono.getImage().flush();
        jLabel1.setIcon(icono);
        jLabel1.revalidate();
        jLabel1.validate();
        jLabel1.repaint();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:

        if (x.isAlive()) {
            x.interrupt();
            x.stop();
        }
        x = new Thread(bubble);
        x.start();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(this);
        File archivo = jf.getSelectedFile();
        if (archivo != null) {
            this.a = numeros(archivo.getAbsolutePath());
            JOptionPane.showMessageDialog(null, "Datos cargados");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.tipoOrdenamiento = "burbuja";
        int[] aux = new int[this.a.length];
        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }
        this.graficarOrdenamientoBurbuja(aux);
        JOptionPane.showMessageDialog(null, "Ordenamiento preparado");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Usuario ventanA = new Usuario(this.u);
        this.setVisible(false);
        ventanA.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ordenamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ordenamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ordenamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ordenamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    public void graficar(String name, int num, String contenido, boolean dot) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        String path = "src\\imagenes\\" + this.tipoOrdenamiento + "\\" + name + num;
        try {
            fichero = new FileWriter(path + ".dot");

            pw = new PrintWriter(fichero);

            pw.println("digraph G{ node [shape = record ];\n rankdir=LR;" + contenido + "}");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            String compiler = "";
            if (dot) {
                compiler = "dot.exe";
            } else {
                compiler = "dot -Kfdp -n ";
            }
            Process p = Runtime.getRuntime().exec("cmd /c " + compiler + " -Tpng " + path + ".dot -o " + path + ".png");
            //Process ap = Runtime.getRuntime().exec("cmd /c  src\\imagenes\\GrafoNodos.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public int[] numeros(String json) {
        int contador = 0;
        int arreglo[] = null;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(json));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonA = (JSONArray) jsonObject.get("Array");

            int num;

            for (Object j : jsonA) {

                contador++;
            }
            arreglo = new int[contador];
            contador = 0;
            for (Object j : jsonA) {
                jsonObject = (JSONObject) j;
                num = toIntExact((long) jsonObject.get("num"));
                arreglo[contador] = num;
                contador++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arreglo;
    }

    /**
     * @param args the command line arguments
     */
    public void graficarOrdenamientoBurbuja(int[] a) {
        if (a == null) {
            JOptionPane.showMessageDialog(null, "Sin arreglo ingresado");
            this.graficar("A", 1, "d->o->e->r", true);
            //this.jTextArea1.setText("No podemos graficar si no sabemos que graficaaar :'c");
            return;
        }
//        this.jTextArea1.setText(""
//                + "El tiempo de ejecución del algoritmo de la burbuja es del orden O(n2)\n"
//                + "Es uno de los peores algoritmos de ordenación\n"
//                + "en cuanto a tiempo de ejecución,\n"
//                + "solamente es recomendable su uso para \n"
//                + "ordenar listas con un número pequeño de elementos."
//                + "");
        String contenido = "";
        String auxiliar = "";
        String fila = "X[label=\"{ ";
        String filaCambio = "";
        String auxiliarCambio = "";
        int i, j, k, m, aux;
        m = 0;
        for (i = 0; i < a.length - 1; i++) {
            for (j = 0; j < a.length - i - 1; j++) {
                //Original
                contenido = "";
                fila = "";
                fila = "X[label=\"{ ";
                for (k = 0; k < a.length; k++) {
                    fila += a[k] + "|";
                }
                fila = fila.substring(0, fila.length() - 1);
                fila += "}\"]";
                m++;
                contenido += fila;
                this.graficar("i", m, contenido, true);
                if (a[j + 1] < a[j]) {
                    filaCambio = "X[label=\"{ ";
                    aux = a[j + 1];
                    //agregamos lo que ya esta y solo marcamos los que se van a modificar
                    fila = "X[label=\"{ ";
                    filaCambio = "X[label=\"{ ";
                    for (k = 0; k < a.length; k++) {
                        if (k == j) {
                            fila += a[k] + " → |";
                            fila += " ↑ " + a[k + 1] + "|";
                            filaCambio += a[k] + " → |";
                            filaCambio += "" + a[k] + "|";
                            k++;
                            continue;
                        }
                        fila += a[k] + "|";
                        filaCambio += a[k] + "|";
                    }
                    auxiliar = "2[label=\"{" + aux + "}\"xlabel=\"Aux\"]\n";
                    fila = fila.substring(0, fila.length() - 1);
                    fila += "}\"]\n";
                    m++;
                    contenido += fila;
                    contenido += auxiliar;
                    this.graficar("i", m, contenido, true);
                    auxiliarCambio = "2[label=\" { ↓ " + aux + "}\"xlabel=\"Aux\"]\n";
                    m++;
                    contenido = "";
                    contenido += filaCambio + "}\"]\n";
                    contenido += auxiliarCambio;
                    this.graficar("i", m, contenido, true);

                    //Marco aux
                    a[j + 1] = a[j];
                    a[j] = aux;
                    //Tras el cambio mostrar
                }
                fila += a[j + 1] + "|";
                auxiliar = "";
            }
            contenido = "";
            fila = "";
            fila = "X[label=\"{ ";
            for (k = 0; k < a.length; k++) {
                fila += a[k] + "|";
            }
            fila = fila.substring(0, fila.length() - 1);
            fila += "}\"]";
            m++;
            contenido += fila;
            this.graficar("i", m, contenido, true);
            // fila+="}\"]";
            //  contenido+=auxiliar;
            //  contenido+=fila;
            //  graficar("b",m,contenido);
            // contenido="";
        }
        this.nImagenes = m;

    }

    public void quickSort(int[] a, int izq, int der, int nImagenes) {
        int pivote; // tomamos primer elemento como pivote

        pivote = a[izq];

        int i = izq; // i realiza la búsqueda de izquierda a derecha
        int j = der; // j realiza la búsqueda de derecha a izquierda
        int aux;
        this.graficarQuickSort(a, a[i], pivote, a[j], nImagenes);

        while (i < j) {            // mientras no se crucen las búsquedas
            while (a[i] <= pivote && i < j) {
                nImagenes++;
                this.graficarQuickSort(a, a[i], pivote, a[j], nImagenes);
                i++;
                //Graphviz, debo mostrar el desplazamiento               
            } // busca elemento mayor que pivote
            nImagenes++;
            this.graficarQuickSort(a, a[i], pivote, a[j], nImagenes);
            while (a[j] > pivote) {
                //Graphviz, debo mostrar tambien este desplazamiento
                nImagenes++;
                this.graficarQuickSort(a, a[i], pivote, a[j], nImagenes);
                j--;
            }         // busca elemento menor que pivote
            nImagenes++;
            this.graficarQuickSort(a, a[i], pivote, a[j], nImagenes);
            if (i < j) {                        // si no se han cruzado                      
                nImagenes++;
                this.graficarQuickSort(a, a[i], pivote, a[j], nImagenes);
                aux = a[i];                  // los intercambia
                nImagenes++;
                this.graficarQuickSort(a, a[i], pivote, a[j], nImagenes);
                a[i] = a[j];
                nImagenes++;
                this.graficarQuickSort(a, a[i], pivote, a[j], nImagenes);
                a[j] = aux;
                nImagenes++;
                this.graficarQuickSort(a, a[i], pivote, a[j], nImagenes);

                //Graphviz, debo mostrar el intercambio
            }
        }
        a[izq] = a[j]; // se coloca el pivote en su lugar de forma que tendremos0
        nImagenes++;
        this.graficarQuickSort(a, a[i], pivote, a[j], nImagenes);
        a[j] = pivote; // los menores a su izquierda y los mayores a su derecha
        nImagenes++;
        this.graficarQuickSort(a, a[i], pivote, a[j], nImagenes);
        if (izq < j - 1) {
            nImagenes++;
            quickSort(a, izq, j - 1, nImagenes); // ordenamos subarray izquierdo             
        }
        if (j + 1 < der) {
            nImagenes++;
            quickSort(a, j + 1, der, nImagenes); // ordenamos subarray derecho             
        }
    }

    public int graficarQuickSort(int[] x, int izq, int piv, int der, int nImagenes) {
//digraph G{ 
//    node [shape = record ];
//    rankdir=LR;
//    puntDer[label="↑ 1 ↑" xlabel="Puntero derecho" pos = "12,0!"]
//    puntIzq[label="↑ 4 ↑" xlabel="Puntero izquierdo" pos = "8,0!"]
//    pivote[label="↓ 2 ↓" xlabel ="Pivote" pos = "10,2!"]
//    X[label="{50|30|20|10|40}" pos = "10,1!"]
//}

        String contenido = "";
        String puntDer = "puntDer[label=\"↑" + der + "↑\" xlabel=\"Puntero derecho\" pos = \"12,0!\"]\n";
        String puntIzq = "puntIzq[label=\"↑" + izq + "↑\" xlabel=\"Puntero izquierdo\" pos = \"8,0!\"]\n";
        String pivote = "pivote[label=\"↓" + piv + "↓\" xlabel =\"Pivote\" pos = \"10,2!\"]\n";
        String fila = "";
        fila = "X[label=\"{ ";
        for (int k = 0; k < x.length; k++) {
            fila += x[k] + "|";
        }
        fila = fila.substring(0, fila.length() - 1);
        fila += "}\" pos = \"10,1!\" ]";
        contenido = puntDer + puntIzq + pivote + fila;
        this.graficar("i", nImagenes, contenido, false);
        return nImagenes;
    }

    public void insercionOrdenada(int[] a) {
        int j;
        int aux;
        nImagenes = 0;
        for (int p = 1; p < a.length; p++) { // desde el segundo elemento hasta
            aux = a[p]; // el final, guardamos el elemento y
            j = p - 1; // empezamos a comprobar con el anterior
            nImagenes++;
            graficarInsercionOrdenada(a, aux, nImagenes);
            while ((j >= 0) && (aux < a[j])) { // mientras queden posiciones y el
                // valor de aux sea menor que los
                a[j + 1] = a[j];       // de la izquierda, se desplaza a
                j--;                   // la derecha
                nImagenes++;
                graficarInsercionOrdenada(a, aux, nImagenes);
            }
            a[j + 1] = aux; // colocamos aux en su sitio
            nImagenes++;
            graficarInsercionOrdenada(a, aux, nImagenes);
        }
    }

    public void graficarInsercionOrdenada(int[] a, int x, int nImagen) {
        String contenido = "";
        for (int i = 0; i < a.length; i++) {
            String fila = "";
            fila = "X[label=\"{ ";
            for (int k = 0; k < a.length; k++) {
                fila += a[k] + "|";
            }
            String aux = "puntDer[label=\"Insertando: " + x + "\" pos = \"9,2!\"] \n";
            fila = fila.substring(0, fila.length() - 1);
            fila += "}\" pos = \"10,1!\" ]";
            contenido = fila + aux;
            this.graficar("i", nImagenes, contenido, false);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSlider jSlider;
    // End of variables declaration//GEN-END:variables
}
