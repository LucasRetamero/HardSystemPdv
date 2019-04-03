package layoutInterno;

import DAO.Conexao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;


public class buscaProduto extends javax.swing.JDialog {
    private Connection conec = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private int Codigo;
    private int Quantidade;
    private String Codbarras;
    private String Descricao;
    private String Reduzida;
    private String Grupo;
    private String img;
    private double ValCusto;
    private String status;
    private double ValLucro;
    private String fornecedor;
    
    
    
    
    public buscaProduto(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        try {
            conec = Conexao.conecta();
            preencher_jtable();
            RetornaVazio();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(buscaProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void preencher_jtable() {
        String sql = ("Select * from produtos");
        try {
            pst = conec.prepareStatement(sql);
            rs = pst.executeQuery();
            tbProdutos.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar Jtable " + erro);
        }
    }
     public void preencher_jtableDigita() {
        String sql = ("Select * from produtos where descricao like ?");
        try {
            pst = conec.prepareStatement(sql);
            pst.setString(1,txtBusca.getText()+"%");
            rs = pst.executeQuery();
            tbProdutos.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar Jtable " + erro);
        }
    }
    protected void RetornaVazio(){
           Codigo = 0;
            Codbarras = "";
            Descricao = "";
            Reduzida = "";
            Grupo = "";
            fornecedor ="";
            Quantidade =0;
            ValCusto = 0.0;
            ValLucro = 0.0;
            img ="";
            status = "";
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProdutos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(447, 344));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Pesquisar pela descrição do produto");

        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaKeyReleased(evt);
            }
        });

        tbProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cod.Barras", "Descriçao", "D.Reduzida", "Grupo", "Fornecedor", "Quantidade", "Valor Custo", "Valor Lucro", "Foto", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProdutos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbProdutos);

        jLabel3.setText("Produto selecionado");

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/selecionarMenu.png"))); // NOI18N
        jMenu1.setText("Selecionar Produto");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novoIcones/sairMenu.png"))); // NOI18N
        jMenu2.setText("Sair");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(740, 391));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProdutosMouseClicked
            int linha_select = tbProdutos.getSelectedRow();
            Codigo = (Integer.parseInt((String) tbProdutos.getValueAt(linha_select, 0).toString()));
            Codbarras = (String) tbProdutos.getValueAt(linha_select, 1);
            Descricao = (String) tbProdutos.getValueAt(linha_select, 2);
            Reduzida = (String) tbProdutos.getValueAt(linha_select, 3);
            Grupo = (String) tbProdutos.getValueAt(linha_select, 4);
            fornecedor = tbProdutos.getValueAt(linha_select,5).toString();
            Quantidade = Integer.parseInt(tbProdutos.getValueAt(linha_select, 6).toString());
            ValCusto = Double.valueOf(tbProdutos.getValueAt(linha_select, 7).toString());
            ValLucro = Double.valueOf(tbProdutos.getValueAt(linha_select, 8).toString());
            img = (String) tbProdutos.getValueAt(linha_select, 9);
            status = (String) tbProdutos.getValueAt(linha_select,10);
            jTextField1.setText(Descricao);
    }//GEN-LAST:event_tbProdutosMouseClicked

    private void txtBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyPressed

    }//GEN-LAST:event_txtBuscaKeyPressed

    private void txtBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyReleased
        txtBusca.setText(txtBusca.getText().toUpperCase());
        if(txtBusca.getText().equals("")){
        preencher_jtable();
        }
        preencher_jtableDigita();
    }//GEN-LAST:event_txtBuscaKeyReleased

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
             if(getDescricao().equals("")){
       JOptionPane.showMessageDialog(null,"Seelcione um produto!","Aviso",JOptionPane.INFORMATION_MESSAGE);
       }else{
        dispose();
       }
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
       RetornaVazio();
        dispose();
    }//GEN-LAST:event_jMenu2MouseClicked

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
            java.util.logging.Logger.getLogger(buscaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(buscaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(buscaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(buscaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    buscaProduto dialog = new buscaProduto(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(buscaProduto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tbProdutos;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the Codigo
     */
    public int getCodigo() {
        return Codigo;
    }

    /**
     * @param Codigo the Codigo to set
     */
    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    /**
     * @return the Quantidade
     */
    public int getQuantidade() {
        return Quantidade;
    }

    /**
     * @param Quantidade the Quantidade to set
     */
    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

 

    /**
     * @return the Codbarras
     */
    public String getCodbarras() {
        return Codbarras;
    }

    /**
     * @param Codbarras the Codbarras to set
     */
    public void setCodbarras(String Codbarras) {
        this.Codbarras = Codbarras;
    }

    /**
     * @return the Descricao
     */
    public String getDescricao() {
        return Descricao;
    }

    /**
     * @param Descricao the Descricao to set
     */
    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    /**
     * @return the Reduzida
     */
    public String getReduzida() {
        return Reduzida;
    }

    /**
     * @param Reduzida the Reduzida to set
     */
    public void setReduzida(String Reduzida) {
        this.Reduzida = Reduzida;
    }

    /**
     * @return the Grupo
     */
    public String getGrupo() {
        return Grupo;
    }

    /**
     * @param Grupo the Grupo to set
     */
    public void setGrupo(String Grupo) {
        this.Grupo = Grupo;
    }

    /**
     * @return the img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return the ValCusto
     */
    public double getValCusto() {
        return ValCusto;
    }

    /**
     * @param ValCusto the ValCusto to set
     */
    public void setValCusto(double ValCusto) {
        this.ValCusto = ValCusto;
    }

    /**
     * @return the ValLucro
     */
    public double getValLucro() {
        return ValLucro;
    }

    /**
     * @param ValLucro the ValLucro to set
     */
    public void setValLucro(double ValLucro) {
        this.ValLucro = ValLucro;
    }

    /**
     * @return the fornecedor
     */
    public String getFornecedor() {
        return fornecedor;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    
}
