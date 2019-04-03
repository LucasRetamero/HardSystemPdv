package layoutCadastro;

import DAO.Conexao;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import layoutInterno.buscaFornecedor;
import layoutInterno.buscaGrupo;
import layoutInterno.buscaProduto;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import layoutInterno.frmBuscarForneFisico;
import layoutInterno.frmBuscarForneJuridico;

public class cadastroProduto extends javax.swing.JInternalFrame {

    Connection conec = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String filename;
    int img = 0, verificaImg = 0,opcao;
    int estMinimo, estMedio, estIdeal;
    String guardaFoto;
    double LucroInicial = 0.00;
    float PrecoDeCusto;
    float ResultadoCalculoPrecoCusto;
    float ValorPorcentagem;
    float SomaDeTudo;
    ButtonGroup objGrupo;
    String statusPro= "S";
    JComboBox objSelecao;
  

    public cadastroProduto() throws SQLException {
        initComponents();
        objGrupo = new ButtonGroup();
        try {
            conec = Conexao.conecta();
            setandoLucro();
            txtQuantidade.setText("0");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(cadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
         DecimalFormat decimal = new DecimalFormat("#,###,###.00");
         NumberFormatter numFormatter = new NumberFormatter(decimal);
         numFormatter.setFormat(decimal);
         numFormatter.setAllowsInvalid(false);
         DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
         txtValorCusto.setFormatterFactory(dfFactory);

    }
    public void setandoLucro() {
        txtValorLucro.setText("" + LucroInicial);
    }

    public void cadastrar() {
       double valoresParaBanco = 0.0;
       if(txtValorCusto.getText().length() >=8){
       valoresParaBanco = Double.parseDouble(txtValorCusto.getText().replaceAll(".","").replace(",","."));
       }else{
       valoresParaBanco = Double.parseDouble(txtValorCusto.getText().replace(",","."));
       }
       String sql = "insert into produtos(codbarras,descricao,reduzida,grupoprod,fornecedor,quantidade,valorcusto,valorlucro,img)\n" +
"                     values(?,?,?,?,?,?,?,?,?)";
        try {
            pst = conec.prepareStatement(sql);
            pst.setString(1, txtBarras.getText());
            pst.setString(2, txtDescricao.getText());
            pst.setString(3, txtReduzida.getText());
            pst.setString(4, txtGrupo.getText());
            pst.setString(5, txtFornecedor.getText());
            pst.setString(6,txtQuantidade.getText());
            pst.setDouble(7,valoresParaBanco);
            pst.setDouble(8,Double.parseDouble(txtValorLucro.getText().replace(",",".")));
            if(img==0){
            filename = "/foto/semFoto.png";
            }
            pst.setString(9, filename);
            if(pst.executeUpdate() > 0){
           JOptionPane.showMessageDialog(null, "Produto Cadastrado", "Produto Cadastrado", JOptionPane.INFORMATION_MESSAGE);
           limparCampos();
        }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage(),"Erro CadProdutos",JOptionPane.ERROR_MESSAGE);
        }

    }

    private void limparCampos(){
        txtBarras.setText("");
        txtDescricao.setText("");
        txtReduzida.setText("");
        txtGrupo.setText("");
        txtFornecedor.setText("");
        txtValorCusto.setText("0");
        setandoLucro();
        txtQuantidade.setText("");
        lbFoto.setText("");
        txtQuantidade.setText("");
        txtValorLucro.setText("");
        if(rbTodosProduto.isSelected() == true){
        
        }else{
        //jComboBoxSelecionarPorcentagem.setSelectedIndex(0);
        txtValorPorcentagem.setText("");
        }
        filename="";
        ImageIcon IMGpadrao = new ImageIcon(this.getClass().getResource("/foto/semFoto.png"));
        lbFoto.setIcon(new ImageIcon(IMGpadrao.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT)));
       objGrupo.clearSelection();
       img=0;
    }

