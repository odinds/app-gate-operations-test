# app-gate-operations-test

##Project(Gradle)

#Build
Open comand Line and execute the next commands
	>cd operarions
	>gradlew build
	
#Package
Open comand Line and execute the next commands
	>cd operarions
	>gradlew bootJar
	
This generate a executable .jar in folder <project>/build/libs

#Package
Open comand Line and execute the next commands
	>cd operarions
	>gradlew bootJar
	
This generate a executable .jar in folder <project>/build/libs
	
#Tests
Open comand Line and execute the next commands
	>cd operarions
	>gradlew test
	
	
##Docker

#Docker build
Open comand Line and execute the next commands
	>cd operarions
	>docker build -t appgate/operations .
	
#Docker run
Open comand Line and execute
	>cd operarions
	>docker run -p 8080:8080 appgate/operations
