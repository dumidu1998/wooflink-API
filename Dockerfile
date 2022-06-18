FROM openjdk:11
ADD target/wooflink.jar wooflink.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","wooflink.jar"]