package pe.com.test.system.testlink.util;

import java.net.URL;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

public final class VisorTestlink {

	private VisorTestlink() {

	}



	/**
	 * 
	 * @param urlTestlink
	 *            Url del Servicio Web del Testlink
	 * @param keyTestlink
	 *            Key del Usuario que registrara la Informacion
	 * @param idTestCaseInterno
	 *            El Id interno del Test Case, se puede visualizar al acercar el
	 *            mouse al Test Case en la opcion cuando lo crean : "Especificación
	 *            de Pruebas"
	 * @param idTestCaseExterno
	 *            El Id que te autogenera, concatenando con el prefijo del Proyecto
	 * @param idTestPlan
	 *            El Id interno del Test Project, se puede visualizar al acercar el
	 *            mouse al Test Project en la opcion cuando lo crean : "Gestión del
	 *            Proyecto de Pruebas"
	 * @param paso
	 *            Indicar si paso o no
	 * @param idBuild
	 *            El Id interno del Build, se puede visualizar al acercar el mouse
	 *            al Build en la opcion cuando lo crean : "Gestión de Builds"
	 * @param nombreBuild
	 *            El nombre del Build
	 * @param mensaje
	 *            El mensaje que queramos que aparezca
	 * @param idPlataforma
	 *            El Id interno de la Plataforma, se puede visualizar al acercar el
	 *            mouse al nombre de la Plataforma en la opcion cuando lo crean :
	 *            "Gestión de Plataformas"
	 * @param nombrePlataforma
	 *            El nombre de la plataforma, tener en cuenta considerar las
	 *            mayusculas y minusculas
	 * @return
	 */
	public static boolean reportarCasoDePrueba(String urlTestlink, String keyTestlink, Integer idTestCaseInterno,
			Integer idTestCaseExterno, Integer idTestPlan, boolean paso, Integer idBuild, String nombreBuild,
			String mensaje, Integer idPlataforma, String nombrePlataforma) {
		boolean seEjecuto = false;
		try {
			// Test Case Interno
			// Test Case Externo
			// Test Plan
			// Paso / No Paso
			// ID de Build
			// Nombre del Build
			// Mensaje
			// Usuario Ivitado
			// ID del Bug
			// ID de Plataforma
			// Nombre Plataforma
			// Campos Personalizadoss
			// Sobre Escribir
			URL testlinkUrl = new URL(urlTestlink);
			TestLinkAPI testlinkApi = new TestLinkAPI(testlinkUrl, keyTestlink);
			testlinkApi.reportTCResult(idTestCaseInterno, idTestCaseExterno, idTestPlan, paso ? ExecutionStatus.PASSED : ExecutionStatus.FAILED, idBuild,
					nombreBuild, mensaje, false, "Ninguno", idPlataforma, nombrePlataforma, null, false);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return seEjecuto;
	}

}
