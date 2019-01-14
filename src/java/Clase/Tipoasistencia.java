/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clase;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Erick
 */
@Table(name = "tbtipoasistencia")
@Entity
public class Tipoasistencia implements Serializable {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id_tipoasistencia;
private  String tipoasistencia;
private  String descripcion;

//geters y seters
public Long getId_tipoasistencia() { 
    return id_tipoasistencia;
 } 

public void setId_tipoasistencia( Long id_tipoasistencia) { 
    this.id_tipoasistencia=id_tipoasistencia ;
 } 

public String getTipoasistencia() { 
    return tipoasistencia;
 } 

public void setTipoasistencia( String tipoasistencia) { 
    this.tipoasistencia=tipoasistencia ;
 } 

public String getDescripcion() { 
    return descripcion;
 } 

public void setDescripcion( String descripcion) { 
    this.descripcion=descripcion ;
 } 


}
