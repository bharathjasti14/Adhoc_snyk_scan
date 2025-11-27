pipeline {
    agent {
        label 'MacAgent'
    }
    environment {
        SNYK_TOKEN = credentials('snyk-token')  // store token in Jenkins credentials
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Run Python script') {
          steps {
            //running the python script
            sh 'python3 simple-addition.py'
          }
        }
        stage('Snyk Scan') {
            steps {
                /*sh """
                docker run --rm --platform linux/amd64\
                  -e SNYK_TOKEN=${SNYK_TOKEN} \
                  -v \$PWD:/project \
                  -w /project \
                  snyk/snyk-cli:docker test
                """
            }
        }

        stage('Send results to Snyk UI') {
            steps {
                sh """
                docker run --rm --platform linux/amd64\
                  -e SNYK_TOKEN=${SNYK_TOKEN} \
                  -v \$PWD:/project \
                  -w /project \
                  snyk/snyk-cli:docker monitor
                """*/
                withCredentials([string(credentialsId: 'snyk-token', variable: 'SNYK_TOKEN')]) {
                    sh '''
                        # Authenticate Snyk CLI
                        snyk auth $SNYK_TOKEN

                        # Run a test for vulnerabilities
                        snyk test

                        # Send results to Snyk UI
                        snyk monitor
                    '''
                }
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
}
