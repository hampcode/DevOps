package pe.com.core.test.unit.test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pe.com.core.dao.CategoriaDao;
import pe.com.core.entity.Categoria;


/**
 * 
 * @author 	Henry Joe Wong Urquiza
 * @email 	hwongu@gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoriaTest {

	@Mock
	private CategoriaDao categoriaDao;
    @Mock
    private Categoria categoria;

    @BeforeClass
    public static void inicioClase() {
        System.out.println("Inicio de la clase");
    }

    @AfterClass
    public static void finClase() {
        System.out.println("Fin de la clase");
    }

    @Before
    public void inicioMetodo() {
        System.out.println("Inicio Metodo");
    }

    @After
    public void finMetodo() {
        System.out.println("Fin Metodo");
    }

    @Test
    public void a_insertar() {
        try {
            System.out.println("Método Insertar");
            categoria = new Categoria();
            categoria.setNombre("Chocolates");
            categoria.setIdCategoria(1);
            when(categoriaDao.insertar(Matchers.any())).thenReturn(categoria);
            categoriaDao.insertar(categoria);
            Assert.assertTrue(categoria.getIdCategoria() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void b_actualizar() {
        try {
            System.out.println("Método Actualizar");
            Categoria categoriaBuscada;
            categoria.setNombre("Menestras");
            categoria.setIdCategoria(1);
            when(categoriaDao.obtener(categoria)).thenReturn(categoria);
            categoriaBuscada = categoriaDao.obtener(categoria);
            when(categoriaDao.actualizar(Matchers.any())).thenReturn(categoria);
            categoriaDao.actualizar(categoria);
            Assert.assertEquals(categoria.getNombre(),categoriaBuscada.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void c_obtener() {
        try {
            System.out.println("Método Obtener");
            Categoria categoriaBuscada;
            categoria.setNombre("Menestras");
            categoria.setIdCategoria(1);
            when(categoriaDao.obtener(categoria)).thenReturn(categoria);
            categoriaBuscada = categoriaDao.obtener(categoria);
            Assert.assertNotNull(categoriaBuscada);            
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void d_listar() {
        try {
            System.out.println("Método Listar");
            List<Categoria> listaCategorias = spy(new ArrayList<Categoria>());
            when(listaCategorias.add(Matchers.any())).thenReturn(true);
            when(categoriaDao.listar()).thenReturn(listaCategorias);
            List<Categoria> lista = categoriaDao.listar();
            Assert.assertTrue(lista.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void e_eliminar() {
        try {
            System.out.println("Método Eliminar");
            when(categoriaDao.eliminar(categoria)).thenReturn(categoria);
            categoriaDao.eliminar(categoria);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

}
