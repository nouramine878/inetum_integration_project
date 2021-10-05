FROM openjdk:11-jre-slim as builder
EXPOSE  5432
ADD target/inetum_integration_docker.jar inetum_integration_docker.jar
ENTRYPOINT ["java","-jar","/inetum_integration_docker.jar"]