# board/urls.py
from django.urls import path
from . import views

app_name = 'board'
urlpatterns = [
    # 아무 말 없이 들어오면 여기로
    path('', views.article_list, name='article_list'),
    path('<int:article_id>/', views.article_detail, name='article_detail'),
    path('new/', views.new_article, name='new_article'),
    path('<int:article_id>/edit/', views.edit_article, name='edit_article'),
    path('<int:article_id>comments/new/', views.new_comment, name='new_comment'),
    # board/1/comments/new <- 여기로 가면 comment를 쓸 수 있도록
    path('<int:article_id>/like/',views.toggle_like, name="toggle_like"), # board/1/like/
]
