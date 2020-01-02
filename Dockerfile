FROM openjdk:8-jre-alpine AS BUILD_JAR
WORKDIR /usr/app/
COPY . .
RUN ./gradlew clean :server:build

FROM openjdk:8-jre-alpine
ENV JAR=dusty-server.jar
RUN mkdir /app
COPY --from=BUILD_JAR /usr/app/server/build/libs/$JAR /app/$JAR
WORKDIR /app
CMD ["java", "-server", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-XX:InitialRAMFraction=2", "-XX:MinRAMFraction=2", "-XX:MaxRAMFraction=2", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=100", "-XX:+UseStringDeduplication", "-jar", "$JAR"]
