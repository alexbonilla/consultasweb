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
import com.iveloper.concesiones.entities.Client;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.iveloper.concesiones.entities.Cliveh;
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
public class ClientJpaController implements Serializable {

    public ClientJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Client client) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (client.getClivehCollection() == null) {
            client.setClivehCollection(new ArrayList<Cliveh>());
        }
        if (client.getTransacCollection() == null) {
            client.setTransacCollection(new ArrayList<Transac>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Cliveh> attachedClivehCollection = new ArrayList<Cliveh>();
            for (Cliveh clivehCollectionClivehToAttach : client.getClivehCollection()) {
                clivehCollectionClivehToAttach = em.getReference(clivehCollectionClivehToAttach.getClass(), clivehCollectionClivehToAttach.getClvpaten());
                attachedClivehCollection.add(clivehCollectionClivehToAttach);
            }
            client.setClivehCollection(attachedClivehCollection);
            Collection<Transac> attachedTransacCollection = new ArrayList<Transac>();
            for (Transac transacCollectionTransacToAttach : client.getTransacCollection()) {
                transacCollectionTransacToAttach = em.getReference(transacCollectionTransacToAttach.getClass(), transacCollectionTransacToAttach.getTraid());
                attachedTransacCollection.add(transacCollectionTransacToAttach);
            }
            client.setTransacCollection(attachedTransacCollection);
            em.persist(client);
            for (Cliveh clivehCollectionCliveh : client.getClivehCollection()) {
                Client oldClvnumclOfClivehCollectionCliveh = clivehCollectionCliveh.getClvnumcl();
                clivehCollectionCliveh.setClvnumcl(client);
                clivehCollectionCliveh = em.merge(clivehCollectionCliveh);
                if (oldClvnumclOfClivehCollectionCliveh != null) {
                    oldClvnumclOfClivehCollectionCliveh.getClivehCollection().remove(clivehCollectionCliveh);
                    oldClvnumclOfClivehCollectionCliveh = em.merge(oldClvnumclOfClivehCollectionCliveh);
                }
            }
            for (Transac transacCollectionTransac : client.getTransacCollection()) {
                Client oldTracliOfTransacCollectionTransac = transacCollectionTransac.getTracli();
                transacCollectionTransac.setTracli(client);
                transacCollectionTransac = em.merge(transacCollectionTransac);
                if (oldTracliOfTransacCollectionTransac != null) {
                    oldTracliOfTransacCollectionTransac.getTransacCollection().remove(transacCollectionTransac);
                    oldTracliOfTransacCollectionTransac = em.merge(oldTracliOfTransacCollectionTransac);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findClient(client.getClinumcl()) != null) {
                throw new PreexistingEntityException("Client " + client + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Client client) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Client persistentClient = em.find(Client.class, client.getClinumcl());
            Collection<Cliveh> clivehCollectionOld = persistentClient.getClivehCollection();
            Collection<Cliveh> clivehCollectionNew = client.getClivehCollection();
            Collection<Transac> transacCollectionOld = persistentClient.getTransacCollection();
            Collection<Transac> transacCollectionNew = client.getTransacCollection();
            List<String> illegalOrphanMessages = null;
            for (Transac transacCollectionOldTransac : transacCollectionOld) {
                if (!transacCollectionNew.contains(transacCollectionOldTransac)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Transac " + transacCollectionOldTransac + " since its tracli field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Cliveh> attachedClivehCollectionNew = new ArrayList<Cliveh>();
            for (Cliveh clivehCollectionNewClivehToAttach : clivehCollectionNew) {
                clivehCollectionNewClivehToAttach = em.getReference(clivehCollectionNewClivehToAttach.getClass(), clivehCollectionNewClivehToAttach.getClvpaten());
                attachedClivehCollectionNew.add(clivehCollectionNewClivehToAttach);
            }
            clivehCollectionNew = attachedClivehCollectionNew;
            client.setClivehCollection(clivehCollectionNew);
            Collection<Transac> attachedTransacCollectionNew = new ArrayList<Transac>();
            for (Transac transacCollectionNewTransacToAttach : transacCollectionNew) {
                transacCollectionNewTransacToAttach = em.getReference(transacCollectionNewTransacToAttach.getClass(), transacCollectionNewTransacToAttach.getTraid());
                attachedTransacCollectionNew.add(transacCollectionNewTransacToAttach);
            }
            transacCollectionNew = attachedTransacCollectionNew;
            client.setTransacCollection(transacCollectionNew);
            client = em.merge(client);
            for (Cliveh clivehCollectionOldCliveh : clivehCollectionOld) {
                if (!clivehCollectionNew.contains(clivehCollectionOldCliveh)) {
                    clivehCollectionOldCliveh.setClvnumcl(null);
                    clivehCollectionOldCliveh = em.merge(clivehCollectionOldCliveh);
                }
            }
            for (Cliveh clivehCollectionNewCliveh : clivehCollectionNew) {
                if (!clivehCollectionOld.contains(clivehCollectionNewCliveh)) {
                    Client oldClvnumclOfClivehCollectionNewCliveh = clivehCollectionNewCliveh.getClvnumcl();
                    clivehCollectionNewCliveh.setClvnumcl(client);
                    clivehCollectionNewCliveh = em.merge(clivehCollectionNewCliveh);
                    if (oldClvnumclOfClivehCollectionNewCliveh != null && !oldClvnumclOfClivehCollectionNewCliveh.equals(client)) {
                        oldClvnumclOfClivehCollectionNewCliveh.getClivehCollection().remove(clivehCollectionNewCliveh);
                        oldClvnumclOfClivehCollectionNewCliveh = em.merge(oldClvnumclOfClivehCollectionNewCliveh);
                    }
                }
            }
            for (Transac transacCollectionNewTransac : transacCollectionNew) {
                if (!transacCollectionOld.contains(transacCollectionNewTransac)) {
                    Client oldTracliOfTransacCollectionNewTransac = transacCollectionNewTransac.getTracli();
                    transacCollectionNewTransac.setTracli(client);
                    transacCollectionNewTransac = em.merge(transacCollectionNewTransac);
                    if (oldTracliOfTransacCollectionNewTransac != null && !oldTracliOfTransacCollectionNewTransac.equals(client)) {
                        oldTracliOfTransacCollectionNewTransac.getTransacCollection().remove(transacCollectionNewTransac);
                        oldTracliOfTransacCollectionNewTransac = em.merge(oldTracliOfTransacCollectionNewTransac);
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
                Integer id = client.getClinumcl();
                if (findClient(id) == null) {
                    throw new NonexistentEntityException("The client with id " + id + " no longer exists.");
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
            Client client;
            try {
                client = em.getReference(Client.class, id);
                client.getClinumcl();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The client with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Transac> transacCollectionOrphanCheck = client.getTransacCollection();
            for (Transac transacCollectionOrphanCheckTransac : transacCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Client (" + client + ") cannot be destroyed since the Transac " + transacCollectionOrphanCheckTransac + " in its transacCollection field has a non-nullable tracli field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Cliveh> clivehCollection = client.getClivehCollection();
            for (Cliveh clivehCollectionCliveh : clivehCollection) {
                clivehCollectionCliveh.setClvnumcl(null);
                clivehCollectionCliveh = em.merge(clivehCollectionCliveh);
            }
            em.remove(client);
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

    public List<Client> findClientEntities() {
        return findClientEntities(true, -1, -1);
    }

    public List<Client> findClientEntities(int maxResults, int firstResult) {
        return findClientEntities(false, maxResults, firstResult);
    }

    private List<Client> findClientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Client.class));
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

    public Client findClient(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Client.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Client> rt = cq.from(Client.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
