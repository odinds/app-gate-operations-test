# app-gate-operations-test

# Pre

* [JAVA 11](https://openjdk.java.net/projects/jdk/11/) 
* [Docker](https://www.docker.com/products/docker-desktop) 
* [Gradle 7](https://gradle.org/) 

# Build
Execute in command line
```sh
cd operarions
gradlew build
```

# Package
Execute in command line
```sh
cd operarions
gradlew bootJar
```

This will generate a executable .jar in folder <project>/build/libs

# Run dev enviroment
Execute in command line
```sh
cd operarions
gradlew bootRunDev
```
This excute in enviroment dev
	
# Tests
Execute in command line
```sh
cd operarions
gradlew test
```	

## Docker

# Docker build
Execute in command line
```sh
operarions
docker build -t appgate/operations .
```
	
# Docker run
Execute in command line

```sh
cd operarions
docker run -p 8081:8081 appgate/operations
```
	
# Docker compose (app.jar and redis)
```sh
docker-compose up --build
```


# Curl 

  * Get session

```sh
curl http://localhost:8081/startSession
0af07a00-0c9d-4049-9460-15313ed67a44
```
	
  * add Operand

```sh
curl -X POST http://localhost:8081/operations/addOperand -H "Content-Type: application/json" -H "id-session: 0af07a00-0c9d-4049-9460-15313ed67a44"  -d "{\"operand\":\"1\"}"
 OK
 ```

* Get Sum
```sh
curl localhost:8081/operations/doSum -H "id-session: 0af07a00-0c9d-4049-9460-15313ed67a44"
```

* Get Multiply
```sh
curl localhost:8081/operations/doMultiply -H "id-session: 0af07a00-0c9d-4049-9460-15313ed67a44"
```

* Get doSubstract
```sh
>curl localhost:8081/operations/doSubstract -H "id-session: 0af07a00-0c9d-4049-9460-15313ed67a44"
```

* Get doSubstract
```sh
>curl localhost:8081/operations/doDevide -H "id-session: 0af07a00-0c9d-4049-9460-15313ed67a44"
```

# Corner cases	

1. Divide on Zero. thows a arithmethic exception in this case return a status 500 -> internal error



# Architecture Diagram

[![N|Solid](https://github.com/odinds/app-gate-operations-test/blob/feature/test/assets/Diagram%20architecture.jpg)](https://github.com/odinds/app-gate-operations-test/blob/feature/test/assets/architecutre%20Diagram.png)


# CI CD Diagram

[![N|Solid](https://github.com/odinds/app-gate-operations-test/blob/feature/test/assets/CI%20CD%20startegy.png)](https://github.com/odinds/app-gate-operations-test/blob/feature/test/assets/CI%20CD%20startegy.png)
