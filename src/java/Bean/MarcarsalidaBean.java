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
public class MarcarsalidaBean {

    /**
     * Creates a new instance of MarcarsalidaBean
     */
    public MarcarsalidaBean() {
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
    /**
     * Creates a new instance of AsistenciadocBean
     */
   
    public void registrarsalida()
    {
        
        
        
        em.getTransaction().begin();
        StoredProcedureQuery storedProcedure=em.createStoredProcedureQuery("SPmarcarsalida");
        
        storedProcedure.registerStoredProcedureParameter("dni", String.class, ParameterMode.IN);
        storedProcedure.setParameter("dni", dni);
        
        storedProcedure.registerStoredProcedureParameter("idusuario",int.class, ParameterMode.OUT);
        
        storedProcedure.registerStoredProcedureParameter("idasistencia",int.class, ParameterMode.OUT);
        storedProcedure.registerStoredProcedureParameter("salida",String.class, ParameterMode.OUT);
        storedProcedure.registerStoredProcedureParameter("horaentrada",String.class, ParameterMode.OUT);
        
        
        
        storedProcedure.execute();
        
        String salida=(String)storedProcedure.getOutputParameterValue("salida");
        
        dni="";
        System.out.println("salida:"+salida);
        if (salida.equals("Lo sentimos no se ah encontrado su horario de trabajo"))
                {
                    FacesMessages.error(salida);
                }
        else
        {
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
