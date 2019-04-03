/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import DAO.Conexao;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.io.File;
import javax.swing.ImageIcon;
//import net.sourceforge.barbecue.Barcode;
//import net.sourceforge.barbecue.BarcodeException;
//import net.sourceforge.barbecue.BarcodeFactory;
//import net.sourceforge.barbecue.BarcodeImageHandler;

/**
 *
 * @author felipe
 */
public class geradorVasilhame extends javax.swing.JInternalFrame {

    Connection conec = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    int converteint;
    int converteintdois;

    public geradorVasilhame() throws SQLException {
        initComponents();
        try {
            conec = Conexao.conecta();
            listarVasilhame();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(geradorVasilhame.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTableVasilhame.getColumnModel().getColumn(0).setPreferredWidth(1);
        jTableVasilhame.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTableVasilhame.getColumnModel().getColumn(2).setPreferredWidth(1);
    }

    private void resetaCampo() {
        txtCodigo.setText("");
        txtDescricao.setText("");
        txtQuantidade.setText("");
    }

    private void listarVasilhame() {
        String sql = "select id_produto,descricao,vasilhame from produtos where vasilhame = 'S' order by descricao";
        try {
            pst = conec.prepareStatement(sql);
            rs = pst.executeQuery();
            jTableVasilhame.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        }
    }

    public void buscaVasilhame() {
        String sql = "Select id_produto,descricao,vasilhame from produtos where descricao like ? and vasilhame = 'S'";
        try {
            pst = conec.prepareStatement(sql);
            pst.setString(1, txtBusca.getText() + "%");
            rs = pst.executeQuery();
            jTableVasilhame.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);

        }
    }

    private void setarCampos() {
        int preenchecampos = jTableVasilhame.getSelectedRow();
        txtCodigo.setText(jTableVasilhame.getModel().getValueAt(preenchecampos, 0).toString());
        txtDescricao.setText(jTableVasilhame.getModel().getValueAt(preenchecampos, 1).toString());
        txtQuantidade.requestFocus();
    }

    private void impressaoVasilhame(String porta) throws IOException {
        FileOutputStream outputFile = null;
        try {

            outputFile = new FileOutputStream(porta);
            try (PrintStream ps = new PrintStream(outputFile)) {
                ps.println("=============================================" + (char) 27 + (char) 69 + (char) 1);
                ps.println("**********  CONTROLE DE VASILHAMES **********");
                ps.println("=============================================" + (char) 27 + (char) 69 + (char) 0);
                ps.println(String.format("DESCRICAO : %-15s", txtDescricao.getText()));
                ps.println(String.format("QUANTIDADE : %-15s", txtQuantidade.getText()));
                ps.println("=============================================" + (char) 27 + (char) 69 + (char) 1);
                ps.println("=============================================" + (char) 27 + (char) 69 + (char) 0);
                ps.flush();
            }
            outputFile.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            outputFile.close();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnImprimir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVasilhame = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lbBarras = new javax.swing.JLabel();

        setTitle("Gerador de Vasilhame");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/imprimir.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Exit-16.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnImprimir)
                .addGap(18, 18, 18)
                .addComponent(btnSair)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnImprimir, btnSair});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimir)
                    .addComponent(btnSair))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableVasilhame.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableVasilhame.getTableHeader().setReorderingAllowed(false);
        jTableVasilhame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVasilhameMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableVasilhame);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Descriçao");

        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusca)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        txtDescricao.setEditable(false);
        txtDescricao.setBackground(new java.awt.Color(153, 153, 153));
        txtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescricaoKeyReleased(evt);
            }
        });

        jLabel1.setText("Descriçao");

        jLabel3.setText("Quantidade");

        txtQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantidadeKeyReleased(evt);
            }
        });

        jLabel4.setText("Codigo");

        txtCodigo.setEditable(false);
        txtCodigo.setBackground(new java.awt.Color(153, 153, 153));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Codigo de Barras"));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbBarras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lbBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 60, Short.MAX_VALUE))
                            .addComponent(txtCodigo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel3))
                            .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
       /* Barcode barcode = null;
        try {
            barcode = BarcodeFactory.createCode39(txtQuantidade.getText(), true);
        } catch (Exception e) {
        }
        barcode.setDrawingText(false);
        barcode.setBarHeight(60);
        barcode.setBarWidth(2);
        BufferedImage image = new BufferedImage(lbBarras.getWidth(), lbBarras.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        try {
            barcode.draw(g, 5, 20);
        } catch (Exception e) {
        }
        ImageIcon icon = new ImageIcon(image);
        lbBarras.setIcon(icon);
        try {
            impressaoVasilhame("COM3");
        } catch (IOException ex) {
            Logger.getLogger(geradorVasilhame.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void txtBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyPressed

    }//GEN-LAST:event_txtBuscaKeyPressed

    private void txtBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyReleased
        txtBusca.setText(txtBusca.getText().toUpperCase());
        buscaVasilhame();
    }//GEN-LAST:event_txtBuscaKeyReleased

    private void txtQuantidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeKeyReleased
        txtQuantidade.setText(txtQuantidade.getText().replaceAll("[^0-9]", ""));
    }//GEN-LAST:event_txtQuantidadeKeyReleased

    private void txtDescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyReleased
        txtDescricao.setText(txtDescricao.getText().toUpperCase());
    }//GEN-LAST:event_txtDescricaoKeyReleased

    private void jTableVasilhameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVasilhameMouseClicked
        setarCampos();
    }//GEN-LAST:event_jTableVasilhameMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableVasilhame;
    private javax.swing.JLabel lbBarras;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
