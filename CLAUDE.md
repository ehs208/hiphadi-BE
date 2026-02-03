# Hiphadi Backend

Spring Boot REST API. 상세 아키텍처는 [루트 문서](../CLAUDE.md) 및 [백엔드 가이드](../.claude/backend.md) 참조.

## 명령어

```bash
./gradlew bootRun          # 8080 포트 실행 (local 프로필)
./gradlew build -x test    # 테스트 제외 빌드
./gradlew test             # JUnit 5 전체 테스트
```

## 프로필

- `local` (기본) — MySQL localhost:3307, ddl-auto: create
- `prod` — MySQL 컨테이너, ddl-auto: validate

## 핵심 규칙

- 모든 API 응답은 `GlobalResponseDto<T>`로 감쌀 것
- 새 엔티티는 `BaseEntity` 상속 (createdAt/updatedAt 감사)
- 메뉴 조회 변경 시 캐시("MENU") 무효화 확인
- 도메인 패키지는 controller/service/repository/dto 구조 유지
- 이미지 업로드는 ImageService 통해 처리
