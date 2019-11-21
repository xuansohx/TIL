from django.db import models

# Create your models here.
class Article(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    def comments(self):
        return Comment.objects.filter(article_id=self.id)

class Comment(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    # 게시글이 삭제가 되면 댓글도 함께 삭제되도록
    article = models.ForeignKey(Article, on_delete=models.CASCADE)