from flask import Flask, escape, request
import json
import requests

# pip install flask -> flask 설치 명령어
# flask run -> flask 실행 명령어
# $env:FLASK_ENV="Development"
# $env:FLASK_DEBUG="TRUE"

# 브라우저에서 'localhost:5000'로 확인

# Flask 실행 할 준비
app = Flask(__name__)
# how to change environment -> production 모드 해제하기 위함
if __name__ == '__main__':
    app.run(debug=True)

@app.route('/')
def index():
    daily_toon_data = {}
    day = 'mon' 
    url = f'http://webtoon.daum.net/data/pc/webtoon/list_serialized/{day}'
    data = request_json_data_form_url(url)
    daily_toon_data[day] = parse_daum_webtoon_data(data) 

    return daily_toon_data

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

  
