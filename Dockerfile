FROM openjdk:17-jdk-slim

EXPOSE 8080

ARG JAR_FILE=target/taller_app-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} taller_app.jar

ENTRYPOINT ["java", "-jar", "taller_app.jar"]