FROM openjdk:11 as builder

COPY . ./

RUN chmod +x ./gradlew
RUN ./gradlew clean build

FROM openjdk:11.0-jre-slim

COPY --from=builder build/libs/*.jar app.jar
EXPOSE 8080

RUN chmod +x app.jar