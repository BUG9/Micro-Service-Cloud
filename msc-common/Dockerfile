FROM java:8
VOLUME /tmp
ADD  micro-service-cloud-common-1.0.0-SNAPSHOT.jar app.jar

RUN bash -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]