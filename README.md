## Market Data Watcher
This codebase contains several components of system with the goal to perform the following tasks:
* Fetch and save market data from several exchanges through their REST APIs and Websockets.
* Ability to calculate several indicators on the market data.
* Provide a REST API to query the market data and indicators
* Define strategies to trade on the market data.

### Components
#### Services
* [**market-watcher-service**](market-watcher-service/README.md): Fetches historic data and keep monitoring close candles from several exchanges to save them to a database.
* **application-engine-service**: Socket server that receives last prices directly from exchanges. 
#### Modules
* **security-api-module**: Contains the security API to authenticate and authorize the users.
#### Libraries
* **common-market-lib**: Contains the common classes to be used by the other modules.


### Docker

Build Image
```bash 
docker build -t MarketDataWatcher .
docker images
```
Run Container
```bash
docker run --name ats-app -p 8080:8080 -d ats
docker ps -a
docker logs ats-app
docker stop ats-app
docker rm ats-app
```

### Dependencies
- Java 21.0.0
- Maven
- Docker
