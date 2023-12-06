
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

### MySQL
Create and run MySQL container
```bash
docker run --detach --name ats-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=atsdb -e MYSQL_USER=ats_user -e MYSQL_PASSWORD=ats_pass -d mysql:8.1
```

##### Interact with Database (link to ats-mysql container) with mysql client
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