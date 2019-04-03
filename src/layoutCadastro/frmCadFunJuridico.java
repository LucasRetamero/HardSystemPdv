/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layoutCadastro;

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
import layoutInterno.buscaFornecedor;
import javax.swing.ButtonGroup;
/**
 *
 * @author Su
 */
public class frmCadFunJuridico extends javax.swing.JInternalFrame {

    Connection conec = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String filename;
    String guardaFoto;
    int img = 0,verificaImg = 0;
    ButtonGroup objFisico,objJuridico;
    buscaFornecedor objBuscarForne;
    char status='S';
    public frmCadFunJuridico() {
        initComponents();
         try {
            conec = Conexao.conecta();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmCadFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frmCadFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        
    }
         telefone();
    }
     public void cadastrarJuridico(){
        String sql = "insert into fornecedorjuridico(nome,telefone,celular,endereco,bairro,cep,cnpj,img)\n" +
"values(?,?,?,?,?,?,?,?)";
       try {
            pst = conec.prepareStatement(sql);
            pst.setString(1, txtNomeJuridico.getText());
            pst.setString(2, txtTelefoneJurid.getText().replace("(", "").replace(")", "").replace("-", ""));
            pst.setString(3,txtCelularJuridi.getText().replace("(", "").replace(")", "").replace("-", ""));
            pst.setString(4, txtEnderecoJuridico.getText());
            pst.setString(5,txtBairroJuridico.getText());
            pst.setString(6,txtCepJuridico.getText().replace("-",""));
            pst.setString(7, txtCnpj.getText().replace(".","").replace(".","").replace("/","").replace("-",""));
            if(img == 0){
            filename = "/foto/semFoto.png";
            }
            pst.setString(8,filename);
            if(pst.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Fornecedor Juridico Cadastrado", "Fornecedor Cadastrado", JOptionPane.INFORMATION_MESSAGE);
             limparCamposJuridico();
       } 
       } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        }
    }
          private void limparCamposJuridico() {
        txtNomeJuridico.setText("");
        txtTelefoneJurid.setText("");
        txtCelularJuridi.setText("");
        txtEnderecoJuridico.setText("");
        txtBairroJuridico.setText("");
        txtCepJuridico.setText("");
        txtCnpj.setText("");
         filename="";
        ImageIcon IMGpadrao = new ImageIcon(this.getClass().getResource("/foto/semFoto.png"));
        lbFotoJuridico.setIcon(new ImageIcon(IMGpadrao.getImage().getScaledInstance(lbFotoJuridico.getWidth(),lbFotoJuridico.getHeight(), Image.SCALE_DEFAULT)));
        img=0;    
}
private void telefone() {

        MaskFormatter mascaraTelefoneFisica,mascaraTelefoneJuridica,mascaraCelularJuridico,mascaraCelularFisico;
        MaskFormatter mascaraCNPJ,mascaraCPF,mascaraCEPFisico,mascaraCEPJuridico;
        try {
            //
            mascaraTelefoneJuridica = new MaskFormatter("(##)####-####");
            mascaraTelefoneJuridica.setValidCharacters("0123456789");
            mascaraTelefoneJuridica.install(txtTelefoneJurid);
            //

            mascaraCelularJuridico = new MaskFormatter("(##)#####-####");
            mascaraCelularJuridico.setValidCharacters("0123456789");
            mascaraCelularJuridico.install(txtCelularJuridi);
            //
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
 protected void TratamentoJuridico(int troll){
  if(txtNomeJuridico.getText().equals("")){
  JOptionPane.showMessageDialog(null,"Campo Nome vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
  }else{
  switch(troll){
      case 1:
         cadastrarJuridico();
         break;   
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setClosable(true);
        setTitle("Tela de fornecedor juridico");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        jLabel14.setText("CNPJ");

        jLabel2.setText("Imagem fornecedor juridico");

        lbFotoJuridico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/semFoto.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(txtNomeJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtEnderecoJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtBairroJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(96, 96, 96)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefoneJurid, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtCelularJuridi)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCepJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCnpj)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lbFotoJuridico))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNomeJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnderecoJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBairroJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbFotoJuridico)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefoneJurid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelularJuridi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCepJuridico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

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

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
         if(JOptionPane.showConfirmDialog(null,"Cadastrar fornecedor juridico?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
     TratamentoJuridico(1);
       }
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        limparCamposJuridico();
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
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
    }//GEN-LAST:event_jMenu3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbFotoJuridico;
    private javax.swing.JTextField txtBairroJuridico;
    private javax.swing.JFormattedTextField txtCelularJuridi;
    private javax.swing.JFormattedTextField txtCepJuridico;
    private javax.swing.JFormattedTextField txtCnpj;
    private javax.swing.JTextField txtEnderecoJuridico;
    private javax.swing.JTextField txtNomeJuridico;
    private javax.swing.JFormattedTextField txtTelefoneJurid;
    // End of variables declaration//GEN-END:variables
}
