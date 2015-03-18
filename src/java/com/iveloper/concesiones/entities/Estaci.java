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
@Table(name = "estaci")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estaci.findAll", query = "SELECT e FROM Estaci e"),
    @NamedQuery(name = "Estaci.findByEstcodig", query = "SELECT e FROM Estaci e WHERE e.estcodig = :estcodig"),
    @NamedQuery(name = "Estaci.findByEstnombr", query = "SELECT e FROM Estaci e WHERE e.estnombr = :estnombr"),
    @NamedQuery(name = "Estaci.findByEstzona", query = "SELECT e FROM Estaci e WHERE e.estzona = :estzona"),
    @NamedQuery(name = "Estaci.findByEstdelet", query = "SELECT e FROM Estaci e WHERE e.estdelet = :estdelet"),
    @NamedQuery(name = "Estaci.findByEstdirec", query = "SELECT e FROM Estaci e WHERE e.estdirec = :estdirec"),
    @NamedQuery(name = "Estaci.findByEstdirec2", query = "SELECT e FROM Estaci e WHERE e.estdirec2 = :estdirec2"),
    @NamedQuery(name = "Estaci.findByEstestab", query = "SELECT e FROM Estaci e WHERE e.estestab = :estestab")})
public class Estaci implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "estcodig")
    private Integer estcodig;
    @Size(max = 255)
    @Column(name = "estnombr")
    private String estnombr;
    @Column(name = "estzona")
    private Integer estzona;
    @Size(max = 255)
    @Column(name = "estdelet")
    private String estdelet;
    @Size(max = 255)
    @Column(name = "estdirec")
    private String estdirec;
    @Size(max = 255)
    @Column(name = "estdirec2")
    private String estdirec2;
    @Column(name = "estestab")
    private Integer estestab;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estaci")
    private Collection<Viadef> viadefCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tracodest")
    private Collection<Transac> transacCollection;

    public Estaci() {
    }

    public Estaci(Integer estcodig) {
        this.estcodig = estcodig;
    }

    public Integer getEstcodig() {
        return estcodig;
    }

    public void setEstcodig(Integer estcodig) {
        this.estcodig = estcodig;
    }

    public String getEstnombr() {
        return estnombr;
    }

    public void setEstnombr(String estnombr) {
        this.estnombr = estnombr;
    }

    public Integer getEstzona() {
        return estzona;
    }

    public void setEstzona(Integer estzona) {
        this.estzona = estzona;
    }

    public String getEstdelet() {
        return estdelet;
    }

    public void setEstdelet(String estdelet) {
        this.estdelet = estdelet;
    }

    public String getEstdirec() {
        return estdirec;
    }

    public void setEstdirec(String estdirec) {
        this.estdirec = estdirec;
    }

    public String getEstdirec2() {
        return estdirec2;
    }

    public void setEstdirec2(String estdirec2) {
        this.estdirec2 = estdirec2;
    }

    public Integer getEstestab() {
        return estestab;
    }

    public void setEstestab(Integer estestab) {
        this.estestab = estestab;
    }

    @XmlTransient
    public Collection<Viadef> getViadefCollection() {
        return viadefCollection;
    }

    public void setViadefCollection(Collection<Viadef> viadefCollection) {
        this.viadefCollection = viadefCollection;
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
        hash += (estcodig != null ? estcodig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estaci)) {
            return false;
        }
        Estaci other = (Estaci) object;
        if ((this.estcodig == null && other.estcodig != null) || (this.estcodig != null && !this.estcodig.equals(other.estcodig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iveloper.concesiones.entities.Estaci[ estcodig=" + estcodig + " ]";
    }
    
}
