# Sakila-Api
> Sakila is a sample database for MySQL that is commonly used for testing and learning purposes. It includes a fictional DVD rental store scenario with a database schema that contains various tables, such as customer, film, inventory, and payment, among others.

  * Many to many relationships
  * Primary keys are called [tablename]_[id]
  * Foreign keys are called like their referenced primary key, if possible. This allows for using JOIN .. USING syntax where supported

## Overview:
> The purpose of this project is to build an API for the Sakila Sample Database in MySQL. The goal of the API is to provide a simple and easy-to-use interface for accessing and managing the data stored in the Sakila database. The API will allow users to perform CRUD (Create, Read, Update, Delete) operations on various tables in the database, such as customer, film, inventory, actor, and more.

## Documentation
[Postman Sakila API](https://documenter.getpostman.com/view/14388106/2s93Y2ShHS)

## Technologies Used:

  * Java: v17
  * Apache Tomcat: v10.1.7 
  * Apache Maven: v3.3
  * MySQL: v8.0.32 Make sure you choose to install the Sakila Sample database while installing.
  * WS-API(SOAP): v4.0
  * JAX-RS (Jersey)API: v3.1.1
  * Mapstruct: v1.5.3
  * Lombok: v1.18
  * Jakarta persistance (Hibernate): v6.1


  
  * And Finally you need to run command
  ```bash
    mvn clean install tomcat7:deploy
```





