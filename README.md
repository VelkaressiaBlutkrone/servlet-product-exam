# Product Web Application (prodwebapp)

Spring Boot 기반의 간단한 상품 관리 웹 애플리케이션입니다. 전통적인 Servlet과 JDBC를 이용해 상품의 등록, 목록 조회, 상세 조회, 삭제 기능을 제공합니다. Mustache 템플릿 엔진을 사용한 서버사이드 렌더링 방식으로 구성되어 있으며, 학습/실습용으로 설계되었습니다.

## 📋 프로젝트 개요

이 프로젝트는 Spring Boot 환경에서 Servlet(Front Controller 패턴)과 순수 JDBC를 사용하여 상품 관리 기능을 구현한 예제입니다. MVC 패턴을 수동으로 구성하고, View는 Mustache 템플릿을 사용해 렌더링합니다.

주요 흐름:
- 모든 요청은 `DispatcherServlet`(서블릿 맵핑: `*.do`)에서 처리
- 컨트롤러(`ProductController`)가 서비스(`ProductService`)를 호출
- 서비스는 레포지토리(`ProductRepository`)를 통해 DB에 접근
- 결과는 Mustache 템플릿으로 렌더링

## 🛠 기술 스택

- Java 21
- Spring Boot 4.0.1
- Jakarta Servlet (annotation-based, `@WebServlet("*.do")`)
- JDBC (MySQL Connector)
- Mustache (템플릿 엔진)
- Gradle 빌드 (toolchain으로 Java 21 설정)
- Lombok (컴파일 타임, 선택적)
- Spring DevTools (개발 편의)

## 📁 프로젝트 구조 (요약)

src/
├── main/
│   ├── java/com/example/prodwebapp/
│   │   ├── ProdwebappApplication.java       # Spring Boot 메인
│   │   ├── DispatcherServlet.java          # Front Controller (*.do)
│   │   ├── DBConnection.java               # JDBC 연결 관리 (현재 하드코딩된 설정)
│   │   ├── lib/
│   │   │   ├── View.java                   # Mustache 렌더링 헬퍼 (뷰 관련 유틸)
│   │   │   └── ViewResolver.java           # viewName -> template 변환 및 forward 처리
│   │   └── product/
│   │       ├── Product.java                # 상품 모델
│   │       ├── ProductController.java      # 컨트롤러
│   │       ├── ProductService.java         # 비즈니스 로직/트랜잭션 경계(단순)
│   │       └── ProductRepository.java      # JDBC 기반 데이터 액세스
│   └── resources/
│       ├── application.properties          # (선택) Spring 설정
│       └── templates/                      # Mustache 템플릿
│           ├── list.mustache               # 상품 목록
│           ├── detail.mustache             # 상품 상세
│           └── insert.mustache             # 상품 등록 폼
└── test/

(전체 파일은 저장소에서 직접 확인하세요.)

## ✨ 주요 기능

- 상품 등록 (폼을 통해 POST)
- 상품 목록 조회 (테이블)
- 상품 상세 조회
- 상품 삭제 (POST 요청으로 처리)
- View는 Mustache 템플릿으로 렌더링
- 요청 경로: `*.do` (예: `/product.do?cmd=list`)

템플릿 파일은 `src/main/resources/templates/` 안에 있으며, `DispatcherServlet`과 `ViewResolver`를 통해 forward 방식으로 렌더링됩니다.

## 데이터베이스 설정

현재 `DBConnection.getConnection()` 메서드에 기본값이 하드코딩되어 있습니다:

- URL: `jdbc:mysql://localhost:3306/productdb`
- 사용자: `root`
- 비밀번호: (코드 내 설정)

(실제 비밀번호는 코드에서 확인하거나, 보안상 변경하길 권장합니다.)

로컬 MySQL에 다음과 같이 DB/테이블을 준비하면 됩니다:

```sql
CREATE DATABASE productdb CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE productdb;

CREATE TABLE product (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  price INT NOT NULL,
  qty INT NOT NULL
);
```

주의:
- 실제 운영/협업 환경에서는 DB 접속 정보를 소스 코드에 하드코딩하지 마세요. 대신 `application.properties`나 환경 변수, 또는 Spring의 DataSource 구성을 사용하세요.
- README에서 DB 비밀번호를 그대로 노출하지 않도록 주의하세요.

## 빌드 및 실행 방법

프로젝트 루트에서 Gradle wrapper 사용을 권장합니다.

1. 빌드 및 실행 (개발용)
   - Linux / macOS:
     - ./gradlew bootRun
   - Windows:
     - gradlew.bat bootRun

2. 또는 jar 생성 후 실행
   - ./gradlew bootJar
   - java -jar build/libs/prodwebapp-0.0.1-SNAPSHOT.jar

애플리케이션 기본 포트는 8080입니다. (application.properties로 변경 가능)

## 코드에서 확인할 부분 / 개선 제안

- DBConnection: 현재 하드코딩된 DB 연결을 application.properties 또는 Spring DataSource로 대체 권장
- 예외 처리: 현재 예외는 RuntimeException을 던지는 식으로 단순 처리되어 있음. 사용자 친화적인 에러 페이지, 로깅, 예외 매핑 개선 권장
- 트랜잭션 관리: 현재는 단순 JDBC 호출만 사용. 트랜잭션이 필요한 경우 수동으로 처리하거나 Spring의 트랜잭션 매니저 사용 고려
- 보안: CSRF, 인증/권한이 없음 — 학습용으로는 무방하지만 실제 서비스에는 추가 필요
- 테스트: 단위/통합 테스트 추가 권장

## 개발 및 기여 안내

- 프로젝트는 학습용 예제입니다. 기능 추가나 개선을 원하시면 PR을 보내주세요.
- 커밋 스타일/브랜치 정책은 팀 규칙에 따르세요.

## 라이선스

원하시는 라이선스(예: MIT)를 명시해 주세요. 지정되어 있지 않다면 기본적으로 명시가 필요합니다.

---

참고: 제가 저장소의 주요 파일(서블릿, 서비스, 레포지토리, 템플릿, 빌드 스크립트 등)을 확인하여 README 내용을 작성했습니다. 코드 검색 결과는 제한될 수 있으므로 전체 파일을 직접 확인하려면 저장소를 열어 보시기 바랍니다: https://github.com/VelkaressiaBlutkrone/servlet-product-exam