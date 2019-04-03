/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Controler.InfoUsuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import layout.telaPrincipal;

/**
 *
 * @author Su
 */
public class FazerLogin extends  Conexao{
    
    public boolean LogarSistema(InfoUsuario objUsuario){
    try{
    //JOptionPane.showMessageDialog(null,objUsuario.getUsuario());
    setPst(Conexao.conecta().prepareStatement("select * from login where usuario = ? and senha = ?"));
    getPst().setString(1,objUsuario.getUsuario());
    getPst().setString(2,objUsuario.getSenha());
    setRs(getPst().executeQuery());
    if(getRs().next()){
      objUsuario.setVerifica(true);
      objUsuario.setUsuario(getRs().getString(2));
      objUsuario.setNivel(getRs().getString(4));
    telaPrincipal objFrmTelaLogin = new  telaPrincipal(objUsuario.getUsuario(),objUsuario.getNivel());
    objFrmTelaLogin.setLocationRelativeTo(null);
    objFrmTelaLogin.setVisible(true);
    }else{
     objUsuario.setVerifica(false);
    JOptionPane.showMessageDialog(null,"Login nâo encontrado!","Aviso",JOptionPane.INFORMATION_MESSAGE);
    }
    }catch(SQLException error){
    JOptionPane.showMessageDialog(null,error.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);
    }catch(ClassNotFoundException ex) {
            Logger.getLogger(FazerLogin.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
    return objUsuario.getVerifica();
    }
    }
    
}
