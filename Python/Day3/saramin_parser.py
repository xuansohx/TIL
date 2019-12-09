from bs4 import BeautifulSoup
import requests

url = "http://www.saramin.co.kr/zf_user/jobs/list/job-category?cat_cd=404&panel_type=&search_optional_item=n&search_done=y&panel_count=y"
response = requests.get(url)

# BeautifulSoup이 응답 온 html 문서를 읽음
html = BeautifulSoup(response.text, 'html.parser')

### 방법1) 요소 하나 선택하여 전부 다 읽어서 찾음
'''
company_names = html.select('.company_name')
recruit_names = html.select('.recruit_name')
recruit_conditions = html.select('.list_recruit_condition')
'''

'''
for company_name in company_names:
    print(company_name.text)
    # tag 빼고 내용만 출력하기 위하여 '.text' 붙임
for recruit_name in recruit_names:
    print(recruit_name.text)
'''

'''
print(len(company_names))
print(len(recruit_names))
'''

# 두 개의 반복문 합침 + 하나 더
'''
for company_name, recruit_name, condition in zip(company_names, recruit_names, recruit_conditions):
    #print(f'{company_name.txt}-{recruit_name.txt}-{condition}')
    #print(condition.text)
'''

### 방법2) 하나의 객체 선택하여 각 요소를 읽어서 찾아 옴 
'''
company = html.select('.part_top')
for com in company:
    print(f'{com.select_one(".company_name").text}- {com.select_one(".recruit_name").text}-{com.select_one(".list_recruit_condition").text}')
    #print(com.select_one('.list_recruit_condition').text) # -> 세부사항 요약
    break
'''

### 데이터 크롤링 하여 내가 원하는 데이터 추출하기
company_list = html.select('ul.product_list li')
for com in company_list:
    idx = com.select_one('a')['href'].split('=')[-1]
    company_info_url = 'http://www.saramin.co.kr/zf_user/jobs/relay/view-ajax'
    company_info_params = { 'rec_idx': idx }
    company_response = requests.post(company_info_url, params=company_info_params)
    print(company_response) # 응답체크 - 200 (정상)
    company_html = BeautifulSoup(company_response.text, 'html.parser')
    company_title = company_html.select_one('a.company').text
    print(company_title.strip()) # '.strip()'을 하면 공백 제거하고 출력 
    break



