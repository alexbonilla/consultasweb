/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iveloper.concesiones.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alexbonilla
 */
@Embeddable
public class ViadefPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "viacoest")
    private int viacoest;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vianuvia")
    private int vianuvia;

    public ViadefPK() {
    }

    public ViadefPK(int viacoest, int vianuvia) {
        this.viacoest = viacoest;
        this.vianuvia = vianuvia;
    }

    public int getViacoest() {
        return viacoest;
    }

    public void setViacoest(int viacoest) {
        this.viacoest = viacoest;
    }

    public int getVianuvia() {
        return vianuvia;
    }

    public void setVianuvia(int vianuvia) {
        this.vianuvia = vianuvia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) viacoest;
        hash += (int) vianuvia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ViadefPK)) {
            return false;
        }
        ViadefPK other = (ViadefPK) object;
        if (this.viacoest != other.viacoest) {
            return false;
        }
        if (this.vianuvia != other.vianuvia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iveloper.concesiones.entities.ViadefPK[ viacoest=" + viacoest + ", vianuvia=" + vianuvia + " ]";
    }
    
}
