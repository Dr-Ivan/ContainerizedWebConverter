FROM eclipse-temurin:21-jre
WORKDIR /converter
COPY ./target/*.jar converter.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "converter.jar" ]