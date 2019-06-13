package pe.com.web.util;

import static java.text.MessageFormat.format;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * @author Henry Joe Wong Urquiza
 * @email hwongu@gmail.com
 */
public class WebUtil {

    private WebUtil() {
    }

    public static void mensajeError(String codigo, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, codigo, mensaje));
    }

    public static void mensajeInformacion(String codigo, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, codigo, mensaje));
    }

    public static void mensajeAdvertencia(String codigo, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, codigo, mensaje));
    }

    public static final String obtenerPropiedad(String clave, Object... parametros) {
        ResourceBundle rs = ResourceBundle.getBundle("messages");
        return MessageFormat.format(rs.getString(clave), parametros);
    }

    public static final void agregarObjetoSesion(String nombre, Object objeto) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute(nombre, objeto);
    }

    public static final Object obtenerObjetoSesion(String nombre) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute(nombre);
    }

    public static final void removerObjetoSesion(String nombre) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute(nombre);
    }

    public static String controlarError(Exception exception, final Logger log) {
        StringBuilder sb = new StringBuilder();
        String idError = idError();
        sb.append(format("Codigo de error: {0} \n", idError));
        sb.append(format("Mensaje de error: {0} \n", exception.getMessage()));
        if (exception.getCause() != null) {
            StackTraceElement[] elem = exception.getCause().getStackTrace();
            for (StackTraceElement ex : elem) {
                sb.append(format("Clase ''{0}'' en la linea ''{1}'' en el metodo ''{2}'' \n",
                        ex.getClassName(), ex.getLineNumber(), ex.getMethodName()));
            }
        }
        sb.append("Imprimir toda la traza del error: \n");
        log.error(sb.toString(), exception);
        return "Ocurrio un error inesperado, codigo de error: " + idError;
    }

    public static void redireccionar(String ruta, final Logger log) {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            FacesContext.getCurrentInstance().responseComplete();
            response.sendRedirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + ruta);
        } catch (Exception e) {
            controlarError(e, log);
        }
    }

    private static final String idError() {
        return convertirDate(new Date(), "ddMMyyyyhhmmss");
    }

    public static final String convertirDate(Date fecha, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.format(fecha);
    }

}
