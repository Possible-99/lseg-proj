FROM openjdk:21
ARG JAR_FILE=target/*jar
COPY ./target/account-system.jar account-system.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/account-system.jar"]