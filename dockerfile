# Étape 1 : Build de l'application
FROM maven:3.9.4-eclipse-temurin-20 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Cache des dépendances Maven + build
RUN mvn dependency:go-offline  # Optimisation cache
RUN mvn clean package -DskipTests

# Étape 2 : Runtime minimal
FROM eclipse-temurin:20-jre-alpine
WORKDIR /app

# Copier le JAR
COPY --from=build /app/target/gestion-taches-depenses-0.0.1-SNAPSHOT.jar app.jar

# Port Fly.io (détecté automatiquement)
EXPOSE 8080

# JVM optimisée pour Fly (mémoire adaptée)
ENV PORT=8080
ENV JAVA_OPTS="-Xms128m -Xmx512m -XX:+UseContainerSupport"

# Lancement avec port dynamique
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar --server.port=$PORT"]
