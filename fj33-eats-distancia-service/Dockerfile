FROM openjdk:8-jre-alpine

WORKDIR /app

EXPOSE 8082

ADD target/eats-distancia-service.jar .

CMD ["java", "-jar", "eats-distancia-service.jar"]