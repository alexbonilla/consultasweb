/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iveloper.concesiones.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexbonilla
 */
@Entity
@Table(name = "viadef")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Viadef.findAll", query = "SELECT v FROM Viadef v"),
    @NamedQuery(name = "Viadef.findByViacoest", query = "SELECT v FROM Viadef v WHERE v.viadefPK.viacoest = :viacoest"),
    @NamedQuery(name = "Viadef.findByVianuvia", query = "SELECT v FROM Viadef v WHERE v.viadefPK.vianuvia = :vianuvia"),
    @NamedQuery(name = "Viadef.findByViacarril", query = "SELECT v FROM Viadef v WHERE v.viacarril = :viacarril"),
    @NamedQuery(name = "Viadef.findByViamodel", query = "SELECT v FROM Viadef v WHERE v.viamodel = :viamodel"),
    @NamedQuery(name = "Viadef.findByViasente", query = "SELECT v FROM Viadef v WHERE v.viasente = :viasente"),
    @NamedQuery(name = "Viadef.findByViaautot", query = "SELECT v FROM Viadef v WHERE v.viaautot = :viaautot"),
    @NamedQuery(name = "Viadef.findByViaviaip", query = "SELECT v FROM Viadef v WHERE v.viaviaip = :viaviaip"),
    @NamedQuery(name = "Viadef.findByViadetej", query = "SELECT v FROM Viadef v WHERE v.viadetej = :viadetej"),
    @NamedQuery(name = "Viadef.findByViadetdo", query = "SELECT v FROM Viadef v WHERE v.viadetdo = :viadetdo"),
    @NamedQuery(name = "Viadef.findByViadetal", query = "SELECT v FROM Viadef v WHERE v.viadetal = :viadetal"),
    @NamedQuery(name = "Viadef.findByVialecto", query = "SELECT v FROM Viadef v WHERE v.vialecto = :vialecto"),
    @NamedQuery(name = "Viadef.findByViatelep", query = "SELECT v FROM Viadef v WHERE v.viatelep = :viatelep"),
    @NamedQuery(name = "Viadef.findByViachip", query = "SELECT v FROM Viadef v WHERE v.viachip = :viachip"),
    @NamedQuery(name = "Viadef.findByViaclrng", query = "SELECT v FROM Viadef v WHERE v.viaclrng = :viaclrng"),
    @NamedQuery(name = "Viadef.findByViaimpcl", query = "SELECT v FROM Viadef v WHERE v.viaimpcl = :viaimpcl"),
    @NamedQuery(name = "Viadef.findByViaimpve", query = "SELECT v FROM Viadef v WHERE v.viaimpve = :viaimpve"),
    @NamedQuery(name = "Viadef.findByViarecve", query = "SELECT v FROM Viadef v WHERE v.viarecve = :viarecve"),
    @NamedQuery(name = "Viadef.findByViavideo", query = "SELECT v FROM Viadef v WHERE v.viavideo = :viavideo"),
    @NamedQuery(name = "Viadef.findByViavpath", query = "SELECT v FROM Viadef v WHERE v.viavpath = :viavpath"),
    @NamedQuery(name = "Viadef.findByViavflag", query = "SELECT v FROM Viadef v WHERE v.viavflag = :viavflag"),
    @NamedQuery(name = "Viadef.findByViavideo2", query = "SELECT v FROM Viadef v WHERE v.viavideo2 = :viavideo2"),
    @NamedQuery(name = "Viadef.findByViadista", query = "SELECT v FROM Viadef v WHERE v.viadista = :viadista"),
    @NamedQuery(name = "Viadef.findByViapuvta", query = "SELECT v FROM Viadef v WHERE v.viapuvta = :viapuvta"),
    @NamedQuery(name = "Viadef.findByViacontr", query = "SELECT v FROM Viadef v WHERE v.viacontr = :viacontr"),
    @NamedQuery(name = "Viadef.findByViadelet", query = "SELECT v FROM Viadef v WHERE v.viadelet = :viadelet"),
    @NamedQuery(name = "Viadef.findByVianombr", query = "SELECT v FROM Viadef v WHERE v.vianombr = :vianombr")})
