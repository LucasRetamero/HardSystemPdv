
package layout;

import DAO.Conexao;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import layoutInterno.buscarProdutosVenda;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import javax.swing.JComboBox;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import Controler.InfoUsuario;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
public class telaVenda extends javax.swing.JFrame{

    DefaultTableModel objjMyTable;
    buscarProdutosVenda  objProdutosVenda;
   JComboBox objListaPagamento;
    Connection conec;
    PreparedStatement pst;
    ResultSet rs;
    int guardarID;
    String objLista[];
    ArrayList lista = new ArrayList();
    String formaPag;
    int QuantidadeCal=0;
    int img = 0, verificaImg = 0;
    String guardaFoto;
    InfoUsuario objUsuario = new InfoUsuario();
    int opcao,opcaoTipo,valorID;
    double totalVashilame=0.00;
    JTextField txtValorVashilame;
    String codigoBarras;
    boolean verificaID = false;
    int cal=0;
    public telaVenda() {
        initComponents();
          try {
            conec = Conexao.conecta();
            ColetandoUltimaVenda();
            lvlIdProximVenda.setText(""+(guardarID+1));
             BuscarNomeTipoPagamento();
              //Definindo cada coluna da tabela e seu devido tamanho
        tbProdutos.getColumnModel().getColumn(0).setPreferredWidth(70);//Coluna para codigo
        tbProdutos.getColumnModel().getColumn(1).setPreferredWidth(250);//Coluna para Descricao
        tbProdutos.getColumnModel().getColumn(2).setPreferredWidth(80);//Coluna para v.unitario
        tbProdutos.getColumnModel().getColumn(3).setPreferredWidth(70);//Coluna para qtde
        tbProdutos.getColumnModel().getColumn(4).setPreferredWidth(70);
        tbProdutos.getColumnModel().getColumn(5).setPreferredWidth(150);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Aviso Inicio",JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(telaVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        objjMyTable = (DefaultTableModel) tbProdutos.getModel();
        objProdutosVenda = new buscarProdutosVenda(null, rootPaneCheckingEnabled);
       txtCodigo.requestFocus(); 
       txtValorVashilame = new JTextField();
       ImageIcon objCal = new ImageIcon(getClass().getResource("/imagens/Calculator-16.png"));
       btnCalcular.setIcon(new javax.swing.ImageIcon(objCal.getImage().getScaledInstance(btnCalcular.getWidth(),btnCalcular.getHeight(),Image.SCALE_DEFAULT)));
       // 
       DecimalFormat decimal = new DecimalFormat("#,###,###.00");
       NumberFormatter numFormatter = new NumberFormatter(decimal);
       numFormatter.setFormat(decimal);
       numFormatter.setAllowsInvalid(false);
       DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
       txtTrocoDigitar.setFormatterFactory(dfFactory);
       URL url = this.getClass().getResource("/imagens/IconePequeno.png");  
       Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);  
       this.setIconImage(iconeTitulo);
    }

