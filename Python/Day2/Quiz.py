import requests
import json
import time

# 1. 평균을 구하세요.
scores = {
    "수학" : 90,
    "영어" : 87,
    "한국지리" : 92
}

# 1번 solution
# 배열의 길이를 구하는 함수는 len(배열)
# 반올림 하는 함수는 round(숫자, 소숫점)

total = 0
for value in scores.values():
    total += value
ave = total/len(scores)
#print(round(ave,1))


# 2. 각 학생의 평균 점수와 반 평균을 구하세요.
scores = {
    "a학생" :{
        "수학" : 80,
        "국어" : 90,
        "음악" : 100
    },
    "b학생" : {
        "수학" : 90,
        "국어" : 90,
        "음악" : 60
    }
}

# 2번 solution
a = 0
b = 0

for score_a in scores.get("a학생").values():
    a += score_a
ave_a = a/len(scores.get("a학생"))
#print(round(ave_a,1))
       
for score_b in scores.get("b학생").values():
    b += score_b
ave_b = b/len(scores.get("b학생"))
#print(round(ave_b,1))


# 3. 다음 웹툰의 금요일 웹툰 전체의 리스트 중에서 
# 각 웹툰의 제목, 설명, 작가 이름, 장르, 썸네일 이미지(주소)만 골라
# 새로운 dictionary를 만들고 이 dictionary를 담고 있는 list를 만드세요.

'''
Title : key 값{
    설명(desc)
    작가(writer)
    장르(genre)
    이미지주소(imgurl)
    }
'''

# 금요일 웹툰의 url 주소
url = "http://webtoon.daum.net/data/pc/webtoon/list_serialized/fri"

# url의 data를 가져옴
response = requests.get(url)

# 응답 온 url을 바로 json으로
data = response.json()

#
'''
for d in data.keys():
    prind(d)
'''
# key가 data이기 때문에 data로 뽑아냄
webtoon_data = data["data"]

toons = [] # 빈 배열
# 웹툰 전체(webtoon_data) 중 하나를 toon에 담아 봄
for toon in webtoon_data:
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

#print(toons)


# 3-1. 금요일 뿐만 아니라 월요일부터 일요일까지의 웹툰 데이터를 파싱해서
# 각각 dictionary로 만드세요.
# url을 일곱 번 보내는 것 아님 -> 하나의 url로 월화수목금토일 어떻게?
# -> format string 활용

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

# 월요일부터 일요일까지 각각을 나타내는 문자열을 정함
days = ['mon','tue','wed','thu','fri','sat','sun']
daily_toon_data = {}

for day in days:
    print(day)
    url = f'http://webtoon.daum.net/data/pc/webtoon/list_serialized/{day}'
    data = request_json_data_form_url(url)
    daily_toon_data[day] = parse_daum_webtoon_data(data)
    print(daily_toon_data)
    time.sleep(3)