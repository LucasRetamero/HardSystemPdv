/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tratamento;
import Controler.InfoUsuario;
/**
 *
 * @author Su
 */
public class tratandoLogin {
    public void VerificandoLogin(InfoUsuario obj) throws mensagem{
    if(obj.getUsuario().equals("")){
    throw new mensagem("Usuario vazio!");
    }else if(obj.getSenha().equals("")){
    throw new mensagem("Senha vazia!");
    }else{
    }
            
         }
}
