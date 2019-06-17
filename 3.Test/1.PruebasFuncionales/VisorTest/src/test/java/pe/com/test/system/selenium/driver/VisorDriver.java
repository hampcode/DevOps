package pe.com.test.system.selenium.driver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class VisorDriver {

	public final static WebDriver inicializarDriver(String navegador) {
		WebDriver webDriver = null;
		try {

			switch (navegador) {
			case "firefox":
				
				//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Hamp\\Documents\\DE\\driver\\geckodriver.exe");
				System.setProperty("webdriver.gecko.driver", "/Users/hamp/Documents/DE/driver//geckodriver");
				webDriver = new FirefoxDriver();

				break;
			case "chrome":

				//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hamp\\Documents\\DE\\driver\\chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", "/Users/hamp/Documents/DE/driver//chromedriver");
				webDriver = new ChromeDriver();

				break;
			}
			webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception exception) {
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
