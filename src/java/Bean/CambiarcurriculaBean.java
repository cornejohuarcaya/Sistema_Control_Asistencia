/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Clase.Asistenciapersonal;
import Clase.Aulaperiodo;
import Clase.Usuario;
import Dao.AsistenciapersonalJpaController;
import Dao.AulaperiodoJpaController;
import Dao.UsuarioJpaController;
import java.util.Date;
import java.util.Iterator;
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
public class CambiarcurriculaBean {

    private String curricula;

    public String getCurricula() {
        return curricula;
    }

    public void setCurricula(String curricula) {
        this.curricula = curricula;
    }
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");

    private Date horainicial;

    public Date getHorainicial() {
        return horainicial;
    }

    public void setHorainicial(Date horainicial) {
        this.horainicial = horainicial;
    }

    public Date getHorafinal() {
        return horafinal;
    }

    public void setHorafinal(Date horafinal) {
        this.horafinal = horafinal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Aulaperiodo getAulaperiodo() {
        return aulaperiodo;
    }

    public void setAulaperiodo(Aulaperiodo aulaperiodo) {
        this.aulaperiodo = aulaperiodo;
    }

    public List<Usuario> getLstusuario() {
        return lstusuario;
    }

    public void setLstusuario(List<Usuario> lstusuario) {
        this.lstusuario = lstusuario;
    }

    public List<Aulaperiodo> getLstaulaperiodo() {
        return lstaulaperiodo;
    }

    public void setLstaulaperiodo(List<Aulaperiodo> lstaulaperiodo) {
        this.lstaulaperiodo = lstaulaperiodo;
    }
    private Date horafinal;
    private Usuario usuario = new Usuario();
    private Aulaperiodo aulaperiodo= new Aulaperiodo();
    private List<Usuario> lstusuario=null;
    private List<Aulaperiodo> lstaulaperiodo=null;
    
    AsistenciapersonalJpaController controlador =new AsistenciapersonalJpaController(emf);
    UsuarioJpaController contusuario=new UsuarioJpaController(emf);
    AulaperiodoJpaController contaulaperiodo= new AulaperiodoJpaController(emf);
    private static List<Asistenciapersonal> listaasistencia=null;

    public List<Asistenciapersonal> getListaasistencia() {
        return listaasistencia;
    }

    public void setListaasistencia(List<Asistenciapersonal> listaasistencia) {
        this.listaasistencia = listaasistencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    private Date fecha;
    
    public void filtrarfecha()
    {
        listaasistencia=controlador.findAsistenciapersonalpordia(fecha);
    }
     public void filtrarfechahora()
    {
        listaasistencia=controlador.findAsistenciapersonalpor_horamayores(fecha, horainicial );
    }
        public void filtrarrangohora()
    {
        listaasistencia=controlador.findAsistenciapersonalpor_rangohora(fecha, horainicial, horafinal);
    }
             public void filtrardocente()
    {
        listaasistencia=controlador.findAsistenciapersonalpor_docente(fecha, Integer.valueOf(usuario.getId().toString()));
    }
                      public void filtraraulaperiodo()
    {
        listaasistencia=controlador.findAsistenciapersonalpor_aula(fecha, Integer.valueOf(aulaperiodo.getId_AulaPeriodo().toString()));
    }
    public CambiarcurriculaBean() {
    }
    public void actualizarcurricula()
    {try {
        for (Iterator<Asistenciapersonal> iterator = listaasistencia.iterator(); iterator.hasNext();) {
            
                Asistenciapersonal asis = iterator.next();
                String curriculaantigua= asis.getCurricula();
                asis.setCurricula(curricula+ ", "+ curriculaantigua);
                controlador.edit(asis);
            
        }
                FacesMessages.info("Curricula Registrada Correctamente");
        } catch (Exception ex) {
            FacesMessages.error("Se ha producido un error");
                Logger.getLogger(CambiarcurriculaBean.class.getName()).log(Level.SEVERE, null, ex);
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
