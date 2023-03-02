FROM openjdk:17-alpine
ADD target/*.jar app.jar
CMD ["java","-jar","app.jar"]
EXPOSE 8080