    private void Tratamento(int addOrEdit){
        if(txtDescricao.getText().equals("")){
        JOptionPane.showMessageDialog(null,"Campo Descrição vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }else if(txtGrupo.getText().equals("")){
        JOptionPane.showMessageDialog(null,"Campo Grupo vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }else if(txtFornecedor.getText().equals("")){
        JOptionPane.showMessageDialog(null,"Campo Fornecedor vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }else if(txtQuantidade.getText().equals("")){
        JOptionPane.showMessageDialog(null,"Campo Quantidade vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }else if(txtValorCusto.getText().equals("")){
        JOptionPane.showMessageDialog(null,"Campo Valor Custo vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }else if(txtValorLucro.getText().equals("")){
        JOptionPane.showMessageDialog(null,"Campo Valor Lucro vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }else{
        if(JOptionPane.showConfirmDialog(null,"Cadastrar Produto?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                cadastrar();
               }
        }     
    }
    private void CalculaPor(){
       DecimalFormat formatavalor = new DecimalFormat("0,000");
       DecimalFormat forma = new DecimalFormat("0,00");
       String converter;
       //ComparaPorcentagem();
       ValorPorcentagem = Integer.parseInt(txtValorPorcentagem.getText()); 
       if(txtValorCusto.getText().length() >=8){
       PrecoDeCusto = Float.valueOf(txtValorCusto.getText().replace(".","").replace(",","."));
       }else{
       PrecoDeCusto = Float.valueOf(txtValorCusto.getText().replace(",","."));
       }
        ResultadoCalculoPrecoCusto = PrecoDeCusto * (ValorPorcentagem/100); // 0 - 100
        SomaDeTudo =  PrecoDeCusto + ResultadoCalculoPrecoCusto;
        BigDecimal objPrimeiroValor = new BigDecimal(String.valueOf(PrecoDeCusto));
        BigDecimal objSegundoValor = new BigDecimal(ValorPorcentagem).divide(new BigDecimal("100"));
        BigDecimal objResultadoMulti = objPrimeiroValor.multiply(objSegundoValor);
        BigDecimal objResultadoSomar = objPrimeiroValor.add(objResultadoMulti);
        //txtValorLucro.setText(""+SomaDeTudo); 
        txtValorLucro.setText(String.format("%.2f",objResultadoSomar).replace(",",".")); 
        BigDecimal objValorCusto = new BigDecimal(String.valueOf(PrecoDeCusto));
        BigDecimal objValorVenda = new BigDecimal(String.valueOf(txtValorLucro.getText().toString()));
        BigDecimal objCalcular = objValorVenda.subtract(objValorCusto);
        txtValorLucroCal.setText(String.format("%.2f",objCalcular));
        
    }
       private void ColetandoFornecedorJuridico(frmBuscarForneJuridico objJuridico){
     txtFornecedor.setText(objJuridico.getNomeJuridico());
     }
      private void ColetandoFornecedorFisico(frmBuscarForneFisico objFisico){
     txtFornecedor.setText(objFisico.getNomeFisico());
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fundo = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtBarras = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtReduzida = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JTextField();
        btnf8Busca1 = new javax.swing.JButton();
        btnf8Busca3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtFornecedor = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtValorCusto = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        txtValorLucro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtValorPorcentagem = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtValorLucroCal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lbFoto = new javax.swing.JLabel();
        rbTodosProduto = new javax.swing.JRadioButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setClosable(true);
        setTitle("Tela de Produto");
        setPreferredSize(new java.awt.Dimension(786, 460));

        Fundo.setBackground(new java.awt.Color(255, 255, 255));
        Fundo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setText("Cod.Barras");
        Fundo.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));
        Fundo.add(txtBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 319, -1));

        jLabel1.setText("Descriçao");
        Fundo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        txtDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescricaoActionPerformed(evt);
            }
        });
        txtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescricaoKeyReleased(evt);
            }
        });
        Fundo.add(txtDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 319, -1));

        jLabel3.setText("Reduzida");
        Fundo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        txtReduzida.setEditable(false);
        txtReduzida.setBackground(new java.awt.Color(204, 204, 204));
        txtReduzida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReduzidaActionPerformed(evt);
            }
        });
        txtReduzida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtReduzidaKeyReleased(evt);
            }
        });
        Fundo.add(txtReduzida, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 145, 319, -1));

        jLabel4.setText("Grupo");
        Fundo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        txtGrupo.setEditable(false);
        txtGrupo.setBackground(new java.awt.Color(255, 255, 255));
        Fundo.add(txtGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 260, -1));

        btnf8Busca1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Search-16.png"))); // NOI18N
        btnf8Busca1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnf8Busca1ActionPerformed(evt);
            }
        });
        Fundo.add(btnf8Busca1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 50, -1));

        btnf8Busca3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Search-16.png"))); // NOI18N
        btnf8Busca3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnf8Busca3ActionPerformed(evt);
            }
        });
        Fundo.add(btnf8Busca3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 255, 50, -1));

        jLabel5.setText("Fornecedor:");
        Fundo.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        txtFornecedor.setEditable(false);
        txtFornecedor.setBackground(new java.awt.Color(255, 255, 255));
        Fundo.add(txtFornecedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 255, 260, -1));

        jLabel16.setText("Quantidade");
        Fundo.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        txtQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantidadeKeyReleased(evt);
            }
        });
        Fundo.add(txtQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 230, -1));

        jLabel15.setText("Custo");
        Fundo.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, -1, -1));
        Fundo.add(txtValorCusto, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 230, -1));

        jLabel17.setText("Valor de Venda");
        Fundo.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, -1, -1));

        txtValorLucro.setEditable(false);
        txtValorLucro.setBackground(new java.awt.Color(255, 255, 255));
        Fundo.add(txtValorLucro, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, 230, -1));

        jLabel6.setText("Porcentagem");
        Fundo.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, -1, -1));

        txtValorPorcentagem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorPorcentagemKeyReleased(evt);
            }
        });
        Fundo.add(txtValorPorcentagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 145, 230, -1));

        jLabel19.setText("Lucro:");
        Fundo.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, -1, -1));

        txtValorLucroCal.setEditable(false);
        txtValorLucroCal.setBackground(new java.awt.Color(255, 255, 255));
        Fundo.add(txtValorLucroCal, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 255, 230, -1));

        jLabel2.setText("Imagem do produto");
        Fundo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, -1, -1));

        lbFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/semFoto.png"))); // NOI18N
        Fundo.add(lbFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, 135, 137));

        rbTodosProduto.setText("Todos os Produtos");
        Fundo.add(rbTodosProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, -1, -1));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/cadastroMenu.png"))); // NOI18N
        jMenu1.setText("Cadastrar");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/LimparMenu.png"))); // NOI18N
        jMenu2.setText("Limpar campos");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/selecionarImagenMenu.png"))); // NOI18N
        jMenu3.setText("Selecionar imagem");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/calculadoraMenu.png"))); // NOI18N
        jMenu4.setText("Efetuar calculo");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fundo, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fundo, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBounds(0, 0, 813, 367);
    }// </editor-fold>//GEN-END:initComponents

    private void txtReduzidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReduzidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReduzidaActionPerformed

    private void txtReduzidaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReduzidaKeyReleased

    }//GEN-LAST:event_txtReduzidaKeyReleased

    private void txtDescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyReleased
        txtDescricao.setText(txtDescricao.getText().toUpperCase());
        if (txtDescricao.getText().length() >= 15) {
            txtReduzida.setText(txtDescricao.getText().substring(0, 22));
        } else {
            txtReduzida.setText(txtDescricao.getText());
        }
    }//GEN-LAST:event_txtDescricaoKeyReleased

    private void btnf8Busca1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnf8Busca1ActionPerformed
        try {
            buscaGrupo jnl = new buscaGrupo(null, closable);
            jnl.setVisible(true);
            txtGrupo.setText(jnl.getGrupo());
        } catch (SQLException ex) {
            Logger.getLogger(cadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnf8Busca1ActionPerformed

    private void txtQuantidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeKeyReleased
        txtQuantidade.setText(txtQuantidade.getText().replaceAll("[^0-1-2-3-4-5-6-7-8-9]",""));
        
    }//GEN-LAST:event_txtQuantidadeKeyReleased

    private void txtDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoActionPerformed

    private void btnf8Busca3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnf8Busca3ActionPerformed
        String dados[] = new String[3];
        dados[0] = "Selecione";
        dados[1] = "Fornecedor Fisico";
        dados[2] = "Fornecedor Juridico";
        objSelecao = new JComboBox(dados);
        do{
        opcao = JOptionPane.showConfirmDialog(null,objSelecao,"Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(opcao == JOptionPane.YES_OPTION){
        switch(objSelecao.getSelectedIndex()){
             case 1:
            frmBuscarForneFisico objFisico = new frmBuscarForneFisico(null, closable);
            objFisico.setLocationRelativeTo(null);
            objFisico.setVisible(true);
            ColetandoFornecedorFisico(objFisico);
            break;
             case 2:
            frmBuscarForneJuridico objJuridico = new  frmBuscarForneJuridico(null, closable);
            objJuridico.setLocationRelativeTo(null);
            objJuridico.setVisible(true);
            ColetandoFornecedorJuridico(objJuridico);
            break;
             default:
             JOptionPane.showMessageDialog(null,"Selecione o tipo de forcencedor","Aviso",JOptionPane.WARNING_MESSAGE);
                 break;
         }
        }
        }while(opcao == JOptionPane.YES_OPTION && objSelecao.getSelectedIndex() == 0);
       
   
    }//GEN-LAST:event_btnf8Busca3ActionPerformed

    private void txtValorPorcentagemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorPorcentagemKeyReleased
        txtValorPorcentagem.setText(txtValorPorcentagem.getText().replaceAll("[^0-9]",""));
        if(Integer.parseInt(txtValorPorcentagem.getText()) > 100){
         JOptionPane.showMessageDialog(null,"Evite digitar valor acima que 100");
         txtValorPorcentagem.setText("");
          }
    }//GEN-LAST:event_txtValorPorcentagemKeyReleased

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
       Tratamento(1);
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        limparCampos();
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
           verificaImg += 1;

        JFileChooser foto = new JFileChooser();
        foto.setDialogTitle("Selecione a Imagem");
        foto.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int opc = foto.showOpenDialog(this);
        if (opc == JFileChooser.APPROVE_OPTION) {
            File file = new File("caminho");
            file = foto.getSelectedFile();
            filename = file.getAbsolutePath();

            ImageIcon images = new ImageIcon(foto.getSelectedFile().getPath());
            lbFoto.setIcon(new ImageIcon(images.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT)));
            img = 1;

        } else {
            img = 0;
            filename = "foto/semFoto.png";
        }

    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        CalculaPor();
    }//GEN-LAST:event_jMenu4MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Fundo;
    private javax.swing.JButton btnf8Busca1;
    private javax.swing.JButton btnf8Busca3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JRadioButton rbTodosProduto;
    private javax.swing.JTextField txtBarras;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtFornecedor;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtReduzida;
    private javax.swing.JFormattedTextField txtValorCusto;
    private javax.swing.JTextField txtValorLucro;
    private javax.swing.JTextField txtValorLucroCal;
    private javax.swing.JTextField txtValorPorcentagem;
    // End of variables declaration//GEN-END:variables
}
