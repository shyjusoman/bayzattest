FROM openjdk:11
LABEL maintainer ="mycom"
ADD target/java-spring-shaiju-0.0.1-SNAPSHOT.jar java-spring-shaiju-0.0.1-SNAPSHOT.jar
RUN chmod -R +x /tmp/*
ENTRYPOINT ["java","-jar","java-spring-shaiju-0.0.1-SNAPSHOT.jar"]
