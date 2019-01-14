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
@Table(name = "tbaula")
@Entity
public class Aula implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Aula")
   private  Long id;
private  String anio;
private  String seccion;
private  String observaciones;

//geters y seters
public Long getId_aula() { 
    return id;
 } 

public void setId_aula( Long id_aula) { 
    this.id=id_aula ;
 } 

public String getAnio() { 
    return anio;
 } 

public void setAnio( String anio) { 
    this.anio=anio ;
 } 

public String getSeccion() { 
    return seccion;
 } 

public void setSeccion( String seccion) { 
    this.seccion=seccion ;
 } 

public String getObservaciones() { 
    return observaciones;
 } 

public void setObservaciones( String observaciones) { 
    this.observaciones=observaciones ;
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
        if (!(object instanceof Aula)) {
            return false;
        }
        Aula other = (Aula) object;
        if ((this.id == null && other.getId_aula()!= null) || (this.getId_aula() != null && !this.id.equals(other.getId_aula()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clase.Aula[ id=" + id + " ]";
    }
}
