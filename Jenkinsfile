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
	}

	post{
	    always{
	        bat "docker-compose -f grid.yaml down"
	        bat "docker-compose -f test-suites.yaml down"
	        archiveArtifacts artifacts: 'output/flight-reservation/emailable-report.html', followSymlinks: false
	        archiveArtifacts artifacts: 'output/vendor-portal/emailable-report.html', followSymlinks: false
	    }
	}
}