/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Clase.Aulaperiodo;
import Clase.Estudiante;
import Clase.Estudianteaulaperiodo;
import Dao.AulaperiodoJpaController;
import Dao.EstudianteJpaController;
import Dao.EstudianteaulaperiodoJpaController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
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
@ApplicationScoped
public class MatriculaBean {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("APP_asistenciaJAPPU");

    Aulaperiodo aulaperiodo = new Aulaperiodo();

    public Aulaperiodo getAulaperiodo() {
        return aulaperiodo;
    }

    public void setAulaperiodo(Aulaperiodo aulaperiodo) {
        this.aulaperiodo = aulaperiodo;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    Estudiante estudiante= new Estudiante();
    EstudianteaulaperiodoJpaController  controlador= new EstudianteaulaperiodoJpaController(emf);
    AulaperiodoJpaController contaulaperiodo = new AulaperiodoJpaController(emf);
    EstudianteJpaController contestudiante=new EstudianteJpaController(emf);
    private boolean activo=true;
    private boolean inactivo=false;
    
    private Estudianteaulaperiodo matricula= new  Estudianteaulaperiodo();
    private  List<Estudianteaulaperiodo> list= null;
    private List<Aulaperiodo> listaulaperiodo= null;
    private List<Estudiante> listestudiante = null;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isInactivo() {
        return inactivo;
    }

    public void setInactivo(boolean inactivo) {
        this.inactivo = inactivo;
    }

    public Estudianteaulaperiodo getMatricula() {
        return matricula;
    }

    public void setMatricula(Estudianteaulaperiodo matricula) {
        this.matricula = matricula;
    }

    public List<Estudianteaulaperiodo> getList() {
        return list;
    }

    public void setList(List<Estudianteaulaperiodo> list) {
        this.list = list;
    }

    public List<Aulaperiodo> getListaulaperiodo() {
        listaulaperiodo=contaulaperiodo.findAulaperiodoEntities();
        return listaulaperiodo;
    }
    public void listar ()
    {
        list=controlador.findEstudianteaulaperiodoEntities();
    }

    public void setListaulaperiodo(List<Aulaperiodo> listaulaperiodo) {
        this.listaulaperiodo = listaulaperiodo;
    }

    public List<Estudiante> getListestudiante() {
    listestudiante=contestudiante.findEstudianteEntities();
        return listestudiante;
    }

    public void setListestudiante(List<Estudiante> listestudiante) {
        this.listestudiante = listestudiante;
    }
    
    public void seleccionarestudiante(Estudiante estudiante)
    {this.estudiante=estudiante;
        System.out.println("PERIODO" + matricula.getId_AulaPeriodo());
        System.out.println("ESTUDIANTE" + estudiante);
       
        matricula.setId_Estudiante(this.estudiante);
         System.out.println("PERIODO" + matricula.getId_AulaPeriodo());
        System.out.println("ESTUDIANTE" + estudiante);
        
    }
    public  void seleccionaraulaperiodo(Aulaperiodo aulaperiodo)
    {
        this.aulaperiodo=aulaperiodo;
        System.out.println("PERIODO" + aulaperiodo);
        System.out.println("ESTUDIANTE" + matricula.getId_Estudiante());

        matricula.setId_AulaPeriodo(this.aulaperiodo);
        System.out.println("PERIODO" + aulaperiodo);
        System.out.println("ESTUDIANTE" + matricula.getId_Estudiante());
        
        
    }
    public void cancelar()
    {
        matricula = new Estudianteaulaperiodo();
    }
    /**
     * Creates a new instance of MatriculaBean
     */
    public MatriculaBean() {
    }
    public void seleccionar(Estudianteaulaperiodo esaulaper)
    {
        matricula=esaulaper;
    }
    public void registraractualziar() {
        try {
            if (matricula.getId()== null)
            {
                controlador.create(matricula);
                matricula=new Estudianteaulaperiodo();
                FacesMessages.info("Registrado Correctamente");
            }
            else if (matricula.getId()> 0) {
                controlador.edit(matricula);
                matricula=new Estudianteaulaperiodo();
                FacesMessages.info("Actualizado Correctamente");
            }
        } catch (Exception e) {
            FacesMessages.error("No se ah podido confirmar los cambios" + e.getMessage());
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
