pipeline {
    agent any

    stages {
        stage('step 1') {
            steps {
                git 'https://github.com/Refly1108/springbootdemo.git'
            }
        }
        stage('step 2') {
            steps {
                sh 'mvn --version'
            }
        }
        stage('step 3') {
            steps {
                echo 'step 3'
            }
        }
    }

}