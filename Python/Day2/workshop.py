# 내가 자주 접속하는 사이트들 중에 우리가 좀 전에 배운 형태로 되어 있는 사이트를 찾아서
# 필요한 정보들만 뽑아서 화면에 출력하시오
import json
import requests

# 다음 영화의 URL 주소를 변수에 저장
url = "https://movie.daum.net/data/movie/movie_info/ticket_rank.json"

# 해당 url에 요청
response = requests.get(url)
data = response.json()
#print(data) 데이터 받아오기 성공

movie_data = data["data"]

# 빈 배열
movies = []
for movie in movie_data:
    title = movie["titleKo"]
    year = movie["prodYear"]
    desc = movie["plot"]

    genres = []
    for genre in movie["genres"]:
        genres.append(genre["genreName"])
    
    score = movie["rank"]["ranking"]

    tmp = {
        title : {
            "year" : year,
            "desc" : desc,
            "genre" : genre,
            "rank" : score
        }
    }

    movies.append(tmp)

print(movies)