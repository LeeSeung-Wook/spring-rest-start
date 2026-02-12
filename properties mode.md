# Properties Mode 정리

Spring Boot는 환경별로 설정을 나눠 관리합니다. 이 프로젝트는 `dev`(개발)와 `prod`(배포) 프로파일을 사용합니다.

## 현재 활성 프로파일
- `src/main/resources/application.properties`
- 내용: `spring.profiles.active=dev`
- 따라서 기본 실행은 **dev 모드**입니다.

## dev 프로파일 (개발 모드)
파일: `src/main/resources/application-dev.properties`

주요 특징
1. DB: H2 인메모리 사용
2. JPA: `ddl-auto=create` (테이블 자동 생성)
3. SQL 로그 출력 및 포맷팅 활성화
4. 초기 데이터 `db/data.sql` 자동 로드
5. 로그 레벨: `DEBUG`
6. CORS: 모든 출처 허용 (`*`)
7. H2 콘솔 활성화

의도
- 빠르게 개발/테스트하기 위한 설정

## prod 프로파일 (배포 모드)
파일: `src/main/resources/application-prod.properties`

주요 특징
1. DB: MySQL (RDS 환경변수 사용)
2. JPA: `ddl-auto=none` (자동 DDL 비활성화)
3. SQL 로그 출력 없음
4. 로그 레벨: `INFO`
5. CORS: 지정된 프론트엔드만 허용
6. H2 콘솔 비활성화

의도
- 안정적이고 안전한 운영 환경을 위한 설정

## dev ↔ prod 전환
실행 시 프로파일을 바꾸려면 `spring.profiles.active` 값을 변경하면 됩니다.

예시
- `dev`로 실행: `spring.profiles.active=dev`
- `prod`로 실행: `spring.profiles.active=prod`
