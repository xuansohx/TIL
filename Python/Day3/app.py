from flask import Flask, request, render_template
import requests

### Flask (app.py)
# pip install flask -> flask 설치 명령어

# Flask 환경설정
# $env:FLASK_ENV="Development"
# $env:FLASK_DEBUG="TRUE"
# DEBUG모드로 해야 서버를 껐다 켜지 않아도 됨

# flask run -> flask 실행 명령어

app = Flask(__name__)
if __name__ == '__main__':
    app.run(debug=True)

@app.route('/')
def index():
    # request.args -> Dictionary(Immutable)
    # Client로 부터 받은 파라미터를 저장하고 있음
    student = request.args.get('student')
    # 괄호 안에 파라미터 넣음
    return{'hello':student}

# localhost:5000/?파라미터=값

@app.route('/daum_webtoon')
def daum_toon_index():
    # 여기에 직접 html='''<a href=></a> ''' 이렇게 입력할 수도 있음
    days = ['mon','tue','wed','thu','fri','sat','sun']
    msg = "도비는 자유에요~~" # '**locals()' 말고 days로 넘기면 msg는 안 넘어감
    return render_template('daum_webtoon_list.html', days=days) 
    # -> 원하는 데이터만 전송하기 위함

    # return render_template('daum_webtoon_list.html', **locals()) 
    # 'templates' 폴더를 만들어줌 -> app.py와 같은 위치
    # 여기에 html 문서 만들어서 연결시켜줌
    
@app.route('/daum_webtoon/<day>')
def daum_toon(day):
    url = f'http://webtoon.daum.net/data/pc/webtoon/list_serialized/{day}'
    data = request_json_data_form_url(url)
    return {day: parse_daum_webtoon_data(data)} 
    
# 데이터 크롤링
def request_json_data_form_url(url):
    response = requests.get(url)
    data = response.json()
    return data 
# 데이터 뽑아내기    
def parse_daum_webtoon_data(data):
    toons = [] # 빈 배열
# 웹툰 전체(webtoon_data) 중 하나를 toon에 담아 봄
    for toon in data["data"]:
        # 제목의 key는 'title'
        title = toon["title"]
        # 설명의 key는 'introduction'
        desc = toon["introduction"]

        # 장르의 위치는 'cartoon' 안에 'genre'라는 리스트 안에  'name'이라는 key
        genres = [] # genre라는 빈 배열 -> 여기에 data를 넣을 것
        for genre in toon["cartoon"]["genres"]: # toon의 cartoon의 genres
            genres.append(genre["name"])

        #print(genres)

        # 작가의 위치는 'cartoon' 안에 'artists'라는
        artists = []
        for artist in toon["cartoon"]["artists"]:
            artists.append(artist["name"])

        # 썸네일 이미지의 위치는
        img_url = toon["pcThumbnailImage"]["url"] 

        tmp = {
            title : {
                "desc" : desc,
                "writer" : artists,
                "img_url" : img_url
            }
        }

        toons.append(tmp)
    return toons
