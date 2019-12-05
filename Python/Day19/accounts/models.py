from django.db import models
from django.contrib.auth.models import AbstractUser

class User(AbstractUser):
    # 왜 AbstractUser를 상속 받는지 ???
    address = models.CharField(max_length=200)
    # 팔로우와 팔로워 단어 헷갈리니까 fans와 stars로 기입
    fans = models.ManyToManyField('self', related_name='stars')

