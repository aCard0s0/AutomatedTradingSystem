### Dependencies
- Java 21.0.0
- Maven 
- Docker
- MySQL 8.1
- MongoDB 7.0.4

An MySql server is required to run the application. The application will create the database and tables on startup.
See below for instructions on how to run a MySql server in a docker container.

### Docker

Build Image
```bash 
docker build -t MarketDataWatcher
 .
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

### MySQL
Download image
```bash
docker pull mysql:8.1
```
Create and run MySQL container
```bash
docker run --detach --name ats-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=atsdb -e MYSQL_USER=ats_user -e MYSQL_PASSWORD=ats_pass -d mysql:8.1
```

Interact with MySQL Database (link to ats-mysql container) with mysql client
```bash
docker run -it --link ats-mysql:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'
```

##### SQl quick reference
```sql
show databases;
create database atsdb;
drop database atsdb;
use atsdb;
show tables;
select * from atsdb.security_user;
delete from flyway_schema_history where version = '1.1';
```

## MongoDB
Download image
```bash
docker pull  mongo:7.0.4
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


