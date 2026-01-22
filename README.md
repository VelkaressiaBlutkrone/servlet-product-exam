# Product Web Application (prodwebapp)

Spring Boot 기반의 상품 관리 웹 애플리케이션입니다. Servlet과 JDBC를 활용하여 상품의 등록, 조회, 삭제 기능을 제공합니다.

## 📋 프로젝트 개요

이 프로젝트는 Spring Boot 환경에서 전통적인 Servlet과 JDBC를 사용하여 상품 관리 시스템을 구현한 학습 프로젝트입니다. 
Mustache 템플릿 엔진을 사용하여 서버 사이드 렌더링을 구현하고, MVC 패턴을 기반으로 구조화되어 있습니다.

## 🛠 기술 스택

### Backend
- **Java**: 21
- **Spring Boot**: 4.0.1
- **Jakarta Servlet**: WebServlet API
- **JDBC**: MySQL Connector

### Frontend
- **Mustache**: 템플릿 엔진
- **HTML/CSS**: 기본 UI

### Database
- **MySQL**: 상품 데이터 저장

### Build Tool
- **Gradle**: 프로젝트 빌드 및 의존성 관리

### 기타
- **Lombok**: 보일러플레이트 코드 감소
- **Spring DevTools**: 개발 편의성 향상 (자동 재시작, 라이브 리로드)

## 📁 프로젝트 구조

```
src/
├── main/
│   ├── java/com/example/prodwebapp/
│   │   ├── ProdwebappApplication.java       # Spring Boot 메인 애플리케이션
│   │   ├── DispatcherServlet.java          # Front Controller (*.do 요청 처리)
│   │   ├── DBConnection.java               # 데이터베이스 연결 관리
│   │   ├── lib/
│   │   │   ├── View.java                   # Mustache 템플릿 렌더링
│   │   │   └── ViewResolver.java           # 뷰 이름을 템플릿으로 변환
│   │   └── product/
│   │       ├── Product.java                # 상품 엔티티 (모델)
│   │       ├── ProductController.java      # 상품 컨트롤러
│   │       ├── ProductService.java         # 상품 비즈니스 로직
│   │       └── ProductRepository.java      # 상품 데이터 액세스 계층
│   └── resources/
│       ├── application.properties          # 애플리케이션 설정
│       └── templates/                      # Mustache 템플릿
│           ├── list.mustache               # 상품 목록 페이지
│           ├── detail.mustache             # 상품 상세 페이지
│           └── insert.mustache             # 상품 등록 페이지
└── test/
```

## ✨ 주요 기능

### 1. 상품 목록 조회
- 등록된 모든 상품을 테이블 형태로 표시
- 상품명 클릭 시 상세 정보로 이동
- 각 상품별 삭제 기능 제공

### 2. 상품 상세 조회
- 선택한 상품의 상세 정보 표시
  - ID, 상품명, 가격, 수량

### 3. 상품 등록
- 새로운 상품 추가
- 입력 항목: 상품명, 가격, 수량

### 4. 상품 삭제
- 상품 목록에서 직접 삭제 가능

## 🚀 설치 및 실행 방법

### 사전 요구사항
- Java 21 이상
- MySQL 데이터베이스
- Gradle

### 데이터베이스 설정

1. MySQL에서 데이터베이스 생성:
```sql
CREATE DATABASE productdb;
USE productdb;
```

2. 상품 테이블 생성:
```sql
CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    qty INT NOT NULL
);
```

3. `DBConnection.java` 파일에서 데이터베이스 연결 정보 수정:
```java
String url = "jdbc:mysql://localhost:3306/productdb";
String username = "root";
String password = "your_password";  // 본인의 MySQL 비밀번호로 변경
```

### 애플리케이션 실행

1. 프로젝트 클론:
```bash
git clone https://github.com/VelkaressiaBlutkrone/servlet-product-exam.git
cd servlet-product-exam
```

2. Gradle 빌드:
```bash
./gradlew build
```

3. 애플리케이션 실행:
```bash
./gradlew bootRun
```

4. 브라우저에서 접속:
```
http://localhost:8080/product.do?cmd=list
```

## 🔗 API 엔드포인트

### GET 요청

| URL | 설명 |
|-----|------|
| `/product.do?cmd=list` | 상품 목록 조회 |
| `/product.do?cmd=detail&id={id}` | 상품 상세 조회 |
| `/product.do?cmd=insert-form` | 상품 등록 폼 |

### POST 요청

| URL | 파라미터 | 설명 |
|-----|----------|------|
| `/product.do?cmd=insert` | name, price, qty | 상품 등록 |
| `/product.do?cmd=delete` | id | 상품 삭제 |

## 🏗 아키텍처 패턴

### MVC 패턴
- **Model**: `Product.java` - 상품 데이터 구조
- **View**: Mustache 템플릿 파일 (`.mustache`)
- **Controller**: `ProductController.java` - 요청 처리 및 응답

### 계층 구조
1. **Presentation Layer** (DispatcherServlet)
   - Front Controller 패턴
   - 모든 `*.do` 요청을 처리

2. **Business Layer** (ProductService)
   - 트랜잭션 관리
   - 비즈니스 로직 검증

3. **Data Access Layer** (ProductRepository)
   - JDBC를 통한 데이터베이스 접근
   - CRUD 작업 수행

## 📝 특징

- **커스텀 MVC 프레임워크**: Spring MVC 대신 Servlet 기반 커스텀 구현
- **ViewResolver**: Mustache 템플릿을 동적으로 로드하고 렌더링
- **Front Controller 패턴**: 단일 진입점을 통한 요청 처리
- **예외 처리**: Service 계층에서 비즈니스 로직 검증 및 예외 발생
- **UTF-8 인코딩**: 한글 지원을 위한 완전한 인코딩 설정

## ⚙️ 개발 설정

`application.properties`에 다음과 같은 개발 편의 기능이 설정되어 있습니다:

- **DevTools**: 파일 변경 시 자동 재시작
- **Live Reload**: 브라우저 자동 새로고침
- **Error Handling**: 상세한 에러 메시지 표시
- **SQL Logging**: 실행되는 SQL 쿼리 로그 출력

## 📄 라이선스

이 프로젝트는 학습 목적으로 작성되었습니다.
