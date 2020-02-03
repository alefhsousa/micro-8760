FROM openjdk:8-jre-alpine

WORKDIR /app

EXPOSE 7776

ADD target/turbine.jar .

CMD ["java", "-jar", "turbine.jar"]