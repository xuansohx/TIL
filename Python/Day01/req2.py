# 1. 요청을 보내기 위한 requests 모듈을 import 한다.
import requests
# 2. url에 주소를 저장한다.
url ="https://ticket.melon.com/offer/ajax/offerList.json?offerPosType=MAIN_B_CO_1"

# 3. 요청을 보내고 응답을 변수에 저장한다.
# headers={} 추가하여 error 해결 가능
headers = {
    # User-Agent를 통하여 정상적인 브라우저인지 확인?
    'User-Agent' : 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36'
}
response = requests.get(url, headers=headers)
# 4. 변수에 저장된 내용을 출력한다.
print(response.text) 
 # 응답확인 -> 406 error 발생 -> Forbidden
 # 각 부분을 출력해보기
 # [parameter 부분을 잘 확인할 것]

# type 마다 다른 data 출력
# url ="https://ticket.melon.com/offer/ajax/offerList.json?offerPosType=MAIN_B_CO_2"
# url ="https://ticket.melon.com/offer/ajax/offerList.json?offerPosType=MAIN_B_CO_3"

# 내일은 원하는 data만 추출하는 것 할 예정
