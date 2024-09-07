# Base image with Java
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the build JAR file to the container
COPY build/libs/inventory-service-0.0.1-SNAPSHOT.jar /app/inventory-service.jar

# Expose the port that the service runs on
EXPOSE 8081

# Command to run the app
ENTRYPOINT ["java", "-jar", "/app/inventory-service.jar"]
