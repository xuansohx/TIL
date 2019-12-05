from django.db import models
from faker import Faker
# user는 직접 참조하지 못 하므로 settings를 우선 settings를 import
from django.conf import settings
f = Faker()

class Article(models.Model):
    author = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)
    title = models.CharField(max_length=300)
    keyword = models.CharField(max_length=50)
    email = models.CharField(max_length=200)
    content = models.TextField()
    # datetime = models.DateField()
    date = models.DateField(blank=True, null=True)
    like_users = models.ManyToManyField(settings.AUTH_USER_MODEL,related_name='like_articles')
    # article_set
    # DB의 column 이름 아님 -> 속성 값을 적은 것임 
    # ManyToManyField로 article과 user과 엮일 수 있는 것???

    created_at = models.DateTimeField(auto_now_add = True)
    updated_at = models.DateTimeField(auto_now = True)

    @classmethod
    def dummy(cls, n):
        for i in range(n):
            cls.objects.create(
                title=f.text(20),
                content=f.text(),
                keyword=f.company(),
                email=f.email(),
                author_id=1,
            )

class Comment(models.Model):
    author = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)
    article = models.ForeignKey(Article, on_delete=models.CASCADE)
    
    content = models.CharField(max_length=300)
    created_at = models.DateTimeField(auto_now_add = True)
    updated_at = models.DateTimeField(auto_now = True)