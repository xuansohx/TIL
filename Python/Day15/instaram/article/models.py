from django.db import models
from django.conf import settings
from django.contrib.auth.models import User

from imagekit.models import ImageSpecField, ProcessedImageField
from imagekit.processors import ResizeToFill, Thumbnail

from django import template
register = template.Library()

# Create your models here.

class Article(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)
    user_likes = models.ManyToManyField(settings.AUTH_USER_MODEL, related_name="article_likes")

    # 원본 이미지를 저장해두고
    # image = models.ImageField(blank=True)
    
    # 수정된 이미지도 따로 저장해서 사용
    # image_resized = ProcessedImageField(
    #     # source='image',
    #     upload_to='articles/images',
    #     processors=[ResizeToFill(200,200)],
    #     format='JPEG',
    #     options={'quality': 90}
    # )

    # 이미지의 썸네일을 생성해줌
    # media/CACHE에 원본 이미지의 썸네일을 자동생성
    # image_thumbnail = ImageSpecField(
    #     source='image',
    #     processors=[Thumbnail(200,200)],
    #     format='JPEG',
    #     options={'quality': 90}
    # )
    def comments(self):
        return Comment.objects.filter(article_id=self.id)
    def article_images(self):
        return ArticleImages.objects.filter(article_id=self.id)
    def is_permitted(self, target_id):
        return self.user_id == target_id

class ArticleImages(models.Model):
    article = models.ForeignKey(Article, on_delete=models.CASCADE)
    image = models.ImageField(blank=True)
    image_thumbnail = ImageSpecField(
        source='image',
        processors=[Thumbnail(300,300)],
        format='JPEG',
        options={'quality': 90}
    )

class Comment(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    article = models.ForeignKey(Article, on_delete=models.CASCADE)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)


class Board(models.Model):
    contents = models.CharField(max_length=16)
    created_at = models.DateTimeField(auto_now_add=True)

class HashTag(models.Model):
    tag = models.CharField(max_length = 16)
    article = models.ManyToManyField(Article, related_name="tags")