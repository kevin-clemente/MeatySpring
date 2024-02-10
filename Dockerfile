FROM amazoncorretto:17.0.8-alpine3.18
EXPOSE 8080
ADD target/Meaty-0.0.1-SNAPSHOT.jar Meaty-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Meaty-0.0.1-SNAPSHOT.jar"]