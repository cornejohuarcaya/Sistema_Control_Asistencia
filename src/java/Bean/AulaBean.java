/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Clase.Aula;
import Dao.AulaJpaController;

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
public class AulaBean {

    /**
     * Creates a new instance of AulaBean
     */
    public AulaBean() {
    }
    private Aula aula = new Aula();

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

   
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");

    AulaJpaController controlador = new AulaJpaController(emf);

   
    private List<Aula> lista = null;

    public List<Aula> getLista() {
        lista=controlador.findAulaEntities();
        return lista;
    }

    


    public void registraractualziar() {
        try {
            if (aula.getId_aula()== null)
            {
                controlador.create(aula);
                aula=new Aula();
                FacesMessages.info("Registrado Correctamente");
            }
            else if (aula.getId_aula()> 0) {
                controlador.edit(aula);
                aula=new Aula();
                FacesMessages.info("Actualziado Correctamente");
            }
        } catch (Exception e) {
            FacesMessages.error("No se ah podido confirmar los cambios" + e.getMessage());
        }
    }

    public void seleccionar(Aula tipoac) {
        aula=new Aula();
        aula=tipoac;
    }
    public void cancelar()
    {
        aula=new Aula();
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
