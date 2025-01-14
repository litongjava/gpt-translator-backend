#FROM litongjava/maven:3.8.8-jdk8u391 AS builder
#WORKDIR /app

#COPY pom.xml pom.xml
#RUN mvn dependency:go-offline

#COPY src src
#RUN mvn package -DskipTests -Pproduction -Passembly -q
#RUN ls target

FROM litongjava/jre:8u391-stable-slim

WORKDIR /app

#COPY --from=builder /app/target/gpt-translator-backend-1.0.0.jar /app
COPY target/gpt-translator-backend-1.0.0.jar /app

EXPOSE 8080

CMD ["java","-jar", "/app/gpt-translator-backend-1.0.0.jar","--app.env=prod"]
