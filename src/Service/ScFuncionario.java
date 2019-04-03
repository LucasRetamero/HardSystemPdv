/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.DAOFuncionario;
import entity.Funcionario;

/**
 *
 * @author Vinicius
 */
public class ScFuncionario {
    
    DAOFuncionario daoFunc;

    public ScFuncionario() {
        daoFunc = new DAOFuncionario();
    }
    
    
    public Funcionario buscaByCodigo (String idFuncionario) throws Exception {
       
       return daoFunc.buscaByCodigo(Integer.parseInt(idFuncionario));
        
    }
}
