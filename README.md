# Required Technologies
1. mvn
2. Java 8.x - chosen for some of its appealing functionality like streams, functions and method references. 
3. Tomcat 8.x - because of the java 8 above.
4. Developed with IDEA Intellij - chosen as my IDE of choice. 
5. Spring Boot 2.0.3 - Please see requirements for this version of Spring Boot 
https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Release-Notes#third-party-library-upgrades


# Dictionary

The sample dictionary contains the following words

"GEEKS", "FOR", "QUIZ", "GO"

# Development and Execution

This section describes the development and execution environment and steps.  

## Build and Run Unit Test

```
mvn clean install
```

## Build and Run Integration Test

```
./mvnw clean install -DskipITs=false
```

## Run Standalone

```
./mvnw clean install spring-boot:run
```

## Deploy to Tomcat

```
cp boggle-app.war [TOMCAT_WEBAPP]/boggle-app.war
```

## Start Tomcat

```
[TOMCAT_INSTALLATION_ROOT_DIR]/bin/startup.sh
```

## Stop Tomcat

```
[TOMCAT_INSTALLATION_ROOT_DIR]/bin/shutdown.sh
```


# Services and Example

This section demonstrate call to invoke the services the application provides. 

## Post Board

```
curl -X POST -H "Content-Type: application/json" -d '{"board":[["G","I","Z"],["U","E","K"],["Q","S","E"]]}' http://localhost:8080/boggle/sample
```







