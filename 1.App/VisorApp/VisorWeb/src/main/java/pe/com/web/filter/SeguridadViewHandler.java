package pe.com.web.filter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import pe.com.web.util.WebUtil;

/**
 * @author Henry Joe Wong Urquiza
 * @email hwongu@gmail.com
 */
@ManagedBean
@SessionScoped
public class SeguridadViewHandler implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(SeguridadViewHandler.class);

    public SeguridadViewHandler() {

    }

    public void isAdmin(ComponentSystemEvent event) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session.getAttribute("usuarioInicio") == null) {
            WebUtil.redireccionar("/index.xhtml", LOGGER);
        }
    }

}
