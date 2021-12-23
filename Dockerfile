# syntax=docker/dockerfile:1
FROM maven:3.8.1-openjdk-11 AS build
COPY src /app/src
COPY pom.xml /app
COPY mvnw /app
RUN mvn -f /app/pom.xml clean install -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app/spring-security-jwt.jar
ENTRYPOINT ["java", "-jar", "spring-security-jwt.jar"]
