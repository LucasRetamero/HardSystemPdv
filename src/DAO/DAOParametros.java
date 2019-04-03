package DAO;

import entity.Parametrosempresa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOParametros {

    ResultSet rs;
    PreparedStatement pst;

    public DAOParametros() {

    }

    public void cadastrarLoja(Parametrosempresa paramentrosEmpresa) throws SQLException, ClassNotFoundException {
        String sql = "insert into parametrosEmpresa(  razao_social        ,"
                + "nomeFantasia       ,"
                + "telefone 	    ,"
                + "celular    	    ,"
                + "rua		    ,"
                + "cidade		    ,"
                + "cep		    ,"
                + "site		    ,"
                + "email		    ) values (?,?,?,?,?,?,?,?,?)";
        pst = Conexao.conecta().prepareStatement(sql);
        pst.setString(1, paramentrosEmpresa.getRazaoSocial());
        pst.setString(2, paramentrosEmpresa.getNomefantasia());
        pst.setString(3, paramentrosEmpresa.getTelefone());
        pst.setString(4, paramentrosEmpresa.getCelular());
        pst.setString(5, paramentrosEmpresa.getRua());
        pst.setString(6, paramentrosEmpresa.getCidade());
        pst.setString(7, paramentrosEmpresa.getCep());
        pst.setString(8, paramentrosEmpresa.getSite());
        pst.setString(9, paramentrosEmpresa.getEmail());
        //
        pst.executeUpdate();
    }
}
