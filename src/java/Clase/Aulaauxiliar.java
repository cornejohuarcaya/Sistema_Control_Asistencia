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
@Table(name = "tbaulaauxiliar")
@Entity
public class Aulaauxiliar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id_AulaAuxiliar;
private  int id_AulaPeriodo;
private  int id_Usuario;
private  String Detalle;
private Aulaperiodo aulaperiodo= new Aulaperiodo();

    public Aulaperiodo getAulaperiodo() {
        return aulaperiodo;
    }

    public void setAulaperiodo(Aulaperiodo aulaperiodo) {
        this.aulaperiodo = aulaperiodo;
    }


//geters y seters
public int getId_AulaAuxiliar() { 
    return id_AulaAuxiliar;
 } 

public void setId_AulaAuxiliar( int id_AulaAuxiliar) { 
    this.id_AulaAuxiliar=id_AulaAuxiliar ;
 } 

public int getId_AulaPeriodo() { 
    return id_AulaPeriodo;
 } 

public void setId_AulaPeriodo( int id_AulaPeriodo) { 
    this.id_AulaPeriodo=id_AulaPeriodo ;
 } 

public int getId_Usuario() { 
    return id_Usuario;
 } 

public void setId_Usuario( int id_Usuario) { 
    this.id_Usuario=id_Usuario ;
 } 

public String getDetalle() { 
    return Detalle;
 } 

public void setDetalle( String Detalle) { 
    this.Detalle=Detalle ;
 } 

}
