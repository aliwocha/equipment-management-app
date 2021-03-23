# **Equipy App**

The app is used for managing equipment in the company. The frontend of the app was prepared originally by the creator of the course. He used AngularJS and RESTful architecture, and provided all endpoints necessary to implement full functionality. My task was to develop the backend side of the app in accordance with the api specification. It is assumed that the only user of the app is the admin, so there is no need of security configuration.

The main functionalities implemented:
* adding and editing users
* adding and editing equipment (assets)
* searching users and assets
* assigning / renting equipment and returning it
* showing history of user and asset assignments

## **Technology used:**
* Java 11
* SpringBoot
* Hibernate
* H2
* REST API

## **How to run:**
1. Clone the repository onto your own computer.

2. Go to the main folder of the project and run this command:

* for the Unix system:
```
./mvnw spring-boot:run
```
* for the Windows CMD:
```
mvnw.cmd spring-boot:run
```

3. Go to the following page in your browser to test the app: [http://localhost:8080/](http://localhost:8080/)

4. Go to the following page in your browser to see database tables: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

5. Use default values to log in to H2 database:

   JDBC URL: *"jdbc:h2:mem:testdb"*

   username: *"sa"*

   password: *"[blank]"*
