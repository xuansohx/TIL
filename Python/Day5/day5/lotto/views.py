from django.shortcuts import render
import random
import requests
from bs4 import BeautifulSoup

# Create your views here.
def lotto(request): # request 넣어주는 것 규칙! 반드시!
    return render(request, 'lotto.html') # (request, 파일명) 이렇게 적는 것이 규칙

def winning(request):
    # 1. 1~45까지의 숫자 중에 n개의 숫자를 랜덤 추출
    # 1-1. 1~45까지의 번호를 가진 배열을 맏듦
    num_list = list(range(1,46)) # 1부터 45까지 담고있는 배열
    num_count = request.GET['count']
       
    # 1-2. 해당 배열에서 count 만큼의 숫자를 샘플링
    result = random.sample(num_list,int(num_count))
    result.sort() # 정렬

    # 2. 로또 당첨번호 공개 사이트로 가서 지난주 당첨번호 가져오기
    # -> 몇 회차인지, 언제 당첨번호인지, 1등 당첨금이 얼마인지
    url = 'https://dhlottery.co.kr/gameResult.do?method=byWin'
    response = requests.get(url)
    html = BeautifulSoup(response.text, 'html.parser')
    winning_numbers = html.select('div.win span')
    winning_count = 0
    winning_list = []
    for number in winning_numbers:
        # result list 변수에 number가 포함되어 있나요?
        winning_list.append(int(number.text))
        if int(number.text) in result: # result에 int가 있는지
            winning_count += 1

    return render(request, 'winning.html', {'result' : result,
                                            'winning_list' : winning_list,
                                            'winning_count': winning_count})


