FROM maven:3.5-jdk-8 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM gcr.io/distroless/java:8
COPY --from=build /usr/src/app/target/gateway-0.0.1-SNAPSHOT.jar /usr/app/gateway-0.0.1-SNAPSHOT.jar
EXPOSE 8079
ENTRYPOINT ["java","-jar","/usr/app/gateway-0.0.1-SNAPSHOT.jar"]