# Project Overview

The Video Game Library & Rental System is a web‐based system that supports searching, lending, and returning games across different platforms. End users may sign up, log in, have a private wishlist, and manage rentals, while staff members may authenticate, add/edit game records, and manage the catalog. It demonstrates a typical three-layer design—isolated presentation (JSP), business logic (Java servlets/DAOs), and access to the storage device using MySQL—and demonstrates best practices in using JDBC, DAO design patterns, and user authentication.

# Setup Instructions 
## System Requirements
* Java 11 & Jakarta Servlet API
* MySQL 8
* Apache Tomcat 9+

## Create db.properties file
You will need to create and modify a db.properties file in the root directory to ensure that the web application can connect to your local MySQL server instance.

Required fields include:
* driverClassName specifying a MySQL JDBC Driver,
* url specifying your database's connection URL,
* username & password specifying your DB password & username.

## Installation

Clone the repository into your local machine and then run the tomcat server. Building the project & artifact should generate a .war file, allowing you to see the website at your configured tomcat URL, using the IDE of your choice (IntelliJ/Eclipse/VSCode). 

