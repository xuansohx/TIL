from django.contrib import admin
from django.urls import path, include
from . import views

app_name = 'articles'

urlpatterns = [
    # CRUD
    # C -> new, create
    # R -> index, show
    # U -> edit, update
    # D -> destroy

    # '' 빈 곳은 'articles/'가 기본
    # django는 URL 끝날 때마다 '/' 붙여주기!
    # URL namespace -> 뒤에 name 추가
    path('', views.index, name="index"),
    path('<int:id>/', views.show, name="show"),
    path('new/', views.new, name="new"),
    path('create/', views.create, name="create"),
    path('<int:id>/edit/', views.edit, name="edit"),
    path('<int:id>/update/', views.update, name="update"),
    path('<int:id>/delete/', views.delete, name="delete")
]