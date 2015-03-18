/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iveloper.concesiones.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexbonilla
 */
@Entity
@Table(name = "tipiva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipiva.findAll", query = "SELECT t FROM Tipiva t"),
    @NamedQuery(name = "Tipiva.findByTipCodig", query = "SELECT t FROM Tipiva t WHERE t.tipCodig = :tipCodig"),
    @NamedQuery(name = "Tipiva.findByTipDescr", query = "SELECT t FROM Tipiva t WHERE t.tipDescr = :tipDescr"),
    @NamedQuery(name = "Tipiva.findByTipTipfa", query = "SELECT t FROM Tipiva t WHERE t.tipTipfa = :tipTipfa")})
public class Tipiva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "tip_codig")
    private Integer tipCodig;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tip_descr")
    private String tipDescr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tip_tipfa")
    private Character tipTipfa;

    public Tipiva() {
    }

    public Tipiva(Integer tipCodig) {
        this.tipCodig = tipCodig;
    }

    public Tipiva(Integer tipCodig, String tipDescr, Character tipTipfa) {
        this.tipCodig = tipCodig;
        this.tipDescr = tipDescr;
        this.tipTipfa = tipTipfa;
    }

    public Integer getTipCodig() {
        return tipCodig;
    }

    public void setTipCodig(Integer tipCodig) {
        this.tipCodig = tipCodig;
    }

    public String getTipDescr() {
        return tipDescr;
    }

    public void setTipDescr(String tipDescr) {
        this.tipDescr = tipDescr;
    }

    public Character getTipTipfa() {
        return tipTipfa;
    }

    public void setTipTipfa(Character tipTipfa) {
        this.tipTipfa = tipTipfa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipCodig != null ? tipCodig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipiva)) {
            return false;
        }
        Tipiva other = (Tipiva) object;
        if ((this.tipCodig == null && other.tipCodig != null) || (this.tipCodig != null && !this.tipCodig.equals(other.tipCodig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iveloper.concesiones.entities.Tipiva[ tipCodig=" + tipCodig + " ]";
    }
    
}
