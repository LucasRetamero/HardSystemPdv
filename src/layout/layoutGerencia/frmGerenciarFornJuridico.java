/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout.layoutGerencia;

import DAO.Conexao;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import java.sql.*;
import javax.swing.JFileChooser;
import layoutInterno.buscaEmpresa;
import layoutInterno.frmBuscarForneJuridico;
import layoutInterno.buscaFornecedor;
import javax.swing.ButtonGroup;
import layoutCadastro.cadastroProduto;
import layoutCadastro.frmCadFornecedor;

/**
 *
 * @author Su
 */
public class frmGerenciarFornJuridico extends javax.swing.JInternalFrame {
 Connection conec = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String filename;
    String guardaFoto;
    int img = 0,verificaImg = 0;
    ButtonGroup objFisico,objJuridico;
    frmBuscarForneJuridico objBuscarForne;
    char status='S';
    public frmGerenciarFornJuridico() {
        initComponents();
        try{
            conec = Conexao.conecta();
        }catch(ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
        }catch(SQLException ex) {
           JOptionPane.showMessageDialog(null,ex.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
        }
        objJuridico = new ButtonGroup();
        objJuridico.add(rbOnJuridico);
        objJuridico.add(rbOffJuridico);
        telefone();
    }
     private void ColetaDadosJuridico(){
   txtCodigoJuridico.setText(""+objBuscarForne.getCodigoJuridico());
   txtNomeJuridico.setText(objBuscarForne.getNomeJuridico());
   txtTelefoneJurid.setText(objBuscarForne.getTelJuridico());
   txtCelularJuridi.setText(objBuscarForne.getCelJuridico());
   txtEnderecoJuridico.setText(objBuscarForne.getEnderecoJuridico());
   txtBairroJuridico.setText(objBuscarForne.getBairroJuridico());
   txtCepJuridico.setText(objBuscarForne.getCepJuridico());
   txtCnpj.setText(objBuscarForne.getCnpj());
   if(objBuscarForne.getStatusJuridico().equals("S")){
  rbOnJuridico.setSelected(true);
   }else{
   rbOffJuridico.setSelected(true);
   }
   if(objBuscarForne.getImgJuridico().equals("/foto/semFoto.png") || objBuscarForne.getImgJuridico().equals("")){
    ImageIcon imgPadrao = new ImageIcon(getClass().getResource("/foto/semFoto.png"));
    lbFotoJuridico.setIcon(imgPadrao);
   }else{
   ImageIcon imgPadrao = new ImageIcon(objBuscarForne.getImgJuridico());
   lbFotoJuridico.setIcon(new ImageIcon(imgPadrao.getImage().getScaledInstance(135,137,Image.SCALE_DEFAULT)));
   }
   }
    protected void TratamentoJuridico(int troll){
    if(txtCodigoJuridico.getText().equals("")){
   JOptionPane.showMessageDialog(null,"Campo Codigo vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
    }else if(txtNomeJuridico.getText().equals("")){
    JOptionPane.showMessageDialog(null,"Campo Nome vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
  }else{
  switch(troll){
      case 1:
         DeletarJuridico();
         break;
      case 2:
          if(JOptionPane.showConfirmDialog(null,"Editar fornecedor juridico(Empresa)?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
          EditarJuridico();
          }
          break;
  }
  }
  }
       private void EditarJuridico() {
          if(img == 0){
            pegarImgAntesEditarJuridico(Integer.parseInt(txtCodigoJuridico.getText()));
            }
         if(rbOnJuridico.isSelected()){
            status = 'S';
            }else if(rbOffJuridico.isSelected()){
            status = 'N';
            }else{
            status = 'S';
            }
         String sql = "update fornecedorjuridico set nome=?,telefone=?,celular=?,endereco=?,bairro=?,cep=?,cnpj=?,img=?,status=? where idfornjuridico=?";
          try {
            pst = conec.prepareStatement(sql);
            pst.setString(1,txtNomeJuridico.getText());
            pst.setString(2,txtTelefoneJurid.getText().replace("(", "").replace(")", "").replace("-", ""));
            pst.setString(3,txtCelularJuridi.getText().replace("(", "").replace(")", "").replace("-", ""));
            pst.setString(4,txtEnderecoJuridico.getText());
            pst.setString(5,txtBairroJuridico.getText());
            pst.setString(6,txtCepJuridico.getText().replace("-",""));
            pst.setString(7,txtCnpj.getText().replace(".","").replace(".","").replace("/","").replace("-",""));
            pst.setString(8,filename);
            pst.setString(9,String.valueOf(status));
            pst.setInt(10,Integer.parseInt(txtCodigoJuridico.getText()));
            if(pst.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Fornecedor Juridico Atualizado", "Fornecedor Atualizado", JOptionPane.INFORMATION_MESSAGE);
            limparCamposJuridico();
            }
            } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
        }
     }
            public void pegarImgAntesEditarJuridico(int pk){
    try{
    pst = conec.prepareStatement(" select img from fornecedorjuridico where idfornjuridico = ?");
    pst.setInt(1,pk);
    rs = pst.executeQuery();
    if(rs.next()){
    filename = rs.getString(1);
    }
    }catch(SQLException e){
    JOptionPane.showMessageDialog(null,e.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
    }
         private void limparCamposJuridico() {
         txtCodigoJuridico.setText("");
        txtNomeJuridico.setText("");
        txtTelefoneJurid.setText("");
        txtCelularJuridi.setText("");
        txtEnderecoJuridico.setText("");
        txtBairroJuridico.setText("");
        txtCepJuridico.setText("");
        txtCnpj.setText("");
        objJuridico.clearSelection();
         filename="";
        ImageIcon IMGpadrao = new ImageIcon(this.getClass().getResource("/foto/semFoto.png"));
        lbFotoJuridico.setIcon(new ImageIcon(IMGpadrao.getImage().getScaledInstance(lbFotoJuridico.getWidth(), lbFotoJuridico.getHeight(), Image.SCALE_DEFAULT)));
        img=0;    
}
          private void telefone() {

        MaskFormatter mascaraTelefoneFisica,mascaraTelefoneJuridica,mascaraCelularJuridico,mascaraCelularFisico;
        MaskFormatter mascaraCNPJ,mascaraCPF,mascaraCEPFisico,mascaraCEPJuridico;
        try {
   
            mascaraTelefoneJuridica = new MaskFormatter("(##)####-####");
            mascaraTelefoneJuridica.setValidCharacters("0123456789");
            mascaraTelefoneJuridica.install(txtTelefoneJurid);
            //
            mascaraCelularJuridico = new MaskFormatter("(##)#####-####");
            mascaraCelularJuridico.setValidCharacters("0123456789");
            mascaraCelularJuridico.install(txtCelularJuridi);
            //--      
            mascaraCEPJuridico = new MaskFormatter("#####-###");
            mascaraCEPJuridico.setValidCharacters("0123456789");
            mascaraCEPJuridico.install(txtCepJuridico);
            //
            mascaraCNPJ = new MaskFormatter("##.###.###/####-##");
            mascaraCNPJ.setValidCharacters("0123456789");
            mascaraCNPJ.install(txtCnpj);
        } catch (Exception ex) {
            Logger.getLogger(frmCadFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
           private void buscaImagem (){
        
        try {
            pst = conec.prepareStatement("select foto from fornecedor where id_fornecedor = ?");
            pst.setInt(1, Integer.parseInt(txtCodigoJuridico.getText()));
            rs = pst.executeQuery();
            if(rs.next()){
                filename = String.valueOf(rs.getString(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(cadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public void DeletarJuridico(){
    if(JOptionPane.showConfirmDialog(null,"Deletar Fornecedor Juridico(Empresa)","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
    try{
    pst= conec.prepareStatement("delete from fornecedorjuridico where idfornjuridico = ?");
    pst.setInt(1,Integer.parseInt(txtCodigoJuridico.getText()));
    if(pst.executeUpdate() > 0){
    JOptionPane.showMessageDialog(null,"Fornecedor Juridico(Empresa) Deletado");
    limparCamposJuridico();
    }
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtCodigoJuridico = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNomeJuridico = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtEnderecoJuridico = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtBairroJuridico = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTelefoneJurid = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCelularJuridi = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        txtCepJuridico = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        txtCnpj = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        lbFotoJuridico = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rbOnJuridico = new javax.swing.JCheckBox();
        rbOffJuridico = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setClosable(true);
        setTitle("Gerenciar fornecedor juridico");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setText("Codigo");

        txtCodigoJuridico.setEditable(false);
        txtCodigoJuridico.setBackground(new java.awt.Color(153, 153, 153));
        txtCodigoJuridico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoJuridicoActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome");

        jLabel11.setText("Endereço");

        jLabel12.setText("Bairro");

        jLabel9.setText("Telefone");

        jLabel10.setText("Celular");

        try {
            txtCelularJuridi.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCelularJuridi.setText("");

        jLabel13.setText("CEP");

        try {
            txtCepJuridico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel14.setText("CNPJ:");

        jLabel2.setText("Imagem do fornecedor juridico");

        lbFotoJuridico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/semFoto.png"))); // NOI18N

        jLabel3.setText("Status");

        rbOnJuridico.setText("Ativo");

        rbOffJuridico.setText("Desativado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15)
                    .addComponent(txtCodigoJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomeJuridico)
                    .addComponent(jLabel11)
                    .addComponent(txtEnderecoJuridico)
                    .addComponent(jLabel12)
                    .addComponent(txtBairroJuridico)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(106, 106, 106)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTelefoneJurid, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtCelularJuridi, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)))
                    .addComponent(jLabel13)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCepJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCnpj)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lbFotoJuridico)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbOnJuridico)
                        .addGap(18, 18, 18)
                        .addComponent(rbOffJuridico)))
                .addGap(91, 91, 91))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCodigoJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnderecoJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBairroJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelefoneJurid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCelularJuridi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbFotoJuridico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbOnJuridico)
                            .addComponent(rbOffJuridico))))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCepJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

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

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/PesquisarMenu.png"))); // NOI18N
        jMenu5.setText("Pesquisar fornecedor juridico");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoJuridicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoJuridicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoJuridicoActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        TratamentoJuridico(2);
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
           DeletarJuridico();
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
           limparCamposJuridico();
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
             JFileChooser foto = new JFileChooser();
        foto.setDialogTitle("Selecione a Imagem");
        foto.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int opc = foto.showOpenDialog(this);
        if (opc == JFileChooser.APPROVE_OPTION) {
            File file = new File("caminho");
            file = foto.getSelectedFile();
            filename = file.getAbsolutePath();

            ImageIcon images = new ImageIcon(foto.getSelectedFile().getPath());
            lbFotoJuridico.setIcon(new ImageIcon(images.getImage().getScaledInstance(lbFotoJuridico.getWidth(), lbFotoJuridico.getHeight(), Image.SCALE_DEFAULT)));
            img = 1;

        } else {
            img = 0;
            filename = "foto/semFoto.png";
        }
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        objBuscarForne = new frmBuscarForneJuridico(null, closable);
        objBuscarForne.setLocationRelativeTo(null);
            objBuscarForne.setVisible(true);
            ColetaDadosJuridico();
    }//GEN-LAST:event_jMenu5MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbFotoJuridico;
    private javax.swing.JCheckBox rbOffJuridico;
    private javax.swing.JCheckBox rbOnJuridico;
    private javax.swing.JTextField txtBairroJuridico;
    private javax.swing.JFormattedTextField txtCelularJuridi;
    private javax.swing.JFormattedTextField txtCepJuridico;
    private javax.swing.JFormattedTextField txtCnpj;
    private javax.swing.JTextField txtCodigoJuridico;
    private javax.swing.JTextField txtEnderecoJuridico;
    private javax.swing.JTextField txtNomeJuridico;
    private javax.swing.JFormattedTextField txtTelefoneJurid;
    // End of variables declaration//GEN-END:variables
}
