FROM openjdk:8
EXPOSE 8080
ADD target/microservice-user-0.0.1-SNAPSHOT.jar microservice-user-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","microservice-user-0.0.1-SNAPSHOT.jar"]
