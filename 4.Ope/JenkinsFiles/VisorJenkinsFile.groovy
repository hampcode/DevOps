#!groovy
node {
    
    def direccionGeneral = "C:\\Jenkins\\Visor03App"
	def rutaJmeter = "D:\\ProgramasInstalados\\apache-jmeter-4.0\\bin"
    def rutaWildfly = "D:\\ProgramasInstalados\\wildfly-12.0.0.Final\\bin"
	def rutaDireccionDataPrueba = "C:\\Jenkins\\Visor03App\\3.Test\\1.PruebasFuncionales\\VisorTest\\src\\test\\resources"
	
    stage('Descargar Fuentes'){
        dir(direccionGeneral) {
            /*checkout([$class: 'SubversionSCM', 
                  additionalCredentials: [], 
                  excludedCommitMessages: '', 
                  excludedRegions: '', 
                  excludedRevprop: '', 
                  excludedUsers: '', 
                  filterChangelog: false, 
                  ignoreDirPropChanges: false, 
                  includedRegions: '', 
                  locations: [[credentialsId: 'af4eff46-94b4-4585-a576-46fc03ee5f54', 
                               depthOption: 'infinity', 
                               ignoreExternalsOption: true, 
                               remote: "https://MATRIX:446/svn/Visor/trunk/prod/"]], 
                  workspaceUpdater: [$class: 'UpdateUpdater']])
			*/
			git branch: 'dev', credentialsId: 'USUARIO_PERSONAL_GIT', url: 'https://hwongu@bitbucket.org/hwongu/visormonolitico.git'

        }
    }
    
    stage('Compilar fuentes'){
        dir(direccionGeneral + '\\1.App\\VisorApp') {
            bat "mvn clean install"
        }
    }
    
    stage('Crear Entorno'){
        dir(direccionGeneral + '\\2.DB\\VisorDb') {
            bat "mvn flyway:clean -DVisorDb.urlBaseDatos=localhost:3306 -DVisorDb.baseDatos=visorbd -DVisorDb.usuarioBaseDatos=visoruser -DVisorDb.claveBaseDatos=visorpass"
			bat "mvn flyway:migrate -DVisorDb.urlBaseDatos=localhost:3306 -DVisorDb.baseDatos=visorbd -DVisorDb.usuarioBaseDatos=visoruser -DVisorDb.claveBaseDatos=visorpass"
		}
    }
    
    stage('Pruebas Unitarias'){
        dir(direccionGeneral + '\\1.App\\VisorApp\\VisorCore') {
			bat "mvn test -Dtest=*UnitSuite"
			bat "mvn sonar:sonar"
            step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml'])
        }
    }
	
	stage('Pruebas Integrales'){
        dir(direccionGeneral + '\\1.App\\VisorApp\\VisorCore') {
			bat "mvn cobertura:cobertura -Dtest=*IntegrationSuite"
            cobertura autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: '**/target/site/cobertura/*.xml', conditionalCoverageTargets: '70, 0, 0', failUnhealthy: false, failUnstable: false, lineCoverageTargets: '80, 0, 0', maxNumberOfBuilds: 0, methodCoverageTargets: '80, 0, 0', onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false
			cucumber fileIncludePattern: '**/target/*.json', sortingMethod: 'ALPHABETICAL'
        }
    }
	
	stage('Desplegar QA'){
		bat "${rutaWildfly}\\jboss-cli.bat -c --command=\"undeploy VisorWeb.war\""
        bat "${rutaWildfly}\\jboss-cli.bat -c --command=\"deploy ${direccionGeneral}\\1.App\\VisorApp\\VisorWeb\\target\\VisorWeb.war\""
	}
	
	stage('Pruebas Funcionales'){
		dir(direccionGeneral + '\\3.Test\\1.PruebasFuncionales\\VisorTest') {
			bat "mvn test -Dchrome.rutaArchivo=\"${rutaDireccionDataPrueba}\\RegistrarCategoriaData.xlsx\" -Dfirefox.rutaArchivo=\"${rutaDireccionDataPrueba}\\RegistrarCategoriaData.xlsx\""
			step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml'])
		}
	}

	
	stage('Pruebas No Funcionales'){
		dir(direccionGeneral + '\\3.Test\\2.PruebasNoFuncionales') {
			bat "${rutaJmeter}\\jmeter.bat -Jjmeter.save.saveservice.output_format=xml -n -t ${direccionGeneral}\\3.Test\\2.PruebasNoFuncionales\\JMeterVisor.jmx -l ResultadoVisor.jtl"
            perfReport percentiles: '0,50,90,100', sourceDataFiles: '**/*.jtl'
		}
	}
	
	    
    stage('Entregar Artefacto'){
        dir(direccionGeneral + '\\1.App\\VisorApp'){
            def server = Artifactory.server 'Artifactory'
            def workspace = pwd() 
            def uploadSpec = """{
             "files": [
                {
                  "pattern": "${direccionGeneral}\\1.App\\VisorApp\\VisorWeb\\target\\*.war",
                  "target": "generic-local/VisorApp/${BUILD_NUMBER}/"
                }
             ]
            }"""
            server.upload(uploadSpec)
        }
    }
   
}