# Welcome to kneotrino github project

Springboot microservice for employee database

You can change the port configuration in appplication.properties

## Before Run

* Please make sure your connection to MYSQL runs smoothly
* Create database/schema with name "office_data" (Note Springboot will automatically create the table within selected
  schema)

## Default Value

```
server.port=9100
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/office_data
spring.datasource.username=root
spring.datasource.password=admin
spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
spring.jpa.hibernate.ddl-auto=update

# swagger config
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

## Front-End Test

```
http://[URL]:[PORT]/swagger-ui.index.html
```

### Example

```
http://127.0.0.1:9100/swagger-ui.index.html
http://localhost:9100/swagger-ui.index.html
```
