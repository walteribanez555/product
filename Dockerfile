# Stage 1: Build the application using Maven with JDK 17
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

# Copy the project files into the container instead of cloning
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application using JDK 17
FROM openjdk:17-jdk

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Expose port 80 for the application
EXPOSE 80

# Run the Spring Boot application on port 80
CMD ["java", "-jar", "app.jar", "--server.port=80"]