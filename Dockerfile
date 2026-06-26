# ==========================
# Etapa 1: Compilar la aplicación
# ==========================
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar archivos de Maven
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
COPY mvnw.cmd .

# Dar permisos al wrapper
RUN chmod +x mvnw

# Descargar dependencias
RUN ./mvnw dependency:go-offline

# Copiar el código fuente
COPY src ./src

# Compilar la aplicación
RUN ./mvnw clean package -DskipTests

# ==========================
# Etapa 2: Imagen final
# ==========================
FROM amazoncorretto:17-alpine-jdk

WORKDIR /app

# Copiar el JAR generado por Spring Boot
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]