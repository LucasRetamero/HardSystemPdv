/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layoutCadastro;

import DAO.Conexao;
import Service.ScFuncionario;
import com.sun.glass.events.KeyEvent;
import entity.Funcionario;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import layoutInterno.buscaFornecedor;
import layoutInterno.buscaFuncionario;
import javax.swing.ButtonGroup;


public class cadastroFuncionario extends javax.swing.JInternalFrame {
        
    Connection conec = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String filename;
    String guardaFoto;
    int img = 0,verificaImg = 0;
    ButtonGroup sta;
    public cadastroFuncionario() {        
        try {
             initComponents();
            conec = Conexao.conecta();
           telefone();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(cadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(cadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        sta= new ButtonGroup();
      
       
    }
    private void Tratamento(int x){
    switch(x){
        case 1:
            if(txtNome.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Campo nome vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
            }else if(txtTelefone.getText().replace("(","").replace(")","").replace("-","").equals("")){
            JOptionPane.showMessageDialog(null,"Campo telefone vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
            }else if(txtCelular.getText().replace("(","").replace(")","").replace("-","").equals("")){
            JOptionPane.showMessageDialog(null,"Campo celular vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
            }else if(txtEndereco.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Campo endereço vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
            }else if(txtBairro.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Campo bairro vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
            }else if(txtCep.getText().replace("-","").equals("")){
            JOptionPane.showMessageDialog(null,"Campo cep vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
            }else if(txtCargo.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Campo cargo vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);
            }else{
            cadastrar();
            Limpar();
            }
            break;
            
    }
    }
  
    public void cadastrar() {
            if(img==0){
               filename = "/foto/semFoto.png";
            }
        String sql = "insert into funcionario (nome,telefone,celular,endereco,bairro,cep,cargo,img) values (?,?,?,?,?,?,?,?)";
        try {
            pst = conec.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtTelefone.getText().replace("(", "").replace(")", "").replace("-", ""));
            pst.setString(3, txtCelular.getText());
            pst.setString(4, txtEndereco.getText());
            pst.setString(5, txtBairro.getText());
            pst.setString(6, txtCep.getText());
            pst.setString(7, txtCargo.getText());
            pst.setString(8, filename);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionario Cadastrado", "Funcionario Cadastrado", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        }
    }
   
    private void telefone() {

        MaskFormatter mascara,mascaraCelular;
        try {
            mascara = new MaskFormatter("(##)####-####");
            mascara.setValidCharacters("0123456789");
            mascara.install(txtTelefone);
        } catch (Exception ex) {
            Logger.getLogger(cadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            mascaraCelular = new MaskFormatter("(##)#####-####");
            mascaraCelular.setValidCharacters("0123456789");
            mascaraCelular.install(txtCelular);
        } catch (Exception ex) {
            Logger.getLogger(cadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCep = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lbFoto = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setClosable(true);
        setTitle("Tela Funcionario");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nome");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));
        jPanel1.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 31, 340, -1));

        jLabel3.setText("Telefone");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));
        jPanel1.add(txtTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 143, -1));

        jLabel4.setText("Celular");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, -1, -1));

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCelular.setText("");
        jPanel1.add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 166, -1));

        jLabel5.setText("Endereço");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));
        jPanel1.add(txtEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, 335, -1));

        jLabel6.setText("Bairro");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));
        jPanel1.add(txtBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 145, 335, -1));

        jLabel7.setText("CEP");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel1.add(txtCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 143, -1));

        jLabel8.setText("Cargo");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, -1, -1));

        txtCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCargoActionPerformed(evt);
            }
        });
        jPanel1.add(txtCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 166, -1));

        jLabel2.setText("Imagem do funcionario");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 11, -1, -1));

        lbFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/semFoto.png"))); // NOI18N
        jPanel1.add(lbFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCargoActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        if(JOptionPane.showConfirmDialog(null,"Cadastrar funcionario?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){ 
        Tratamento(1);
        }
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
         Limpar();
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
            lbFoto.setIcon(new ImageIcon(images.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT)));
            img = 1;

        } else {
            img = 0;
            filename = "foto/semFoto.png";
        }
    }//GEN-LAST:event_jMenu3MouseClicked
    
    private void populaCampos (Funcionario func) {
        txtNome.setText(func.getNome());
        txtCelular.setText(func.getCelular());
        txtBairro.setText(func.getBairro());
        txtCargo.setText(func.getCargo());
        txtCep.setText(func.getCep());
        txtEndereco.setText(func.getEndereco());
        txtTelefone.setText(func.getTelefone());
    }
    private void Limpar(){

        txtNome.setText("");
        txtTelefone.setText("");
        txtCelular.setText("");
        txtEndereco.setText("");
        txtBairro.setText("");
        txtCep.setText("");
        txtCargo.setText("");
        ImageIcon imgPadrao = new ImageIcon(getClass().getResource("/foto/semFoto.png"));
        lbFoto.setIcon(imgPadrao);
        sta.clearSelection();
        img = 0;
        filename = "";
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
