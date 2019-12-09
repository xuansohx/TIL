> Day15 배운 내용(191126) : 댓글 등록 | 댓글 삭제 및 수정
### 0. 지난 주 리뷰
- JavaScript는 속도의 문제 때문에 `<body>` 태그가 끝나기 직전에 선언
- `<style>`에서 틀을 그리고, `<script>`에서 이벤트 처리
- JavaScript에서 변수를 선언하는 방법 : var 변수명
- `$('#선택').click(function(){})`의 구조!
    - 요소를 선택해서 이벤트를 실행하는 것

- JS, jQuery
    - 요소를 찾아
    - 요소에 이벤트가 발생하는 것을 포착(Event Listener)
    - 이벤트가 발생했을 때 어떤 로직을 실행할 지 결정(Event Handler)
- AJAX (Asynchronous Javascript And XML)
    - JS를 사용하는 이유 중 하나는 AJAX를 이용하기 위하여
    - 비동기 JS & XML
    - Callback
    ``` html
    <script>
        $(function(){
            $.ajax({
                url : '어느 주소로 요청을 보낼지',
                method : '어떤 request method로 보낼지',
                data :{
                    key: '어떤 형태로 보낼지'
                },
                success : function(data){
                    '요청이 성공적으로 완료됐을 때'
                },
                error : function(data){
                    '요청이 정상적으로 완료되지 않았을 때'
                }
            })
        })
    ```

- jQuery와 AJAX 공부하기

- 이벤트가 발생하면
    - 방법1) JS로 HTML 요소를 추가한 다음, AJAX로 서버에 요청을 보내 실제 DB에 반영
    - 방법2) AJAX로 서버에 요청을 보내 실제 DB에 반영되고, JS로 HAML 요소 추가

### 1. 댓글 등록
- `models.py`
``` python
class Board(models.Model):
    contents = models.CharField(max_length=16)
    created_at = models.DateTimeField(auto_now_add=True)
```
> models.py를 추가했으면 DB에 적용 (makemigrations, migrate)
- `urls.py`에 path 추가
`path('jq-test/boards/', article_views.submit_boards, name="submit_boards")`

### 2. 댓글 삭제

### 3. 댓글 수정
- 수정 버튼을 누름
- 원래의 댓글 내용이 입력창에 들어감
- 확인버튼을 누르면 수정한 내용이 반영됨
    - 방법 1) 확인 버튼에 속성을 추가 -> 제출할 때 해당 속성의 유무를 파악하여 서로 다른 로직을 탈 수 있도록 함
    - 방법 2) 수정할 때 ajax로 제출하는 url 부분을 변수(HTML 속성)로 만들어서 처리

- jq_test.html
    - 수정버튼에 editBoard class 추가 & id 지정
    ``` python
     <button type="button" class="btn btn-warning editBoard" data-id="{{board.id}}">수정</button>
     ```
    - 

### 3. Auth(User)
- django는 `INSTALLED_APPS`에 auth(user)가 있음
- 

### Workshop
- form에 아이디를 주면 무조건 첫 번째만 찾아 -> 클래스 권장
- 댓글에 각각 아이디를 주면..? 
    - 내가 누른 위치의 댓글 아이디 찾는 법 -> `this` 활용
    - 이벤트가 발생한 위치에 있는 댓글


### 댓글 등록
``` html
<!-- index.html -->
<!-- 여러 개의 댓글 중 하나의 댓글 찾기 위해 class 지정 -->
         <form action="{% url 'comments' %}" method="POST" class="commentForm">
                        <input type="hidden" name="csrfmiddlewaretoken" value="{{csrf_token}}">
                        <input type="hidden" name="article_id" value="{{article.id}}">
                        <div class="row">
                            <div class="col-9">
                                <input type="text" class="form-control" name="contents" class="commentInput">
                            </div>
                            <div class="col-3 text-center">
                                <!-- 댓글을 등록하는 버튼 -->
                                <input type="submit" class="btn btn-primary" value="쓰기">
                            </div>
                        </div>
                    </form>
```

``` python
# views.py
# revised return type

def comments(request):
    if request.method == "POST":
        contents = request.POST["contents"]
        article_id = request.POST["article_id"]

        comment = Comment()
        comment.contents = contents
        comment.article_id = article_id
        comment.save()
        context = {
            'comment' : comment.contents,
            'comment_id' : comment.id
        }
        # json을 application으로 보내는 것은 django에서의 약속
        return HttpResponse(json.dumps(context), content_type="application/json")
```

- index.html에 69 line에 id 추가
    `<ul class="list-group list-group-flush" id="comment-list-group-{{article.id}}">`

``` html
// create comment
    // form으로부터 내용을 받아서 코멘트를 작성(DB에 반영)
    // article 별로 있는 form으로부터
    // 'submit'이라는 이벤트에
    $('.commentForm').on('submit',function(e){
        e.preventDefault();
        console.log("done");
        // 해당 form에 있는 input 중에서 텍스트를 입력한 input으로부터
        var contents = $(this).find('input.commentInput').val();
        // (어떤 article에 대한 댓글인지 알아야함)
        // <input> 태그의 articleID class에서 value를 꺼내옴
        var id = $(this).find('input.articleId').val();
        // 여기까지 필요한 값은 다 꺼내왔음
        // 내용을 받아서 create comment 역할을 하는 url로 요청을 보냄
        $.ajax({
            url: '{% url "comments" %}',
            method: 'POST',
            data: {
                contents: contents,
                // 위에서 id 변수를 만들어 저장해뒀음
                article_id: id,
                csrfmiddlewaretoken: '{{ csrf_token }}'
            },
            success: function(data){
                console.log("성공")
                console.log(data);
                // data로부터 내용을 꺼내 저장
                // `를 사용해서 data 불러옴
                var comment = `
                    <li class = "list-group-item">
                        <i class = "fas fa-comment-dots"></i>
                        ${data.comment}
                        <span class = "float-right">
                            <a href="" class="btn btn-warning text-white"><i class="fas fa-edit"></i></a>
                            <a href="" class="btn btn-danger text-white"><i class="fas fa-trash-alt"></i></a>
                        </span>
                    </li>
                `
                // 새로운 댓글 element를 추가
                $('#comment-list-group-'+data.article_id).append(comment);
            },
            error: function(data){
                console.log("실패")
            }
        })
    })
        
```

### 댓글 삭제
- 버튼에 class 적용 : `deleteComment`
    - `<a>`도 `<button>`으로 변경
    - `data-id="{{comment.id}}"` 추가

index에서 보낸거 views에서 다시 받아줌


### 댓글변경

### cookie와 session 
> 면접 단골 질문 : 정보 저장의 주체와 LifeCycle가 키워드
- cookie
    - 정보 저장의 주체 : 내 컴퓨터 / 브라우저
    - 누구나 열어볼 수 있음 -> 보안에 취약
    - 브라우저가 종료돼도 유지
- session 
    - 정보 저장의 주체 : 서버 컴퓨터
    - 브라우저가 종료되면 날아감

### AUTH
- admin 계정 만들기 : python manage.py createsuperuser
    - 사용자 이름 :
    - 이메일 :
    - 비밀번호 : 

