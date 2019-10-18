FROM openjdk:8-jre-alpine
RUN mkdir -p /app

# Copy jar file to app folder
COPY ./target/import-service-0.0.1-SNAPSHOT.jar /app

ENV DB_HOST $DB_HOST
ENV DB_USERNAME $DB_USERNAME
ENV DB_PASSWORD $DB_PASSWORD
ENV DB_NAME $DB_NAME
ENV API $API
ENV CONTENT_API $CONTENT_API
ENV S3_BACKUP_BUCKETNAME $S3_BACKUP_BUCKETNAME
ENV AWS_ACCESS_KEY_ID $AWS_ACCESS_KEY_ID
ENV AWS_SECRET_ACCESS_KEY $AWS_SECRET_ACCESS_KEY
ENV BASIC_AUTH_PASSWORD $BASIC_AUTH_PASSWORD
ENV BASIC_AUTH_USERNAME $BASIC_AUTH_USERNAME
ENV ONIX_SERVICE_BASIC_AUTH_PASSWORD $ONIX_SERVICE_BASIC_AUTH_PASSWORD
ENV ONIX_BUILDER_SERVICE_BASIC_AUTH_PASSWORD $ONIX_BUILDER_SERVICE_BASIC_AUTH_PASSWORD
ENV ONIX_ENV  $ONIX_ENV
ENV ONIXPROCESSOR_DOWNLOADPATH $ONIXPROCESSOR_DOWNLOADPATH
ENV API_ONLY $API_ONLY

EXPOSE 7080
#CMD ["java", $JAVA_OPTS, "-jar", "/app/import-service-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT exec java $JAVA_OPTS -jar /app/import-service-0.0.1-SNAPSHOT.jar