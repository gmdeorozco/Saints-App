FROM openjdk:latest
COPY ./target/{{ jar file name goes here }} {{ jar file name goes here }}
CMD ["java","-jar","{{ jar file name goes here }}"]