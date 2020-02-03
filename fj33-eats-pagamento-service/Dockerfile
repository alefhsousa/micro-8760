FROM openjdk:8-jre-alpine

WORKDIR /app

EXPOSE 8081

ADD target/eats-pagamento-servicer.jar .

CMD ["java", "-jar", "eats-pagamento-service.jar"]