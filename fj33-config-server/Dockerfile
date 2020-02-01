FROM openjdk:8-jre-alpine

WORKDIR /app

EXPOSE 8888

ADD target/config-server.jar .

CMD ["java", "-jar", "config-server.jar"]