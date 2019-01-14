/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import net.bootsfaces.utils.FacesMessages;

/**
 *
 * @author Erick
 */
@ManagedBean
@RequestScoped
public class AsistenciaestudianteBean {

    /**
     * Creates a new instance of AsistenciaestudianteBean
     */
    public AsistenciaestudianteBean() {
    }
     private String dni ;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");
EntityManager em = emf.createEntityManager();
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    
    public void registrarasistencia()
    {
        
        
        
        em.getTransaction().begin();
        StoredProcedureQuery storedProcedure=em.createStoredProcedureQuery("SPmarcarasistenciaestudiantes");
        
        storedProcedure.registerStoredProcedureParameter("dni", String.class, ParameterMode.IN);
        storedProcedure.setParameter("dni", dni);
        
        storedProcedure.registerStoredProcedureParameter("idestudianteaulaperiodo",int.class, ParameterMode.OUT);
        
        storedProcedure.registerStoredProcedureParameter("idasistencia",int.class, ParameterMode.OUT);
        storedProcedure.registerStoredProcedureParameter("salida",String.class, ParameterMode.OUT);
      
        
        
        storedProcedure.execute();
        
        String salida=(String)storedProcedure.getOutputParameterValue("salida");
        
        
        System.out.println("salida:"+salida);
        if (salida.equals("Lo sentimos no se pudo registrar su asistencia"))
                {
                    dni="";
                    FacesMessages.error(salida);
                }
        else
        {
            dni="";
            FacesMessages.info(salida);
        }
        
        em.getTransaction().commit();
        em.close();
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
