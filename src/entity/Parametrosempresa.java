/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Vinicius
 */
public class Parametrosempresa implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idParametroempresa;
    private String razaoSocial;
    private String nomefantasia;
    private String telefone;
    private String celular;
    private String rua;
    private String cidade;
    private String cep;
    private String site;
    private String email;

    public Parametrosempresa() {
    }

    public Parametrosempresa(Integer idParametroempresa) {
        this.idParametroempresa = idParametroempresa;
    }

    public Parametrosempresa(Integer idParametroempresa, String razaoSocial, String nomefantasia, String rua, String cidade, String email) {
        this.idParametroempresa = idParametroempresa;
        this.razaoSocial = razaoSocial;
        this.nomefantasia = nomefantasia;
        this.rua = rua;
        this.cidade = cidade;
        this.email = email;
    }

    public Integer getIdParametroempresa() {
        return idParametroempresa;
    }

    public void setIdParametroempresa(Integer idParametroempresa) {
        this.idParametroempresa = idParametroempresa;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametroempresa != null ? idParametroempresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametrosempresa)) {
            return false;
        }
        Parametrosempresa other = (Parametrosempresa) object;
        if ((this.idParametroempresa == null && other.idParametroempresa != null) || (this.idParametroempresa != null && !this.idParametroempresa.equals(other.idParametroempresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Parametrosempresa[ idParametroempresa=" + idParametroempresa + " ]";
    }

}
