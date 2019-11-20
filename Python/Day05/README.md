> Day5 배운 내용(191112) : Django | Django Project

:heavy_check_mark: `request`와 `requests`의 차이점? `requests` : 요청을 보낼 때 / `request` : 받은 요청

### 1. Django

- 프로젝트 vs 어플리케이션(app)

- MVC와 유사한데 Templetes 폴더를 사용하여 'MVT'라고 부름

  ∴ Model View Controller -> Model View(Controller) Template(View)

- Controller 역할을 하는 것이 `view.py`

  > def 함수를 통하여 requests를 보내거나 결과를 return

- django에서 app 단위는 하나의 모델에 대한 모든 내용이 담겨 있음

  예를들어, 게시판을 만든다고 하면 'post'라는 app을 만든 후 그 안에서 모든 내용을 처리

### 2. Django Project

:last_quarter_moon_with_face: **Django Project - app** 만드는 방법 

- 프로젝트 생성

```command
> django-admin startproject [ProjectName]
> cd myproject
> python manage.py startapp [AppName] # Create APP
```

> 'day5'라는 Project에 'lotto'라는 App 만듦

- `settings.py` 수정

Language Code와 Time Zone 설정

```Python
LANGUAGE_CODE = 'ko' # Modify Language Code
TIME_ZONE = 'Asia/Seoul' # Modify Time Zone
```

우리가 해당 프로젝트에서 사용 할 app을 추가

```python
# Application definition

INSTALLED_APPS = [
    'lotto', # 여기에 해당 프로젝트에서 사용 할 app 이름 추가
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
]
```

- 만든 app 폴더로 가서 `views.py` 파일에 함수 등록
- 해당 함수의 결과로 return 할 template 선언
- 위 template 폴더 만들기
- `urls.py`에 등록된 함수 연결

> request가 들어오면 urls.py를 거쳐 어느 method로 갈지 정해짐

```python
from lotto import views as lotto_views
from ascii import views as ascii_views
from opgg import views as opgg_views

urlpatterns = [
    path('admin/', admin.site.urls),
    path('lotto/', lotto_views.lotto),
    path('lotto/winning', lotto_views.winning),
    path('ascii/', ascii_views.ascii),
    path('ascii/result', ascii_views.result),
    path('opgg/', opgg_views.opgg),
    path('opgg/result', opgg_views.result)
]
```

- `app`  실행

```command
> python manage.py runserver
```

- `views.py`와 templates 폴더 내 `html` 문서 만듦

<br>

위의 프로젝트 생성 과정은 공통적으로 진행 / 주제 별 특별하거나 새로운 함수 기록 :pencil:

#### 2-1. 로또번호생성기

- 랜덤으로 번호 생성

```python
a = range(10) # 0부터 10미만
range(0,10) # 시작 숫자와 끝 숫자 -> 끝 숫자는 포함되지 않음
```

- Python의 for문 : result에서 num을 골라 하나씩 출력

```html
<body>
    <h1>
        당신의 추첨 번호는
        {% for num in result %}
        <span>{{ num }}</span>
        {% endfor %}
    </h1>
</body>
```

#### 2-2. Ascii (아스키 아트 만들기)

참고 사이트 : http://artii.herokuapp.com/make?text=hello&font=kban

- 문자열 포맷팅 -> 앞에 `f` 로 표시하고 `{ }` 안에 치환 할 공간을 표시

```python
def result(request):
    # ascii에서 입력한 텍스트와 폰트를
    font = request.GET['font']
    text = reauest.GET['text']
    # artii에 보내서 결과 값을 받아서 보내줌
    url=f'http://artii.herokuapp.com/make?text={text}&font={font}'
    response = requests.get(url)
    context = {
        'result':response.text
    }
    return render(request, 'result.html', context)
```

> request를 result.html로 전송! context에 담아서 :smile:

#### 2-3. opgg

- 하나의 프로젝트의 `templates` 내 같은 이름의 html 문서가 존재하면 안됨
  - templates 안에 또 하나의 폴더를 만들어주면 같은 이름을 사용할 수 있음

- `span.WinLose .wins` : `WinLose` 클래스를 가진 <span> 태그 안에서 `wins` 클래스를 찾음

:heavy_check_mark:`CSS selector` 공부해보기 