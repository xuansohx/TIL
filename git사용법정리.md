:memo: **git 사용법 정리** : 내가 사용해 본 명령어 위주

- 컴퓨터에서 git 로그인 하기

```
git config --global user.name "xuansohx"
git config --global user.email "email"
```

- 컴퓨터에 저장 된 계정 확인 및 로그아웃

```
git config --global --list 
git config --global --unset-all user.name
git config --global --unset-all user.email
```

- 저장소 만들기

```
mkdir 디렉토리명
cd 디렉토리명 → 디렉토리 접속
git init → 현재 디렉토리를 git의 저장소로 만듦

git remote add origin 저장소 주소
git push -u origin matster → 이후엔 git push만 하면 됨
```

- 파일 생성 및 추적하도록 명령

```
vim file.txt 
touch file.txt
→ 위의 명령어로 파일 생성

git add file.txt → 버전관리 시작
git status → 변경사항 확인
```

- 버전 관리하기

```
git status → 변동 사항이 있는지 저장소 상태 확인

git add 파일명 → 지정한 파일만 stage에 추가
git add . → 변경사항이 있는 모든 파일을 stage에 추가
git commit -m "message"
git push
```

- commit 관리 및 수정

```
git log -1 → 이전 commit 확인
git commit --amend -m "message" → 수정메시지 입력
git push -u origin master 
```

- 이미 push한 commit 삭제하기

```
git reset HEAD^ → 이전 한 개의 commit 취소
git reset HEAD~2 → 두 개의 commit 취소

git commit -m "message"
git push origin +master → '+'를 붙이면 에러를 무시하며 강제로 push
```

- [branch 생성하고 관리하기](https://github.com/xuansohx/study/blob/master/%EC%95%88%EC%86%8C%ED%98%84/%ED%95%99%EC%8A%B5%EB%82%B4%EC%9A%A9/190730.md)

- `.gitignore` 추가 및 적용 → [Eclipse를 개발 환경으로 사용하는 경우 등록 내용](https://github.com/xuansohx/study/blob/master/%EC%95%88%EC%86%8C%ED%98%84/.gitignore)

```
git rm -r --cached .
git add .
git commit -m "apply .gitignore" 
git push
```

> 특정 디렉토리에만 적용할 수도 있음

