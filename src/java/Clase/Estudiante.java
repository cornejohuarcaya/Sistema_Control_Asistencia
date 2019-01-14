/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clase;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Erick
 */
@Table(name = "tbestudiante")
@Entity
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_Estudiante")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getApoderado() {
        return apoderado;
    }

    public void setApoderado(String apoderado) {
        this.apoderado = apoderado;
    }

    public String getTelefonoapoderado() {
        return telefonoapoderado;
    }

    public void setTelefonoapoderado(String telefonoapoderado) {
        this.telefonoapoderado = telefonoapoderado;
    }


    public String getCorreoapoderado() {
        return correoapoderado;
    }

    public void setCorreoapoderado(String correoapoderado) {
        this.correoapoderado = correoapoderado;
    }
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "sexo")
    private boolean sexo;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "dni")
    private String dni;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "apoderado")
    private String apoderado;
    @Column(name = "telefonoapoderado")
    private String telefonoapoderado;
    @Column(name = "fechanac")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date fechanac;

    public Date getFechanac() {
        return fechanac;
    }
    public String sexocadena()
    {
        if (sexo)
            return "M";
        else
            return "F";
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }
    @Column(name = "correoapoderado")
    private String correoapoderado;
    
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
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clase.Estudiante[ id=" + id + " ]";
    }
    
}
