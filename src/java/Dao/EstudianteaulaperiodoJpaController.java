/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Clase.Estudianteaulaperiodo;
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
public class EstudianteaulaperiodoJpaController implements Serializable {

    public EstudianteaulaperiodoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estudianteaulaperiodo estudianteaulaperiodo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estudianteaulaperiodo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estudianteaulaperiodo estudianteaulaperiodo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estudianteaulaperiodo = em.merge(estudianteaulaperiodo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estudianteaulaperiodo.getId();
                if (findEstudianteaulaperiodo(id) == null) {
                    throw new NonexistentEntityException("The estudianteaulaperiodo with id " + id + " no longer exists.");
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
            Estudianteaulaperiodo estudianteaulaperiodo;
            try {
                estudianteaulaperiodo = em.getReference(Estudianteaulaperiodo.class, id);
                estudianteaulaperiodo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudianteaulaperiodo with id " + id + " no longer exists.", enfe);
            }
            em.remove(estudianteaulaperiodo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estudianteaulaperiodo> findEstudianteaulaperiodoEntities() {
        return findEstudianteaulaperiodoEntities(true, -1, -1);
    }

    public List<Estudianteaulaperiodo> findEstudianteaulaperiodoEntities(int maxResults, int firstResult) {
        return findEstudianteaulaperiodoEntities(false, maxResults, firstResult);
    }

    private List<Estudianteaulaperiodo> findEstudianteaulaperiodoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estudianteaulaperiodo.class));
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

    public Estudianteaulaperiodo findEstudianteaulaperiodo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estudianteaulaperiodo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstudianteaulaperiodoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudianteaulaperiodo> rt = cq.from(Estudianteaulaperiodo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
