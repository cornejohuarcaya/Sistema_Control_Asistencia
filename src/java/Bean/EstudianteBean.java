/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Clase.Estudiante;
import Dao.EstudianteJpaController;
import java.io.Serializable;


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
public class EstudianteBean implements Serializable{
EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");
    EstudianteJpaController controlador = new EstudianteJpaController(emf);
    private List<Estudiante> lista = null;

private Estudiante estudiante=new Estudiante();
    private boolean verdadero=true;

    public boolean isVerdadero() {
        return verdadero;
    }

    public boolean isFalso() {
        return falso;
    }
    private boolean falso=false;
    /**
     * Creates a new instance of EstudianteBean
     */
    public EstudianteBean() {
    }
    

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
   
    

    
    public List<Estudiante> getLista() {
        lista=controlador.findEstudianteEntities();
        return lista;
    }

    public void registraractualziar() {
        try {
            if (estudiante.getId()== null)
            {
                controlador.create(estudiante);
                estudiante=new Estudiante();
                FacesMessages.info("Registrado Correctamente");
            }
            else if (estudiante.getId()> 0) {
                System.out.println("Actualizandooo");
                controlador.edit(estudiante);
                estudiante=new Estudiante();
                FacesMessages.info("Actualziado Correctamente");
            }
        } catch (Exception e) {
            FacesMessages.error("No se ah podido confirmar los cambios" + e.getMessage());
        }
    }

    public void seleccionar(Estudiante tipoac) {
        estudiante=new Estudiante();
        estudiante=tipoac;
    }
    public void cancelar()
    {
        estudiante=new Estudiante();
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
