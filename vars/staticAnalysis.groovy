def call(boolean abortQualityGate = false, boolean abortPipeline = false) {
    // Obtener el nombre de la rama de la variable de entorno o pipeline
    def branchName = env.BRANCH_NAME ?: 'undefined'

    // Configuración del entorno de análisis estático (simulado)
    withEnv(['STATIC_ANALYSIS_ENV=static']) {
        try {
            // Simulación del análisis estático de código
            sh 'echo "Ejecución de análisis estático de código..."'

            // Esperar el resultado del análisis estático de código con un timeout de 5 minutos
            timeout(time: 5, unit: 'MINUTES') {
                // Aquí iría la lógica para evaluar el QualityGate
                // Por ahora, solo simulamos el proceso
                sh 'echo "Esperando el resultado del análisis estático..."'
            }
        } catch (Exception e) {
            echo "Se produjo un error durante el análisis estático: ${e.message}"
        }

        // Evaluar si se debe cortar el pipeline
        if (abortQualityGate) {
            currentBuild.result = 'UNSTABLE'
            if (abortPipeline || shouldAbortPipeline(branchName)) {
                error "El análisis estático falló y se abortará el pipeline."
            }
        } else {
            echo "El análisis estático pasó con éxito."
        }
    }
}

// Función auxiliar para determinar si se debe abortar el pipeline según el nombre de la rama
def shouldAbortPipeline(String branchName) {
    if (branchName == 'master' || branchName.startsWith('hotfix')) {
        echo "La rama es ${branchName}, se abortará el pipeline."
        return true
    }
    echo "La rama es ${branchName}, no se abortará el pipeline."
    return false
}
