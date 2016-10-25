# a base image for the container, here something that has the java executable
FROM java:openjdk-8-jre
# copy the jar file inside the container to /app/withdocker.jar
COPY target/withdocker-0.1.0-SNAPSHOT-standalone.jar /app/withdocker.jar
# say the container will be exposing an app on port 8080
EXPOSE 8080
# what will be executed when you do, docker run ...
ENTRYPOINT [ "java", "-jar", "/app/withdocker.jar"]