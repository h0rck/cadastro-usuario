FROM openjdk:24-jdk-slim AS dev

WORKDIR /app

RUN apt-get update && apt-get install -y maven

COPY pom.xml ./
CMD ["mvn", "spring-boot:run"]
