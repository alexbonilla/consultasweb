/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iveloper.concesiones.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "vehiculos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculos.findAll", query = "SELECT v FROM Vehiculos v"),
    @NamedQuery(name = "Vehiculos.findByClvpaten", query = "SELECT v FROM Vehiculos v WHERE v.clvpaten = :clvpaten"),
    @NamedQuery(name = "Vehiculos.findByClvnumcl", query = "SELECT v FROM Vehiculos v WHERE v.clvnumcl = :clvnumcl"),
    @NamedQuery(name = "Vehiculos.findByClvmarca", query = "SELECT v FROM Vehiculos v WHERE v.clvmarca = :clvmarca"),
    @NamedQuery(name = "Vehiculos.findByClvmodel", query = "SELECT v FROM Vehiculos v WHERE v.clvmodel = :clvmodel"),
    @NamedQuery(name = "Vehiculos.findByClvcolor", query = "SELECT v FROM Vehiculos v WHERE v.clvcolor = :clvcolor"),
    @NamedQuery(name = "Vehiculos.findByClvcateg", query = "SELECT v FROM Vehiculos v WHERE v.clvcateg = :clvcateg"),
    @NamedQuery(name = "Vehiculos.findByClvfecve", query = "SELECT v FROM Vehiculos v WHERE v.clvfecve = :clvfecve")})
public class Vehiculos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "clvpaten")
    private String clvpaten;
    @Column(name = "clvnumcl")
    private Integer clvnumcl;
    @Column(name = "clvmarca")
    private Integer clvmarca;
    @Column(name = "clvmodel")
    private Integer clvmodel;
    @Column(name = "clvcolor")
    private Integer clvcolor;
    @Column(name = "clvcateg")
    private Integer clvcateg;
    @Column(name = "clvfecve")
    @Temporal(TemporalType.TIMESTAMP)
    private Date clvfecve;

    public Vehiculos() {
    }

    public Vehiculos(String clvpaten) {
        this.clvpaten = clvpaten;
    }

    public String getClvpaten() {
        return clvpaten;
    }

    public void setClvpaten(String clvpaten) {
        this.clvpaten = clvpaten;
    }

    public Integer getClvnumcl() {
        return clvnumcl;
    }

    public void setClvnumcl(Integer clvnumcl) {
        this.clvnumcl = clvnumcl;
    }

    public Integer getClvmarca() {
        return clvmarca;
    }

    public void setClvmarca(Integer clvmarca) {
        this.clvmarca = clvmarca;
    }

    public Integer getClvmodel() {
        return clvmodel;
    }

    public void setClvmodel(Integer clvmodel) {
        this.clvmodel = clvmodel;
    }

    public Integer getClvcolor() {
        return clvcolor;
    }

    public void setClvcolor(Integer clvcolor) {
        this.clvcolor = clvcolor;
    }

    public Integer getClvcateg() {
        return clvcateg;
    }

    public void setClvcateg(Integer clvcateg) {
        this.clvcateg = clvcateg;
    }

    public Date getClvfecve() {
        return clvfecve;
    }

    public void setClvfecve(Date clvfecve) {
        this.clvfecve = clvfecve;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clvpaten != null ? clvpaten.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculos)) {
            return false;
        }
        Vehiculos other = (Vehiculos) object;
        if ((this.clvpaten == null && other.clvpaten != null) || (this.clvpaten != null && !this.clvpaten.equals(other.clvpaten))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iveloper.concesiones.entities.Vehiculos[ clvpaten=" + clvpaten + " ]";
    }
    
}
