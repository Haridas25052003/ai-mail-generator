# Use Java 17
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy all files
COPY . .

# Build project
RUN ./mvnw clean install

# Run app
CMD ["java", "-jar", "target/ai-job-mail-0.0.1-SNAPSHOT.jar"]