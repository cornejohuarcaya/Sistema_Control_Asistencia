/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Clase.Usuario;
import Clase.Tipousuario;
import Clase.TipoDocumento;
import Dao.TipoDocumentoJpaController;
import Dao.TipousuarioJpaController;

import Dao.UsuarioJpaController;
import java.util.ArrayList;
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
public class PersonalBean {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");

    UsuarioJpaController controlador = new UsuarioJpaController(emf);
    List<Tipousuario> lsttipousuario=null;
    TipousuarioJpaController conttipousu = new TipousuarioJpaController(emf);
    TipoDocumentoJpaController conttipodoc= new TipoDocumentoJpaController(emf);
    public List<Tipousuario> getLsttipousuario() {
        
        lsttipousuario=conttipousu.findTipousuarioEntities();
        return lsttipousuario;
    }

    public List<TipoDocumento> getLsttipodocumento() {
        
        lsttipodocumento=conttipodoc.findTipoDocumentoEntities();
        
        return lsttipodocumento;
    }
    List<TipoDocumento> lsttipodocumento=null;
    public boolean activo=true;

    public boolean isActivo() {
        return activo;
    }

    public boolean isInactivo() {
        return inactivo;
    }
    public boolean inactivo=false;
    
    /**
     * Creates a new instance of PersonalBean
     */
    public PersonalBean() {
        lsttipousuario= new ArrayList<>();
        lsttipodocumento= new ArrayList<>();
    }
     private Usuario personal = new Usuario();

    public Usuario getPersonal() {
        return personal;
    }

    public void setPersonal(Usuario personal) {
        this.personal = personal;
    }



   
   

    
    private List<Usuario> lista = null;

    public List<Usuario> getLista() {
        lista=controlador.findUsuarioEntities();
        return lista;
    }

    


    public void registraractualziar() {
        try {
            if (personal.getId()== null)
            {
                controlador.create(personal);
                personal=new Usuario();
                FacesMessages.info("Registrado Correctamente");
            }
            else if (personal.getId()> 0) {
                controlador.edit(personal);
                personal=new Usuario();
                FacesMessages.info("Actualziado Correctamente");
            }
        } catch (Exception e) {
            FacesMessages.error("No se ah podido confirmar los cambios" + e.getMessage());
        }
    }

    public void seleccionar(Usuario usu) {
        personal=usu;
    }
    public void cancelar()
    {
        personal=new Usuario();
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

