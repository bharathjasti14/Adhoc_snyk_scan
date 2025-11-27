pipeline {
    agent { label 'MacAgent' }

    environment {
        // Make sure Snyk CLI and Maven are in PATH
        PATH = "/opt/homebrew/bin:$PATH"
    }

    stages {
        stage('Check Tools') {
            steps {
                sh '''
                    echo "PATH: $PATH"
                    which mvn
                    mvn -v
                    which snyk
                    snyk --version
                '''
            }
        }
        
        stage('Checkout') {
            steps {
                // Checkout your code
                checkout scm
            }
        }

        stage('Build Java Project') {
            steps {
                // Build with Maven to resolve dependencies
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Snyk Test & Monitor') {
            steps {
                // Use Jenkins credentials for Snyk token
                withCredentials([string(credentialsId: 'snyk-token', variable: 'SNYK_TOKEN')]) {
                    sh '''
                        # Authenticate Snyk CLI
                        snyk auth $SNYK_TOKEN
                        
                        # Test for vulnerabilities in all projects
                        snyk test --all-projects --fail-on=all || true
                        
                        # Monitor and send results to Snyk UI
                        snyk monitor --all-projects
                    '''
                }
            }
        }
        stage('Cleanup Dependencies') {
            steps {
                sh 'mvn dependency:purge-local-repository -DreResolve=false'
                sh 'mvn clean'
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished'
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check logs for details.'
        }
    }
}
