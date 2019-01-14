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
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Erick
 */
@Table(name = "tbperiodoestudio")
@Entity
public class Periodoestudio implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_periodoestudio")
    private  Long id;
    
    @Column(name = "Anio", nullable = false)
private  String anio;
    
    @Column(name = "Observaciones")
private  String observaciones;

@Temporal(TemporalType.TIME)
private  Date horarioinicioclase;
@Temporal(TemporalType.TIME)
private  Date horarioiniciosalida;
@Temporal(TemporalType.DATE)
private  Date fechainicioescolar;
@Temporal(TemporalType.DATE)
private  Date fechasalidaescolar;
private  boolean activo;
@Temporal(TemporalType.DATE)
private  Date fecharealinicioescolar;
@Temporal(TemporalType.DATE)
private  Date fecharealsalidaescolar;

//geters y seters
public Long getId_periodoestudio() { 
    return id;
 } 

public void setId_periodoestudio( Long id_periodoestudio) { 
    this.id=id_periodoestudio ;
 } 

public String getAnio() { 
    return anio;
 } 

public void setAnio( String anio) { 
    this.anio=anio ;
 } 

public String getObservaciones() { 
    return observaciones;
 } 

public void setObservaciones( String observaciones) { 
    this.observaciones=observaciones ;
 } 

public Date getHorarioinicioclase() { 
    return horarioinicioclase;
 } 

public void setHorarioinicioclase( Date horarioinicioclase) { 
    this.horarioinicioclase=horarioinicioclase ;
 } 

public Date getHorarioiniciosalida() { 
    return horarioiniciosalida;
 } 

public void setHorarioiniciosalida( Date horarioiniciosalida) { 
    this.horarioiniciosalida=horarioiniciosalida;
 } 

public Date getFechainicioescolar() { 
    return fechainicioescolar;
 } 

public void setFechainicioescolar( Date fechainicioescolar) { 
    this.fechainicioescolar=fechainicioescolar ;
 } 

public Date getFechasalidaescolar() { 
    return fechasalidaescolar;
 } 

public void setFechasalidaescolar( Date fechasalidaescolar) { 
    this.fechasalidaescolar=fechasalidaescolar ;
 } 

public boolean getActivo() { 
    return activo;
 } 

public void setActivo( boolean activo) { 
    this.activo=activo ;
 } 

public Date getFecharealinicioescolar() { 
    return fecharealinicioescolar;
 } 

public void setFecharealinicioescolar( Date fecharealinicioescolar) { 
    this.fecharealinicioescolar=fecharealinicioescolar ;
 } 

public Date getFecharealsalidaescolar() { 
    return fecharealsalidaescolar;
 } 

public void setFecharealsalidaescolar( Date fecharealsalidaescolar) { 
    this.fecharealsalidaescolar=fecharealsalidaescolar ;
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
        if (!(object instanceof Periodoestudio)) {
            return false;
        }
        Periodoestudio other = (Periodoestudio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clase.Periodoestudio[ id=" + id + " ]";
    }

}
