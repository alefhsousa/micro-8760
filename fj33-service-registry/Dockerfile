FROM openjdk:8-jre-alpine

WORKDIR /app

EXPOSE 8761

ADD target/service-registry.jar .

CMD ["java", "-jar", "service-registry.jar"]