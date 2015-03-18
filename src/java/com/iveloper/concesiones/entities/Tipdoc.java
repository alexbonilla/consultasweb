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
@Table(name = "tipdoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipdoc.findAll", query = "SELECT t FROM Tipdoc t"),
    @NamedQuery(name = "Tipdoc.findByTidCodig", query = "SELECT t FROM Tipdoc t WHERE t.tidCodig = :tidCodig"),
    @NamedQuery(name = "Tipdoc.findByTidDescr", query = "SELECT t FROM Tipdoc t WHERE t.tidDescr = :tidDescr")})
public class Tipdoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "tid_codig")
    private Integer tidCodig;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tid_descr")
    private String tidDescr;

    public Tipdoc() {
    }

    public Tipdoc(Integer tidCodig) {
        this.tidCodig = tidCodig;
    }

    public Tipdoc(Integer tidCodig, String tidDescr) {
        this.tidCodig = tidCodig;
        this.tidDescr = tidDescr;
    }

    public Integer getTidCodig() {
        return tidCodig;
    }

    public void setTidCodig(Integer tidCodig) {
        this.tidCodig = tidCodig;
    }

    public String getTidDescr() {
        return tidDescr;
    }

    public void setTidDescr(String tidDescr) {
        this.tidDescr = tidDescr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tidCodig != null ? tidCodig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipdoc)) {
            return false;
        }
        Tipdoc other = (Tipdoc) object;
        if ((this.tidCodig == null && other.tidCodig != null) || (this.tidCodig != null && !this.tidCodig.equals(other.tidCodig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iveloper.concesiones.entities.Tipdoc[ tidCodig=" + tidCodig + " ]";
    }
    
}
