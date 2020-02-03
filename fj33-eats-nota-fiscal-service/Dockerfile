FROM openjdk:8-jre-alpine

WORKDIR /app

EXPOSE 8083

ADD target/eats-nota-fiscal-service.jar .

CMD ["java", "-jar", "eats-nota-fiscal-service.jar"]