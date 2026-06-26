# --- Etapa 1: Compilación ---
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copiar el archivo de configuración de Maven y el código fuente
COPY pom.xml .
COPY src ./src

# Compilar el proyecto omitiendo las pruebas unitarias para acelerar el despliegue
RUN mvn clean package -DskipTests

# --- Etapa 2: Imagen de ejecución ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar el JAR generado desde la etapa de compilación
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto que usará la app (Render asigna uno dinámicamente, pero es buena práctica)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]