FROM openjdk:8-jre-alpine

WORKDIR /app

EXPOSE 9999

ADD target/api-gateway.jar .

CMD ["java", "-jar", "api-gateway.jar"]