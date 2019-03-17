# Required Technologies
1. mvn
2. Java 8.x - chosen for some of its appealing functionality like streams, functions and method references.
3. Developed with IDEA Intellij - chosen as my IDE of choice.
4. Spring Boot 2.0.3 - Please see requirements for this version of Spring Boot
https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Release-Notes#third-party-library-upgrades


# Dictionary

The sample dictionary contains the following words

"GEEKS", "FOR", "QUIZ", "GO"

# Development and Execution

This section describes the development and execution environment and steps.  

## Build and Run Unit Test

```
./mvnw clean package
```

## Build and Run Test

```
./mvnw clean package
```

## Run Service

```
./mvnw clean package && java -jar target/boggle-app-0.0.1-SNAPSHOT.jar
```

# Services and Example

This section demonstrate call to invoke the services the application provides. 

## Post Board

```
curl -X POST -H "Content-Type: application/json" -d '{"board":[["G","I","Z"],["U","E","K"],["Q","S","E"]]}' http://localhost:8080/boggle/sample
```







