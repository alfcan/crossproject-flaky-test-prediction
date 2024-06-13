FROM openjdk:17-alpine



WORKDIR /FlakinessMetricsDetector
COPY .mvn ./.mvn
COPY mvnw pom.xml ./
COPY lib ./lib
COPY src ./src


RUN mkdir -p ../sharedSpace
RUN mkdir -p ../sharedSpace/Dataset
RUN mkdir -p ../sharedSpace/MetricsDetector
RUN mkdir -p ../sharedSpace/Repository



RUN ./mvnw dependency:resolve
RUN ./mvnw package

CMD ["java", "-jar", "./target/FlakinessMetricsDetector-0.0.1-SNAPSHOT.jar"]
