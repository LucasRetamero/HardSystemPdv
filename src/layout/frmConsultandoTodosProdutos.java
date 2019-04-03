/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import javax.swing.JOptionPane;
import DAO.Conexao;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Su
 */
public class frmConsultandoTodosProdutos extends javax.swing.JDialog {

    /**
     * Creates new form frmConsultandoTodosProdutos
     */
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    public frmConsultandoTodosProdutos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            connection = Conexao.conecta();
            BuscarTodoProdutos();
            ColetarTodosInfo();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmConsultandoTodosProdutos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frmConsultandoTodosProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
       FicaInvisivel();
       URL url = this.getClass().getResource("/imagens/IconePequeno.png");  
       Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);  
       this.setIconImage(iconeTitulo);
    }
    private void FicaInvisivel(){
     cbFazerPesquisa.setVisible(false);
     lblPesquisa.setVisible(false);
    }
    private void FicaVisivel(){
     cbFazerPesquisa.setVisible(true);
     lblPesquisa.setVisible(true);
    }
    private void PegarTodosGrupos(){
    cbFazerPesquisa.removeAllItems();
    cbFazerPesquisa.addItem("Selecionar");
    lblPesquisa.setText("Selecione o grupo:");
    try{
    pst = connection.prepareStatement("select grupo from grupo");
    rs = pst.executeQuery();
    while(rs.next()){
  cbFazerPesquisa.addItem(rs.getString(1));
    }
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
    }
    private void PegarTodosJuridicos(){
    cbFazerPesquisa.removeAllItems();;
    cbFazerPesquisa.addItem("Selecionar");
    lblPesquisa.setText("Selecione o Juridico:");
    try{
    pst = connection.prepareStatement("select nome from fornecedorjuridico");
    rs = pst.executeQuery();
    while(rs.next()){
  cbFazerPesquisa.addItem(rs.getString(1));
    }
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
    }
     private void PegarTodosFisico(){
      cbFazerPesquisa.removeAllItems();;
    cbFazerPesquisa.addItem("Selecionar");
    lblPesquisa.setText("Selecione o Fisico:");
    try{
    pst = connection.prepareStatement("select nome from fornecedorfisico");
    rs = pst.executeQuery();
    while(rs.next()){
  cbFazerPesquisa.addItem(rs.getString(1));
    }
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
    }
    private void BuscarTodoProdutos(){
    try{
    pst = connection.prepareStatement("select * from produtos");
    rs = pst.executeQuery();
    tbProdutos.setModel(DbUtils.resultSetToTableModel(rs));
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
    }
    private void BuscarSemCODBARRAS(){
    try{
    pst = connection.prepareStatement("select * from produtos where codbarras like ''");
    rs = pst.executeQuery();
    tbProdutos.setModel(DbUtils.resultSetToTableModel(rs));
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
    }
    private void BuscarNomeGRUPO(){
    try{
    pst = connection.prepareStatement("select * from produtos where grupoprod like ?");
    pst.setString(1,cbFazerPesquisa.getSelectedItem()+"%");
    rs = pst.executeQuery();
    tbProdutos.setModel(DbUtils.resultSetToTableModel(rs));
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
    }
     private void BuscarStatusATIVADOS(){
    try{
    pst = connection.prepareStatement("select * from produtos where status like 'S'");
    rs = pst.executeQuery();
    tbProdutos.setModel(DbUtils.resultSetToTableModel(rs));
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
    }
      private void BuscarStatusDESATIVADOS(){
    try{
    pst = connection.prepareStatement("select * from produtos where status like 'N'");
    rs = pst.executeQuery();
    tbProdutos.setModel(DbUtils.resultSetToTableModel(rs));
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
    }
    private void BuscarNomeFISICOAndJURIDICO(){
    try{
    pst = connection.prepareStatement("select * from produtos where fornecedor like ?");
    pst.setString(1,cbFazerPesquisa.getSelectedItem()+"%");
    rs = pst.executeQuery();
    tbProdutos.setModel(DbUtils.resultSetToTableModel(rs));
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
    }
    private void ColetarTodosInfo(){
    //Buscar Total Fornecedor
     try{
    pst = connection.prepareStatement(" select count(fornecedor) from produtos");
    rs = pst.executeQuery();
    if(rs.next())
    lblTotalFornecedores.setText(rs.getString(1));
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
     //Valor total em custo
     try{
    pst = connection.prepareStatement("select sum(valorcusto) from produtos");
    rs = pst.executeQuery();
    if(rs.next())
    lblTotalCusto.setText("R$ "+rs.getString(1).replace(".",","));
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
     //Quantidade em produtos
      try{
    pst = connection.prepareStatement("select count(id_produto) from produtos");
    rs = pst.executeQuery();
    if(rs.next())
    lblQuantidadeProduto.setText(rs.getString(1));
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
      //Valor total em lucro
        try{
    pst = connection.prepareStatement("select sum(valorlucro) from produtos");
    rs = pst.executeQuery();
    if(rs.next())
    lblTotalLucro.setText("R$ "+rs.getString(1).replace(".",","));
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
        //Tota produto ativado
          try{
    pst = connection.prepareStatement("select count(status) from produtos where status like 'S'");
    rs = pst.executeQuery();
    if(rs.next())
    lblTotalProdutosAtivados.setText(rs.getString(1));
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
    //Total produtos desativados
    try{
    pst = connection.prepareStatement("select count(status) from produtos where status like 'N'");
    rs = pst.executeQuery();
    if(rs.next())
    lbltotalProdutosDesativados.setText(rs.getString(1));
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbProdutos = new javax.swing.JTable();
        cbFazerPesquisa = new javax.swing.JComboBox<>();
        lblPesquisa = new javax.swing.JLabel();
        InfoRegistros = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblQuantidadeProduto = new javax.swing.JTextField();
        lblTotalFornecedores = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblTotalProdutosAtivados = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbltotalProdutosDesativados = new javax.swing.JTextField();
        lblTotalCusto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lblTotalLucro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbTipoPesquisa = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tbProdutos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbProdutos);

        cbFazerPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar a pesquisa", "Produtos sem codigo de barra" }));
        cbFazerPesquisa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbFazerPesquisaItemStateChanged(evt);
            }
        });

        lblPesquisa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPesquisa.setText("AQUI VAI TEXTO");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Total em Produtos:");

        lblQuantidadeProduto.setEditable(false);
        lblQuantidadeProduto.setBackground(new java.awt.Color(255, 255, 255));

        lblTotalFornecedores.setEditable(false);
        lblTotalFornecedores.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Total em Fornecedores:");

        lblTotalProdutosAtivados.setEditable(false);
        lblTotalProdutosAtivados.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Produtos Ativados:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Produtos Desativados:");

        lbltotalProdutosDesativados.setEditable(false);
        lbltotalProdutosDesativados.setBackground(new java.awt.Color(255, 255, 255));

        lblTotalCusto.setEditable(false);
        lblTotalCusto.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Total Custo:");

        lblTotalLucro.setEditable(false);
        lblTotalLucro.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Total Lucro:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuantidadeProduto)
                    .addComponent(lblTotalFornecedores, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(lblTotalProdutosAtivados, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(lbltotalProdutosDesativados, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(lblTotalCusto, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(lblTotalLucro, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalProdutosAtivados, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltotalProdutosDesativados, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalLucro, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        InfoRegistros.addTab("Informaçâo dos registros", jPanel6);

        cbTipoPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar a pesquisa", "Produtos sem codigo de barra", "Pelo nome do grupo", "Pelo nome Fisico", "Pelo nome Juridico", "Pelo status Ativados", "Pelo status Desativados" }));
        cbTipoPesquisa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoPesquisaItemStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Selecione o tipo da pesquisa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbTipoPesquisa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPesquisa)
                                    .addComponent(cbFazerPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(InfoRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPesquisa)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFazerPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(InfoRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbTipoPesquisaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoPesquisaItemStateChanged
      if(cbTipoPesquisa.getSelectedIndex() == 0){//Selecionar a pesquisa
      BuscarTodoProdutos();
      FicaInvisivel();
      }else if(cbTipoPesquisa.getSelectedIndex() == 1){//Produtos sem codigo de barra
      FicaInvisivel();
      BuscarSemCODBARRAS();
      }else if(cbTipoPesquisa.getSelectedIndex() == 2){//Pelo nome do grupo
      PegarTodosGrupos();
      FicaVisivel();
      }else if(cbTipoPesquisa.getSelectedIndex() == 3){//Pelo nome Fisico
      PegarTodosFisico();
      FicaVisivel();
      }else if(cbTipoPesquisa.getSelectedIndex() == 4){//Pelo nome Juridico
      PegarTodosJuridicos();
      FicaVisivel();
      }else if(cbTipoPesquisa.getSelectedIndex() == 5){//Pelo status Ativado
      BuscarStatusATIVADOS();
      FicaInvisivel();
      }else if(cbTipoPesquisa.getSelectedIndex() == 6){//Pelo status Desativado
      BuscarStatusDESATIVADOS();
      FicaInvisivel();
      }
      
    }//GEN-LAST:event_cbTipoPesquisaItemStateChanged

    private void cbFazerPesquisaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbFazerPesquisaItemStateChanged
        if(cbFazerPesquisa.getSelectedIndex() == 0){
       BuscarTodoProdutos();
        }else if(cbTipoPesquisa.getSelectedIndex() == 2){//Pelo nome do grupo
      BuscarNomeGRUPO();
      }else if(cbTipoPesquisa.getSelectedIndex() == 3 || cbTipoPesquisa.getSelectedIndex() == 4){//Pelo nome Fisico || Pelo nome Juridico
      BuscarNomeFISICOAndJURIDICO();
      } 
    }//GEN-LAST:event_cbFazerPesquisaItemStateChanged

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
            java.util.logging.Logger.getLogger(frmConsultandoTodosProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmConsultandoTodosProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmConsultandoTodosProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmConsultandoTodosProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmConsultandoTodosProdutos dialog = new frmConsultandoTodosProdutos(new javax.swing.JFrame(), true);
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
    private javax.swing.JTabbedPane InfoRegistros;
    private javax.swing.JComboBox<String> cbFazerPesquisa;
    private javax.swing.JComboBox<String> cbTipoPesquisa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JTextField lblQuantidadeProduto;
    private javax.swing.JTextField lblTotalCusto;
    private javax.swing.JTextField lblTotalFornecedores;
    private javax.swing.JTextField lblTotalLucro;
    private javax.swing.JTextField lblTotalProdutosAtivados;
    private javax.swing.JTextField lbltotalProdutosDesativados;
    private javax.swing.JTable tbProdutos;
    // End of variables declaration//GEN-END:variables
}
