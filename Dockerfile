FROM java:8-jdk-alpine
COPY ./target/microservice-user.0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch location-1.jar'
ENTRYPOINT ["java","-jar","microservice-user.0.0.1-SNAPSHOT.jar"]
