package layoutInterno;

import DAO.Conexao;
import com.sun.glass.events.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import layoutCadastro.frmCadFornecedor;
import net.proteanit.sql.DbUtils;

public class buscaFornecedor extends javax.swing.JDialog {

    Connection conec = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    private int codigoFisico,codigoJuridico;
    private String  nomeFisico,nomeJuridico;
    private String  telFisico,TelJuridico;
    private String  celFisico,CelJuridico;
    private String  cpf,cnpj,cepFisico,cepJuridico;
    private String enderecoFisico,enderecoJuridico;
    private String bairroFisico,bairroJuridico;
    private String statusFisico,statusJuridico;
    private String imgFisico,imgJuridico;
    
    
    

    public buscaFornecedor(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        try {
            conec = Conexao.conecta();
            PreencherTabelaFisico();
            PreencherTabelaJuridico();
            RetornaVazio();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(buscaEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void RetornaVazio(){
    codigoFisico=0;
    codigoJuridico=0;
    nomeFisico="";
    nomeJuridico="";
    telFisico="";
    TelJuridico="";;
    celFisico="";
    CelJuridico="";
    cpf="";
    cnpj="";
    cepFisico="";
    cepJuridico="";
    enderecoFisico="";
    enderecoJuridico="";
    bairroFisico="";
    bairroJuridico="";
    statusFisico="";
    statusJuridico="";
    imgFisico="";
    imgJuridico="";
    }
    public void PreencherTabelaFisico() {
           String sql = ("select * from fornecedorfisico order by idfornefisico asc");
        try {
            pst = conec.prepareStatement(sql);
            rs = pst.executeQuery();
             tbFisica.setModel(DbUtils.resultSetToTableModel(rs));
            /*while (rs.next()){
                modelo.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)});
            }*/
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar a Tabela Fisica " + erro,"Aviso",JOptionPane.ERROR_MESSAGE);
        }
    }
     public void PreencherTabelaFisicoDigita() {
           String sql = ("select * from fornecedorfisico where nome like ?");
        try {
            pst = conec.prepareStatement(sql);
            pst.setString(1,txtBuscaFisica.getText()+"%");
            rs = pst.executeQuery();
            tbFisica.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar a Tabela Fisica " + erro,"Aviso",JOptionPane.ERROR_MESSAGE);
        }
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtBuscaFisica = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFisica = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnGravar = new javax.swing.JButton();
        btnGravar1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtBuscaJuridico = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbJuridico = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btnGravar2 = new javax.swing.JButton();
        btnGravar3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa por Nome do Fornecedor Fisico(Pessoa)"));

        txtBuscaFisica.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaFisicaKeyReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Search-20.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tbFisica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbFisica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFisicaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbFisica);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Exit-16.png"))); // NOI18N
        btnGravar.setText("Sair");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnGravar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Todo List-16.png"))); // NOI18N
        btnGravar1.setText("Selecionar");
        btnGravar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGravar1)
                .addGap(18, 18, 18)
                .addComponent(btnGravar)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGravar)
                    .addComponent(btnGravar1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pessoa Fisica", jPanel3);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa por Nome do Fornecedor Juridico(Empresa)"));

