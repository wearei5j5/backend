# syntax=docker/dockerfile:experimental
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=develop","/app.jar"]