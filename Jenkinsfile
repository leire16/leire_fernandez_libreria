@Library('threepoints-sharedlib') _

pipeline {
    agent any

    environment {
        // Definir BRANCH_NAME manualmente
        BRANCH_NAME = 'prueba'  // Puedes cambiar 'master' por cualquier otro valor
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
                    // Llamada a la función staticAnalysis pasándole el parámetro abortQualityGate
                    staticAnalysis(false, false) // Puedes cambiar el primer parametro 'false' a 'true' para forzar el corte
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
