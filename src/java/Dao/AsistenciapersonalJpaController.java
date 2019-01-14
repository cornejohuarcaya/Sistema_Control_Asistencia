/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Clase.Asistenciapersonal;
import Dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.Date;
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
public class AsistenciapersonalJpaController implements Serializable {

    public AsistenciapersonalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asistenciapersonal asistenciapersonal) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(asistenciapersonal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asistenciapersonal asistenciapersonal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            asistenciapersonal = em.merge(asistenciapersonal);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asistenciapersonal.getId();
                if (findAsistenciapersonal(id) == null) {
                    throw new NonexistentEntityException("The asistenciapersonal with id " + id + " no longer exists.");
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
            Asistenciapersonal asistenciapersonal;
            try {
                asistenciapersonal = em.getReference(Asistenciapersonal.class, id);
                asistenciapersonal.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asistenciapersonal with id " + id + " no longer exists.", enfe);
            }
            em.remove(asistenciapersonal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asistenciapersonal> findAsistenciapersonalEntities() {
        return findAsistenciapersonalEntities(true, -1, -1);
    }

    public List<Asistenciapersonal> findAsistenciapersonalEntities(int maxResults, int firstResult) {
        return findAsistenciapersonalEntities(false, maxResults, firstResult);
    }

    private List<Asistenciapersonal> findAsistenciapersonalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asistenciapersonal.class));
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

    public Asistenciapersonal findAsistenciapersonal(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asistenciapersonal.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsistenciapersonalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asistenciapersonal> rt = cq.from(Asistenciapersonal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Asistenciapersonal> findAsistenciapersonalpordia(Date fecha) {
                 EntityManager em = getEntityManager();
         List<Asistenciapersonal> lstasistencia= null;
        try {
             Query q=em.createNamedQuery("Asistenciapersonal.filtrofecha");
             q.setParameter("fecha", fecha);
             return (lstasistencia)= q.getResultList();
        }
        finally {
            em.close();
        }
    }
      public List<Asistenciapersonal> findAsistenciapersonalpor_aula(Date fecha , int idaulaperiodo) {
                 EntityManager em = getEntityManager();
         List<Asistenciapersonal> lstasistencia= null;
        try {
             Query q=em.createNamedQuery("Asistenciapersonal.filtroaula");
             q.setParameter("fecha", fecha);
             q.setParameter("idaulaperiodo", idaulaperiodo);
             return (lstasistencia)= q.getResultList();
        }
        finally {
            em.close();
        }
    }
          public List<Asistenciapersonal> findAsistenciapersonalpor_docente(Date fecha , int idusuario) {
                 EntityManager em = getEntityManager();
         List<Asistenciapersonal> lstasistencia= null;
        try {
             Query q=em.createNamedQuery("Asistenciapersonal.filtrodocente");
             q.setParameter("fecha", fecha);
             q.setParameter("idusuario", idusuario);
             return (lstasistencia)= q.getResultList();
        }
        finally {
            em.close();
        }
    }
                    public List<Asistenciapersonal> findAsistenciapersonalpor_rangohora (Date fecha , Date horainicial, Date horafinal) {
                 EntityManager em = getEntityManager();
         List<Asistenciapersonal> lstasistencia= null;
        try {
             Query q=em.createNamedQuery("Asistenciapersonal.filtrorangohora");
             q.setParameter("fecha", fecha);
             q.setParameter("horainicial", horainicial);
             q.setParameter("horafinal", horafinal);
             return (lstasistencia)= q.getResultList();
        }
        finally {
            em.close();
        }
    }
 public List<Asistenciapersonal> findAsistenciapersonalpor_horamayores (Date fecha , Date horainicial) {
                 EntityManager em = getEntityManager();
         List<Asistenciapersonal> lstasistencia= null;
        try {
             Query q=em.createNamedQuery("Asistenciapersonal.filtrohoramayores");
             q.setParameter("fecha", fecha);
             q.setParameter("horainicial", horainicial);
             return (lstasistencia)= q.getResultList();
        }
        finally {
            em.close();
        }
    }
}
