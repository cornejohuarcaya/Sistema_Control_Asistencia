/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Clase.Tipoactividad;

import Dao.TipoactividadJpaController;
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
public class TipoactividadBean {

    /**
     * Creates a new instance of TipoactividadBean
     */
    public TipoactividadBean() {
    }
    private Tipoactividad tipoactividad = new Tipoactividad();

    public Tipoactividad getTipoactividad() {
        return tipoactividad;
    }

    public void setTipoactividad(Tipoactividad tipoactividad) {
        this.tipoactividad = tipoactividad;
    }
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");

    TipoactividadJpaController controlador = new TipoactividadJpaController(emf);
    private List<Tipoactividad> lista = null;

    public List<Tipoactividad> getLista() {
        lista=controlador.findTipoactividadEntities();
        return lista;
    }

    


    public void registraractualziar() {
        try {
            if (tipoactividad.getId_tipoactividad() == null)
            {
                controlador.create(tipoactividad);
                tipoactividad=new Tipoactividad();
                FacesMessages.info("Registrado Correctamente");
            }
            else if (tipoactividad.getId_tipoactividad()> 0) {
                controlador.edit(tipoactividad);
                tipoactividad=new Tipoactividad();
                FacesMessages.info("Actualziado Correctamente");
            }
        } catch (Exception e) {
            FacesMessages.error("No se ah podido confirmar los cambios" + e.getMessage());
        }
    }

    public void seleccionar(Tipoactividad tipoac) {
        tipoactividad=new Tipoactividad();
        tipoactividad=tipoac;
    }
    public void cancelar()
    {
        tipoactividad=new Tipoactividad();
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
