FROM amazoncorretto:17
LABEL authors="selcukdurukan"
MAINTAINER selcukdurukan
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
CMD apt-get update -y
ENTRYPOINT ["java", "-jar", "/application.jar", "-Xmx2048M"]