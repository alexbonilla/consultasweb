/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iveloper.concesiones.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "transac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transac.findAll", query = "SELECT t FROM Transac t"),
    @NamedQuery(name = "Transac.findByTranombre", query = "SELECT t FROM Transac t WHERE t.tranombre = :tranombre"),
    @NamedQuery(name = "Transac.findByTradocumento", query = "SELECT t FROM Transac t WHERE t.tradocumento = :tradocumento"),
    @NamedQuery(name = "Transac.findByTradireccion", query = "SELECT t FROM Transac t WHERE t.tradireccion = :tradireccion"),
    @NamedQuery(name = "Transac.findByTralocalidad", query = "SELECT t FROM Transac t WHERE t.tralocalidad = :tralocalidad"),
    @NamedQuery(name = "Transac.findByTratipo", query = "SELECT t FROM Transac t WHERE t.tratipo = :tratipo"),
    @NamedQuery(name = "Transac.findByTraprovincia", query = "SELECT t FROM Transac t WHERE t.traprovincia = :traprovincia"),
    @NamedQuery(name = "Transac.findByTratelefono", query = "SELECT t FROM Transac t WHERE t.tratelefono = :tratelefono"),
    @NamedQuery(name = "Transac.findByTrafecha", query = "SELECT t FROM Transac t WHERE t.trafecha = :trafecha"),
    @NamedQuery(name = "Transac.findByTrahora", query = "SELECT t FROM Transac t WHERE t.trahora = :trahora"),
    @NamedQuery(name = "Transac.findByTrafectran", query = "SELECT t FROM Transac t WHERE t.trafectran = :trafectran"),
    @NamedQuery(name = "Transac.findByTrahoratra", query = "SELECT t FROM Transac t WHERE t.trahoratra = :trahoratra"),
    @NamedQuery(name = "Transac.findByTraestacion", query = "SELECT t FROM Transac t WHERE t.traestacion = :traestacion"),
    @NamedQuery(name = "Transac.findByTravia", query = "SELECT t FROM Transac t WHERE t.travia = :travia"),
    @NamedQuery(name = "Transac.findByTratoperacion", query = "SELECT t FROM Transac t WHERE t.tratoperacion = :tratoperacion"),
    @NamedQuery(name = "Transac.findByTraplaca", query = "SELECT t FROM Transac t WHERE t.traplaca = :traplaca"),
    @NamedQuery(name = "Transac.findByTradispositivo", query = "SELECT t FROM Transac t WHERE t.tradispositivo = :tradispositivo"),
    @NamedQuery(name = "Transac.findByTracategoria", query = "SELECT t FROM Transac t WHERE t.tracategoria = :tracategoria"),
    @NamedQuery(name = "Transac.findByTralecman", query = "SELECT t FROM Transac t WHERE t.tralecman = :tralecman"),
    @NamedQuery(name = "Transac.findByTraval", query = "SELECT t FROM Transac t WHERE t.traval = :traval"),
    @NamedQuery(name = "Transac.findByTramontocr", query = "SELECT t FROM Transac t WHERE t.tramontocr = :tramontocr"),
    @NamedQuery(name = "Transac.findByTramontodb", query = "SELECT t FROM Transac t WHERE t.tramontodb = :tramontodb"),
    @NamedQuery(name = "Transac.findByTrasaldo", query = "SELECT t FROM Transac t WHERE t.trasaldo = :trasaldo"),
    @NamedQuery(name = "Transac.findByTraagrupacion", query = "SELECT t FROM Transac t WHERE t.traagrupacion = :traagrupacion"),
    @NamedQuery(name = "Transac.findByTraobservacion", query = "SELECT t FROM Transac t WHERE t.traobservacion = :traobservacion"),
    @NamedQuery(name = "Transac.findByTraid", query = "SELECT t FROM Transac t WHERE t.traid = :traid"),
    @NamedQuery(name = "Transac.findByTrafactu", query = "SELECT t FROM Transac t WHERE t.trafactu = :trafactu"),
    @NamedQuery(name = "Transac.findByTrafechaTrans", query = "SELECT t FROM Transac t WHERE t.trafechaTrans = :trafechaTrans"),
    /*
    Consultas definidas para consultas del portal
    */
    @NamedQuery(name = "Transac.findByTracliAndDateRange", query = "SELECT t FROM Transac t WHERE t.tracli.clinumcl = :tracli AND t.trafecha BETWEEN :startdate AND :enddate")})
