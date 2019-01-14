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
@Table(name = "tbusuario")
@Entity
@NamedQueries(
        {@NamedQuery(name = "Usuario.loguearse", query = "SELECT u FROM Usuario u WHERE u.usu= :usuario and u.pas= :pass")}
)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "Id_Usuario")
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JoinColumn(name = "id_TipoUsuario", referencedColumnName = "id_TipoUsuario")
    @ManyToOne
    private Tipousuario id_TipoUsuario;

    public Tipousuario getId_TipoUsuario() {
        return id_TipoUsuario;
    }

    public void setId_TipoUsuario(Tipousuario id_TipoUsuario) {
        this.id_TipoUsuario = id_TipoUsuario;
    }

    public TipoDocumento getId_TipoDocumento() {
        return id_TipoDocumento;
    }

    public void setId_TipoDocumento(TipoDocumento id_TipoDocumento) {
        this.id_TipoDocumento = id_TipoDocumento;
    }
    
    @JoinColumn(name = "id_TipoDocumento", referencedColumnName = "id_TipoDocumento")
    @ManyToOne
    private TipoDocumento id_TipoDocumento;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "numeroDoc")
    private String numeroDoc;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "Usuario")
    private String usu;
    @Column(name = "pass")
    private String pas;
    @Column(name = "activo")
    private boolean activo;
    @Column(name = "sexo")
    private boolean sexo;
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaNac")
    private Date fechaNac;
    @Column(name = "email")
    private String email;
    
  
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean getSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String sexocadena()
    {
        if (sexo)
            return "M";
                    else
            return "F";
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
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
