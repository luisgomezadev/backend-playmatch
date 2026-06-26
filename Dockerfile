# ==========================
# Etapa 1: Compilar el proyecto
# ==========================
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
COPY mvnw.cmd .

RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean package -DskipTests

# ==========================
# Etapa 2: Imagen final
# ==========================
FROM amazoncorretto:17-alpine-jdk

WORKDIR /app

COPY --from=build /app/target/playmatch.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]