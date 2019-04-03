package layout;

import Controler.InfoUsuario;
import DAO.Conexao;
import java.awt.Dimension;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import layoutCadastro.cadastroProduto;
import layoutCadastro.cadastroUsuario;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.Hashtable;
import javax.swing.ImageIcon;
import layoutCadastro.cadastrarFormaPagamento;
import layoutCadastro.cadastroFuncionario;
import layoutCadastro.*;
import layout.layoutGerencia.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.view.SaveContributorUtils;
import com.toedter.calendar.JDateChooser;
import java.io.File;
import java.io.InputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class telaPrincipal extends javax.swing.JFrame {

    Connection connection;
    PreparedStatement pst;
    ResultSet rs; 
    private final DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
    InfoUsuario Obj;
    JDateChooser dcInicio = new JDateChooser();
    JDateChooser dcFinal  = new JDateChooser();
    public telaPrincipal(String user,String nivel){
        initComponents();
         try {
              connection = Conexao.conecta();
              dcInicio.setDateFormatString("yyyy-MM-dd");
              dcFinal.setDateFormatString("yyyy-MM-dd");
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(telaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
          } catch (SQLException ex) {
              Logger.getLogger(telaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
          }
        this.setExtendedState(MAXIMIZED_BOTH);
        jLabelUser.setText(user);
        Thread Hora = new Thread(new telaPrincipal.DataHora());
        Hora.start();
        Obj = new InfoUsuario();
        Obj.setNivel(nivel);
       URL url = this.getClass().getResource("/imagens/IconePequeno.png");  
       Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);  
       this.setIconImage(iconeTitulo);
    }

    private void centralizaForm(JInternalFrame frame) {
        Dimension desktopSize = this.getSize();
        Dimension jInternalFrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
    }

    telaPrincipal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class DataHora implements Runnable {

        public void run() {
            while (true) {
                lbHorario.setText(formatoHora.format(new Date()));
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex);
                    System.exit(1);
                }
            }
        }
    }
    private double ColetaValorCusto(){
   double custo=0;
        try{
    pst = connection.prepareStatement("select sum(valorcusto) from produtos");
    rs = pst.executeQuery();
    if(rs.next())
    custo = rs.getDouble(1);
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
     return custo;
}
private int ColetaQuantidadeProduto(){
    int quantidadeProduto = 0;
  try{
    pst = connection.prepareStatement("select count(id_produto) from produtos");
    rs = pst.executeQuery();
    if(rs.next())
    quantidadeProduto = rs.getInt(1);
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
  return quantidadeProduto;
}
private double ColetaValorLucro(){
    double valorLucro = 0;
  try{
    pst = connection.prepareStatement("select sum(valorlucro) from produtos");
    rs = pst.executeQuery();
    if(rs.next())
    valorLucro = rs.getDouble(1);
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
  return valorLucro;
}
private int ColetaTotalQuantidadeProduto(){
  int quantidareProduto =0;
    try{
    pst = connection.prepareStatement("select sum(quantidade::integer) from produtos");
    rs = pst.executeQuery();
    if(rs.next())
     quantidareProduto = rs.getInt(1);
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
    return quantidareProduto;
}
private int ColetaTotalVendaRegistrada(){
int registroVenda = 0;
 try{
     pst = connection.prepareStatement("select count(codigo) from venda");
     rs= pst.executeQuery();
     if(rs.next())
     registroVenda = rs.getInt(1);
     }catch(SQLException bug){
     JOptionPane.showMessageDialog(null,bug.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
     }
 return registroVenda;
}
private int ColetaTotalEmDinheiro(){
int registroVenda = 0;
 try{
     pst = connection.prepareStatement("select count(codigo) from venda where tipo_paga like '%DINHEIRO%'");
     rs= pst.executeQuery();
     if(rs.next())
     registroVenda = rs.getInt(1);
     }catch(SQLException bug){
     JOptionPane.showMessageDialog(null,bug.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
     }
 return registroVenda;
}
private int ColetaTotalEmCredito(){
int registroVenda = 0;
 try{
     pst = connection.prepareStatement("select count(codigo) from venda where tipo_paga like '%CARTÃO CREDITO%'");
     rs= pst.executeQuery();
     if(rs.next())
     registroVenda = rs.getInt(1);
     }catch(SQLException bug){
     JOptionPane.showMessageDialog(null,bug.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
     }
 return registroVenda;
}
private int ColetaTotalEmDebito(){
int registroVenda = 0;
 try{
     pst = connection.prepareStatement("select count(codigo) from venda where tipo_paga like '%CARTÃO DEBITO%'");
     rs= pst.executeQuery();
     if(rs.next())
     registroVenda = rs.getInt(1);
     }catch(SQLException bug){
     JOptionPane.showMessageDialog(null,bug.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
     }
 return registroVenda;
}
private double ColetaTotalEmVenda(){
double registroVenda = 0;
 try{
     pst = connection.prepareStatement("select sum(valor_total) from venda");
     rs= pst.executeQuery();
     if(rs.next())
     registroVenda = rs.getInt(1);
     }catch(SQLException bug){
     JOptionPane.showMessageDialog(null,bug.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
     }
 return registroVenda;
}
private String ColetandoCaminhoRel(){
   String caminho = "";
    JFileChooser objWindows = new JFileChooser();
    objWindows.setDialogTitle("Selecione o relatorio de produtos");
    objWindows.addChoosableFileFilter(new FileNameExtensionFilter("Relatorios","jasper"));
    objWindows.setFileSelectionMode(JFileChooser.FILES_ONLY);
    if(objWindows.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
    caminho = objWindows.getSelectedFile().toString();
    }
    return caminho;
}
 public void gerarRelProduto() throws JRException , SQLException, ClassNotFoundException {
      String caminhoRelatorioProduto = ColetandoCaminho(1);
       File objVerificaRelatorio = new File(caminhoRelatorioProduto);
       if(objVerificaRelatorio.exists() == true){
         JOptionPane.showMessageDialog(null,"Relatorio sendo gerado,aguarde um momento!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        //Image logo = new ImageIcon(getClass().getResource("/novoIcones/LOGO.png")).getImage();
        Hashtable parametros = new Hashtable();
        parametros.put("totalCadastrados",String.valueOf(ColetaQuantidadeProduto()));
        parametros.put("totalCusto",String.valueOf(ColetaValorCusto()));
        parametros.put("totalLucro",String.valueOf(ColetaValorLucro()));
        parametros.put("totalQuantidadeProdutos",String.valueOf(ColetaTotalQuantidadeProduto()));
        JasperReport objTestando = (JasperReport) JRLoader.loadObject(new File(caminhoRelatorioProduto));
        JasperPrint jp = JasperFillManager.fillReport(objTestando, parametros, connection);
        JasperViewer jv = new JasperViewer(jp, false);   
        jv.setExtendedState(Frame.MAXIMIZED_BOTH);
        jv.setVisible(true);
         }else{
        JOptionPane.showMessageDialog(null,"Relatorio não encontrado!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        CadastrarCaminhoRel(1);
        gerarRelProduto();
        }
 }
 private String ColetandoCaminho(int id){
 String pegandoUrl = "";
     switch(id){
 case 1: 
 try{
 pst = connection.prepareStatement("select url from relacaminho where id=?");
 pst.setInt(1,1);
 rs = pst.executeQuery();
 if(rs.next()){
 pegandoUrl = rs.getString(1);
 }else{
 pegandoUrl ="Vazio";   
 }
 }catch(SQLException bug){
  JOptionPane.showMessageDialog(null,bug.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);  
 }
  break;
 case 2: 
 try{
 pst = connection.prepareStatement("select url from relacaminho where id=?");
 pst.setInt(1,2);
 rs = pst.executeQuery();
 if(rs.next()){
 pegandoUrl = rs.getString(1);
 }else{
 pegandoUrl ="Vazio";   
 }
 }catch(SQLException bug){
  JOptionPane.showMessageDialog(null,bug.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);  
 }
 break;
 case 3: 
 try{
 pst = connection.prepareStatement("select url from relacaminho where id=?");
 pst.setInt(1,3);
 rs = pst.executeQuery();
 if(rs.next()){
 pegandoUrl = rs.getString(1);
 }else{
 pegandoUrl ="Vazio";   
 }
 }catch(SQLException bug){
  JOptionPane.showMessageDialog(null,bug.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);  
 }
     break;
 default:
 break;
 }
     return pegandoUrl;
 }
 public void CadastrarCaminhoRel(int id){
 String caminhoRel = "";
 switch(id){
     case 1:
         try{        
         do{
         caminhoRel = ColetandoCaminhoRel();       
         }while(caminhoRel.equals(""));
         pst = connection.prepareStatement("update relacaminho set url = ? where id = ?");
         pst.setString(1,caminhoRel);
         pst.setInt(2,1);
         pst.executeUpdate();    
         }catch(SQLException bug){
         JOptionPane.showMessageDialog(null,bug.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
         }    
         break;
     case 2:
         try{
         caminhoRel = ColetandoCaminhoRel();
         pst = connection.prepareStatement("update relacaminho set url = ? where id = ?");
         pst.setString(1,caminhoRel);
         pst.setInt(2,2);
         pst.executeUpdate();
         }catch(SQLException bug){
         JOptionPane.showMessageDialog(null,bug.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
         }
         break;
        case 3:
         try{
         caminhoRel = ColetandoCaminhoRel();
         pst = connection.prepareStatement("update relacaminho set url = ? where id = ?");
         pst.setString(1,caminhoRel);
         pst.setInt(2,3);
         pst.executeUpdate();
         }catch(SQLException bug){
         JOptionPane.showMessageDialog(null,bug.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
         }
         break;
        default:
        break;
 }
 }
 public void gerarRelVendaTodas() throws JRException , SQLException, ClassNotFoundException {
        Hashtable parametros = new Hashtable();
        parametros.put("totalVendaCadastrada",String.valueOf(ColetaTotalVendaRegistrada()));
        parametros.put("totalEmDinheiro",String.valueOf(ColetaTotalEmDinheiro()));
        parametros.put("totalEmCartaoCredito",String.valueOf(ColetaTotalEmCredito()));
        parametros.put("totalEmCartaoDebito",String.valueOf(ColetaTotalEmDebito()));
        parametros.put("totalValorTotal",String.valueOf(ColetaTotalEmVenda()));
        JasperPrint jp = JasperFillManager.fillReport("relatorios/MyReports/RelatorioVendas.jasper", parametros, connection);
        JasperViewer jv = new JasperViewer(jp, false);        
        jv.setExtendedState(Frame.MAXIMIZED_BOTH);
        jv.setVisible(true);

 }
 public void gerarRelVendaDATA() throws JRException , SQLException, ClassNotFoundException {
        JOptionPane.showMessageDialog(null,"Relatorio sendo gerado,aguarde um momento!","Aviso",JOptionPane.INFORMATION_MESSAGE);  
        Hashtable parametros = new Hashtable();
        SimpleDateFormat objFor = new SimpleDateFormat("yyyy-MM-dd");
        parametros.put("totalVendaCadastrada",String.valueOf(ColetaTotalVendaRegistrada()));
        parametros.put("totalEmDinheiro",String.valueOf(ColetaTotalEmDinheiro()));
        parametros.put("totalEmCartaoCredito",String.valueOf(ColetaTotalEmCredito()));
        parametros.put("totalEmCartaoDebito",String.valueOf(ColetaTotalEmDebito()));
        parametros.put("totalValorTotal",String.valueOf(ColetaTotalEmVenda()));
        parametros.put("dataInicial",dcInicio.getDate());
        parametros.put("dataFinal",dcFinal.getDate());
        JasperPrint jp = JasperFillManager.fillReport("relatorios/MyReports/RelatorioVendasDATA.jasper", parametros, connection);
        JasperViewer jv = new JasperViewer(jp, false);        
        jv.setExtendedState(Frame.MAXIMIZED_BOTH);
        jv.setVisible(true);

 }
 public void RelatoriosVenda(int opcao){
 switch(opcao){
     case 1:
 {
     try {
         gerarRelVendaTodas();
     } catch (JRException ex) {
         Logger.getLogger(telaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SQLException ex) {
         Logger.getLogger(telaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(telaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
     }
 }
         break;
     case 2:
         int opcaoRel[] = new int[2];
         do{
         opcaoRel[0] = JOptionPane.showConfirmDialog(null,dcInicio,"selecione a data inicial",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
         opcaoRel[1] = JOptionPane.showConfirmDialog(null,dcFinal,"selecione a data final",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
         }while(opcaoRel[0] == JOptionPane.YES_OPTION && dcInicio.getDate() == null || opcaoRel[1] == JOptionPane.YES_OPTION && dcFinal.getDate() == null);
     if(dcInicio.getDate() != null && dcFinal != null){
         try {
         gerarRelVendaDATA();
     } catch (JRException ex) {
         Logger.getLogger(telaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SQLException ex) {
         Logger.getLogger(telaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(telaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
     }
     }
         break;
 }
 }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageIcon icon = new ImageIcon(getClass().getResource("/imagens/fundoInicial.png"));
        final Image image = icon.getImage();
        Desktop = new javax.swing.JDesktopPane(){

            public void paintComponent(Graphics g){
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }

        };
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelUser = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbHorario = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnCadasatrado = new javax.swing.JMenu();
        cadProdutos = new javax.swing.JMenuItem();
        cadFuncionarios = new javax.swing.JMenuItem();
        cadFornecedores = new javax.swing.JMenu();
        cadFornFisico = new javax.swing.JMenuItem();
        cadFornJuridico = new javax.swing.JMenuItem();
        mnGerencia = new javax.swing.JMenu();
        GenProdutos = new javax.swing.JMenuItem();
        GenFuncionario = new javax.swing.JMenuItem();
        GenFornecedores = new javax.swing.JMenu();
        GenFornFisico = new javax.swing.JMenuItem();
        GenFornJuridico = new javax.swing.JMenuItem();
        GenVendas = new javax.swing.JMenu();
        GenVendasRealizadas = new javax.swing.JMenuItem();
        GenProdutosVendidos = new javax.swing.JMenuItem();
        mnRelatorios = new javax.swing.JMenu();
        RelProdutos = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        mnCaixa = new javax.swing.JMenu();
        Caixa = new javax.swing.JMenuItem();
        mnConfiguracao = new javax.swing.JMenu();
        Usuarios = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Principal");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Bem Vindo");

        jLabelUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelUser.setText("usuario");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Versao 1.0.0");

        lbHorario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbHorario.setText("hora");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelUser)
                .addGap(106, 106, 106)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addComponent(lbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelUser)
                    .addComponent(jLabel3)
                    .addComponent(lbHorario)))
        );

        Desktop.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DesktopLayout.createSequentialGroup()
                .addGap(0, 290, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mnCadasatrado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/cadastroMenu.png"))); // NOI18N
        mnCadasatrado.setText("Cadastro");
        mnCadasatrado.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        cadProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/produtosMenu.png"))); // NOI18N
        cadProdutos.setText("Produto");
        cadProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadProdutosActionPerformed(evt);
            }
        });
        mnCadasatrado.add(cadProdutos);

        cadFuncionarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/funcionarioMenu.png"))); // NOI18N
        cadFuncionarios.setText("Funcionario");
        cadFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadFuncionariosActionPerformed(evt);
            }
        });
        mnCadasatrado.add(cadFuncionarios);

        cadFornecedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/fornecedoresMenu.png"))); // NOI18N
        cadFornecedores.setText("Fornecedor");

        cadFornFisico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/fisicoMenu.png"))); // NOI18N
        cadFornFisico.setText("Fisico(Pessoa)");
        cadFornFisico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadFornFisicoActionPerformed(evt);
            }
        });
        cadFornecedores.add(cadFornFisico);

        cadFornJuridico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/juridicoMenu.png"))); // NOI18N
        cadFornJuridico.setText("Juridico(Empresa)");
        cadFornJuridico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadFornJuridicoActionPerformed(evt);
            }
        });
        cadFornecedores.add(cadFornJuridico);

        mnCadasatrado.add(cadFornecedores);

        jMenuBar1.add(mnCadasatrado);

        mnGerencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/gerenciaMenu.png"))); // NOI18N
        mnGerencia.setText("Gerencia");

        GenProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/produtosMenu.png"))); // NOI18N
        GenProdutos.setText("Produtos");
        GenProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenProdutosActionPerformed(evt);
            }
        });
        mnGerencia.add(GenProdutos);

        GenFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/funcionarioMenu.png"))); // NOI18N
        GenFuncionario.setText("Funcionarios");
        GenFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenFuncionarioActionPerformed(evt);
            }
        });
        mnGerencia.add(GenFuncionario);

        GenFornecedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/fornecedoresMenu.png"))); // NOI18N
        GenFornecedores.setText("Fornecedores");

        GenFornFisico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/fisicoMenu.png"))); // NOI18N
        GenFornFisico.setText("Fisico(Pessoa)");
        GenFornFisico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenFornFisicoActionPerformed(evt);
            }
        });
        GenFornecedores.add(GenFornFisico);

        GenFornJuridico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/juridicoMenu.png"))); // NOI18N
        GenFornJuridico.setText("Juridico(Empresa)");
        GenFornJuridico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenFornJuridicoActionPerformed(evt);
            }
        });
        GenFornecedores.add(GenFornJuridico);

        mnGerencia.add(GenFornecedores);

        GenVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/vendasMenu.png"))); // NOI18N
        GenVendas.setText("Vendas/Itens Vendidos");
        GenVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenVendasActionPerformed(evt);
            }
        });

        GenVendasRealizadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/vendasMenu.png"))); // NOI18N
        GenVendasRealizadas.setText("Vendas");
        GenVendasRealizadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenVendasRealizadasActionPerformed(evt);
            }
        });
        GenVendas.add(GenVendasRealizadas);

        GenProdutosVendidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/produtosMenu.png"))); // NOI18N
        GenProdutosVendidos.setText("Itens Vendidos");
        GenProdutosVendidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenProdutosVendidosActionPerformed(evt);
            }
        });
        GenVendas.add(GenProdutosVendidos);

        mnGerencia.add(GenVendas);

        jMenuBar1.add(mnGerencia);

        mnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/relatorioMenu.png"))); // NOI18N
        mnRelatorios.setText("Relatorios");

        RelProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/produtosMenu.png"))); // NOI18N
        RelProdutos.setText("Produtos");
        RelProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RelProdutosActionPerformed(evt);
            }
        });
        mnRelatorios.add(RelProdutos);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/vendasMenu.png"))); // NOI18N
        jMenu1.setText("Vendas");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/relatorioMenuPequeno.png"))); // NOI18N
        jMenuItem1.setText("Todas as vendas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/relatorioMenuPequeno.png"))); // NOI18N
        jMenuItem2.setText("Data da venda");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        mnRelatorios.add(jMenu1);

        jMenuBar1.add(mnRelatorios);

        mnCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/vendaMenu.png"))); // NOI18N
        mnCaixa.setText("Venda");

        Caixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pdv.png"))); // NOI18N
        Caixa.setText("Caixa");
        Caixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaixaActionPerformed(evt);
            }
        });
        mnCaixa.add(Caixa);

        jMenuBar1.add(mnCaixa);

        mnConfiguracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/configuracaoMenu.png"))); // NOI18N
        mnConfiguracao.setText("Configuração");

        Usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/usuarios24.png"))); // NOI18N
        Usuarios.setText("Usuarios");
        Usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuariosActionPerformed(evt);
            }
        });
        mnConfiguracao.add(Usuarios);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/grupoMenu.png"))); // NOI18N
        jMenuItem3.setText("Grupos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mnConfiguracao.add(jMenuItem3);

        jMenuBar1.add(mnConfiguracao);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Desktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Desktop)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cadProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadProdutosActionPerformed
 if(Obj.getNivel().equals("ADMIN") || Obj.getNivel().equals("GERENCIA")){
        try {
            cadastroProduto frm;
            frm = new cadastroProduto();
            Desktop.add(frm);
            frm.setVisible(true);
            centralizaForm(frm);
        } catch (SQLException ex) {
            Logger.getLogger(telaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
 }else{
 JOptionPane.showMessageDialog(null,"Acesso não permitido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }
    }//GEN-LAST:event_cadProdutosActionPerformed

    private void UsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuariosActionPerformed
if(Obj.getNivel().equals("ADMIN")){
       
                    cadastroUsuario frm;
    try {
        frm = new cadastroUsuario();
          Desktop.add(frm);
                    frm.setVisible(true);
                    centralizaForm(frm);
    } catch (SQLException ex) {
        Logger.getLogger(telaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
    }
                  

}else{
 JOptionPane.showMessageDialog(null,"Acesso não permitido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }
    }//GEN-LAST:event_UsuariosActionPerformed

    private void cadFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadFuncionariosActionPerformed
if(Obj.getNivel().equals("ADMIN")){
                    cadastroFuncionario frm;
                    frm = new cadastroFuncionario();
                    Desktop.add(frm);
                    frm.setVisible(true);
                    centralizaForm(frm);
}else{
 JOptionPane.showMessageDialog(null,"Acesso não permitido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }
    }//GEN-LAST:event_cadFuncionariosActionPerformed

    private void CaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaixaActionPerformed
     if(Obj.getNivel().equals("ADMIN") || Obj.getNivel().equals("CAIXA")){  
        telaVenda objTelaVenda = new telaVenda();
        objTelaVenda.setVisible(true);
        objTelaVenda.setLocationRelativeTo(null); 
}else{
 JOptionPane.showMessageDialog(null,"Acesso não permitido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }
    }//GEN-LAST:event_CaixaActionPerformed

    private void GenProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenProdutosActionPerformed
if(Obj.getNivel().equals("ADMIN") || Obj.getNivel().equals("GERENCIA")){
   frmGerenciarProdutos objGeProdutos = new frmGerenciarProdutos();
   Desktop.add(objGeProdutos);
   objGeProdutos.setVisible(true);
   centralizaForm(objGeProdutos); 
 }else{
 JOptionPane.showMessageDialog(null,"Acesso não permitido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }
    }//GEN-LAST:event_GenProdutosActionPerformed

    private void GenFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenFuncionarioActionPerformed
      if(Obj.getNivel().equals("ADMIN")){
      frmGerenciaFuncionar obj = new frmGerenciaFuncionar();
        Desktop.add(obj);
        obj.setVisible(true);
        centralizaForm(obj);
 }else{
 JOptionPane.showMessageDialog(null,"Acesso não permitido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }
    }//GEN-LAST:event_GenFuncionarioActionPerformed

    private void cadFornFisicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadFornFisicoActionPerformed
   if(Obj.getNivel().equals("ADMIN") || Obj.getNivel().equals("GERENCIA")){
    frmCadFunFisico obj = new frmCadFunFisico();
     Desktop.add(obj);
     obj.setVisible(true);
     centralizaForm(obj);
 }else{
 JOptionPane.showMessageDialog(null,"Acesso não permitido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }
      
    }//GEN-LAST:event_cadFornFisicoActionPerformed

    private void GenFornFisicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenFornFisicoActionPerformed
      if(Obj.getNivel().equals("ADMIN") || Obj.getNivel().equals("GERENCIA")){
  frmGerenciarFornFisico obj = new frmGerenciarFornFisico();
       Desktop.add(obj);
       obj.setVisible(true);
        centralizaForm(obj);
 }else{
 JOptionPane.showMessageDialog(null,"Acesso não permitido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }    
    }//GEN-LAST:event_GenFornFisicoActionPerformed

    private void GenFornJuridicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenFornJuridicoActionPerformed
  if(Obj.getNivel().equals("ADMIN") || Obj.getNivel().equals("GERENCIA")){
  frmGerenciarFornJuridico obj = new frmGerenciarFornJuridico();
  Desktop.add(obj);
  obj.setVisible(true);
  centralizaForm(obj);
 }else{
 JOptionPane.showMessageDialog(null,"Acesso não permitido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }  
    }//GEN-LAST:event_GenFornJuridicoActionPerformed

    private void cadFornJuridicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadFornJuridicoActionPerformed
      if(Obj.getNivel().equals("ADMIN") || Obj.getNivel().equals("GERENCIA")){
    frmCadFunJuridico obj = new frmCadFunJuridico();
       Desktop.add(obj);
       obj.setVisible(true);
        centralizaForm(obj);
 }else{
 JOptionPane.showMessageDialog(null,"Acesso não permitido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }
    }//GEN-LAST:event_cadFornJuridicoActionPerformed

    private void RelProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RelProdutosActionPerformed
        if(Obj.getNivel().equals("ADMIN") || Obj.getNivel().equals("GERENCIA")){
  if(JOptionPane.showConfirmDialog(null,"Gerar relatorio de produtos?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
      try {
          gerarRelProduto();
      } catch (JRException ex) {
          Logger.getLogger(telaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SQLException ex) {
          Logger.getLogger(telaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(telaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
 }else{
 JOptionPane.showMessageDialog(null,"Acesso não permitido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }  
    }//GEN-LAST:event_RelProdutosActionPerformed

    private void GenVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenVendasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GenVendasActionPerformed

    private void GenVendasRealizadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenVendasRealizadasActionPerformed
     if(Obj.getNivel().equals("ADMIN") || Obj.getNivel().equals("GERENCIA")){
   frmGerenciaVendas obj = new frmGerenciaVendas();
     Desktop.add(obj);
     obj.setVisible(true);
        centralizaForm(obj);
 }else{
 JOptionPane.showMessageDialog(null,"Acesso não permitido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }   
    }//GEN-LAST:event_GenVendasRealizadasActionPerformed

    private void GenProdutosVendidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenProdutosVendidosActionPerformed
    if(Obj.getNivel().equals("ADMIN") || Obj.getNivel().equals("GERENCIA")){
   frmGerenciaProdutosVendidos obj = new frmGerenciaProdutosVendidos();
      Desktop.add(obj);
      obj.setVisible(true);
        centralizaForm(obj);
 }else{
 JOptionPane.showMessageDialog(null,"Acesso não permitido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }
    }//GEN-LAST:event_GenProdutosVendidosActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    if(Obj.getNivel().equals("ADMIN") || Obj.getNivel().equals("GERENCIA")){
    if(JOptionPane.showConfirmDialog(null,"Gerar relatorio com todas as vendas?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
        JOptionPane.showMessageDialog(null,"Relatorio sendo gerado,aguarde um momento!","Aviso",JOptionPane.INFORMATION_MESSAGE);
        RelatoriosVenda(1);
    }
 }else{
 JOptionPane.showMessageDialog(null,"Acesso não permitido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
     if(Obj.getNivel().equals("ADMIN") || Obj.getNivel().equals("GERENCIA")){
    if(JOptionPane.showConfirmDialog(null,"Gerar relatorio com data da venda??","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
        RelatoriosVenda(2);
    }
 }else{
 JOptionPane.showMessageDialog(null,"Acesso não permitido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
 if(Obj.getNivel().equals("ADMIN") || Obj.getNivel().equals("GERENCIA")){
     frmGerenciaGrupo obj = new frmGerenciaGrupo(null, rootPaneCheckingEnabled);
        obj.setLocationRelativeTo(null);
        obj.setVisible(true);
 }else{
 JOptionPane.showMessageDialog(null,"Acesso não permitido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
 }
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Caixa;
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenuItem GenFornFisico;
    private javax.swing.JMenuItem GenFornJuridico;
    private javax.swing.JMenu GenFornecedores;
    private javax.swing.JMenuItem GenFuncionario;
    private javax.swing.JMenuItem GenProdutos;
    private javax.swing.JMenuItem GenProdutosVendidos;
    private javax.swing.JMenu GenVendas;
    private javax.swing.JMenuItem GenVendasRealizadas;
    private javax.swing.JMenuItem RelProdutos;
    private javax.swing.JMenuItem Usuarios;
    private javax.swing.JMenuItem cadFornFisico;
    private javax.swing.JMenuItem cadFornJuridico;
    private javax.swing.JMenu cadFornecedores;
    private javax.swing.JMenuItem cadFuncionarios;
    private javax.swing.JMenuItem cadProdutos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbHorario;
    private javax.swing.JMenu mnCadasatrado;
    private javax.swing.JMenu mnCaixa;
    private javax.swing.JMenu mnConfiguracao;
    private javax.swing.JMenu mnGerencia;
    private javax.swing.JMenu mnRelatorios;
    // End of variables declaration//GEN-END:variables
}
