Spring Web Test
============================

Spring Web with Security, JPA AND Mustache

# Spring Boot
* Spring Boot 1.5.14
* Gradle

# Java Dependencies
* org.springframework.boot:spring-boot-starter-web
* org.springframework.boot:spring-boot-starter-data-jpa
* org.springframework.boot:spring-boot-starter-security
* org.springframework.boot:spring-boot-starter-mustache

* org.apache.commons:commons-lang3:3.7
* org.apache.commons:commons-collections4:4.1
* org.apache.httpcomponents:httpclient:4.5.2
* com.google.guava:guava:14.0

# UI Dependencies
* jquery:2.2.1
* jquery-ui:1.11.4
* bootstrap:3.3.6
* mustache:2.3.0
* bootpag:1.0.7

# Setting
* Create DB
run sql file
src/sql/v1_create-database.sql
src/sql/v2_create-table.sql

* Edit properties
src/main/java/resources/application.properties
```
appkey.kakao={user App Key}
```

# How to start
* Run Confiquration
```
Main Class : com.example.test.TestApplication
```

* STart
```
localhost:8080
```

* Login
```
ID : test
PW : test
```
