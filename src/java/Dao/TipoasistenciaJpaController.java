/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Clase.Tipoasistencia;
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
public class TipoasistenciaJpaController implements Serializable {

    public TipoasistenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoasistencia tipoasistencia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoasistencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoasistencia tipoasistencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoasistencia = em.merge(tipoasistencia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoasistencia.getId_tipoasistencia();
                if (findTipoasistencia(id) == null) {
                    throw new NonexistentEntityException("The tipoasistencia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoasistencia tipoasistencia;
            try {
                tipoasistencia = em.getReference(Tipoasistencia.class, id);
                tipoasistencia.getId_tipoasistencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoasistencia with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoasistencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoasistencia> findTipoasistenciaEntities() {
        return findTipoasistenciaEntities(true, -1, -1);
    }

    public List<Tipoasistencia> findTipoasistenciaEntities(int maxResults, int firstResult) {
        return findTipoasistenciaEntities(false, maxResults, firstResult);
    }

    private List<Tipoasistencia> findTipoasistenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoasistencia.class));
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

    public Tipoasistencia findTipoasistencia(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoasistencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoasistenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipoasistencia> rt = cq.from(Tipoasistencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
