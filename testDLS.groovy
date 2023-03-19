pipeline {
    agent any

    stages {
        stage('step 1') {
            steps {
                echo 'step 1'
            }
        }
        stage('step 2') {
            steps {
                echo 'step 2'
            }
        }
        stage('step 3') {
            steps {
                echo 'step 3'
            }
        }
    }

}