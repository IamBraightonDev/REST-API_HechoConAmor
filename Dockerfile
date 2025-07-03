# Etapa de compilación
FROM maven:3.9.6-eclipse-temurin-17 as builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jdk-slim
COPY --from=builder /app/target/hechoconamor-api-0.0.1.jar /hechoconamor-api.jar
EXPOSE 3304
ENTRYPOINT ["java", "-jar", "/hechoconamor-api.jar"]