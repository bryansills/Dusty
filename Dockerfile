FROM openjdk:8-alpine AS BUILD_JAR
WORKDIR /usr/app/
COPY . .
COPY ./app/gradle.properties.example ./app/gradle.properties
COPY ./server/src/main/java/ninja/bryansills/dusty/server/BuildConfig.kt.example ./server/src/main/java/ninja/bryansills/dusty/server/BuildConfig.kt
RUN ./gradlew clean :server:build

FROM openjdk:8-jre-alpine
RUN mkdir /app
COPY --from=BUILD_JAR /usr/app/server/build/libs/dusty-server.jar /app/dusty-server.jar
WORKDIR /app
CMD ["java", "-server", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-XX:InitialRAMFraction=2", "-XX:MinRAMFraction=2", "-XX:MaxRAMFraction=2", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=100", "-XX:+UseStringDeduplication", "-jar", "dusty-server.jar"]
