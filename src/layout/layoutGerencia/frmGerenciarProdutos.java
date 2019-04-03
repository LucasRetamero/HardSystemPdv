package layout.layoutGerencia;

import DAO.Conexao;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import layoutCadastro.cadastroProduto;
import layoutInterno.buscaFornecedor;
import layoutInterno.buscaGrupo;
import layoutInterno.buscaProduto;
import layoutInterno.buscarProdutosVenda;
import layoutInterno.frmBuscarForneFisico;
import layoutInterno.frmBuscarForneJuridico;

/**
 *
 * @author Su
 */
public class frmGerenciarProdutos extends javax.swing.JInternalFrame {

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
    public frmGerenciarProdutos() {
        initComponents();
        objGrupo = new ButtonGroup();
        objGrupo.add(rbOn);
        objGrupo.add(rbOff);
        try {
            conec = Conexao.conecta();
            setandoLucro();
            txtQuantidade.setText("0");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(cadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
             Logger.getLogger(frmGerenciarProdutos.class.getName()).log(Level.SEVERE, null, ex);
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
    private void Tratamento(int addOrEdit){
        if(txtCodigo.getText().equals("")){
        JOptionPane.showMessageDialog(null,"É necessario selecionar o produto!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }else if(txtDescricao.getText().equals("")){
        JOptionPane.showMessageDialog(null,"Campo Descrição vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }else if(txtFornecedor.getText().equals("")){
        JOptionPane.showMessageDialog(null,"Campo Fornecedor vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }else if(txtQuantidade.getText().equals("")){
        JOptionPane.showMessageDialog(null,"Campo Quantidade vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }else if(txtValorCusto.getText().equals("")){
        JOptionPane.showMessageDialog(null,"Campo Valor Custo vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }else if(txtValorLucro.getText().equals("")){
        JOptionPane.showMessageDialog(null,"Campo Valor Lucro vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }else{
        switch(addOrEdit){
            case 1:
                DeletarProdutos();      
                break;
            case 2:         
                if(JOptionPane.showConfirmDialog(null,"Deseja Alterar?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                editar();
                }            
                break;
        }
        }     
    }
   
     private void limparCampos(){
        txtCodigo.setText("");
       txtGrupo.setText("");
        txtBarras.setText("");
        txtDescricao.setText("");
        txtReduzida.setText("");
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
      private void editar() {
         double valoresParaBanco = 0.0;
       if(txtValorCusto.getText().length() >=8){
       valoresParaBanco = Double.parseDouble(txtValorCusto.getText().replace(".","").replace(",","."));
       }else{
       valoresParaBanco = Double.parseDouble(txtValorCusto.getText().replace(",","."));
       }
        if(rbOn.isSelected() == true){
             statusPro = "S";
            }else if(rbOff.isSelected() == true){
             statusPro = "N";
            }else{
            statusPro = "S";
            }
            if(img == 0){
            pegarImgAntesEditar(Integer.parseInt(txtCodigo.getText()));
            }
        String sql = "update produtos set codbarras=?,descricao=?,reduzida=?,grupoprod = ?,fornecedor = ?,quantidade=?,valorcusto=?,valorlucro=?,img = ?,status=?  where  id_produto = ?";
     
        try {
            pst = conec.prepareStatement(sql); 
            pst.setString(1, txtBarras.getText());
            pst.setString(2, txtDescricao.getText());
            pst.setString(3, txtReduzida.getText());
            pst.setString(4, txtGrupo.getText());
            pst.setString(5, txtFornecedor.getText());
            pst.setString(6,txtQuantidade.getText());
            pst.setDouble(7,valoresParaBanco);
            pst.setDouble(8,Double.parseDouble(txtValorLucro.getText()));
            pst.setString(9, filename);
            pst.setString(10,statusPro);
            pst.setInt(11,Integer.parseInt(txtCodigo.getText()));
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Produto Alterado","Aviso",JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
                verificaImg = 0;
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        }

    }
      public void pegarImgAntesEditar(int pk){
    try{
    pst = conec.prepareStatement(" select img from produtos where id_produto =?");
    pst.setInt(1,pk);
    rs = pst.executeQuery();
    if(rs.next()){
    filename = rs.getString(1);
    }
    }catch(SQLException e){
    JOptionPane.showMessageDialog(null,e.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
    }
  private void DeletarProdutos(){
   if(txtCodigo.getText().equals("")){
       JOptionPane.showMessageDialog(null,"Selecione o produto!","Aviso",JOptionPane.WARNING_MESSAGE);
   }else{
      if(JOptionPane.showConfirmDialog(null,"Remover Produto?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
  try{
  pst = conec.prepareStatement("delete from produtos where id_produto = ?");
  pst.setInt(1,Integer.parseInt(txtCodigo.getText()));
  if(pst.executeUpdate() > 0){
  JOptionPane.showMessageDialog(null,"Deletado com sucesso!","Aviso",JOptionPane.INFORMATION_MESSAGE);
  limparCampos();
  }
  }catch(SQLException bug){
  JOptionPane.showMessageDialog(null,bug.getMessage(),"Aviso Delete",JOptionPane.ERROR_MESSAGE);
  }
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
       //PrecoDeCusto = Float.parseFloat(txtValorCusto.getText().replaceAll(",","."));
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
      private void chamaDados() {
        String sql = "select * from produtos where codbarras = ? and status = 'S'";

        try {
            pst = conec.prepareStatement(sql);
            //pst.setInt(1, Integer.parseInt(txtBusca.getText()));
            pst.setString(1, txtBusca.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtCodigo.setText(rs.getString(1));
                txtDescricao.setText(rs.getString(3));
                txtReduzida.setText(rs.getString(4));
                txtGrupo.setText(rs.getString(5));
                txtFornecedor.setText(rs.getString(6));
                txtQuantidade.setText(rs.getString(7));
                txtValorCusto.setText(rs.getString(8));
                txtValorLucro.setText(rs.getString(9));
                guardaFoto = String.valueOf(rs.getString(10));
                if(guardaFoto.equals("/foto/semFoto.png")){
                ImageIcon IMGpadrao = new ImageIcon(this.getClass().getResource("/foto/semFoto.png"));
                 lbFoto.setIcon(new ImageIcon(IMGpadrao.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT)));
                }else{
                ImageIcon icon = new ImageIcon(guardaFoto);
                lbFoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT)));
                  }   
                txtBarras.setText(txtBusca.getText());
            }else{
                JOptionPane.showMessageDialog(null, "Nâo encontrado!","Aviso",JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "FORNECEDOR NÃO ENCONTRADO", "Erro ChamarDados", JOptionPane.ERROR_MESSAGE);
            txtBusca.setText("");
        }
    }
      
     private void ColetandoFornecedorJuridico(frmBuscarForneJuridico objJuridico){
     txtFornecedor.setText(objJuridico.getNomeJuridico());
     }
      private void ColetandoFornecedorFisico(frmBuscarForneFisico objFisico){
     txtFornecedor.setText(objFisico.getNomeFisico());
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fundo = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtBarras = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtReduzida = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JTextField();
        btnf8Busca2 = new javax.swing.JButton();
        btnf8Busca4 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtFornecedor = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtValorCusto = new javax.swing.JFormattedTextField();
        jLabel23 = new javax.swing.JLabel();
        txtValorLucro = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtValorPorcentagem = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtValorLucroCal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        lbFoto = new javax.swing.JLabel();
        rbTodosProduto = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        rbOn = new javax.swing.JCheckBox();
        rbOff = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        btnf8Busca = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setClosable(true);
        setTitle("Gerenciar Produtos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Fundo.setBackground(new java.awt.Color(255, 255, 255));
        Fundo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setText("Cod.Barras");
        Fundo.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));
        Fundo.add(txtBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 319, -1));

        jLabel7.setText("Descriçao");
        Fundo.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

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
        Fundo.add(txtDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 319, -1));

        jLabel8.setText("Reduzida");
        Fundo.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

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
        Fundo.add(txtReduzida, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 319, -1));

        jLabel10.setText("Grupo");
        Fundo.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        txtGrupo.setEditable(false);
        txtGrupo.setBackground(new java.awt.Color(255, 255, 255));
        Fundo.add(txtGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 260, -1));

        btnf8Busca2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Search-16.png"))); // NOI18N
        btnf8Busca2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnf8Busca2ActionPerformed(evt);
            }
        });
        Fundo.add(btnf8Busca2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 50, -1));

        btnf8Busca4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Search-16.png"))); // NOI18N
        btnf8Busca4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnf8Busca4ActionPerformed(evt);
            }
        });
        Fundo.add(btnf8Busca4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 50, -1));

        jLabel11.setText("Fornecedor:");
        Fundo.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        txtFornecedor.setEditable(false);
        txtFornecedor.setBackground(new java.awt.Color(255, 255, 255));
        Fundo.add(txtFornecedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 260, -1));

        jLabel21.setText("Quantidade");
        Fundo.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, -1, -1));

        txtQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantidadeKeyReleased(evt);
            }
        });
        Fundo.add(txtQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 230, -1));

        jLabel22.setText("Custo");
        Fundo.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, -1, -1));
        Fundo.add(txtValorCusto, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, 230, -1));

        jLabel23.setText("Valor de Venda");
        Fundo.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, -1, -1));

        txtValorLucro.setEditable(false);
        txtValorLucro.setBackground(new java.awt.Color(255, 255, 255));
        Fundo.add(txtValorLucro, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 230, -1));

        jLabel12.setText("Porcentagem");
        Fundo.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, -1, -1));

        txtValorPorcentagem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorPorcentagemKeyReleased(evt);
            }
        });
        Fundo.add(txtValorPorcentagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 230, -1));

        jLabel24.setText("Lucro:");
        Fundo.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 360, -1, -1));

        txtValorLucroCal.setEditable(false);
        txtValorLucroCal.setBackground(new java.awt.Color(255, 255, 255));
        Fundo.add(txtValorLucroCal, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, 230, -1));

        jLabel13.setText("Imagem do produto");
        Fundo.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, -1, -1));

        lbFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/semFoto.png"))); // NOI18N
        Fundo.add(lbFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, 135, 137));

        rbTodosProduto.setText("Todos os Produtos");
        Fundo.add(rbTodosProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 280, -1, -1));

        jLabel9.setText("Codigo");
        Fundo.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        txtCodigo.setEditable(false);
        txtCodigo.setBackground(new java.awt.Color(153, 153, 153));
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        Fundo.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 319, -1));

        rbOn.setText("Ativado");
        Fundo.add(rbOn, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, -1, -1));

        rbOff.setText("Desativado");
        Fundo.add(rbOff, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, -1, -1));

        jLabel14.setText("Status");
        Fundo.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, -1, -1));

        jLabel2.setText("Pesquisar produto pelo codigo de barras ou pelo botão");
        Fundo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaKeyReleased(evt);
            }
        });
        Fundo.add(txtBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 250, 20));

        btnf8Busca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Search-16.png"))); // NOI18N
        btnf8Busca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnf8BuscaActionPerformed(evt);
            }
        });
        Fundo.add(btnf8Busca, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 57, -1));

        getContentPane().add(Fundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 460));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/updateMenu.png"))); // NOI18N
        jMenu1.setText("Editar");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/deletarMenu.png"))); // NOI18N
        jMenu2.setText("Deletar");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/LimparMenu.png"))); // NOI18N
        jMenu3.setText("Limpar campos");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/selecionarImagenMenu.png"))); // NOI18N
        jMenu4.setText("Selecionar imagem");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/calculadoraMenu.png"))); // NOI18N
        jMenu5.setText("Calcular");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_F8){
            try {
                buscaFornecedor jd = new buscaFornecedor(null, closable);
                jd.setLocationRelativeTo(null);
                jd.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(cadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if ("".equals(txtBusca.getText())){

            } else {
                chamaDados();
                txtBusca.getText();
            }
        }
    }//GEN-LAST:event_txtBuscaKeyPressed

    private void txtBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyReleased
        if ("".equals(txtBusca.getText())){

        } else {
            chamaDados();
            txtBusca.getText();
        }
    }//GEN-LAST:event_txtBuscaKeyReleased

    private void btnf8BuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnf8BuscaActionPerformed
        try {
            buscaProduto jnl = new buscaProduto(null, closable);
            jnl.setVisible(true);
            String coletaFoto = jnl.getImg();
            txtCodigo.setText("" + jnl.getCodigo());
            txtBarras.setText(jnl.getCodbarras());
            txtDescricao.setText(jnl.getDescricao());
            txtReduzida.setText(jnl.getReduzida());
            txtGrupo.setText(jnl.getGrupo());
            txtFornecedor.setText(jnl.getFornecedor());
            txtQuantidade.setText("" + jnl.getQuantidade());
            txtValorCusto.setValue(jnl.getValCusto());
            txtValorLucro.setText(""+jnl.getValLucro());
            if(jnl.getStatus().equals("S")){
                rbOn.setSelected(true);
            }else if(jnl.getStatus().equals("N")){
                rbOff.setSelected(true);
            }
            if(jnl.getImg().equals("/foto/semFoto.png")){
                ImageIcon icon = new ImageIcon(getClass().getResource("/foto/semFoto.png"));
                lbFoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT)));
            } else{
                ImageIcon icon = new ImageIcon(coletaFoto);
                lbFoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(cadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnf8BuscaActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoActionPerformed

    private void txtDescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyReleased
        txtDescricao.setText(txtDescricao.getText().toUpperCase());
        if (txtDescricao.getText().length() >= 15) {
            txtReduzida.setText(txtDescricao.getText().substring(0, 22));
        } else {
            txtReduzida.setText(txtDescricao.getText());
        }
    }//GEN-LAST:event_txtDescricaoKeyReleased

    private void txtReduzidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReduzidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReduzidaActionPerformed

    private void txtReduzidaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReduzidaKeyReleased

    }//GEN-LAST:event_txtReduzidaKeyReleased

    private void btnf8Busca2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnf8Busca2ActionPerformed
        try {
            buscaGrupo jnl = new buscaGrupo(null, closable);
            jnl.setVisible(true);
            txtGrupo.setText(jnl.getGrupo());
        } catch (SQLException ex) {
            Logger.getLogger(cadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnf8Busca2ActionPerformed

    private void btnf8Busca4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnf8Busca4ActionPerformed
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

    }//GEN-LAST:event_btnf8Busca4ActionPerformed

    private void txtQuantidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeKeyReleased
        txtQuantidade.setText(txtQuantidade.getText().replaceAll("[^0-1-2-3-4-5-6-7-8-9]",""));

    }//GEN-LAST:event_txtQuantidadeKeyReleased

    private void txtValorPorcentagemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorPorcentagemKeyReleased
        txtValorPorcentagem.setText(txtValorPorcentagem.getText().replaceAll("[^0-9]",""));
        if(Integer.parseInt(txtValorPorcentagem.getText()) > 100){
            JOptionPane.showMessageDialog(null,"Evite digitar valor acima que 100");
            txtValorPorcentagem.setText("");
        }
    }//GEN-LAST:event_txtValorPorcentagemKeyReleased

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
           Tratamento(2);
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
       Tratamento(1);
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
       limparCampos();
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
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

    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
             CalculaPor();
    }//GEN-LAST:event_jMenu5MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Fundo;
    private javax.swing.JButton btnf8Busca;
    private javax.swing.JButton btnf8Busca2;
    private javax.swing.JButton btnf8Busca4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JCheckBox rbOff;
    private javax.swing.JCheckBox rbOn;
    private javax.swing.JRadioButton rbTodosProduto;
    private javax.swing.JTextField txtBarras;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtCodigo;
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
