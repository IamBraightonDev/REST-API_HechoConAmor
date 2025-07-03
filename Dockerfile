FROM openjdk:17-jdk-slim
COPY target/hechoconamor-api-0.0.1.jar /hechoconamor-api.jar
EXPOSE 3304
ENTRYPOINT ["java", "-jar", "/hechoconamor-api.jar"]