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
public class frmCadFunFisico extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmCadFunFisico
     */
    Connection conec = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String filename;
    String guardaFoto;
    int img = 0,verificaImg = 0;
    public frmCadFunFisico() {
        initComponents();
         try {
            conec = Conexao.conecta();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmCadFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frmCadFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        telefone();
         //URL url = this.getClass().getResource("/imagens/IconePequeno.png");  
       //Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);  
       //this.setIconImage(iconeTitulo);
    }
     protected void TratamentoFisico(int troll){
  if(txtNomeFisico.getText().equals("")){
  JOptionPane.showMessageDialog(null,"Campo Nome vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
  }else{
  switch(troll){
      case 1:
         cadastrarFisica();
         break;
  }
  }
  }
     public void cadastrarFisica(){
        String sql = "insert into fornecedorfisico(nome,telefone,celular,cpf,endereco,bairro,cep,img)\n" +
"values(?,?,?,?,?,?,?,?)";
       try {
            pst = conec.prepareStatement(sql);
            pst.setString(1, txtNomeFisico.getText());
            pst.setString(2, txtTelefeFisic.getText().replace("(", "").replace(")", "").replace("-", ""));
            pst.setString(3,txtCelularFisi.getText().replace("(", "").replace(")", "").replace("-", ""));
            pst.setString(4, txtCPF.getText().replace(".","").replace(".","").replace("-",""));
            pst.setString(5, txtEnderecoFisico.getText());
            pst.setString(6,txtBairroFisico.getText());
            pst.setString(7,txtCepFisico.getText().replace("-",""));
            if(filename.equals("")){
            filename = "/foto/semFoto.png";
            }
            pst.setString(8,filename);
            if(pst.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Fornecedor Fisico Cadastrado", "Fornecedor Cadastrado", JOptionPane.INFORMATION_MESSAGE);
            limparCamposFisico();
            }
            } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        }
    }
 private void limparCamposFisico() {
        txtNomeFisico.setText("");
        txtTelefeFisic.setText("");
        txtCelularFisi.setText("");
        txtEnderecoFisico.setText("");
        txtBairroFisico.setText("");
        txtCepFisico.setText("");
        txtCPF.setText("");
        filename="";
        ImageIcon IMGpadrao = new ImageIcon(this.getClass().getResource("/foto/semFoto.png"));
        lbFotoFisico.setIcon(new ImageIcon(IMGpadrao.getImage().getScaledInstance(lbFotoFisico.getWidth(), lbFotoFisico.getHeight(), Image.SCALE_DEFAULT)));
        txtNomeFisico.requestFocus();
        img=0;
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setClosable(true);
        setTitle("Cadastrar fornecedor fisico");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Nome");

        jLabel17.setText("Endereço");

        jLabel18.setText("Bairro");

        jLabel5.setText("Telefone");

        jLabel16.setText("Celular");

        jLabel19.setText("CEP");

        try {
            txtCepFisico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setText("CPF");

        jLabel1.setText("Imagem do fornecedor fisico");

        lbFotoFisico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/semFoto.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(txtNomeFisico, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtEnderecoFisico, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txtBairroFisico, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCepFisico, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCPF))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefeFisic, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel6)
                            .addComponent(txtCelularFisi))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbFotoFisico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNomeFisico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnderecoFisico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBairroFisico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel16)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbFotoFisico)))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefeFisic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelularFisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCepFisico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
       if(JOptionPane.showConfirmDialog(null,"Cadastrar fornecedor fisico?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
             if (img == 0) {
            filename = "foto/semFoto.png";
        }
        TratamentoFisico(1);
       }
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
       limparCamposFisico();
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
            lbFotoFisico.setIcon(new ImageIcon(images.getImage().getScaledInstance(lbFotoFisico.getWidth(), lbFotoFisico.getHeight(), Image.SCALE_DEFAULT)));
            img = 1;
        } else {
            img = 0;
            filename = "foto/semFoto.png";
        }
    }//GEN-LAST:event_jMenu3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbFotoFisico;
    private javax.swing.JTextField txtBairroFisico;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JFormattedTextField txtCelularFisi;
    private javax.swing.JFormattedTextField txtCepFisico;
    private javax.swing.JTextField txtEnderecoFisico;
    private javax.swing.JTextField txtNomeFisico;
    private javax.swing.JFormattedTextField txtTelefeFisic;
    // End of variables declaration//GEN-END:variables
}
