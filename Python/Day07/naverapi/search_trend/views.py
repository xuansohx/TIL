from django.shortcuts import render

import json
import requests # 요청을 보내기 위하여 필요한 module

# Create your views here.
def search(request):
    # 검색어를 입력하는 곳
    return render(request, 'search.html')

def result(request):
    # 검색어에 대한 검색 트렌드를 받아보는 곳
    # search.html에서 넘어온 파라미터
    start_date = request.GET['search_start_date']
    end_date = request.GET['search_end_date']
    time_unit = request.GET['search_time_unit']
    group_name = request.GET['search_group_name']
    keywords = request.GET['search_keywords'].split(',')
    # 대괄호 주의
    # 파라미터로 넘어온 값을 context에 담아 넘김

    # Dictionary
    query = {
    "startDate": start_date,
    "endDate": end_date,
    "timeUnit": time_unit,
    "keywordGroups": [
        {
        "groupName": group_name,
        "keywords": keywords
        }
    ]
    }

    # 요청URL
    url = 'https://openapi.naver.com/v1/datalab/search'
    client_id = '*******'
    client_secret = '*******'

    headers = {
        'X-Naver-Client-Id' : client_id,
        'X-Naver-Client-Secret' : client_secret
    }

    # 파라미터를 JSON 형식으로 전달
    # ∴ Dictionary -> JSON
    params = json.dumps(query)

    response = requests.post(url, data=params, headers=headers)
    
    result = response.text
    context = {
        'result' : result
    }

    '''
    context = {
        'result':{
            'start_date':start_date,
            'end_date':end_date,
            'time_unit':time_unit,
            'group_name':group_name,
            'keyworkds':keywords
        }
    }
    '''
    return render(request, 'result.html',context)