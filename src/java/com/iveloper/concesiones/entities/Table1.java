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
 * @author alexbonilla
 */
@Entity
@Table(name = "table1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Table1.findAll", query = "SELECT t FROM Table1 t"),
    @NamedQuery(name = "Table1.findByClvpaten", query = "SELECT t FROM Table1 t WHERE t.clvpaten = :clvpaten"),
    @NamedQuery(name = "Table1.findByClvnumcl", query = "SELECT t FROM Table1 t WHERE t.clvnumcl = :clvnumcl"),
    @NamedQuery(name = "Table1.findByClvmarca", query = "SELECT t FROM Table1 t WHERE t.clvmarca = :clvmarca"),
    @NamedQuery(name = "Table1.findByClvmodel", query = "SELECT t FROM Table1 t WHERE t.clvmodel = :clvmodel"),
    @NamedQuery(name = "Table1.findByClvcolor", query = "SELECT t FROM Table1 t WHERE t.clvcolor = :clvcolor"),
    @NamedQuery(name = "Table1.findByClvcateg", query = "SELECT t FROM Table1 t WHERE t.clvcateg = :clvcateg"),
    @NamedQuery(name = "Table1.findByClvfecve", query = "SELECT t FROM Table1 t WHERE t.clvfecve = :clvfecve")})
public class Table1 implements Serializable {
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

    public Table1() {
    }

    public Table1(String clvpaten) {
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
        if (!(object instanceof Table1)) {
            return false;
        }
        Table1 other = (Table1) object;
        if ((this.clvpaten == null && other.clvpaten != null) || (this.clvpaten != null && !this.clvpaten.equals(other.clvpaten))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iveloper.concesiones.entities.Table1[ clvpaten=" + clvpaten + " ]";
    }
    
}
