> Day23 배운 내용(191206) : Heroku로 배포하기



- `heroku.com` 회원가입
- `heroku cli` 다운로드 

- 배포 할 프로젝트 `git repo.` 밖으로 꺼내기 ('master' 뜨면 안됨)

:heavy_check_mark: (venv) 설정하는 법

``` command
> python -m venv venv
> source venv/Scripts/activate
> pip install django
> pip freeze > requirements.txt
```

> (venv)가 바로 실행되지 않는 이유 : Python Extension이 실행되는 속도와 console의 속도가 다르기 때문

`settings.py` 내 Django 버전이랑 `python freeze` 했을 때의  Django의 버전이 같아야 됨

- `settings.py`에 ALLOWED_HOSTS 추가

```python
# SECURITY WARNING: don't run with debug turned on in production!
DEBUG = True # 배포할 땐 'False'로 하기

ALLOWED_HOSTS = ['127.0.0.1', '']
```

- 

git init > add > commit

pip install django_heroku

>  오류나면 pip install postgresql

- settings.py

``` python
# Static files (CSS, JavaScript, Images)
# https://docs.djangoproject.com/en/2.2/howto/static-files/

STATIC_URL = '/static/'
STATIC_ROOT = os.path.join(BASE_DIR, 'static')
# BASE_DIR은 Project Root -> 지금의 위치를 잡음?
# static 폴더 있으면 안됨

# 아까 install한 heroku import 해줌
import django_heroku
django_heroku.settings(locals())
```

``` command
> touch Procfile
```

``` 
web: gunicorn django_advance.wsgi --log-file -
```

> 띄어쓰기 중요
>
> heroku가 프로젝트 전달받자마자 읽어 -> 

pip install gunicorn

touch runtime.txt

pyhon -V : 버전확인

- runtime.txt

``` 
python-3.7.4
```

heroku

heroku login : 이때 기본 프로그램 Chrome으로 뜨도록 설정 (제어판- 윈도우+R -> control)

컴퓨터 빌리기 (로그인 한 상태에서)

heroku create  [서비스명] -> 서비스명 안 쓰면 랜덤숫자 받아서 만들어짐

git remote -v

heroku config:set DEBUG=False

git push heroku master



heroku run python manage.py migrate -> 우리 컴퓨터가 아니라 heroku DB에 migrate 적용

안되면 heroku 사이트 내 console에 입력



python manage.py dumpdata -> db 데이터 json으로 보내는 것 /



> 하나의 앱에 'fixtures/' 새 폴더 만듦

python manage.py loaddata data.json

- dump 된 상태에서 git add > commit > push

heroku cli