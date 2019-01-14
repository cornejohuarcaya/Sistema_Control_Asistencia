/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clase;
import Clase.Aula;
import Clase.Periodoestudio;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Erick
 */
@Table(name = "tbaulaperiodo")
@Entity
public class Aulaperiodo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_AulaPeriodo")
    private Long id;
    @ManyToOne  
    @JoinColumn(name="id_Aula", referencedColumnName="id_aula")
    private Aula id_Aula;
    
    @ManyToOne
    @JoinColumn(name="id_periodoestudio", referencedColumnName="id_periodoestudio")
    private Periodoestudio id_PeriodoEstudio;
    
    @Column(name = "Observaciones")
    private String observaciones;
    

    public Long getId_AulaPeriodo() {
        return id;
    }

    public void setId_AulaPeriodo(Long id_AulaPeriodo) {
        this.id = id_AulaPeriodo;
    }

    public Aula getId_Aula() {
        return id_Aula;
    }

    public void setId_Aula(Aula id_Aula) {
        this.id_Aula = id_Aula;
    }

    public Periodoestudio getId_PeriodoEstudio() {
        return id_PeriodoEstudio;
    }

    public void setId_PeriodoEstudio(Periodoestudio id_PeriodoEstudio) {
        this.id_PeriodoEstudio = id_PeriodoEstudio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
        if (!(object instanceof Aulaperiodo)) {
            return false;
        }
        Aulaperiodo other = (Aulaperiodo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "Aulaperiodo{" + "id=" + id + ", id_Aula=" + id_Aula + ", id_PeriodoEstudio=" + id_PeriodoEstudio + ", observaciones=" + observaciones + '}';
    }

    
    

}
