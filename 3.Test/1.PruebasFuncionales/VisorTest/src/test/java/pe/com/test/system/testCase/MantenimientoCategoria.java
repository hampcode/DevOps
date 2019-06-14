package pe.com.test.system.testCase;

import org.testng.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import pe.com.test.system.selenium.page.BienvenidaPage;
import pe.com.test.system.selenium.page.CategoriaPage;
import pe.com.test.system.selenium.page.IniciarSesionPage;


public class MantenimientoCategoria {

	private String urlInicial="http://localhost:8080/VisorWeb/";
	private IniciarSesionPage iniciarSesionPage;
	private BienvenidaPage bienvenidaPage;
	private CategoriaPage categoriaPage;
	

	@BeforeTest
	@Parameters({"navegador"})
	public void inicioClase(String navegador) throws Exception {
		this.iniciarSesionPage = new IniciarSesionPage(navegador.toLowerCase());
		this.bienvenidaPage = new BienvenidaPage(this.iniciarSesionPage.getWebDriver());
		this.categoriaPage = new CategoriaPage(this.iniciarSesionPage.getWebDriver());
		
	}

	
	@Test
	public void insertarCategoria_FlujoBasico() throws Exception {
		try {
			this.iniciarSesionPage.ingresarPaginaIniciarSesion(urlInicial);
			this.iniciarSesionPage.iniciarSesion("admin", "clave");
			this.bienvenidaPage.hacerClicMenuPrincipal();
			this.bienvenidaPage.hacerClicMenuModuloAlmancen();
			this.bienvenidaPage.hacerClicMantenimientoCategoria();
			this.categoriaPage.hacerClicBotonNuevo();
			this.categoriaPage.escribirCampoNombre("Prueba");
			String valorObtenido = categoriaPage.hacerClicBotonGuardar();
			Assert.assertEquals(valorObtenido, "Se guardó de manera correcta la Categoría");
			
		} catch (AssertionError e) {
			
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void insertarCategoria_FlujoAlternativo() throws Exception {
		try {
			this.iniciarSesionPage.ingresarPaginaIniciarSesion(urlInicial);
			this.iniciarSesionPage.iniciarSesion("admin", "clave");
			this.bienvenidaPage.hacerClicMenuPrincipal();
			this.bienvenidaPage.hacerClicMenuModuloAlmancen();
			this.bienvenidaPage.hacerClicMantenimientoCategoria();
			this.categoriaPage.hacerClicBotonNuevo();
			this.categoriaPage.escribirCampoNombre("");
			String valorObtenido = categoriaPage.hacerClicBotonGuardar();
			Assert.assertEquals(valorObtenido, "Nombre: Validation Error: Value is required.");
			
		} catch (AssertionError e) {
			
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@AfterTest
	public void tearDown() throws Exception {
		categoriaPage.cerrarPagina();
	}

}
