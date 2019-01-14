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
@Table(name = "tbacceso")
@Entity
public class Acceso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id_acceso;
private  String acceso;
private  String detalle;

//geters y seters
public int getId_acceso() { 
    return id_acceso;
 } 

public void setId_acceso( int id_acceso) { 
    this.id_acceso=id_acceso ;
 } 

public String getAcceso() { 
    return acceso;
 } 

public void setAcceso( String acceso) { 
    this.acceso=acceso ;
 } 

public String getDetalle() { 
    return detalle;
 } 

public void setDetalle( String detalle) { 
    this.detalle=detalle ;
 } 


}
