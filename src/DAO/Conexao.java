package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private PreparedStatement pst;
    private ResultSet rs;
    public static Connection conecta() throws ClassNotFoundException, SQLException {
        //o try e o catch servem para tratar erros na conexao com o banco nessa caso
        //classe que faz a conexao com o banco de dados
        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vendas", "postgres", "123456");

        return con;
    }

    /**
     * @return the pst
     */
    public PreparedStatement getPst() {
        return pst;
    }

    /**
     * @param pst the pst to set
     */
    public void setPst(PreparedStatement pst) {
        this.pst = pst;
    }

    /**
     * @return the rs
     */
    public ResultSet getRs() {
        return rs;
    }

    /**
     * @param rs the rs to set
     */
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
}
