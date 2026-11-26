# ===== BUILD STAGE =====
FROM maven:3.9-eclipse-temurin-26 AS build

WORKDIR /app

# Baixa dependências primeiro para aproveitar o cache do Docker
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código-fonte e gera o .jar
COPY src ./src
RUN mvn clean package -DskipTests


# ===== RUNTIME STAGE =====
FROM eclipse-temurin:26-jre

WORKDIR /app

# Cria a pasta onde o ImageFileWriter salva as fotos enviadas
RUN mkdir -p /app/uploads

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]