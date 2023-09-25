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
                bat "docker-compose up"
            }
        }

        stage('Bring Grid Down'){

            steps{
                  bat "docker-compose down"
              }
        }
	}
}