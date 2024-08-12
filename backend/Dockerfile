FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
ENV SCOPE="dev"
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]