# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set work directory
WORKDIR /app

# Copy the Kotlin Spring Boot JAR (replace with your actual JAR name)
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

# Install curl and unzip
RUN apt-get update && apt-get install -y curl unzip && rm -rf /var/lib/apt/lists/*

# Download and unzip New Relic Java agent
RUN curl -L https://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip -o /tmp/newrelic-java.zip \
    && unzip /tmp/newrelic-java.zip -d /opt \
    && rm /tmp/newrelic-java.zip

# Set New Relic environment variables
ENV NEW_RELIC_LICENSE_KEY=eu01xx577cc0f7d76378de40516191a3FFFFNRAL
ENV NEW_RELIC_APP_NAME=HelloWorldApp
ENV JAVA_TOOL_OPTIONS="-javaagent:/opt/newrelic/newrelic.jar"

# Expose the port your Spring Boot app uses
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
