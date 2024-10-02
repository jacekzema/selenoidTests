pipeline {
    agent any
    tools {
        maven 'Maven' // Ensure Maven is installed and configured in Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/jacekzema/selenoidTests.git', branch: 'main'
            }
        }
        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
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