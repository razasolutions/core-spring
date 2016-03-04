# Raza Solutions. We dev it.

# Spring Boot Web Application

## Core Spring Boot for Java Web Applications

### This core was originally structured to be maintained with the following environment:
###  :: Eclipse Mars
###  :: Spring Tool Suite (Eclipse Plug-in)
###  :: Tomcat V8
###  :: JAutodoc (Eclipse Plug-in)
###  :: Lombok (Double-click lombok.jar after a mvn install and go to clean the projects)

## Instructions for use

### 1) First of all, configure your Eclipse environment with all the required plug-ins (see previous section);
### 2) Import this project to your workspace;
### 3) Hit ALT+F5 and Update the Maven Project;
### 4) Go to "Servers" and set the project for deployment on Tomcat;
### 5) Remember to configure the server.xml Tomcat configuration file: the <Context/> tag has an attribute called "path", which describes where the application starts responding from. It is a Raza pattern to start with path="/"
### 6) Hit Ctrl+Shift+R and open up application.properties. This is Spring boot default file for auto-configuration override. There, the datasource is defined. Remember to point those configurations to your MySQL database.
### 7) Build the project and start the Tomcat server.
### 8) Access your application by connecting to http://localhost:8080/ or http://localhost:8080/whatever-path-you-configured-in-server-xml
