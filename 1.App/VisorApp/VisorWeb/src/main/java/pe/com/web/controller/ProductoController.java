package pe.com.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import pe.com.core.dao.CategoriaDao;
import pe.com.core.dao.ProductoDao;
import pe.com.core.entity.Categoria;
import pe.com.core.entity.Producto;
import pe.com.web.util.WebUtil;

/**
 * @author Henry Joe Wong Urquiza
 * @email hwongu@gmail.com
 */
@ManagedBean
@SessionScoped
public class ProductoController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(CategoriaController.class);
    private static final long serialVersionUID = 1L;
    private String filtro = "";
    private List<Producto> listaProducto = new ArrayList<Producto>();
    private Producto productoGuardar = new Producto();
    private Producto productoSeleccionado = new Producto();
    private String inicioMantenimiento = "";
    private List<SelectItem> listaItemGrupos = null;
    private final CategoriaDao categoriaDao = new CategoriaDao();
    private final ProductoDao productoDao = new ProductoDao();
    
    private void limpiar() {
        this.listaProducto = new ArrayList<Producto>();
        this.productoGuardar = new Producto();
        this.productoSeleccionado = new Producto();
        this.filtro = "";
    }

    private void inicializarCategorias() {
        try {
            this.listaItemGrupos = new ArrayList<SelectItem>();
            List<Categoria> listaCategorias = categoriaDao.listar("");
            this.productoGuardar.setIdCategoria(listaCategorias.get(0).getIdCategoria());
            for (Categoria categoria : listaCategorias) {
                this.listaItemGrupos.add(new SelectItem(categoria.getIdCategoria(), categoria.getNombre()));
            }
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    public void insertar() {
        try {
            this.productoDao.insertar(this.productoGuardar);
            WebUtil.mensajeInformacion(WebUtil.obtenerPropiedad("productoController.guardarExito", this.productoGuardar.getIdProducto()), WebUtil.obtenerPropiedad("productoController.guardarExito", this.productoGuardar.getIdProducto()));
            this.limpiar();
            this.inicializarCategorias();
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    public void actualizar() {
        try {
            this.productoDao.actualizar(this.productoGuardar);
            WebUtil.mensajeInformacion(WebUtil.obtenerPropiedad("productoController.actualizarExito", this.productoGuardar.getIdProducto()), WebUtil.obtenerPropiedad("productoController.actualizarExito", this.productoGuardar.getIdProducto()));
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    public void eliminar() {
        try {
            if (this.productoSeleccionado != null && this.productoSeleccionado.getNombre().length() >= 0) {
                this.productoDao.eliminar(productoSeleccionado);
                WebUtil.mensajeInformacion(WebUtil.obtenerPropiedad("productoController.eliminarExito", this.productoSeleccionado.getIdProducto()), WebUtil.obtenerPropiedad("productoController.eliminarExito", this.productoSeleccionado.getIdProducto()));
                this.limpiar();
            } else {
                WebUtil.mensajeAdvertencia(WebUtil.obtenerPropiedad("productoController.noSeleccionado"), WebUtil.obtenerPropiedad("productoController.noSeleccionado"));
            }
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    public void buscar() {
        try {
            this.listaProducto = this.productoDao.listar(this.filtro.trim());
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    public String irMantenimiento() {
        this.limpiar();
        return "mntAdmProducto";
    }

    public String irNuevo() {
        this.limpiar();
        this.inicializarCategorias();
        return "registrarAdmProducto";
    }

    public String irActualizar() {
        String rpta = "";
        this.inicializarCategorias();
        if (this.productoSeleccionado != null && this.productoSeleccionado.getNombre().length() >= 0) {
            this.productoGuardar = this.productoSeleccionado;
            this.listaProducto.clear();
            this.filtro = "";
            rpta = "actualizarAdmProducto";
        } else {
            WebUtil.mensajeAdvertencia(WebUtil.obtenerPropiedad("productoController.noSeleccionado"), WebUtil.obtenerPropiedad("productoController.noSeleccionado"));
        }
        return rpta;
    }

    public String irActualizarStock() {
        String rpta = "";
        this.inicializarCategorias();
        if (this.productoSeleccionado != null && this.productoSeleccionado.getNombre().length() >= 0) {
            this.productoGuardar = this.productoSeleccionado;
            this.listaProducto.clear();
            this.filtro = "";
            rpta = "actualizarAlmacenProducto";
        } else {
            WebUtil.mensajeAdvertencia(WebUtil.obtenerPropiedad("productoController.noSeleccionado"), WebUtil.obtenerPropiedad("productoController.noSeleccionado"));
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

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public Producto getProductoGuardar() {
        return productoGuardar;
    }

    public void setProductoGuardar(Producto productoGuardar) {
        this.productoGuardar = productoGuardar;
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public List<SelectItem> getListaItemGrupos() {
        return listaItemGrupos;
    }

    public void setListaItemGrupos(List<SelectItem> listaItemGrupos) {
        this.listaItemGrupos = listaItemGrupos;
    }

}
