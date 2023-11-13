FROM gradle:8.4.0-jdk17-alpine AS BUILD

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle build --no-daemon

FROM registry.access.redhat.com/ubi8/openjdk-17:1.17

ENV LANGUAGE='en_US:en'

# We make four distinct layers so if there are application changes the library layers can be re-used
COPY --from=build --chown=185 /home/gradle/src/build/quarkus-app/lib/ /deployments/lib/
COPY --from=build --chown=185 /home/gradle/src/build/quarkus-app/*.jar /deployments/
COPY --from=build --chown=185 home/gradle/src/build/quarkus-app/app/ /deployments/app/
COPY --from=build --chown=185 home/gradle/src/build/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185
ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]

