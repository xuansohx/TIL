> Day8 배운 내용(191115) :
### Yesterday Review
- Naver API 사용해보기
    - 외부 사이트에 Request를 보낼 때, POST 방식으로 요청하는 방법 학습
    - Request Body에 단순히 파라미터 값으로 이루어진 쌍이 아니라 json 형식으로 파라미터를 보내는 방법
- ORM 기초
    - Create, Read를 Django Shell에서 실행
    - ORM(Object Relationship Mapping)이 무엇인지? 왜 사용해야하는지?
---
### Today To DO
- 기본 게시판 만들기
    - URL 분리하기
        - `url.py`에 우리가 접속할 모든 주소를 명시했는데, CRUD를 하다보면 만들어야 할 페이지가 점점 많아져 구분하기가 어려워짐 -> 따라서 각 역할을 하는 App마다 `urls.py` 파일을 생성할 예정
        - 공용으로 사용할 수 있는(공유할 수 있는) HTML 파일 만들기
            - 반복되는 HTML 구조를 계속해서 새로 만들지 말고, 공통되는 부분은 하나의 파일로 묶어서 반복해서 사용함
        - CRUD 계속 반복
---
```
project 만들기 > django-admin startproject crudtest
app 만들기 > python manage.py startapp boards
```
- `settings.py`
```
INSTALLED_APPS = [
    'boards',
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
]

LANGUAGE_CODE = 'ko'

TIME_ZONE = 'Asia/Seoul'

USE_I18N = True

USE_L10N = True

USE_TZ = False
```

- `views.py`
- `urls.py`
- `models.py`

``` command
구조를 만듦?? DB의 뼈대를 만들어 / 
환경을 만들 수 있는 파일
> python manage.py makemigrations
실제 DB에 넣음?? 동작시켜
> python manage.py migrate 
 ```

### Bootstrap
getbootstrap.com

- beautify 적용하기
`Ctrl` + `p` -> `>Beautify file`





#### 회사에서 사용하는 작업관리 Tool
Trello
XMind
Jira Softwore : 주로 큰 기업에서 사용
Notion 

--- 오후
C - new, Create
R - Index, Show
U - Edit, Update
D - Destory

### URL 분리
- Django 스택의 근본적인 목표는 '느슨한 결합, 탄탄한 응집'
- 프레임워크의 각 계층은 서로 알지 못 해야 함?

- boards에 urls.py 만들어서 분리 


- render : 직접 html을 뿌리는 것
- redirect : 다른 path와 연결해서 html 뿌려


- URL 분리해서 CRUD 구현
