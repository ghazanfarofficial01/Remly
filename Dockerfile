# Stage 1: Build the application using Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy the pom.xml and download dependencies for caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and package the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image using a slim JDK image
FROM openjdk:22
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/Remly-1.0.0.jar .

# Expose port 8080 (or use the PORT environment variable if provided)
EXPOSE 8080

# Use the PORT variable if available, defaulting to 8080 for local testing
ENTRYPOINT ["java", "-jar", "Remly-1.0.0.jar"]


#FROM openjdk:22
#WORKDIR /app
#COPY target/Remly-1.0.0.jar Remly-1.0.0.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "Remly-1.0.0.jar"]
