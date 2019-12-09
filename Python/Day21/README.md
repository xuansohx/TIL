> Day21 배운 내용(191204) : 사용자 검증 (signup / login / logout)

:heavy_check_mark: 간단한 리뷰

- `forms.py`에서 ArticleForm 정의함

- application의 핵심 로직은  `views.py`에 담김 ∴ form을 import 하여 사용 

- form을 사용하면 만들어진 html 문서 양식을 가져와서 편리하게 사용
- 인증 기능도 포함 돼 있기 때문에 간편하게 활용 가능 / 보안 기능도 우수함

> 'django'에는 기본적으로 제공되는 frame work가 많음 (admin, user, login ...)

- `settings.py` 문서 상에는 없어도 보이지 않는 다양한 frame work가 존재

> `auth` 라는 app 안에 `User` 라는 모델은 존재하지만 문서에는 기입되지 않았음

- admin 계정 만들고 관리

``` command
> python manage.py createsuperuser
```

---------------------------------------

accounts

새 파일로 forms.py 만듦

``` python
from django.contrib.auth.forms import UserCreationForm, AuthenticationForm
```

accounts의 views.py

``` python
from django.shortcuts import render
from .forms import AuthenticationForm, UserCreationForm
```

urls.py가 실행되고 views.py가 들어오니까 -> urls.py 먼저

accounts/에서는 ursl.py가 없으니까 만듦

``` python
from django.urls import path
from . import views

app_name = 'accounts'

urlpatterns = [
    path('signup/', views.signup, name='signup'),
    path('login/', views.login, name='login'),
    path('logout/', views.logout, name='logout'),
]
```

다시 views.py

``` python
from django.shortcuts import render
from .forms import AuthenticationForm, UserCreationForm

def signup(request):
    if request.method == 'POST':
        form = UserCreationForm(request.POST)
        if form.is_valid():
            user = form.save()
            return redirect('board:article_list')
    else: # GET
        form = ArticleForm()
    context = {'form' : form}
    return render(request, 'accounts/signup.html', context)

def login(request):
    pass
def logout(request):
    pass
```

master의 urls.py

``` python
path('accounts/', include('accounts.urls')),
```

accounts/templates/accounts/signup.html

settings.py에 등록

-------

accounts/views.py

accounts/login.html

--------------------

accounts/models.py

인증관련된 것은 여기에



board/views.py

accounts/views.py





key 값 꺼내기

``` python
# 찾았는데 없으면 없다고 말해줌 -> 'null'이라는 return
request.GET.get('next')
# 없는 index 접근하면 error
request.GET['next']
```

