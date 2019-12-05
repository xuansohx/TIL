from django import forms
# from django.db import models
from .models import Article, Comment

# ArticleForm -> 케이스 같은 것
# 추가적인 기능 & 외부로부터 보호
# django forms valdation
class ArticleForm(forms.ModelForm): 
    # model은 data를 받아주지만,
    # case에서 필터링하여 받음
    title = forms.CharField(min_length=2, strip=True)
    email = forms.EmailField()
    # keyword = forms.CharField(min_length=1, max_length=10)
    class Meta: # django 내부에서 제공하는 form...?
        model = Article
        #fields = '__all__'

        # 일부만 전송? 이용? 하고 싶으면 아래와 같이 입력
        # title과 content만 input 할 수 있는 form이 출력
        # fields = ('title', 'content')

        # 하나만 빼고 다 쓰고 싶은 경우
        # date만 빼고 form이 생성돼 출력
        exclude = ['date', 'author', 'like_users']

class CommentForm(forms.ModelForm):
    content = forms.CharField(min_length=1, max_length=200)
    class Meta:
        model = Comment
        # fields = '__all__'
        fields = ['content',]