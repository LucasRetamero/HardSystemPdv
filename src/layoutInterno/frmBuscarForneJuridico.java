/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layoutInterno;

import DAO.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Su
 */
public class frmBuscarForneJuridico extends javax.swing.JDialog {

      private Connection conec = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    private int codigoJuridico;
    private String nomeJuridico;
    private String TelJuridico;
    private String CelJuridico;
    private String cepJuridico,cnpj;
    private String enderecoJuridico;
    private String bairroJuridico;
    private String statusJuridico;
    private String imgJuridico;
    public frmBuscarForneJuridico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         try {
            conec = Conexao.conecta();
            PreencherTabelaJuridico();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(buscaEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frmBuscarForneFisico.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscaJuridico = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbJuridico = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtForneceJuri = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar fornecedor juridico");
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Pesquisar pelo nome do fornecedor juridico:");

        txtBuscaJuridico.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscaJuridico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaJuridicoKeyReleased(evt);
            }
        });

        tbJuridico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idJuridico", "Nome", "TEL", "CEL", "Ende", "Bairro", "CEP", "CNPJ", "IMG", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbJuridico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbJuridicoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbJuridico);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Fornecedor juridico selecionado");

        txtForneceJuri.setEditable(false);
        txtForneceJuri.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscaJuridico)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                    .addComponent(txtForneceJuri)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel1))
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscaJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(11, 11, 11)
                .addComponent(txtForneceJuri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/selecionarMenu.png"))); // NOI18N
        jMenu1.setText("Selecionar fornecedor juridico");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/sairMenu.png"))); // NOI18N
        jMenu2.setText("Edit");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscaJuridicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaJuridicoKeyReleased
        if(txtBuscaJuridico.getText().equals("")){
            PreencherTabelaJuridico();
        }else{
            PreencherTabelaJuridicoDigita();
        }
    }//GEN-LAST:event_txtBuscaJuridicoKeyReleased

    private void tbJuridicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbJuridicoMouseClicked
        int linha_select = tbJuridico.getSelectedRow();
        // cod_produto = (int) jTable1.getValueAt(linha_select, 0);
        codigoJuridico = (Integer.parseInt((String) tbJuridico.getValueAt(linha_select, 0).toString()));
        nomeJuridico = (String) tbJuridico.getValueAt(linha_select, 1);
        TelJuridico = (String) tbJuridico.getValueAt(linha_select, 2);
        CelJuridico = (String) tbJuridico.getValueAt(linha_select, 3);
        enderecoJuridico = (String) tbJuridico.getValueAt(linha_select, 4);
        bairroJuridico = (String) tbJuridico.getValueAt(linha_select, 5);
        cepJuridico = tbJuridico.getValueAt(linha_select, 6).toString();
        cnpj = tbJuridico.getValueAt(linha_select, 7).toString();
        imgJuridico = (String) tbJuridico.getValueAt(linha_select, 8);
        statusJuridico = (String) tbJuridico.getValueAt(linha_select,9);
        txtForneceJuri.setText(nomeJuridico);
    }//GEN-LAST:event_tbJuridicoMouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
               if(getNomeJuridico().equals("")){
            JOptionPane.showMessageDialog(null,"Selecione o fornecedor","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }else{
            PreencherTabelaJuridico();
            dispose();
        }
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
          PreencherTabelaJuridico();
        RetornaVazio();
        dispose();
    }//GEN-LAST:event_jMenu2MouseClicked
 public void PreencherTabelaJuridicoDigita() {
       String sql = ("select * from fornecedorjuridico where nome like ? order by idfornjuridico asc");
        try {
            pst = conec.prepareStatement(sql);
            pst.setString(1,txtBuscaJuridico.getText()+"%");
            rs = pst.executeQuery();
        tbJuridico.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar a Tabela Juridico" + erro,"Aviso",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void RetornaVazio(){
    codigoJuridico=0;
    nomeJuridico="";
    TelJuridico="";;
    CelJuridico="";
    cnpj="";;
    cepJuridico="";
    enderecoJuridico="";
    bairroJuridico="";
    statusJuridico="";
    imgJuridico="";
    }
    public void PreencherTabelaJuridico() {
    String sql = ("select * from fornecedorjuridico order by idfornjuridico asc");
        try {
            pst = conec.prepareStatement(sql);
            rs = pst.executeQuery();
          tbJuridico.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar a Tabela Juridico" + erro,"Aviso",JOptionPane.ERROR_MESSAGE);
        }
    }
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
            java.util.logging.Logger.getLogger(frmBuscarForneJuridico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmBuscarForneJuridico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmBuscarForneJuridico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmBuscarForneJuridico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmBuscarForneJuridico dialog = new frmBuscarForneJuridico(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbJuridico;
    private javax.swing.JTextField txtBuscaJuridico;
    private javax.swing.JTextField txtForneceJuri;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the conec
     */
    public Connection getConec() {
        return conec;
    }

    /**
     * @return the pst
     */
    public PreparedStatement getPst() {
        return pst;
    }

    /**
     * @return the rs
     */
    public ResultSet getRs() {
        return rs;
    }

    /**
     * @return the codigoJuridico
     */
    public int getCodigoJuridico() {
        return codigoJuridico;
    }

    /**
     * @return the nomeJuridico
     */
    public String getNomeJuridico() {
        return nomeJuridico;
    }

    /**
     * @return the TelJuridico
     */
    public String getTelJuridico() {
        return TelJuridico;
    }

    /**
     * @return the CelJuridico
     */
    public String getCelJuridico() {
        return CelJuridico;
    }

    /**
     * @return the cepJuridico
     */
    public String getCepJuridico() {
        return cepJuridico;
    }

    /**
     * @return the enderecoJuridico
     */
    public String getEnderecoJuridico() {
        return enderecoJuridico;
    }

    /**
     * @return the bairroJuridico
     */
    public String getBairroJuridico() {
        return bairroJuridico;
    }

    /**
     * @return the statusJuridico
     */
    public String getStatusJuridico() {
        return statusJuridico;
    }

    /**
     * @return the imgJuridico
     */
    public String getImgJuridico() {
        return imgJuridico;
    }
    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

   
}