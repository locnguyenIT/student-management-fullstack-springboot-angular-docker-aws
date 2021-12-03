# Student Management Web Application | Fullstack | Spring Boot | Angular |

![aws](https://user-images.githubusercontent.com/86077654/144479406-566a7e3c-7cd8-4e24-82aa-d3d0ec6116f8.png)

![Screenshot 2021-12-03 011341](https://user-images.githubusercontent.com/86077654/144479534-c8610fb9-be97-44d6-b721-cec04ce55951.png)

## Overview
- This project is an web application fullstack based on Spring Boot & Angular with Spring Boot for backend and Angular for frontend. 
- Basically, on the backend connect to MySQL database and use Spring Data JPA to generates table in database and from that i have ER Diagram, you can see it below description. On the frontend, build UI, interface,  component, service, router and make REST HTTP. 
- After I finished building both frontend and backend then i use frontend-maven-plugin and maven-resources-plugin from maven to package frontend & backend to a single jar. 
- Next i use jib-maven-plugin to containerize project then build image and push this image into docker hub and create docker-compose.yml to run docker container. 
- Finally, use CI/CD with Github Action to automatic deploy project into AWS with AWS RDS to manage database and AWS Elastic Beanstalk (Docker platform) to hold application then upload docker-compose.yml on AWS Elastic Beanstalk and from that i have a new application version.

## Process
![process-build-finish](https://user-images.githubusercontent.com/86077654/144560372-7dcdf4eb-f765-4d59-b368-1a6087681ce2.png)

## Application run live
-Link: http://web-app-fullstack.ap-southeast-1.elasticbeanstalk.com/

## Technology
- Spring Boot
- Angular
- Spring Data JPA
- Spring Web MVC
- MySQL
- Maven
- Docker
- AWS
- Github Action

## Frontend (client) communicate with backend (server)
![communicate](https://user-images.githubusercontent.com/86077654/141684313-71b57416-fa47-4010-9b8c-60d981a8efce.png)

## Angular
![angular](https://user-images.githubusercontent.com/86077654/139235800-ecf841b2-52b3-4211-b271-cd479941ba9e.png)

## Spring Boot
![spring-boot](https://user-images.githubusercontent.com/86077654/140656034-bb9a9e11-7b8f-4bdb-9ea6-661af5a1697c.png)

## ER Diagram
![student-management-erd](https://user-images.githubusercontent.com/86077654/140683431-fce3d5de-e51b-4b2b-877a-7e3301cec41e.png)

Good luck !!!
