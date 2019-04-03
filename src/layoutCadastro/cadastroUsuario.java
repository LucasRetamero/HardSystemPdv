package layoutCadastro;

import DAO.Conexao;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.ButtonGroup;

public class cadastroUsuario extends javax.swing.JInternalFrame {

    Connection conec = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    int converteint;
    int converteintdois;
    String usuario, senha;

    public cadastroUsuario() throws SQLException {
        initComponents();
        try {
            conec = Conexao.conecta();
            listarUsuario();
            jcbUsuario.setSelected(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(cadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        ButtonGroup objGru = new ButtonGroup();
        objGru.add(jcbNivelBusca);
         objGru.add(jcbUsuario);
        
    }

    private void resetaCampo() {
        txtCodigo.setText("");
        txtSenha.setText("");
        txtUsuario.setText("");
        txtBusca.setText("");
        jcbNivel.setSelectedIndex(2);

    }

    private void listarUsuario() {
        String sql = "select * from login order by usuario Asc";
        try {
            pst = conec.prepareStatement(sql);
            rs = pst.executeQuery();
            jTableUsuario.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        }
    }

    public void buscarNivel() {
        String sql = "Select *from login where tipo like ?";
        try {
            pst = conec.prepareStatement(sql);
            pst.setString(1, txtBusca.getText() + "%");
            rs = pst.executeQuery();
            jTableUsuario.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);

        }
    }

    public void buscarUsuario() {
        String sql = "Select *from login where usuario like ?";
        try {
            pst = conec.prepareStatement(sql);
            pst.setString(1, txtBusca.getText() + "%");
            rs = pst.executeQuery();
            jTableUsuario.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);

        }
    }
    public void Deletar(){
    if(JOptionPane.showConfirmDialog(null,"Deletar login?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
    try{
    pst = conec.prepareStatement("delete from login where id_login = ?");
    pst.setInt(1,Integer.parseInt(txtCodigo.getText()));
    if(pst.executeUpdate() > 0){
    JOptionPane.showMessageDialog(null,"Login deletado!","Aviso",JOptionPane.INFORMATION_MESSAGE);
    resetaCampo();
    listarUsuario();
    }
    }catch(SQLException erro){
    JOptionPane.showMessageDialog(null,erro.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }
        }
    }
    private void editar() {
        String sql = "update login set usuario = ?,senha = ?, tipo = ? where id_login = ?";

        if (txtUsuario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo Usuario é Obrigatorio", "Aviso", JOptionPane.WARNING_MESSAGE);
            txtUsuario.setBackground(Color.yellow);

            return;
        }
        if (txtSenha.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo Senha é Obrigatorio", "Aviso", JOptionPane.WARNING_MESSAGE);
            txtSenha.setBackground(Color.yellow);
            return;
        }

        try {

            pst = conec.prepareStatement(sql);
            pst.setString(1, txtUsuario.getText());
            pst.setString(2, txtSenha.getText());
            pst.setString(3, jcbNivel.getSelectedItem().toString());
            pst.setInt(4, Integer.parseInt(txtCodigo.getText()));
            int verificaAuto = pst.executeUpdate();
            if (verificaAuto > 0) {
                JOptionPane.showMessageDialog(null, "Usuario e senha alterados");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);

        }

    }

    private void setarCampos() {
        int preenchecampos = jTableUsuario.getSelectedRow();
        txtCodigo.setText(jTableUsuario.getModel().getValueAt(preenchecampos, 0).toString());
        txtUsuario.setText(jTableUsuario.getModel().getValueAt(preenchecampos, 1).toString());
        txtSenha.setText(jTableUsuario.getModel().getValueAt(preenchecampos, 2).toString());
        jcbNivel.setSelectedItem(jTableUsuario.getModel().getValueAt(preenchecampos, 3).toString());
    }

    private void inserirUsuario() {
        String sql = "insert into login (usuario,senha,tipo) values (?,?,?)";
        if (txtUsuario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo Usuario é Obrigatorio", "Aviso", JOptionPane.WARNING_MESSAGE);
            txtUsuario.setBackground(Color.yellow);

            return;
        }
        if (txtSenha.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo Senha é Obrigatorio", "Aviso", JOptionPane.WARNING_MESSAGE);
            txtSenha.setBackground(Color.yellow);
            return;
        }
        try {
            pst = conec.prepareStatement(sql);
            pst.setString(1, txtUsuario.getText());
            pst.setString(2, txtSenha.getText());
            pst.setString(3, jcbNivel.getSelectedItem().toString());

            pst.execute();
            resetaCampo();
            JOptionPane.showMessageDialog(null, "Usuario cadastrado", "Produto Cadastrado", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        }
    }

    private void gera_cracha() throws JRException {
        String url;
        String usuario = "postgres";
        String senha = "admin";
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sistema", "postgres", "admin");
            usuario = txtUsuario.getText();
            senha = txtSenha.getText();
            //ImageIcon gto = new ImageIcon(getClass().getResource("Relatorio/MyReports/logotipo.jpg"));
            //HashMap parametros = new HashMap(); 
            Hashtable parametros = new Hashtable();
            parametros.put("usuario", usuario);
            parametros.put("senha", senha);
            //parametros.put("logo", gto.getImage());
            JasperPrint jp = JasperFillManager.fillReport("Relatorio/MyReports/cracha2.jasper", parametros, conn);

            JasperViewer jv = new JasperViewer(jp, false);
            jv.setExtendedState(Frame.MAXIMIZED_BOTH);
            jv.setVisible(true);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jcbUsuario = new javax.swing.JCheckBox();
        jcbNivelBusca = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsuario = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jcbNivel = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setClosable(true);
        setTitle("Tela de Usuario");
        setToolTipText("");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Pesquisar");

        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaKeyReleased(evt);
            }
        });

        jLabel6.setText("Selecione o tipo da pesquisa");

        jcbUsuario.setText("Usuario");

        jcbNivelBusca.setText("Nivel");
        jcbNivelBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbNivelBuscaActionPerformed(evt);
            }
        });

        jTableUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Usuario", "Senha", "Nivel"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableUsuario);

        jLabel1.setText("Código");

        txtCodigo.setEditable(false);
        txtCodigo.setBackground(new java.awt.Color(153, 153, 153));

        jLabel3.setText("Usuario");

        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
        });

        jLabel5.setText("Nivel");

        jcbNivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "ADMIN", "GERENCIA", "CAIXA" }));

        jLabel4.setText("Senha");

        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSenhaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jcbUsuario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                .addComponent(jcbNivelBusca)
                                .addGap(32, 32, 32))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jcbNivel, 0, 126, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4)
                                        .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                        .addComponent(txtSenha))
                                    .addComponent(jLabel3))
                                .addGap(0, 82, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbUsuario)
                    .addComponent(jcbNivelBusca))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/cadastroMenu.png"))); // NOI18N
        jMenu1.setText("Cadastrar");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/updateMenu.png"))); // NOI18N
        jMenu2.setText("Editar");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/deletarMenu.png"))); // NOI18N
        jMenu3.setText("Deletar");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/LimparMenu.png"))); // NOI18N
        jMenu4.setText("Limpar campos");
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
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyPressed

    }//GEN-LAST:event_txtBuscaKeyPressed

    private void txtBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyReleased
        txtBusca.setText(txtBusca.getText().toUpperCase());
        if (jcbUsuario.isSelected()) {
            buscarUsuario();
        }
        if (jcbNivelBusca.isSelected()) {
            buscarNivel();
        } else {

        }
    }//GEN-LAST:event_txtBuscaKeyReleased

    private void jcbNivelBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbNivelBuscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbNivelBuscaActionPerformed

    private void jTableUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUsuarioMouseClicked
        setarCampos();
    }//GEN-LAST:event_jTableUsuarioMouseClicked

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
       txtUsuario.setText(txtUsuario.getText().toUpperCase());
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void txtSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyReleased
         txtSenha.setText(txtSenha.getText().toUpperCase());
    }//GEN-LAST:event_txtSenhaKeyReleased

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
              inserirUsuario();
        listarUsuario();
        resetaCampo();
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        editar();
        listarUsuario();
        resetaCampo();
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
           resetaCampo();
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
            if(txtCodigo.getText().equals("")){
       JOptionPane.showMessageDialog(null,"Selecione o login!","Aviso",JOptionPane.INFORMATION_MESSAGE);
       }else{
           Deletar();
       }
        
    }//GEN-LAST:event_jMenu3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableUsuario;
    private javax.swing.JComboBox<String> jcbNivel;
    private javax.swing.JCheckBox jcbNivelBusca;
    private javax.swing.JCheckBox jcbUsuario;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
