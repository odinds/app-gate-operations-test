# app-gate-operations-test

##Project(Gradle)

#Build
Open comand Line and execute
	>cd operarions
	>gradlew build
	
#Tests
	>cd operarions
	>gradlew test
	
	
##Docker

#Docker build
	docker build -t appgate/operations .
	
#Docker run

	docker run -p 8080:8080 appgate/operations
