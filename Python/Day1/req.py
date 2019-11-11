# 1. 요청을 보내기 위한 requests 모듈을 import 한다.
# 모듈이 없을 경우 'pip install requests'를 실행
import requests
# 2. 요청을 보내기 위한 url을 변수에 저장한다.
url = "http://webtoon.daum.net/data/pc/webtoon/list_serialized/fri"
# 3. 해당 url에 요청을 보낸다.
response = requests.get(url)
# 4. 응답을 출력한다.
# print(response)는 응답을 확인하는 것
# 뒤에 .text를 붙이면 응답 받은 data를 출력하는 것
print(response.text)