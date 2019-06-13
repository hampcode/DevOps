package pe.com.web.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import pe.com.web.bean.UsuarioBean;
import pe.com.web.util.WebUtil;

/**
 * @author Henry Joe Wong Urquiza
 * @email hwongu@gmail.com
 */
@ManagedBean
@ViewScoped
public class IniciarSesionController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(IniciarSesionController.class);
    private UsuarioBean usuarioBean = new UsuarioBean();

    public IniciarSesionController() {

    }

    @PostConstruct
    public void iniciarlizar(){
        this.usuarioBean = new UsuarioBean();
    }
    
    public String iniciarSesion() {
        String ruta = "";
        try {
            if (usuarioBean.getCorreo().equalsIgnoreCase("admin") && usuarioBean.getClave().equalsIgnoreCase("clave")) {
                ruta = "bienvenida?faces-redirect=true";
                WebUtil.agregarObjetoSesion("usuarioInicio", usuarioBean);
            } else {
                WebUtil.mensajeAdvertencia(
                        WebUtil.obtenerPropiedad("index.claveIncorrecta"),
                        WebUtil.obtenerPropiedad("index.claveIncorrecta"));

            }
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
        return ruta;
    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

}
