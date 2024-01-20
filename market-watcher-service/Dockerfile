FROM openjdk:21

WORKDIR /
ADD target/MarketDataWatcher-0.0.1-SNAPSHOT.jar //
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "MarketDataWatcher-0.0.1-SNAPSHOT.jar"]
