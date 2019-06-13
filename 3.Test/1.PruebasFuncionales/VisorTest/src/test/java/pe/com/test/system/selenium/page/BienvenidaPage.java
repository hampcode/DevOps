package pe.com.test.system.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pe.com.test.system.selenium.driver.VisorDriver;

public class BienvenidaPage {

	private By linkMenu = By.xpath("/html/body/section/div[1]/div");
	private By linkModAlmacen = By.xpath("/html/body/section/div[1]/nav/ul/li/span");
	private By linkMntCategoria = By.linkText("Mnt. de Categoría");
	private WebDriver webDriver = null;
	
	public BienvenidaPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public void hacerClicMenuPrincipal() throws Exception{
		webDriver.findElement(linkMenu).click();
		Thread.sleep(2000);
	}
	
	public void hacerClicMenuModuloAlmancen() throws Exception{
		webDriver.findElement(linkModAlmacen).click();
		Thread.sleep(2000);
	}
	
	public void hacerClicMantenimientoCategoria() throws Exception{
		webDriver.findElement(linkMntCategoria).click();
		Thread.sleep(2000);
	}
	
	
	public void cerrarPagina(){
		VisorDriver.cerrarPagina(webDriver);
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}
	
}
