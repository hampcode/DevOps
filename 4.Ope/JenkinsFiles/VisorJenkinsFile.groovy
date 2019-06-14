#!groovy
node {
    
    def repositorio = "C:\\Users\\Hamp\\Documents\\RepoLocal\\Visor" 
    def serverAplicaciones ="C:\\Software\\wildfly\\bin"
	
	
    stage('Descargar Fuentes'){
        dir(repositorio) {
           
			git branch: 'develop', credentialsId: 'USUARIO_PERSONAL_GIT', url: 'https://github.com/gitHAMP/DevOps.git'

        }
    }
    
    stage('Compilar fuentes'){
        dir(repositorio + '\\1.App\\VisorApp') {
            bat "mvn clean install"
        }
    }
    
    stage('Crear Entorno'){
        dir(repositorio + '\\2.DB\\VisorDb') {
            bat "mvn flyway:clean -DVisorDb.urlBaseDatos=localhost:3306 -DVisorDb.baseDatos=visorbd -DVisorDb.usuarioBaseDatos=visoruser -DVisorDb.claveBaseDatos=visorpass"
			bat "mvn flyway:migrate -DVisorDb.urlBaseDatos=localhost:3306 -DVisorDb.baseDatos=visorbd -DVisorDb.usuarioBaseDatos=visoruser -DVisorDb.claveBaseDatos=visorpass"
		}
    }
    
    stage('Pruebas Unitarias'){
        dir(repositorio + '\\1.App\\VisorApp\\VisorCore') {
			bat "mvn test -Dtest=*UnitSuite"
			bat "mvn sonar:sonar"
            step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml'])
        }
    }
	
	stage('Pruebas Integrales'){
        dir(repositorio + '\\1.App\\VisorApp\\VisorCore') {
			bat "mvn cobertura:cobertura -Dtest=*IntegrationSuite"
            cobertura autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: '**/target/site/cobertura/*.xml', conditionalCoverageTargets: '70, 0, 0', failUnhealthy: false, failUnstable: false, lineCoverageTargets: '80, 0, 0', maxNumberOfBuilds: 0, methodCoverageTargets: '80, 0, 0', onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false
			cucumber fileIncludePattern: '**/target/*.json', sortingMethod: 'ALPHABETICAL'
        }
    }
	
	stage('Desplegar QA'){
		bat "${serverAplicaciones}\\jboss-cli.bat -c --command=\"undeploy VisorWeb.war\""
        bat "${serverAplicaciones}\\jboss-cli.bat -c --command=\"deploy ${repositorio}\\1.App\\VisorApp\\VisorWeb\\target\\VisorWeb.war\""
	}
	
	stage('Pruebas Funcionales'){
		dir(repositorio + '\\3.Test\\1.PruebasFuncionales\\VisorTest') {
			bat "mvn test"
			step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml'])
		}
	}

	    
    stage('Entregar Artefacto'){
        dir(repositorio + '\\1.App\\VisorApp'){
            def server = Artifactory.server 'Artifactory'
            def workspace = pwd() 
            def uploadSpec = """{
             "files": [
                {
                  "pattern": "${repositorio}\\1.App\\VisorApp\\VisorWeb\\target\\*.war",
                  "target": "generic-local/VisorApp/${BUILD_NUMBER}/"
                }
             ]
            }"""
            server.upload(uploadSpec)
        }
    }
   
}