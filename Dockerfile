FROM openjdk:24

COPY target/ProductService_27_08-0.0.1-SNAPSHOT.jar ps.jar

# java -jar ps.jar
ENTRYPOINT ["java", "-jar", "ps.jar"]

#try to set the environment variables in dockerfile.
#try to install other dependencies in dockerfile.