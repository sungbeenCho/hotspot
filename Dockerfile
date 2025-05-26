# Java 21 버전의 경량 이미지 사용
FROM openjdk:21-jdk-slim

# 컨테이너 내 작업 디렉토리 설정
WORKDIR /app

# jar 파일 복사 (Gradle 빌드시 생성된 jar 경로)
COPY build/libs/hotspot-0.0.1-SNAPSHOT.jar app.jar

# 애플리케이션이 실행될 포트 지정
EXPOSE 8080

# Spring Boot 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
