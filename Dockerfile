FROM openjdk:17
EXPOSE 8080
ARG JAR_FILE=target/Auto-Service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} spring-service
ENTRYPOINT ["java", "-jar", "spring-service"]