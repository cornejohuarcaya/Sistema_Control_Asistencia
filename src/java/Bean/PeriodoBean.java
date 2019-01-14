/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Clase.Periodoestudio;
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
public class PeriodoBean {

    private boolean activo=true;

    public boolean isActivo() {
        return activo;
    }

    public boolean isInactivo() {
        return inactivo;
    }
    private boolean inactivo=false;
    /**
     * Creates a new instance of PeriodoBean
     */
    public PeriodoBean() {
    }
     private Periodoestudio periodoestudio = new Periodoestudio();

    public Periodoestudio getPeriodoestudio() {
        return periodoestudio;
    }

    public void setPeriodoestudio(Periodoestudio periodoestudio) {
        this.periodoestudio = periodoestudio;
    }

   
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");

    PeriodoestudioJpaController controlador = new PeriodoestudioJpaController(emf);

    
    private List<Periodoestudio> lista = null;

    public List<Periodoestudio> getLista() {
        lista=controlador.findPeriodoestudioEntities();
        return lista;
    }

    


    public void registraractualziar() {
        try {
            if (periodoestudio.getId_periodoestudio()== null)
            {
                controlador.create(periodoestudio);
                periodoestudio=new Periodoestudio();
                FacesMessages.info("Registrado Correctamente");
            }
            else if (periodoestudio.getId_periodoestudio()> 0) {
                controlador.edit(periodoestudio);
                periodoestudio=new Periodoestudio();
                FacesMessages.info("Actualziado Correctamente");
            }
        } catch (Exception e) {
            FacesMessages.error("No se ah podido confirmar los cambios" + e.getMessage());
        }
    }

    public void seleccionar(Periodoestudio tipoac) {
        periodoestudio=new Periodoestudio();
        periodoestudio=tipoac;
    }
    public void cancelar()
    {
        periodoestudio=new Periodoestudio();
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
