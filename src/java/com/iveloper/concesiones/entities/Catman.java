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
@Table(name = "catman")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catman.findAll", query = "SELECT c FROM Catman c"),
    @NamedQuery(name = "Catman.findByCattarif", query = "SELECT c FROM Catman c WHERE c.cattarif = :cattarif"),
    @NamedQuery(name = "Catman.findByCatdescr", query = "SELECT c FROM Catman c WHERE c.catdescr = :catdescr"),
    @NamedQuery(name = "Catman.findByCatcodig", query = "SELECT c FROM Catman c WHERE c.catcodig = :catcodig"),
    @NamedQuery(name = "Catman.findByCatdelet", query = "SELECT c FROM Catman c WHERE c.catdelet = :catdelet"),
    @NamedQuery(name = "Catman.findByCatdescl", query = "SELECT c FROM Catman c WHERE c.catdescl = :catdescl")})
public class Catman implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cattarif")
    private Integer cattarif;
    @Size(max = 255)
    @Column(name = "catdescr")
    private String catdescr;
    @Size(max = 255)
    @Column(name = "catcodig")
    private String catcodig;
    @Size(max = 255)
    @Column(name = "catdelet")
    private String catdelet;
    @Size(max = 255)
    @Column(name = "catdescl")
    private String catdescl;
    @OneToMany(mappedBy = "clvcateg")
    private Collection<Cliveh> clivehCollection;

    public Catman() {
    }

    public Catman(Integer cattarif) {
        this.cattarif = cattarif;
    }

    public Integer getCattarif() {
        return cattarif;
    }

    public void setCattarif(Integer cattarif) {
        this.cattarif = cattarif;
    }

    public String getCatdescr() {
        return catdescr;
    }

    public void setCatdescr(String catdescr) {
        this.catdescr = catdescr;
    }

    public String getCatcodig() {
        return catcodig;
    }

    public void setCatcodig(String catcodig) {
        this.catcodig = catcodig;
    }

    public String getCatdelet() {
        return catdelet;
    }

    public void setCatdelet(String catdelet) {
        this.catdelet = catdelet;
    }

    public String getCatdescl() {
        return catdescl;
    }

    public void setCatdescl(String catdescl) {
        this.catdescl = catdescl;
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
        hash += (cattarif != null ? cattarif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catman)) {
            return false;
        }
        Catman other = (Catman) object;
        if ((this.cattarif == null && other.cattarif != null) || (this.cattarif != null && !this.cattarif.equals(other.cattarif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iveloper.concesiones.entities.Catman[ cattarif=" + cattarif + " ]";
    }
    
}
