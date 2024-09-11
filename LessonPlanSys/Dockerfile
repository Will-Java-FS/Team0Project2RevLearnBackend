FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/LessonPlanSys-0.0.1-SNAPSHOT.jar lessonplansys.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar lessonplansys.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar lessonplansys.jar
