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
                    bat 'mvn clean test -Dheadless=true'  // only headless mode will work on jenkins
        }
            }
        }
    }
    post {
        always {
            bat 'mvn allure:report'
            junit 'target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'target/allure-results/**', allowEmptyArchive: true
            publishHTML(target: [
                reportName: 'Allure Report',
                reportDir: 'target/site/allure-maven-plugin',
                reportFiles: 'index.html'
            ])
        }
    }
}