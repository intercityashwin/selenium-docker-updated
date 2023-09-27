pipeline{

	agent any

	parameters {
      choice choices: ['chrome', 'firefox'], description: 'Select the browser', name: 'BROWSER'
    }
	
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
                bat "docker-compose -f grid.yaml up --scale ${params.BROWSER}=2 -d"
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