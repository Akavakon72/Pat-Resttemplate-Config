FROM openjdk:11-jdk
VOLUME /tmp
COPY target/Pat-Resttemplate-Config-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
