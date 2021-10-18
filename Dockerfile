#Image of the application execution package in question
FROM openjdk:8-jdk-slim
FROM maven:3.2-jdk-8

#Creating application directory
RUN mkdir /app

#Creating application directory
WORKDIR /app

#Copy the .jar to the working directory
COPY ${JAR_FILE} app.jar

#Port configuration
EXPOSE 8080:8080

#Command to run the application
CMD ["java","-jar","spring-security-jwt.jar"]
