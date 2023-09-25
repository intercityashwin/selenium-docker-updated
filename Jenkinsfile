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

		stage('Bring Grid Up'){

            steps{
                bat "docker-compose -f grid.yaml up -d"
            }
        }

        stage('Run Tests'){

            steps{
                  bat "docker-compose -f test-suites.yaml up"
              }
        }

         stage('Bring Grid Dowm'){

            steps{
                  bat "docker-compose -f grid.yaml down"
               }
        }

        stage('Bring Tests Dowm'){
            steps{
                  bat "docker-compose -f test-suites.yaml down"
               }
        }
	}
}