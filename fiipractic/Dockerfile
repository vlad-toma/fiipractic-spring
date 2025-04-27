FROM bellsoft/liberica-openjdk-alpine:21 AS builder
WORKDIR /fiipractic
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY ./src ./src
RUN ./mvnw clean install -DskipTests

FROM bellsoft/liberica-openjre-alpine:21 AS runner
WORKDIR /fiipractic
EXPOSE 8081
COPY --from=builder /fiipractic/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]