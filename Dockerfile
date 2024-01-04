FROM maven:3.9.5-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn install clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/todo-list-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "todo-list-0.0.1-SNAPSHOT.jar"]