    public void AlteraProdutoEdita(int pk,String quantidade){
    try{
    pst = conec.prepareStatement("select quantidade from produtos where id_produto = ?");
    pst.setInt(1,pk);
    rs = pst.executeQuery();
    if(rs.next()){
    cal = Integer.parseInt(rs.getString(1));
    cal-=Integer.parseInt(quantidade);
    try{
    pst = conec.prepareStatement("update produtos set quantidade = ? where id_produto = ?");
    pst.setString(1,String.valueOf(cal));
    pst.setInt(2, pk);
    pst.executeUpdate();
    cal = 0;
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
    }
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
   
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbProdutos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lvlIdProximVenda = new javax.swing.JLabel();
        lblTotalVenda = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTrocoDigitar = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        lblTroco = new javax.swing.JLabel();
        btnCalcular = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnListaProduto = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtValorUnitario = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        btnEnviarProduto = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lbFoto = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Vendas");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 204, 102));
        jScrollPane1.setForeground(new java.awt.Color(255, 204, 51));
        jScrollPane1.setAutoscrolls(true);

        tbProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descriçâo", "V.Unitario", "QTDE", "V.Total", "C.Barras"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbProdutos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 410, 250));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.setLayout(null);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("CUPOM");
        jPanel2.add(jLabel13);
        jLabel13.setBounds(10, 20, 80, 22);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("TOTAL");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(190, 20, 70, 22);

        lvlIdProximVenda.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lvlIdProximVenda.setText("000001");
        jPanel2.add(lvlIdProximVenda);
        lvlIdProximVenda.setBounds(100, 20, 66, 22);

        lblTotalVenda.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTotalVenda.setText("0");
        jPanel2.add(lblTotalVenda);
        lblTotalVenda.setBounds(270, 20, 140, 29);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Valor recebido:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 60, 110, 15);

        txtTrocoDigitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrocoDigitarActionPerformed(evt);
            }
        });
        txtTrocoDigitar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTrocoDigitarKeyPressed(evt);
            }
        });
        jPanel2.add(txtTrocoDigitar);
        txtTrocoDigitar.setBounds(10, 80, 90, 30);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("TROCO:");
        jPanel2.add(jLabel15);
        jLabel15.setBounds(190, 70, 80, 22);

        lblTroco.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTroco.setText("0");
        jPanel2.add(lblTroco);
        lblTroco.setBounds(270, 70, 140, 29);

        btnCalcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/calculadoraMenu.png"))); // NOI18N
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });
        jPanel2.add(btnCalcular);
        btnCalcular.setBounds(110, 70, 60, 40);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 410, 120));

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel8.setText("CODIGO");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        btnListaProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Search-16.png"))); // NOI18N
        btnListaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaProdutoActionPerformed(evt);
            }
        });

        jLabel10.setText("V.Unitario");

        txtValorUnitario.setEditable(false);
        txtValorUnitario.setBackground(new java.awt.Color(153, 153, 153));

        jLabel11.setText("QTDE");

        txtQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantidadeKeyReleased(evt);
            }
        });

        btnEnviarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/LadoDireitoMenu.png"))); // NOI18N
        btnEnviarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarProdutoActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/LimparPequeno.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnListaProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEnviarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListaProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnEnviarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        lbFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/LogoBolado.jpg"))); // NOI18N
        getContentPane().add(lbFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblDescricao.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblDescricao.setForeground(new java.awt.Color(255, 255, 255));
        lblDescricao.setText("D    E      S     C     R     I      C    A     O");
        getContentPane().add(lblDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 770, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/background_laranja.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -20, 850, 620));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/terminarVendaMenu.png"))); // NOI18N
        jMenu1.setText("Terminar Venda");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/cancelarVendaMenu.png"))); // NOI18N
        jMenu2.setText("Cancelar Venda");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(808, 563));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnListaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaProdutoActionPerformed
      objProdutosVenda.setLocationRelativeTo(null);
      objProdutosVenda.setVisible(true);
      ColetarTodosDadosProdutos();
    }//GEN-LAST:event_btnListaProdutoActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
      if(evt.getKeyCode() == KeyEvent.VK_F8){
      JOptionPane.showMessageDialog(null, "teste");
      }
    }//GEN-LAST:event_formKeyPressed

    private void btnEnviarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarProdutoActionPerformed
  if(txtCodigo.getText().toString().equals("")){
      JOptionPane.showMessageDialog(null,"Selecione o produtos antes!","Aviso",JOptionPane.INFORMATION_MESSAGE);
  }else if(txtQuantidade.getText().equals("")){
  JOptionPane.showMessageDialog(null,"Digite a quantidade!","Aviso",JOptionPane.INFORMATION_MESSAGE);
  }else{
        Object dados[] = new Object[6];
        dados[0] = objProdutosVenda.getIdProduto();
        dados[1] = lblDescricao.getText();
        dados[2] = txtValorUnitario.getText();
        dados[3] = txtQuantidade.getText();
        dados[4] = String.valueOf((Double.valueOf(txtValorUnitario.getText())* Double.valueOf(txtQuantidade.getText())));
        dados[5] = objProdutosVenda.getcodBarras();
        objjMyTable.addRow(dados);
        SomarTudo();
       txtCodigo.setText("");
      txtValorUnitario.setText("");
      txtQuantidade.setText("");
      lblDescricao.setText("D    E      S     C     R     I      C    A     O");
      ImageIcon pega = new ImageIcon(getClass().getResource("/foto/LogoBolado.jpg"));
    lbFoto.setIcon(pega);
      totalVashilame = 0.00;
      
  }
    }//GEN-LAST:event_btnEnviarProdutoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      if(JOptionPane.showConfirmDialog(null,"Cancelar produto?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) ==JOptionPane.YES_OPTION){
      txtCodigo.setText("");
      txtValorUnitario.setText("");
      txtQuantidade.setText("");
      lblDescricao.setText("D    E      S     C     R     I      C    A     O");
      ImageIcon pega = new ImageIcon(getClass().getResource("/foto/LogoBolado.jpg"));
      lbFoto.setIcon(pega);
       totalVashilame = 0.00;
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProdutosMouseClicked
        Removerlinha();
    }//GEN-LAST:event_tbProdutosMouseClicked

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
 if(evt.getKeyCode() == KeyEvent.VK_F8){
      objProdutosVenda.setLocationRelativeTo(null);
      objProdutosVenda.setVisible(true);
      ColetarTodosDadosProdutos();
}else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
     txtValorVashilame.setText(""+totalVashilame);
            if ("".equals(txtCodigo.getText())) {
                JOptionPane.showMessageDialog(null, "Entre com o codigo do Produto", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            } else {
                BuscarCodBarras();      
            }
     
        }
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
coletaID();
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       txtCodigo.requestFocus();
    }//GEN-LAST:event_formWindowOpened

    private void txtTrocoDigitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrocoDigitarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrocoDigitarActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
      double valorParaTroco = 0.0;
      if(lblTotalVenda.getText().equals("0")){
      JOptionPane.showMessageDialog(null,"Inicie uma venda!","Aviso",JOptionPane.INFORMATION_MESSAGE);
      }else{
      if(txtTrocoDigitar.getText().length() >=8){
       valorParaTroco = Double.parseDouble(txtTrocoDigitar.getText().replaceAll(".","").replace(",","."));
       }else{
       valorParaTroco = Double.parseDouble(txtTrocoDigitar.getText().replace(",","."));
       }
      if(valorParaTroco < Double.parseDouble(lblTotalVenda.getText().replace(",","."))){
      JOptionPane.showMessageDialog(null,"Troco digitado é menor que o total da venda!","Aviso",JOptionPane.INFORMATION_MESSAGE);
      }else{
      //valorParaTroco-=Double.parseDouble(lblTotalVenda.getText().replace(",","."));
        BigDecimal objPrimeiro = new BigDecimal(lblTotalVenda.getText().replace(",",".").toString());
        BigDecimal objSegundo = new BigDecimal(String.valueOf(valorParaTroco));
        BigDecimal objCal = objSegundo.subtract(objPrimeiro);
        lblTroco.setText(""+objCal);
      }
      }
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void txtTrocoDigitarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTrocoDigitarKeyPressed
      if(evt.getKeyCode() == KeyEvent.VK_ENTER){
       double valorParaTroco = 0.0;
      if(lblTotalVenda.getText().equals("0")){
      JOptionPane.showMessageDialog(null,"Inicie uma venda!","Aviso",JOptionPane.INFORMATION_MESSAGE);
      }else{
      if(txtTrocoDigitar.getText().length() >=8){
       valorParaTroco = Double.parseDouble(txtTrocoDigitar.getText().replaceAll(".","").replace(",","."));
       }else{
       valorParaTroco = Double.parseDouble(txtTrocoDigitar.getText().replace(",","."));
       }
      if(valorParaTroco < Double.parseDouble(lblTotalVenda.getText().replace(",","."))){
      JOptionPane.showMessageDialog(null,"Troco digitado é menor que o total da venda!","Aviso",JOptionPane.INFORMATION_MESSAGE);
      }else{
      //valorParaTroco-=Double.parseDouble(lblTotalVenda.getText().replace(",","."));
        BigDecimal objPrimeiro = new BigDecimal(lblTotalVenda.getText().replace(",",".").toString());
        BigDecimal objSegundo = new BigDecimal(String.valueOf(valorParaTroco));
        BigDecimal objCal = objSegundo.subtract(objPrimeiro);
        lblTroco.setText(""+objCal);
      }
      }
      }
    }//GEN-LAST:event_txtTrocoDigitarKeyPressed

    private void txtQuantidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeKeyReleased
       txtQuantidade.setText(txtQuantidade.getText().replaceAll("[^0-1-2-3-4-5-6-7-8-9]",""));
       
    }//GEN-LAST:event_txtQuantidadeKeyReleased

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
            if(tbProdutos.getModel().getRowCount() == 0){
         JOptionPane.showMessageDialog(null,"É necessario ao menos um produto para realizar a venda!","Aviso",JOptionPane.INFORMATION_MESSAGE);
     }else{
         opcao = JOptionPane.showConfirmDialog(null,"Finalizar a venda?","Aviso",JOptionPane.WARNING_MESSAGE);
      if(opcao == JOptionPane.YES_OPTION){    
     FinalizarVenda();
      if(objListaPagamento.getSelectedIndex() != 0 && opcaoTipo == JOptionPane.YES_OPTION){ 
         /* try {*/
              
              //impressaoLocal("COM4");
              for(int b=0;b<tbProdutos.getRowCount();b++){
              AlteraProdutoEdita(Integer.parseInt(tbProdutos.getValueAt(b,0).toString()),tbProdutos.getValueAt(b,3).toString());
              }
               Limpar();
          /*} catch (IOException ex) {
              Logger.getLogger(telaVenda.class.getName()).log(Level.SEVERE, null, ex);
          }*/
         }
      }
     }
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
         if(JOptionPane.showConfirmDialog(null,"Cancelar a venda?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
      Limpar();
      }
    }//GEN-LAST:event_jMenu2MouseClicked
  private void BuscarCodBarras(){
  try{
  pst = conec.prepareStatement("select id_produto,descricao,valorlucro from produtos where codbarras like ?");
  pst.setString(1,txtCodigo.getText()+"%");
  rs = pst.executeQuery();
  if(rs.next()){
  verificaID = true;
  valorID = rs.getInt(1);
  lblDescricao.setText(rs.getString(2));
  txtValorUnitario.setText(rs.getString(3));
  objProdutosVenda.setCodBarras(txtCodigo.getText());
  txtQuantidade.setText("1");
  txtQuantidade.requestFocus();
  }else{
  JOptionPane.showMessageDialog(null,"Produto não existe!","Aviso",JOptionPane.WARNING_MESSAGE);
  txtCodigo.setText("");
  }
  }catch(SQLException bug){
  JOptionPane.showMessageDialog(null,bug.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
  }
  }
   private void Limpar(){
   do{
   for(int x=0;x<=tbProdutos.getModel().getRowCount()-1;x++){
   objjMyTable.removeRow(x);
   tbProdutos.setModel(objjMyTable);
   }
   }while(tbProdutos.getModel().getRowCount() >0);
    ColetandoUltimaVenda();
    lvlIdProximVenda.setText(""+(guardarID+1));
    lblTotalVenda.setText("0");
    lblDescricao.setText("D    E      S     C     R     I      C    A     O");
    txtQuantidade.setText("");
    txtValorUnitario.setText("");
    txtCodigo.setText("");
    txtCodigo.requestFocus();
    ImageIcon pega = new ImageIcon(getClass().getResource("/foto/LogoBolado.jpg"));
    lbFoto.setIcon(pega);
    txtTrocoDigitar.setValue(0);
    lblTroco.setText("0");  
   }
     private void ColetaCodBarras(int pk){
    try{
    pst = conec.prepareStatement("select codbarras from produtos where id_produto = ?");
   pst.setInt(1, pk);
    rs = pst.executeQuery();
    if(rs.next()){
    codigoBarras = rs.getString(1);
    }
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
   }
    private void ColetarTodosDadosProdutos(){
     if(objProdutosVenda.getsaida() == true){
  txtCodigo.setText(objProdutosVenda.getIdProduto());
  lblDescricao.setText(objProdutosVenda.getDescricao());
  txtValorUnitario.setText(objProdutosVenda.getValorVenda());
  txtQuantidade.setText(""+1);
     }
     }
   private void ColetaQuantidade(int quantidadeProdTabela,int primary){
    try{
    pst = conec.prepareStatement("select quantidade from produtos where codbarras = ?");
   pst.setString(1,txtCodigo.getText() + "%");
    rs = pst.executeQuery();
    if(rs.next()){
    QuantidadeCal = Integer.parseInt(rs.getString(1));
    QuantidadeCal-=quantidadeProdTabela;
    TirarQuantidadeEstoque(primary,String.valueOf(QuantidadeCal));
    }
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
   }
    private void TirarQuantidadeEstoque(int pk,String Quantidade){
    try{
    pst = conec.prepareStatement("update produtos set quantidade = ? where id_produto = ?");
    pst.setString(1,Quantidade);
    pst.setInt(2, pk);
    pst.executeUpdate();
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso Tirar Quantidade",JOptionPane.ERROR_MESSAGE);
    }
    
    }
    private void SomarTudo(){
  double somarTotal=0;
  for(int x=0;x<=tbProdutos.getModel().getRowCount()-1;x++){
  somarTotal+=Double.parseDouble(tbProdutos.getModel().getValueAt(x,4).toString());
  lblTotalVenda.setText(String.format("%.2f",somarTotal));  
  }
 }
   private void Removerlinha(){
   if(JOptionPane.showConfirmDialog(null,"Remover produto?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
   objjMyTable.removeRow(tbProdutos.getSelectedRow());
   tbProdutos.setModel(objjMyTable);
   SomarTudo();
   if(tbProdutos.getModel().getRowCount() == 0){
    lblTotalVenda.setText("0");
   }
   }
   }
   private void BuscarNomeTipoPagamento(){
   try{
          pst = conec.prepareStatement("select count(codigo) from formaspagamento");
          rs = pst.executeQuery();
          if(rs.next()){
              int totalQuant = Integer.parseInt(rs.getString(1));
              objLista = new String[totalQuant];
            try{
          pst = conec.prepareStatement("select tipo from formaspagamento");
          rs = pst.executeQuery();
          while(rs.next()){
              lista.add(rs.getString(1));
              }     
                }catch(SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"ERRO VENDA", JOptionPane.ERROR_MESSAGE);               
                }    
               }     
                }catch(SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"ERRO VENDA", JOptionPane.ERROR_MESSAGE);               
                }
    for(int o=0;o<=lista.size()-1;o++){
    objLista[o] = lista.get(o).toString();   
    } 
   }
   private void FinalizarVenda(){
    
   objListaPagamento = new JComboBox(objLista);
   opcaoTipo = JOptionPane.showConfirmDialog(null,objListaPagamento,"Selecione o tipo de pagamento",JOptionPane.WARNING_MESSAGE);
  if(opcaoTipo == JOptionPane.YES_OPTION){
 
 if(objListaPagamento.getSelectedIndex() != 0){ 
   formaPag = objListaPagamento.getSelectedItem().toString();
     SalvarVenda();
 }
  }
   }
   private void SalvarVenda(){
    try {
          pst = conec.prepareStatement("insert into venda(data_venda,valor_total,tipo_paga) values(?,?,?)");
          pst.setDate(1,Date.valueOf(Data()));
          pst.setDouble(2,Double.parseDouble(lblTotalVenda.getText().replace(",",".")));
          pst.setString(3,objListaPagamento.getModel().getSelectedItem().toString());
            if(pst.executeUpdate() > 0){
    JOptionPane.showMessageDialog(null,"Venda cadastrada!","Aviso",JOptionPane.INFORMATION_MESSAGE);
            SalvarTodosProdutos();     
            }     
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"ERRO VENDA", JOptionPane.ERROR_MESSAGE);               
                }
   }
   
   private void coletaID(){
    try {
          pst = conec.prepareStatement("select id_produto from produtos where codbarras like ?");
          pst.setString(1, txtCodigo.getText()+ "%");
         rs = pst.executeQuery();
         if(rs.next()){
             objProdutosVenda.setIdProduto(rs.getString(1));
         }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"Produto não encontrado", JOptionPane.ERROR_MESSAGE);               
                }
   }
   
   private void SalvarTodosProdutos(){
       ColetandoUltimaVenda();
   for(int op=0;op<=tbProdutos.getModel().getRowCount()-1;op++){
       try{
          pst = conec.prepareStatement("insert into item_venda(cod_venda,cod_produto,preco_un,quantidade,preco_total) values(?,?,?,?,?)");
          pst.setInt(1,guardarID);
          pst.setInt(2, Integer.parseInt(tbProdutos.getModel().getValueAt(op,0).toString()));
          pst.setDouble(3,Double.valueOf(tbProdutos.getModel().getValueAt(op,2).toString()));
          pst.setInt(4,Integer.parseInt(tbProdutos.getModel().getValueAt(op,3).toString()));
          pst.setDouble(5,Double.valueOf(tbProdutos.getModel().getValueAt(op,4).toString()));
          if(pst.executeUpdate() > 0){
             ColetaQuantidade(Integer.parseInt(tbProdutos.getModel().getValueAt(op,3).toString()),Integer.parseInt(tbProdutos.getModel().getValueAt(op,0).toString()));
          }     
            }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"ERRO CAD PRODUTOS", JOptionPane.ERROR_MESSAGE);               
                }
       }
   }
   private void ColetandoUltimaVenda(){
       
    try {
        pst = conec.prepareStatement("select max(codigo) from venda");
        rs = pst.executeQuery();
        if(rs.next()){
       guardarID = rs.getInt(1);
            } 
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"ERRO COLETA ULTIMA VENDA", JOptionPane.ERROR_MESSAGE);               
                }
   }
   private String Data(){
   String Data = "yyy-MM-dd";
   String DataFormatada;
   java.util.Date ColData = new java.util.Date();
   SimpleDateFormat DataFormatar = new SimpleDateFormat(Data);
   DataFormatada = DataFormatar.format(ColData);
  //DataSystem.setText(DataFormatada);
    final SimpleDateFormat HHmmss = new SimpleDateFormat("HH:mm:ss");
        // crie um TimerTask estendendo dele ou sobreescrevendo ele na hora
        TimerTask task = new TimerTask() {
            // sobreescreva o método run
            @Override
            public void run() {
                // aqui vai o código a ser executado
                // no lugar do sop pode vir um jpanel.setText(), ou de qqr
                // outro componente de java desktop
                // não se esqueça que os objetos de fora usados aqui devem
                // ser final
               
            }
        };
       
        // use este método para rodar o código acima repetidamente
        // leia o javadoc do Timer se quiser saber o que isto significa
        new Timer().scheduleAtFixedRate(task, 0, 1000);
        //return  HHmmss.format(new java.util.Date());
       return DataFormatada;
   }
    private String Hora(){
   String Data = "yyy-MM-dd";
   String DataFormatada;
   java.util.Date ColData = new java.util.Date();
   SimpleDateFormat DataFormatar = new SimpleDateFormat(Data);
   DataFormatada = DataFormatar.format(ColData);
  //DataSystem.setText(DataFormatada);
    final SimpleDateFormat HHmmss = new SimpleDateFormat("HH:mm:ss");
        // crie um TimerTask estendendo dele ou sobreescrevendo ele na hora
        TimerTask task = new TimerTask() {
            // sobreescreva o método run
            @Override
            public void run() {
                // aqui vai o código a ser executado
                // no lugar do sop pode vir um jpanel.setText(), ou de qqr
                // outro componente de java desktop
                // não se esqueça que os objetos de fora usados aqui devem
                // ser final
               
            }
        };
       
        // use este método para rodar o código acima repetidamente
        // leia o javadoc do Timer se quiser saber o que isto significa
        new Timer().scheduleAtFixedRate(task, 0, 1000);
        return  HHmmss.format(new java.util.Date());
      // return DataFormatada;
   }
    private void impressaoLocal(String porta) throws IOException {
        FileOutputStream outputFile = null;

        try{
            outputFile = new FileOutputStream(porta);
            try (PrintStream ps = new PrintStream(outputFile)) {
     
                ps.println("=============================================");
                ps.println("**********  NAO E DOCUMENTO FISCAL **********");
                ps.println("=============================================");
                ps.print(String.format(String.format("%-28s","Data:"+" "+Data())));
                ps.println(String.format(String.format("%15s","Horario:"+" "+ Hora())));
                ps.println(String.format("%-28s","CODIGO CUPOM: "+ lvlIdProximVenda.getText()));
                ps.println("=============================================");
                ps.println("DESCRICAO           QTDE    VALOR.UN    VALOR");
               
                //Laço percorre a tabela com os itens
                for (int x = 0; x < tbProdutos.getRowCount(); x++) {

                    ps.print(String.format("%-20s", tbProdutos.getModel().getValueAt(x, 1)));// o parametro '-' indica alinhamento a esquerda
                    ps.print(String.format("%2s", tbProdutos.getModel().getValueAt(x, 3)));
                    ps.print(String.format("%12s", tbProdutos.getModel().getValueAt(x, 2)).replace(".", ",")); // o parametro %18s indiaca alinhamento a direita
                    ps.print(String.format("%11s", tbProdutos.getModel().getValueAt(x, 4)).replace(".", ","));
                
                    ps.println();

            
                }
               
                ps.println("=============================================");
                ps.println(String.format("VALOR TOTAL DA COMPRA %23s", lblTotalVenda.getText().replace(".", ",")));
                 //ps.println(String.format("Emitido por:"+ lbNome_User.getText()+"   "+"\nPagamento:"+ txtTipoPag.getText()));
                ps.println(String.format("TIPO DO PAGAMENTO %27s", formaPag));
                ps.println(String.format("OPERADOR %23s", objUsuario.getUsuario()));
                ps.println("=============================================");
                ps.println(" ");
                ps.println(" ");
               
                ps.flush();
            }
            outputFile.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            outputFile.close();
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
            java.util.logging.Logger.getLogger(telaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new telaVenda().setVisible(true);
                
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnEnviarProduto;
    private javax.swing.JButton btnListaProduto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblTotalVenda;
    private javax.swing.JLabel lblTroco;
    private javax.swing.JLabel lvlIdProximVenda;
    private javax.swing.JTable tbProdutos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JFormattedTextField txtTrocoDigitar;
    private javax.swing.JTextField txtValorUnitario;
    // End of variables declaration//GEN-END:variables

    
}
