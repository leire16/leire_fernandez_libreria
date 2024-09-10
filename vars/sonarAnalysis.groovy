def call(boolean abortQualityGate = false, boolean abortPipeline = false) {
    // Configuración del entorno de SonarQube
    withEnv(['SONAR_ENV=sonar']) {
        try {
            // Ejemplo de ejecución de SonarQube (reemplazar con comando real si se usa SonarQube)
            sh 'echo "Ejecución de las pruebas de calidad de código"'
            
            // Esperar el resultado con un timeout de 5 minutos
            timeout(time: 5, unit: 'MINUTES') {
                // Aquí se colocaría la lógica para comprobar el Quality Gate de SonarQube
                // Por ahora, simplemente se muestra un mensaje
                sh 'echo "Esperando el resultado de SonarQube..."'
            }
        } catch (Exception e) {
            echo "Se produjo un error durante el análisis de SonarQube"
        }
        
        // Evaluar el Quality Gate y decidir si abortar el pipeline
        if (abortQualityGate) {
            currentBuild.result = 'UNSTABLE'
            if (abortPipeline) {
                error "El Quality Gate ha fallado y se abortará el pipeline."
            }
        }
    }
}
