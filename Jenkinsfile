@Library('threepoints-sharedlib') _

pipeline {
    agent any

    stages {
        stage('Análisis de Calidad') {
            steps {
                script {
                    // Llamar a la función sonarAnalysis de la librería
                    sonarAnalysis abortQualityGate: true, abortPipeline: true
                }
            }
        }
        stage('Continuar') {
            steps {
                echo 'Continuando con el pipeline...'
            }
        }
    }
}
