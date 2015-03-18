/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iveloper.concesiones.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alexbonilla
 */
@Entity
@Table(name = "client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findByClinumcl", query = "SELECT c FROM Client c WHERE c.clinumcl = :clinumcl"),
    @NamedQuery(name = "Client.findByClinombr", query = "SELECT c FROM Client c WHERE c.clinombr = :clinombr"),
    @NamedQuery(name = "Client.findByClitidoc", query = "SELECT c FROM Client c WHERE c.clitidoc = :clitidoc"),
    @NamedQuery(name = "Client.findByClidocum", query = "SELECT c FROM Client c WHERE c.clidocum = :clidocum"),
    @NamedQuery(name = "Client.findByClidomic", query = "SELECT c FROM Client c WHERE c.clidomic = :clidomic"),
    @NamedQuery(name = "Client.findByCliprovi", query = "SELECT c FROM Client c WHERE c.cliprovi = :cliprovi"),
    @NamedQuery(name = "Client.findByClilocal", query = "SELECT c FROM Client c WHERE c.clilocal = :clilocal"),
    @NamedQuery(name = "Client.findByClitelef", query = "SELECT c FROM Client c WHERE c.clitelef = :clitelef"),
    @NamedQuery(name = "Client.findByClitiiva", query = "SELECT c FROM Client c WHERE c.clitiiva = :clitiiva"),
    @NamedQuery(name = "Client.findByClicomen", query = "SELECT c FROM Client c WHERE c.clicomen = :clicomen"),
    @NamedQuery(name = "Client.findByCliexped", query = "SELECT c FROM Client c WHERE c.cliexped = :cliexped"),
    @NamedQuery(name = "Client.findByCliemail", query = "SELECT c FROM Client c WHERE c.cliemail = :cliemail"),
    @NamedQuery(name = "Client.findByClidelet", query = "SELECT c FROM Client c WHERE c.clidelet = :clidelet"),
    @NamedQuery(name = "Client.findByClirazsoc", query = "SELECT c FROM Client c WHERE c.clirazsoc = :clirazsoc"),
    @NamedQuery(name = "Client.findByCliautog", query = "SELECT c FROM Client c WHERE c.cliautog = :cliautog")})
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "clinumcl")
    private Integer clinumcl;
    @Size(max = 255)
    @Column(name = "clinombr")
    private String clinombr;
    @Column(name = "clitidoc")
    private Integer clitidoc;
    @Size(max = 255)
    @Column(name = "clidocum")
    private String clidocum;
    @Size(max = 255)
    @Column(name = "clidomic")
    private String clidomic;
    @Column(name = "cliprovi")
    private Integer cliprovi;
    @Size(max = 255)
    @Column(name = "clilocal")
    private String clilocal;
    @Size(max = 255)
    @Column(name = "clitelef")
    private String clitelef;
    @Column(name = "clitiiva")
    private Integer clitiiva;
    @Size(max = 255)
    @Column(name = "clicomen")
    private String clicomen;
    @Size(max = 255)
    @Column(name = "cliexped")
    private String cliexped;
    @Size(max = 255)
    @Column(name = "cliemail")
    private String cliemail;
    @Size(max = 255)
    @Column(name = "clidelet")
    private String clidelet;
    @Size(max = 255)
    @Column(name = "clirazsoc")
    private String clirazsoc;
    @Column(name = "cliautog")
    private Character cliautog;
    @OneToMany(mappedBy = "clvnumcl")
    private Collection<Cliveh> clivehCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tracli")
    private Collection<Transac> transacCollection;

    public Client() {
    }

    public Client(Integer clinumcl) {
        this.clinumcl = clinumcl;
    }

    public Integer getClinumcl() {
        return clinumcl;
    }

    public void setClinumcl(Integer clinumcl) {
        this.clinumcl = clinumcl;
    }

    public String getClinombr() {
        return clinombr;
    }

    public void setClinombr(String clinombr) {
        this.clinombr = clinombr;
    }

    public Integer getClitidoc() {
        return clitidoc;
    }

    public void setClitidoc(Integer clitidoc) {
        this.clitidoc = clitidoc;
    }

    public String getClidocum() {
        return clidocum;
    }

    public void setClidocum(String clidocum) {
        this.clidocum = clidocum;
    }

    public String getClidomic() {
        return clidomic;
    }

    public void setClidomic(String clidomic) {
        this.clidomic = clidomic;
    }

    public Integer getCliprovi() {
        return cliprovi;
    }

    public void setCliprovi(Integer cliprovi) {
        this.cliprovi = cliprovi;
    }

    public String getClilocal() {
        return clilocal;
    }

    public void setClilocal(String clilocal) {
        this.clilocal = clilocal;
    }

    public String getClitelef() {
        return clitelef;
    }

    public void setClitelef(String clitelef) {
        this.clitelef = clitelef;
    }

    public Integer getClitiiva() {
        return clitiiva;
    }

    public void setClitiiva(Integer clitiiva) {
        this.clitiiva = clitiiva;
    }

    public String getClicomen() {
        return clicomen;
    }

    public void setClicomen(String clicomen) {
        this.clicomen = clicomen;
    }

    public String getCliexped() {
        return cliexped;
    }

    public void setCliexped(String cliexped) {
        this.cliexped = cliexped;
    }

    public String getCliemail() {
        return cliemail;
    }

    public void setCliemail(String cliemail) {
        this.cliemail = cliemail;
    }

    public String getClidelet() {
        return clidelet;
    }

    public void setClidelet(String clidelet) {
        this.clidelet = clidelet;
    }

    public String getClirazsoc() {
        return clirazsoc;
    }

    public void setClirazsoc(String clirazsoc) {
        this.clirazsoc = clirazsoc;
    }

    public Character getCliautog() {
        return cliautog;
    }

    public void setCliautog(Character cliautog) {
        this.cliautog = cliautog;
    }

    @XmlTransient
    public Collection<Cliveh> getClivehCollection() {
        return clivehCollection;
    }

    public void setClivehCollection(Collection<Cliveh> clivehCollection) {
        this.clivehCollection = clivehCollection;
    }

    @XmlTransient
    public Collection<Transac> getTransacCollection() {
        return transacCollection;
    }

    public void setTransacCollection(Collection<Transac> transacCollection) {
        this.transacCollection = transacCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clinumcl != null ? clinumcl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.clinumcl == null && other.clinumcl != null) || (this.clinumcl != null && !this.clinumcl.equals(other.clinumcl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iveloper.concesiones.entities.Client[ clinumcl=" + clinumcl + " ]";
    }
    
}
