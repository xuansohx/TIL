from django.db import models

# Create your models here.
# 뼈대를 만드는 과정
class Board(models.Model):
    title = models.CharField(max_length=30)
    contents = models.TextField()
    create_by = models.CharField(max_length=10, null=True)
