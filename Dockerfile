FROM adoptopenjdk/openjdk11:ubi

EXPOSE 5500

ADD target/MoneyTransferService-0.0.1-SNAPSHOT.jar myapp.jar

ENTRYPOINT ["java", "-jar", "/myapp.jar"]