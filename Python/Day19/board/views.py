from django.shortcuts import render
from .models import Article, Comment

# Create your views here.
def article_list(request):
    articles = Article.objects.all()
    context = {'articles' : articles}
    return render(request, 'board/article_list.html', context)

def article_detail(request, article_id):
    article = Article.objects.get(id=article_id)
    context = {'article' : article}
    return render(request, 'board/article_detail.html', context)