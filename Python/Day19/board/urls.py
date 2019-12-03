# board/urls.py
from django.urls import path
from . import views

app_name = 'board'
urlpatterns = [
    # 아무 말 없이 들어오면 여기로
    path('', views.article_list, name='article_list'),
    path('<int:article_id>/', views.article_detail, name='article_detail'),
    path('new/', views.new_article, name='new_article')
]
