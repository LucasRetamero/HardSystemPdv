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
import layoutInterno.frmBuscarForneFisico;
import layoutInterno.frmBuscarForneJuridico;
import javax.swing.ButtonGroup;
import layoutCadastro.cadastroProduto;
import layoutCadastro.frmCadFornecedor;

/**
 *
 * @author Su
 */
public class frmGerenciarFornFisico extends javax.swing.JInternalFrame {

    Connection conec = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String filename;
    String guardaFoto;
    int img = 0,verificaImg = 0;
    ButtonGroup objFisico,objJuridico;
    frmBuscarForneFisico objBuscarForne;
    char status='S';
    public frmGerenciarFornFisico() {
        initComponents();
         try {
            conec = Conexao.conecta();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmCadFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frmCadFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        objFisico = new ButtonGroup();
        objFisico.add(rbOnFisico);
        objFisico.add(rbOffFisico);
        telefone();
    }
     private void ColetaDadosFisico(){
  txtCodigoFisico.setText(""+objBuscarForne.getCodigoFisico());
   txtNomeFisico.setText(objBuscarForne.getNomeFisico());
   txtTelefeFisic.setText(objBuscarForne.getTelFisico());
   txtCelularFisi.setText(""+objBuscarForne.getCelFisico());
   txtEnderecoFisico.setText(objBuscarForne.getEnderecoFisico());
   txtBairroFisico.setText(objBuscarForne.getBairroFisico());
   txtCepFisico.setText(objBuscarForne.getCepFisico());
   txtCPF.setText(objBuscarForne.getCpf());
   if(objBuscarForne.getStatusFisico().equals("S")){
  rbOnFisico.setSelected(true);
   }else{
   rbOffFisico.setSelected(true);
   }
   if(objBuscarForne.getImgFisico().equals("foto/semFoto.png")){
    ImageIcon imgPadrao = new ImageIcon(getClass().getResource("/foto/semFoto.png"));
    lbFotoFisico.setIcon(imgPadrao);
   }else{
   ImageIcon imgPadrao = new ImageIcon(objBuscarForne.getImgFisico());
   lbFotoFisico.setIcon(new ImageIcon(imgPadrao.getImage().getScaledInstance(lbFotoFisico.getWidth(),lbFotoFisico.getHeight(),Image.SCALE_DEFAULT)));
   }
   }
      private void EditarFisico(){
         if(img == 0){
            pegarImgAntesEditar(Integer.parseInt(txtCodigoFisico.getText()));
            }
         if(rbOnFisico.isSelected()){
            status = 'S';
            }else if(rbOffFisico.isSelected()){
            status = 'N';
            }else{
            status = 'S';
            }
         String sql = "update fornecedorfisico set nome=?,telefone=?,celular=?,cpf=?,endereco=?,bairro=?,cep=?,img=?,status=? where idfornefisico = ?";
       try {
            pst = conec.prepareStatement(sql);
            pst.setString(1, txtNomeFisico.getText());
            pst.setString(2, txtTelefeFisic.getText().replace("(", "").replace(")", "").replace("-", ""));
            pst.setString(3,txtCelularFisi.getText().replace("(", "").replace(")", "").replace("-", ""));
            pst.setString(4, txtCPF.getText().replace(".","").replace(".","").replace("-",""));
            pst.setString(5, txtEnderecoFisico.getText());
            pst.setString(6,txtBairroFisico.getText());
            pst.setString(7,txtCepFisico.getText().replace("-",""));
            pst.setString(8,filename);
            pst.setString(9,String.valueOf(status));
            pst.setInt(10,Integer.parseInt(txtCodigoFisico.getText()));
            if(pst.executeUpdate() >0){
            JOptionPane.showMessageDialog(null, "Fornecedor Fisico Atualizado", "Fornecedor Atualizado", JOptionPane.INFORMATION_MESSAGE);
             limparCamposFisico();
            }
            } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        }
    }
         private void limparCamposFisico() {
         txtCodigoFisico.setText("");
        txtNomeFisico.setText("");
        txtTelefeFisic.setText("");
        txtCelularFisi.setText("");
        txtEnderecoFisico.setText("");
        txtBairroFisico.setText("");
        txtCepFisico.setText("");
        txtCPF.setText("");
        objFisico.clearSelection();
        filename="";
        ImageIcon IMGpadrao = new ImageIcon(this.getClass().getResource("/foto/semFoto.png"));
        lbFotoFisico.setIcon(new ImageIcon(IMGpadrao.getImage().getScaledInstance(lbFotoFisico.getWidth(), lbFotoFisico.getHeight(), Image.SCALE_DEFAULT)));
        txtNomeFisico.requestFocus();
        img=0;
    }
        public void pegarImgAntesEditar(int pk){
    try{
    pst = conec.prepareStatement(" select img from fornecedorfisico where idfornefisico =?");
    pst.setInt(1,pk);
    rs = pst.executeQuery();
    if(rs.next()){
    filename = rs.getString(1);
    }
    }catch(SQLException e){
    JOptionPane.showMessageDialog(null,e.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
    }
         private void telefone() {

        MaskFormatter mascaraTelefoneFisica,mascaraTelefoneJuridica,mascaraCelularJuridico,mascaraCelularFisico;
        MaskFormatter mascaraCNPJ,mascaraCPF,mascaraCEPFisico,mascaraCEPJuridico;
        try {
            mascaraTelefoneFisica = new MaskFormatter("(##)####-####");
            mascaraTelefoneFisica.setValidCharacters("0123456789");
            mascaraTelefoneFisica.install(txtTelefeFisic);
            //
            mascaraCelularFisico = new MaskFormatter("(##)#####-####");
            mascaraCelularFisico.setValidCharacters("0123456789");
            mascaraCelularFisico.install(txtCelularFisi);
            //--
            mascaraCEPFisico = new MaskFormatter("#####-###");
            mascaraCEPFisico.setValidCharacters("0123456789");
            mascaraCEPFisico.install(txtCepFisico);
          
            //
            mascaraCPF = new MaskFormatter("###.###.###-##");
            mascaraCPF.setValidCharacters("0123456789");
            mascaraCPF.install(txtCPF);
        } catch (Exception ex) {
            Logger.getLogger(frmCadFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      protected void TratamentoFisico(int troll){
  if(txtCodigoFisico.getText().equals("")){
  JOptionPane.showMessageDialog(null,"Selecione o fornecedor fisico(Pessoa)!","Aviso",JOptionPane.INFORMATION_MESSAGE);
  }else{
  switch(troll){
      case 1:
         DeletarFisico();
         break;
      case 2:
          EditarFisico();
          break;
  }
  }
  }
       public void DeletarFisico(){
    if(JOptionPane.showConfirmDialog(null,"Deletar Fornecedor Fisico(Pessoa)?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
    try{
    pst= conec.prepareStatement("delete from fornecedorfisico where idfornefisico = ?");
    pst.setInt(1,Integer.parseInt(txtCodigoFisico.getText()));
    if(pst.executeUpdate() > 0){
    JOptionPane.showMessageDialog(null,"Fornecedor Fisico(Pessoa) Deletado");
        limparCamposFisico();
    }
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
    }
    }
      private void buscaImagem (){
        
        try {
            pst = conec.prepareStatement("select foto from fornecedor where id_fornecedor = ?");
            pst.setInt(1, Integer.parseInt(txtCodigoFisico.getText()));
            rs = pst.executeQuery();
            if(rs.next()){
                filename = String.valueOf(rs.getString(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(cadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigoFisico = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNomeFisico = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtEnderecoFisico = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtBairroFisico = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTelefeFisic = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        txtCelularFisi = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        txtCepFisico = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        lbFotoFisico = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rbOffFisico = new javax.swing.JCheckBox();
        rbOnFisico = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setClosable(true);
        setTitle("Gerenciar fornecedor fisico");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Codigo");

        txtCodigoFisico.setEditable(false);
        txtCodigoFisico.setBackground(new java.awt.Color(153, 153, 153));
        txtCodigoFisico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoFisicoActionPerformed(evt);
            }
        });

        jLabel4.setText("Nome");

        jLabel17.setText("Endereço");

        jLabel18.setText("Bairro");

        jLabel5.setText("Telefone");

        jLabel16.setText("Celular:");

        jLabel19.setText("CEP");

        try {
            txtCepFisico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setText("CPF:");

        jLabel1.setText("Imagem do fornecedor fisico");

        lbFotoFisico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/semFoto.png"))); // NOI18N

        jLabel7.setText("Status");

        rbOffFisico.setText("Desativado");

        rbOnFisico.setText("Ativo");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCodigoFisico, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNomeFisico, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtEnderecoFisico, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtBairroFisico, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTelefeFisic, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCepFisico, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19))
                            .addGap(28, 28, 28)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(0, 149, Short.MAX_VALUE))
                                .addComponent(txtCelularFisi)
                                .addComponent(txtCPF))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(116, 116, 116)
                        .addComponent(jLabel16)
                        .addGap(153, 153, 153)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(rbOnFisico)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbOffFisico))
                        .addComponent(lbFotoFisico)))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoFisico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeFisico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnderecoFisico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBairroFisico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel16))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelefeFisic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCelularFisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel6))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCepFisico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbFotoFisico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbOnFisico)
                            .addComponent(rbOffFisico))))
                .addContainerGap(31, Short.MAX_VALUE))
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
        jMenu5.setText("Pesquisar fornecedor fisico");
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
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoFisicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoFisicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoFisicoActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        TratamentoFisico(2);
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        TratamentoFisico(1);
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
       limparCamposFisico();
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
            lbFotoFisico.setIcon(new ImageIcon(images.getImage().getScaledInstance(lbFotoFisico.getWidth(), lbFotoFisico.getHeight(), Image.SCALE_DEFAULT)));
            img = 1;
        } else {
            img = 0;
            filename = "foto/semFoto.png";
        }
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
               objBuscarForne = new frmBuscarForneFisico(null, closable);
            objBuscarForne.setLocationRelativeTo(null);
            objBuscarForne.setVisible(true);
            if(objBuscarForne.getNomeFisico().equals("")){
            }else{
                ColetaDadosFisico();
            }
 
    }//GEN-LAST:event_jMenu5MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbFotoFisico;
    private javax.swing.JCheckBox rbOffFisico;
    private javax.swing.JCheckBox rbOnFisico;
    private javax.swing.JTextField txtBairroFisico;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JFormattedTextField txtCelularFisi;
    private javax.swing.JFormattedTextField txtCepFisico;
    private javax.swing.JTextField txtCodigoFisico;
    private javax.swing.JTextField txtEnderecoFisico;
    private javax.swing.JTextField txtNomeFisico;
    private javax.swing.JFormattedTextField txtTelefeFisic;
    // End of variables declaration//GEN-END:variables
}
