/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clase;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Erick
 */
@Table(name = "tbestudianteaulaperiodo")
@Entity
public class Estudianteaulaperiodo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id_estudianteaulaperiodo")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_Estudiante")
    private Estudiante id_Estudiante=null;
    @ManyToOne
    @JoinColumn(name = "id_AulaPeriodo")
    private Aulaperiodo id_AulaPeriodo=null;
    private String detalle;
    private boolean activo;
    
    public Estudiante getId_Estudiante() {
        return id_Estudiante;
    }

    public void setId_Estudiante(Estudiante id_Estudiante) {
        this.id_Estudiante = id_Estudiante;
    }

    public Aulaperiodo getId_AulaPeriodo() {
        return id_AulaPeriodo;
    }

    public void setId_AulaPeriodo(Aulaperiodo id_AulaPeriodo) {
        this.id_AulaPeriodo = id_AulaPeriodo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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
        if (!(object instanceof Estudianteaulaperiodo)) {
            return false;
        }
        Estudianteaulaperiodo other = (Estudianteaulaperiodo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clase.Estudianteaulaperiodo[ id=" + id + " ]";
    }
    
}
