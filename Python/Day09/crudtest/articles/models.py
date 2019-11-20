from django.db import models

# Create your models here.
class Article(models.Model):
    title = models.CharField(max_length=16)
    contents = models.TextField()
    creator = models.CharField(max_length=8)
    # 맨 처음에 한 번만 추가
    created_at = models.DateTimeField(auto_now_add=True, null=True) 
    # 수정할 때마다 계속 바뀜
    updated_at = models.DateTimeField(auto_now=True, null=True) 

    def __str__(self):
        return f'[{self.title}] - created by {self.creator} at {self.created_at}'

    def created_by(self):
        return "created by" + self.creator

    def datetime_to_string(self):
        return self.created_at.strftime("%Y-%m-%d")

