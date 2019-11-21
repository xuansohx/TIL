from django.shortcuts import render
import requests
import json

# Create your views here.
def main(request):
    # 주소를 검색하는 페이지
    return render(request, 'kakao_main.html')

def find_address(request):
    # main에서 검색한 검색어를 
    # 카카오 로컬 검색으로 검색한 결과를
    # 보여주는 페이지
    # + 키워드 입력하는 페이지
    url = f'https://dapi.kakao.com/v2/local/search/address.json'
    key = '*******' # REST API 키
    q = request.GET['address']
    params = {
        'query': q,
        'size': 30
    }
    headers = {
        'Authorization': f'KakaoAK {key}'
    }
    response = requests.get(url, params=params, headers=headers)

    address_data = json.loads(response.text)
    context = {
        'result': address_data["documents"]
    }

    return render(request, 'kakao_address.html', context)

def keyword_result(request):
    # 키워드를 입력하는 곳에서 입력한 키워드와
    # position(위,경도) 좌표를 추출해서
    # kakao api의 키워드 검색 api에 요청을 보낸다.
    keyword = request.GET['keyword']
    position = request.GET['position']
    # postion -> 126.94222541982484,37.54024517457614
    gps_x = position.split(',')[0]
    gps_y = position.split(',')[1]
    url = 'https://dapi.kakao.com/v2/local/search/keyword.json'
    key = '*******'
    params = {
        'query' : keyword,
        'x' : gps_x,
        'y' : gps_y
    }
    headers = {
        'Authorization' : f'KakaoAK {key}'
    }

    response = requests.get(url, params=params, headers=headers)
    context = {
        'result' : json.loads(response.text)["documents"]
    }
    
    return render(request, 'keyword_result.html', context)