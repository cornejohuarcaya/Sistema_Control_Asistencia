/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Clase.Usuario;
import Dao.UsuarioJpaController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Erick
 */
@ManagedBean
@RequestScoped
public class UsuarioBean {

    private List<Usuario> listausuario;
private Usuario usuario=null;
    public List<Usuario> getListausuario() {
       listausuario=controlador.findUsuarioEntities();
        return listausuario;
    }

    public void setListausuario(List<Usuario> listausuario) {
        this.listausuario = listausuario;
    }
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");
    private UsuarioJpaController controlador=new UsuarioJpaController(emf);
    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }
    public void registrar()
    {
        controlador.create(usuario);
        usuario=new Usuario();
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
