/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iveloper.concesiones.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "marcas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marcas.findAll", query = "SELECT m FROM Marcas m"),
    @NamedQuery(name = "Marcas.findByMarCodig", query = "SELECT m FROM Marcas m WHERE m.marCodig = :marCodig"),
    @NamedQuery(name = "Marcas.findByMarDescr", query = "SELECT m FROM Marcas m WHERE m.marDescr = :marDescr"),
    @NamedQuery(name = "Marcas.findByMarDelet", query = "SELECT m FROM Marcas m WHERE m.marDelet = :marDelet")})
public class Marcas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "mar_codig")
    private Integer marCodig;
    @Size(max = 45)
    @Column(name = "mar_descr")
    private String marDescr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mar_delet")
    private Character marDelet;
    @OneToMany(mappedBy = "clvmarca")
    private Collection<Cliveh> clivehCollection;

    public Marcas() {
    }

    public Marcas(Integer marCodig) {
        this.marCodig = marCodig;
    }

    public Marcas(Integer marCodig, Character marDelet) {
        this.marCodig = marCodig;
        this.marDelet = marDelet;
    }

    public Integer getMarCodig() {
        return marCodig;
    }

    public void setMarCodig(Integer marCodig) {
        this.marCodig = marCodig;
    }

    public String getMarDescr() {
        return marDescr;
    }

    public void setMarDescr(String marDescr) {
        this.marDescr = marDescr;
    }

    public Character getMarDelet() {
        return marDelet;
    }

    public void setMarDelet(Character marDelet) {
        this.marDelet = marDelet;
    }

    @XmlTransient
    public Collection<Cliveh> getClivehCollection() {
        return clivehCollection;
    }

    public void setClivehCollection(Collection<Cliveh> clivehCollection) {
        this.clivehCollection = clivehCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (marCodig != null ? marCodig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marcas)) {
            return false;
        }
        Marcas other = (Marcas) object;
        if ((this.marCodig == null && other.marCodig != null) || (this.marCodig != null && !this.marCodig.equals(other.marCodig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iveloper.concesiones.entities.Marcas[ marCodig=" + marCodig + " ]";
    }
    
}
