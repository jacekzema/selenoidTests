pipeline {
    agent any
    tools {
        maven 'Maven' // Ensure Maven is installed and configured in Jenkins
        git 'Default' // Ensure this matches the name configured in Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/jacekzema/selenoidTests.git', branch: 'main'
            }
        }
        stage('Build and Test') {
            steps {
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    bat 'mvn clean test'
        }
            }
        }
        stage('Allure Report') {
            steps {
                 bat 'allure generate --clean'
                 }
            }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'allure-report/**', allowEmptyArchive: true
            publishHTML(target: [
                reportName: 'Allure Report',
                reportDir: 'allure-report',
                reportFiles: 'index.html'
            ])
        }
    }
}