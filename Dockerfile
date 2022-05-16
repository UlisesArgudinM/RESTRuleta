FROM openjdk:11
VOLUMEN /tmp
EXPOSE 8082
ADD ./target/RESTRuleta-1.0 jar rest-ruleta.jar
ENTRYPOINT ["java", "-jar", "/rest-ruleta.jar"]