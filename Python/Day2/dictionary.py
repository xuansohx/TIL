import random
import json
import requests

student_phone_number = {
    # key : value
    "김민지" : "010-1234-1234"
}

# 배열의 a[0] 대신 a[key]로 꺼내줌
# a["김민지"]
# print(student_phone_number["김민지"])
# print(student_phone_number.get("김민지"))

lunch_menu = {
    "20층 식당" : {
        "A코스" : "돈까스",
        "B코스" : "순대국"
    },
    "양자강" : {
        "점심메뉴" : "탕짬면",
        "특선메뉴" : "군만두"
    },
    "대동집" : {
        "밥안주" : "비빔면",
        "술안주" : "오돌뼈"
    }
}

# key 값 이어서 쓰기 -> 20층 식당의 B코스
print(lunch_menu["20층 식당"]["B코스"])
# lunch_munt의 20층 식당 중 B코스를 가져오기
print(lunch_menu.get("20층 식당").get("B코스"))

# lunch_menu에 data 추가하기
# 새로운 key 값을 기입한 후,
lunch_menu["경성불백"] = {
    "한식메뉴" : "석쇠불고기",
    "특식메뉴" : "돈까스"
}

# 경성불백 추가 test
print(lunch_menu)

# key 값에 value 하나만 추가하기
lunch_menu["양자강"] = "짜장면"
# 양자강에 짜장면 추가 test -> 기존의 값 날아가고 짜장면만 남음! 주의!
print(lunch_menu)

# lunch_menu의 모든 key 값
print(lunch_menu.keys())
# lunch_menu의 value 값 
print(lunch_menu.values())
# 전체(key-value) 값 
print(lunch_menu.items())

### 반복문
# for a in b
# b는 순회대상(key, value, item), a는 한 번 순회할 때마다 데이터를 담아두는 곳
for key in lunch_menu.keys():
    print(key)

for value in lunch_menu.values():
    print(value)

# a 자리에 item만 적으면 데이터를 담아둘 곳이 부족
# 'key, value' 두 가지를 기입해야됨 -> 동시에 추출 가능
for key, value in lunch_menu.items():
    print(key)
    print(value)

# -> Python은 indent(들여쓰기)도 하나의 문법! for문 안에 indent 된 부분까지 scope
# start와 end 대신 indent로 구분

##3 random 
# import random -> 맨 앞에서 하기
# choice()는 하나, sample()은 여러개 중 내가 선택한 숫자만큼
print(random.choice(list(lunch_menu.keys())))
print(random.sample(lunch_menu.keys(),2)) 

'''
주석은 작은 따옴표 세 개
'''

### 조건문
'''
if 조건1 :
    # 조건 적을 때 괄호는 필요없음
    indent
    조건1의 실행문
elif 조건2 :
    조건2의 실행문
else :
    나머지 실행문
'''

### Review
# 2. 요청을 보내기 위한 url을 변수에 저장한다.
url = "http://webtoon.daum.net/data/pc/webtoon/list_serialized/fri"
# 3. 해당 url에 요청을 보낸다.
response = requests.get(url)

data = response.json()

print(data)
print(type(data))

### make 함수
'''
def 함수명(파라미터):
'''


### Flask (app.py)
# pip install flask -> flask 설치 명령어

# Flask 환경설정
# $env:FLASK_ENV="Development"
# $env:FLASK_DEBUG="TRUE

# flask run -> flask 실행 명령어