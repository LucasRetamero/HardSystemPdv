/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import DAO.Conexao;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

/**
 *
 * @author felipe
 */
public class ajusteEstoque extends javax.swing.JInternalFrame {

    Connection conec = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    int converteint;
    int converteintdois;
    int estAtual, estLancamento, estAjustado;
    ButtonGroup objBotao;
    public ajusteEstoque() throws SQLException {
        initComponents();
        objBotao = new ButtonGroup();
        objBotao.add(rbCodBarras);
        objBotao.add(rbDescricao);
        try {
            conec = Conexao.conecta();
            preencher_jtable();
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ajusteEstoque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void preencher_jtable() {
        String sql = ("Select * from produtos");
        try {
            pst = conec.prepareStatement(sql);
            rs = pst.executeQuery();
            tbProdutos.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar Jtable " + erro);
        }
    }
    
     public void Limpar(){
     txtID.setText("");
     txtDescricao.setText("");
     txtQuantidade.setText("");
     txtBusca.setText("");
     }
     public void Tratamento(int x){
     if(txtID.getText().equals("")){
     JOptionPane.showMessageDialog(null,"Selecione o produto!","Aviso",JOptionPane.INFORMATION_MESSAGE);
     }else{
     switch(x){
         case 1:
             if(txtDescricao.getText().equals("") || txtQuantidade.getText().equals("")){
             JOptionPane.showMessageDialog(null,"Campos vazios!","Aviso",JOptionPane.INFORMATION_MESSAGE);
             }else{
            if(JOptionPane.showConfirmDialog(null,"Deseja alterar produto?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
             Editar();
             preencher_jtable();
             Limpar();
             }
             }
             break;
         case 2:
             if(JOptionPane.showConfirmDialog(null,"Deseja remover o produto?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
             Delete();
             preencher_jtable();
             Limpar();
             }
     }
     }
     }
      public void Delete() {
        String sql = ("delete from produtos where id_produto = ?");
        try {
            pst = conec.prepareStatement(sql);
           pst.setInt(1,Integer.parseInt(txtID.getText()));
           if(pst.executeUpdate() > 0){
           JOptionPane.showMessageDialog(null,"Produto deletado!","Aviso",JOptionPane.INFORMATION_MESSAGE);
           } 
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar Jtable " + erro);
        }
    }
      public void Editar() {
        String sql = ("update produtos set descricao = ?,quantidade=? where id_produto = ?");
        try {
            pst = conec.prepareStatement(sql);
            pst.setString(1,txtDescricao.getText());
            pst.setString(2,txtQuantidade.getText());
            pst.setInt(3,Integer.parseInt(txtID.getText()));
           if(pst.executeUpdate() > 0){
           JOptionPane.showMessageDialog(null,"Produto alterado!","Aviso",JOptionPane.INFORMATION_MESSAGE);
           } 
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar Jtable " + erro);
        }
    }
     public void preencher_jtableDigitaDescricao() {
        String sql = ("Select * from produtos where descricao like ?");
        try {
            pst = conec.prepareStatement(sql);
            pst.setString(1,txtBusca.getText()+"%");
            rs = pst.executeQuery();
            tbProdutos.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar Jtable " + erro);
        }
    }
     public void preencher_jtableDigitaCodBarra() {
        String sql = ("Select * from produtos where codbarras like ?");
        try {
            pst = conec.prepareStatement(sql);
            pst.setString(1,txtBusca.getText()+"%");
            rs = pst.executeQuery();
            tbProdutos.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar Jtable " + erro);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbProdutos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnLimpar1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        rbCodBarras = new javax.swing.JCheckBox();
        rbDescricao = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Ajuste de Estoque");

        tbProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Cod.Barras", "Descrição", "Qtde", "Estoque Minimo", "Estoque Médio", "Estoque Ideal"
            }
        ));
        tbProdutos.getTableHeader().setReorderingAllowed(false);
        tbProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbProdutos);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Pesquisa");

        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusca)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Box-16.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Eraser-16.png"))); // NOI18N
        btnLimpar.setText("limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        btnLimpar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Cancel-16.png"))); // NOI18N
        btnLimpar1.setText("Deletar");
        btnLimpar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpar1ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Edit Image-16.png"))); // NOI18N
        jButton1.setText("Consultar Lista");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnEditar, btnLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLimpar)
                        .addComponent(btnLimpar1)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rbCodBarras.setText("Cod.Barras");
        rbCodBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCodBarrasActionPerformed(evt);
            }
        });

        rbDescricao.setText("Descrição");

        jLabel1.setText("ID");

        txtID.setEditable(false);
        txtID.setBackground(new java.awt.Color(153, 153, 153));
        txtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIDKeyReleased(evt);
            }
        });

        txtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescricaoKeyReleased(evt);
            }
        });

        jLabel5.setText("Descrição");

        txtQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantidadeKeyReleased(evt);
            }
        });

        jLabel7.setText("Quantidade:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbCodBarras)
                            .addComponent(rbDescricao))
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbCodBarras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbDescricao)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyPressed
 
    }//GEN-LAST:event_txtBuscaKeyPressed

    private void txtBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyReleased
        txtBusca.setText(txtBusca.getText().toUpperCase());
        if(rbCodBarras.isSelected() == true){
        preencher_jtableDigitaCodBarra();
        }else if(rbDescricao.isSelected() == true){
        preencher_jtableDigitaDescricao();
        }else{
        JOptionPane.showMessageDialog(null,"É necessario selecionar o tipo da pesquisa!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_txtBuscaKeyReleased

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Tratamento(1);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
       Limpar();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void rbCodBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCodBarrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbCodBarrasActionPerformed

    private void tbProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProdutosMouseClicked
       txtID.setText(tbProdutos.getValueAt(tbProdutos.getSelectedRow(),0).toString());
       txtDescricao.setText(tbProdutos.getValueAt(tbProdutos.getSelectedRow(),2).toString());
       txtQuantidade.setText(tbProdutos.getValueAt(tbProdutos.getSelectedRow(),6).toString());
    }//GEN-LAST:event_tbProdutosMouseClicked

    private void txtDescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyReleased
        txtDescricao.setText(txtDescricao.getText().toUpperCase());
        txtDescricao.setText(txtDescricao.getText().replaceAll("[^A-Z]", ""));
    }//GEN-LAST:event_txtDescricaoKeyReleased

    private void txtIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyReleased
        txtID.setText(txtID.getText().replaceAll("[^0-9]", ""));
    }//GEN-LAST:event_txtIDKeyReleased

    private void txtQuantidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeKeyReleased
       txtQuantidade.setText(txtQuantidade.getText().replaceAll("[^0-9]",""));
    }//GEN-LAST:event_txtQuantidadeKeyReleased

    private void btnLimpar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpar1ActionPerformed
        Tratamento(2);
    }//GEN-LAST:event_btnLimpar1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        frmConsultandoTodosProdutos obj = new frmConsultandoTodosProdutos(null, closable);
        obj.setTitle("Gerencia de consulta de produtos");
        obj.setLocationRelativeTo(null);
        obj.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnLimpar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox rbCodBarras;
    private javax.swing.JCheckBox rbDescricao;
    private javax.swing.JTable tbProdutos;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
