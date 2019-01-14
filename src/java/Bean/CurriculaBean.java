/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Clase.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpSession;
import net.bootsfaces.utils.FacesMessages;

/**
 *
 * @author Erick
 */
@ManagedBean
@RequestScoped
public class CurriculaBean {

    /**
     * Creates a new instance of CurriculaBean
     */
    private String dni;
    public CurriculaBean() {
        HttpSession session = util.Util.getSession();
        Usuario usuario= new Usuario();
        usuario= (Usuario) session.getAttribute("usuario");
        dni=usuario.getNumeroDoc();
        
    }
     private String curricula ;

    public String getCurricula() {
        return curricula;
    }

    public void setCurricula(String curricula) {
        this.curricula = curricula;
    }
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");
EntityManager em = emf.createEntityManager();
    public String getDni() {
        return curricula;
    }

    public void setDni(String dni) {
        this.curricula = dni;
    }
 
    public void registrar()
    {
        
        System.out.println("1");
        em.getTransaction().begin();
        StoredProcedureQuery storedProcedure=em.createStoredProcedureQuery("SPregistrarcurricula");
        storedProcedure.registerStoredProcedureParameter("dni", String.class, ParameterMode.IN);
        storedProcedure.setParameter("dni", dni);
        storedProcedure.registerStoredProcedureParameter("curricula", String.class, ParameterMode.IN);
        storedProcedure.setParameter("curricula", curricula);
        
        storedProcedure.registerStoredProcedureParameter("idusuario",int.class, ParameterMode.OUT);
        
        storedProcedure.registerStoredProcedureParameter("idasistencia",int.class, ParameterMode.OUT);
        storedProcedure.registerStoredProcedureParameter("salida",String.class, ParameterMode.OUT);
        storedProcedure.registerStoredProcedureParameter("horaentrada",String.class, ParameterMode.OUT);
        storedProcedure.registerStoredProcedureParameter("horasalida",String.class, ParameterMode.OUT);
        
        
        System.out.println("2");
        storedProcedure.execute();
        System.out.println("3");
        String salida="";
        int idasistencia=0;
        int idusuario=0;
        String horasalida="";
        
        salida=(String)storedProcedure.getOutputParameterValue("salida");
        idasistencia=(int)storedProcedure.getOutputParameterValue("idasistencia");
        idusuario=(int)storedProcedure.getOutputParameterValue("idusuario");
//        horasalida= storedProcedure.getOutputParameterValue("horasalida").toString();
        
        System.out.println("dni: " + dni  + " curricula: " + curricula);
        
        System.out.println("4");
        System.out.println("salida:"+salida +"         idasistencia: " + idasistencia+"         idusuario: " + idusuario +  "  horasalida." + horasalida  );
        
        System.out.println("5");
        
        if (salida.equals("Temas registrados correctamente"))
                {
                    FacesMessages.info("Temas registrados correctamente");
                }
        else
        {
            FacesMessages.error("" + salida);
        }
        
        System.out.println("6");
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
