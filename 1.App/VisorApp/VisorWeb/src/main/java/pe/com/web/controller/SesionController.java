package pe.com.web.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import pe.com.web.util.WebUtil;

/**
 * @author Henry Joe Wong Urquiza
 * @email hwongu@gmail.com
 */
@ManagedBean
@SessionScoped
public class SesionController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(SesionController.class);
    private static final long serialVersionUID = 1L;

    public void cerrarSesion() {
        try {
            WebUtil.removerObjetoSesion("usuarioInicio");
            WebUtil.redireccionar("/index.xhtml", LOGGER);
        } catch (Exception e) {
            String mensaje = WebUtil.controlarError(e, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }
}
