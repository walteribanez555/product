# Stage 1: Build the application using Maven with JDK 17
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app
RUN git clone https://github.com/walteribanez555/product.git .

RUN mvn clean package -DskipTests

# Stage 2: Run the application using JDK 17
FROM openjdk:17-jdk

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 80

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]