> Day1 배운 내용(191106) : 개발자 도구 | Browser의 렌더링 과정 및 구조 분석 | Python 다운 | 크롤링

### 1. 개발자 도구

- Web 분석을 할 때 사용
- Chrome으로 접속 -> `F12`를 눌러 개발자 모드 접속 > `Network` 탭 -> index.html 문서



### 2. Browser의 렌더링 과정 및 구조 분석

#### 2-1. Browser의 Rendering

- URL 참고 : https://d2.naver.com/helloworld/59361
  - [그림2] 중요
  - 렌더트리, DOM 트리가 무엇이고, 어떻게 그려지는지 잘 보기
- 최근 jQuery 잘 사용하지 않고 JavaScript 쓰는 이유는?
- 웹 분석을 위하여 Wappalyzer 다운 -> Chrome에서 검색하여 추가하면 됨

#### 2-2. Browser의 구조

- 개발자모드로 확인해보면 <body> 위의 <head>에 간단한 script와 CSS 배치
- body 태그가 로그되기 시작하면 div와 a 태그를 거쳐 DOM 트리가 그려짐 (preview에서 구조 확인)

- jQuery 대신 JavaScript 사용하는 이유 찾아보기:question:

- XML이나 JSON에서 데이터를 담고 있음 / 예전에는 HTML로 크롤링 했는데 요즘은 XML이나 JSON으로 함
  - XHR에서 대부분의 데이터 갖고 있음
- `Network`의 `Headers` 를 통하여 파라미터를 어떻게 전송하는지 알 수 있음

:heavy_check_mark: 예전에는 App / Mobile / DeskTop이 각각의 서버를 구성하였으나, 요즘은 하나의 서버를 가짐 → 서버에 요청을 하면 데이터를 뿌려줌 → 서버는 CRUD 등 최소한의 역할만 수행

:heavy_check_mark: graphql vs sql

> sql이 발전하여 graphql ???
>
> https://tech.kakao.com/2019/08/01/graphql-basic/ 참고하기

- sql은 **데이터베이스 시스템**에 저장된 데이터를 효율적으로 가져오는 것이 목적
  - Back End에서 statement를 작성하고 호출
- gql은 **웹 클라이언트**가 데이터를 서버로부터 효율적으로 가져오는 것이 목적
  - Front End에서 statement를 작성하고 호출

:heavy_check_mark: Browser가 로딩되는 과정

- Client는 `click`을 하면 변화가 나타나기를 바람 ∴ click을 하면 일단 화면을 바꿔 → 데이터를 채움

- CSS로 틀을 그려준 후, 뱅글뱅글 돌면서 데이터가 채워짐

- 예전에는 html 문서 하나를 사용했기 때문에 빈 화면으로 대기하다 모든 데이터가 다 떠야 출력되었음

- 요즘은 index 파일 → CSS → 데이터



### 3. Python 설치

- python 3.7.4 버전 다운로드
  - Windows x86-64 executable installer (Windows)
  - Add python 3.7 to PATH 꼭 클릭

- Editor는 VS Code 다운 (Visual Studio Code)
  - VS Code에서 Python 코드 작성
  - `Window`+`R` 눌러 **PowerShell**에서 코드를 실행
    - 해당 디렉토리로 이동하여 `python 파일명.py`

### 4. 크롤링

- Crawling이란? web 상에 존재하는 contents를 수집하는 작업 
  - HTML 페이지를 가져와서, HTML/CSS 등을 파싱하고 필요한 데이터만 추출하는 기법
- BeautifulSoup 라이브러리 : HTML의 태그를 파싱해서 필요한 데이터만 보기 좋게 추출할 수 있음

```command
> pip install bs4
```

- 요청을 보내기 위한 requests 모듈 (import 필요)

```command
> python install requests
> pip install requests
```

- 응답코드
  - 200 : 정상 
    - 서버로부터 정상적인 응답을 받음
  - 300 : 리디렉션 
    - 다른 페이지 연결해줌
  - 400 : 클라이언트 오류 :warning:
    - 사용자의 책임
    - 해당 url을 찾을 수 없는 경우, 404 오류
    - 권한이 없는 Forbidden 상태인 경우, 403 오류
  - 500 : 서버의 오류 :warning:
    - 개발자의 책임
    - 서버에서 연산을 하던 중 코드에서 오류가 발견되거나
    - 원하는 데이터 타입이 잘 안 맞는 경우 발생







