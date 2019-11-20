> Day9 배운 내용(191118) : 환경설정 | CRUD | URL namespace | RESTful API | 데이터필드 | 관리자모드

### Python workspace setting
- powershell에서 install
``` command
pip install pylint
pip install pylint-django
```
- VS Code PATH 추가하기
    -  내컴퓨터 > 속성 > 고급 > 환경변수 > PATH 편집 열어서 값 복사 > 메모장 열어서 ';'로 구분한 후 > Python 관련된 내용 복사 
    - VS Code에서  `Ctrl+P` 실행 후, Pytthon Settings 검색
    - Settings 페이지에서 Python Path 검색 > Python:Python Path에 path 붙여넣기
    ```
    C:\Users\Administrator\AppData\Local\Programs\Python\Python36\Scripts\;
    C:\Users\Administrator\AppData\Local\Programs\Python\Python36\;%USERPROFILE%\AppData\Local\Microsoft\WindowsApps;;
    ```

### 1. 프로젝트 생성
- Day9 폴더에서 프로젝트 생성
``` command
> django-admin startproject crudtest
```
- crudtest에 접속하여 app 만듦
``` command
> python manage.py startapp articles
```
- crudtest의 `settings.py` 수정
``` python
# INSTALLED_APPS 추가
# 콤마(,) 주의하기
INSTALLED_APPS = [
    'articles',
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
] 

# Internationalization 수정
LANGUAGE_CODE = 'ko'

TIME_ZONE = 'Asia/Seoul'

USE_I18N = True

USE_L10N = True

USE_TZ = False
```
- `articles` 폴더 내 `urls.py` 만들기 : URL 분리
> articles/urls.py에 path 추가하기
> 사용자로부터 온 request -> urls.py의 path가 연결 -> views.py 내 def
- `articles` 폴더에 `templates` 폴더 만듦
    - `base.html` 만들어서 templates 틀 생성
    ``` python
    {% extends 'base.html' %} # base.html 참조
    {% block content %}
    # 내용 입력하기
    {% endblock %}
    ```
- `views.py` 
    - view를 갖는 것은 `render`, 갖지 않는 것은 `redirect`
    - 파라미터 확인 -> id를 넘겨 받는지 아닌지

#### Migration
- migrations 
    - Table의 구조를 잡아줌
    - model 변경 내용 히스토리 관리
    - `models.py`는 migration을 쉽게 작성하기 위함 
- migrate
    - 

``` command
# Create Migration File
> python manage.py makemigrations
# Apply Migration File - DB에 반영
> python manage.py migrate
```

### 2. CRUD 구현
- `views.py`와 html 문서의 연결


### 3. URL namespace
- 각각의 url에 별명을 지어줘서 html 파일에서 사용하는 링크를 추가적으로 바꾸지 않고, `urls.py`에서만 수정하면 html 파일에서도 링크 수정이 반영되도록 함
- 사용방법?
``` python
# {% url '우리가 지정한 이름' 파라미터 %}
# index.html
 <td><a href="{% url 'show' article.id %}">{{article.title}}</a></td>
 ```
### 4. RESTful API
| 역할 | Request Method | <center>End-Point</center> | Views(Function) |
| :----: | :----: | :---- | :----: | 
| Create | GET | /articles/new | new |
| Create | POST | /articles/ | create |
| Read | GET | /articles/<id> | show |
| Read | GET | /articles | index |
| Update | GET | /articles/<id>edit | edit |
| Update | POST | /articles/<id>/ | update |
| Delete | POST | /articles/<id>/delete | delete |

- `GET`은 조회만 가능
- `POST`는 DB에 반영

### 5. 데이터 필드 추가 : 시간
``` python
from django.db import models

# Create your models here.
class Article(models.Model):
    title = models.CharField(max_length=16)
    contents = models.TextField()
    creator = models.CharField(max_length=8)
    # 맨 처음에 한 번만 추가
    created_at = models.DateTimeField(auto_now_add=True, null=True) 
    # 수정할 때마다 계속 바뀜
    updated_at = models.DateTimeField(auto_now=True, null=True)
```
- `python manage.py makemigrations` 실행
- 터미널에서 field 추가된 것 확인하기


### 6. 관리자모드
- 'localhost:8000/admin'으로 접속하면 django가 만들어 둔 관리자 페이지로 이동
- 관리자 계정 생성하기 : `python manage.py createsuperuser`
- admin.py에서 우리가 만든 class 등록하여 관리
``` python
from django.contrib import admin
from .models import Article

# Register your models here.
# admin page에서 Article을 관리하도록 등록
# models.py에서 만든 class를 등록하여 사용
admin.site.register(Article)
```

