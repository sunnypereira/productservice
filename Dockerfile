
FROM eclipse-temurin:17-jre-alpine
RUN mkdir -p /appdir
WORKDIR /appdir
COPY target/*.jar app.jar
RUN addgroup -S appuser && adduser -S appuser -G appuser
USER appuser
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
