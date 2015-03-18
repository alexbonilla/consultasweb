/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iveloper.concesiones.controllers;

import com.iveloper.concesiones.controllers.exceptions.IllegalOrphanException;
import com.iveloper.concesiones.controllers.exceptions.NonexistentEntityException;
import com.iveloper.concesiones.controllers.exceptions.PreexistingEntityException;
import com.iveloper.concesiones.controllers.exceptions.RollbackFailureException;
import com.iveloper.concesiones.entities.Estaci;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.iveloper.concesiones.entities.Viadef;
import java.util.ArrayList;
import java.util.Collection;
import com.iveloper.concesiones.entities.Transac;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author alexbonilla
 */
public class EstaciJpaController implements Serializable {

    public EstaciJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estaci estaci) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (estaci.getViadefCollection() == null) {
            estaci.setViadefCollection(new ArrayList<Viadef>());
        }
        if (estaci.getTransacCollection() == null) {
            estaci.setTransacCollection(new ArrayList<Transac>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Viadef> attachedViadefCollection = new ArrayList<Viadef>();
            for (Viadef viadefCollectionViadefToAttach : estaci.getViadefCollection()) {
                viadefCollectionViadefToAttach = em.getReference(viadefCollectionViadefToAttach.getClass(), viadefCollectionViadefToAttach.getViadefPK());
                attachedViadefCollection.add(viadefCollectionViadefToAttach);
            }
            estaci.setViadefCollection(attachedViadefCollection);
            Collection<Transac> attachedTransacCollection = new ArrayList<Transac>();
            for (Transac transacCollectionTransacToAttach : estaci.getTransacCollection()) {
                transacCollectionTransacToAttach = em.getReference(transacCollectionTransacToAttach.getClass(), transacCollectionTransacToAttach.getTraid());
                attachedTransacCollection.add(transacCollectionTransacToAttach);
            }
            estaci.setTransacCollection(attachedTransacCollection);
            em.persist(estaci);
            for (Viadef viadefCollectionViadef : estaci.getViadefCollection()) {
                Estaci oldEstaciOfViadefCollectionViadef = viadefCollectionViadef.getEstaci();
                viadefCollectionViadef.setEstaci(estaci);
                viadefCollectionViadef = em.merge(viadefCollectionViadef);
                if (oldEstaciOfViadefCollectionViadef != null) {
                    oldEstaciOfViadefCollectionViadef.getViadefCollection().remove(viadefCollectionViadef);
                    oldEstaciOfViadefCollectionViadef = em.merge(oldEstaciOfViadefCollectionViadef);
                }
            }
            for (Transac transacCollectionTransac : estaci.getTransacCollection()) {
                Estaci oldTracodestOfTransacCollectionTransac = transacCollectionTransac.getTracodest();
                transacCollectionTransac.setTracodest(estaci);
                transacCollectionTransac = em.merge(transacCollectionTransac);
                if (oldTracodestOfTransacCollectionTransac != null) {
                    oldTracodestOfTransacCollectionTransac.getTransacCollection().remove(transacCollectionTransac);
                    oldTracodestOfTransacCollectionTransac = em.merge(oldTracodestOfTransacCollectionTransac);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEstaci(estaci.getEstcodig()) != null) {
                throw new PreexistingEntityException("Estaci " + estaci + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estaci estaci) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estaci persistentEstaci = em.find(Estaci.class, estaci.getEstcodig());
            Collection<Viadef> viadefCollectionOld = persistentEstaci.getViadefCollection();
            Collection<Viadef> viadefCollectionNew = estaci.getViadefCollection();
            Collection<Transac> transacCollectionOld = persistentEstaci.getTransacCollection();
            Collection<Transac> transacCollectionNew = estaci.getTransacCollection();
            List<String> illegalOrphanMessages = null;
            for (Viadef viadefCollectionOldViadef : viadefCollectionOld) {
                if (!viadefCollectionNew.contains(viadefCollectionOldViadef)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Viadef " + viadefCollectionOldViadef + " since its estaci field is not nullable.");
                }
            }
            for (Transac transacCollectionOldTransac : transacCollectionOld) {
                if (!transacCollectionNew.contains(transacCollectionOldTransac)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Transac " + transacCollectionOldTransac + " since its tracodest field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Viadef> attachedViadefCollectionNew = new ArrayList<Viadef>();
            for (Viadef viadefCollectionNewViadefToAttach : viadefCollectionNew) {
                viadefCollectionNewViadefToAttach = em.getReference(viadefCollectionNewViadefToAttach.getClass(), viadefCollectionNewViadefToAttach.getViadefPK());
                attachedViadefCollectionNew.add(viadefCollectionNewViadefToAttach);
            }
            viadefCollectionNew = attachedViadefCollectionNew;
            estaci.setViadefCollection(viadefCollectionNew);
            Collection<Transac> attachedTransacCollectionNew = new ArrayList<Transac>();
            for (Transac transacCollectionNewTransacToAttach : transacCollectionNew) {
                transacCollectionNewTransacToAttach = em.getReference(transacCollectionNewTransacToAttach.getClass(), transacCollectionNewTransacToAttach.getTraid());
                attachedTransacCollectionNew.add(transacCollectionNewTransacToAttach);
            }
            transacCollectionNew = attachedTransacCollectionNew;
            estaci.setTransacCollection(transacCollectionNew);
            estaci = em.merge(estaci);
            for (Viadef viadefCollectionNewViadef : viadefCollectionNew) {
                if (!viadefCollectionOld.contains(viadefCollectionNewViadef)) {
                    Estaci oldEstaciOfViadefCollectionNewViadef = viadefCollectionNewViadef.getEstaci();
                    viadefCollectionNewViadef.setEstaci(estaci);
                    viadefCollectionNewViadef = em.merge(viadefCollectionNewViadef);
                    if (oldEstaciOfViadefCollectionNewViadef != null && !oldEstaciOfViadefCollectionNewViadef.equals(estaci)) {
                        oldEstaciOfViadefCollectionNewViadef.getViadefCollection().remove(viadefCollectionNewViadef);
                        oldEstaciOfViadefCollectionNewViadef = em.merge(oldEstaciOfViadefCollectionNewViadef);
                    }
                }
            }
            for (Transac transacCollectionNewTransac : transacCollectionNew) {
                if (!transacCollectionOld.contains(transacCollectionNewTransac)) {
                    Estaci oldTracodestOfTransacCollectionNewTransac = transacCollectionNewTransac.getTracodest();
                    transacCollectionNewTransac.setTracodest(estaci);
                    transacCollectionNewTransac = em.merge(transacCollectionNewTransac);
                    if (oldTracodestOfTransacCollectionNewTransac != null && !oldTracodestOfTransacCollectionNewTransac.equals(estaci)) {
                        oldTracodestOfTransacCollectionNewTransac.getTransacCollection().remove(transacCollectionNewTransac);
                        oldTracodestOfTransacCollectionNewTransac = em.merge(oldTracodestOfTransacCollectionNewTransac);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estaci.getEstcodig();
                if (findEstaci(id) == null) {
                    throw new NonexistentEntityException("The estaci with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estaci estaci;
            try {
                estaci = em.getReference(Estaci.class, id);
                estaci.getEstcodig();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estaci with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Viadef> viadefCollectionOrphanCheck = estaci.getViadefCollection();
            for (Viadef viadefCollectionOrphanCheckViadef : viadefCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estaci (" + estaci + ") cannot be destroyed since the Viadef " + viadefCollectionOrphanCheckViadef + " in its viadefCollection field has a non-nullable estaci field.");
            }
            Collection<Transac> transacCollectionOrphanCheck = estaci.getTransacCollection();
            for (Transac transacCollectionOrphanCheckTransac : transacCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estaci (" + estaci + ") cannot be destroyed since the Transac " + transacCollectionOrphanCheckTransac + " in its transacCollection field has a non-nullable tracodest field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estaci);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estaci> findEstaciEntities() {
        return findEstaciEntities(true, -1, -1);
    }

    public List<Estaci> findEstaciEntities(int maxResults, int firstResult) {
        return findEstaciEntities(false, maxResults, firstResult);
    }

    private List<Estaci> findEstaciEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estaci.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Estaci findEstaci(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estaci.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstaciCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estaci> rt = cq.from(Estaci.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
