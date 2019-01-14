/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Clase.Aula;
import Clase.Aulaperiodo;
import Clase.Periodoestudio;
import Dao.AulaJpaController;
import Dao.AulaperiodoJpaController;
import Dao.PeriodoestudioJpaController;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.bootsfaces.utils.FacesMessages;

/**
 *
 * @author Erick
 */
@ManagedBean
@RequestScoped
public class AulaperiodoBean{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");
    
    private List<Aula> lstaula = null;
    private List<Periodoestudio> lstperiodoestudio = null;
    private List<Aulaperiodo> lista = null;
    
    AulaperiodoJpaController controlador = new AulaperiodoJpaController(emf);
    AulaJpaController contaula = new AulaJpaController(emf);
    PeriodoestudioJpaController contperiodoestudio = new PeriodoestudioJpaController(emf);
private Aulaperiodo aulaperiodoest = new Aulaperiodo();
    public List<Aula> getLstaula() {
        lstaula = contaula.findAulaEntities();
        return lstaula;
    }

    public void setLstaula(List<Aula> lstaula) {
        this.lstaula = lstaula;
    }

    public List<Periodoestudio> getLstperiodoestudio() {
        lstperiodoestudio = contperiodoestudio.findPeriodoestudioEntities();
        return lstperiodoestudio;
    }

    public void setLstperiodoestudio(List<Periodoestudio> lstperiodoestudio) {
        this.lstperiodoestudio = lstperiodoestudio;
    }

    /**
     * Creates a new instance of AulaperiodoBean
     */
    public AulaperiodoBean() {
    }

    public Aulaperiodo getAulaperiodoest() {
        return aulaperiodoest;
    }

    public void setAulaperiodoest(Aulaperiodo aulaperiodoest) {
        this.aulaperiodoest = aulaperiodoest;
    }

    

    

    

    public List<Aulaperiodo> getLista() {
        lista = controlador.findAulaperiodoEntities();
        return lista;
    }

    public void registraractualziar() {
        try {
            if (aulaperiodoest.getId_AulaPeriodo() == null) {
                controlador.create(aulaperiodoest);
                aulaperiodoest = new Aulaperiodo();
                FacesMessages.info("Registrado Correctamente");
            } else if (aulaperiodoest.getId_AulaPeriodo() > 0) {
                controlador.edit(aulaperiodoest);
                aulaperiodoest = new Aulaperiodo();
                FacesMessages.info("Actualizado Correctamente");
            }
        } catch (Exception e) {
            FacesMessages.error("No se ah podido confirmar los cambios" + e.getMessage());
        }
    }

    public void seleccionar(Aulaperiodo tipoac) {
        System.out.println("seleccionado " + tipoac.getId_AulaPeriodo() + tipoac.getId_Aula().getAnio() + tipoac.getId_Aula().getSeccion());
        System.out.println("seleccionado " + tipoac.getId_PeriodoEstudio().getAnio());
        aulaperiodoest = tipoac;
    }

    public void cancelar() {
        aulaperiodoest = new Aulaperiodo();
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