public class Viadef implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ViadefPK viadefPK;
    @Column(name = "viacarril")
    private Integer viacarril;
    @Size(max = 255)
    @Column(name = "viamodel")
    private String viamodel;
    @Size(max = 255)
    @Column(name = "viasente")
    private String viasente;
    @Size(max = 255)
    @Column(name = "viaautot")
    private String viaautot;
    @Column(name = "viaviaip")
    private Integer viaviaip;
    @Size(max = 255)
    @Column(name = "viadetej")
    private String viadetej;
    @Size(max = 255)
    @Column(name = "viadetdo")
    private String viadetdo;
    @Size(max = 255)
    @Column(name = "viadetal")
    private String viadetal;
    @Size(max = 255)
    @Column(name = "vialecto")
    private String vialecto;
    @Size(max = 255)
    @Column(name = "viatelep")
    private String viatelep;
    @Size(max = 255)
    @Column(name = "viachip")
    private String viachip;
    @Size(max = 255)
    @Column(name = "viaclrng")
    private String viaclrng;
    @Size(max = 255)
    @Column(name = "viaimpcl")
    private String viaimpcl;
    @Size(max = 255)
    @Column(name = "viaimpve")
    private String viaimpve;
    @Size(max = 255)
    @Column(name = "viarecve")
    private String viarecve;
    @Size(max = 255)
    @Column(name = "viavideo")
    private String viavideo;
    @Size(max = 255)
    @Column(name = "viavpath")
    private String viavpath;
    @Size(max = 255)
    @Column(name = "viavflag")
    private String viavflag;
    @Size(max = 255)
    @Column(name = "viavideo2")
    private String viavideo2;
    @Column(name = "viadista")
    private Integer viadista;
    @Size(max = 255)
    @Column(name = "viapuvta")
    private String viapuvta;
    @Column(name = "viacontr")
    private Integer viacontr;
    @Size(max = 255)
    @Column(name = "viadelet")
    private String viadelet;
    @Size(max = 255)
    @Column(name = "vianombr")
    private String vianombr;
    @JoinColumn(name = "viacoest", referencedColumnName = "estcodig", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estaci estaci;

    public Viadef() {
    }

    public Viadef(ViadefPK viadefPK) {
        this.viadefPK = viadefPK;
    }

    public Viadef(int viacoest, int vianuvia) {
        this.viadefPK = new ViadefPK(viacoest, vianuvia);
    }

    public ViadefPK getViadefPK() {
        return viadefPK;
    }

    public void setViadefPK(ViadefPK viadefPK) {
        this.viadefPK = viadefPK;
    }

    public Integer getViacarril() {
        return viacarril;
    }

    public void setViacarril(Integer viacarril) {
        this.viacarril = viacarril;
    }

    public String getViamodel() {
        return viamodel;
    }

    public void setViamodel(String viamodel) {
        this.viamodel = viamodel;
    }

    public String getViasente() {
        return viasente;
    }

    public void setViasente(String viasente) {
        this.viasente = viasente;
    }

    public String getViaautot() {
        return viaautot;
    }

    public void setViaautot(String viaautot) {
        this.viaautot = viaautot;
    }

    public Integer getViaviaip() {
        return viaviaip;
    }

    public void setViaviaip(Integer viaviaip) {
        this.viaviaip = viaviaip;
    }

    public String getViadetej() {
        return viadetej;
    }

    public void setViadetej(String viadetej) {
        this.viadetej = viadetej;
    }

    public String getViadetdo() {
        return viadetdo;
    }

    public void setViadetdo(String viadetdo) {
        this.viadetdo = viadetdo;
    }

    public String getViadetal() {
        return viadetal;
    }

    public void setViadetal(String viadetal) {
        this.viadetal = viadetal;
    }

    public String getVialecto() {
        return vialecto;
    }

    public void setVialecto(String vialecto) {
        this.vialecto = vialecto;
    }

    public String getViatelep() {
        return viatelep;
    }

    public void setViatelep(String viatelep) {
        this.viatelep = viatelep;
    }

    public String getViachip() {
        return viachip;
    }

    public void setViachip(String viachip) {
        this.viachip = viachip;
    }

    public String getViaclrng() {
        return viaclrng;
    }

    public void setViaclrng(String viaclrng) {
        this.viaclrng = viaclrng;
    }

    public String getViaimpcl() {
        return viaimpcl;
    }

    public void setViaimpcl(String viaimpcl) {
        this.viaimpcl = viaimpcl;
    }

    public String getViaimpve() {
        return viaimpve;
    }

    public void setViaimpve(String viaimpve) {
        this.viaimpve = viaimpve;
    }

    public String getViarecve() {
        return viarecve;
    }

    public void setViarecve(String viarecve) {
        this.viarecve = viarecve;
    }

    public String getViavideo() {
        return viavideo;
    }

    public void setViavideo(String viavideo) {
        this.viavideo = viavideo;
    }

    public String getViavpath() {
        return viavpath;
    }

    public void setViavpath(String viavpath) {
        this.viavpath = viavpath;
    }

    public String getViavflag() {
        return viavflag;
    }

    public void setViavflag(String viavflag) {
        this.viavflag = viavflag;
    }

    public String getViavideo2() {
        return viavideo2;
    }

    public void setViavideo2(String viavideo2) {
        this.viavideo2 = viavideo2;
    }

    public Integer getViadista() {
        return viadista;
    }

    public void setViadista(Integer viadista) {
        this.viadista = viadista;
    }

    public String getViapuvta() {
        return viapuvta;
    }

    public void setViapuvta(String viapuvta) {
        this.viapuvta = viapuvta;
    }

    public Integer getViacontr() {
        return viacontr;
    }

    public void setViacontr(Integer viacontr) {
        this.viacontr = viacontr;
    }

    public String getViadelet() {
        return viadelet;
    }

    public void setViadelet(String viadelet) {
        this.viadelet = viadelet;
    }

    public String getVianombr() {
        return vianombr;
    }

    public void setVianombr(String vianombr) {
        this.vianombr = vianombr;
    }

    public Estaci getEstaci() {
        return estaci;
    }

    public void setEstaci(Estaci estaci) {
        this.estaci = estaci;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (viadefPK != null ? viadefPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Viadef)) {
            return false;
        }
        Viadef other = (Viadef) object;
        if ((this.viadefPK == null && other.viadefPK != null) || (this.viadefPK != null && !this.viadefPK.equals(other.viadefPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iveloper.concesiones.entities.Viadef[ viadefPK=" + viadefPK + " ]";
    }
    
}
