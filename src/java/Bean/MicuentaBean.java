/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Clase.Usuario;
import Dao.UsuarioJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;
import net.bootsfaces.utils.FacesMessages;

/**
 *
 * @author Erick
 */
@ManagedBean
@RequestScoped
public class MicuentaBean {

    private Usuario usuario= new Usuario();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");
    UsuarioJpaController controlador= new UsuarioJpaController(emf);
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    /**
     * Creates a new instance of MicuentaBean
     */
    public MicuentaBean() {
        HttpSession session= util.Util.getSession();
        usuario=(Usuario)session.getAttribute("usuario");
    }
    public void actualizar() 
    {
        try {
            controlador.edit(usuario);
            FacesMessages.info("Cambios Registrados");
        } catch (Exception ex) {
            FacesMessages.error("Lo sentimos las modificacions no se pudieron guardar");
            Logger.getLogger(MicuentaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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
