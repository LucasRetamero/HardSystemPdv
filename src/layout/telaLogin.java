package layout;

import Controler.InfoUsuario;
import DAO.FazerLogin;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tratamento.mensagem;
import tratamento.tratandoLogin;

public class telaLogin extends javax.swing.JFrame {
    
   InfoUsuario objColetando;
   tratandoLogin objTratar;
   FazerLogin objLogar;
    public telaLogin(){
        initComponents();
         URL url = this.getClass().getResource("/imagens/IconePequeno.png");  
       Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);  
       this.setIconImage(iconeTitulo);
       objColetando = new InfoUsuario();
       objTratar = new tratandoLogin();
       objLogar = new  FazerLogin();
    }
 private void  Logar(){
      try{
           objColetando.setUsuario(txtUsuario.getText());
           objColetando.setSenha(txtSenha.getText());
           objTratar.VerificandoLogin(objColetando);
           if(objLogar.LogarSistema(objColetando) == true){
           this.dispose();
           }
      }catch(mensagem ex) {
          JOptionPane.showMessageDialog(null,ex.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
       }
 
 }
    /*private void Logar() throws ClassNotFoundException {
        String sql = "select * from login where usuario = ? and senha = ?";
        try {
            pst = conec.prepareStatement(sql);
            pst.setString(1, txtUsuario.getText());
            pst.setString(2, txtSenha.getText());
             
            rs = pst.executeQuery();
            if (rs.next()) {
           objColetando.setUsuario(rs.getString(2));
           objColetando.setNivel(rs.getString(4));
                telaPrincipal frm = new telaPrincipal(txtUsuario.getText());
                frm.setVisible(true);

                dispose();

            } else {
                JOptionPane.showMessageDialog(null, "Usuario ou senha invalidos !!!");
            }

        } catch (SQLException | HeadlessException e) {

        }

    }*/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela de Acesso");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Senha ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(100, 210, 50, 17);

        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
        });
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(160, 160, 90, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Usuario");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(100, 170, 60, 17);

        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSenhaKeyReleased(evt);
            }
        });
        getContentPane().add(txtSenha);
        txtSenha.setBounds(160, 200, 90, 30);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Exit.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(260, 200, 50, 30);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Key-27.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(260, 160, 50, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/login.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 420, 400);

        setSize(new java.awt.Dimension(397, 398));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Logar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
       txtUsuario.setText(txtUsuario.getText().toUpperCase());
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void txtSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyReleased
        txtSenha.setText(txtSenha.getText().toUpperCase());
    }//GEN-LAST:event_txtSenhaKeyReleased

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtSenha.requestFocus();
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER){
               Logar();
          
        }
    }//GEN-LAST:event_txtSenhaKeyPressed

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
            java.util.logging.Logger.getLogger(telaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             
                        new telaLogin().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
