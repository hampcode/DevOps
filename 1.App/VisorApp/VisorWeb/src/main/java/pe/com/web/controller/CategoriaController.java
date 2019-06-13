package pe.com.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import pe.com.core.dao.CategoriaDao;
import pe.com.core.entity.Categoria;
import pe.com.web.util.WebUtil;

/**
 * @author Henry Joe Wong Urquiza
 * @email hwongu@gmail.com
 */
@ManagedBean
@SessionScoped
public class CategoriaController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(CategoriaController.class);
    private static final long serialVersionUID = 1L;
    private String filtro = "";
    private List<Categoria> listaCategoria = new ArrayList<Categoria>();
    private CategoriaDao categoriaDao = new CategoriaDao();
    private Categoria categoriaGuardar = new Categoria();
    private Categoria categoriaSeleccionada = new Categoria();
    private String inicioMantenimiento = "";

    public CategoriaController() {

    }

    private void limpiar() {
        this.listaCategoria = new ArrayList<Categoria>();
        this.categoriaGuardar = new Categoria();
        this.categoriaSeleccionada = new Categoria();
    }

    public void insertar() {
        try {
            this.categoriaDao.insertar(categoriaGuardar);
            WebUtil.mensajeInformacion(WebUtil.obtenerPropiedad("categoriaController.guardarExito"), WebUtil.obtenerPropiedad("categoriaController.guardarExito"));
            this.limpiar();
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    public void actualizar() {
        try {
            this.categoriaDao.actualizar(categoriaGuardar);
            WebUtil.mensajeInformacion(WebUtil.obtenerPropiedad("categoriaController.actualizarExito"), WebUtil.obtenerPropiedad("categoriaController.actualizarExito"));
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    public void eliminar() {
        try {
            if (this.categoriaSeleccionada != null && this.categoriaSeleccionada.getNombre().length() >= 0) {
                this.categoriaDao.eliminar(categoriaSeleccionada);
                WebUtil.mensajeInformacion(WebUtil.obtenerPropiedad("categoriaController.eliminarExito"), WebUtil.obtenerPropiedad("categoriaController.eliminarExito"));
                this.limpiar();
            } else {
                WebUtil.mensajeAdvertencia(WebUtil.obtenerPropiedad("categoriaController.noSeleccionado"), WebUtil.obtenerPropiedad("categoriaController.noSeleccionado"));
            }
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    public void buscar() {
        try {
            this.listaCategoria = this.categoriaDao.listar(this.filtro.trim());
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    public String irMantenimiento() {
        this.limpiar();
        return "mntAdmCategoria";
    }

    public String irNuevo() {
        this.limpiar();
        return "registrarAdmCategoria";
    }

    public String irActualizar() {
        String rpta = "";
        if (this.categoriaSeleccionada != null && this.categoriaSeleccionada.getNombre().length() >= 0) {
            this.categoriaGuardar = this.categoriaSeleccionada;
            this.listaCategoria.clear();
            this.filtro = "";
            rpta = "actualizarAdmCategoria";
        } else {
            WebUtil.mensajeAdvertencia(WebUtil.obtenerPropiedad("categoriaController.noSeleccionado"), WebUtil.obtenerPropiedad("categoriaController.noSeleccionado"));
        }
        return rpta;
    }

    public String getInicioMantenimiento() {
        this.limpiar();
        return inicioMantenimiento;
    }

    public void setInicioMantenimiento(String inicioMantenimiento) {
        this.inicioMantenimiento = inicioMantenimiento;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<Categoria> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public Categoria getCategoriaSeleccionada() {
        return categoriaSeleccionada;
    }

    public void setCategoriaSeleccionada(Categoria categoriaSeleccionada) {
        this.categoriaSeleccionada = categoriaSeleccionada;
    }

    public Categoria getCategoriaGuardar() {
        return categoriaGuardar;
    }

    public void setCategoriaDao(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

}
