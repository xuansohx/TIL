from django.db import models
from django.contrib.auth.models import AbstractUser

class User(AbstractUser):
    # 왜 AbstractUser를 상속 받는지 ???
    address = models.CharField(max_length=200)

