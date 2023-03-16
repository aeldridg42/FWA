# First Web Application

## About:
This simple web application is using `Java Servlet API`, `Spring`, `jsp` and `PostgreSQL`.

## How to compile:
Before start you should add your database credentials to [application.properties](ex02/Cinema/src/main/resources/application.properties) file.

There are 3 ways to run this app:
1) Add Tomcat 10.0 to your Intellij Idea. IDE will conigure everything. Just run app by clicking to green button.
2) Run `mvn clean package` then deploy Cinema.war to Tomcat by yourself xD (Make sure that context path "\\" is not busy!)
3) Create user "tomcat", password "tomcat" then run `mvn clean package tomcat7:deploy`

## How to run:
Open https://localhost:8080 in any browser!
