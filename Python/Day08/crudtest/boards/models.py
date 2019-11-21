from django.db import models

# Create your models here.
class Board(models.Model):
    objects = models.Manager()
    # CharField는 반드시 'max_length' 지정하기
    title = models.CharField(max_length=32)
    contents = models.TextField()
    creator = models.CharField(max_length=16)