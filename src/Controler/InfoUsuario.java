/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

/**
 *
 * @author Su
 */
public class InfoUsuario {
    private static String usuario;
    private static String nivel;
    private String senha;
    private boolean verifica;

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the nivel
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the verifica
     */
    public boolean getVerifica() {
        return verifica;
    }

    /**
     * @param verifica the verifica to set
     */
    public void setVerifica(boolean verifica) {
        this.verifica = verifica;
    }
}
