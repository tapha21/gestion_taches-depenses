# Étape 1 : Build de l'application
FROM maven:3.9.4-eclipse-temurin-20 AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier les fichiers Maven
COPY pom.xml .
COPY src ./src

# Compiler le projet et créer le jar
RUN mvn clean package -DskipTests

# Étape 2 : Exécution de l'application
FROM eclipse-temurin:20-jre-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier le jar depuis l'étape de build
COPY --from=build /app/target/gestion-taches-depenses-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port sur lequel Spring Boot écoute
EXPOSE 8080

# Commande pour lancer l'application
ENTRYPOINT ["java","-jar","app.jar"]
