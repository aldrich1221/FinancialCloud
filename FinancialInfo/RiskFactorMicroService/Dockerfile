

# Use the official Maven image as the base image for building the application
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and the source code
COPY pom.xml /app
COPY src /app/src

# Run the Maven package command
RUN mvn package

# Use a smaller JDK base image for running the application
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged jar from the build stage
COPY --from=build /app/target/RiskFactorMicroService-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application will run on
EXPOSE 1111
EXPOSE 1112

# Set the entry point for the container to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]