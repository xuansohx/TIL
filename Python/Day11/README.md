> Day11 배운 내용(191120) : fake Instagram 만들기 
- 오늘부터 금요일까지 1개의 프로젝트로 수업 진행
- Instagram
- 댓글(comment) : model, url
    - database relation(1:N)
    - 1:N(일대다) -> 유저가 여러 개의 게시글을 작성하는 경우
    이때 게시글(N)은 유저(1)에 대한 정보를 알고 있음 - ID
- (내일) 이미지 업로드, 좋아요
    - 좋아요, 해시태그 : database relation(M:N)
    - M:N(다대다) -> ??? 
## Instagram
### 1. django 프로젝트 생성
> 실제 Instagram도 django로 되어있음
``` command
> django-admin startproject instagram
> cd instagram
> python manage.py startapp article
```
- settings.py와 urls.py 수정하기
- article/에 urls.py 파일과 templates 폴더 생성
> urls.py로 URL을 분리하고, templates에서 화면 구성

#### 'redirect'와 'render'
- redirect : 
    - 다시 한 번 요청을 보내는 것
    - html 문서 지정하지 않음
- render :
    - html 문서를 띄움

#### Python 문법
- {% %} : Python Logic
- {{ }} : 실제 출력되는 내용
- html 문서에서 <!-- --> 주석 안에 Python 문법 넣어도 주석으로 인식하지 못 하므로 주의하기

### 2. migrations와 migrate
- migrations은 테이블을 만들기 위한 파일 -> 테이블 구조
- migrate는 구조파일을 이용하여 실제 테이블을 생성
``` command
> python manage.py makemigrations
> python manage.py migrate
```
- migrations 폴더 내 0001~ 파일 생성되었는지 확인

### 3. Fontawesome
- https://fontawesome.com/
- CSS를 기반으로 하는 글꼴 및 아이콘 툴킷
- 아이콘 다운 받아서 HTML 화면 꾸미기
- 'getbootstrap'에서 받은 CSS 링크 아래 'fontawesome' 링크 추가
- 아이콘 class 추가하여 나타내기

### 4. Update and Delete
해당하는 게시글의 댓글 인식하도록 아이디 설정하기 :star:
- models.py
``` python
class Comment(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    # 게시글이 삭제가 되면 댓글도 함께 삭제되도록
    article = models.ForeignKey(Article, on_delete=models.CASCADE)
```
댓글 삭제하기
- urls.py
``` python
urlpatterns=[
path('comments/<int:comment_id>/delete', article_views.delete_comment, name="delete_comment")
]
```
- views.py
``` python
def delete_comment(request, comment_id):
    comment = Comment.objects.get(id=comment_id)
    comment.delete
    # 삭제될 때는 view가 필요 없음
    return redirect('articles')
```
- index.html에서 delete button 설정
``` python
<a href="{% url 'delete_comment' comment.id %}" class="btn btn-danger"><i class="fas fa-trash-alt"></i></a>
```
> comment의 id를 찾아 해당하는 comment를 delete
댓글 수정하기

- urls.py
``` python
urlpatterns = [
    path('comments/<int:comment_id>/edit', article_views.edit_comment, name="edit_comment")
 ]
```
- views.py
``` python
def edit_comment(request, comment_id):
    comment = Comment.objects.get(id=comment_id)
    if request.method == "POST":
        comment.contents = request.POST["contents"]
        comment.save()
        return redirect('articles')
    else:
        context = {
            'comment' : comment
        }
        return render(request, 'edit.html', context)
```
- index.html
``` html
 <a href="{% url 'edit_comment' comment.id %}" class="btn btn-warning"><i class="fas fa-edit"></i></a>
```
 - templates/comment/edit.html
 ``` html
 {% extends 'base.html' %}

{% block content %}
<div class="container">
    <div class="card">
        <form action="{% url 'edit_comment' comment.id %}" method="POST">
            <input type="hidden" name="csrfmiddlewaretoken" value="{{csrf_token}}">
            <div class="card-body">
                <textarea class="form-control" name="contents" rows="5">{{comment.contents}}</textarea>
            </div>
            <div class="card-footer text-right">
                <input type="submit" class="btn btn-success" value="작성하기">
            </div>
    </div>
    </form>
</div>
{% endblock %}
 ```
본문 수정하기
- urls.py
``` python
urlpatterns = [
    path('articles/<int:article_id>/edit/', article_views.edit, name="edit")
 ]
```
- views.py
``` python
def edit(request, article_id):
    article = Article.objects.get(id=article_id)
    if request.method == "POST":
        article.contents = request.POST["contents"]
        article.save()
        return redirect('articles')
    else:
        context = {
            'article' : article
        }
        # 아래 edit_comment와 동일하게 edit.html로 이동하면 안됨
        # 따라서, templates에 article과 comment 폴더 만들어서 분리
        return render(request, 'article/edit.html', context)
```
- index.html
``` html
<a href="{% url 'edit' article.id %}" class="btn btn-warning text-black"><i class="fas fa-edit"></i></a>
```
- templates/article/edit.html
``` html
{% extends 'base.html' %}

{% block content %}
<div class="container">
    <div class="card">
        <form action="{% url 'edit' article.id %}" method="POST">
            <input type="hidden" name="csrfmiddlewaretoken" value="{{csrf_token}}">
            <div class="card-body">
                <textarea class="form-control" name="contents" rows="5">{{article.contents}}</textarea>
            </div>
            <div class="card-footer text-right">
                <input type="submit" class="btn btn-success" value="작성하기">
            </div>
    </div>
    </form>
</div>
{% endblock %}
```
<br>

#### models.py 활용하여 가독성 높이기
- index.html
```html
 <form action="{% url 'comment_set.all' %}" method="POST">
                        .....
                    </form>
```
-> `article.comment_set.all` 가독성 좋지 않음
- models.py에서 method 만들어 간단히 사용할 수 있도록 하기
``` python
class Article(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
	# 여기에서 새롭게 정의하여 추가함!
    def comments(self):
        return Comment.objects.filter(article_id=self.id)
```

```html
<!-- 간단한 형태로 나타낼 수 있음 --> 
<form action="{% url 'comments' %}" method="POST">
                        .....
                    </form>
````

<br>

#### 비동기성 vs 동기성

예를들어, 집 청소를 해야한다 -> 설거지(1시간) / 빨래(1시간) / 청소(1시간)  소요

- 비동기성 : 동시에 실행시켜 두고 callback만 받음 :star:
  
    > 식기세척기와 세탁기, 로봇청소기를 동시에 가동하여 종료 알림만 받는 것
    
    ∴ 동시에 진행하는 것이 가능함! ajax를 활용하면 비동기성 가능
    
- 동기성 : 하나씩 실행 -> 하나를 진행하는 동안 다른 것을 하지 못 함