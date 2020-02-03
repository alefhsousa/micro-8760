FROM openjdk:8-jre-alpine

WORKDIR /app

EXPOSE 7777

ADD target/hystrix-dashboard.jar .

CMD ["java", "-jar", "hystrix-dashboard.jar"]