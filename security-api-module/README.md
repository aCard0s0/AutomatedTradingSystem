This application is a simple market data watcher that allows to monitor the candles of a given crypto pair in a given timeframe.

### Dependencies
- Java 21.0.0
- Maven 
- Docker
- MongoDB



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

## MongoDB
Download image
```bash
docker pull mongo:7.0.4
```
Create and run MongoDB container
```bash
docker run --name ats-mongo -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=ats_mongo_user -e MONGO_INITDB_ROOT_PASSWORD=ats_mongo_pass -d mongo:7.0.4
```
Interact with MongoDB Database (link to ats-mongo container) with mongo client
```bash
docker exec -it ats-mongo bash
```

##### Mongo quick reference
```bash
show dbs;
use atsdb;
show collections;
db.your_collection.drop()
db.your_collection.find();
db.your_collection.insertOne({ "key": "value" })
db.your_collection.deleteOne({ "key": "value" })
db.your_collection.updateOne({ "key": "value" }, { $set: { "updated_key": "updated_value" }})
exit
```
Tip: use MongoCompass client

## PostgreSQL
Download image
```bash
docker pull postgres:16.1
```
Create and run PostgreSQL container
```bash
docker run --name ats-postgres -e POSTGRES_USER=ats_postgres_user -e POSTGRES_PASSWORD=ats_postgres_pass -e POSTGRES_DB=MarketDataDB -p 5432:5432 -d postgres:16.1
```
Interact with PostgreSQL Database (link to my-postgres container) with psql client:
```bash
docker exec -it ats-postgres psql -U ats_postgres_user -W MarketDataDB
```
PostgreSQL quick reference:
```bash
\l # list databases
\c MarketDataDB # connect to database
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
