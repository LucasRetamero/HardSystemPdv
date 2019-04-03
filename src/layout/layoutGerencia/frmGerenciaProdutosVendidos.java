/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout.layoutGerencia;

import DAO.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Su
 */
public class frmGerenciaProdutosVendidos extends javax.swing.JInternalFrame {

     private Connection connection;
      private PreparedStatement pst;
      private ResultSet rs;
    public frmGerenciaProdutosVendidos() {
        initComponents();
        try {
             connection = Conexao.conecta();
             BuscarDados();
             BuscarVendasRealizadas();
         }catch(SQLException ex) {
             Logger.getLogger(frmGerenciaVendas.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(frmGerenciaVendas.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
       private void BuscarDados(){
    try{
    pst = connection.prepareStatement("select * from item_venda");
    rs = pst.executeQuery();
    tbVendas.setModel(DbUtils.resultSetToTableModel(rs));
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);    
    }
    }
   private void BuscarVendasRealizadas(){
    try{
    pst = connection.prepareStatement("select codigo from venda");
    rs = pst.executeQuery();
    cbVendas.removeAllItems();
    cbVendas.addItem("Selecionar");
    while(rs.next()){
    cbVendas.addItem(rs.getString(1));
    }
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);    
    }
    }
    private void BuscarDadosData(){
    try{
    pst = connection.prepareStatement("select * from item_venda where cod_venda = ?");
    pst.setInt(1,Integer.parseInt(cbVendas.getSelectedItem().toString()));
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
     JOptionPane.showMessageDialog(null,"Produtos vendidos deletados!","Aviso",JOptionPane.INFORMATION_MESSAGE);
     DeletaVendaSelecionada();
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);    
    }
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
    cbVendas.setSelectedIndex(0);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbVendas = new javax.swing.JComboBox<>();
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
        setTitle("Gerencia de produtos vendidos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Pesquise pelo codigo da venda");

        cbVendas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(cbVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(0, 317, Short.MAX_VALUE))
                    .addComponent(txtVendaSelecionado))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtVendaSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
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

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/CodigoMenu.png"))); // NOI18N
        jMenuItem1.setText("Codigo da venda");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/produtosMenu.png"))); // NOI18N
        jMenuItem2.setText("Todos os produtos");
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
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbVendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVendasMouseClicked
        txtVendaSelecionado.setText(tbVendas.getModel().getValueAt(tbVendas.getSelectedRow(),1).toString());
    }//GEN-LAST:event_tbVendasMouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
             if(txtVendaSelecionado.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Selecione algum produto vendido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }else{
            if(JOptionPane.showConfirmDialog(null,"Deletar venda?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                DeletarSelecionado();
                Limpar();
                BuscarDados();
            }
        }
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
          if(cbVendas.getSelectedIndex() !=0){
            BuscarDadosData();
            Limpar();
        }else{
        JOptionPane.showMessageDialog(null,"Selecione o codigo da venda!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        BuscarDados();
        Limpar();
    }//GEN-LAST:event_jMenuItem2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbVendas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbVendas;
    private javax.swing.JTextField txtVendaSelecionado;
    // End of variables declaration//GEN-END:variables
}
