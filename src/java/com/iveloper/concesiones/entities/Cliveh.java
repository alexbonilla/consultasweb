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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cliveh")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliveh.findAll", query = "SELECT c FROM Cliveh c"),
    @NamedQuery(name = "Cliveh.findByClvpaten", query = "SELECT c FROM Cliveh c WHERE c.clvpaten = :clvpaten"),
    @NamedQuery(name = "Cliveh.findByClvfecve", query = "SELECT c FROM Cliveh c WHERE c.clvfecve = :clvfecve")})
public class Cliveh implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "clvpaten")
    private String clvpaten;
    @Column(name = "clvfecve")
    @Temporal(TemporalType.TIMESTAMP)
    private Date clvfecve;
    @JoinColumn(name = "clvcateg", referencedColumnName = "cattarif")
    @ManyToOne
    private Catman clvcateg;
    @JoinColumn(name = "clvnumcl", referencedColumnName = "clinumcl")
    @ManyToOne
    private Client clvnumcl;
    @JoinColumn(name = "clvcolor", referencedColumnName = "col_codig")
    @ManyToOne
    private Color clvcolor;
    @JoinColumn(name = "clvmarca", referencedColumnName = "mar_codig")
    @ManyToOne
    private Marcas clvmarca;
    @JoinColumn(name = "clvmodel", referencedColumnName = "mod_codig")
    @ManyToOne
    private Modelos clvmodel;

    public Cliveh() {
    }

    public Cliveh(String clvpaten) {
        this.clvpaten = clvpaten;
    }

    public String getClvpaten() {
        return clvpaten;
    }

    public void setClvpaten(String clvpaten) {
        this.clvpaten = clvpaten;
    }

    public Date getClvfecve() {
        return clvfecve;
    }

    public void setClvfecve(Date clvfecve) {
        this.clvfecve = clvfecve;
    }

    public Catman getClvcateg() {
        return clvcateg;
    }

    public void setClvcateg(Catman clvcateg) {
        this.clvcateg = clvcateg;
    }

    public Client getClvnumcl() {
        return clvnumcl;
    }

    public void setClvnumcl(Client clvnumcl) {
        this.clvnumcl = clvnumcl;
    }

    public Color getClvcolor() {
        return clvcolor;
    }

    public void setClvcolor(Color clvcolor) {
        this.clvcolor = clvcolor;
    }

    public Marcas getClvmarca() {
        return clvmarca;
    }

    public void setClvmarca(Marcas clvmarca) {
        this.clvmarca = clvmarca;
    }

    public Modelos getClvmodel() {
        return clvmodel;
    }

    public void setClvmodel(Modelos clvmodel) {
        this.clvmodel = clvmodel;
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
        if (!(object instanceof Cliveh)) {
            return false;
        }
        Cliveh other = (Cliveh) object;
        if ((this.clvpaten == null && other.clvpaten != null) || (this.clvpaten != null && !this.clvpaten.equals(other.clvpaten))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iveloper.concesiones.entities.Cliveh[ clvpaten=" + clvpaten + " ]";
    }
    
}
