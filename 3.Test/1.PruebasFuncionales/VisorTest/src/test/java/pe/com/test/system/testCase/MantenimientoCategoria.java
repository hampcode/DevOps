package pe.com.test.system.testCase;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pe.com.test.system.selenium.dataManager.Excel;
import pe.com.test.system.selenium.page.BienvenidaPage;
import pe.com.test.system.selenium.page.CategoriaPage;
import pe.com.test.system.selenium.page.IniciarSesionPage;
import pe.com.test.system.selenium.util.Utilitario;
import pe.com.test.system.testlink.util.VisorTestlink;

public class MantenimientoCategoria {

	private IniciarSesionPage iniciarSesionPage;
	private BienvenidaPage bienvenidaPage;
	private CategoriaPage categoriaPage;
	private String rutaCarpetaError = "C:\\CapturasPantallas\\Categorias";
	private Integer idNavegadorTestlink;
	private String nombreNavegadorTestlink;

	@BeforeTest
	@Parameters({ "navegador", "remoto", "testlinkNavegadorId", "testlinkNavegadorNombre" })
	public void inicioClase(String navegador, int remoto, int testlinkNavegadorId, String testlinkNavegadorNombre) throws Exception {
		this.iniciarSesionPage = new IniciarSesionPage(navegador.toLowerCase(), remoto == 1);
		this.bienvenidaPage = new BienvenidaPage(this.iniciarSesionPage.getWebDriver());
		this.categoriaPage = new CategoriaPage(this.iniciarSesionPage.getWebDriver());
		this.idNavegadorTestlink = testlinkNavegadorId;
		this.nombreNavegadorTestlink = testlinkNavegadorNombre;
	}

	@DataProvider(name = "datosEntrada")
	public static Object[][] datosPoblados(ITestContext context) {
		Object[][] datos = null;
		String fuenteDatos = context.getCurrentXmlTest().getParameter("fuenteDatos");
		System.out.println("Fuente de Datos: " + fuenteDatos);
		switch (fuenteDatos.toLowerCase()) {
		case "excel":
			String rutaArchivo = context.getCurrentXmlTest().getParameter("rutaArchivo");
			datos = Excel.leerExcel(rutaArchivo);
			break;
		}
		return datos;
	}

	@Test(dataProvider = "datosEntrada")
	public void insertarCategoria(String casoPrueba, String urlInicial, String usuario, String clave, String nombre,
			String valorEsperado, String urlTestlink, String keyTestlink, String idTestCaseInterno,
			String idTestCaseExterno, String idTestPlan, String idBuild, String nombreBuild) throws Exception {
		try {
			this.iniciarSesionPage.ingresarPaginaIniciarSesion(urlInicial);
			this.iniciarSesionPage.iniciarSesion(usuario, clave);
			this.bienvenidaPage.hacerClicMenuPrincipal();
			this.bienvenidaPage.hacerClicMenuModuloAlmancen();
			this.bienvenidaPage.hacerClicMantenimientoCategoria();
			this.categoriaPage.hacerClicBotonNuevo();
			this.categoriaPage.escribirCampoNombre(nombre.trim());
			String valorObtenido = categoriaPage.hacerClicBotonGuardar();
			Assert.assertEquals(valorObtenido, valorEsperado);
			VisorTestlink.reportarCasoDePrueba(urlTestlink, keyTestlink, Integer.parseInt(idTestCaseInterno),
					Integer.parseInt(idTestCaseExterno), Integer.parseInt(idTestPlan), true, Integer.parseInt(idBuild),
					nombreBuild, "Paso correctame la ejecución automática", this.idNavegadorTestlink,
					this.nombreNavegadorTestlink);
		} catch (AssertionError e) {
			Utilitario.caputarPantallarError(rutaCarpetaError, e.getMessage(), categoriaPage.getWebDriver());
			VisorTestlink.reportarCasoDePrueba(urlTestlink, keyTestlink, Integer.parseInt(idTestCaseInterno),
					Integer.parseInt(idTestCaseExterno), Integer.parseInt(idTestPlan), false, Integer.parseInt(idBuild),
					nombreBuild, "Ocurrio un error: " + e.getMessage(), this.idNavegadorTestlink,
					this.nombreNavegadorTestlink);
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
