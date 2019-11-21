from django.urls import path, include
from . import views as boards_views

urlpatterns = [
    # urls.py가 boards 폴더 내에 있으므로 path에서 boards 안 써도 됨
    # 게시판의 메인 페이지, 전체 리스트
    path('', boards_views.index),
    # 게시판의 새 글을 작성하는 페이지
    path('new/', boards_views.new),
    path('create/', boards_views.create),
    # 게시판의 글 하나를 상세히 보는 페이지
    # 여기에서 id의 형을 정해주면, views.py에서 안 해줘도 됨
    # -> 이게 훨씬 간단함!
    path('<int:id>/', boards_views.show),
    path('<int:id>/edit', boards_views.edit),
    path('<int:id>/update', boards_views.update),
    path('<int:id>/delete', boards_views.delete)
]