> Day3 배운 내용 :

Devlog : tramyu.github.io/etc/interview/

- parameter

  - query string
  - path parameter

- 웹툰 데이터를 요일별로 다르게 url 세우기

- html 파일로 view 만들기 (render template)

- Beautiful soup

  - 사이트 구조 분석하는 방법 (html은 어떻게 하는지)

  - URL 구조(query string) 분석하는 방법

  - 사람인 크롤링 해보기

  - 데이터가 XML 형태로 주고 받는 사이트 제외 모두 크롤링 가능 

    -> 단, 로그인 안한 상태에서 볼 수 있는 것만





```command
>pip install bs4
```



```
http://www.saramin.co.kr/zf_user/jobs/relay/view?isMypage=no&rec_idx=37214787&recommend_ids=eJxtkMERxDAIA6u5PyCM4H2FpP8ubi6JbTKT51oSYIFa6mUHnR9%2B0fDw6wEMH8eoPNEMIfGCt93EBLb1hXNclmKP2zjzkErs%2FMKmVz30C6eunult%2F0jLhkXKA2UvU0Br366hEdVOTRdtWVpYL2LwDVcvhXNzTF0c3X7jrCmK4LKrMvH%2FVfwA%2FAxfcQ%3D%3D&view_type=list&gz=1&t_ref_content=premium_recruit_fix&t_ref=jobcategory_recruit&t_ref_area=502#seq=0
```

