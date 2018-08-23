node {
    try {
        def app
        stage('Clone repository') {
            checkout scm
        }
        stage('Build Jar') {
            sh 'bash ./build.sh'
        }
        stage('Build Docker image') {
            app = docker.build("alekseysamoylov/hellojenkins", "-f hello-jenkins/Dockerfile hello-jenkins/.")
        }
        stage('Push image') {
            docker.withRegistry('https://registry.hub.docker.com', 'mydockerhub') {
                app.push("${env.BUILD_NUMBER}")
                app.push("latest")
            }
        }
    } finally {
        stage('cleanup') {
            echo "doing some cleanup..."
        }
    }
}
