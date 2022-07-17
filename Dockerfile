# BUILDER IMAGE
FROM openjdk:11 as Builder

COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY .env .
COPY gradle gradle
COPY src src

RUN chmod +x ./gradlew
RUN ./gradlew clean
RUN ./gradlew --stop
RUN ./gradlew build -x test

# BASE IMAGE
FROM openjdk:11-jre-slim
COPY --from=Builder build/libs/LakkuLakku-*SNAPSHOT.jar app.jar
EXPOSE 8080

RUN chmod +x app.jar