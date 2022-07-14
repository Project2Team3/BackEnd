FROM openjdk:8-jdk-alpine

COPY /target/BackEnd.jar BackEnd.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "BackEnd.jar"]
