pipeline {
  agent any

  triggers{
    githubPush()
  }
  stages {
    stage('Run Python script') {
      steps {
        //running the python script
        sh 'python3 simple-addition.py'
      }
    }
  }
}
