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
@Table(name = "modelos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modelos.findAll", query = "SELECT m FROM Modelos m"),
    @NamedQuery(name = "Modelos.findByModCodig", query = "SELECT m FROM Modelos m WHERE m.modCodig = :modCodig"),
    @NamedQuery(name = "Modelos.findByModDescr", query = "SELECT m FROM Modelos m WHERE m.modDescr = :modDescr"),
    @NamedQuery(name = "Modelos.findByModMarca", query = "SELECT m FROM Modelos m WHERE m.modMarca = :modMarca"),
    @NamedQuery(name = "Modelos.findByModDelet", query = "SELECT m FROM Modelos m WHERE m.modDelet = :modDelet")})
public class Modelos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "mod_codig")
    private Integer modCodig;
    @Size(max = 30)
    @Column(name = "mod_descr")
    private String modDescr;
    @Column(name = "mod_marca")
    private Integer modMarca;
    @Column(name = "mod_delet")
    private Character modDelet;
    @OneToMany(mappedBy = "clvmodel")
    private Collection<Cliveh> clivehCollection;

    public Modelos() {
    }

    public Modelos(Integer modCodig) {
        this.modCodig = modCodig;
    }

    public Integer getModCodig() {
        return modCodig;
    }

    public void setModCodig(Integer modCodig) {
        this.modCodig = modCodig;
    }

    public String getModDescr() {
        return modDescr;
    }

    public void setModDescr(String modDescr) {
        this.modDescr = modDescr;
    }

    public Integer getModMarca() {
        return modMarca;
    }

    public void setModMarca(Integer modMarca) {
        this.modMarca = modMarca;
    }

    public Character getModDelet() {
        return modDelet;
    }

    public void setModDelet(Character modDelet) {
        this.modDelet = modDelet;
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
        hash += (modCodig != null ? modCodig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modelos)) {
            return false;
        }
        Modelos other = (Modelos) object;
        if ((this.modCodig == null && other.modCodig != null) || (this.modCodig != null && !this.modCodig.equals(other.modCodig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iveloper.concesiones.entities.Modelos[ modCodig=" + modCodig + " ]";
    }
    
}
