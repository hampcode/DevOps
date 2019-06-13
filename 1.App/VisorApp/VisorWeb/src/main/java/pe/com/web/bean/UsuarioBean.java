/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.web.bean;

import java.io.Serializable;

/**
 * @author Henry Joe Wong Urquiza
 * @email hwongu@gmail.com
 */
public class UsuarioBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String correo;
    private String clave;

    public UsuarioBean() {
    }

    public UsuarioBean(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}
