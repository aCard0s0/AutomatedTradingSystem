# Stage 1: Build the application
FROM maven:3.9.9-amazoncorretto-21 AS build

# Set the working directory in the container
WORKDIR /market-watcher

# Copy the source code into the container
COPY src /market-watcher/src
COPY pom.xml /market-watcher
COPY src/main/resources/application.properties /market-watcher/application.properties
COPY jvm/MANIFEST.MF /market-watcher/jvm/MANIFEST.MF
COPY settings.xml /root/.m2/settings.xml

# Package the application
RUN mvn -X clean package -DskipTests

# Stage 2: Run the application
FROM amazoncorretto:21

# Set the working directory in the container
WORKDIR /market-watcher

# Copy the jar file from the build stage
COPY --from=build /market-watcher/target/market-watcher-service-0.0.3.jar /market-watcher/market-watcher-service.jar

RUN ls -l
# Copy the application.yml file into the container
#COPY application.yml /market-watcher/application.yml


# Set environment variables for custom memory settings
#ENV JAVA_OPTS="-Xms512m -Xmx1024m"
#ENV SERVER_PORT=8082

# Make port 8081 available to the world outside this container
EXPOSE ${SERVER_PORT}

# Run the jar file with logging and custom memory settings
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar market-watcher-service.jar", "--spring.config.location=file:/market-watcher/application.properties"]
# --spring.config.location=file:/app/application.yml > /market-watcher/logs/cmcfeed-service.log 2>&1