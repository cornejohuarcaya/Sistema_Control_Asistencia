/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Clase.Periodoestudio;
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
public class PeriodoestudioJpaController implements Serializable {

    public PeriodoestudioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Periodoestudio periodoestudio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(periodoestudio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Periodoestudio periodoestudio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            periodoestudio = em.merge(periodoestudio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = periodoestudio.getId_periodoestudio();
                if (findPeriodoestudio(id) == null) {
                    throw new NonexistentEntityException("The periodoestudio with id " + id + " no longer exists.");
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
            Periodoestudio periodoestudio;
            try {
                periodoestudio = em.getReference(Periodoestudio.class, id);
                periodoestudio.getId_periodoestudio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The periodoestudio with id " + id + " no longer exists.", enfe);
            }
            em.remove(periodoestudio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Periodoestudio> findPeriodoestudioEntities() {
        return findPeriodoestudioEntities(true, -1, -1);
    }

    public List<Periodoestudio> findPeriodoestudioEntities(int maxResults, int firstResult) {
        return findPeriodoestudioEntities(false, maxResults, firstResult);
    }

    private List<Periodoestudio> findPeriodoestudioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Periodoestudio.class));
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

    public Periodoestudio findPeriodoestudio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Periodoestudio.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeriodoestudioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Periodoestudio> rt = cq.from(Periodoestudio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
