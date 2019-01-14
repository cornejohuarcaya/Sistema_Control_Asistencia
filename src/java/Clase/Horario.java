/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clase;
import Clase.Aulaperiodo;
import Clase.Usuario;
import Clase.Tipoactividad;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Erick
 */
@Table(name = "tbhorario")
@Entity
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_horario")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JoinColumn(name = "id_TipoActividad", referencedColumnName = "id_TipoActividad")
    @ManyToOne
    private Tipoactividad id_TipoActividad;

    @JoinColumn(name = "id_Usuario", referencedColumnName = "id_Usuario")
    @ManyToOne
    private Usuario id_Usuario;

    
    @ManyToOne
    @JoinColumn(name = "id_Aulaperiodo")
    private Aulaperiodo id_AulaPeriodo;

    @Column(name = "dia")
    private String dia;
    @Temporal(TemporalType.TIME)
    private Date horaEntrada;
    @Temporal(TemporalType.TIME)
    private Date HoraSalida;
    @Column(name = "activo")
    private boolean activo;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public Aulaperiodo getId_AulaPeriodo() {
        return id_AulaPeriodo;
    }

    public void setId_AulaPeriodo(Aulaperiodo aupe) {
        this.id_AulaPeriodo = aupe;
    }
    

    public Tipoactividad getId_TipoActividad() {
        return id_TipoActividad;
    }

    public void setId_TipoActividad(Tipoactividad id_TipoActividad) {
        this.id_TipoActividad = id_TipoActividad;
    }

    public Usuario getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(Usuario id_Usuario) {
        this.id_Usuario = id_Usuario;
    }



    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getHoraSalida() {
        return HoraSalida;
    }

    public void setHoraSalida(Date HoraSalida) {
        this.HoraSalida = HoraSalida;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clase.Horario[ id=" + id + " ]";
    }
    
}
