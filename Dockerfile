FROM azul/zulu-openjdk:21

EXPOSE 8080
EXPOSE 8081

COPY  build/libs/*.jar /app/app.jar

ENV JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:MaxRAM=200M"

CMD java $JAVA_OPTS -jar /app/app.jar
