FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/taller_app-0.0.1-SNAPSHOT.jar /app/taller_app.jar

ENTRYPOINT ["java", "-jar", "taller_app.jar"]

