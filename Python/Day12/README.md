> Day12 배운 내용(191121) : Static File 관리 | 이미지 업로드(+ Multiple) | JS 기본
- Static File
    - **개발환경** vs 배포환경
- 이미지 업로드
    - 모델 하나에 직접 입력
    - 이미지 리사이징
    - 이미지 썸네일
- Multiple 이미지 업로드
    - 하나의 Article에 여러 이미지 업로드 하기
- JS(JavaScript) 기본
    - 하나의 페이지를 동적으로 만듦
    - jQuery -> JS프레임워크(x), JS라이브러리(O)

### 1. Static File
- Static File은 사용자가 업로드한 이미지가 아니라, 개발자가 기본으로 제공하는 이미지
- settings.py
``` python
# Static files (CSS, JavaScript, Images)
# https://docs.djangoproject.com/en/2.2/howto/static-files/

STATIC_URL = '/static/'
```
- 배포환경에서는 static file의 경로를 자세히 알려주면 안됨
    ∴ 실제 위치와 파일이 보여지는 위치를 다르게 설정
- 이미지 불러오기
    - index.html
    ``` python
    # 맨 위에 추가
    {% load static %}
    # instagram > article > static > images에 있는 이미지 파일 불러오기
     <img src="{% static '/article/images/SAM_2722.JPG' %}" alt="SAM_2722.JPG" class="card-img-top">
     ```
    - static file은 새롭게 추가되거나 폴더 구조를 바꿨을 경우, 서버를 재시작 해야 됨
- 이미지 업로드
    - 이미지 업로드를 위한 form
    ``` python
    <div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
  </div>
  <div class="custom-file">
    <input type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
    <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
  </div>
</div>
    ```
    - `<form>`에 enctype 추가
    ``` python
    <form action="{% url 'articles' %}" method="POST" enctype="multipart/form-data">
    </form>
    ```

- models.py의 Article에
이미지 필드 추가
- pip install Pillow

- 우리가 원하는 위치에 이미지가 업로드 되도록 설정
settings.py
``` python
# Static files (CSS, JavaScript, Images)
# https://docs.djangoproject.com/en/2.2/howto/static-files/

STATIC_URL = '/static/'

# STATIC FILE이 실제 저장되는 위치
MEDIA_ROOT = os.path.join(BASE_DIR, 'media')
# 사용자에게 보여지는 위치
MEDIA_URL = '/media/'
```
- basic urls.py
``` python
from django.contrib import admin
from django.urls import path, include
from django.conf import settings
from django.conf.urls.static import static

urlpatterns = [
    path('admin/', admin.site.urls),
    path('insta/', include('article.urls'))
]

# 우리가 보는 static file의 URL을 만들어줌?
urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
```

- 이미지 크기 맞추기
    - `resizetofill` : 설정한 사이즈에 딱 맞춰서 이미지 크기 수정 -> 가로가 긴 경우, 좌우 영역을 삭제
    - `resizetpFit` : 비율 유지한 채로 이미지 수정 -> 가로가 긴 경우, 사이즈에 맞추고 위아래 여백을 만듦
- models.py
``` python

```

``` command
> pip install django-imagekit
```
- settings.py
INSTALLED_APPS에 imagekit 추가


- 이미지 여러개 < >
    - multiple 이용