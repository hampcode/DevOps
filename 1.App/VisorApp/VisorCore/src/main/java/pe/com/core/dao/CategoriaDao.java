package pe.com.core.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import pe.com.core.entity.Categoria;

/**
 * Clase que tiene los metodos de Acceso a Datos para la tabla Categoria
 * Comentario agregado
 * @author Henry Joe Wong Urquiza
 * @email hwongu@gmail.com
 */
public class CategoriaDao extends Conexion<Categoria> {

	@Override
	public Categoria insertar(Categoria e) throws Exception {
		try {
			cn = obtenerConexion();
			String sql = "INSERT INTO CATEGORIA (nombre) VALUES (?)";
			pr = cn.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			pr.setString(1, e.getNombre().toUpperCase());
			pr.executeUpdate();
			rs = pr.getGeneratedKeys();
			rs.next();
			e.setIdCategoria(rs.getInt(1));
		} finally {
			rs.close();
			pr.close();
			cn.close();
		}
		return e;
	}

	@Override
	public Categoria actualizar(Categoria e) throws Exception {
		try {
			cn = obtenerConexion();
			String sql = "UPDATE CATEGORIA SET nombre=? WHERE idCategoria=?";
			pr = cn.prepareStatement(sql);
			pr.setString(1, e.getNombre().toUpperCase());
			pr.setInt(2, e.getIdCategoria());
			pr.executeUpdate();
		} finally {
			pr.close();
			cn.close();
		}
		return e;
	}

	@Override
	public Categoria eliminar(Categoria e) throws Exception {
		try {
			cn = obtenerConexion();
			String sql = "DELETE FROM CATEGORIA WHERE idCategoria=?";
			pr = cn.prepareStatement(sql);
			pr.setInt(1, e.getIdCategoria());
			pr.executeUpdate();
		} finally {
			pr.close();
			cn.close();
		}
		return e;
	}

	@Override
	public Categoria obtener(Categoria e) throws Exception {
		Categoria categoria = null;
		try {
			cn = obtenerConexion();
			String sql = "SELECT * FROM CATEGORIA WHERE idCategoria=? ORDER BY nombre";
			pr = cn.prepareStatement(sql);
			pr.setInt(1, e.getIdCategoria());
			rs = pr.executeQuery();
			while (rs.next()) {
				categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("idCategoria"));
				categoria.setNombre(rs.getString("nombre").toUpperCase());
			}
		} finally {
			rs.close();
			pr.close();
			cn.close();
		}
		return categoria;
	}

	@Override
	public List<Categoria> listar() throws Exception {
		List<Categoria> categorias = new ArrayList<Categoria>();
		Categoria categoria;
		try {
			cn = obtenerConexion();
			String sql = "SELECT * FROM CATEGORIA ORDER BY nombre";
			pr = cn.prepareStatement(sql);
			rs = pr.executeQuery();
			while (rs.next()) {
				categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("idCategoria"));
				categoria.setNombre(rs.getString("nombre").toUpperCase());
				categorias.add(categoria);
			}
		} finally {
			rs.close();
			pr.close();
			cn.close();
		}
		return categorias;
	}

	public List<Categoria> listar(String nombre) throws Exception {
		List<Categoria> categorias = new ArrayList<Categoria>();
		Categoria categoria;
		try {
			cn = obtenerConexion();
			String sql = "SELECT * FROM CATEGORIA ";
			sql += " WHERE UCASE(nombre) LIKE '%" + nombre + "%'";
			sql += " ORDER BY nombre";
			pr = cn.prepareStatement(sql);
			rs = pr.executeQuery();
			while (rs.next()) {
				categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("idCategoria"));
				categoria.setNombre(rs.getString("nombre").toUpperCase());
				categorias.add(categoria);
			}
		} finally {
			rs.close();
			pr.close();
			cn.close();
		}
		return categorias;
	}

}
