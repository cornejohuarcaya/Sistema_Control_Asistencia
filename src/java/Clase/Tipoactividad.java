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
import javax.persistence.Table;

/**
 *
 * @author Erick
 */
@Table(name = "Tbtipoactividad")
@Entity
public class Tipoactividad implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_tipoactividad")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
private  String actividad;
private  String observacion;

//geters y seters
public Long getId_tipoactividad() { 
    return id;
 } 

public void setId_tipoactividad( Long id_tipoactividad) { 
    this.id=id_tipoactividad ;
 } 

public String getActividad() { 
    return actividad;
 } 

public void setActividad( String actividad) { 
    this.actividad=actividad ;
 } 

public String getObservacion() { 
    return observacion;
 } 

public void setObservacion( String observacion) { 
    this.observacion=observacion ;
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
        if (!(object instanceof Tipoactividad)) {
            return false;
        }
        Tipoactividad other = (Tipoactividad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clase.Usuario[ id=" + id + " ]";
    }

}
