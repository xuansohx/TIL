from django.shortcuts import render, redirect, get_object_or_404
# 팔찌를 채워주는 것(login) <-> logout / def login과 중복되므로 as 이용하여 이름 재정의
from django.contrib.auth import login as auth_login, logout as auth_logout

# from .forms import AuthenticationForm, UserCreationForm
from .forms import CustomAuthenticationForm, CustomUserCreationForm

def follow(request, user_id):
    fan = request.user
    star = get_object_or_404(id=user_id)
    if fan.stars.filter(id=star.id).exists():
        fan.stars.remove(star)
    else:
        fan.stars.add(star)
    return redirect('accounts:user_detail', star.id)

def signup(request):
    # 요청을 보낸 사용자가 인증이 되어 있지 않으면 -> 회원가입
    if not request.user.is_authenticated:
        if request.method == 'POST':
            # form = UserCreationForm(request.POST)
            form = CustomUserCreationForm(request.POST)
            if form.is_valid():
                user = form.save()
                # 왜 로그인 할 때 인자가 두개?
                # request는 사용자가 보내는 모든 요청 -> 요청이 있을 때마다 확인
                # cookies를 통하여 사용자가 누구인지 확인
                auth_login(request, user) 
                return redirect('board:article_list')
        else: # GET
            # form = UserCreationForm()
            form = CustomUserCreationForm()
        context = {'form' : form}
        return render(request, 'accounts/signup.html', context)
    else:
        return redirect('board:article_list')

def login(request):
    if request.user.is_authenticated:
        return redirect('board:article_list')
    else:
        if request.method == 'POST':
            # form = AuthenticationForm(request, request.POST)
            form = CustomAuthenticationForm(request, request.POST)
            if form.is_valid(): # 검증
                # 인증을 한 후, save가 아니라 get_user 
                # 누군지 가져오기
                user = form.get_user()
                # user 인증되면 팔찌를 채워
                # cash에 'sessionid' 추가된 것으로 확인
                auth_login(request, user)
                # -> 이 상태에서는 회원가입 페이지로 이동하지 못 함
                # return redirect('board:article_list')
                return redirect(request.GET.get('next') or 'board:article_list')
                # key 값 get 하는 법
                # request.GET.get('next') -> 찾았는데 없으면 없다고 말해줌('null'로 return)
                # request.GET['next'] -> 없는 index 접근하면 error
        else: # GET
            # form = AuthenticationForm()
            form = CustomAuthenticationForm()
        context = {'form' : form}
        return render(request, 'accounts/login.html', context) 

def logout(request):
    auth_logout(request)
    return redirect('board:article_list')