pipeline{
    agent any
    tools {
        jdk 'jdk-25'
        gradle 'gradle-latest'
    }
    environment{
        DOCKER_IMAGE = 'beyzakomis/swe304-backend'
    }
    stages{
        stage('Stage 1 : Clone GitHub Repo'){
            steps{
                checkout scm
            }
        }
        stage('Stage 2 : Build Application'){
            steps{
                dir('backend'){
                gradle tasks:'clean build -x test'
            }
        } 
        }
        stage('Stage 3 : Docker Build and Push'){
            steps{
                script{
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-creds') {
                        def app = docker.build("${DOCKER_IMAGE}:latest","./backend")
                        app.push()
                    }
                }
            }
        }
        stage('Stage 4 : Cluster Deployment') {
            steps {
                withCredentials([file(credentialsId: 'k8s-config', variable: 'KUBECONFIG')]) {
                    sh "kubectl apply -f deployment.yaml"
                    sh "kubectl apply -f services.yaml"
                    sh "kubectl rollout restart deployment/spring-backend-deployment"
                }
            }
        }   
        
    }

}