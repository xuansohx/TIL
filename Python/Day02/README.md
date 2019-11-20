> Day2 배운 내용(191107) : Python Dictionary | 데이터 추출 | 함수 활용 | Flask  

:heavy_check_mark: json viewer chrome 다운

### 1. Python Dictionary

- 정보를 담기 위하여 변수 필요 -> 변수를 배열(array)로 -> HashMap 또는 Dictionary

  ∵ Key-Value로 이루어져 있기 때문에 찾기가 쉬움

- Dictionary는 immutable한 키(key)와 mutable한 값(value)으로 맵핑되어 있는 순서가 없는 집합

  > immutable : 값이 변하지 않는 / mutable : 값이 변하는

```python
# Dictionary의 형태 (Key-Value)
{Key : value}

# Example
"20층 식당"{
    "A코스" : "돈까스",
    "B코스" : "순대국"
}
```

- Java에서는 데이터 타입이 하나로 같아야 하지만 Python에서는 자유롭게 가능

- `key`와 `value` 값을 함께 등록하여 이름이 중복되는 경우를 방지 ∵ `key`는 고유한 값

  - 하나의 `key`에 `value`는 자유롭게 담을 수 있음

  

[참고] Duck Typing - '동적 타이핑'의 한 종류

> 덕 타이핑 (동적 타이핑)
>
> 객체의 변수 및 메소드의 집합이 객체의 타입을 결정하는 것 -> 자유로운 형 변환이 가능



:blue_book: Dictionary 활용 문제 / **Quiz**

```python
# 1. 평균을 구하세요.
scores = {
    "수학" : 90,
    "영어" : 87,
    "한국지리" : 92
}

# 1번 solution
# 배열의 길이를 구하는 함수는 len(배열)
# 반올림 하는 함수는 round(숫자, 소숫점)

total = 0
for value in scores.values():
    total += value
ave = total/len(scores)
#print(round(ave,1))
```

> scores의 각 value 값을 for문을 통해 모두 더함

```python
# 2. 각 학생의 평균 점수와 반 평균을 구하세요.
scores = {
    "a학생" :{
        "수학" : 80,
        "국어" : 90,
        "음악" : 100
    },
    "b학생" : {
        "수학" : 90,
        "국어" : 90,
        "음악" : 60
    }
}

# 2번 solution
a = 0
b = 0

for score_a in scores.get("a학생").values():
    a += score_a
ave_a = a/len(scores.get("a학생"))
#print(round(ave_a,1))
       
for score_b in scores.get("b학생").values():
    b += score_b
ave_b = b/len(scores.get("b학생"))
#print(round(ave_b,1))
```



### 2. 데이터 추출

- Format String

```python
url = f'http://webtoon.daum.net/data/pc/webtoon/list_serialized/{day}'
```

> 우리가 'day'라는 변수에 저장한 값이 url의 해당 위치에 들어감 ∴ 원하는 요일의 웹툰 데이터를 크롤링

- 원하는 데이터 추출하기

```python
    # 장르의 위치는 'cartoon' 안에 'genre'라는 리스트 안에 'name'이라는 key
    genres = []
    for genre in toon["cartoon"]["genres"]:
        genres.append(genre["name"])
```



### 3. 함수 활용

### 4. Flask

> Python Framework의 일종으로 매우 가볍고 심플하다는 특징
>
> Django와 비교하여 자유도가 높음 -> 개발자가 할 것이 많음

- Flask 설치하기

```command
> pip install flask
```

- 환경설정

```command
> $env:FLASK_ENV="development"
> $env:FLASK_DEBUG="True"
```

> Debug Mode가 'ON' 되어야 서버를 껐다 켜지 않고 새로고침(F5)만 눌러도 실행

- Flask 서버 돌리기

```python
from flask import Flask, escape, request
import requests

app = Flask(__name__)
# 이 부분 추가해주기
if __name__ == '__main__':
    app.run(debug=True)
    
@app.route('/')
def index():
    return { 'hello': 'world'}
```

