FROM openjdk:15-alpine
ADD target/*.jar app.jar
CMD ["java","-jar","app.jar"]






