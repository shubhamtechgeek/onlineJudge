FROM amazoncorretto:17-alpine
WORKDIR /
COPY target/judgeService-0.0.1-SNAPSHOT.jar /judge.jar
ENTRYPOINT ["java", "-jar", "judge.jar"]