# Product Web Application (prodwebapp)

Spring Boot 기반의 제품 관리 웹 애플리케이션입니다. Servlet과 Mustache 템플릿 엔진을 활용한 MVC 패턴 구현 예제입니다.

## 프로젝트 개요

이 프로젝트는 Servlet을 이용한 제품 관리 시스템으로, DispatcherServlet 패턴을 구현하여 요청을 처리하고 Mustache 템플릿으로 뷰를 렌더링합니다.

## 기술 스택

- **Java**: 21
- **Spring Boot**: 4.0.1
- **템플릿 엔진**: Mustache
- **빌드 도구**: Gradle
- **데이터베이스**: MySQL
- **주요 라이브러리**:
  - Spring Boot Starter WebMVC
  - Spring Boot Starter Mustache
  - MySQL Connector/J
  - Lombok
  - Spring Boot DevTools

## 프로젝트 구조

```
servlet-product-exam/
├── src/
│   ├── main/
│   │   ├── java/com/example/prodwebapp/
│   │   │   ├── ProdwebappApplication.java      # Spring Boot 애플리케이션 진입점
│   │   │   ├── DispatcherServlet.java          # 요청 처리 서블릿
│   │   │   ├── DBConnection.java               # 데이터베이스 연결 관리
│   │   │   └── lib/
│   │   │       ├── View.java                   # 뷰 렌더링 처리
│   │   │       └── ViewResolver.java           # 뷰 리졸버
│   │   └── resources/
│   │       ├── application.properties          # 애플리케이션 설정
│   │       └── templates/                      # Mustache 템플릿
│   │           ├── list.mustache               # 상품 목록 화면
│   │           ├── save-form.mustache          # 상품 등록 화면
│   │           └── detail.mustache             # 상품 상세 화면
│   └── test/
│       └── java/com/example/prodwebapp/
├── build.gradle                                 # Gradle 빌드 설정
└── settings.gradle                              # Gradle 프로젝트 설정
```

## 주요 기능

### 1. DispatcherServlet
- `@WebServlet("*.do")` 어노테이션을 통해 모든 `.do` 요청 처리
- `cmd` 파라미터를 통한 요청 라우팅
- GET, POST 요청을 각각 처리

### 2. ViewResolver
- Mustache 템플릿 파일을 로드하고 컴파일
- UTF-8 인코딩 지원
- 템플릿 파일 경로: `src/main/resources/templates/`

### 3. View
- Mustache 템플릿 실행 및 렌더링
- 요청 속성의 모델 데이터를 템플릿에 전달
- HTML 응답 생성

### 4. DBConnection
- MySQL 데이터베이스 연결 관리
- JDBC를 통한 데이터베이스 접근

## API 엔드포인트

| Method | URL | 설명 |
|--------|-----|------|
| GET | `/product.do?cmd=list` | 상품 목록 조회 |
| POST | `/product.do?cmd=save` | 상품 등록 폼 |
| POST | `/product.do?cmd=detail` | 상품 상세 조회 |

## 데이터베이스 설정

### MySQL 설정
```properties
URL: jdbc:mysql://localhost:3306/productdb
Username: root
Password: bitc5600!
```

### 데이터베이스 생성
```sql
CREATE DATABASE productdb;
```

## 실행 방법

### 1. 사전 요구사항
- Java 21 이상
- MySQL 서버
- Gradle (또는 Wrapper 사용)

### 2. 데이터베이스 설정
```bash
# MySQL에 접속하여 데이터베이스 생성
mysql -u root -p
CREATE DATABASE productdb;
```

### 3. 애플리케이션 실행

#### Gradle Wrapper 사용 (권장)
```bash
# Windows
gradlew.bat bootRun

# Linux/Mac
./gradlew bootRun
```

#### IDE에서 실행
- `ProdwebappApplication.java`의 main 메서드 실행

### 4. 접속
애플리케이션이 시작되면 브라우저에서 접속:
- http://localhost:8080/product.do?cmd=list

## 개발 도구 설정

### DevTools
- 자동 재시작 활성화
- 라이브 리로드 지원
- 변경 감지 간격: 2초

### 로깅
- 전체 로그 레벨: INFO
- Spring Web 로그: DEBUG
- SQL 쿼리 로그: DEBUG

## 빌드

```bash
# 빌드
./gradlew build

# 테스트
./gradlew test

# 클린 빌드
./gradlew clean build
```

## 라이센스

이 프로젝트는 학습 목적으로 제작되었습니다.
