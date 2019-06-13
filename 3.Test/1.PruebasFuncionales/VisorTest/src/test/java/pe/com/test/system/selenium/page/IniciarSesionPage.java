package pe.com.test.system.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pe.com.test.system.selenium.driver.VisorDriver;

public class IniciarSesionPage {

	private By cajaUsuario = By.id("txtUsuario");
	private By cajaClave = By.id("txtClave");
	private By botonIniciarSesion = By.id("btnIniciarSesion");
	private WebDriver webDriver = null;
	
	public IniciarSesionPage(String navegador, boolean remoto){
		this.webDriver = VisorDriver.inicializarDriver(navegador, remoto);
	}
	
	public void ingresarPaginaIniciarSesion(String urlInicial) throws Exception{
		webDriver.get(urlInicial);
		Thread.sleep(2000);
	}
	
	public void iniciarSesion(String usuario, String clave) throws Exception{
		webDriver.findElement(cajaUsuario).clear();
		webDriver.findElement(cajaUsuario).sendKeys(usuario);
		webDriver.findElement(cajaClave).clear();
		webDriver.findElement(cajaClave).sendKeys(clave);
		webDriver.findElement(botonIniciarSesion).click();
		Thread.sleep(2000);
	}
	
	public void cerrarPagina(){
		VisorDriver.cerrarPagina(webDriver);
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}
	
}
