from openjdk:8-jre-alpine

workdir /app

expose 8080

ADD eats-application/target/eats-application.jar .

CMD ["java", "-jar", "eats-application.jar"]