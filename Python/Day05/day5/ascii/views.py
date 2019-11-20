from django.shortcuts import render
import requests

# Create your views here.
def ascii(request):
    # 입력하고자 하는 text를 받아야함
    # artii에서 제공하는 폰트 중 선택
    url = 'http://artii.herokuapp.com/fonts_list'
    response = requests.get(url)
    fonts_list = response.text.split('\n')
    context = {
        'fonts': fonts_list
    }
    return render(request, 'ascii.html', context)

def result(request):
    # ascii에서 입력한 텍스트와 폰트를
    font = request.GET['font']
    text = request.GET['text']
    # artii에 보내서 결과값을 받아서 보여줌
    url=f'http://artii.herokuapp.com/make?font={font}&text={text}'
    response = requests.get(url)
    context = {
        'result' : response.text
    }
    return render(request, 'result.html', context)
