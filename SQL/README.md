# SQL (Structured Query Language)

:green_book: SQL이란? 데이터베이스에 질의(Query)를 실행해서 원하는 데이터를 조회하거나 입력, 수정, 삭제 등을 할 수 있는 절차형 언어

:blue_book: 데이터베이스 관리 시스템(DBMS, DataBase Management System) 중 **Oracle**을 사용하여 학습

## [day14](https://github.com/xuansohx/TIL/blob/master/SQL/190530.md)

> 19.05.30.Thu

1. 데이터베이스의 정의와 구성

> 대량의 정보를 컴퓨터가 효율적으로 접근할 수 있도록 가공 및 저장한 것으로, 2차원 표 형식으로 데이터를 관리하기 때문에 이해하기 쉬운 관계형 데이터베이스(Relation Database, RDBS) 학습

2. SQL(Structured Query Language)의 개요

- DDL(Data Definition Language, 데이터 정의 언어)
  - 데이터를 저장하는 데이터베이스 및 테이블을 생성 및 삭제하기 위함
  - `CREATE` `DROP` `ALTER` 
- DML(Data Manipulation Language, 데이터 조작 언어) :heavy_check_mark:
  - 테이블의 행을 검색하거나 변경하기 위함
  - `SELECT` `INSERT` `DELETE` `UPDATE`
- DCL(Data Control Language, 데이터 제어 언어)
  - 데이터에서 처리한 변경 내용을 확정하거나 취소하기 위함
  - RDBMS 사용자에게 처리 권한 부여
  - `COMMIT` `ROLLBACK` `GRANT` `REVOKE`

3. SQL의 기본적인 작성 규칙

- 마지막에 세미콜론(;)을 붙임

- 대문자와 소문자 구분 없이 키워드 사용 할 수 있음

  > 하지만 테이블에 등록된 데이터는 대문자와 소문자 구분이 필요!

- 문자열을 SQL 안에 기술할 경우, 작은 따옴표(')로 문자열을 감싸기

- 단어는 공백 문자나 줄바꿈 문자로 구분



## [day15](https://github.com/xuansohx/TIL/blob/master/SQL/190531.md)

> 19.05.31.Fri

1. SELECT 문의 기본

- `SELECT` + `FROM` : 결과출력
- `AS` 키워드 : 열에 별명을 부여
- `DISTINCT` : 결과에서 중복된 행 제거
- `WHERE` : 원하는 행을 선택할 때 사용하며, `FROM` 구 바로 뒤에서 사용

2. 산술 연산자와 비교 연산자

3. 논리 연산자 (`NOT` `AND` `OR`)

4. 검색 결과 재정렬 (`ORDER BY`)

   > `ASC`를 사용하면 default인 오름차순, `DESC`를 사용하면 내림차순 정렬

5. 함수 (산수 함수, 문자열 함수, 날짜 함수, 변환 함수)

6. 술어 : `LIKE`를 사용하여 특정 단어를 가진 데이터를 추출할 때

7. CASE식

:round_pushpin: 오늘 배운 SQL 연습문제 보러가기 [click](https://github.com/xuansohx/TIL/blob/master/SQL/%EC%97%B0%EC%8A%B5%EB%AC%B8%EC%A0%9C/190531_SQL_%EC%97%B0%EC%8A%B5%EB%AC%B8%EC%A0%9C.md) 



## [day16](https://github.com/xuansohx/TIL/blob/master/SQL/190603.md)

> 19.06.03.Mon

1. 집합 함수

- 여러 행에 적용하는 함수로 '집약 함수' 또는 '그룹 함수'라고 함
- `COUNT` `SUM` `AVG` `MAX` `MIN`을 하나의 결과로 나타냄
- `GROUP BY` 
- 그룹에 대한 조건을 지정하는 `HAVING`과 행에 대한 조건을 지정하는 `WHERE`

2. 뷰(View) : 자주 사용하는 SELECT문을 저장하여 반복사용이 용이함

3. 서브쿼리(스칼라, 상관)

4. 결합(Join)

:round_pushpin: 오늘 배운 SQL 연습문제 보러가기 [click](https://github.com/xuansohx/TIL/blob/master/SQL/%EC%97%B0%EC%8A%B5%EB%AC%B8%EC%A0%9C/190603_SQL_%EC%97%B0%EC%8A%B5%EB%AC%B8%EC%A0%9C.md)



## [day18](https://github.com/xuansohx/TIL/blob/master/SQL/190605.md)

> 19.06.05.Wed

1. day16 복습 문제 (상관서브쿼리 & `JOIN`)
2. `EXISTS` 술어 
3. 집합 연산 (`UNION` `JOIN`)
4. 윈도우 함수 : 순위 구하기
5. `ROLLUP` : `GROUP BY`와 함께 사용되며, 함께와 소계를 한 번에 구할 수 있음

:round_pushpin: 오늘 배운 SQL 연습문제 보러가기 [click](https://github.com/xuansohx/TIL/blob/master/SQL/%EC%97%B0%EC%8A%B5%EB%AC%B8%EC%A0%9C/190605_SQL_%EC%97%B0%EC%8A%B5%EB%AC%B8%EC%A0%9C.md)



## [day19](https://github.com/xuansohx/TIL/blob/master/SQL/190610_workshop.md)

> 19.06.10.Mon

1. 데이터베이스 구조 

- App : 사용자 요청정보(data)를 받아서 Biz에 전달하과 처리 결과를 보여줌
- Biz
  - 실제 데이터에 대한 삽입, 삭제, 수정, 조회 등 공통된 작업을 수행하는 추상클래스
  - 전달된 데이터를 로직에 의하여 처리하고 결과를 반환
  - Dao에서 담당하는 CRUD 작업의 성공 여부에 따른 최종 COMMIT과 ROLLBACK 수행 - 보안과 검증
- Dao
  - Biz에 전달 된 파라미터를 기반으로 DB에서 CRUD 작업을 수행
  - 최종 transaction 처리를 Biz에 위임하고, 처리 결과만 Biz에 전달
  - 작업 중 Exception이 발생하면 처리하지 않고, 초기화 된 데이터를 Biz에 전달

2. 데이터베이스 연동(JDBC)

> JDBC (Java DataBase Connectivity) : 자바 프로그램 내에서 SQL을 실행하기 위해 데이터베이스를 연결해주는 응용프로그램 인터페이스) -> Java에서 DB(Oracle)에 접근하여 데이터를 조회, 삽입, 수정, 삭제 가능

3. WORKSHOP

- 데이터 테이블 구축 후 ERD 작성
- 데이터베이스 구축
- UML을 통해 biz dao 표시
- JDBC API를 이용하여 CRUD 프로그램 구현



## [day40](https://github.com/xuansohx/TIL/blob/master/SQL/190709.md)

> 19.07.09.Tue

1. 쇼핑몰 ERD 작성

> ERD(Entity-Relationship Diagram) : 데이터베이스에 저장된 데이터를 개체(entity)와 관계(relationship)를 중심으로 작성한 것

2. 작성한 ERD를 바탕으로 데이터베이스 구축