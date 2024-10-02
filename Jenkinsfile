pipeline {
    agent any
    tools {
        maven 'Maven 3.6.3' // Ensure Maven is installed and configured in Jenkins
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
        }
    }
}