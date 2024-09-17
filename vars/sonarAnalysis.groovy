def call(boolean abortQualityGate = false, boolean abortPipeline = false) {
    // Configuración del entorno de SonarQube (real o simulado)
    withSonarQubeEnv('SonarQubeServer') {  // 'SonarQubeServer' es el nombre de tu server Sonar en Jenkins
        try {
            // Simulación del escaneo de SonarQube
            sh 'echo "Ejecución de las pruebas de calidad de código"'

            // Esperar el resultado del análisis de SonarQube con un timeout de 5 minutos
            timeout(time: 5, unit: 'MINUTES') {
                // Si estás usando SonarQube, espera el resultado real del QualityGate
                def qualityGate = waitForQualityGate()

                // Evaluar el estado del Quality Gate
                if (qualityGate.status != 'OK') {
                    echo "El Quality Gate ha fallado: ${qualityGate.status}"
                    
                    if (abortQualityGate) {
                        // Marcar el build como UNSTABLE o abortar el pipeline según los parámetros
                        currentBuild.result = 'UNSTABLE'
                        
                        if (abortPipeline) {
                            error "El Quality Gate ha fallado y se abortará el pipeline."
                        }
                    }
                } else {
                    echo "El Quality Gate ha pasado con éxito."
                }
            }
        } catch (Exception e) {
            echo "Se produjo un error durante el análisis de SonarQube: ${e.message}"
        }
    }
}
