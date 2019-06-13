package pe.com.test.system.selenium.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public final class Utilitario {

	public static void caputarPantallarError(String rutaCarpeta, String mensajeError, WebDriver webDriver) {
		try {
			// Los archivos que se crearan tendran el formato de dia, mes, anio,
			// hora, minuto, segundo y milisegundos
			String nombreArchivo = new SimpleDateFormat("ddMMyyyy-hhmmssSSS").format(new Date());
			// Se tiene que indicar de que navegardor se realizara una captura
			// de pantalla
			// Realizamos una captura de pantalla
			File capturaDePantalla = ((TakesScreenshot)webDriver ).getScreenshotAs(OutputType.FILE);
			// En caso que la carpeta no exista lo creara
			new File(rutaCarpeta).mkdirs();
			// Copia la captura de pantalla a la ruta que indicamos
			FileUtils.copyFile(capturaDePantalla, new File(rutaCarpeta + "\\" + nombreArchivo + ".jpg"));
			// Creamos un archivo txt con el mensaje de error
			File archivoMensajeError = new File(rutaCarpeta + "\\" + nombreArchivo + ".txt");
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(archivoMensajeError));
			bufferedWriter.write(mensajeError);
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Utilitario() {

	}
}
