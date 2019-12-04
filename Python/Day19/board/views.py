from django.shortcuts import render, redirect, get_object_or_404
# get_object_or_404 : 404 오류를 내기 위한 객체
from .models import Article, Comment
from .forms import ArticleForm

# @login_required
def new_article(request):
    if request.method == 'POST':
        # 원래는 a.title = request.POST.get('title')
        # 이렇게 분리해서 집어 넣어줘야 하는데
        # 지금은 form 통째로 넣으면, title / content / ... 자동으로 분류되어 들어감
        form = ArticleForm(request.POST)
        # print('Data 넘어옴!')
        if form.is_valid(): # 넘어 온 데이터가 유효하다면
            article = form.save() # form이 save 되면 article 객체가 나옴
            return redirect('board:article_detail', article.id)
    else: # GET
        form = ArticleForm()
    context = {'form' : form}
    #print(form.errors)
    # form을 내보내면 우리가 만들지 않아도
    # bootstrap이 적용돼 나옴 (form.py)
    return render(request, 'board/new_article.html', context)

def article_list(request):
    articles = Article.objects.all()
    context = {'articles' : articles}
    return render(request, 'board/article_list.html', context)

def article_detail(request, article_id):
    article = Article.objects.get(id=article_id)
    context = {'article' : article}
    return render(request, 'board/article_detail.html', context)

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