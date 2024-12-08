FROM openjdk:21-slim

WORKDIR /app

# JAR 파일 복사
COPY build/libs/*.jar app.jar

# 컨테이너 실행 시 실행할 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]
