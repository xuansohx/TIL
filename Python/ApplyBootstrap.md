### Django와 'Bootstrap' 연결하기:art:

#### 1. Bootstrap을 연결하기 전, project와 app을 생성

``` command
> django-admin startproject ootd
> cd ootd
> python manage.py startapp codi
```
:heavy_check_mark: 기본설정하기
- `settings.py`
``` python
# INSTALLED_APPS 추가
INSTALLED_APPS = [
    'codi',
    ...
]
# LANGUAGE_CODE와 TIME_ZONE 수정
LANGUAGE_CODE = 'ko'

TIME_ZONE = 'Asia/Seoul'
```
- app 내 `urls.py` 폴더를 만들어 url 분리

ootd/urls.py

``` python
from django.contrib import admin
from django.urls import path, include
from django.conf import settings
from django.conf.urls.static import static

urlpatterns = [
    path('admin/', admin.site.urls),
    path('codi/', include('codi.urls')) # url 분리
]
```

coid/urls.py
``` python
from django.urls import path
from . import views as codi_views

urlpatterns = [ 
    # views.py에 index 실행
    path('', codi_views.index, name="codi"),
 ]
```
- `views.py`
``` python
from django.shortcuts import render

# index에 request가 있으면 index.html을 화면에 띄움
def index(request):
    return render(request, 'index.html')
```

#### 2. 'templates/'와 'static/' 만듦 

- app 폴더인 'codi'에 templates와 static 폴더 생성
> Javascript, CSS, image 파일을 static이라 부르며 정적인 동작
- static 파일은 `settings.py`에서 경로 설정
``` python
# 맨 아래에 추가
STATIC_ROOT = os.path.join(BASE_DIR, 'static')
```
- static 폴더에 `index.html`을 포함한 html 문서를 제외한 폴더를 넣음(CSS, fonts, image)
- templates 폴더에 Bootstrap의 `index.html` 추가

#### 3. CSS와 JS, Img를 Bootstrap과 연결하기 (`index.html`)

``` html
'{% static "기존 tag 내용" %}'
```
> 위의 형태로 감싸줌

:interrobang: Img를 Bootstrap과 연결할 때 따옴표 어떻게?

`style='background-image: url("{% static "images/work-1.jpg" %}");'` -> 오류 안 나고 실행은 됨

#### 4. `index.html` 문서 상단에 `{% load staticfiles %}` 추가