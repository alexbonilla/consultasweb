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
@Table(name = "color")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Color.findAll", query = "SELECT c FROM Color c"),
    @NamedQuery(name = "Color.findByColCodig", query = "SELECT c FROM Color c WHERE c.colCodig = :colCodig"),
    @NamedQuery(name = "Color.findByColDescr", query = "SELECT c FROM Color c WHERE c.colDescr = :colDescr"),
    @NamedQuery(name = "Color.findByColDelet", query = "SELECT c FROM Color c WHERE c.colDelet = :colDelet")})
public class Color implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "col_codig")
    private Integer colCodig;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "col_descr")
    private String colDescr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "col_delet")
    private Character colDelet;
    @OneToMany(mappedBy = "clvcolor")
    private Collection<Cliveh> clivehCollection;

    public Color() {
    }

    public Color(Integer colCodig) {
        this.colCodig = colCodig;
    }

    public Color(Integer colCodig, String colDescr, Character colDelet) {
        this.colCodig = colCodig;
        this.colDescr = colDescr;
        this.colDelet = colDelet;
    }

    public Integer getColCodig() {
        return colCodig;
    }

    public void setColCodig(Integer colCodig) {
        this.colCodig = colCodig;
    }

    public String getColDescr() {
        return colDescr;
    }

    public void setColDescr(String colDescr) {
        this.colDescr = colDescr;
    }

    public Character getColDelet() {
        return colDelet;
    }

    public void setColDelet(Character colDelet) {
        this.colDelet = colDelet;
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
        hash += (colCodig != null ? colCodig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Color)) {
            return false;
        }
        Color other = (Color) object;
        if ((this.colCodig == null && other.colCodig != null) || (this.colCodig != null && !this.colCodig.equals(other.colCodig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iveloper.concesiones.entities.Color[ colCodig=" + colCodig + " ]";
    }
    
}
