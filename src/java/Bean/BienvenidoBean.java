/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Clase.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Erick
 */
@ManagedBean
@RequestScoped
public class BienvenidoBean {

    Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    /**
     * Creates a new instance of BienvenidoBean
     */
    public BienvenidoBean() {
        HttpSession session = util.Util.getSession();
        
            usuario= (Usuario) session.getAttribute("usuario");
        
    }
    public String cerrarsesion()
    {
        HttpSession session = util.Util.getSession();
        session.removeAttribute("usuario");
        System.out.println("Saliendo del sistema");
        return "logout";
    }
    
}
