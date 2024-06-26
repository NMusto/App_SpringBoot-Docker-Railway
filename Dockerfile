FROM openjdk:17-jdk-slim AS build

COPY . /app
WORKDIR /app

RUN chmod +x mvnw
RUN ./mvnw package -DskipTests
RUN mv -f target/*.jar taller_app.jar


ARG PORT
ENV PORT=${PORT}

COPY --from=build /app/taller_app.jar .

RUN useradd runtime
USER runtime

ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "taller_app.jar"]

