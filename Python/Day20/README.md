> Day20 배운 내용(191203) : Bootstrap | form | delete DataBase

### 1. make and apply Bootstrap
- install `django-bootstrap4`
``` command
> mv board/templates/board/base.html ./templates/
> touch board/forms.py
> pip install django-boostrap4
```
- CSS와 JavaScript(JS) 적용하기

``` html
<!-- CSS 적용하기 위한 link -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
```

```  html
<!-- JavaScript 적용하기 위한 link -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
```



:page_facing_up: **django form**

form(양식)을 활용하면 편하고 데이터를 넣을 수 있는 예쁜 페이지 양식을 볼 수 있음

- form을 만들기 위한 `form.py`를 만듦



### 2. delete DataBase
``` command
rm db.sqlite3
rm board/migrations/0*
```
> '0*'은 0으로 시작하는 모든 파일



### 3. Data Check
- 데이터 확인 절차 : HTML -> django -> DataBase
- HTML : 90% 확인 (필수사항..?)
- django : form을 이용하여 check, CharField, blank ...
- DataBase :
    
    - 모순 -> DB가 비어있는 field가 null이 아니라는 이유로 받음..?????
    
    


### 4. check Login-User
``` python
@login_required
def mypage(request):
    return ''
```
- 함수(def)에 `@login_required`를 사용하여 사용자 인증 가능
- `.../accounts/login/?next=.../` 
    - `/?` 부분에 전달받은 파라미터 표시
    - `next=...`로 페이지 이동 -> 데이터 전송

