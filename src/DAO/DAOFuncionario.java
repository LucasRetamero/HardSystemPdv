/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.Funcionario;
import entity.Parametrosempresa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Vinicius
 */
public class DAOFuncionario {
    Conexao con;
    ResultSet rs;
    PreparedStatement pst;

public DAOFuncionario(){
    }
    
    public Funcionario buscaByCodigo (int codigo) throws SQLException, ClassNotFoundException, Exception{
        //Buscando funcionario
        String sql = "select * from funcionario where id_funcionario = ?";
        pst =  Conexao.conecta().prepareStatement(sql);
        pst.setInt(1, codigo);
        //Inserindo dados no funcionario
        rs  = pst.executeQuery();
        if(rs.next()){
            Funcionario funcionario = new Funcionario(rs.getInt("id_funcionario"), 
                                          rs.getString("nome"), 
                                          rs.getString("telefone"),
                                          rs.getString("celular"),
                                          rs.getString("endereco"),
                                          rs.getString("bairro"),
                                          rs.getString("cep"),
                                          rs.getString("cargo"),
                                          rs.getString("foto"),
                                          rs.getString("status").charAt(0));
            return funcionario; 
        }else {
            throw new Exception("Funcionario não cadastrato", new Throwable("Nome")); 
        }        
    }
    
}
