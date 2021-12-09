#FROM gradle:7.1.0-jdk8 AS build
FROM gradle:7.1.0-jdk11 AS build
WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . .
#RUN gradle build --no-daemon
RUN gradle build --no-daemon -x spotlessGroovyGradleCheck -x spotlessJavaCheck -x spotlessSqlCheck -x spotlessXmlCheck
# RUN gradle bootBuildImage

#FROM openjdk:8-jre-alpine
FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
COPY --from=build /home/gradle/src/build/libs/encrypt-0.0.1.jar /app.jar


ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom", "-jar", "/app.jar"]

EXPOSE 8080