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
	
This will generate a executable .jar in folder <project>/build/libs

#Run dev enviroment
Open comand line and execute the next commands
	>cd operarions
	>gradlew bootRunDev
	
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
	>docker run -p 8081:8081 appgate/operations
	
	
#Docker compose
	>docker-compose up --build
	

#Curl 
	* Get session
	>curl http://localhost:8081/startSession
	
	Output Example->0af07a00-0c9d-4049-9460-15313ed67a44
	
	* add Operand
	>curl -X POST http://localhost:8081/operations/addOperand -H "Content-Type: application/json" -H "id-session: 0af07a00-0c9d-4049-9460-15313ed67a44"  -d "{\"operand\":\"1\"}"
	
	Output Example -> OK 

	* Get Sum
	>curl localhost:8081/operations/doSum -H "id-session: 0af07a00-0c9d-4049-9460-15313ed67a44"

	* Get Multiply
	>curl localhost:8081/operations/doMultiply -H "id-session: 0af07a00-0c9d-4049-9460-15313ed67a44"
	
	* Get doSubstract
	>curl localhost:8081/operations/doSubstract -H "id-session: 0af07a00-0c9d-4049-9460-15313ed67a44"
	
	* Get doSubstract
	>curl localhost:8081/operations/doDevide -H "id-session: 0af07a00-0c9d-4049-9460-15313ed67a44"


#Corner cases	

1. Divide on Zero. thows a arithmethic exception in this case return a status 500 -> internal error



# Architecture Diagram

![Alt text](assets/architecutre Diagram.png?raw=true "AD")


# CI CD Diagram

![Alt text](assets/CI CD startegy.png?raw=true "CC")