 # Docker 镜像构建
# @author hwang
#
FROM maven:3.9.6-jdk-8-slim as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# Run the web service on container startup.
CMD ["java","-jar","/app/target/huangOJ_system_backend-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]