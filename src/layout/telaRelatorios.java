package layout;

import DAO.Conexao;
import java.awt.Frame;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.pdf.*;
import java.time.Instant;

public class telaRelatorios extends javax.swing.JInternalFrame {

    Connection conec = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private Date ant, depois;
    private String arquivo,arquivoEnviar;
    private String CaminhoSalvarGeralDadosVenda="";
    private int quantidadePagina=1,quantidadeTotalBanco,totalCalculo=40,quantidadeUltimoDado=1;
    private boolean verificaFoi;
    public telaRelatorios() throws ClassNotFoundException {

        try {
            initComponents();
            conec = Conexao.conecta();
        } catch (SQLException ex) {
            Logger.getLogger(telaRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*
    private void gera_relatorio() throws JRException {
       
        ant = jDateInicial.getDate();
        depois = jDateFinal.getDate();
        Hashtable parametros = new Hashtable();
        parametros.put("Data_inicial", ant);
        parametros.put("Data_final", depois);
        JasperPrint jp = JasperFillManager.fillReport("relatorios/MyReports/relatorio_total.jasper", parametros, conec);
        JasperViewer jv = new JasperViewer(jp, false);
        jv.setExtendedState(Frame.MAXIMIZED_BOTH);
        jv.setVisible(true);

    }*/
       private void caminhoFile(boolean opcao){
     JFileChooser windows = new JFileChooser();
     windows.setFileSelectionMode(JFileChooser.FILES_ONLY);
     int identificar = windows.showSaveDialog(null);
    //JOptionPane.showMessageDialog(null,identificar);
     if(identificar == 1){ // 0 = salvar 1 = cancelar 
      
     }else{
     File arquivo =  windows.getSelectedFile();
     CaminhoSalvarGeralDadosVenda = arquivo.getPath();
     if(opcao == true){
     //GerarRelatorioVendaPDF();
     }else{
    // GerarRelatorioProdutosPDF();
     }
     }
    }
       private void caminhoFileVariasPagina(boolean opcao){
     if(quantidadePagina == 1){
     JFileChooser windows = new JFileChooser();
     windows.setFileSelectionMode(JFileChooser.FILES_ONLY);
     int identificar = windows.showSaveDialog(null);
     if(identificar == 1){ // 0 = salvar 1 = cancelar 
     totalCalculo=0;
     quantidadePagina=1;
     quantidadeTotalBanco=0;
     totalCalculo=40;
     quantidadeUltimoDado = 1;
     }else{
     File arquivo =  windows.getSelectedFile();
     CaminhoSalvarGeralDadosVenda = arquivo.getPath();
     if(opcao == true){
     //GerarRelatorioVendaPDF();
     }else{
    // GerarRelatorioProdutosVariasPaginasPDF(CaminhoSalvarGeralDadosVenda+"- Pagina "+quantidadePagina);
     }
     }
     }else{
       arquivoEnviar = CaminhoSalvarGeralDadosVenda+"- Pagina "+quantidadePagina;
     if(opcao == true){
    // GerarRelatorioVendaPDF();
     }else{
    // GerarRelatorioProdutosVariasPaginasPDF(arquivoEnviar);
     arquivoEnviar="";
     }
     }
    }
       private String Data(){
   String Data = "dd/MM/yyyy";
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
       private Double ColetarTotalVenda(){
       String sql = "select sum(valor_total) from venda where data_venda >= ? and data_venda <= ?";
       double resultado = 0.0;
       try {
                ant = jDateInicial.getDate();
                depois = jDateFinal.getDate();
                pst = conec.prepareStatement(sql);
                pst.setDate(1,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(ant)));
                pst.setDate(2,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(depois)));
                rs = pst.executeQuery();
               if(rs.next()){
               resultado = rs.getDouble(1);
               }
               
            }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Aviso sql Relatorio",JOptionPane.ERROR_MESSAGE);
            }
       return resultado;
       }

        private String ColetarTotalEmDinheiro(){
       String sql = "select count(tipo_paga) from venda where data_venda >= ? and data_venda <= ? and tipo_paga like '%DINHEIRO%'";
       String resultado = "";
       try {
                ant = jDateInicial.getDate();
                depois = jDateFinal.getDate();
                pst = conec.prepareStatement(sql);
                pst = conec.prepareStatement(sql);
                pst.setDate(1,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(ant)));
                pst.setDate(2,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(depois)));
                rs = pst.executeQuery();
               if(rs.next()){
               resultado = rs.getString(1);
               }
               
            }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Aviso sql Relatorio",JOptionPane.ERROR_MESSAGE);
            }
       return resultado;
       }
        private String ColetarTotalEmCartaoCredito(){
       String sql = "select count(tipo_paga) from venda where data_venda >= ? and data_venda <= ? and tipo_paga like '%CARTÃO CREDITO%'";
       String resultado = "";
       try {
                ant = jDateInicial.getDate();
                depois = jDateFinal.getDate();
                pst = conec.prepareStatement(sql);
                pst = conec.prepareStatement(sql);
                pst.setDate(1,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(ant)));
                pst.setDate(2,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(depois)));
                rs = pst.executeQuery();
               if(rs.next()){
               resultado = rs.getString(1);
               }
               
            }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Aviso sql Relatorio",JOptionPane.ERROR_MESSAGE);
            }
       return resultado;
       }
        private void ChecaQuantidadeUltima(){
         try{
        pst = conec.prepareStatement("select max(id_produto) from produtos where id_produto >="+quantidadeUltimoDado+"and id_produto <="+totalCalculo);
        rs = pst.executeQuery();
        if(rs.next()){
            quantidadeUltimoDado = Integer.parseInt(rs.getString(1));
        }
        }catch(SQLException error){
        JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
        }
        }
  
        private void ChecaQuantidade(){
         try{
        pst = conec.prepareStatement("select count(id_produto) from produtos");
        rs = pst.executeQuery();
        if(rs.next()){
            quantidadeTotalBanco = Integer.parseInt(rs.getString(1));
        if(quantidadeTotalBanco > 40){
           do{
            if(quantidadePagina == 1){
            JOptionPane.showMessageDialog(null,"O RELATTORIO CONTERA MAIS DE UMA PAGINA","aVISO",JOptionPane.INFORMATION_MESSAGE);
            }else{
            //JOptionPane.showMessageDialog(null,"Escolha caminho da pagina "+quantidadePagina,"aVISO",JOptionPane.INFORMATION_MESSAGE);
            }
          caminhoFileVariasPagina(false);
          ChecaQuantidadeUltima();
          quantidadePagina++;
          quantidadeTotalBanco-=35;
          totalCalculo+=35;
          //if(totalCalculo > 40){
         // totalCalculo-=5;
         // }
         
           }while(quantidadeTotalBanco >0);
    
        }else{
            caminhoFile(false);
        }
        }
        }catch(SQLException error){
        JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
        }
        }
        private String TotalValorLucroProdutos(){
        String valot = "";
        try{
        pst = conec.prepareStatement("select sum(valorlucro) from produtos");
        rs = pst.executeQuery();
        if(rs.next())
        valot = rs.getString(1);
        }catch(SQLException error){
        JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
        }
        return valot;
        }
          private String TotalValorCustoProdutos(){
        String valot = "";
        try{
        pst = conec.prepareStatement("select sum(valorcusto) from produtos");
        rs = pst.executeQuery();
        if(rs.next())
        valot = rs.getString(1);
        }catch(SQLException error){
        JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
        }
        return valot;
        }
           private String TotalQuantidadeProdutos(){
        String valot = "";
        try{
        pst = conec.prepareStatement("select count(quantidade) from produtos");
        rs = pst.executeQuery();
        if(rs.next())
        valot = rs.getString(1);
        }catch(SQLException error){
        JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
        }
        return valot;
        }
        private String ColetarTotalEmCartaoDebito(){
       String sql = "select count(tipo_paga) from venda where data_venda >= ? and data_venda <= ? and tipo_paga like '%CARTÃO DEBITO%'";
       String resultado = "";
       try {
                ant = jDateInicial.getDate();
                depois = jDateFinal.getDate();
                pst = conec.prepareStatement(sql);
                pst = conec.prepareStatement(sql);
                pst.setDate(1,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(ant)));
                pst.setDate(2,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(depois)));
                rs = pst.executeQuery();
               if(rs.next()){
               resultado = rs.getString(1);
               }
               
            }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Aviso sql Relatorio",JOptionPane.ERROR_MESSAGE);
            }
       return resultado;
       }
        
   /* private void GerarRelatorioVendaPDF(){
        //CaminhoSalvarRelatorioDadosVendaGeral();
        Document documentoPDF = null;
        try {
          documentoPDF = new Document(PageSize.A4,0,0,0,0);
            //Criar uma instando do documento
            PdfWriter write = PdfWriter.getInstance(documentoPDF,new FileOutputStream(CaminhoSalvarGeralDadosVenda+".pdf"));
            //PdfWriter.getInstance(documentoPDF,new FileOutputStream("C:\\Users\\Su\\Desktop\\RELATORIO TESTE\\Teste.pdf"));
          //Abrir documento
            documentoPDF.open();
            //setar o tamanho da pagina
            //documentoPDF.setPageSize(PageSize.A4);//a4 é o padrão
            //Descrição TOPO
           Font CorTopoEmpresa = new Font();
           CorTopoEmpresa.setColor(BaseColor.RED);
           CorTopoEmpresa.setStyle(Font.BOLD);
           Font objTitulo = new Font();
           objTitulo.setColor(BaseColor.RED);
           objTitulo.setStyle(Font.BOLD);
           //
           Paragraph NomeEmpresa = new Paragraph("RELATÓRIO DE VENDAS",objTitulo);
           //
           PdfPTable objInfoTopo = new PdfPTable(1);
           //
           PdfPCell objTopo = new PdfPCell(NomeEmpresa);
           objTopo.setBackgroundColor(BaseColor.BLACK);
           objTopo.setHorizontalAlignment(Element.ALIGN_CENTER);
           objTopo.setColspan(1);
           objInfoTopo.addCell(objTopo);
           //
           Paragraph objMeioTitulo = new Paragraph("INFORMAÇÃO DE EMISSÃO/VENDA",objTitulo);
           //
           PdfPTable objMeio = new PdfPTable(3);
           //
           PdfPCell objMeioCAB = new PdfPCell(objMeioTitulo);
           objMeioCAB.setBackgroundColor(BaseColor.BLACK);
           objMeioCAB.setHorizontalAlignment(Element.ALIGN_CENTER);
           objMeioCAB.setColspan(3);  
           //
           Paragraph DataEmissao = new Paragraph("Data de emissão ");
           Paragraph HoraEmissao = new Paragraph("Hora de emissão ");
           Paragraph TotalVenda = new Paragraph("Total em venda");
            //
             objMeio.addCell(objMeioCAB);
             objMeio.addCell(DataEmissao);
             objMeio.addCell(HoraEmissao);
             objMeio.addCell(TotalVenda);
             //inserindo
             String data = Data();
             String hora = Hora();
             String valorTotal = String.valueOf(ColetarTotalVenda()).replace(".",",");
             objMeio.addCell(data);
             objMeio.addCell(hora);
             objMeio.addCell("R$ "+valorTotal);
            //
            PdfPTable objMeioDois = new PdfPTable(3);
            //
            PdfPCell objMeioDoisCAB = new PdfPCell(new Paragraph("INFORMAÇÃO TOTAL DE TIPO DE PAGAMENTOS",objTitulo));
            objMeioDoisCAB.setBackgroundColor(BaseColor.BLACK);
            objMeioDoisCAB.setHorizontalAlignment(Element.ALIGN_CENTER);
            objMeioDoisCAB.setColspan(3);
            PdfPCell objDinheiro = new PdfPCell(new Paragraph("DINHEIRO"));
            PdfPCell objCartaoCredito = new PdfPCell(new Paragraph("CARTÃO CREDITO"));
            PdfPCell objCartaoDebito = new PdfPCell(new Paragraph("CARTÃO DEBITO"));
            objMeioDois.addCell(objMeioDoisCAB);
            objMeioDois.addCell(objDinheiro);
            objMeioDois.addCell(objCartaoCredito);
            objMeioDois.addCell(objCartaoDebito);
            //inserindo
            String totalDinheiro = ColetarTotalEmDinheiro();
            String totalCartaoCredito = ColetarTotalEmCartaoCredito();
            String totalCartaoDebito = ColetarTotalEmCartaoDebito();
            objMeioDois.addCell(totalDinheiro);
            objMeioDois.addCell(totalCartaoCredito);
            objMeioDois.addCell(totalCartaoDebito);
             //Paragraph TotalQuantidade = new Paragraph("                 Total de vendas registradas: "+QuantidadeMaxDeDadosCadastrado);
           //Paragraph TotalVenda = new Paragraph("                 Valor total da vendas: "+ValorTotalVendas);
            Paragraph Separalinha = new Paragraph("                      ");
           //Paragrafo do cabeçalho
           Font CorTopoCelula = new Font();
           CorTopoCelula.setColor(BaseColor.RED);
           CorTopoCelula.setStyle(Font.BOLD);
           //
           Paragraph cabe = new Paragraph("LISTA COM DADOS DA VENDA",CorTopoCelula);
          //criando tabela
            PdfPTable tabela = new PdfPTable(3);
           
          //criando celulas
            PdfPCell cabecalho = new PdfPCell(cabe);
            cabecalho.setBackgroundColor(BaseColor.BLACK);
            cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);   
            cabecalho.setColspan(3);
            //
            PdfPCell TabCodVenda = new PdfPCell(new Paragraph("Data"));
            //
            PdfPCell TabCodProduto= new PdfPCell(new Paragraph("Tipo Pagamento"));
            //
            PdfPCell TabProduto = new PdfPCell(new Paragraph("Valor total no dia"));
            tabela.addCell(cabecalho);
            tabela.setWidths(new int [] {55,50,50});//Setar largura da tabela..cada valor representa um atributo
            //tabela.setWidthPercentage(100);
            tabela.addCell(TabCodVenda);
            tabela.addCell(TabCodProduto);
            tabela.addCell(TabProduto);
            //
            String sql = "select data_venda,tipo_paga,valor_total from venda where data_venda >= ? and data_venda <= ?";
            try {
                ant = jDateInicial.getDate();
                depois = jDateFinal.getDate();
                pst = conec.prepareStatement(sql);
                pst = conec.prepareStatement(sql);
                pst.setDate(1,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(ant)));
                pst.setDate(2,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(depois)));
                rs = pst.executeQuery();
                while(rs.next()){
                //---------------
                    tabela.addCell(rs.getString(1)); 
                    tabela.addCell(rs.getString(2)); 
                    tabela.addCell(rs.getString(3));
                 }
            }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Aviso sql Relatorio",JOptionPane.ERROR_MESSAGE);
            }
  
            documentoPDF.add(objInfoTopo);
            //documentoPDF.add(TipoRelatorio);
            documentoPDF.add(Separalinha);
            documentoPDF.add(objMeio);
            documentoPDF.add(Separalinha);
            documentoPDF.add(objMeioDois);
            documentoPDF.add(Separalinha);
            documentoPDF.add(tabela);
        }catch(DocumentException e){
        JOptionPane.showMessageDialog(null, e);
        }catch(IOException ioe){
        JOptionPane.showMessageDialog(null, ioe);
        }finally{
        documentoPDF.close();
        }
     
    }*/
      /*private void GerarRelatorioProdutosPDF(){
        //CaminhoSalvarRelatorioDadosVendaGeral();
        Document documentoPDF = null;
        try {
          documentoPDF = new Document(PageSize.A4,0,0,0,0);
            //Criar uma instando do documento
            PdfWriter write = PdfWriter.getInstance(documentoPDF,new FileOutputStream(CaminhoSalvarGeralDadosVenda+".pdf"));
            //PdfWriter.getInstance(documentoPDF,new FileOutputStream("C:\\Users\\Su\\Desktop\\RELATORIO TESTE\\Teste.pdf"));
          //Abrir documento
            documentoPDF.open();
            //setar o tamanho da pagina
            //documentoPDF.setPageSize(PageSize.A4);//a4 é o padrão
            //Descrição TOPO
           Font CorTopoEmpresa = new Font();
           CorTopoEmpresa.setColor(BaseColor.RED);
           CorTopoEmpresa.setStyle(Font.BOLD);
           Font objTitulo = new Font();
           objTitulo.setColor(BaseColor.RED);
           objTitulo.setStyle(Font.BOLD);
           //
           Paragraph NomeEmpresa = new Paragraph("RELATÓRIO DE PRODUTOS",objTitulo);
           //
           PdfPTable objInfoTopo = new PdfPTable(1);
           //
           PdfPCell objTopo = new PdfPCell(NomeEmpresa);
           objTopo.setBackgroundColor(BaseColor.BLACK);
           objTopo.setHorizontalAlignment(Element.ALIGN_CENTER);
           objTopo.setColspan(1);
           objInfoTopo.addCell(objTopo);
           //
           Paragraph objMeioTitulo = new Paragraph("INFORMAÇÃO DE EMISSÃO",objTitulo);
           //
           PdfPTable objMeio = new PdfPTable(2);
           //
           PdfPCell objMeioCAB = new PdfPCell(objMeioTitulo);
           objMeioCAB.setBackgroundColor(BaseColor.BLACK);
           objMeioCAB.setHorizontalAlignment(Element.ALIGN_CENTER);
           objMeioCAB.setColspan(2);  
           //
           Paragraph DataEmissao = new Paragraph("Data de emissão ");
           Paragraph HoraEmissao = new Paragraph("Hora de emissão ");
 
            //
             objMeio.addCell(objMeioCAB);
             objMeio.addCell(DataEmissao);
             objMeio.addCell(HoraEmissao);
      
             //inserindo
             String data = Data();
             String hora = Hora();
        
             objMeio.addCell(data);
             objMeio.addCell(hora);
       
            //
            PdfPTable objMeioDois = new PdfPTable(3);
            //
            PdfPCell objMeioDoisCAB = new PdfPCell(new Paragraph("INFORMAÇÃO ADICIONAIS DOS PRODUTOS",objTitulo));
            objMeioDoisCAB.setBackgroundColor(BaseColor.BLACK);
            objMeioDoisCAB.setHorizontalAlignment(Element.ALIGN_CENTER);
            objMeioDoisCAB.setColspan(3);
            PdfPCell objDinheiro = new PdfPCell(new Paragraph("tOTAL CADASTRADOS"));
            PdfPCell objCartaoCredito = new PdfPCell(new Paragraph("TOTAL EM CUSTO"));
            PdfPCell objCartaoDebito = new PdfPCell(new Paragraph("TOTAL EM LUCRO"));
            objMeioDois.addCell(objMeioDoisCAB);
            objMeioDois.addCell(objDinheiro);
            objMeioDois.addCell(objCartaoCredito);
            objMeioDois.addCell(objCartaoDebito);
            //inserindo
   
            objMeioDois.addCell(TotalQuantidadeProdutos());
            objMeioDois.addCell(TotalValorCustoProdutos());
            objMeioDois.addCell(TotalValorLucroProdutos());
             //Paragraph TotalQuantidade = new Paragraph("                 Total de vendas registradas: "+QuantidadeMaxDeDadosCadastrado);
           //Paragraph TotalVenda = new Paragraph("                 Valor total da vendas: "+ValorTotalVendas);
            Paragraph Separalinha = new Paragraph("                      ");
           //Paragrafo do cabeçalho
           Font CorTopoCelula = new Font();
           CorTopoCelula.setColor(BaseColor.RED);
           CorTopoCelula.setStyle(Font.BOLD);
           //
           Paragraph cabe = new Paragraph("LISTA COM DADOS Do PRODUTO",CorTopoCelula);
          //criando tabela
            PdfPTable tabela = new PdfPTable(5);
           
          //criando celulas
            PdfPCell cabecalho = new PdfPCell(cabe);
            cabecalho.setBackgroundColor(BaseColor.BLACK);
            cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);   
            cabecalho.setColspan(5);
         
            PdfPCell TabNome = new PdfPCell(new Paragraph("Nome"));
            PdfPCell TabGrupo = new PdfPCell(new Paragraph("Grupo"));
            PdfPCell TabQuantidade = new PdfPCell(new Paragraph("Qtde"));
            PdfPCell TabValorCusto = new PdfPCell(new Paragraph("ValCusto"));
            PdfPCell TabValorLucro = new PdfPCell(new Paragraph("ValLucro"));
            tabela.addCell(cabecalho);
            tabela.setWidths(new int [] {55,50,50,50,50});//Setar largura da tabela..cada valor representa um atributo
            //tabela.setWidthPercentage(100);
            tabela.addCell(TabNome);
            tabela.addCell(TabGrupo);
            tabela.addCell(TabQuantidade);
            tabela.addCell(TabValorCusto);
             tabela.addCell(TabValorLucro);
            //
            String sql = "select  descricao,grupoprod,quantidade,valorcusto,valorlucro from produtos";;
            try {
                ant = jDateInicial.getDate();
                depois = jDateFinal.getDate();
                pst = conec.prepareStatement(sql);
                //pst.setDate(1,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(ant)));
               // pst.setDate(2,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(depois)));
                rs = pst.executeQuery();
                while(rs.next()){
                //---------------
                    tabela.addCell(rs.getString(1)); 
                    tabela.addCell(rs.getString(2)); 
                    tabela.addCell(rs.getString(3));
                    tabela.addCell(rs.getString(4));
                     tabela.addCell(rs.getString(5));
                 }
            }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Aviso sql Relatorio",JOptionPane.ERROR_MESSAGE);
            }
  
            documentoPDF.add(objInfoTopo);
            //documentoPDF.add(TipoRelatorio);
            documentoPDF.add(Separalinha);
            documentoPDF.add(objMeio);
            documentoPDF.add(Separalinha);
            documentoPDF.add(objMeioDois);
            documentoPDF.add(Separalinha);
            documentoPDF.add(tabela);
        }catch(DocumentException e){
        JOptionPane.showMessageDialog(null, e);
        }catch(IOException ioe){
        JOptionPane.showMessageDialog(null, ioe);
        }finally{
        documentoPDF.close();
        }
     
    }*//*
         private void GerarRelatorioProdutosVariasPaginasPDF(String caminho){
           
             //CaminhoSalvarRelatorioDadosVendaGeral();
        Document documentoPDF = null;
        try {
          documentoPDF = new Document(PageSize.A4,0,0,0,0);
            //Criar uma instando do documento
            PdfWriter write = PdfWriter.getInstance(documentoPDF,new FileOutputStream(caminho+".pdf"));
            //PdfWriter.getInstance(documentoPDF,new FileOutputStream("C:\\Users\\Su\\Desktop\\RELATORIO TESTE\\Teste.pdf"));
          //Abrir documento
            documentoPDF.open();
            //setar o tamanho da pagina
            //documentoPDF.setPageSize(PageSize.A4);//a4 é o padrão
            //Descrição TOPO
           Font CorTopoEmpresa = new Font();
           CorTopoEmpresa.setColor(BaseColor.RED);
           CorTopoEmpresa.setStyle(Font.BOLD);
           Font objTitulo = new Font();
           objTitulo.setColor(BaseColor.RED);
           objTitulo.setStyle(Font.BOLD);
           //
           Paragraph NomeEmpresa = new Paragraph("RELATÓRIO DE PRODUTOS",objTitulo);
           //
           PdfPTable objInfoTopo = new PdfPTable(1);
           //
           PdfPCell objTopo = new PdfPCell(NomeEmpresa);
           objTopo.setBackgroundColor(BaseColor.BLACK);
           objTopo.setHorizontalAlignment(Element.ALIGN_CENTER);
           objTopo.setColspan(1);
           objInfoTopo.addCell(objTopo);
           //
           Paragraph objPagina = new Paragraph(String.valueOf("Pagina:"+quantidadePagina));
           Paragraph objMeioTitulo = new Paragraph("INFORMAÇÃO DE EMISSÃO",objTitulo);
           //
           PdfPTable objMeio = new PdfPTable(2);
           //
           PdfPCell objMeioCAB = new PdfPCell(objMeioTitulo);
           objMeioCAB.setBackgroundColor(BaseColor.BLACK);
           objMeioCAB.setHorizontalAlignment(Element.ALIGN_CENTER);
           objMeioCAB.setColspan(2);  
           //
           Paragraph DataEmissao = new Paragraph("Data de emissão ");
           Paragraph HoraEmissao = new Paragraph("Hora de emissão ");
 
            //
             objMeio.addCell(objMeioCAB);
             objMeio.addCell(DataEmissao);
             objMeio.addCell(HoraEmissao);
      
             //inserindo
             String data = Data();
             String hora = Hora();
        
             objMeio.addCell(data);
             objMeio.addCell(hora);
       
            //
            PdfPTable objMeioDois = new PdfPTable(3);
            //
            PdfPCell objMeioDoisCAB = new PdfPCell(new Paragraph("INFORMAÇÃO ADICIONAIS DOS PRODUTOS",objTitulo));
            objMeioDoisCAB.setBackgroundColor(BaseColor.BLACK);
            objMeioDoisCAB.setHorizontalAlignment(Element.ALIGN_CENTER);
            objMeioDoisCAB.setColspan(3);
            PdfPCell objDinheiro = new PdfPCell(new Paragraph("TOTAL CADASTRADOS"));
            PdfPCell objCartaoCredito = new PdfPCell(new Paragraph("TOTAL EM CUSTO"));
            PdfPCell objCartaoDebito = new PdfPCell(new Paragraph("TOTAL EM LUCRO"));
            objMeioDois.addCell(objMeioDoisCAB);
            objMeioDois.addCell(objDinheiro);
            objMeioDois.addCell(objCartaoCredito);
            objMeioDois.addCell(objCartaoDebito);
            //inserindo
   
            objMeioDois.addCell(TotalQuantidadeProdutos());
            objMeioDois.addCell("R$ "+TotalValorCustoProdutos().replace(".",","));
            objMeioDois.addCell("R$ "+TotalValorLucroProdutos().replace(".",","));
             //Paragraph TotalQuantidade = new Paragraph("                 Total de vendas registradas: "+QuantidadeMaxDeDadosCadastrado);
           //Paragraph TotalVenda = new Paragraph("                 Valor total da vendas: "+ValorTotalVendas);
            Paragraph Separalinha = new Paragraph("                      ");
           //Paragrafo do cabeçalho
           Font CorTopoCelula = new Font();
           CorTopoCelula.setColor(BaseColor.RED);
           CorTopoCelula.setStyle(Font.BOLD);
           //
           Paragraph cabe = new Paragraph("LISTA COM DADOS Do PRODUTO",CorTopoCelula);
          //criando tabela
            PdfPTable tabela = new PdfPTable(5);
           
          //criando celulas
            PdfPCell cabecalho = new PdfPCell(cabe);
            cabecalho.setBackgroundColor(BaseColor.BLACK);
            cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);   
            cabecalho.setColspan(5);
         
            PdfPCell TabNome = new PdfPCell(new Paragraph("Nome"));
            PdfPCell TabGrupo = new PdfPCell(new Paragraph("Grupo"));
            PdfPCell TabQuantidade = new PdfPCell(new Paragraph("Qtde"));
            PdfPCell TabValorCusto = new PdfPCell(new Paragraph("ValCusto"));
            PdfPCell TabValorLucro = new PdfPCell(new Paragraph("ValLucro"));
            tabela.addCell(cabecalho);
            tabela.setWidths(new int [] {55,50,50,50,50});//Setar largura da tabela..cada valor representa um atributo
            //tabela.setWidthPercentage(100);
            tabela.addCell(TabNome);
            tabela.addCell(TabGrupo);
            tabela.addCell(TabQuantidade);
            tabela.addCell(TabValorCusto);
             tabela.addCell(TabValorLucro);
            //
            String sql = " select  id_produto,descricao,grupoprod,quantidade,valorcusto,valorlucro from produtos where id_produto >="+String.valueOf(quantidadeUltimoDado)+"and id_produto <="+String.valueOf(totalCalculo);
            try {
                ant = jDateInicial.getDate();
                depois = jDateFinal.getDate();
                pst = conec.prepareStatement(sql);
                //pst.setDate(1,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(ant)));
               // pst.setDate(2,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(depois)));
                rs = pst.executeQuery();
                while(rs.next()){
                //---------------
                    tabela.addCell(rs.getString(2)); 
                    tabela.addCell(rs.getString(3)); 
                    tabela.addCell(rs.getString(4));
                    tabela.addCell(rs.getString(5));
                    tabela.addCell(rs.getString(6));
                 }
            }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Aviso sql Relatorio",JOptionPane.ERROR_MESSAGE);
            }
  
            documentoPDF.add(objInfoTopo);
            //documentoPDF.add(TipoRelatorio);
            documentoPDF.add(Separalinha);
            documentoPDF.add(objMeio);
            documentoPDF.add(Separalinha);
            documentoPDF.add(objMeioDois);
            documentoPDF.add(Separalinha);
            documentoPDF.add(tabela);
            documentoPDF.add(objPagina);
        }catch(DocumentException e){
        JOptionPane.showMessageDialog(null, e);
        }catch(IOException ioe){
        JOptionPane.showMessageDialog(null, ioe);
        }finally{
        documentoPDF.close();
        }
     
    }*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jDateFinal = new com.toedter.calendar.JDateChooser();
        jDateInicial = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Tela de Relatorios");

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Relatorio de Produtos");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ajuste24.png"))); // NOI18N
        jButton1.setText("Relatorio Venda");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Data Final");

        jLabel3.setText("Data Inicial");

        jButton2.setText("Relatorio Produto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateInicial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(72, 72, 72))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if ("".equals(jDateInicial.getName())) {
            JOptionPane.showMessageDialog(null, "Entre com a data inicial");
        } else if ("".equals(jDateFinal.getName())) {
            JOptionPane.showMessageDialog(null, "Entre com a data final");
        }
        /*try {
            gera_relatorio();
        } catch (JRException ex) {
            Logger.getLogger(telaRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }*/
       caminhoFile(true);
       dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

   ChecaQuantidade();        
//caminhoFile(false);
       //dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateFinal;
    private com.toedter.calendar.JDateChooser jDateInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
