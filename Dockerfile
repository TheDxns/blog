FROM openjdk:8u191-jdk-alpine3.9
ADD target/blog-0.1.0.jar .
EXPOSE 8080
CMD java -jar -Dspring.profiles.active=prod blog-0.1.0.jar