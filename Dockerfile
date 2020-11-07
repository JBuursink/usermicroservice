FROM openjdk:8
ADD target/microservice-user.0.0.1-SNAPSHOT.jar microservice-user.0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","microservice-user.0.0.1-SNAPSHOT.jar"]
