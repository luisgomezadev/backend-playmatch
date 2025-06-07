# Fase de construcción
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Fase de ejecución
FROM amazoncorretto:17-alpine-jdk
COPY --from=builder /app/target/PlayMatch-0.0.1-SNAPSHOT.jar /api-v1.jar
ENTRYPOINT ["java", "-jar", "/api-v1.jar"]