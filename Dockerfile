FROM openjdk:22
WORKDIR /app
COPY target/Remly-1.0.0.jar Remly-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Remly-1.0.0.jar"]
