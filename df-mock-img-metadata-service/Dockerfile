FROM openjdk:21
WORKDIR /app
COPY build/libs/*.jar app.jar

# Expose the Spring Boot application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]