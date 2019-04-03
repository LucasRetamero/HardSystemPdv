/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.DAOParametros;
import entity.Parametrosempresa;
import java.sql.SQLException;

/**
 *
 * @author Vinicius
 */
public class ScParametros {
    DAOParametros daoParametros;
    
    public ScParametros(){
        daoParametros = new DAOParametros();
    }
    
    public void insereParamentros(Parametrosempresa parametros) throws SQLException, ClassNotFoundException{
        daoParametros.cadastrarLoja(parametros);
    }
    
}
