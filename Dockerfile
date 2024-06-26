FROM openjdk:17-jdk-alpine as build

EXPOSE 8080

COPY . /app
WORKDIR /app

RUN chmod +x mvnw
RUN ./mvnw package -DskipTests
RUN mv -f target/*.jar taller_app.jar

COPY --from=build /app/taller_app.jar .

RUN useradd runtime
USER runtime

ENTRYPOINT ["java", "-jar", "taller_app.jar"]