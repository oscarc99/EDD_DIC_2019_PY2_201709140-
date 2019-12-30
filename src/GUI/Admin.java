/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import EDD.Node;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyecto_2.Proyecto_2;

/**
 *
 * @author Oscar C
 */
public class Admin extends javax.swing.JFrame implements KeyListener {

    DefaultTableModel md;
    String data[][] = {};
    String cabeza[] = {"Carmet", "Nombre", "Apellido", "Password", "Motivo"};

    /**
     * Creates new form Admin
     */
    public Admin() {
        initComponents();
        this.setLocationRelativeTo(null);
        md = new DefaultTableModel(data, cabeza);
        jTable.setModel(md);
        Node temp = Proyecto_2.err.getFirst();
        while (temp != null) {
            Object datos[] = {temp.getDato().getCarnet(), temp.getDato().getName(), temp.getDato().getApellido(), temp.getDato().getPass(), temp.getDato().getMotivo()};
            md.addRow(datos);
            temp = temp.getNext();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButtonAdd = new javax.swing.JButton();
        jTextFieldAdd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButtonCerrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemMod = new javax.swing.JMenuItem();
        jMenuItemDelete = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Bienvenido usuario administrador");

        jButtonAdd.setText("Cargar Usuarios");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jButtonAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonAddKeyPressed(evt);
            }
        });

        jTextFieldAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAddKeyPressed(evt);
            }
        });

        jLabel2.setText("Nombre Archivo de Carga:");

        jButtonCerrar.setText("Cerrar Sesion");
        jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarActionPerformed(evt);
            }
        });

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable);

        jLabel3.setText("Usuarios no guardados");

        jMenu1.setText("Usuarios");

        jMenuItemMod.setText("Modificar");
        jMenuItemMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemMod);

        jMenuItemDelete.setText("Eliminar");
        jMenuItemDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeleteActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemDelete);

        jMenuItem3.setText("Reporte");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Información");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(119, 119, 119)
                                        .addComponent(jTextFieldAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonAdd)
                                    .addComponent(jButtonCerrar))))
                        .addGap(45, 45, 45))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButtonCerrar))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAdd))
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModActionPerformed
        UserAdmin ventanA = new UserAdmin();
        this.setVisible(false);
        ventanA.setVisible(true);
    }//GEN-LAST:event_jMenuItemModActionPerformed

    private void jMenuItemDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeleteActionPerformed
        UserAdmin ventanA = new UserAdmin();
        this.setVisible(false);
        ventanA.setVisible(true);
    }//GEN-LAST:event_jMenuItemDeleteActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        try {
            Proyecto_2.readUser(jTextFieldAdd.getText());
            Node temp = Proyecto_2.err.getFirst();
            while (temp != null) {
                Object datos[] = {temp.getDato().getCarnet(), temp.getDato().getName(), temp.getDato().getApellido(), temp.getDato().getPass(), temp.getDato().getMotivo()};
                md.addRow(datos);
                temp = temp.getNext();
            }
            JOptionPane.showMessageDialog(null, "Carga realizada con exito");
        } catch (Exception e) {
            System.out.println("ERROR EN CARGA" + e);
        }


    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Proyecto_2.usuarios.report();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        Login ventanA = new Login();
        this.setVisible(false);
        ventanA.setVisible(true);
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void jButtonAddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonAddKeyPressed
        int KeyCode = evt.getKeyCode();
        if (KeyCode == KeyEvent.VK_ENTER) {
            Proyecto_2.readUser(jTextFieldAdd.getText());
            Node temp = Proyecto_2.err.getFirst();
            while (temp != null) {
                Object datos[] = {temp.getDato().getCarnet(), temp.getDato().getName(), temp.getDato().getApellido(), temp.getDato().getPass(), temp.getDato().getMotivo()};
                md.addRow(datos);
                temp = temp.getNext();
            }
            JOptionPane.showMessageDialog(null, "Carga realizada con exito");
        }
    }//GEN-LAST:event_jButtonAddKeyPressed

    private void jTextFieldAddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAddKeyPressed
        int KeyCode = evt.getKeyCode();
        if (KeyCode == KeyEvent.VK_ENTER) {
            Proyecto_2.readUser(jTextFieldAdd.getText());
            Node temp = Proyecto_2.err.getFirst();
            while (temp != null) {
                Object datos[] = {temp.getDato().getCarnet(), temp.getDato().getName(), temp.getDato().getApellido(), temp.getDato().getPass(), temp.getDato().getMotivo()};
                md.addRow(datos);
                temp = temp.getNext();
            }
            JOptionPane.showMessageDialog(null, "Carga realizada con exito");
        }
    }//GEN-LAST:event_jTextFieldAddKeyPressed

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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemDelete;
    private javax.swing.JMenuItem jMenuItemMod;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextFieldAdd;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int KeyCode = e.getKeyCode();
        if (KeyCode == KeyEvent.VK_ENTER) {
            Proyecto_2.readUser(jTextFieldAdd.getText());
            Node temp = Proyecto_2.err.getFirst();
            while (temp != null) {
                Object datos[] = {temp.getDato().getCarnet(), temp.getDato().getName(), temp.getDato().getApellido(), temp.getDato().getPass(), temp.getDato().getMotivo()};
                md.addRow(datos);
                temp = temp.getNext();
            }
            JOptionPane.showMessageDialog(null, "Carga realizada con exito");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
