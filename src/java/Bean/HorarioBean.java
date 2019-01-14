/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Clase.Aulaperiodo;
import Clase.Horario;

import Clase.Tipoactividad;

import Clase.Usuario;
import Dao.AulaperiodoJpaController;
import Dao.HorarioJpaController;

import Dao.TipoactividadJpaController;

import Dao.UsuarioJpaController;
import java.io.Serializable;
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
public class HorarioBean implements Serializable{

    /**
     * Creates a new instance of HorarioBean
     */
    public HorarioBean() {
    }
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");
   private Horario horario = new Horario();
    HorarioJpaController controlador = new HorarioJpaController(emf);
    List<Usuario> lstusuario=null;
    List<Tipoactividad> lsttipoactividad=null;
   List<Aulaperiodo> lstaulaper=null;

    public List<Aulaperiodo> getLstaulaper() {
        lstaulaper=contaulapaeariodo.findAulaperiodoEntities();
        return lstaulaper;
    }

    public void setLstaulaper(List<Aulaperiodo> lstaulaper) {
        this.lstaulaper = lstaulaper;
    }


    TipoactividadJpaController conttipoactividad = new TipoactividadJpaController(emf);
    UsuarioJpaController contusuario= new UsuarioJpaController(emf);
    AulaperiodoJpaController contaulapaeariodo= new AulaperiodoJpaController(emf);

    public List<Usuario> getLstusuario() {
        lstusuario=contusuario.findUsuarioEntities();
        return lstusuario;
    }

    public void setLstusuario(List<Usuario> lstusuario) {
        this.lstusuario = lstusuario;
    }

    public List<Tipoactividad> getLsttipoactividad() {
        lsttipoactividad=conttipoactividad.findTipoactividadEntities();
        return lsttipoactividad;
    }

    public void setLsttipoactividad(List<Tipoactividad> lsttipoactividad) {
        this.lsttipoactividad = lsttipoactividad;
    }

    
    
    
    public boolean activo=true;

    public boolean isActivo() {
        return activo;
    }

    public boolean isInactivo() {
        return inactivo;
    }
    public boolean inactivo=false;
    

  

   



   
   

    
    private List<Horario> lista = null;

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public List<Horario> getLista() {
        lista=controlador.findHorarioEntities();
        return lista;
    }

    


    public void registraractualziar() {
        try {
            if (horario.getId()== null)
            {
                controlador.create(horario);
                
                horario=new Horario();
                FacesMessages.info("Registrado Correctamente");
            }
            else if (horario.getId()> 0) {
                controlador.edit(horario);
                horario=new Horario();
                FacesMessages.info("Actualziado Correctamente");
            }
        } catch (Exception e) {
            FacesMessages.error("No se ah podido confirmar los cambios" + e.getMessage());
        }
    }

    public void seleccionar(Horario horar) {
        System.out.println("seleccionado"+ horar.getId_AulaPeriodo().toString());
        
       horario=horar;
       System.out.println("horario "+ horario.getId_AulaPeriodo().toString());
        
    }
    public void cancelar()
    {
        horario=new Horario();
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
