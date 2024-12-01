FROM amazoncorretto:17

WORKDIR /app

# Copiar los archivos .jar
COPY applications/app-service/build/libs/bank_application_v1.jar app-service.jar
COPY applications/log-service/build/libs/log_application_v1.jar log-service.jar

# Crear un script start.sh dentro del Dockerfile
RUN echo '#!/bin/bash' > /start.sh && \
    echo 'java -jar app-service.jar &' >> /start.sh && \
    echo 'java -jar log-service.jar &' >> /start.sh && \
    echo 'wait' >> /start.sh

# Hacer que el script sea ejecutable
RUN chmod +x /start.sh

# Exponer puertos
EXPOSE 8080
EXPOSE 8081

# Ejecutar el script
ENTRYPOINT ["/start.sh"]
