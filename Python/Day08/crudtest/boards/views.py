from django.shortcuts import render, redirect

# models 파일에서 Board를 불러와야 사용가능
from .models import Board

# Create your views here.
# 게시글 제목, 내용, 작성자

def index(request):
    # Board 모델에 담긴 모든 글들을 가져와서 보여줌
    boards = Board.objects.all()
    context = {
        'boards' : boards
    }
    return render(request, 'index.html', context)

def new(request):
    return render(request, 'new.html')

def create(request):
    # new.html에서 입력한 data 받아옴
    title = request.GET['title']
    contents = request.GET['contents']
    creator = request.GET['creator']

    # new_board 객체를 만들어서 값을 대입
    '''
    new_board = Board()
    new_board.title = title
    new_board.contents = contents
    new_board.creator = creator
    '''
    #new_board = Board(title=title, contents=contents, creator=creator)
    # 값을 데이터베이스에 저장
    # new_board.save()
    # 위의 두 줄울 하나로 만드는 또 다른 방법
    new_board = Board.objects.create(title=title, contents=contents, creator=creator)
    return redirect(f'/boards/{new_board.id}')

def show(request, id): # id까지 같이 넘김
    # 파라미터로 넘어오는 값은 string이기 때문에 int로 형변환
    # 하지만, urls.py에서 '<int:id>/'로 해주면 여기에서 안 해도 됨
    # board = Board.objects.get(id=int(id))
    board = Board.objects.get(id=id) 
    context = {
        'board' : board
    }
    return render(request, 'show.html', context)

def edit(request, id):
    # 원래 있던 내용이 들어있는 form
    board = Board.objects.get(id=id) 
    context = {
        'board' :board
    }
    return render(request, 'edit.html', context)

def update(request, id):
    # 실제로 update가 일어나는 곳
    # 새로운 board를 만드는 것이 아니라, 안의 내용을 다 바꿔서 넣는 것
    board = Board.objects.get(id=id) 
    title = request.GET['title']
    contents = request.GET['contents']

    board.title = title
    board.contents = contents
    board.save()

    context = {
        'board' : board
    }
    return redirect(f'/boards/{board.id}')

def delete(request,id):
    board = Board.objects.get(id=id) 
    board.delete()
    return redirect('/boards')