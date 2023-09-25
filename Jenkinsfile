pipeline{

	agent any
	
	stages {
		
		stage('Build Jar'){
			
			steps{
				bat "mvn clean package -DskipTests"
			}
		}


		stage('Build Image'){
			
			steps{
				bat "docker build -t intercityashwin/selenium ."
			}
		}

		stage('Run Test'){

            steps{
                bat "docker compose up"
            }
        }

        stage('Bring Grid Down'){

            steps{
                  bat "docker compose down"
              }
        }

		stage('Push Image'){
			environment{
			    DOCKER_HUB = credentials('dokcerhub-cred')
			}

			steps{
                bat 'docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}'
				bat "docker push intercityashwin/selenium"
			}
		}
	
	}
	post {
	    always{
	        bat "docker logout"
	    }
	}
}