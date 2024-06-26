FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/taller_app-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} taller_app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "taller_app.jar"]