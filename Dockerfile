# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the project and skip tests to save time (optional)
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# Set the working directory for the application
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application's port (adjust if your app uses a different port)
EXPOSE 80

# Run the application (make sure the application listens on port 80)
CMD ["java", "-jar", "/app/crud-application.jar", "--server.port=80"]
