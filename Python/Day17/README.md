> Day17 배운 내용(191128) : 
오늘의 할 일
- 로그인이 되지 않은 상태에서 글을 작성할 수 없어야 함
    - 글 작성, 댓글 작성, 좋아요 -> 로그인이 반드시 필요한 기능
    - 1:N은 거의 끝나감! 이제 M:N을 할 차례...!
    - M:N ? 한 사람이 여러 개의 글을 작성하면 어떻게 구분할까!
- Pusher(실시간 기능 추가) - 외부 API
    

### 1. 사용자 권한
> 로그인을 해야만 글과 댓글을 작성이 가능하도록 & 좋아요 & 로그인 필수
- 기존의 DB 지우고 `models.py` 다시
``` python
# import 추가
from django.conf import settings
from django.contrib.auth.models import User

# class에 코드 추가
class Article(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)
```
- `views.py`
``` python
def index(request):
    if request.method == "POST":
        if request.user.is_authenticated: # 로그인 필수!
            article = Article()
            article.contents = request.POST["contents"]
            # 원본 이미지를 저장
            # article.image = request.FILES["image"]
            # 원본 이미지를 프로세싱 한 이미지를 저장
            # article.image_resized = request.FILES["image"]
            article.save()
            for image in request.FILES.getlist("image"):
                ArticleImages.objects.create(article_id=article.id, image=image)
            return redirect('articles')
        else: # 로그인이 안 되어 있으면 로그인을 해야됨
            return redirect('accounts:login')
    else:    
        articles = Article.objects.all().order_by("created_at").reverse()
        context = {
            'articles': articles
        }
        return render(request, 'index.html', context)
```

login.html 50번 째 줄 수정 - signup 추가
signup.html
index.html 155
views.py 에서 로그인 했을 때만 작성할 수 있도록 if문 설정
-> 70번 근처

댓글에서도 권한 설정
``` python
# .is_authenticated -> 로그인 여부 확인
if request.user.is_authenticated:
    # 로그인하여 user에게 권한이 있으면
    return ''
else:
    return ''

# user.id 확인 -> 게시글과 댓글을 쓴 사람 맞는지
if comment.user.id == request.user.id:
    # 글을 쓴 사람과 현재 사용자가 같은 사람인지 확인
    return ''
else:
    return ''
```

### 2. '좋아요' 기능
- midels.py 18번째 줄 -> makemigrations, migrate 하기
- views.py def likes 만듦
- index.html에서 ajax로 해줘야 화면이 바뀌지 않고 실행
> 68, 116
> ~123 ajax

### 3. 해시태그
- models.py 에 HashTag clss 추가