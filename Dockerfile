﻿FROM openjdk:17-jdk

COPY target/Notification-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
