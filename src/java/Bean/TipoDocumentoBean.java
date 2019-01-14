/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Clase.TipoDocumento;

import Dao.TipoDocumentoJpaController;
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
public class TipoDocumentoBean {

    /**
     * Creates a new instance of TipoDocumentoBean
     */
    public TipoDocumentoBean() {
    }
     private TipoDocumento tipodocumento = new TipoDocumento();

   
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");

    TipoDocumentoJpaController controlador = new TipoDocumentoJpaController(emf);

    public TipoDocumento getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(TipoDocumento tipodocumento) {
        this.tipodocumento = tipodocumento;
    }
    private List<TipoDocumento> lista = null;

    public List<TipoDocumento> getLista() {
        lista=controlador.findTipoDocumentoEntities();
        return lista;
    }

    


    public void registraractualziar() {
        try {
            if (tipodocumento.getId()== null)
            {
                controlador.create(tipodocumento);
                tipodocumento=new TipoDocumento();
                FacesMessages.info("Registrado Correctamente");
            }
            else if (tipodocumento.getId()> 0) {
                controlador.edit(tipodocumento);
                tipodocumento=new TipoDocumento();
                FacesMessages.info("Actualziado Correctamente");
            }
        } catch (Exception e) {
            FacesMessages.error("No se ah podido confirmar los cambios" + e.getMessage());
        }
    }

    public void seleccionar(TipoDocumento tipoac) {
        tipodocumento=new TipoDocumento();
        tipodocumento=tipoac;
    }
    public void cancelar()
    {
        tipodocumento=new TipoDocumento();
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
