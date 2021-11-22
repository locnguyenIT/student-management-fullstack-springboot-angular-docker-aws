# Student Management Web Application | Fullstack | Spring Boot | Angular |

![index](https://user-images.githubusercontent.com/86077654/140655653-23f22cd3-8736-4524-8d5d-2a285e17b3ca.png)

![student](https://user-images.githubusercontent.com/86077654/140655717-e3d6f796-afc0-4b25-8c1d-67520171e9b6.png)

![result](https://user-images.githubusercontent.com/86077654/140655714-a84a6001-44d8-4698-b851-6fb74e02d161.png)

## Description
This project is an web application fullstack based on Spring Boot & Angular with Spring Boot for backend and Angular for frontend. Basically, on the backend connect to MySQL database and use Spring Data JPA to generates table in database and from that i have ER Diagram, you can see it below description. On the frontend, build UI, interface,  component, service, router and make REST HTTP.

I used IntelliJ IDEA for backend and Visual Studio Code for frontend. I have Dockerfile to build own image for application to run App container. When I build already done both backend, frontend then i put frontend project into backend project in IntelliJ IDEA

## Frontend (client) communicate with backend (server)
![communicate](https://user-images.githubusercontent.com/86077654/141684313-71b57416-fa47-4010-9b8c-60d981a8efce.png)

## Angular
![angular](https://user-images.githubusercontent.com/86077654/139235800-ecf841b2-52b3-4211-b271-cd479941ba9e.png)

## Spring Boot
![spring-boot](https://user-images.githubusercontent.com/86077654/140656034-bb9a9e11-7b8f-4bdb-9ea6-661af5a1697c.png)

## ER Diagram
![student-management-erd](https://user-images.githubusercontent.com/86077654/140683431-fce3d5de-e51b-4b2b-877a-7e3301cec41e.png)

## Run project
### This project has 4 ways to run but make sure you have installed
- MySQL database.
- Docker Desktop.

### Before you run project you must follow description below to easier run this application:
- Go to pom.xml file -> Maven -> Reload project
- Open the project and follow path "\src\main\resources\application.properties" to configuation application.properties to connect database. Make sure you created database "student" and change username and password with your MySQL account
- Go to Maven at the top-right corner and run command "mvn clean package" to create jar file

#### The first way(Different localhost):
Click DemoApplication and Run. After the program has finished running. Open your browser "http://localhost:8080/api/spring-boot/student"  to see API of backend.

Open terminal at "\src\frontend" run command "ng serve" then open "http://localhost:4200" in your browser to see the frontend.

Run backend first, then run frontend later.

#### The second way (App run single localhost:8080):
Open terminal at project and run command "java -jar target/demo-0.0.1-SNAPSHOT.jar" then open "http://localhost:8080" in your browser.
![8080](https://user-images.githubusercontent.com/86077654/141683190-b30b9db0-c2d5-42af-a836-121331edda34.png)

#### The third way (Run application on Docker)
Step 1: Open terminal at project and run "docker pull mysql" command to get image of MySQL.

Step 2: Run "docker build -t app-image ." to build own image of application

Step 3: Create network to MySQL Container communicate with App Container. Run "docker network create db".

Step 4: Create MySQL container & App container.
- Create "data" folder inside root project, then open terminal at data and run command below. Means is you must store the data of appicaltion because containner can die and you lost data
- Run "docker run --name mysql-db --network db -v ${PWD}:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=yourpassword -e MYSQL_DATABASE=student -d -p 3307:3306 mysql" - MySQL container
- Then back to terminal at project and run "docker run --name web-fullstack --network db -e MYSQL_HOST=mysql-db -d -p 8080:8080 app-image" - App container then open localhost:8080 to see Application.

![8080](https://user-images.githubusercontent.com/86077654/141683190-b30b9db0-c2d5-42af-a836-121331edda34.png)

Start with MySQL container first, then run App container later.

Stop App container first, then stop MySQL container later.

#### The fourth way (use Docker-compose to run container)
Make sure you created empty data folder in root project.

Open terminal at project and follow this command "docker-compose -f docker-compose.yml up" to run MySQL containner and App container then open localhost:8080 on your browser. If you remove docker-compose, follow this command "docker-compose -f docker-compose.yml down".

Good luck !!!
