from django.contrib import admin
from .models import Article

# Register your models here.
# admin page에서 Article을 관리하도록 등록
# models.py에서 만든 class를 등록하여 사용
admin.site.register(Article)
