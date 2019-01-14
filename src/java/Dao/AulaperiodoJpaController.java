/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Clase.Aulaperiodo;
import Dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Erick
 */
public class AulaperiodoJpaController implements Serializable {

    public AulaperiodoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aulaperiodo aulaperiodo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(aulaperiodo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aulaperiodo aulaperiodo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            aulaperiodo = em.merge(aulaperiodo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = aulaperiodo.getId_AulaPeriodo();
                if (findAulaperiodo(id) == null) {
                    throw new NonexistentEntityException("The aulaperiodo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aulaperiodo aulaperiodo;
            try {
                aulaperiodo = em.getReference(Aulaperiodo.class, id);
                aulaperiodo.getId_AulaPeriodo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aulaperiodo with id " + id + " no longer exists.", enfe);
            }
            em.remove(aulaperiodo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aulaperiodo> findAulaperiodoEntities() {
        return findAulaperiodoEntities(true, -1, -1);
    }

    public List<Aulaperiodo> findAulaperiodoEntities(int maxResults, int firstResult) {
        return findAulaperiodoEntities(false, maxResults, firstResult);
    }

    private List<Aulaperiodo> findAulaperiodoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Aulaperiodo.class));
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

    public Aulaperiodo findAulaperiodo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aulaperiodo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAulaperiodoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Aulaperiodo> rt = cq.from(Aulaperiodo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
