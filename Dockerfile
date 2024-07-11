FROM openjdk:21
EXPOSE 8085
ADD target/bank.jar bank.jar
ENTRYPOINT ["java" ,"-jar" ,"/bank.jar"]
