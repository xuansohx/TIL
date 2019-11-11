> Day2 배운 내용 : 파이썬 딕셔너리 | 데이터 추출 | 함수 활용 | 플라스크  

window + R / powershell

json viewer chrome 다운

```command
> pip install flask
```

> 터미널 명령어



- 파이썬 딕셔너리
  - 딕셔너리?
  - 왜 쓰는지?
  - 어떻게 활용할지?
  - JSON과 비교했을 때 어떤 차이가 있는지?
  - 딕셔너리 활용 문제

- 어제 사용했던 다음 웹툰 코드를 활용해서 우리가 원하는 정보를 뽑아내는 것
- 자유롭게 원하는 사이트에서 데이터를 뽑아내보기 - 사이트 분석 / 데이터 추출
- 함수 활용하기
- 플라스크 기본



## 1. Python Dictionary

- 정보를 담기 위한 방법 : 변수를 만듦 -> 변수를 배열(array)로 만듦(a[0], a[1], a[2] ...) -> HashMap

  - 정보를 담기 위해서는 변수가 필요

  - Java에서는 배열 내 데이터 타입이 하나로 같아야하지만, Python에서는 자유롭게 가능

  - 배열의 자리수로 나타내면 변동의 위험 ∴ 이름을 지정

  - 이때 이름이 중복되는 경우를 방지하기 위하여 `key`와 `value` 값을 함께 등록 -> HashMap

    - HashMap은 `key-value`의 데이터 구조

    - `key`는 고유한 값을 가지도록 함
    - 하나의 `key`에 `value`는 자유롭게 담을 수 있음



> 덕 타이핑 (동적 타이핑)
>
> 객체의 변수 및 메소드의 집합이 객체의 타입을 결정하는 것 -> 자유로운 형 변환이 가능



### Quiz

```
1. 평균을 구하세요.
SCORES =
```

format string

https://brownbears.tistory.com/421



웹툰 http://webtoon.daum.net/data/pc/webtoon/list_serialized/fri



## Flask

debug 모드 : 서버를 껐다 켜지 않고 새로고침(F5)만 눌러도 실행되도록



$env:FLASK_ENV="Development"

$env:FLASK_DEBUG="TRUE"