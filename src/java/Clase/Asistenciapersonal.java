/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clase;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Erick
 */
@Table(name = "TbAsistenciapersonal")
@Entity
@NamedQueries(
        {@NamedQuery(name = "Asistenciapersonal.filtrofecha", query = "SELECT u FROM Asistenciapersonal u WHERE u.fecha = :fecha"),
        @NamedQuery(name = "Asistenciapersonal.filtrohoramayores", query = "SELECT u FROM Asistenciapersonal u WHERE u.fecha = :fecha AND u.id_horario.horaEntrada  >= :horainicial "),
        @NamedQuery(name = "Asistenciapersonal.filtrorangohora", query = "SELECT u FROM Asistenciapersonal u WHERE u.fecha = :fecha AND ((:horainicial>=u.id_horario.horaEntrada and :horainicial <u.id_horario.HoraSalida) or (:horainicial <= u.id_horario.horaEntrada  AND  :horafinal >= u.id_horario.HoraSalida) or (:horafinal> u.id_horario.horaEntrada  AND  :horafinal <=u.id_horario.HoraSalida))"),
        @NamedQuery(name = "Asistenciapersonal.filtrodocente", query = "SELECT u FROM Asistenciapersonal u WHERE u.fecha = :fecha AND u.id_usuario= :idusuario "),
        @NamedQuery(name = "Asistenciapersonal.filtroaula", query = "SELECT u FROM Asistenciapersonal u WHERE u.fecha = :fecha AND u.id_horario.id_AulaPeriodo = :idaulaperiodo")}
)
public class Asistenciapersonal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_AsistenciaPersonal")
    private Long id;

    @JoinColumn(name = "id_horario", referencedColumnName = "id_horario")
    @ManyToOne
    private Horario id_horario;
    @JoinColumn(name = "id_Usuario", referencedColumnName = "id_Usuario")
    @ManyToOne
    private Usuario id_usuario;
    
    @Temporal(TemporalType.TIME)
    private Date horaEntrada;

    public Horario getId_horario() {
        return id_horario;
    }

    public void setId_horario(Horario id_horario) {
        this.id_horario = id_horario;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public boolean isFalta() {
        return falta;
    }

    public void setFalta(boolean falta) {
        this.falta = falta;
    }

    public boolean isTardanza() {
        return tardanza;
    }

    public void setTardanza(boolean tardanza) {
        this.tardanza = tardanza;
    }

    public String getCurricula() {
        return curricula;
    }

    public void setCurricula(String curricula) {
        this.curricula = curricula;
    }
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Temporal(TemporalType.TIME)
    private Date horaSalida;
    private boolean falta;
    private boolean tardanza;
    private String curricula;
    
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
        if (!(object instanceof Asistenciapersonal)) {
            return false;
        }
        Asistenciapersonal other = (Asistenciapersonal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clase.Asistenciapersonal[ id=" + id + " ]";
    }
    
}