        txtBuscaJuridico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaJuridicoKeyReleased(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Search-20.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscaJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscaJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGravar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Exit-16.png"))); // NOI18N
        btnGravar2.setText("Sair");
        btnGravar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravar2ActionPerformed(evt);
            }
        });

        btnGravar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Todo List-16.png"))); // NOI18N
        btnGravar3.setText("Selecionar");
        btnGravar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravar3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(btnGravar3)
                .addGap(18, 18, 18)
                .addComponent(btnGravar2)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGravar2)
                    .addComponent(btnGravar3))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 353, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Pessoa Juridica", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscaFisicaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaFisicaKeyReleased
       if(txtBuscaFisica.getText().equals("")){ 
           PreencherTabelaFisico();    
       }else{
           PreencherTabelaFisicoDigita();
       }
    }//GEN-LAST:event_txtBuscaFisicaKeyReleased

    private void tbFisicaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFisicaMouseClicked
            int linha_select = tbFisica.getSelectedRow();
            // cod_produto = (int) jTable1.getValueAt(linha_select, 0);
            codigoFisico = (Integer.parseInt((String) tbFisica.getValueAt(linha_select, 0).toString()));
            nomeFisico = (String) tbFisica.getValueAt(linha_select, 1);
            telFisico = (String) tbFisica.getValueAt(linha_select, 2);
            celFisico = (String) tbFisica.getValueAt(linha_select, 3);
            cpf =  tbFisica.getValueAt(linha_select, 4).toString();
            enderecoFisico = (String) tbFisica.getValueAt(linha_select, 5);
            bairroFisico = (String) tbFisica.getValueAt(linha_select, 6);
            cepFisico = tbFisica.getValueAt(linha_select, 7).toString();
            imgFisico = (String) tbFisica.getValueAt(linha_select, 8);
            statusFisico = (String) tbFisica.getValueAt(linha_select, 9);
    }//GEN-LAST:event_tbFisicaMouseClicked

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
         PreencherTabelaFisico();
            PreencherTabelaJuridico();
            RetornaVazio();
        dispose();
    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnGravar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravar1ActionPerformed
       if(getNomeFisico().equals("")){
       JOptionPane.showMessageDialog(null,"Selecione o fornecedor","Aviso",JOptionPane.INFORMATION_MESSAGE);
       }else{
           int linha_select = tbFisica.getSelectedRow();
            // cod_produto = (int) jTable1.getValueAt(linha_select, 0);
            codigoFisico = (Integer.parseInt((String) tbFisica.getValueAt(linha_select, 0).toString()));
            nomeFisico = tbFisica.getValueAt(linha_select, 1).toString();
            telFisico = (String) tbFisica.getValueAt(linha_select, 2);
            celFisico = (String) tbFisica.getValueAt(linha_select, 3);
            cpf =  tbFisica.getValueAt(linha_select, 4).toString();
            enderecoFisico = (String) tbFisica.getValueAt(linha_select, 5);
            bairroFisico = (String) tbFisica.getValueAt(linha_select, 6);
            cepFisico = tbFisica.getValueAt(linha_select, 7).toString();
            imgFisico = (String) tbFisica.getValueAt(linha_select, 8);
            statusFisico = (String) tbFisica.getValueAt(linha_select, 9);
            PreencherTabelaFisico();
            PreencherTabelaJuridico();
            dispose();
       }
    }//GEN-LAST:event_btnGravar1ActionPerformed

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
    }//GEN-LAST:event_tbJuridicoMouseClicked

    private void btnGravar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravar2ActionPerformed
          PreencherTabelaFisico();
          PreencherTabelaJuridico();
          RetornaVazio();
        dispose();
    }//GEN-LAST:event_btnGravar2ActionPerformed

    private void btnGravar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravar3ActionPerformed
 if(getNomeJuridico().equals("")){
       JOptionPane.showMessageDialog(null,"Selecione o fornecedor","Aviso",JOptionPane.INFORMATION_MESSAGE);
       }else{
       PreencherTabelaFisico();
       PreencherTabelaJuridico();
        dispose();
       }
    }//GEN-LAST:event_btnGravar3ActionPerformed

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
            java.util.logging.Logger.getLogger(buscaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(buscaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(buscaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(buscaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    buscaFornecedor dialog = new buscaFornecedor(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(buscaFornecedor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnGravar1;
    private javax.swing.JButton btnGravar2;
    private javax.swing.JButton btnGravar3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbFisica;
    private javax.swing.JTable tbJuridico;
    private javax.swing.JTextField txtBuscaFisica;
    private javax.swing.JTextField txtBuscaJuridico;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the codigoFisico
     */
    public int getCodigoFisico() {
        return codigoFisico;
    }

    /**
     * @return the codigoJuridico
     */
    public int getCodigoJuridico() {
        return codigoJuridico;
    }

    /**
     * @return the nomeFisico
     */
    public String getNomeFisico() {
        return nomeFisico;
    }

    /**
     * @return the nomeJuridico
     */
    public String getNomeJuridico() {
        return nomeJuridico;
    }

    /**
     * @return the telFisico
     */
    public String getTelFisico() {
        return telFisico;
    }

    /**
     * @return the TelJuridico
     */
    public String getTelJuridico() {
        return TelJuridico;
    }

    /**
     * @return the celFisico
     */
    public String getCelFisico() {
        return celFisico;
    }

    /**
     * @return the CelJuridico
     */
    public String getCelJuridico() {
        return CelJuridico;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @return the cepFisico
     */
    public String getCepFisico() {
        return cepFisico;
    }

    /**
     * @return the cepJuridico
     */
    public String getCepJuridico() {
        return cepJuridico;
    }

    /**
     * @return the enderecoFisico
     */
    public String getEnderecoFisico() {
        return enderecoFisico;
    }

    /**
     * @return the enderecoJuridico
     */
    public String getEnderecoJuridico() {
        return enderecoJuridico;
    }

    /**
     * @return the bairroFisico
     */
    public String getBairroFisico() {
        return bairroFisico;
    }

    /**
     * @return the bairroJuridico
     */
    public String getBairroJuridico() {
        return bairroJuridico;
    }

    /**
     * @return the statusFisico
     */
    public String getStatusFisico() {
        return statusFisico;
    }

    /**
     * @return the statusJuridico
     */
    public String getStatusJuridico() {
        return statusJuridico;
    }

    /**
     * @return the imgFisico
     */
    public String getImgFisico() {
        return imgFisico;
    }

    /**
     * @return the imgJuridico
     */
    public String getImgJuridico() {
        return imgJuridico;
    }

  
}
