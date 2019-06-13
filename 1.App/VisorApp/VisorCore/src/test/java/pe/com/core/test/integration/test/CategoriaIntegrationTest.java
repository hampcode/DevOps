package pe.com.core.test.integration.test;

import static org.mockito.Mockito.doNothing;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pe.com.core.business.CategoriaBusiness;
import pe.com.core.entity.Categoria;


public class CategoriaIntegrationTest {
	private final CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
	private static Categoria categoria = new Categoria();
	private String mensaje = "";
	private String nombreCategoria = "";
	@Mock
	private HttpServletResponse response;

	@Given("^despues de iniciar sesion en la aplicacion$")
	public void despues_de_iniciar_sesion_en_la_aplicacion() throws Throwable {
		MockitoAnnotations.initMocks(this);
		doNothing().when(response).sendRedirect("http://master/VisorWeb");
		Assert.assertTrue(true);
	}

	@When("^hago click en el enlace de Mantenimiento de Categoria$")
	public void hago_click_en_el_enlace_de_Mantenimiento_de_Categoria() throws Throwable {
		doNothing().when(response).sendRedirect("http://master/mntAdmCategoria.xhtml");
		Assert.assertTrue(true);
	}

	@When("^luego hago click en el boton de Nuevo$")
	public void luego_hago_click_en_el_boton_de_Nuevo() throws Throwable {
		doNothing().when(response).sendRedirect("http://master/registrarAdmCategoria.xhtml");
		Assert.assertTrue(true);
	}

	@When("^en la nueva pantalla escribo en campo Nombre el valor de \"([^\"]*)\"$")
	public void en_la_nueva_pantalla_escribo_en_campo_Nombre_el_valor_de(String arg1) throws Throwable {
		nombreCategoria = arg1;
		categoria.setNombre(nombreCategoria);
	}

	@When("^presiono el boton de Guardar$")
	public void presiono_el_boton_de_Guardar() throws Throwable {
		try {
			categoriaBusiness.insertar(categoria);
			mensaje = "Se guardo correctamente la Categoría";
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: " + e.getMessage());
		}
	}

	@Then("^el sistema me muestra el mensaje de: \"([^\"]*)\"$")
	public void el_sistema_me_muestra_el_mensaje_de(String arg1) throws Throwable {
		try {
			Assert.assertEquals(arg1, mensaje);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: " + e.getMessage());
		}
	}

	@When("^busco la categoria \"([^\"]*)\" para seleccionarlo de la tabla de Categorias$")
	public void busco_la_categoria_para_seleccionarlo_de_la_tabla_de_Categorias(String arg1) throws Throwable {
		try {
			List<Categoria> lista = categoriaBusiness.listar(arg1);
			Assert.assertTrue(lista.size() > 0);
			categoria = lista.get(0);
			doNothing().when(response).sendRedirect("http://master/mntAdmCategoria.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: " + e.getMessage());
		}

	}

	@When("^luego hago click en el boton de Editar$")
	public void luego_hago_click_en_el_boton_de_Editar() throws Throwable {
		doNothing().when(response).sendRedirect("http://master/mntAdmCategoria.xhtml");
		Assert.assertTrue(true);
	}

	@When("^presiono el boton de Actualizar$")
	public void presiono_el_boton_de_Actualizar() throws Throwable {
		try {
			
			categoriaBusiness.actualizar(categoria);
			mensaje = "Se actualizo correctamente la Categoría";
			List<Categoria> lista = categoriaBusiness.listar(nombreCategoria);
			Assert.assertTrue(lista.size() > 0);
			Assert.assertEquals(lista.get(0).getNombre().toUpperCase(), categoria.getNombre().toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: " + e.getMessage());
		}
	}

	@When("^luego hago click en el boton de Eliminar$")
	public void luego_hago_click_en_el_boton_de_Eliminar() throws Throwable {
		try {
			categoriaBusiness.eliminar(categoria);
			mensaje = "Se elimino correctamente la Categoría";
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error: " + e.getMessage());
		}
	}

}
