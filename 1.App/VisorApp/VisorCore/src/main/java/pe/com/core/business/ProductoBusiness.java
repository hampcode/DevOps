package pe.com.core.business;

import java.util.List;

import pe.com.core.dao.ProductoDao;
import pe.com.core.entity.Producto;

/**
 * Clase que tiene los metodos de Negocio para Producto
 * @author 	Henry Joe Wong Urquiza
 * @email 	hwongu@gmail.com
 */
public class ProductoBusiness {

	private final ProductoDao productoDao = new ProductoDao();
	
	public void insertar(Producto producto) throws Exception{
		this.productoDao.insertar(producto);
	}
	
	public void actualizar(Producto producto) throws Exception{
		this.productoDao.actualizar(producto);
	}
	
	public void eliminar(Producto producto) throws Exception{
		this.productoDao.eliminar(producto);
	}
	
	public Producto obtener(Integer idProducto)throws Exception{
		return this.productoDao.obtener(new Producto(idProducto));
	}
	
	public List<Producto> listar() throws Exception{
		return this.productoDao.listar();
	}
	
	public List<Producto> listar(String filtro) throws Exception{
		return this.productoDao.listar(filtro.trim().toUpperCase());
	}
	
}
