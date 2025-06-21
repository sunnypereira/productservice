
FROM eclipse-temurin:17.0.15_6-jre-alpine-3.21@sha256:b10e4fda9d71b3819a91fbb0dbb28512edbb37a45f6af2a301c780223bb42fb8
RUN mkdir -p /appdir
WORKDIR /appdir
COPY target/*.jar app.jar
RUN addgroup -S appuser && adduser -S appuser -G appuser
USER appuser
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar", "--spring.config.location=file:./config/"]
