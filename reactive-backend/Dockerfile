FROM openjdk:17-jdk-alpine
EXPOSE 8090
VOLUME /tmp
COPY target/*.jar reactive-spring-app.jar
ENTRYPOINT ["java","-jar","/reactive-spring-app.jar"]