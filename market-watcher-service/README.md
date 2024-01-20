This application is a simple market data watcher that allows to monitor the candles of a given crypto pair in a given timeframe.

### Dependencies
- Java 21.0.0
- Maven 
- Docker
- PostgreSQL

### Java
```bash
java -jar market-watcher-service-0.0.1-SNAPSHOT.jar
```

### Docker
Build Image
```bash 
docker build -t ats .
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

## PostgreSQL
Download image
```bash
docker pull postgres:16.1
```
Create and run PostgreSQL container
```bash
docker run --name ats-postgres -e POSTGRES_USER=ats_postgres_user -e POSTGRES_PASSWORD=ats_postgres_pass -e POSTGRES_DB=marketdatadb -p 5432:5432 -d postgres:16.1
```
Interact with PostgreSQL Database (link to my-postgres container) with psql client:
```bash
docker exec -it ats-postgres psql -U ats_postgres_user -W marketdatadb
```
PostgreSQL quick reference:
```bash
\l # list databases
\c marketdatadb # connect to database
\dt # list tables
\dt+ your_table # list table details
CREATE TABLE your_table (
    id SERIAL PRIMARY KEY,
    key VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL
); # create table
\q # quit
select * from your_table;
```

## API Endpoints

### Market Endpoints
| HTTP Verbs | Endpoints                       | Action                                                                                                            |
|------------|---------------------------------|-------------------------------------------------------------------------------------------------------------------|
| POST       | /api/v0/markets                 | Add a list of markets to the DB. It will not start any task with the created market.                              |
| GET        | /api/v0/markets                 | Retrieves the list of markets details according with the parameters: _exchange_, _pair_, _timeframe_ and _active_ |
| GET        | /api/v0/markets/{id}            | Retrieves the market details for the given id.                                                                    |
| PUT        | /api/v0/markets/{id}            | Update the existing market for the given id.                                                                      |
| POST       | /api/v0/markets/{id}/activate   | Sets the market active=true and starts the tasks if param task=start                                              |
| POST       | /api/v0/markets/{id}/deactivate | Sets the market active=true and stops the tasks if param task=stop                                                |

### Task Endpoints
| HTTP Verbs | Endpoints          | Action                                                                                                  |
|------------|--------------------|---------------------------------------------------------------------------------------------------------|
| GET        | /api/v0/task       | Retrieves the list of tasks.                                                                            |
| POST       | /api/v0/task/start | Start the task of retrieving the historic data or watch the market if updated for the given market code |
| POST       | /api/v0/task/stop  | Stops any task for the given market code.                                                               |

### JMX Operations
| Operation Name   | Description                                                                                     |
|------------------|-------------------------------------------------------------------------------------------------|
| startTask        | Starts the task of retrieving the historic data or watch the market if updated for the given id |
| stopTask         | Stops any task for the given id                                                                 |
| updateTaskCount  | Updates the number of task allowed to be executed at the same time                              |
| activateMarket   | Sets the _active=true_ for any given market id                                                  |
| deactivateMarket | Sets the _active=false_ for any given market id                                                 |

