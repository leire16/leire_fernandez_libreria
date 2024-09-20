@Library('threepoints-sharedlib') _

pipeline {
    agent any

    environment {
        BRANCH_NAME = "${params.BRANCH_NAME ?: 'master'}"  // Valor por defecto 'master'
    }

    stages {
        stage('Verificar BRANCH_NAME') {
            steps {
                script {
                    // Verificar el nombre de la rama
                    echo "La rama actual es: ${env.BRANCH_NAME}"
                }
            }
        }
        stage('Static Code Analysis') {
            steps {
                script {
                    // Llamada a la función staticAnalysis
                    staticAnalysis(false)
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
