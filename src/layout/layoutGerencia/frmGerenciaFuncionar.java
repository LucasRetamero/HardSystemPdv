/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout.layoutGerencia;
import DAO.Conexao;
import Service.ScFuncionario;
import entity.Funcionario;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import layoutCadastro.cadastroFuncionario;
import layoutInterno.buscaFuncionario; 
/**
 *
 * @author Su
 */
public class frmGerenciaFuncionar extends javax.swing.JInternalFrame {

    ScFuncionario serviceFunc;
    Connection conec = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String filename;
    String guardaFoto;
    int img = 0,verificaImg = 0;
    ButtonGroup sta;
    public frmGerenciaFuncionar() {
        try {
             initComponents();
            conec = Conexao.conecta();
            serviceFunc = new ScFuncionario();
           telefone();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(cadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(cadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        sta= new ButtonGroup();
        sta.add(boxAtivo);
        sta.add(boxDesativado);
    }
     private void Deletar(){
    try{
    pst = conec.prepareStatement("delete from funcionario where idfuncionario = ?");
    pst.setInt(1,Integer.parseInt(txtCodigo.getText()));
    if(pst.executeUpdate() > 0){
      JOptionPane.showMessageDialog(null,"Excluido com sucesso!","Aviso",JOptionPane.INFORMATION_MESSAGE);
      Limpar();
    }
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
     }
    private void PegarfotoEditar(int pk){
        String sql = "select img from funcionario where idfuncionario = ?";
        try {
            pst = conec.prepareStatement(sql);
            pst.setInt(1, pk);
            rs = pst.executeQuery();
            if(rs.next()){
            filename = rs.getString(1);
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        }
   
    }
    private void editar() {
        String statusVeri = "S";
        if(img==0){
                PegarfotoEditar(Integer.parseInt(txtCodigo.getText()));
            }
        if(boxAtivo.isSelected() == true){
        statusVeri = "S";
        }else if(boxDesativado.isSelected() == true){
        statusVeri = "N";
        }else{
        statusVeri = "S";
        }
        String sql = "update funcionario set nome = ?, telefone = ?, celular = ?, endereco = ?, bairro = ?, cep = ?, cargo = ?,status=? ,img = ? where idfuncionario  = ?";
        try {
            pst = conec.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtTelefone.getText());
            pst.setString(3, txtCelular.getText());
            pst.setString(4, txtEndereco.getText());
            pst.setString(5, txtBairro.getText());
            pst.setString(6, txtCep.getText());
            pst.setString(7, txtCargo.getText());
            pst.setString(8,statusVeri);
            pst.setString(9, filename);
            pst.setInt(10, Integer.parseInt(txtCodigo.getText()));
            int verificaAuto = pst.executeUpdate();
            if (verificaAuto > 0) {
                JOptionPane.showMessageDialog(null, "Funcionario Alterado");
                Limpar();
            }
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
     private void populaCampos (Funcionario func) {
        txtCodigo.setText(""+func.getIdFuncionario());
        txtNome.setText(func.getNome());
        txtCelular.setText(func.getCelular());
        txtBairro.setText(func.getBairro());
        txtCargo.setText(func.getCargo());
        txtCep.setText(func.getCep());
        txtEndereco.setText(func.getEndereco());
        txtTelefone.setText(func.getTelefone());
    }
    private void Limpar(){
      txtCodigo.setText("");
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
 private void Tratamento(int x){
    switch(x){
        case 1:
            if(txtCodigo.getText().equals("")){
             JOptionPane.showMessageDialog(null,"Codigo vazio!","Aviso",JOptionPane.INFORMATION_MESSAGE);    
            }else if(txtNome.getText().equals("")){
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
             if(JOptionPane.showConfirmDialog(null,"Deletar Funcionario?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
            Deletar();
             }
            }
            break;
            case 2:
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
            if(JOptionPane.showConfirmDialog(null,"Atualizar funcionario?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
            editar();
            }
            }
            break;
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
        jLabel9 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
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
        txtCargo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbFoto = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        boxAtivo = new javax.swing.JCheckBox();
        boxDesativado = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setClosable(true);
        setTitle("Gerenciar Funcionario");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("Codigo");

        txtCodigo.setEditable(false);
        txtCodigo.setBackground(new java.awt.Color(153, 153, 153));
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome");

        jLabel3.setText("Telefone");

        jLabel4.setText("Celular");

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCelular.setText("");

        jLabel5.setText("Endereço");

        jLabel6.setText("Bairro");

        jLabel7.setText("CEP");

        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        txtCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCargoActionPerformed(evt);
            }
        });

        jLabel8.setText("Cargo");

        jLabel10.setText("Imagem do funcionario");

        lbFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto/semFoto.png"))); // NOI18N

        jLabel11.setText("Status");

        boxAtivo.setText("Ativo");

        boxDesativado.setText("Desativado");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(131, 131, 131)
                        .addComponent(jLabel8))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(107, 107, 107)
                                .addComponent(jLabel4))
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCargo))
                                .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel9)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(boxAtivo)
                                .addGap(18, 18, 18)
                                .addComponent(boxDesativado))
                            .addComponent(jLabel11))))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(boxAtivo)
                            .addComponent(boxDesativado))
                        .addGap(5, 5, 5)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
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
        jMenu2.setText("Excluir");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/LimparMenu.png"))); // NOI18N
        jMenu4.setText("Limpar campos");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/selecionarImagenMenu.png"))); // NOI18N
        jMenu3.setText("Selecionar imagem");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/PesquisarMenu.png"))); // NOI18N
        jMenu5.setText("Pesquisar funcionario");
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

    private void txtCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCargoActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
    Tratamento(2);
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
      Tratamento(1);
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
      Limpar();
    }//GEN-LAST:event_jMenu4MouseClicked

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

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
         try {
            buscaFuncionario jnl = new buscaFuncionario(null, closable);
            jnl.setVisible(true);
            //String coletaFoto =
            txtCodigo.setText("" + jnl.getCodigo());
            txtNome.setText(jnl.getNome());
            txtTelefone.setText(String.valueOf(jnl.getTelefone()));
            txtCelular.setText(String.valueOf(jnl.getCelular()));
            txtEndereco.setText(String.valueOf(jnl.getEndereco()));
            txtBairro.setText(String.valueOf(jnl.getBairro()));
            txtCep.setText(String.valueOf(jnl.getCep()));
            txtCargo.setText(String.valueOf(jnl.getCargo()));
            if(jnl.getFoto().equals("/foto/semFoto.png")){
                ImageIcon icon = new ImageIcon(getClass().getResource("/foto/semFoto.png"));
                lbFoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT)));
            } else{
                ImageIcon icon = new ImageIcon(jnl.getFoto());
                lbFoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT)));
            }
            switch(jnl.getstatus()){
                case "S":
                boxAtivo.setSelected(true);
                break;
                case "N":
                boxDesativado.setSelected(true);
                break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(cadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenu5MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox boxAtivo;
    private javax.swing.JCheckBox boxDesativado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
