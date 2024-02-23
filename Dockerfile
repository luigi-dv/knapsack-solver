# Use the official maven/Java 21 image to create a build artifact.
FROM maven:3.9.6-eclipse-temurin-21 as builder

LABEL authors="Luigelo Davila"

# Set the working directory in the image to /app
WORKDIR /app

# Copy the pom.xml file to our app directory
COPY pom.xml .

# Download all required dependencies into one layer
RUN mvn dependency:go-offline -B

# Copy the rest of the code
COPY src /app/src

# Build the project
RUN mvn clean package