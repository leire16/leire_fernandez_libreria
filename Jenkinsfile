@Library('threepoints-sharedlib') _

pipeline {
    agent any

    stages {
        stage('SonarQube Analysis') {
           steps {
                script {
                    // Llamada a la función call del script sonarAnalysis.groovy
                    sonarAnalysis(true, true)
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
