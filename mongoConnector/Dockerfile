FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/mongoConnector-0.0.1-SNAPSHOT.jar saleApi.jar
ENTRYPOINT ["java","-jar","/saleApi.jar"]