/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iveloper.concesiones.beans;

import com.iveloper.concesiones.entities.Estaci;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alexbonilla
 */
@Stateless
public class EstaciFacade extends AbstractFacade<Estaci> {
    @PersistenceContext(unitName = "consultaswebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstaciFacade() {
        super(Estaci.class);
    }
    
}
