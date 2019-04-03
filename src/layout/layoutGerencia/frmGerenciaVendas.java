/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout.layoutGerencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import DAO.Conexao;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Su
 */
public class frmGerenciaVendas extends javax.swing.JInternalFrame {

     private Connection connection;
      private PreparedStatement pst;
      private ResultSet rs;
    public frmGerenciaVendas() {
        initComponents();
         try {
             connection = Conexao.conecta();
             BuscarDados();
         }catch(SQLException ex) {
             Logger.getLogger(frmGerenciaVendas.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(frmGerenciaVendas.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    private void BuscarDados(){
    try{
    pst = connection.prepareStatement("select * from venda");
    rs = pst.executeQuery();
    tbVendas.setModel(DbUtils.resultSetToTableModel(rs));
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);    
    }
    }
     private void BuscarDadosData(){
         SimpleDateFormat objData = new SimpleDateFormat("yyyy-MM-dd");
         try{
    pst = connection.prepareStatement("select * from venda where data_venda >= ? and data_venda <= ?");
    pst.setDate(1,java.sql.Date.valueOf(objData.format(dcDataInicio.getDate())));
    pst.setDate(2,java.sql.Date.valueOf(objData.format(dcDataFim.getDate())));
    rs = pst.executeQuery();
    tbVendas.setModel(DbUtils.resultSetToTableModel(rs));
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);    
    }
    }
 
     private void DeletarSelecionado(){
         try{
    pst = connection.prepareStatement("delete from item_venda where cod_venda = ?");
    pst.setInt(1,Integer.parseInt(txtVendaSelecionado.getText()));
    pst.executeUpdate();
     JOptionPane.showMessageDialog(null,"Vendas deletadas!","Aviso",JOptionPane.INFORMATION_MESSAGE);
     DeletaVendaSelecionada();
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);    
    }
    BuscarDados();
    }
     private void DeletaVendaSelecionada(){
      try{
    pst = connection.prepareStatement("delete from venda where codigo = ?");
    pst.setInt(1,Integer.parseInt(txtVendaSelecionado.getText()));
    pst.executeUpdate();
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);    
    }
     }
   
    private void Limpar(){
    dcDataInicio.setDate(null);
    dcDataFim.setDate(null);
    txtVendaSelecionado.setText("");
    }
    private void PegarTodosDadosProdutosVendidos(){
    int quantidadeTotal;
    int soma=1;
        try{
    pst = connection.prepareStatement("select count(codigo) from item_venda");
    if(pst.executeUpdate() > 0){
     quantidadeTotal = rs.getInt(1);
     if(quantidadeTotal > 0){
     while(soma <= quantidadeTotal){
     soma++;
    try{
    pst = connection.prepareStatement("delete from item_venda where codigo = ?");
    pst.setInt(1,soma);
    pst.executeUpdate();
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);    
    }
     }
     }
    }
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);    
    }
    JOptionPane.showMessageDialog(null,"Todas as vendas e produtos vendidos apagados!","Aviso",JOptionPane.INFORMATION_MESSAGE);
     PegarTodosDadosVendidos();
    }
    private void PegarTodosDadosVendidos(){
    int quantidadeTotal;
    int soma=1;
        try{
    pst = connection.prepareStatement("select count(codigo) from venda");
    if(pst.executeUpdate() > 0){
     quantidadeTotal = rs.getInt(1);
     if(quantidadeTotal > 0){
     while(soma <= quantidadeTotal){
     soma++;
    try{
    pst = connection.prepareStatement("delete from venda where codigo = ?");
    pst.setInt(1,soma);
    pst.executeUpdate();
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);    
    }
     }
     }
    }
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);    
    }
    JOptionPane.showMessageDialog(null,"Todas as vendas apagadas!","Aviso",JOptionPane.INFORMATION_MESSAGE);
    } 
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dcDataInicio = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dcDataFim = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbVendas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtVendaSelecionado = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setClosable(true);
        setTitle("Gerencia Venda");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Data inicial:");

        dcDataInicio.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Data Final:");

        dcDataFim.setBackground(new java.awt.Color(255, 255, 255));

        tbVendas.setModel(new javax.swing.table.DefaultTableModel(
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
        tbVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVendasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbVendas);

        jLabel3.setText("Venda selecionada:");

        txtVendaSelecionado.setEditable(false);
        txtVendaSelecionado.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(85, 85, 85)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dcDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtVendaSelecionado))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtVendaSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/deletarMenu.png"))); // NOI18N
        jMenu1.setText("Deletar");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/PesquisarMenu.png"))); // NOI18N
        jMenu2.setText("Pesquisar");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/dataMenu.png"))); // NOI18N
        jMenuItem1.setText("Data da venda");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/vendasTodasMenu.png"))); // NOI18N
        jMenuItem2.setText("Todas as vendas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 239, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 240, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbVendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVendasMouseClicked
       txtVendaSelecionado.setText(tbVendas.getModel().getValueAt(tbVendas.getSelectedRow(),0).toString());   
    }//GEN-LAST:event_tbVendasMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
          if(dcDataInicio.getDate() != null && dcDataFim.getDate() != null){
       BuscarDadosData();
       Limpar();
       }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
         BuscarDados();
        Limpar();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
         if(txtVendaSelecionado.getText().equals("")){
 JOptionPane.showMessageDialog(null,"Selecione a venda!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }else{
  if(JOptionPane.showConfirmDialog(null,"Deletar venda?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
   DeletarSelecionado();
   Limpar();
   }
 }
    }//GEN-LAST:event_jMenu1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dcDataFim;
    private com.toedter.calendar.JDateChooser dcDataInicio;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable tbVendas;
    private javax.swing.JTextField txtVendaSelecionado;
    // End of variables declaration//GEN-END:variables
}
