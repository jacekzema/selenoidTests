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
            // Publish the Allure results and report as an artifact
            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'target/allure-results']]  // Location of Allure results
            ])

            // Archive Allure report directory
            archiveArtifacts artifacts: 'target/site/allure-maven-plugin/**', allowEmptyArchive: true

            // Display link to Allure report on Jenkins
            publishHTML(target: [
                reportName : 'Allure Report',
                reportDir  : 'target/site/allure-maven-plugin',
                reportFiles: 'index.html',
                alwaysLinkToLastBuild: true,
                keepAll: true
            ])
        }
    }
}