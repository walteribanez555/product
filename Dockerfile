# Stage 1: Build the application with Maven using JDK 17
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Clone the specified repository
RUN git clone https://github.com/walteribanez555/product.git .

# Build the project and skip tests to save time (optional)
RUN mvn clean package -DskipTests

# Stage 2: Deploy to Tomcat with JDK 17
FROM tomcat:10-jdk17

# Remove the default Tomcat webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the built WAR file from the build stage
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Expose the application's port
# EXPOSE 80
EXPOSE 80


# Start Tomcat
CMD ["catalina.sh", "run"]
