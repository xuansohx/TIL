> Day19 배운 내용(191202) : 가상환경 venv | .gitignore | Python Faker
:heavy_check_mark: 터미널 powershell 대신 bash 사용 권장

### 1. 가상환경 venv
``` command
> python -m venv venv
> source venv/Scripts/activate
> pip install django
> pip freeze > requirements.txt
```
- `source ~` 대신 F1 -> Select Interpreter -> '.\venv\...' 이 방법도 가능
- `requirements.txt`는 주기적으로 업데이트하여 코드 공유 시, 사용하는 프로그램 버전을 알려줌

### 2. gitignore
버전관리를 하지 않아도 되는 코드는 `.gitignore`에 등록하여 제외
- venv/는 용량이 매우 크기 때문에 .gitignore에 추가
- http://gitignore.io/ -> django, python, venv 등록
> `.vscode/`는 직접 추가

### 3. Create Project and App
- create Project
``` command
> django-admin startproject django_advance .
> python manage.py runserver
```
- settings.py
``` python
LANGUAGE_CODE = 'ko-kr'
TIME_ZONE = 'Asia/Seoul'
```
- create App
``` command
> python manage.py startapp board
> touch board/urls.py 
```
``` python
# INSTALLED_APPS 추가 (Line33)
# Ctrl+G -> Line 찾아가기
INSTALLED_APPS = [
    'board',
    ...
]
```
- url 분리
``` python
# master urls.py
from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('board/', include('board:urls')),
]

# board/urls.py
from django.urls import path
from . import views

app_name = 'board'
urlpatterns = [
    # 아무 말 없이 들어오면 여기로
    path('', views.article_list, name="article_list"),   
]
```
``` python
from django.shortcuts import render

# Create your views here.
def article_list(request):
    return render(request, 'board/article_list.html')
```
- templates/ 폴더 생성
``` command
> mkdir -p board/templates/board
> cd board
> touch article_list.html article_detail.html article_form.html
```

- models.py
``` command
> python manage.py migrate board
```
> 뒤에 board 추가하면 해당하는 내용만 출력

### 4. Input dummy data by faker
- install Python Faker
``` command
pip install faker
pip install django_extensions
pip install ipython
```
- input Data
``` command
> python manage.py shell plus
>> from board.models import Article
>> Article.dummy(20)
>> Article.objects.all()
```
- SQLite 실행해서 확인('▶' 눌러서 확인)