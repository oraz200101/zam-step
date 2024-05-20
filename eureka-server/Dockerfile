# Stage 1: Build the application
FROM gradle:8.5-jdk17 AS build
WORKDIR /app

COPY . .
RUN gradle build --no-daemon

# Stage 2: Create the final image
FROM openjdk:17
VOLUME /tmp

RUN mkdir -p /app/files
COPY --from=build /app/build/libs/eureka-server-0.0.1-SNAPSHOT.jar /eureka-server-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/eureka-server-0.0.1-SNAPSHOT.jar"]
EXPOSE 8761