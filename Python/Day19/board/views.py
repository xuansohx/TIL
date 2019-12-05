from django.shortcuts import render, redirect, get_object_or_404
# get_object_or_404 : 404 오류를 내기 위한 객체
from .models import Article, Comment
from .forms import ArticleForm, CommentForm
# 로그인이 필요할 때 추가
from django.contrib.auth.decorators import login_required 
from django.views.decorators.http import require_GET, require_POST 
# GET 요청 : HTTP 달라고 하는 것 / 사용자에게 무언가를 주는 것
# POST 요청 : DB에 영향을 주는 것은 POST로 처리해야 됨(CRUD)

# 이렇게 표시해주면 로그인을 해야만 아래 함수가 실행
@login_required
def new_article(request):
    if request.method == 'POST':
        # 원래는 a.title = request.POST.get('title')
        # 이렇게 분리해서 집어 넣어줘야 하는데
        # 지금은 form 통째로 넣으면, title / content / ... 자동으로 분류되어 들어감
        form = ArticleForm(request.POST)
        # print('Data 넘어옴!')
        if form.is_valid(): # 넘어 온 데이터가 유효하다면
            article = form.save(commit=False) # form이 save 되면 article 객체가 나옴
            # commit=False -> DB에 저장되지 못 하도록 함 -> 작성자 추가하기 위하여?
            # DB에 추가하지 않고 객체 return만 한 후, 작성자 추가하고 DB에 저장(save)
            article.author = request.user
            article.save()
            return redirect('board:article_detail', article.id)
    else: # GET
        form = ArticleForm()
    context = {'form' : form}
    #print(form.errors)
    # form을 내보내면 우리가 만들지 않아도
    # bootstrap이 적용돼 나옴 (form.py)
    return render(request, 'board/article_form.html', context)

@require_GET
def article_list(request):
    articles = Article.objects.all()
    context = {'articles' : articles}
    return render(request, 'board/article_list.html', context)

def article_detail(request, article_id):
    article = Article.objects.get(id=article_id)
    comment_form = CommentForm()
    comments = Comment.objects.all() # 댓글 목록 보내주기 위에 전체 다 보냄
    if request.user.is_authenticated:
        is_like = article.like_users.filter(id=request.user.id).exists()
    else:
        is_like = None

    context = {
        'article' : article,
        'comment_form' : comment_form,
        'comments' : comments,
        'is_like' : is_like,        
        }
    return render(request, 'board/article_detail.html', context)

@login_required
def edit_article(request, article_id):
    article = get_object_or_404(Article, id=article_id)
    if request.method == 'POST':
        form = ArticleForm(request.POST, instance=article)
        if form.is_valid():
            article = form.save()
            return redirect('board:article_detail', article_id)
    else:
        form = ArticleForm(instance=article)
    context = { 'form' : form,}
    return render(request, 'board/article_form.html', context)

@login_required
@require_POST
# POST 요청이 아니면 처리하지 않겠다고 선언
def new_comment(request, article_id):
    # 잘못된 id가 넘어오는 것을 막기 위함..!
    # 잘못된 값이 넘어오면 오류 메시지를 보내서 막음
    article = get_object_or_404(Article, id=article_id)
    form = CommentForm(request.POST)
    if form.is_valid():
        comment = form.save(commit=False)
        comment.author = request.user
        comment.article_id = article_id
        comment.save()
    return redirect('board:article_detail', article_id)

def toggle_like(request, article_id):
    # 사용자가 '좋아요'를 누르면 DB에 저장되므로 POST
    # 1차 검증
    article = get_object_or_404(Article, id=article_id)
    user = request.user
    if article.like_users.filter(id=user.id).exists(): 
    # (SQL) 속도가 훨씬 빨라 ∵ DB에 빠르게 접근해서 옴
    # if user in article.like_users.all(): # 이 코드가 더 직관적 / 하지만 속도 느려
        article.like_users.remove(user)
    else:
        article.like_users.add(user)
    return redirect('board:article_detail', article.id)

    # article.like_users.add(request.user) # 이 게시글을 좋아하는 사용자 목록에 add like
    # request.user.like_articles(article) 위와 완전히 같음 -> user가 '좋아하는 게시글'에 add like

