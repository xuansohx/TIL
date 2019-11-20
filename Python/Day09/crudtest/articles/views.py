from django.shortcuts import render, redirect

# models.py 폴더에서 Article을 불러오겠다고 선언
from .models import Article

# Create your views here.
# view를 갖지 않는 것은 redirect -> 다른 곳으로 보내기
# view를 갖는 것은 render
def index(request):
    # Article Model에 있는 모든 Article을 불러옴
    articles = Article.objects.all()
    context = {
        'articles' : articles
    }
    return render(request, 'index.html', context)

def show(request, id):
    # Article Model에서 특정 id를 가진 하나의 Article을 불러옴(detail)
    article = Article.objects.get(id=id)
    context = {
        'article' : article
    }
    return render(request, 'show.html', context)

def new(request):
    # 불러올 Article이 없음
    return render(request, 'new.html')

def create(request):
    # Article을 새로 생성 / request로부터 받아옴
    title = request.GET['title']
    contents = request.GET['contents']
    creator = request.GET['creator']
    '''
    # 방법1
    article = Article.objects.create(title=title, contents=contents, creator=creator)
    '''
    # 방법2
    article = Article() # 빈 객체를 만들어서 저장
    article.title = title
    article.contents = contents
    article.creator = creator
    article.save()

    # return redirect(f'/articles/{article.id}')
    # URL namespace 적용 -> 여기에서는 ',' 찍어줌
    return redirect('articles:show', article.id)

def edit(request, id):
    # Article Model에 있는 특정 Article을 가져와야 함
    article = Article.objects.get(id=id)
    context = {
        'article' : article
    }
    return render(request, 'edit.html', context)

def update(request, id):
    # Article Model에 있는 특정 Article을 가져와야 함
    article = Article.objects.get(id=id)
    # 기존 article 정보를 바꿔서 저장하는 부분
    # 새로 바뀐 정보를 받아서
    title = request.GET['title']
    contents = request.GET['contents']
    creator = request.GET['creator']
    # 이렇게 저장
    article.title = title
    article.contents = contents
    article.creator = creator
    article.save()
    # 저장된 정보를 해당 아이디로 전송 -> redirect
    # return redirect(f'/articles/{article.id}')
    # URL namespace 적용 -> 여기에서는 ',' 찍어줌
    return redirect('articles:show', article.id)

def delete(request, id):
    # Article Model에 있는 특정 Article을 가져와야 함
    article = Article.objects.get(id=id)
    article.delete()
    return redirect('articles:index')
