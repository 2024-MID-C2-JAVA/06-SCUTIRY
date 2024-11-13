FROM amazoncorretto:17
WORKDIR /app
COPY applications/app-service/build/libs/bank_application_v1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]