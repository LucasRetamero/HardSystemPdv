package layoutInterno;

import DAO.Conexao;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import layout.layoutGerencia.frmGerenciaGrupo;

public class buscaGrupo extends javax.swing.JDialog {

    Connection conec = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    private String grupo;

    public buscaGrupo(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        try {
            conec = Conexao.conecta();
            preencher_jtable('S');
            bloqueiaCampo();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(buscaGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void preencher_jtable(char status_item) {

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(500);

        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setNumRows(0);
        String sql = ("Select id_grupo,grupo from grupo where status ='S' order by grupo asc");
        try {
            pst = conec.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString("id_grupo"), rs.getString("grupo")});
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar a Tabela " + erro);
        }
    }
     public void preencher_jtableNomeGrupo(char status_item) {

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(500);

        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setNumRows(0);
        String sql = ("Select id_grupo,grupo from grupo where grupo like ? and status ='S' order by grupo asc");
        try {
            pst = conec.prepareStatement(sql);
            pst.setString(1,txtBusca.getText()+"%");
            rs = pst.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString("id_grupo"), rs.getString("grupo")});
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar a Tabela " + erro);
        }
    }
    private void bloqueiaCampo (){
        txtGrupo.setEnabled(false);
    }
    
    private void cadastraGrupo (){
        String sql = "insert into grupo (grupo) values (?)";
        if (txtGrupo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo Nome é Obrigatorio", "Aviso", JOptionPane.WARNING_MESSAGE);
            txtGrupo.setBackground(Color.yellow);
            return;
        }
        try {
            pst = conec.prepareStatement(sql);
            pst.setString(1, txtGrupo.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Grupo Cadastrado", "Grupo Cadastrado", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Pesquisar pelo nome do grupo");

        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaKeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Grupo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Grupo selecionado");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                    .addComponent(txtBusca)
                    .addComponent(txtGrupo)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/selecionarMenu.png"))); // NOI18N
        jMenu1.setText("Selecionar grupo");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/sairMenu.png"))); // NOI18N
        jMenu2.setText("Sair");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/cadastroMenu.png"))); // NOI18N
        jMenu3.setText("Cadastrar grupo");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyReleased
        preencher_jtableNomeGrupo('S');
        if(txtBusca.getText().equals("")){
        preencher_jtable('S');
        }
    }//GEN-LAST:event_txtBuscaKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (jTable1.getSelectedRowCount() == 1) {
            int linha_select = jTable1.getSelectedRow();
            setGrupo((String) jTable1.getValueAt(linha_select, 1));
            txtGrupo.setText(jTable1.getValueAt(linha_select, 1).toString());
            if (evt.getClickCount() == 2) {
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um grupo", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
             if (jTable1.getSelectedRowCount() == 1) {
            int linha_select = jTable1.getSelectedRow();
            setGrupo((String) jTable1.getValueAt(linha_select, 1));
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um grupo", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
         dispose();
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        frmGerenciaGrupo obj = new frmGerenciaGrupo(null, rootPaneCheckingEnabled);
        obj.setLocationRelativeTo(null);
        obj.setVisible(true);
        preencher_jtable('S');
    }//GEN-LAST:event_jMenu3MouseClicked

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
            java.util.logging.Logger.getLogger(buscaGrupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(buscaGrupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(buscaGrupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(buscaGrupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    buscaGrupo dialog = new buscaGrupo(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(buscaGrupo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtGrupo;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
