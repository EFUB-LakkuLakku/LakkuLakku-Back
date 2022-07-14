FROM openjdk:11 as builder

# Create working directory
RUN mkdir -p /app
WORKDIR /app
COPY . /app

RUN ./gradlew clean build

FROM openjdk:11.0-jre-slim

WORKDIR /app
COPY --from=builder /app/build/libs/core.jar /app
EXPOSE 8080

RUN chmod +x /app/core.jar