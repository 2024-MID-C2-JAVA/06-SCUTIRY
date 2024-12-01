# Usa una imagen ligera de OpenJDK
FROM openjdk:21-jdk-slim

# Crea un directorio de trabajo
WORKDIR /app

# Copia el JAR en la imagen de Docker
COPY app_service-0.0.1-SNAPSHOT.jar /app/app.jar

# Expone el puerto que tu aplicación utiliza
EXPOSE 8080

# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]