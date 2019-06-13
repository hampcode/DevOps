package pe.com.test.system.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pe.com.test.system.selenium.driver.VisorDriver;

public class CategoriaPage {


	private By botonNuevo = By.id("btnNuevo");
	private By cajaNombre = By.id("txtNombre");
	private By botonGuardar = By.id("btnGuardar");
	private By mensajeRespuesta = By.id("messages");
	private WebDriver webDriver = null;
	
	public CategoriaPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public void hacerClicBotonNuevo() throws Exception{
		webDriver.findElement(botonNuevo).click();
		Thread.sleep(2000);
	}
	
	public void escribirCampoNombre(String nombre) throws Exception {
		webDriver.findElement(cajaNombre).clear();
		webDriver.findElement(cajaNombre).sendKeys(nombre);
		Thread.sleep(2000);
	}

	public String hacerClicBotonGuardar() throws Exception {
		webDriver.findElement(botonGuardar).click();
		Thread.sleep(2000);
		return webDriver.findElement(mensajeRespuesta).getText();
	}
	
	public void cerrarPagina(){
		VisorDriver.cerrarPagina(webDriver);
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}

}
