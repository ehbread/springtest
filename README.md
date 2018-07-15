Spring Web Test
============================

Spring Web with Security, JPA and Mustache

# Project 기능
Login후 `카카오 책 검색` API 통신를 통한 `검색어`를 통해 책 검색 결과를 표시, 결과 책 리스트에서 `Bookmark` 가능

* `검색어` : 검색된 검색어는 사용자별 DB에 저장
* `Bookmark` : 결과 책 리스트에서 Bookmark를 하면 사용자별 DB에 저장
* `Layout` : Mustache Layout Header, Footer
* `Login, Logout` : Spring Security 이용

* `카카오 책 검색` : https://developers.kakao.com/docs/restapi/search#%EC%B1%85-%EA%B2%80%EC%83%89

# Project Page
* Book Search Page
```
- 검색 리스트 Pagination
- 검색 리스트 Sort(최신순 등)
- 책 리스트에서 Bookmark 추가
- 타이틀 클릭시 레이어 팝업으로 책 상세정보 표시
```
* Query History List Page
```
- 검색어 History 리스트 Pagination
```
* Bookmark List Page
```
- 검색 리스트 Pagination
- 검색 리스트 Sort(타이틀, 추가일 등)
- Bookmark 리스트에서 Bookmark 삭제
```



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

# 설정
* Create DB
```
MySQL 사용
run sql file
src/sql/v1_create-database.sql
src/sql/v2_create-table.sql
```

* Edit properties
```
src/main/java/resources/application.properties
appkey.kakao={user App Key}
```

# 시작
* Run Configuration
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
