> Day22 배운 내용(191205) : '좋아요' 버튼 | 댓글 작성을 위한 Form | 요청 처리 | 팔로우

### 1. '좋아요' 버튼

- `board/models.py`

``` python
from django.conf import settings
# user를 사용하기 위한 import
# 이때 user는 직접 참조하지 못하기 때문에 settings를 import 하여 사용
	...
class Article(models.Model):
    author = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)
    # on_delete : ForeignKeyField를 사용할 때, DB 상에서 참조 무결성을 유지하기 위함 -> 무결성이란? 데이터의 정확성과 일관성을 유지하는 것
    # CASCADE : FK가 바라보는 값이 삭제될 때, FK를 포함하는 row도 함께 삭제
    
    ...
    
    like_users = models.ManyToManyField(settings.AUTH_USER_MODEL, related_name='like_articles')
    # '좋아요' -> article과 user를 연결해줘야 됨 -> 연결하기 위한 class 생성
    # 'ManyToManyField'로 다대다관계 만듦
    # 'related_name'은 ForeignKey의 이름을 지정해주기 위함
```

:heavy_check_mark: `models.py` 수정했으면 DataBase 비우고 다시 시작하기

``` command
> rm db.sqlite3 board/migrations/0* accounts/migrations/0* 

> python manage.py makemigrations
> python manage.py migrate
```

- `board/forms.py` 

``` python
class Meta: # django 내부에서 제공하는 form...?
        model = Article
        ...
        exclude = ['date', 'author', 'like_users']
```

> Article 작성하는 form에서는 'date, author, like_users'는 필요 없음

- `board/urls.py`

``` python
urlpatterns = [
    ...
    path('<int:article_id>/like/', views.toggle_like, name="toggle_like"),
]
```

- `board/views.py`

``` python
def toggle_like(request, article_id):
    article = get_object_or_404(Article, id=article_id)
    user = request.user
    if article.like_users.filter(id=user.id).exists(): # 이미 '좋아요' 눌렀으면
        article.like_users.remove(user) # '좋아요' 취소
   	else:
        article.like_users.add(user) # '좋아요' 없으면 누르기
    return redirect('board:article_detail', article.id)
```

``` python
# 아래 두 가지 코드 동일함 -> 속도 차이 존재
if article.like_users.filter(id=user.id).exists(): # DB에 빠르게 접근해서 오기 때문에 속도 빠름
if user in article.like_users.all(): # 이 코드가 더 직관저기지만 속도는 느림
```

``` python
# 아래 두 가지 코드도 완전히 동일 ('좋아요' 기능)
article.like_users.add(request.user) # 이 게시글을 좋아하는 사용자 목록에 user를 추가
request.user.like_articles(article) # user가 '좋아하는 게시글'에 article을 추가
```

- `board/article_detail.html` : '좋아요' 버튼 추가 :thumbsup:

``` html
<form action="{% url 'board:toggle_like' article.id %}" method="POST">
    {% csrf_token %}
    {% if is_like %} 
        <!-- '좋아요' 버튼을 눌렀을 때와 취소했을 때 상태 다르게 보여줌 -->
        <button class="btn btn-warning">dislike</button>
    {% else %}
        <button class="btn btn-primary">like it!</button>
    {% endif %}
</form>
```



### 2. Comment Form

- `board/models.py`

``` python
class Comment(models.Model):
    author = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete = models.CASCADE)
    article = models.ForeignKey(Article, on_delete = models.CASCADE)
    
    content = models.CharField(max_length = 300)
    created_at = models.DateTimeField(auto_now_add = True)
    updated_at = models.DateTimeField(auto_now = True)
```

- `board/urls.py`

``` python
urlpatterns = [
    ...
    path('<int:article_id>comments/new/', views.new_comment, name="new_comment"),
]
```

- `board/views.py`

``` python
from .forms import ArticleForm, CommentForm

def article_detail(request, article_id):
    ...
    comment_form = CommentForm()
    comments = Comment.objects.all() # 댓글 목록을 보여주기 위하여 전체를 보냄
    ...
    context = {
        'article' : article,
        'comment_form' : comment_form,
        'comments' : comments,
        'is_like' : is_like
    } 
    return render(request, 'board/article_detail.html', context)

def new_comment(request, article_id):
    # 잘못된 id가 넘어오면 404 오류 메시지를 보내서 막음
    article = get_object_or_404(Article,id=article_id)
    form = CommentForm(request.POST)
    if form.is_valid():
        comment = form.save(commit=False)
        comment.author = request.user
        comment.article_id = article_id
        comment.save()
    return redirect('board:article_detail', article_id)
```

- `board/forms.py`

``` python
class CommentForm(forms.ModelForm):
    content = forms.CharField(min_length=1, max_length=200)
    class Meta:
        model = Comment
        fields = ['content',]
```

- `board/article_detail.html` :speech_balloon:

``` html
{% if user.is_authenticated %} 
<!-- user가 login 하면 출력 -->
    <form action="{% url 'board:new_comment' article.id %}" method="POST">
        {% csrf_token %}
        {% bootstrap_form comment_form %}
        <button class="btn btn-secondary">댓글 작성</button>
    </form>
{% else %}
    로그인 하세요!
{% endif %}

★★★★★
{% if comments %}
{% for comment in comments %}
    <div>
        {{ comment.content }} - {{ comment.author }}
    </div>
{% endfor %}

{% else %}
    <div>댓글이 없어요</div>

{% endif %}
```

``` html
<!-- 위의 '★★★★★' 아래 코드 간단하게 나타내기 -->
{% for comment in comments %}
{% if comment.article_id == article.id %} 
    <div>
        {{ comment.content }} - {{ comment.author }}
    </div>
{% endif %}

{% empty %}
    <div>댓글이 없어요</div>
{% endfor %}
```

> `{% if comment.article_id == article.id %}` : 게시글에 해당하는 댓글만 나오도록 함



### 3. 요청 처리

require_GET , require_POST

- `board/views.py`

``` python
from django.views.decorators.http import require_GET, require_POST
# GET 요청 : HTTP 달라고 하는 것 / 사용자에게 무언가를 줌
# POST 요청 : DB에 영향을 주는 것은 모두 POST로 처리해야 됨 -> CRUD
```

``` python
@require_GET
def article_list(request):
    pass

@require_POST
def new_comment(request, article_id):
    pass
```



### 4. 팔로우

- `accounts/models.py`

``` python
class User(AbstractUser):
    ...
    fans = models.ManyToManyField('self', related_name='stars')
    # 팔로우와 팔로워 단어 헷갈리니까 fans와 stars로 기입
```

- `accounts/views.py`

``` python
def follow(request, user_id):
    fan = request.user
    star = get_object_or_404(id=user_id)
    if fan.stars.filter(id=star.id).exists(): 
        fan.stars.remove(star)
    else:
        fan.starts.add(star)
    return redirect('accounts:user_detail', star.id)
```

