FROM maven:3.9.9-amazoncorretto-17-alpine
COPY . /trade-market
WORKDIR /trade-market
RUN mvn package -DskipTests
ENTRYPOINT ["java","-jar","/trade-market/target/trade-market.jar"]