
# Sakila Api
> The purpose of this project is to build an API (REST - SOAP) for the Sakila Sample Database in MySQL. The goal of the API is to provide a simple and easy-to-use interface for accessing and managing the data stored in the Sakila database. The API will allow users to perform CRUD (Create, Read, Update, Delete) operations on various tables in the database, such as customer, film, inventory, actor, and more.


## Overview:
> Sakila is a sample database for MySQL that is commonly used for testing and learning purposes. It includes a fictional DVD rental store scenario with a database schema that contains various tables, such as customer, film, inventory, and payment, among others.
  * Many to many relationships
  * Primary keys are called [tablename]_[id]
  * Foreign keys are called like their referenced primary key, if possible. This allows for using JOIN .. USING syntax where supported

  > [Sakila Documentation](https://dev.mysql.com/doc/sakila/en/)

## Some Features

* CRUD operations (Film, Store , Category, Inventory, Customer, Staff).
* View film details.
* Get qunatity for film rentals.
* Get film customers.
* Get film actors.
* Search for film by name, language, rate, cost, length ..etc .
* View all available films in stores.
* View all rented films.
* View all unreturned films.
* Get where film available (Store, Inventory).
* Add actor to film.
* Add category to film.
* Add film to store.
* Get store details (Address, Staff, Rentals, Films). 
* Search for customer by email, name, date, ..etc .
* Get customers rented films.
* Get customer unreturned films.

## Documentation
[Postman Sakila API](https://documenter.getpostman.com/view/14388106/2s93Y2ShHS)

## Usage

## ```SOAP```
> This SOAP API only accepts HTTP POST requests but it also supports several common operations for all item types, including Add, Delete, Load, and Update. You’ll see these operations instead of the HTTP verbs GET, PUT, PATCH, and DELETE.
You can use postman to use this API or bulid your client application and then import wsdl.

[SOAP WSDL] (https://github.com/rahmamustafa/Sakila-Api/blob/main/SOAP%20wsdl)

## ```REST```
> REST requests are HTTP requests made to the the endpoint URL of Sakila REST API, which has the following format: http://{your-server}:{port-number}/sakila/api/{resource}s/. This API uses the HTTP methods GET, POST, PUT, and DELETE.
All subsequent requests the user-agent may make are discovered inside the response to each request. The media types used for these representations, and the link relations they may contain, are part of the API.


## Technologies Used:
  * Java: v17
  * Apache Tomcat: v10.1.7 
  * Apache Maven: v3.3
  * MySQL: v8.0.32 Make sure you choose to install the Sakila Sample database while installing.
  * WS-API: v4.0
  * JAX-RS API: v3.1.1
  * Jersy: v3.1
  * Mapstruct: v1.5.3
  * Lombok: v1.18
  * Jakarta persistance: v6.1
  * Hibernate core: v6.1
  * Hibernate Validator: v7.0


  
  * And Finally you need to run command
  ```bash
    mvn clean install tomcat7:deploy
```





