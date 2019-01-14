/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;


import Clase.Tipoasistencia;
import Dao.TipoactividadJpaController;
import Dao.TipoasistenciaJpaController;
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
public class TipoasistenciaBean {

    /**
     * Creates a new instance of TipoasistenciaBean
     */
    public TipoasistenciaBean() {
    }
    
    private Tipoasistencia tipoasistencia = new Tipoasistencia();

    public Tipoasistencia getTipoasistencia() {
        return tipoasistencia;
    }

    public void setTipoasistencia(Tipoasistencia tipoasistencia) {
        this.tipoasistencia = tipoasistencia;
    }

   
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");

    TipoasistenciaJpaController controlador = new TipoasistenciaJpaController(emf);
    private List<Tipoasistencia> lista = null;

    public List<Tipoasistencia> getLista() {
        lista=controlador.findTipoasistenciaEntities();
        return lista;
    }

    


    public void registraractualziar() {
        try {
            if (tipoasistencia.getId_tipoasistencia()== null)
            {
                controlador.create(tipoasistencia);
                tipoasistencia=new Tipoasistencia();
                FacesMessages.info("Registrado Correctamente");
            }
            else if (tipoasistencia.getId_tipoasistencia()> 0) {
                controlador.edit(tipoasistencia);
                tipoasistencia=new Tipoasistencia();
                FacesMessages.info("Actualziado Correctamente");
            }
        } catch (Exception e) {
            FacesMessages.error("No se ah podido confirmar los cambios" + e.getMessage());
        }
    }

    public void seleccionar(Tipoasistencia tipoac) {
        tipoasistencia=new Tipoasistencia();
        tipoasistencia=tipoac;
    }
    public void cancelar()
    {
        tipoasistencia=new Tipoasistencia();
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
