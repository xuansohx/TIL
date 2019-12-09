> Day7 배운 내용(191114) : 검색어 트렌드 조회 | Class
### 1. 검색어 트렌드 조회
- NAVER Developers 등록 -> 데이터랩(검색어트렌드)
- Project 생성
``` command
> django-admin startproject naverapi
> cd naverapi
> python manage.py startapp search_trend
```
- `settings.py`에서 `INSTALLED_APPS` 등록
``` python
INSTALLED_APPS = [
    'search_trend',
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
]
```
> LANGUAGE_CODE와 TIME_ZONE 변경
> 오늘은 USE_TZ도 'False'로 바꿔주기!
- `urls.py`에서 path 설정 및 views 만듦
``` python
urlpatterns = [
    path('admin/', admin.site.urls),
    # 검색어를 입력하는 곳
    path('search/', search_trend_view.search),
    # 검색어에 대한 트렌드 결과를 받는 곳
    path('search/result', search_trend_view.result)
]
```
``` python
from search_trend import views as search_trend_view
```
- `views.py` -> html과 연동하여 view와 파라미터 관리
``` python
from django.shortcuts import render

import json
import requests # 요청을 보내기 위하여 필요한 module

# Create your views here.
def search(request):
    # 검색어를 입력하는 곳
    return render(request, 'search.html')

def result(request):
    # 검색어에 대한 검색 트렌드를 받아보는 곳
    # search.html에서 넘어온 파라미터
    start_date = request.GET['search_start_date']
    end_date = request.GET['search_end_date']
    time_unit = request.GET['search_time_unit']
    group_name = request.GET['search_group_name']
    keywords = request.GET['search_keywords'].split(',')
    # 대괄호 주의
    # 파라미터로 넘어온 값을 context에 담아 넘김

    # Dictionary
    query = {
    "startDate": start_date,
    "endDate": end_date,
    "timeUnit": time_unit,
    "keywordGroups": [
        {
        "groupName": group_name,
        "keywords": keywords
        }
    ]
    }

    # 요청URL
    url = 'https://openapi.naver.com/v1/datalab/search'
    client_id = 'nAtDuIvYiytutopxKiEp'
    client_secret = 'GjCFOiGBgL'

    headers = {
        'X-Naver-Client-Id' : client_id,
        'X-Naver-Client-Secret' : client_secret
    }

    # 파라미터를 JSON 형식으로 전달
    # ∴ Dictionary -> JSON
    params = json.dumps(query)

    response = requests.post(url, data=params, headers=headers)
    
    result = response.text
    context = {
        'result' : result
    }

    '''
    context = {
        'result':{
            'start_date':start_date,
            'end_date':end_date,
            'time_unit':time_unit,
            'group_name':group_name,
            'keyworkds':keywords
        }
    }
    '''
    return render(request, 'result.html',context)
```
> 'templates' 폴더를 만들어 html 문서 생성 (search.html와 result.html)

- `search.html`에서 필수 파라미터 전송을 위한 form 생성
``` python
<body>
    <h1>
        검색어를 입력하세요.
    </h1>
    <form action="/search/result">
        <label>기간 시간날짜</label>
        <input type="date" name="search_start_date">
        <br/>
        <label>기간 종료날짜</label>
        <input type="date" name="search_end_date">
        <br/>
        <label>구간 단위</label>
        <select name="search_time_unit">
            <option value="date">일간</option>
            <option value="week">주간</option>
            <option value="month">월간</option>
        </select>
        <br/>
        <label>검색어 그룹명</label>
        <input type="text" name="search_group_name">
        <br/>
        <label>트렌드를 볼 검색어 목록(여러 개의 검색어의 경우 ','로 구분)</label>
        <input type="text" name="search_keywords">
        <br/>
        <input type="submit" value="트렌드 보기">
    </form> 
</body>
```

- `result.html`에서 파라미터를 띄워줌 -> 결과 출력


- Python Server Loading
``` command
> python manage.py runserver
```
> 'json viewer chrome' 검색해서 확장프로그램 추가하기

### 2. Class
- Class 안에 함수를 담음
- SQL 문을 사용하지 않아도 Class를 활용하면 Python 문법으로 Table 생성할 수 있음
- https://sqlitebrowser.org/

#### ORM (Object-Relational Mapping, 객체 관계 매핑)
- 데이터베이스와 객체 지향 프로그래밍 언어 간의 호환되지 않는 데이터를 변환하는 프로그래밍 기법
-------
- models.py 에서 db 
- db.sqlite3
- python manage.py migrate -> 뼈대를 반영
    - db.sqlite3에 data 확인

- python manage.py makemigrations

- migration : Python 파일로 Table의 구조를 만드는 것
- migrate : 명령어 -> DB에 반영

- python manage.py shell
> phthon 문법으로 할 수 있음..? 무엇을??

- model 불러오기
from boards.models import Board
-> 이렇게 하면 Board를 활용할 수 있음
- Board 
b1 = Board()
b1.title = '첫번째 제목'
b1.contents = '롤리폴리 맛있다.'
b1.creator = 'Spectre'
> print(b1.~)로 출력해서 확인
b1.save()
----
- Create new Instance 
b2 = Board()
b2.title = 'ansoh'
b2.contents = 'memo'
b2.creator = 'an'
b2.save()

- 전체 리스트 뽑기
Board.objects.all()

- filter

Board.objects.filter(title='ansoh')[0] # 0번째
Board.objects.filter(title='ansoh').first()
> 이렇게 두 개 같음

Board.objects.filter(id=1)

b_all = Board.objects.all()
for b in b_all:
    print(b.title)
----
b1 = Board()
b1.~~
b1.save()

b1 = Board() -> 또 새로운 행이 만들어지는 것???
이름은 같지만 다른 것!
----
p.save() -> 내가 작업하던 것을 DB에 반영
// ORM의 좋은 점은???
p.destroy() -> DB에서 삭제
p.create() ->
p.update() ->