package pe.com.test.system.selenium.driver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class VisorDriver {

	private static final String URL_HUB = "http://localhost:4445/wd/hub";
	
	public final static WebDriver inicializarDriver(String navegador, boolean remoto) {
		WebDriver webDriver = null;
		try{
			System.out.println("Ejecución Remota: " + (remoto ? "SI" : "NO"));
			switch (navegador) {
			case "firefox":
				if(remoto){
					URL server = new URL(URL_HUB);
					DesiredCapabilities capabilities=DesiredCapabilities.firefox();
				    webDriver = new RemoteWebDriver(server, capabilities);
				}else{
					System.setProperty("webdriver.gecko.driver", "C:\\ProgramasInstalados\\geckodriver.exe");
					webDriver = new FirefoxDriver();
				}
				break;
			case "chrome":
				if(remoto){
					URL server = new URL(URL_HUB);
					DesiredCapabilities capabilities=DesiredCapabilities.chrome();
				    webDriver = new RemoteWebDriver(server, capabilities);
				}else{
					System.setProperty("webdriver.chrome.driver", "C:\\ProgramasInstalados\\chromedriver.exe");
					webDriver = new ChromeDriver();
				}
				break;
			}
			webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return webDriver;
	}

	public final static void cerrarPagina(WebDriver webDriver) {
		if (webDriver != null) {
			webDriver.quit();
		}
	}


}