public class Transac implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "tranombre")
    private String tranombre;
    @Size(max = 255)
    @Column(name = "tradocumento")
    private String tradocumento;
    @Size(max = 255)
    @Column(name = "tradireccion")
    private String tradireccion;
    @Size(max = 255)
    @Column(name = "tralocalidad")
    private String tralocalidad;
    @Size(max = 255)
    @Column(name = "tratipo")
    private String tratipo;
    @Size(max = 255)
    @Column(name = "traprovincia")
    private String traprovincia;
    @Size(max = 255)
    @Column(name = "tratelefono")
    private String tratelefono;
    @Column(name = "trafecha")
    @Temporal(TemporalType.DATE)
    private Date trafecha;
    @Column(name = "trahora")
    @Temporal(TemporalType.TIME)
    private Date trahora;
    @Column(name = "trafectran")
    @Temporal(TemporalType.DATE)
    private Date trafectran;
    @Column(name = "trahoratra")
    @Temporal(TemporalType.TIME)
    private Date trahoratra;
    @Size(max = 255)
    @Column(name = "traestacion")
    private String traestacion;
    @Size(max = 255)
    @Column(name = "travia")
    private String travia;
    @Size(max = 255)
    @Column(name = "tratoperacion")
    private String tratoperacion;
    @Size(max = 255)
    @Column(name = "traplaca")
    private String traplaca;
    @Size(max = 255)
    @Column(name = "tradispositivo")
    private String tradispositivo;
    @Size(max = 255)
    @Column(name = "tracategoria")
    private String tracategoria;
    @Size(max = 255)
    @Column(name = "tralecman")
    private String tralecman;
    @Size(max = 255)
    @Column(name = "traval")
    private String traval;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tramontocr")
    private BigDecimal tramontocr;
    @Column(name = "tramontodb")
    private BigDecimal tramontodb;
    @Column(name = "trasaldo")
    private BigDecimal trasaldo;
    @Size(max = 255)
    @Column(name = "traagrupacion")
    private String traagrupacion;
    @Size(max = 255)
    @Column(name = "traobservacion")
    private String traobservacion;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "traid")
    private Integer traid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "trafactu")
    private String trafactu;
    @Column(name = "tra_fechaTrans")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trafechaTrans;
    @JoinColumn(name = "tracli", referencedColumnName = "clinumcl")
    @ManyToOne(optional = false)
    private Client tracli;
    @JoinColumn(name = "tracodest", referencedColumnName = "estcodig")
    @ManyToOne(optional = false)
    private Estaci tracodest;

    public Transac() {
    }

    public Transac(Integer traid) {
        this.traid = traid;
    }

    public Transac(Integer traid, String trafactu) {
        this.traid = traid;
        this.trafactu = trafactu;
    }

    public String getTranombre() {
        return tranombre;
    }

    public void setTranombre(String tranombre) {
        this.tranombre = tranombre;
    }

    public String getTradocumento() {
        return tradocumento;
    }

    public void setTradocumento(String tradocumento) {
        this.tradocumento = tradocumento;
    }

    public String getTradireccion() {
        return tradireccion;
    }

    public void setTradireccion(String tradireccion) {
        this.tradireccion = tradireccion;
    }

    public String getTralocalidad() {
        return tralocalidad;
    }

    public void setTralocalidad(String tralocalidad) {
        this.tralocalidad = tralocalidad;
    }

    public String getTratipo() {
        return tratipo;
    }

    public void setTratipo(String tratipo) {
        this.tratipo = tratipo;
    }

    public String getTraprovincia() {
        return traprovincia;
    }

    public void setTraprovincia(String traprovincia) {
        this.traprovincia = traprovincia;
    }

    public String getTratelefono() {
        return tratelefono;
    }

    public void setTratelefono(String tratelefono) {
        this.tratelefono = tratelefono;
    }

    public Date getTrafecha() {
        return trafecha;
    }

    public void setTrafecha(Date trafecha) {
        this.trafecha = trafecha;
    }

    public Date getTrahora() {
        return trahora;
    }

    public void setTrahora(Date trahora) {
        this.trahora = trahora;
    }

    public Date getTrafectran() {
        return trafectran;
    }

    public void setTrafectran(Date trafectran) {
        this.trafectran = trafectran;
    }

    public Date getTrahoratra() {
        return trahoratra;
    }

    public void setTrahoratra(Date trahoratra) {
        this.trahoratra = trahoratra;
    }

    public String getTraestacion() {
        return traestacion;
    }

    public void setTraestacion(String traestacion) {
        this.traestacion = traestacion;
    }

    public String getTravia() {
        return travia;
    }

    public void setTravia(String travia) {
        this.travia = travia;
    }

    public String getTratoperacion() {
        return tratoperacion;
    }

    public void setTratoperacion(String tratoperacion) {
        this.tratoperacion = tratoperacion;
    }

    public String getTraplaca() {
        return traplaca;
    }

    public void setTraplaca(String traplaca) {
        this.traplaca = traplaca;
    }

    public String getTradispositivo() {
        return tradispositivo;
    }

    public void setTradispositivo(String tradispositivo) {
        this.tradispositivo = tradispositivo;
    }

    public String getTracategoria() {
        return tracategoria;
    }

    public void setTracategoria(String tracategoria) {
        this.tracategoria = tracategoria;
    }

    public String getTralecman() {
        return tralecman;
    }

    public void setTralecman(String tralecman) {
        this.tralecman = tralecman;
    }

    public String getTraval() {
        return traval;
    }

    public void setTraval(String traval) {
        this.traval = traval;
    }

    public BigDecimal getTramontocr() {
        return tramontocr;
    }

    public void setTramontocr(BigDecimal tramontocr) {
        this.tramontocr = tramontocr;
    }

    public BigDecimal getTramontodb() {
        return tramontodb;
    }

    public void setTramontodb(BigDecimal tramontodb) {
        this.tramontodb = tramontodb;
    }

    public BigDecimal getTrasaldo() {
        return trasaldo;
    }

    public void setTrasaldo(BigDecimal trasaldo) {
        this.trasaldo = trasaldo;
    }

    public String getTraagrupacion() {
        return traagrupacion;
    }

    public void setTraagrupacion(String traagrupacion) {
        this.traagrupacion = traagrupacion;
    }

    public String getTraobservacion() {
        return traobservacion;
    }

    public void setTraobservacion(String traobservacion) {
        this.traobservacion = traobservacion;
    }

    public Integer getTraid() {
        return traid;
    }

    public void setTraid(Integer traid) {
        this.traid = traid;
    }

    public String getTrafactu() {
        return trafactu;
    }

    public void setTrafactu(String trafactu) {
        this.trafactu = trafactu;
    }

    public Date getTrafechaTrans() {
        return trafechaTrans;
    }

    public void setTrafechaTrans(Date trafechaTrans) {
        this.trafechaTrans = trafechaTrans;
    }

    public Client getTracli() {
        return tracli;
    }

    public void setTracli(Client tracli) {
        this.tracli = tracli;
    }

    public Estaci getTracodest() {
        return tracodest;
    }

    public void setTracodest(Estaci tracodest) {
        this.tracodest = tracodest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (traid != null ? traid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transac)) {
            return false;
        }
        Transac other = (Transac) object;
        if ((this.traid == null && other.traid != null) || (this.traid != null && !this.traid.equals(other.traid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iveloper.concesiones.entities.Transac[ traid=" + traid + " ]";
    }
    
}
