/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import javax.swing.JOptionPane;
import proyecto_2.Proyecto_2;

import java.awt.Image;
import java.awt.Toolkit;


/**
 *
 * @author Oscar C
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        
    }
    
    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/logo.jpg"));
        return retValue;
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBottonLogin = new javax.swing.JButton();
        jButtonClose = new javax.swing.JButton();
        jPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextUser = new javax.swing.JTextField();
        juse = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBottonLogin.setText("Login");
        jBottonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBottonLoginActionPerformed(evt);
            }
        });
        getContentPane().add(jBottonLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 480, -1, -1));

        jButtonClose.setText("Close");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 480, -1, -1));

        jPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(jPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 420, 140, -1));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Password:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, -1, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("User:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 30, -1));

        jTextUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextUserActionPerformed(evt);
            }
        });
        getContentPane().add(jTextUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 140, -1));

        juse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user.png"))); // NOI18N
        getContentPane().add(juse, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 250, 270));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fodno.jpg"))); // NOI18N
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBottonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBottonLoginActionPerformed
        if (this.jTextUser.getText().equals("admin")  && this.jPassword.getText().equals("123")){
            Admin ventanA = new Admin();
            this.setVisible(false);
            ventanA.setVisible(true);
            
            
        }else if(Proyecto_2.usuarios.login( Integer.parseInt(this.jTextUser.getText()), this.jPassword.getText())){
            Admin ventanA = new Admin();
            this.setVisible(false);
            ventanA.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null,"Usuario no existe");
        }



    }//GEN-LAST:event_jBottonLoginActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        System.exit(0);        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordActionPerformed

    private void jTextUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextUserActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBottonLogin;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JTextField jTextUser;
    private javax.swing.JLabel juse;
    // End of variables declaration//GEN-END:variables
}
