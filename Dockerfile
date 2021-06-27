FROM java:8
ADD build/libs/*.jar simple-project.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "simple-project.jar"]