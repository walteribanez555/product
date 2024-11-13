# Stage 1: Build the application with Maven using JDK 17
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app
RUN git clone https://github.com/walteribanez555/product.git .

RUN mvn clean package -DskipTests

# Stage 2: Deploy to Tomcat with JDK 17
FROM tomcat:10-jdk17

RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Install curl for testing
RUN apt-get update && apt-get install -y curl

EXPOSE 80

CMD ["catalina.sh", "run"]