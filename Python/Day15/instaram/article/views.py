from django.shortcuts import render, redirect
# json으로 응답을 보낼 때 아래 꼭 import 시켜주기
from django.http.response import HttpResponse, JsonResponse
from .models import Article, Comment, ArticleImages, Board, HashTag
import json

# Create your views here.
def js_test(request):
    return render(request, 'js_test.html')

def jq_test(request):
    boards = Board.objects.all().order_by("created_at").reverse()
    context = {
        'boards':boards
    }
    return render(request, 'jq_test.html', context)

def submit_boards(request):
    if request.method == "POST":
        contents = request.POST["board"]
        board = Board.objects.create(contents=contents)
        context = {
            'board' : board
        }
        return render(request, 'empty.html', context)

def delete_boards(request):
    if request.method == 'POST':
        id = request.POST["board_id"]
        board = Board.objects.get(id=id)
        board.delete()
        context = {
            'board_id' : id
        }        
        return HttpResponse(json.dumps(context), content_type="application/json")

def edit_boards(request):
    if request.method == "POST":
        id = request.POST["board_id"]
        contents = request.POST["contents"]
        board = Board.objects.get(id=id)
        board.contents = contents
        board.save()
        return HttpResponse('', status=204)

def index(request):
    if request.method == "POST":
        if request.user.is_authenticated:
            article = Article()
            article.contents = request.POST["contents"]
            article.user_id = request.user.id
            
            # 원본 이미지를 저장
            # article.image = request.FILES["image"]
            # 원본 이미지를 프로세싱 한 이미지를 저장
            # article.image_resized = request.FILES["image"]
            
            article.save()
            # 게시글을 저장한 뒤 hashtag를 등록해야됨
            tags = request.POST["hashtag"] 
            for tag in tags.split(","):
                tag = tag.strip()
                if not HashTag.objects.filter(tag=tag):
                    tag = HashTag.objects.create(tag=tag)
                else:
                    tag = HashTag.objects.filter(tag=tag)[0]
                article.tags.add(tag)

            for image in request.FILES.getlist("image"):
                ArticleImages.objects.create(article_id=article.id, image=image)
            return redirect('articles')
        else:
            return redirect('accounts:login')
    else:
        query = request.GET["tags"]
        articles = HashTag.objects.filter(tag=query)[0].article    
        articles = Article.objects.all().order_by("created_at").reverse()
        tags = HashTag.objects.all()
        context = {
            'articles': articles
        }
        return render(request, 'index.html', context)

def edit(request, article_id):
    article = Article.objects.get(id=article_id)
    # 로그인을 하여 권한이 있는 사람만 게시글 작성할 수 있도록 함
    if article.is_permitted(request.user.id):
        if request.method == "POST":
            article.contents = request.POST["contents"]
            article.save()
            return redirect('articles')
        else:
            context = {
                'article': article
            }
            # 권한이 있으면 글을 작성할 수 있지만
            return render(request, 'article/edit.html', context)
    else:
        return redirect('articles')

def delete(request, article_id):
    article = Article.objects.get(id=article_id)
    # 로그인 하지 않은 상태에서 게시글을 삭제하기 위해 시도해도 
    # 글이 삭제되지 않음
    if article.is_permitted(request.user.id):
        article.delete()
        return redirect('articles')
    else:
        return redirect('articles')

def comments(request):
    if request.method == "POST":
        if request.user.is_authenticated:
            contents = request.POST["contents"]
            article_id = request.POST["article_id"]

            if request.POST["form_method"] == "create":
                comment = Comment()
            elif request.POST["form_method"] == "edit":
                comment_id = request.POST["comment_id"]
                comment = Comment.objects.get(id=comment_id)
                if comment.user_id != request.user.id:
                    return HttpResponse('', status=401)

            comment.contents = contents
            comment.article_id = article_id
            comment.user_id = request.user.id
            comment.save()
            context = {
                # 'comment'라는 이름으로 data를 보냄
                'method' : request.POST["form_method"], # class가 아닌 name으로 가져옴
                'comment' : comment.contents,
                'username' : comment.user.username,
                'comment_id' : comment.id,
                'article_id' : comment.article_id,
            } 
            # json을 application으로 보내는 것은 django에서의 약속
            return HttpResponse(json.dumps(context), content_type="application/json")
    else:
        context = {
            'status' : 401,
            'message' : '로그인이 필요합니다'    
        }
        return HttpResponse(json.dumps(context), status=401, content_type="application/json")

def delete_comment(request):
    if request.method == "POST":
        comment_id = request.POST["comment_id"]
        comment = Comment.objects.get(id=comment_id)
        if comment.user_id == request.user.id:
            comment.delete()
            return HttpResponse('', status=204)
        else:
            return HttpResponse('', status=401)
    

def edit_comment(request, comment_id):
    comment = Comment.objects.get(id=comment_id)
    if request.method == "POST":
        comment.contents = request.POST["contents"]
        comment.save()
        return redirect('articles')
    else:
        context = {
            'comment': comment
        }
        return render(request, 'comment/edit.html', context)

def likes(request):
    if request.user.is_authenticated and request.method == "POST":
        article_id = request.POST["article_id"]
        article = Article.objects.get(id=article_id)

        if request.user in article.user_likes.all():
            article.user_likes.remove(request.user) # '좋아요' 취소
        else:
            article.user_likes.add(request.user) # 좋아요^0^

        likes_count = len(article.user_likes.all()) # 몇 명이 눌렀는지
        context = {
            'count' : likes_count
        }
        return HttpResponse(json.dumps(context), content_type="application/json")
    else:
        return HttpResponse('', status=403)