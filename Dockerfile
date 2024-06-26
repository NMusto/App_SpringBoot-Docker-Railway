FROM openjdk:17-jdk-slim

EXPOSE 8080

WORKDIR /app

COPY target/taller_app-0.0.1-SNAPSHOT.jar app/taller_app.jar

ENTRYPOINT ["java", "-jar", "/app/taller_app.jar"]