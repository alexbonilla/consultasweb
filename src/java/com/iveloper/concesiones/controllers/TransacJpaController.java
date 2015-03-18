/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iveloper.concesiones.controllers;

import com.iveloper.concesiones.controllers.exceptions.NonexistentEntityException;
import com.iveloper.concesiones.controllers.exceptions.PreexistingEntityException;
import com.iveloper.concesiones.controllers.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.iveloper.concesiones.entities.Client;
import com.iveloper.concesiones.entities.Estaci;
import com.iveloper.concesiones.entities.Transac;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author alexbonilla
 */
public class TransacJpaController implements Serializable {

    public TransacJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Transac transac) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Client tracli = transac.getTracli();
            if (tracli != null) {
                tracli = em.getReference(tracli.getClass(), tracli.getClinumcl());
                transac.setTracli(tracli);
            }
            Estaci tracodest = transac.getTracodest();
            if (tracodest != null) {
                tracodest = em.getReference(tracodest.getClass(), tracodest.getEstcodig());
                transac.setTracodest(tracodest);
            }
            em.persist(transac);
            if (tracli != null) {
                tracli.getTransacCollection().add(transac);
                tracli = em.merge(tracli);
            }
            if (tracodest != null) {
                tracodest.getTransacCollection().add(transac);
                tracodest = em.merge(tracodest);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTransac(transac.getTraid()) != null) {
                throw new PreexistingEntityException("Transac " + transac + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Transac transac) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Transac persistentTransac = em.find(Transac.class, transac.getTraid());
            Client tracliOld = persistentTransac.getTracli();
            Client tracliNew = transac.getTracli();
            Estaci tracodestOld = persistentTransac.getTracodest();
            Estaci tracodestNew = transac.getTracodest();
            if (tracliNew != null) {
                tracliNew = em.getReference(tracliNew.getClass(), tracliNew.getClinumcl());
                transac.setTracli(tracliNew);
            }
            if (tracodestNew != null) {
                tracodestNew = em.getReference(tracodestNew.getClass(), tracodestNew.getEstcodig());
                transac.setTracodest(tracodestNew);
            }
            transac = em.merge(transac);
            if (tracliOld != null && !tracliOld.equals(tracliNew)) {
                tracliOld.getTransacCollection().remove(transac);
                tracliOld = em.merge(tracliOld);
            }
            if (tracliNew != null && !tracliNew.equals(tracliOld)) {
                tracliNew.getTransacCollection().add(transac);
                tracliNew = em.merge(tracliNew);
            }
            if (tracodestOld != null && !tracodestOld.equals(tracodestNew)) {
                tracodestOld.getTransacCollection().remove(transac);
                tracodestOld = em.merge(tracodestOld);
            }
            if (tracodestNew != null && !tracodestNew.equals(tracodestOld)) {
                tracodestNew.getTransacCollection().add(transac);
                tracodestNew = em.merge(tracodestNew);
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
                Integer id = transac.getTraid();
                if (findTransac(id) == null) {
                    throw new NonexistentEntityException("The transac with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Transac transac;
            try {
                transac = em.getReference(Transac.class, id);
                transac.getTraid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transac with id " + id + " no longer exists.", enfe);
            }
            Client tracli = transac.getTracli();
            if (tracli != null) {
                tracli.getTransacCollection().remove(transac);
                tracli = em.merge(tracli);
            }
            Estaci tracodest = transac.getTracodest();
            if (tracodest != null) {
                tracodest.getTransacCollection().remove(transac);
                tracodest = em.merge(tracodest);
            }
            em.remove(transac);
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

    public List<Transac> findTransacEntities() {
        return findTransacEntities(true, -1, -1);
    }

    public List<Transac> findTransacEntities(int maxResults, int firstResult) {
        return findTransacEntities(false, maxResults, firstResult);
    }

    private List<Transac> findTransacEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Transac.class));
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

    public Transac findTransac(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Transac.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransacCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Transac> rt = cq.from(Transac.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /*
     Aqui empiezan las funciones definidas especificamente para el portal de usuarios
     */
//    public Collection<Transac> findTransacsByCustomerId(int clientid) {
//        ClientJpaController clientJpaController = new ClientJpaController(utx, emf);
//        Client client = clientJpaController.findClient(clientid);
//        return client.getTransacCollection();
//    }

    public Collection<Transac> findByTracliAndDateRange(int tracli, Date startdate, Date enddate) {

        Query query = getEntityManager().createNamedQuery("Transac.findByTracliAndDateRange", com.iveloper.concesiones.entities.Transac.class);
        query.setParameter("tracli", tracli);
        query.setParameter("startdate", startdate);
        query.setParameter("enddate", enddate);

        return query.getResultList();
    }

}
