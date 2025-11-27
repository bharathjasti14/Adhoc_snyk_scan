pipeline {
    agent any

    environment {
        SNYK_TOKEN = credentials('snyk-token')  // store token in Jenkins credentials
    }
    stages {
        stage('Run Python script') {
          steps {
            //running the python script
            sh 'python3 simple-addition.py'
          }
        }
        /*stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Snyk Test') {
            steps {
                sh """
                docker run --rm \
                  -e SNYK_TOKEN=${SNYK_TOKEN} \
                  -v \$PWD:/project \
                  -w /project \
                  snyk/snyk:latest test
                """
            }
        }

        stage('Send results to Snyk UI') {
            steps {
                sh """
                docker run --rm \
                  -e SNYK_TOKEN=${SNYK_TOKEN} \
                  -v \$PWD:/project \
                  -w /project \
                  snyk/snyk:latest monitor
                """
            }
        }*/
    }
}
