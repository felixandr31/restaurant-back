FROM gradle:6.9.1-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ls
#ENTRYPOINT ["gradle"]
#CMD ["build", "--stacktrace"]
RUN gradle build --no-daemon --stacktrace
RUN ls

FROM openjdk:8-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar


CMD ["java", "-jar","/app/spring-boot-application.jar"]
