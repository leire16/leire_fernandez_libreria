@Library('threepoints-sharedlib') _

pipeline {
    agent any

    stages {
        stage('SonarQube Analysis') {
            environment {
                abortQualityGate = true
                abortPipeline = true
            }
            steps {
                script {
                    // Llamada a la función call del script sonarAnalysis.groovy
                    sonarAnalysis(abortQualityGate, abortPipeline)
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
