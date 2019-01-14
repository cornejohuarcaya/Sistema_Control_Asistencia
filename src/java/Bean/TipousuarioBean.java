/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Clase.Tipousuario;
import Dao.TipousuarioJpaController;
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
public class TipousuarioBean {

    private Tipousuario tipousuario = new Tipousuario();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");

    TipousuarioJpaController controlador = new TipousuarioJpaController(emf);
    private List<Tipousuario> lista = null;

    public List<Tipousuario> getLista() {
        lista=controlador.findTipousuarioEntities();
        return lista;
    }

    public void setLista(List<Tipousuario> lista) {
        this.lista = lista;
    }

    public Tipousuario getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(Tipousuario tipousuario) {
        this.tipousuario = tipousuario;
    }

    /**
     * Creates a new instance of Tipousuario
     */
    public TipousuarioBean() {
    }

    public void registraractualziar() {
        try {
            if (tipousuario.getId()==null)
            {
                controlador.create(tipousuario);
                tipousuario=new Tipousuario();
                FacesMessages.info("Registrado Correctamente");
            }
            else if (tipousuario.getId() > 0) {
                controlador.edit(tipousuario);
                tipousuario=new Tipousuario();
                FacesMessages.info("Actualziado Correctamente");
            }
        } catch (Exception e) {
            FacesMessages.error("No se ah podido confirmar los cambios" + e.getMessage());
        }
    }

    public void seleccionar(Tipousuario tipousu) {
        tipousuario=new Tipousuario();
        tipousuario=tipousu;
    }
    public void cancelar()
    {
        tipousuario=new Tipousuario();
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
