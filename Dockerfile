FROM openjdk:11-jre-slim

# Copy the application JAR file to the container
COPY target/crud-application.jar /app/crud-application.jar

# Expose port 80 to be used by ECS
EXPOSE 80

# Run the application (make sure the application listens on port 80)
CMD ["java", "-jar", "/app/crud-application.jar", "--server.port=80"]