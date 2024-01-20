FROM openjdk:21

WORKDIR /
ADD target/AutomatedTradingSystem-0.0.1-SNAPSHOT.jar //
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "AutomatedTradingSystem-0.0.1-SNAPSHOT.jar"]
