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
public class Loguear {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");
    UsuarioJpaController controlador = new UsuarioJpaController(emf);

    private Usuario objusuario = new Usuario();

    public Usuario getObjusuario() {
        return objusuario;
    }

    public void setObjusuario(Usuario objusuario) {
        this.objusuario = objusuario;
    }
    private String usuario = "";

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private String password = "";

    /**
     * Creates a new instance of Loguear
     */
    public Loguear() {
    }

    public String loguear() {
        String salida="";
        try {
            objusuario = controlador.logueando(usuario, password);

            if (objusuario.getId() > 0) {
                HttpSession session = util.Util.getSession();
                session.setAttribute("usuario", objusuario);
                System.out.println("Logueado correactamente, Bienvenido " + objusuario.getApellidos());
                salida="bienvenido";
            } else {
                usuario="";
                password="";
                FacesMessages.error("Error de Credenciales");
                System.out.println("Error de Credenciales");
                salida="";
            }
            return salida;
        } catch (Exception e) {
            usuario="";
                password="";
                FacesMessages.error("Error de Credenciales");
                System.out.println("Error de Credenciales");
                salida="";
            return salida;
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
