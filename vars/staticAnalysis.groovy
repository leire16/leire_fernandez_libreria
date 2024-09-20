def call(boolean abortQualityGate = false, boolean abortPipeline = false) {
    // Configuración del entorno del análisis estático (genérico o personalizado)
    try {
        // Simulación o ejecución del análisis estático (puedes reemplazar este comando por uno real)
        sh 'echo "Ejecución de análisis estático de código..."'

        // Esperar el resultado del análisis con un timeout de 5 minutos
        timeout(time: 5, unit: 'MINUTES') {
            // Simulación de la evaluación del Quality Gate
            // Aquí puedes implementar la lógica real si estás usando una herramienta específica
            def qualityGateStatus = 'OK'  // Simulación: cambiar por la evaluación real del Quality Gate

            // Evaluar el estado del Quality Gate
            if (qualityGateStatus != 'OK') {
                echo "El Quality Gate ha fallado: ${qualityGateStatus}"
                
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

        // Ampliación: Evaluar el nombre de la rama y las reglas de corte de pipeline
        def branchName = env.BRANCH_NAME  // Usar la variable de entorno definida manualmente en el Jenkinsfile

        if (abortPipeline) {
            error "El análisis ha determinado que se debe abortar el pipeline."
        } else {
            // Reglas según la rama
            if (branchName == 'master' || branchName.startsWith('hotfix')) {
                error "La rama es ${branchName}. Se abortará el pipeline según las reglas definidas."
            } else {
                echo "La rama es ${branchName}. El pipeline continuará."
            }
        }
        
    } catch (Exception e) {
        echo "Se produjo un error durante el análisis estático de código: ${e.message}"
    }
}
